package com.example.scriptgenerator.data.repository

import com.example.scriptgenerator.data.remote.GeminiApi
import com.example.scriptgenerator.domain.entity.Script
import com.example.scriptgenerator.domain.repository.ScriptRepository
import javax.inject.Inject

class ScriptRepositoryImpl @Inject constructor(
    private val geminiApi: GeminiApi
): ScriptRepository {

    override suspend fun generateScript(prompt: String): Script {
        val textResponse = geminiApi.makeRequest(prompt)

        val response = Script(
            text = textResponse
        )

        return response
    }
}