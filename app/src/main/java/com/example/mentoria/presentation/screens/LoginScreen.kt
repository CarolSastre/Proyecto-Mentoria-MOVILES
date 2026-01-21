import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentoria.presentation.components.PasswordOutTextField
import com.example.mentoria.presentation.components.UserOutTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean = false,
    imageError: Boolean = false,
    onClick: () -> Unit = {}, // onClick: (user: String, password: String) -> Unit
) {
    var user by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    val isValidate = rememberSaveable { user.isNotBlank() && password.isNotBlank() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /*
        Image(
            painter = painterResource(
                id = // TODO R.drawable.ic_login_image
                ),
            contentDescription = "Image Login",
            modifier = modifier
                .fillMaxWidth()
                .size(300.dp),
            alignment = Alignment.Center
        )
         */
        Text(
            text = "Login",
            fontSize = 30.sp
        )
        Spacer(modifier = modifier.height(15.dp))
        UserOutTextField(
            textValue = user,
            onValueChange = { user = it },
            onClickButton = { user = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = modifier.height(15.dp))

        PasswordOutTextField(
            textValue = password,
            onValueChange = { password = it },
            onDone = {
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = modifier.height(35.dp))

        Button(
            onClick = {
                onClick
                // TODO: pasar los datos de autentificación
            },
            modifier = Modifier.width(200.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
            enabled = isValidate,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                Color.Yellow
            )
        ) {
            Text(
                text = "Login",
                fontSize = 35.sp,
                color = Color.White
            )
        }

        Spacer(modifier = modifier.height(20.dp))
    }

    if (imageError) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            /*
            Image(
                painter = painterResource(
                    id = // TODO R.drawable.ic_error_imagen
                        ),
                contentDescription = "Image Error",
                modifier = modifier.size(250.dp)

            )
            */
        }
    }

    if (loadingProgressBar) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 5.dp,
                modifier = modifier.size(60.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}