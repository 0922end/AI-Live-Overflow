package com.example.deskpet.data

import io.github.jan.supabase.SupabaseClient as SClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    // TODO: 替换成你自己的 Supabase URL 和 Anon Key
    private const val SUPABASE_URL = "https://your-project.supabase.co"
    private const val SUPABASE_ANON_KEY = "your-anon-key"

    fun create(): SClient {
        return createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_ANON_KEY
        ) {
            install(Postgrest)
            install(Realtime)
        }
    }
}
