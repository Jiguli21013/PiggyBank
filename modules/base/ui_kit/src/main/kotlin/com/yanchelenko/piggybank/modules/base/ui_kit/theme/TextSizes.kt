package com.yanchelenko.piggybank.modules.base.ui_kit.theme

import androidx.compose.ui.unit.sp

/**
 * Centralized text size tokens (sp) for cases where MaterialTheme.typography
 * is not enough or you want explicit numeric sizes.
 *
 * If a size maps to a standard style, prefer MaterialTheme.typography.* instead.
 */
object TextSizes {
    /** Large numeric value for quantity counters between +/- buttons */
    val Quantity = 32.sp

    /** Small annotations, badges, helpers (if you need explicit sp) */
    val Caption = 12.sp

    /** Common small text outside of typography, e.g. tags */
    val Small = 14.sp

    /** Generic medium text size (fallback) */
    val Medium = 16.sp
}
