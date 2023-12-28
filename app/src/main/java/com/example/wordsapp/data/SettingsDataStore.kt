package com.example.wordsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// Preferences Datastore name
private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"

// 建立 DataStore instance
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCES_NAME
)

// 在 SettingsDataStore class 中加入 type 為 Context 的 constructor 參數
class SettingsDataStore(context: Context) {
    // 建立用於儲存 Boolean 值的 key (key name: "is_linear_layout_manager")
    private val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")

    // 公開儲存在 Flow<Preferences> 的資料(只公開 Boolean 值)
    val preferenceFlow: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            // On the first run of the app, we will use LinearLayoutManager by default
            preferences[IS_LINEAR_LAYOUT_MANAGER] ?: true
        }

    // 將 Boolean 值 layout 設定儲存在 DataStore 中
    suspend fun saveLayoutToPreferencesStore(isLinearLayoutManager: Boolean, context: Context) {
        // 呼叫 dataStore.edit()，並傳遞 code block 以儲存新的 value
        context.dataStore.edit {
            preferences ->
            preferences[IS_LINEAR_LAYOUT_MANAGER] = isLinearLayoutManager
        }

    }

}