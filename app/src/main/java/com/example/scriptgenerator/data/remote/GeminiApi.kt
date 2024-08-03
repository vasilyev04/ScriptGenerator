package com.example.scriptgenerator.data.remote

import com.example.scriptgenerator.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

object GeminiApi {
    private const val API_KEY = BuildConfig.GEMINI_API_KEY
    private val geminiModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = API_KEY
    )

    suspend fun makeRequest(prompt: String): String {
        val inputContent = content {
            text(prompt)
        }

        val response = geminiModel.generateContent(inputContent)

        return response.text ?: "Couldn't generate"
    }
}