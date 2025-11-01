package com.yanchelenko.piggybank.modules.base.ui_kit.test

/**
 * Centralized test tags for UI automation and baseline profile flows.
 * Use these in Compose modifiers via `.semantics { contentDescription = UiTestTags.XYZ }`.
 */
object UiTestTags {
    const val NAV_SCANNER = "nav_scanner"
    const val NAV_HISTORY_OF_SCANS = "nav_history_of_scans"

    const val SCANNER_PREVIEW = "scanner_preview"
    const val SCANNER_PERMISSION = "scanner_permission_text"
    const val SCANNER_ROOT = "scanner_root"

    const val HISTORY_OF_SCANS_LIST = "history_of_scans_list"
    const val HISTORY_OF_SCANS_ROOT = "history_of_scans_root"
    const val HISTORY_OF_SCANS_EMPTY = "history_of_scans_empty"
    const val HISTORY_OF_SCANS_ITEM_PREFIX = "history_of_scans_item_"

    /** common */
    const val DATE_HEADER_PREFIX = "date_header_"
}
