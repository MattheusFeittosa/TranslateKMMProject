package com.plcoding.translator_kmm.core.language.domain.utils

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(initialMutableStateFlow: T) : CommonMutableStateFlow<T>(
    MutableStateFlow(initialMutableStateFlow)
) {
}