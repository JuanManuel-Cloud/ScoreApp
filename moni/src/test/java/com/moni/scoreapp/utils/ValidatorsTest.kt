package com.moni.scoreapp.utils

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
class ValidatorsTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `validateNameOrLastname should return true when name is valid`() {
        val name = "Moni"
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isTrue()
    }

    @Test
    fun `validateNameOrLastname should return false when name is too long`() {
        val name = buildString { for (i in 1..51) append("a") }
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateNameOrLastname should return false when name is too short`() {
        val name = "M"
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateNameOrLastname should return false when name is empty`() {
        val name = ""
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateNameOrLastname should return false when name has numbers`() {
        val name = "Moni123"
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateNameOrLastname should return false when name has special characters`() {
        val name = "Moni!@#"
        val result = Validators.validateNameOrLastname(name)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateEmail should return true when email is valid`() {
        val email = "test@gmail.com"
        val result = Validators.validateEmail(email)
        assertThat(result).isTrue()
    }

    @Test
    fun `validateEmail should return false when email has not @`() {
        val email = "testgmail.com"
        val result = Validators.validateEmail(email)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateEmail should return false when email has not domain`() {
        val email = "test@gmail"
        val result = Validators.validateEmail(email)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateEmail should return false when email has not user`() {
        val email = "@gmail.com"
        val result = Validators.validateEmail(email)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateEmail should return false when email has not extension`() {
        val email = "test@.com"
        val result = Validators.validateEmail(email)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateEmail should return false when email is empty`() {
        val email = ""
        val result = Validators.validateEmail(email)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return true when dni is valid`() {
        val dni = "12345678"
        val result = Validators.validateDni(dni)
        assertThat(result).isTrue()
    }

    @Test
    fun `validateDni should return false when dni is too long`() {
        val dni = "123456789"
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni is too short`() {
        val dni = "1234567"
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni is empty`() {
        val dni = ""
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni has letters`() {
        val dni = "1234567a"
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni has special characters`() {
        val dni = "1234567!"
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni has spaces`() {
        val dni = "1234567 "
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }

    @Test
    fun `validateDni should return false when dni has dots`() {
        val dni = "1234567."
        val result = Validators.validateDni(dni)
        assertThat(result).isFalse()
    }
}