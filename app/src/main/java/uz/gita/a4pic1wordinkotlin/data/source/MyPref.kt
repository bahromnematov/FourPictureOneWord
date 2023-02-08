package uz.gita.a4pic1wordinkotlin.data.source

import android.content.Context
import android.content.SharedPreferences
import uz.gita.a4pic1wordinkotlin.app.App

class MyPref private constructor() {
    private val preferences: SharedPreferences =
        App.appContext.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)

    companion object {

        private lateinit var myPref: MyPref

        fun getInstance(): MyPref {
            if (!this::myPref.isInitialized) {
                myPref = MyPref()
            }
            return myPref
        }
    }

    fun saveQuestionPos(pos: Int) {
        preferences.edit().putInt("QUESTION_POS", pos).apply()
    }

    fun getQuestionPos(): Int {
        return preferences.getInt("QUESTION_POS", 0)
    }

    fun saveAnsweringPos(pos: Int) {
        preferences.edit().putInt("ANSWERING_POS", pos).apply()
    }

    fun getAnsweringPos(): Int {
        return preferences.getInt("ANSWERING_POS", 1)
    }

    fun saveCoinCount(count: Int) {
        preferences.edit().putInt("COIN_COUNT", count).apply()
    }
    fun getCoinCount():Int{
        return preferences.getInt("COIN_COUNT", 100)
    }
}