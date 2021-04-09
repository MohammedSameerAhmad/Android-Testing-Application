package `in`.utilitysoftwares.androidtestingapplication


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilsTest{

    @Test
    fun userNameEmpty_False_Empty(){
        val result = RegistrationUtils.validateRegistrationInput(
            "",
            "123",
            "123")
        assertThat(result).isFalse()
    }

    @Test
    fun  userNameExist_False_username(){
        val result = RegistrationUtils.validateRegistrationInput(
            "Peter",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  confirmPassword_False_wrongConfirmPassword(){
        val result = RegistrationUtils.validateRegistrationInput(
            "Charls",
            "123",
            "111"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  emptyPassword_False_emptyPassword(){
        val result = RegistrationUtils.validateRegistrationInput(
            "Charls",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  passwordValidation_False_oneDigitPassword(){
        val result = RegistrationUtils.validateRegistrationInput(
            "Charls",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun  userNameCorrect_True_usernameAndPassword(){
        val result = RegistrationUtils.validateRegistrationInput(
            "Charls",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

}