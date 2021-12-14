package com.rmiranda.mockkvsmockito.data

import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.fakeUser
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.password
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.resultError
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.userName
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.wrongPassword
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

/**
 * Created by rodrigomiranda on 14/12/21.
 * Applaudo Studios
 */
class LoginRepositoryMockitoTest {
    private lateinit var loginRepository: LoginRepository
    private lateinit var loginDataSource: LoginDataSource

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

    @Test
    fun `User logs out`() {
        loginRepository.logout()
        verify(loginDataSource).logout()
    }
}