package com.example.winyourlife

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.presentation.MainActivity
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.loginpage.LoginViewModel
import com.example.winyourlife.presentation.loginpage.PortraitLayout
import com.example.winyourlife.presentation.navigation.AppNavHost
import com.example.winyourlife.presentation.navigation.NavigationScreens
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginPageTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testPortraitLayoutInteractions() {
        val currentUser:CurrentUser = Mockito.mock(CurrentUser::class.java)
        val authenticationService : AuthenticationService = Mockito.mock(AuthenticationService::class.java)
        val fakeViewModel = LoginViewModel(authenticationService,currentUser)
        val navController = TestNavHostController(composeTestRule.activity)




        composeTestRule.activity.setContent {

            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
            PortraitLayout(viewModel = fakeViewModel, navController = navController)
        }


        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.app_logo_description)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.email_label)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.password_label)).assertExists()


        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.email_label))
            .performTextInput("test@example.com")
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.password_label))
            .performTextInput("password123")

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.log_in_button))
            .performClick()

        assert(fakeViewModel.email.value == "test@example.com")
        assert(fakeViewModel.password.value == "password123")


        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.sign_up_button))
            .performClick()
        assert(navController.currentDestination?.route == NavigationScreens.REGISTER.name)


        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.forgot_password_button))
            .performClick()
        assert(navController.currentDestination?.route == NavigationScreens.FORGOT_PASSWORD.name)
    }
}
