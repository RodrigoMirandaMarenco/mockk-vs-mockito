package com.rmiranda.mockkvsmockito.data

import com.rmiranda.mockkvsmockito.data.model.LoggedInUser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.io.IOException
import java.util.*

/**
 * Created by rodrigomiranda on 14/12/21.
 * Applaudo Studios
 */
class LoginRepositoryTest {
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginDataSource: LoginDataSource
    private val userName = "userName"
    private val password = "password"
    private val wrongPassword = "wrongPassword"
    private val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Android Huddle")
    private val resultError = Result.Error(IOException("Wrong credentials"))

    @Before
    fun setup() {
        loginDataSource = mock {
            on { login(userName, password) } doReturn Result.Success(fakeUser)
            on { login(userName, wrongPassword) } doReturn resultError
        }
        loginRepository = LoginRepository(loginDataSource)
    }

    @Test
    fun `User logs in with correct credentials`() {
        val result = loginRepository.login(userName, password)
        Assert.assertEquals(result, Result.Success(fakeUser))
    }

    @Test
    fun `User logs in with incorrect credentials`() {
        val result = loginRepository.login(userName, wrongPassword)
        Assert.assertEquals(result, resultError)
    }
}