package com.example.clickme

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val timer : CountDownTimer
    //set live data
    private val _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long> get() = _currentTime

    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int> get() = _score

    private val _currentButton = MutableLiveData<Int>()
    val currentButton : LiveData<Int> get() = _currentButton

    private val _scoreColor = MutableLiveData<String>()
    val scoreColor : LiveData<String> get() = _scoreColor

    private val _gameFinish = MutableLiveData<Boolean>()
    val gameFinish : LiveData<Boolean> get() = _gameFinish

    init{
        _score.value = 0
        _currentButton.value = (1..8).random()
        _scoreColor.value = "purple"
        _gameFinish.value = false

        //set the timer
        timer = object : CountDownTimer(31000,1000){
            override fun onTick(p0: Long) {
                _currentTime.value = p0 / 1000
                _scoreColor.value = "purple"
            }

            override fun onFinish() {
                _gameFinish.value = true
            }

        }
        timer.start()
    }
    //to give point
    fun gainPoint(){
        _score.value = _score.value?.plus(1)
        _currentButton.value = (1..8).random()
        _scoreColor.value = "purple"
    }
    //lose point fun
    fun losePoint(){
        _score.value = _score.value?.plus(-1)
        _currentButton.value = (1..8).random()
        _scoreColor.value = "red"
    }
}