package com.example.winyourlife


import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.winyourlife.data.dto.LoginRequest
import com.example.winyourlife.domain.AuthenticationService
import com.example.winyourlife.domain.wrapper.Resource
import com.example.winyourlife.presentation.dataObjects.CurrentUser
import com.example.winyourlife.presentation.loginpage.LoginViewModel
import com.example.winyourlife.presentation.utils.ExceptionText
import com.example.winyourlife.presentation.utils.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {



    private lateinit var viewModel: LoginViewModel
    private lateinit var authenticationService: AuthenticationService
    private lateinit var currentUser: CurrentUser
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        authenticationService = mockk(relaxed = true)
        currentUser = mockk(relaxed = true)
        viewModel = LoginViewModel(authenticationService, currentUser)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {

        Dispatchers.resetMain()
    }

    @Test
    fun `updateEmail updates email value`() {

        val newEmail = "test@example.com"
        viewModel.updateEmail(newEmail)

        assertEquals(newEmail, viewModel.email.value)
    }

    @Test
    fun `updatePassword updates password value`() {

        val newPassword = "password123"
        viewModel.updatePassword(newPassword)

        assertEquals(newPassword, viewModel.password.value)
    }

    @Test
    fun `login sets state to loading and then to success`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val loginRequest = LoginRequest(email, password)
        val successResource = Resource.Success(Unit)

        coEvery { authenticationService.login(loginRequest) } returns successResource

        viewModel.login(email, password)
        advanceUntilIdle()

        assertEquals(successResource, viewModel.state.obj)
        assertEquals(false, viewModel.state.isLoading)
        assertEquals(true, viewModel.state.isReady)
        assertEquals(null, viewModel.state.error)
    }

    @Test
    fun `login sets state to loading and then to error`() = runTest(testDispatcher) {
        val email = "test@example.com"
        val password = "password123"
        val loginRequest = LoginRequest(email, password)
        val errorResource = Resource.Error(message = ExceptionText.InternalServer.text, data = Unit)

        coEvery { authenticationService.login(loginRequest) } returns errorResource
        viewModel.login(email, password)
        advanceUntilIdle()

        assertEquals(ExceptionText.InternalServer.text, viewModel.state.error)
        assertEquals(false, viewModel.state.isLoading)
        assertEquals(true, viewModel.state.isReady)
    }
}
