package com.plcoding.translator_kmm.core.language.translate.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DataBaseDriverFactory {
    fun create():SqlDriver
}