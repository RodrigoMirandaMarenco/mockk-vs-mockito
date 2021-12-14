package com.rmiranda.mockkvsmockito.data.utils

import com.rmiranda.mockkvsmockito.data.Result
import com.rmiranda.mockkvsmockito.data.model.LoggedInUser
import java.io.IOException
import java.util.*

/**
 * Created by rodrigomiranda on 14/12/21.
 * Applaudo Studios
 */
object TestLoginUtils {
    const val USER_NAME = "userName"
    const val PASSWORD = "password"
    const val WRONG_PASSWORD = "wrongPassword"
    val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Android Huddle")
    val resultError = Result.Error(IOException("Wrong credentials"))
}