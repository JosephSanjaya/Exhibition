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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joseph.exhibition.core.ui.R
import com.joseph.exhibition.core.ui.presentation.themes.MainTheme
import com.joseph.exhibition.core.ui.presentation.themes.appColor

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
    startIcon: Pair<Painter, () -> Unit>? = null,
    endIcon: Pair<Painter, () -> Unit>? = null,
    iconColorFilter: ColorFilter? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    onClickAction: (() -> Unit)? = null
) {
    val appColor = appColor()
    val interactionSource = remember { MutableInteractionSource() }
    Column(modifier) {
        Row {
            Text(
                modifier = Modifier, text = title,
                color = appColor.neutral.darker,
                style = MaterialTheme.typography.labelLarge
            )
            if (isRequired) Text(
                modifier = Modifier.padding(start = 1.dp),
                text = " *",
                color = appColor.primary.normalActive,
                style = MaterialTheme.typography.labelLarge
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
                        painter = startIcon.first,
                        contentDescription = "start icon",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier.clickable {
                            startIcon.second()
                        }
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
                    keyboardOptions = keyboardOptions,
                    onValueChange = {
                        onInputChange(it)
                    },
                    visualTransformation = visualTransformation,
                    textStyle = MaterialTheme.typography.bodySmall,
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (input.isEmpty()) {
                                Text(
                                    text = hint, color = appColor.neutral.lightActive,
                                    style = MaterialTheme.typography.bodySmall
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
                        painter = endIcon.first,
                        contentDescription = "end icon",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier.clickable {
                            endIcon.second()
                        },
                        colorFilter = iconColorFilter
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
        Background {
            Column {
                InputBox(
                    modifier = Modifier.padding(16.dp),
                    title = "E-mail",
                    hint = "dublin12@example",
                    errorMsg = "Cannot be empty",
                    input = "",
                    isRequired = true,
                    endIcon = painterResource(id = R.drawable.ic_mail) to {}
                )
                InputBox(
                    modifier = Modifier.padding(16.dp),
                    title = "Nama Toko",
                    hint = "Toko Test",
                    errorMsg = "Cannot be empty",
                    input = "",
                    isRequired = true
                )
                InputBox(
                    modifier = Modifier.padding(16.dp),
                    title = "Nama Toko",
                    hint = "Toko Test",
                    errorMsg = "Cannot be empty",
                    input = "test",
                    isRequired = true
                )
            }
        }
    }
}