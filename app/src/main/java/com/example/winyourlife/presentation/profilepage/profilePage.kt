package com.example.winyourlife.presentation.profilepage

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.utils.ImageEncoder
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import java.util.Base64

@Composable
fun ProfilePage(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.userData?.mapOfSettings?.get(Settings.IS_DARK_THEME.name)
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout(navController)
    }

}

@Composable
fun ResponsiveLayout(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
    val context = LocalContext.current
    if (isPortrait) {
       PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }

    when(viewModel.stateUpdateData.isReady){
        true -> {
            when(viewModel.stateUpdateData.error != null){
                true -> {
                    // ErrorScreen(message = viewModel.stateUpdateData.error)
                }
                false -> {
                    Toast.makeText(context,stringResource(id = R.string.data_saved_snack), Toast.LENGTH_SHORT).show()
                }
            }
        }
        false -> {}
    }

    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }
}

@Composable
fun LandscapeLayout(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

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
        avatar = if (uri != null)
            ImageEncoder().encodeImage(uri, context)
        else
            null
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            when {
                avatar == null ||
                        avatar?.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = stringResource(id = R.string.user_avatar_description),
                    modifier = Modifier
                        .clickable { if(viewModel.isEditProfile) pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                        .size(128.dp)
                        .padding(16.dp)
                )
                else -> Image(
                    bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar!!.size).asImageBitmap(),
                    contentDescription = stringResource(id = R.string.user_avatar_description),
                    modifier = Modifier
                        .clickable { if(viewModel.isEditProfile) pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                        .size(128.dp)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(nickname ?: "",{ nickname = it },stringResource(id = R.string.nickname_label), false)

            WhiteOutlinedTextField(email ?: "",{ email = it },stringResource(id = R.string.email_label), false)

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OrangeButton({viewModel.updateUserData(email=email?:"", name = nickname?:"", avatar = avatar?: Base64.getDecoder().decode(""))}, stringResource(id = R.string.change_data_button))

            OrangeButton({ navController.navigate(NavigationScreens.RESET_PASSWORD.name) }, stringResource(id = R.string.change_password_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

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
        avatar = if (uri != null)
            ImageEncoder().encodeImage(uri, context)
        else
            null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.profile_hd))

        Spacer(modifier = Modifier.height(60.dp))

        when {
            avatar == null ||
            avatar?.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = stringResource(id = R.string.user_avatar_description),
                modifier = Modifier
                    .clickable { if(viewModel.isEditProfile) pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .size(128.dp)
                    .padding(16.dp)
            )
            else -> Image(
                bitmap = BitmapFactory.decodeByteArray(avatar, 0, avatar!!.size).asImageBitmap(),
                contentDescription = stringResource(id = R.string.user_avatar_description),
                modifier = Modifier
                    .clickable { if(viewModel.isEditProfile) pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .size(128.dp)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(nickname ?: "",{ nickname = it },stringResource(id = R.string.nickname_label), viewModel.isEditProfile)

        WhiteOutlinedTextField(email ?: "",{ email = it },stringResource(id = R.string.email_label), viewModel.isEditProfile)

        Spacer(modifier = Modifier.weight(1f))

        OrangeButton(onClick = {if (viewModel.isEditProfile) viewModel.updateUserData(email=email?:"", name = nickname?:"", avatar =avatar?:Base64.getDecoder().decode("")) else viewModel.editProfile()}, if(!viewModel.isEditProfile) stringResource(id = R.string.change_data_button) else stringResource(id = R.string.save_data_button))

        OrangeButton({ viewModel.resetViewModel(); navController.navigate(NavigationScreens.RESET_PASSWORD.name) }, stringResource(id = R.string.change_password_button))

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController, viewModel)
    }
}
