package com.joseph.exhibition.core.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.core.ui.presentation.themes.colors.AppColor

@Composable
fun LoadingDialog(
    isLoadingShow: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false,
) {
    val appColor = AppColor(LocalInspectionMode.current)
    if (isLoadingShow) {
        Dialog(
            onDismissRequest = onDismissRequest,
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(100.dp)
                    .background(appColor.neutral.light, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun LoadingDialogPreview() {
    MainTheme {
        Background {
            LoadingDialog(true, onDismissRequest = {})
        }
    }
}