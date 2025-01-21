package com.example.winyourlife
import com.example.winyourlife.presentation.utils.PasswordValidator
import org.junit.Assert.*
import org.junit.Test

class PasswordValidatorTest {

    private val passwordValidator = PasswordValidator()

    @Test
    fun `validatePassword should return true for valid password`() {
        val validPassword = "Valid123"
        val result = passwordValidator.validatePassword(validPassword)
        assertTrue("Password should be valid", result)
    }

    @Test
    fun `validatePassword should return false for password without numbers`() {
        val invalidPassword = "InvalidPass"
        val result = passwordValidator.validatePassword(invalidPassword)
        assertFalse("Password without numbers should be invalid", result)
    }

    @Test
    fun `validatePassword should return false for password without uppercase letters`() {
        val invalidPassword = "invalid123"
        val result = passwordValidator.validatePassword(invalidPassword)
        assertFalse("Password without uppercase letters should be invalid", result)
    }

    @Test
    fun `validatePassword should return false for password without lowercase letters`() {
        val invalidPassword = "INVALID123"
        val result = passwordValidator.validatePassword(invalidPassword)
        assertFalse("Password without lowercase letters should be invalid", result)
    }

    @Test
    fun `validatePassword should return false for password with spaces`() {
        val invalidPassword = "Valid 123"
        val result = passwordValidator.validatePassword(invalidPassword)
        assertFalse("Password with spaces should be invalid", result)
    }

    @Test
    fun `validatePassword should return false for password shorter than 8 characters`() {
        val invalidPassword = "Val123"
        val result = passwordValidator.validatePassword(invalidPassword)
        assertFalse("Password shorter than 8 characters should be invalid", result)
    }

    @Test
    fun `checkPasswordsMatch should return true for matching passwords`() {
        val password = "Valid123"
        val confirmPassword = "Valid123"
        val result = passwordValidator.checkPasswordsMatch(password, confirmPassword)
        assertTrue("Passwords should match", result)
    }

    @Test
    fun `checkPasswordsMatch should return false for non-matching passwords`() {
        val password = "Valid123"
        val confirmPassword = "Invalid123"
        val result = passwordValidator.checkPasswordsMatch(password, confirmPassword)
        assertFalse("Passwords should not match", result)
    }
}
