package com.example.deskpet.data

import org.json.JSONObject

data class PetState(
    val mood: String,
    val message: String?,
    val heat: Int = 0
) {
    companion object {
        fun fromJson(json: String): PetState? {
            return try {
                val obj = JSONObject(json)
                PetState(
                    mood = obj.optString("mood", "idle"),
                    message = obj.optString("message", null),
                    heat = obj.optInt("heat", 0)
                )
            } catch (e: Exception) {
                null
            }
        }
    }
}
