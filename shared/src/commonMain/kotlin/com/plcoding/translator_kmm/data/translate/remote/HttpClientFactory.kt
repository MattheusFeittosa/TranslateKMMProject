package com.plcoding.translator_kmm.data.translate.remote

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun creator(): HttpClient
}