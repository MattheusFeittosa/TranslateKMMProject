package com.plcoding.translator_kmm.core.language.domain.utils

import kotlinx.coroutines.flow.StateFlow

actual class CommonStateFlow<T> actual constructor(private val flow: StateFlow<T>): StateFlow<T> by flow