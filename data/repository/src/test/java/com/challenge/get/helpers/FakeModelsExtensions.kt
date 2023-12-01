package com.challenge.get.helpers

import com.challenge.get.repository.util.RequestState

fun genericApiError() = RequestState.Error(
    "generic error message"
)
