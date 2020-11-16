package com.example.aula33kotlin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import java.io.IOException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Gambiarra
    private val context = getApplication<Application>().applicationContext

    //Transformando o JSON em um ArrayList
        fun getGastos(): ArrayList<Gasto> {

        var listaGastos: ArrayList<Gasto> = arrayListOf()

        try {

            //Lendo o JSON
            val jsonString = context.assets.open("gastos.json")
                    .bufferedReader()
                    .use { it.readText() }

            //Passando pra um Array
            var jsonArray = JSONArray(jsonString)

            //Percorrendo Array
            for (i in 0 until jsonArray.length() - 1) {
                val jsonObject = jsonArray.getJSONObject(i)

                val gasto = Gasto(jsonObject.getInt("id"),
                        jsonObject.getString("desc"),
                        jsonObject.getDouble("valor")
                )
                listaGastos.add(gasto)
            }
            return listaGastos

        } catch (ioException: IOException) {
            Log.i("MainViewModel", ioException.toString())

            return listaGastos
        }
    }

}