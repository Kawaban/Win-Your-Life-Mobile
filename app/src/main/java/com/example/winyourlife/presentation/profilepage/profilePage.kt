package com.example.winyourlife.presentation.profilepage

import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.ImageEncoder
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current

    var nickname by remember {
        mutableStateOf(viewModel.currentUser.userData?.name)
    }
    var email by remember {
        mutableStateOf(viewModel.currentUser.userData?.email)
    }

    var avatar by remember {
        mutableStateOf(viewModel.currentUser.userData?.avatar)
    }

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->

        avatar = ImageEncoder().encodeImage(uri, context)
        if (uri != null) {
            println("Media selected: $uri")
        } else {
            println("No media selected")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline("YOUR PROFILE")

        Spacer(modifier = Modifier.height(60.dp))


        when(avatar) {
            null -> Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "User avatar",
                modifier = Modifier
                    .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .size(128.dp)
                    .padding(16.dp)
            )
            byteArrayOf(0) -> Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "User avatar",
                modifier = Modifier
                    .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .size(128.dp)
                    .padding(16.dp)
            )
            else -> Image(
                bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar!!.size).asImageBitmap(),
                contentDescription = "User avatar",
                modifier = Modifier
                    .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .size(128.dp)
                    .padding(16.dp)
            )
        }
//        Image(
//            painter = painterResource(id = R.drawable.avatar),
//            contentDescription = "User avatar",
//            modifier = Modifier
//                .clickable { pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
//                .size(128.dp)
//                .padding(16.dp)
//        )

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(nickname ?: "",{ nickname = it },"Nickname", false)

        WhiteOutlinedTextField(email ?: "",{ email = it },"Email", false)

        Spacer(modifier = Modifier.weight(1f))

//        OrangeButton({}, "Change avatar")

        OrangeButton({viewModel.updateUserData(email=email?:"", name = nickname?:"", avatar =avatar)}, "Change data")

        OrangeButton({ navController.navigate(NavigationScreens.RESET_PASSWORD.name) }, "Change password")

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}
