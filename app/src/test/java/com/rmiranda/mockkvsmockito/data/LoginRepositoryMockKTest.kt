package com.rmiranda.mockkvsmockito.data

import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.fakeUser
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.password
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.resultError
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.userName
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.wrongPassword
import io.mockk.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by rodrigomiranda on 14/12/21.
 * Applaudo Studios
 */
class LoginRepositoryMockKTest {
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginDataSource: LoginDataSource

    @Before
    fun setup() {
        loginDataSource = mockk {
            every { login(userName, password) } returns Result.Success(fakeUser)
            every { login(userName, wrongPassword) } returns resultError
            every { logout() } just Runs
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

    @Test
    fun `User logs out`() {
        loginRepository.logout()
        verify { loginDataSource.logout() }
    }
}