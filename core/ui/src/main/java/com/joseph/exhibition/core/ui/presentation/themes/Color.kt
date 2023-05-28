package com.joseph.exhibition.core.ui.presentation.themes

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// region Primary
val Blue00 = Color(0xFFF4F9FC)
val Blue01 = Color(0xFFEFFAFF)
val Blue02 = Color(0xFFC8EEFF)
val Blue03 = Color(0xFF73D2FF)
val Blue04 = Color(0xFF0BB1FF)
val Blue05 = Color(0xFF098FDB)
val Blue06 = Color(0xFF075EA5)
// endregion

// region Secondary
val Yellow01 = Color(0xFFFFFBE9)
val Yellow02 = Color(0xFFFFF5C2)
val Yellow03 = Color(0xFFFEE873)
val Yellow04 = Color(0xFFF2C94C)
// endregion

// region Neutral
val Black00 = Color(0xFF000000)
val Black01 = Color(0xFFA0A0A0)
val Black02 = Color(0xFF707070)
val Black03 = Color(0xFF414141)
val Black04 = Color(0xFF111111)
val White = Color(0xFFFFFFFF)
val Transparent = Color(0xFFFFFF)
val Grey01 = Color(0xFFF7F7F7)
val Grey02 = Color(0xFFF1F1F1)
val Grey03 = Color(0xFFE4E4E4)
val Grey04 = Color(0xFFD8D8D8)
// endregion

// region Success
val Green01 = Color(0xFFEBFBEF)
val Green02 = Color(0xFF9EF4B1)
val Green03 = Color(0xFF2DD151)
val Green04 = Color(0xFF1A9C37)
val DarkGreen = Color(0xFF2E9F45)
// endregion

// region Error
val Red01 = Color(0xFFFEE6E7)
val Red02 = Color(0xFFF29EA4)
val Red03 = Color(0xFFEB3540)
val Red04 = Color(0xFFD91A26)
val Red05 = Color(0xFFBB1822)
// endregion

// region Limited
val Purple01 = Color(0xFFDDA3F1)
val Purple02 = Color(0xFFD174F0)
val Purple03 = Color(0xFFB205ED)
val Purple04 = Color(0xFF9800CC)
// endregion

// region divider gradient
val Grey05 = Color(0x16FAF9F9)
val Grey06 = Color(0x94CEC8C8)
// endregion

@Composable
fun CommonButtonColor() = ButtonDefaults.buttonColors(
    containerColor = Blue05,
    contentColor = White,
    disabledContainerColor = Grey02,
    disabledContentColor = Black01
)