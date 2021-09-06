package com.example.clickme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(private val score: Int) : ViewModel() {
    //setup live data
    private val _finalScore = MutableLiveData<Int>()
    val finalScore : LiveData<Int> get() = _finalScore

    private val _hasGamble = MutableLiveData<Boolean>()
    val hasGamble : LiveData<Boolean> get() = _hasGamble

    //set vale of life data
    init {
        _finalScore.value = score
    }
    //gamble fun points
    fun gamble(){
        val swing = (-5..5).random()
        _finalScore.value = _finalScore.value?.plus(swing)
        _hasGamble.value = true
    }
}