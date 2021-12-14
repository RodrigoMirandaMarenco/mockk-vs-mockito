package com.rmiranda.mockkvsmockito.data

import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.fakeUser
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.PASSWORD
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.resultError
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.USER_NAME
import com.rmiranda.mockkvsmockito.data.utils.TestLoginUtils.WRONG_PASSWORD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
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
            on { login(USER_NAME, PASSWORD) } doReturn Result.Success(fakeUser)
            on { login(USER_NAME, WRONG_PASSWORD) } doReturn resultError
        }
        loginRepository = LoginRepository(loginDataSource)
    }

    @Test
    fun `User logs in with correct credentials`() {
        val result = loginRepository.login(USER_NAME, PASSWORD)
        Assert.assertEquals(result, Result.Success(fakeUser))
    }

    @Test
    fun `User logs in with incorrect credentials`() {
        val result = loginRepository.login(USER_NAME, WRONG_PASSWORD)
        Assert.assertEquals(result, resultError)
    }

    @Test
    fun `User logs out`() {
        loginRepository.logout()
        verify(loginDataSource).logout()
    }

    @Test
    fun `fetch User URL`() {
        runBlocking {
            loginRepository.fetchUserUrl(Dispatchers.Unconfined)
            verify(loginDataSource).fetchUserUrl()
        }
    }
}