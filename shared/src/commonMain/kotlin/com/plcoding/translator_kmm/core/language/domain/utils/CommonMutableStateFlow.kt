package com.plcoding.translator_kmm.core.language.domain.utils

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow:MutableStateFlow<T>):MutableStateFlow<T>

fun <T>MutableStateFlow<T>.toCommonMutableStateFlow()= CommonMutableStateFlow(this)