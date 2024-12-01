package com.example.winyourlife.presentation.editTaskPage

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.winyourlife.R
import com.example.winyourlife.presentation.createtaskpage.CreateTaskViewModel
import com.example.winyourlife.presentation.customItems.BottomNavigationBar
import com.example.winyourlife.presentation.customItems.Headline
import com.example.winyourlife.presentation.customItems.MyHorizontalDivider
import com.example.winyourlife.presentation.customItems.MyVerticalDivider
import com.example.winyourlife.presentation.customItems.OrangeButton
import com.example.winyourlife.presentation.customItems.SideNavigationBar
import com.example.winyourlife.presentation.customItems.WhiteOutlinedTextField
import com.example.winyourlife.presentation.utils.ImageEncoder
import com.example.winyourlife.presentation.utils.Settings
import com.example.winyourlife.presentation.utils.mapExceptionText
import com.example.winyourlife.ui.theme.WinYourLifeTheme
import java.util.Base64

@Composable
fun EditTaskPage(navController: NavHostController, viewModel: EditTaskViewModel = hiltViewModel()) {
    WinYourLifeTheme(darkTheme = viewModel.currentUser.mapOfSettings[Settings.IS_DARK_THEME.name]
        ?.toBooleanStrictOrNull() ?: isSystemInDarkTheme()) {
        ResponsiveLayout(navController)
    }
    BackHandler {
        viewModel.resetViewModel()
        navController.popBackStack()
    }
    val context = LocalContext.current
    when(viewModel.state.isReady){
        true -> {
            when(viewModel.state.error != null){
                true -> {
                    Toast.makeText(context, mapExceptionText(viewModel.state.error!!, context), Toast.LENGTH_SHORT).show()
                }
                false -> {
                    Toast.makeText(context, stringResource(id = R.string.data_saved_snack), Toast.LENGTH_SHORT).show()
                }
            }
        }
        false -> {}
    }
}

@Composable
fun ResponsiveLayout(navController: NavHostController) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    if (isPortrait) {
        PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }
}

@Composable
fun LandscapeLayout(navController: NavHostController, viewModel: CreateTaskViewModel = hiltViewModel()) {

    var taskName by remember {
        mutableStateOf("")
    }

    var taskImage by remember {
        mutableStateOf<ByteArray>("".toByteArray())
    }

    val context = LocalContext.current

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        taskImage = if (uri != null)
            ImageEncoder().encodeImage(uri, context)
        else
            "".toByteArray()
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
                taskImage.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = stringResource(id = R.string.user_avatar_description),
                    modifier = Modifier
                        .size(128.dp)
                        .padding(16.dp)
                )
                else -> Image(
                    bitmap = BitmapFactory.decodeByteArray(taskImage, 0, taskImage.size).asImageBitmap(),
                    contentDescription = stringResource(id = R.string.user_avatar_description),
                    modifier = Modifier
                        .size(128.dp)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        MyVerticalDivider()

        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            WhiteOutlinedTextField(taskName, {taskName = it}, stringResource(id = R.string.task_name_label), true)

            Spacer(modifier = Modifier.height(30.dp))

            MyHorizontalDivider()

            OrangeButton({pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}, stringResource(id = R.string.pick_image_button))

            OrangeButton({viewModel.createTask(taskName, taskImage)}, stringResource(id = R.string.save_task_button))

            Spacer(modifier = Modifier.weight(1f))
        }

        SideNavigationBar(navController, viewModel)
    }
}

@Composable
fun PortraitLayout(navController: NavHostController, viewModel: CreateTaskViewModel = hiltViewModel()) {

    var taskName by remember {
        mutableStateOf("")
    }

    var taskImage by remember {
        mutableStateOf<ByteArray>("".toByteArray())
    }

    val context = LocalContext.current

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        taskImage = if (uri != null)
            ImageEncoder().encodeImage(uri, context)
        else
            "".toByteArray()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(stringResource(id = R.string.edit_task_hd))

        Spacer(modifier = Modifier.weight(1f))

        when {
            taskImage.decodeToString() == Base64.getDecoder().decode("").decodeToString() -> Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = stringResource(id = R.string.user_avatar_description),
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
            else -> Image(
                bitmap = BitmapFactory.decodeByteArray(taskImage, 0, taskImage.size).asImageBitmap(),
                contentDescription = stringResource(id = R.string.user_avatar_description),
                modifier = Modifier
                    .size(128.dp)
                    .padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        WhiteOutlinedTextField(taskName, {taskName = it}, stringResource(id = R.string.task_name_label), true)

        Spacer(modifier = Modifier.height(30.dp))

        MyHorizontalDivider()

        OrangeButton({pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))}, stringResource(id = R.string.pick_image_button))

        OrangeButton({viewModel.createTask(taskName,taskImage)}, stringResource(id = R.string.save_task_button))

        Spacer(modifier = Modifier.height(30.dp))

        BottomNavigationBar(navController, viewModel)
    }
}
