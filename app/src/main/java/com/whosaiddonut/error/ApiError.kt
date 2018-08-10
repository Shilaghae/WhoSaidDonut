package com.whosaiddonut.error

import retrofit2.HttpException
import java.net.UnknownHostException

fun handleException(exception: Throwable): ErrorMatcher {
    when {
        exception is HttpException -> {
            when (exception.code()) {
                403 -> return ErrorMatcher.FORBIDDEN
                404 -> return ErrorMatcher.NOT_FOUND
                else -> return ErrorMatcher.GENERAL_ERROR
            }
        }
        exception is UnknownHostException -> return ErrorMatcher.NO_CONNECTION
        else -> return ErrorMatcher.GENERAL_ERROR
    }
}

