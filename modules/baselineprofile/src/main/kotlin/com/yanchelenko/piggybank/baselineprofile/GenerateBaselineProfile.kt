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

@LargeTest
@RunWith(AndroidJUnit4::class)
class GenerateBaselineProfile {

    @get:Rule
    val baselineRule = BaselineProfileRule()

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
        sleep(200)
        clickIfExists(desc = "nav_history", timeout = 3_000)
        waitDesc("history_list", 3_000)
        scrollDesc("history_list", downTimes = 3, upTimes = 2)
        clickIfExists(descStartsWith = "history_item_", timeout = 2_000)
        device.pressBack()
        device.waitForIdle()
        clickIfExists(desc = "nav_scanner", timeout = 3_000)
        device.waitForIdle()
        sleep(200)
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
        // при необходимости медиа:
        // device.executeShellCommand("pm grant $APP_ID android.permission.READ_MEDIA_IMAGES")
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

    private fun clickIfExists(desc: String? = null, descStartsWith: String? = null, timeout: Long = 2_000) {
        val sel = when {
            desc != null -> By.desc(desc)
            descStartsWith != null -> By.descStartsWith(descStartsWith)
            else -> return
        }
        device.wait(Until.hasObject(sel), timeout)
        device.findObject(sel)?.click()
    }

    private fun waitDesc(desc: String, timeout: Long) {
        device.wait(Until.hasObject(By.desc(desc)), timeout)
    }

    private fun scrollDesc(listDesc: String, downTimes: Int, upTimes: Int) {
        val list = device.findObject(By.desc(listDesc)) ?: return
        repeat(downTimes) { list.safeScroll(Direction.DOWN) }
        repeat(upTimes) { list.safeScroll(Direction.UP) }
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