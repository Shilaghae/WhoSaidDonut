package com.whosaiddonut.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class TestDataLoader {
    fun loadString(path: String): String {
        val inputStream = javaClass.getResourceAsStream(path)
        val reader = BufferedReader(InputStreamReader(inputStream,
                Charset.defaultCharset()))
        return reader.use { it.readText() }
    }
}