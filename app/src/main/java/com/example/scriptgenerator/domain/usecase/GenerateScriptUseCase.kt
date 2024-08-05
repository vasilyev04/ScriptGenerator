package com.example.scriptgenerator.domain.usecase

import com.example.scriptgenerator.domain.entity.GenerationSettings
import com.example.scriptgenerator.domain.entity.Script
import com.example.scriptgenerator.domain.repository.ScriptRepository
import javax.inject.Inject

class GenerateScriptUseCase @Inject constructor(
    private val repository: ScriptRepository
){
    suspend operator fun invoke(generationSettings: GenerationSettings): Script{
        return repository.generateScript(generationSettings)
    }
}