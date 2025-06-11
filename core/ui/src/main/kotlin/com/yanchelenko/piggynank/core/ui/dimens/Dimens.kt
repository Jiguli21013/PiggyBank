package com.yanchelenko.piggynank.core.ui.dimens

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val screenPadding: Dp,
    val sectionSpacing: Dp,
    val itemSpacing: Dp,
    val buttonSpacing: Dp,
    val buttonHeight: Dp,
    val iconSize: Dp,
    val textLarge: TextUnit,
    val textNormal: TextUnit,
    val textSmall: TextUnit
)

val compactDimens = Dimens(
    screenPadding = 16.dp,
    sectionSpacing = 16.dp,
    itemSpacing = 8.dp,
    buttonSpacing = 16.dp,
    buttonHeight = 48.dp,
    iconSize = 24.dp,
    textLarge = 20.sp,
    textNormal = 16.sp,
    textSmall = 14.sp
)

val mediumDimens = Dimens(
    screenPadding = 24.dp,
    sectionSpacing = 20.dp,
    itemSpacing = 12.dp,
    buttonSpacing = 20.dp,
    buttonHeight = 52.dp,
    iconSize = 28.dp,
    textLarge = 22.sp,
    textNormal = 18.sp,
    textSmall = 14.sp
)

val expandedDimens = Dimens(
    screenPadding = 32.dp,
    sectionSpacing = 24.dp,
    itemSpacing = 16.dp,
    buttonSpacing = 24.dp,
    buttonHeight = 56.dp,
    iconSize = 32.dp,
    textLarge = 24.sp,
    textNormal = 20.sp,
    textSmall = 16.sp
)
