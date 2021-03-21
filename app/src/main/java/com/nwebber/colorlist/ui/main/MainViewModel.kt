package com.nwebber.colorlist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class ColorVocab : ArrayList<ColorDefinition>()

data class ColorDefinition(val id: Int, val hexString: String, val rgb: Triple<Int, Int, Int>, val hsl: Triple<Int, Int, Int>, val name: String)

class ColorList(jsonString: String) {
    var colors = ArrayList<ColorDefinition>()
    init {
        val gson = Gson()
        colors = gson.fromJson(jsonString, ColorVocab::class.java)
    }
}

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private lateinit var colorList: ColorList

    private val _colorVocab = MutableLiveData<List<ColorDefinition>>()
    var colorVocab: LiveData<List<ColorDefinition>> = _colorVocab

    init {
        val jsonString = app.assets.open("data.json").bufferedReader().use {it.readText()}
        colorList = ColorList(jsonString)

        _colorVocab.value = colorList.colors

        colorList.colors.forEach{ //iterate through the color list and print each line
            //println(it)
            Log.i("DATA", it.toString())
        }
    }
}