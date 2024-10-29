package com.example.winyourlife.presentation.registerpage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.util.ErrorScreen
import com.example.winyourlife.presentation.util.LoadingScreen

@Composable
fun RegisterScreen(navController: NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {

    when(viewModel.state.isReady) {
        false -> {
            when(viewModel.state.isLoading){
                true ->{
                    LoadingScreen()
                }
                false -> {
                    RegisterMainContent(viewModel, navController)
                }
            }

        }

        true -> {
            when (viewModel.state.error != null) {
                true -> {
                    ErrorScreen(message = viewModel.state.error)
                }

                false -> {

                    viewModel.reset()

                    val context = LocalContext.current
                    Toast.makeText(context,"Account has been created", Toast.LENGTH_SHORT).show()

                    navController.navigate(NavigationScreens.LOGIN.name)
                }
            }
        }
    }



}


@Composable
fun RegisterMainContent(viewModel: RegisterViewModel, navController: NavHostController){

    var nick by remember {
        mutableStateOf ("")
    }

    var email by remember {
        mutableStateOf ("")
    }

    var password by remember {
        mutableStateOf ("")
    }

    var repeatPassword by remember {
        mutableStateOf ("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Create an account",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = nick,
            onValueChange = { nick = it},
            label = { Text("Nick") },
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Black
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Black
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Black
            )
        )

        OutlinedTextField(
            value = repeatPassword,
            onValueChange = {repeatPassword = it},
            label = { Text("Repeat password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = Color.Black
            )
        )

        Button(
            onClick = {viewModel.register(email, nick, password, repeatPassword)},
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Register", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider(
            color = Color.White,
            thickness = 1.dp,
            modifier = Modifier
                .width(280.dp)
                .padding(vertical = 16.dp)
        )

        Text(
            text = "Already have an account?",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Light
        )

        Button(
            onClick = {
                viewModel.reset()
                navController.navigate(NavigationScreens.LOGIN.name)
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .width(280.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Sign up",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//    RegisterMainContent()
//}
