package com.example.winyourlife

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.presentation.MainActivity
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.navigation.AppNavHost
import com.example.winyourlife.presentation.navigation.NavigationScreens
import com.example.winyourlife.presentation.registerpage.PortraitLayout
import com.example.winyourlife.presentation.registerpage.RegisterViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterPageTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testRegistrationLayoutInteractions() {
        val currentUser: CurrentUser = Mockito.mock(CurrentUser::class.java)
        val authenticationService: AuthenticationService = Mockito.mock(AuthenticationService::class.java)
        val fakeViewModel = RegisterViewModel(authenticationService, currentUser)
        val navController = TestNavHostController(composeTestRule.activity)

        composeTestRule.activity.setContent {
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
            PortraitLayout(viewModel = fakeViewModel, navController = navController)
        }

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.register_hd)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.nickname_label)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.email_label)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.password_label)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.repeat_password_label)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.register_button)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.register_text)).assertExists()


        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.nickname_label))
            .performTextInput("testuser")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.email_label))
            .performTextInput("test@example.com")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.password_label))
            .performTextInput("password123")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.repeat_password_label))
            .performTextInput("password123")

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.register_button))
            .performClick()

        assert(fakeViewModel.nickname.value == "testuser")
        assert(fakeViewModel.email.value == "test@example.com")
        assert(fakeViewModel.password.value == "password123")
        assert(fakeViewModel.repeatPassword.value == "password123")

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.register_text))
            .performClick()
        assert(navController.currentDestination?.route == NavigationScreens.LOGIN.name)

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.forgot_password_button))
            .performClick()
        assert(navController.currentDestination?.route == NavigationScreens.FORGOT_PASSWORD.name)
    }
}