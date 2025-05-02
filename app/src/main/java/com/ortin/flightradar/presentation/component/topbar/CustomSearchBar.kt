package com.ortin.flightradar.presentation.component.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R
import com.ortin.flightradar.ui.theme.Primary
import com.ortin.flightradar.ui.theme.Selected

@Composable
fun CustomSearchBar(
    onValueChanged: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
    isIconVisible: MutableState<Boolean>,
    placeholderColor: Color = Color.Gray,
    focusedIconsColor: Color = Selected,
    unfocusedIconsColor: Color = Primary,
    focusedIndicatorColor: Color = Selected,
    unfocusedIndicatorColor: Color = Primary,
    focusedContainerColor: Color = Color.White,
    unfocusedContainerColor: Color = Color.White
) {
    val focusManager = LocalFocusManager.current
    val placeholderText = remember { mutableStateOf("OpenAirRadar") }
    val horizontalPadding = remember { mutableStateOf(16.dp) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.value, vertical = 4.dp)
            .onFocusChanged {
                if (it.isFocused)  {
                    isIconVisible.value = false
                    horizontalPadding.value = 0.dp
                    placeholderText.value = "Рейс, аэропорт, авиакомпания"
                }
                else {
                    isIconVisible.value = true
                    horizontalPadding.value = 16.dp
                    placeholderText.value = "OpenAirRadar"
                }
            },
        value = value,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = placeholderText.value,
                overflow = TextOverflow.Ellipsis,
            )
        },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "Поиск",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { onValueChanged("") },
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = "Стереть текст",
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedLeadingIconColor = unfocusedIconsColor,
            unfocusedTrailingIconColor = unfocusedIconsColor,
            focusedLeadingIconColor = focusedIconsColor,
            focusedTrailingIconColor = focusedIconsColor,
            focusedContainerColor = focusedContainerColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedPlaceholderColor = placeholderColor,
            unfocusedPlaceholderColor = placeholderColor,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
    )
}
