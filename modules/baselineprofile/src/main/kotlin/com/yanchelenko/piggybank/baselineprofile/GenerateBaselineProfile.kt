package com.yanchelenko.piggybank.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Baseline profile generator that warms up critical user flows.
 * Target flows: Scanner → History → Scanner (home).
 * Keep selectors and timeouts centralized to improve readability.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class GenerateBaselineProfile {

    @get:Rule
    val baselineRule = BaselineProfileRule()

    // Centralized semantic tags and ids
    private object Tags {
        const val NAV_SCANNER = "nav_scanner"
        const val NAV_HISTORY = "nav_history"

        const val SCANNER_PREVIEW = "scanner_preview"
        const val SCANNER_PERMISSION = "scanner_permission_text"
        const val SCANNER_ROOT = "scanner_root"

        const val HISTORY_LIST = "history_list"
        const val HISTORY_ROOT = "history_root"
        const val HISTORY_EMPTY = "history_empty"
        const val HISTORY_ITEM_PREFIX = "history_item_"
    }

    // Centralized timeouts and small sleeps to avoid magic numbers
    private object Times {
        const val SHORT: Long = 2_000
        const val MED:   Long = 3_000
        const val LONG:  Long = 5_000

        const val NAP_SHORT: Long = 150
        const val NAP_MED:   Long = 200
    }

    @Test
    fun generate() = baselineRule.collect(packageName = APP_ID) {
        wakeAndUnlock()
        pressHome()                 // НЕ делаем killProcess() — пусть rule управляет

        grantRuntimePermissions()   // на случай системных диалогов

        // Старт с ретраями (без force-stop)
        startLauncherWithRetry(retries = 2, waitTopMs = 10_000)

        // На всякий — прикроем разрешалку, если всплыла
        allowPermissionDialogIfShown()

        // ===== прогрев =====
        warmUpScanner()
        warmUpHistory()
        returnToScanner()
    }

    // ---------- запуск с ретраями ----------
    private fun startLauncherWithRetry(retries: Int, waitTopMs: Long) {
        repeat(retries + 1) { attempt ->
            // Явный старт MainActivity через am (обход проблем amStartAndWait)
            device.executeShellCommand(
                "am start -W -n $APP_ID/$LAUNCHER_ACTIVITY -a android.intent.action.MAIN -c android.intent.category.LAUNCHER"
            )

            if (waitAppOnTop(APP_ID, waitTopMs)) return
            // вдруг висит разрешение — закроем и попробуем ещё раз
            allowPermissionDialogIfShown(timeoutMs = 1500)
            device.waitForIdle()
            sleep(300)
        }
        diagDumpAndFail("App window for $APP_ID did not reach top after retries")
    }

    /** Warm up Scanner screen (preview/permission path). */
    private fun warmUpScanner() {
        sleep(Times.NAP_MED)
        clickIfExists(desc = Tags.NAV_SCANNER, timeout = Times.MED)
        // ждём любой из возможных семантик на экране сканера
        waitAnyDesc(Times.MED, Tags.SCANNER_PREVIEW, Tags.SCANNER_PERMISSION, Tags.SCANNER_ROOT)
        allowPermissionDialogIfShown()
        device.waitForIdle()
        sleep(Times.NAP_SHORT)
    }

    /** Warm up History: open list, scroll, open an item and return back. */
    private fun warmUpHistory() {
        if (!clickNavHistory()) {
            println("WARN: nav_history not found by common selectors")
        }
        waitAnyDesc(Times.LONG, Tags.HISTORY_LIST, Tags.HISTORY_ROOT, Tags.HISTORY_EMPTY)
        val didScroll = scrollDescOrSwipe(Tags.HISTORY_LIST, downTimes = 3, upTimes = 2)
        if (!didScroll) println("WARN: history_list not found, used screen swipe fallback")
        clickIfExists(descStartsWith = Tags.HISTORY_ITEM_PREFIX, timeout = Times.SHORT)
        device.waitForIdle()
        device.pressBack()
        device.waitForIdle()
    }

    /** Return to Scanner so that profile captures the main/home screen. */
    private fun returnToScanner() {
        clickIfExists(desc = Tags.NAV_SCANNER, timeout = Times.MED)
        waitAnyDesc(Times.MED, Tags.SCANNER_PREVIEW, Tags.SCANNER_ROOT)
        device.waitForIdle()
        sleep(Times.NAP_MED)
    }

    // ---------- helpers ----------
    private fun waitAppOnTop(appId: String, timeoutMs: Long): Boolean {
        val start = System.currentTimeMillis()
        do {
            val current = device.currentPackageName
            if (current == appId) return true
            if (current == PERMISSION_CONTROLLER) {
                allowPermissionDialogIfShown(timeoutMs = 1200)
            }
            device.waitForIdle()
            sleep(200)
        } while (System.currentTimeMillis() - start < timeoutMs)
        return false
    }

    private fun wakeAndUnlock() {
        device.executeShellCommand("input keyevent 224") // WAKEUP
        device.executeShellCommand("wm dismiss-keyguard")
        device.executeShellCommand("input keyevent 82")  // MENU
        device.waitForIdle()
    }

    private fun grantRuntimePermissions() {
        device.executeShellCommand("pm grant $APP_ID android.permission.CAMERA")
        device.executeShellCommand("pm grant $APP_ID android.permission.POST_NOTIFICATIONS")
    }

    private fun allowPermissionDialogIfShown(timeoutMs: Long = 3000) {
        device.wait(Until.hasObject(By.pkg(PERMISSION_CONTROLLER)), timeoutMs)

        val ids = listOf(
            "$PERMISSION_CONTROLLER:id/permission_allow_button",
            "$PERMISSION_CONTROLLER:id/permission_allow_foreground_only_button",
            "$PERMISSION_CONTROLLER:id/permission_allow_one_time_button",
            "$PERMISSION_CONTROLLER:id/permission_allow_always_button",
            "$PERMISSION_CONTROLLER:id/continue_button"
        )
        for (res in ids) {
            device.findObject(By.res(res))?.let { it.click(); device.waitForIdle(); return }
        }

        val texts = listOf(
            "Allow", "Allow only while using the app", "Only this time",
            "Разрешить", "Разрешить только при использовании приложения", "Только сейчас"
        )
        for (t in texts) {
            device.findObject(By.textContains(t))?.let { it.click(); device.waitForIdle(); return }
        }
    }

    /**
     * Clicks by exact contentDescription or prefix (if present) within [timeout].
     * No-op if the selector is not found.
     */
    private fun clickIfExists(desc: String? = null, descStartsWith: String? = null, timeout: Long = 2_000) {
        val sel = when {
            desc != null -> By.desc(desc)
            descStartsWith != null -> By.descStartsWith(descStartsWith)
            else -> return
        }
        device.wait(Until.hasObject(sel), timeout)
        device.findObject(sel)?.click()
    }

    private fun clickNavHistory(timeout: Long = 3_000): Boolean {
        val selectors = listOf(
            By.res("$APP_ID:id/nav_history"),
            By.res("$APP_ID:id/history"),
            By.res("$APP_ID:id/menu_history"),
            By.desc(Tags.NAV_HISTORY),
            By.text("History"),
            By.text("История")
        )
        for (sel in selectors) {
            device.wait(Until.hasObject(sel), timeout)
            device.findObject(sel)?.let {
                it.click()
                device.waitForIdle()
                return true
            }
        }
        // Fallback: try to click 2nd item of a known BottomNavigation container
        val bottoms = listOf(
            By.res("$APP_ID:id/bottom_bar"),
            By.res("$APP_ID:id/bottomNavigation"),
            By.res("$APP_ID:id/navigation"),
        )
        for (b in bottoms) {
            val bar = device.findObject(b)
            val clicked = bar?.children?.getOrNull(1)?.also { it.click() } != null
            if (clicked) {
                device.waitForIdle()
                return true
            }
        }
        return false
    }

    private fun waitDesc(desc: String, timeout: Long) {
        device.wait(Until.hasObject(By.desc(desc)), timeout)
    }

    private fun scrollDesc(listDesc: String, downTimes: Int, upTimes: Int) {
        device.wait(Until.hasObject(By.desc(listDesc)), Times.SHORT)
        val list = device.findObject(By.desc(listDesc)) ?: return
        repeat(downTimes) { list.safeScroll(Direction.DOWN) }
        repeat(upTimes) { list.safeScroll(Direction.UP) }
    }

    /**
     * Tries to scroll a list by contentDescription. If not found, does screen swipes as fallback.
     * @return true if list scroll happened, false if fallback swipe used.
     */
    private fun scrollDescOrSwipe(listDesc: String, downTimes: Int, upTimes: Int): Boolean {
        device.wait(Until.hasObject(By.desc(listDesc)), Times.SHORT)
        val list = device.findObject(By.desc(listDesc))
        return if (list != null) {
            repeat(downTimes) { list.safeScroll(Direction.DOWN) }
            repeat(upTimes) { list.safeScroll(Direction.UP) }
            true
        } else {
            // Fallback: swipe on screen center to simulate list scroll
            val display = device.displayWidth to device.displayHeight
            val (w, h) = display
            val x = w / 2
            repeat(downTimes) {
                device.drag(x, (h * 0.75f).toInt(), x, (h * 0.25f).toInt(), 24)
                device.waitForIdle()
                sleep(Times.NAP_SHORT)
            }
            repeat(upTimes) {
                device.drag(x, (h * 0.25f).toInt(), x, (h * 0.75f).toInt(), 24)
                device.waitForIdle()
                sleep(Times.NAP_SHORT)
            }
            false
        }
    }

    private fun UiObject2.safeScroll(direction: Direction) {
        val r = visibleBounds
        val startX = r.centerX()
        val (startY, endY) = when (direction) {
            Direction.DOWN -> (r.bottom - (r.height() * 0.25f)).toInt() to (r.top + (r.height() * 0.25f)).toInt()
            Direction.UP   -> (r.top + (r.height() * 0.25f)).toInt()    to (r.bottom - (r.height() * 0.25f)).toInt()
            else -> return
        }
        device.drag(startX, startY, startX, endY, 24)
        device.waitForIdle()
        sleep(100)
    }

    private fun waitAnyDesc(timeout: Long, vararg descs: String): Boolean {
        val deadline = System.currentTimeMillis() + timeout
        while (System.currentTimeMillis() < deadline) {
            for (d in descs) {
                device.findObject(By.desc(d))?.let { return true }
            }
            device.waitForIdle()
            sleep(Times.NAP_SHORT)
        }
        return false
    }

    private fun diagDumpAndFail(msg: String): Nothing {
        val top = device.currentPackageName
        val resumed = device.executeShellCommand("dumpsys activity activities | grep mResumedActivity -n")
        val frames = device.executeShellCommand("dumpsys gfxinfo $APP_ID framestats")
        val log = device.executeShellCommand("logcat -d -t 200 | tail -n 200")
        println("=== DIAG ===\ncurrent=$top\nresumed=$resumed\nframestats:\n$frames\n---logcat tail---\n$log")
        error(msg)
    }

    private fun sleep(ms: Long) = Thread.sleep(ms)

    private val device: UiDevice
        get() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private companion object {
        const val APP_ID = "com.yanchelenko.piggybank"
        const val LAUNCHER_ACTIVITY = "com.yanchelenko.piggybank.MainActivity"
        const val PERMISSION_CONTROLLER = "com.android.permissioncontroller"
    }
}