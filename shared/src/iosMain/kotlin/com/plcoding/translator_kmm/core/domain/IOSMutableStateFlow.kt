package com.plcoding.translator_kmm.core.domain

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(initialMutableStateFlow: T) : CommonMutableStateFlow<T>(
    MutableStateFlow(initialMutableStateFlow)
) {
}