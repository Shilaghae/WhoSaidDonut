package com.whosaiddonut.error

enum class ErrorMatcher(val error: String) {
    FORBIDDEN("Unauthorised"),
    NOT_FOUND("Impossible to retrieve info"),
    NO_CONNECTION("There is no connection"),
    GENERAL_ERROR("Error."),
}