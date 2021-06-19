package com.noby.tempotask.ui.splash

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Noby Ahmed on 6/19/21.
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel(){
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private val taskEventChannel =  Channel<TaskEvent>()
    val taskEvent = taskEventChannel.receiveAsFlow()
    private var context: Context? = null


    fun initializeObjects(context: Context) {
        this.context =  context
        handler = Handler()
        runnable = Runnable {
            activityFinishedSplashTimer() }
    }
    fun startSplashViewTimer() {
        handler!!.postDelayed(runnable!!, 3500)
    }
    fun stopSplashViewTimer() {
        if (handler != null && runnable != null) {
            handler!!.removeCallbacks(runnable!!)
        }
    }

    fun activityFinishedSplashTimer()=  viewModelScope.launch {
            taskEventChannel.send(TaskEvent.NavigateToHome)
    }


    sealed class TaskEvent{
        object NavigateToHome : TaskEvent()
    }

}