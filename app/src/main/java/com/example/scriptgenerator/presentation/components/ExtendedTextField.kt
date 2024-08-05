package com.example.scriptgenerator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scriptgenerator.presentation.theme.inputTextFieldColor
import com.example.scriptgenerator.presentation.theme.textColor

@Composable
fun ExtendedInputTextField(
    modifier: Modifier = Modifier.height(40.dp),
    value: String,
    hint: String,
    onTextChanged: (String) -> Unit
){
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = inputTextFieldColor,
                shape = RoundedCornerShape(10.dp)
            ),
        value = value,
        textStyle = TextStyle(fontSize = 12.sp, color = textColor),
        onValueChange = {
            onTextChanged(it)
        },
        singleLine = false,
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .padding(start = 15.dp, top = 12.dp, end = 15.dp)) {
                    if (value.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 1.dp),
                            text = hint,
                            style = TextStyle(
                                color = textColor,
                                fontSize = 12.sp
                            )
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}