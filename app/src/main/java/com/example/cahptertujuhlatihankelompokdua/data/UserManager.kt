package com.example.cahptertujuhlatihankelompokdua.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map

class UserManager (context : Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_prefs")
    private val loginDataStore : DataStore<Preferences> = context.createDataStore(name = "login_prefs")
    private val imageData: DataStore<Preferences> = context.createDataStore(name = "image_prefs")

    companion object{
        val ID = preferencesKey<String>("USER_ID")
        val EMAIL = preferencesKey<String>("USER_EMAIL")
        val PASSWORD = preferencesKey<String>("USER_PASSWORD")
        val USERNAME = preferencesKey<String>("USER_NAME")
        val NAMALENGKAP = preferencesKey<String>("NAMA_LENGKAP")
        val BIRTH = preferencesKey<String>("USER_BIRTH")
        val ADDRESS = preferencesKey<String>("USER_ADDRESS")
        val LOGIN_STATE = preferencesKey<String>("USER_LOGIN")
        val IMAGE  = preferencesKey<String>("USER_IMAGE")
    }

    suspend fun saveDataUser(id : String, email:String, password : String, username: String) {
        dataStore.edit {
            it[ID] = id
            it[PASSWORD] = password
            it[USERNAME] = username
            it[EMAIL] = email
        }
    }

    suspend fun saveDataLogin(login : String) {
        loginDataStore.edit {
            it[LOGIN_STATE] = login
        }
    }

    suspend fun deleteDataLogin(){
        loginDataStore.edit{
            it.clear()

        }
    }

    suspend fun saveDataImage(image: String) {
        imageData.edit {
            it[IMAGE] = image

        }
    }

    suspend fun deleteDataImage(){
        imageData.edit{
            it.clear()

        }
    }

    val userID : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ID] ?: ""
    }
    val userUsername : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [USERNAME] ?: ""
    }
    val userEmail : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [EMAIL] ?: ""
    }
    val userPass : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [PASSWORD] ?: ""
    }
    val userNamaLengkap : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [NAMALENGKAP] ?: ""
    }
    val userBirth : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [BIRTH] ?: ""
    }
    val userAddress : kotlinx.coroutines.flow.Flow<String> = dataStore.data.map {
        it [ADDRESS] ?: ""
    }
    val userLogin : kotlinx.coroutines.flow.Flow<String> = loginDataStore.data.map {
        it [LOGIN_STATE] ?: "false"
    }
    val userImage : kotlinx.coroutines.flow.Flow<String> = imageData.data.map {
        it [IMAGE] ?: "x"
    }

}