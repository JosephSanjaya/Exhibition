package com.joseph.exhibition.core.ui.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.core.ui.presentation.themes.colors.AppColor

@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    title: String = "",
    hint: String = "",
    errorMsg: String = "",
    input: String = "",
    onInputChange: (String) -> Unit = {},
    isSingleLine: Boolean = true,
    isRequired: Boolean = false,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    startIcon: Painter? = null,
    endIcon: Painter? = null,
    onClickAction: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val appColor = AppColor()
    Column(modifier) {
        Row {
            Text(
                modifier = Modifier, text = title,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
            if (isRequired) Text(
                modifier = Modifier.padding(start = 1.dp),
                text = " *",
                color = appColor.primary.normalActive,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            border = BorderStroke(1.dp, appColor.neutral.lightActive),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                if (startIcon != null) {
                    val imageModifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp)
                    Image(
                        painter = startIcon,
                        contentDescription = "start icon",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier
                    )
                }
                BasicTextField(
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(1f, true)
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onClickAction?.invoke()
                        }
                        .defaultMinSize(),
                    value = input,
                    readOnly = onClickAction != null,
                    enabled = onClickAction == null,
                    singleLine = isSingleLine,
                    onValueChange = {
                        onInputChange(it)
                    },
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (input.isEmpty()) {
                                Text(
                                    text = hint, color = appColor.neutral.lightActive,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                            innerTextField()
                        }
                    },
                    interactionSource = interactionSource
                )
                if (endIcon != null) {
                    val imageModifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                    Image(
                        painter = endIcon,
                        contentDescription = "end icon",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier
                    )
                }
            }
        }
        if (errorMsg.isNotBlank()) {
            Text(
                modifier = Modifier, text = errorMsg, color = appColor.primary.normalActive,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun CommonInputPreview() {
    MainTheme {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(0.dp)
        ) {
            InputBox(
                title = "Nama Toko",
                hint = "Toko Test",
                input = "test",
                isRequired = true,
                errorMsg = "Cannot be empty"
            )
        }
    }
}