package com.ortin.flightradar.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ortin.flightradar.presentation.component.header.CustomHeader
import com.ortin.flightradar.presentation.viewmodel.FeedbackScreenViewModel
import com.ortin.flightradar.ui.theme.Additional
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedbackScreen(
    onBackPressed: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val viewModel: FeedbackScreenViewModel = koinViewModel()
    val mailBody by viewModel.body.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        focusManager.clearFocus()
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomHeader(
            name = "Обратная связь",
            onBackPressed = onBackPressed
        )
        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            text = "По вопросам о приложении, пожалуйста, сначала прочитайте наш раздел FAQ.",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 12.dp)
                .weight(1f)
                .fillMaxSize(),
            value = mailBody,
            onValueChange = viewModel::onBodyChange,
            placeholder = {
                Text(
                    text = "Write something here...",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W300,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Start
                    )
                )
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Additional)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            text = "Не забудьте указать свой email, если вы хотите, чтобы мы с вами связались.",
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,
            ),
        )
        Button(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 12.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.sendMail()
                Toast
                    .makeText(context, "Сообщение успешно отправлено", Toast.LENGTH_SHORT)
                    .show()
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Additional)
        ) {
            Text(
                text = "Подтвердить",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W300,
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}
