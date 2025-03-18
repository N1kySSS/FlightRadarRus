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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ortin.flightradar.R
import com.ortin.flightradar.ui.theme.Primary

@Composable
fun CustomSearchBar(
    onValueChanged: (String) -> Unit,
    value: String,
    modifier: Modifier = Modifier,
    placeholderColor: Color = Color.Gray,
    focusedIconsColor: Color = Primary,
    unfocusedIconsColor: Color = Color.Black,
    focusedIndicatorColor: Color = Primary,
    unfocusedIndicatorColor: Color = Color.White,
    focusedContainerColor: Color = Color.White,
    unfocusedContainerColor: Color = Color.White
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        value = value,
        onValueChange = onValueChanged,
        placeholder = { Text(text = "FlightRadarRus") },
        singleLine = true,
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
