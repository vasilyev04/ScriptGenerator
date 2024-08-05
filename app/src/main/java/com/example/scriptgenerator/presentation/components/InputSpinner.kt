package com.example.scriptgenerator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.scriptgenerator.R
import com.example.scriptgenerator.presentation.theme.inputTextFieldColor
import com.example.scriptgenerator.presentation.theme.textColor

@Composable
fun InputSpinner(
    list: List<String>,
    defaultSelected: String,
    color: Color,
    modifier: Modifier = Modifier,
    onSelected: (Int) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var expand by remember { mutableStateOf(false) }
    Box(
        modifier
            .background(
                color = inputTextFieldColor,
                shape = RoundedCornerShape(10.dp)
            )
            .height(40.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                expand = !expand
            }
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f) // takes the whole space before the icon
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = defaultSelected,
                    color = textColor,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                )
            }

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.drop_down_icon),
                contentDescription = "",
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.Unspecified
            )
        }

        DropdownMenu(
            expanded = expand,
            onDismissRequest = {
                expand = false
            },
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
            modifier = Modifier
                .background(inputTextFieldColor)
                .padding(2.dp)
                .fillMaxWidth(.9f)
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        selectedIndex = index
                        expand = false
                        onSelected(selectedIndex)
                    },
                    text = {
                        Text(
                            text = item,
                            color = color,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}
