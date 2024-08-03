package com.example.scriptgenerator.domain.repository

import com.example.scriptgenerator.domain.entity.Script

interface ScriptRepository {
    suspend fun generateScript(prompt: String): Script
}