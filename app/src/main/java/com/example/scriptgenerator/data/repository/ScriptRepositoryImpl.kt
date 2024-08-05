package com.example.scriptgenerator.data.repository

import com.example.scriptgenerator.data.remote.GeminiApi
import com.example.scriptgenerator.domain.entity.GenerationSettings
import com.example.scriptgenerator.domain.entity.Script
import com.example.scriptgenerator.domain.repository.ScriptRepository
import javax.inject.Inject

class ScriptRepositoryImpl @Inject constructor(
    private val geminiApi: GeminiApi
): ScriptRepository {
    private val basicPrompt = "Сгенерируй мне дидактическую игру для детей дошкольного возраста в тематике: %s, которая будет развивать у ребенка данную образовательную область: %s, количество участников: %s. Возрастная категория детей: %s. Дополнение= %s. \nВажно: Не пиши ничего от себя по типу *Надеюсь...*. \nВсе должно быть только на счет игры"

    override suspend fun generateScript(generationSettings: GenerationSettings): Script {

        val prompt = with(generationSettings){
           basicPrompt.format(
               thematic,
               educationalArea,
               participantsCount,
               ageCategory,
               additionalInformation
           )
        }

        val textResponse = geminiApi.makeRequest(prompt)

        val response = Script(
            text = textResponse
        )

        return response
    }
}