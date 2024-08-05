package com.example.scriptgenerator.domain.repository

import com.example.scriptgenerator.domain.entity.GenerationSettings
import com.example.scriptgenerator.domain.entity.Script

interface ScriptRepository {
    suspend fun generateScript(generationSettings: GenerationSettings): Script
}