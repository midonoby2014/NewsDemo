package com.noby.tempotask.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.noby.tempotask.R
import com.noby.tempotask.databinding.ActivitySplashBinding
import com.noby.tempotask.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel : SplashViewModel by viewModels()
    lateinit var binding:ActivitySplashBinding
    var animFadein: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =   DataBindingUtil.setContentView(this, R.layout.activity_splash)
        getSupportActionBar()!!.hide()
        InilizeVaribles()
    }

     fun InilizeVaribles() {
        animFadein = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        binding!!.txtlogo.startAnimation(animFadein)
        viewModel.initializeObjects(this)


        lifecycleScope.launchWhenStarted {
            viewModel.taskEvent.collect {     event ->
                when (event) {
                    is SplashViewModel.TaskEvent.NavigateToHome -> {
                        openHomeActivity()
                    }
                }
            }
        }
    }

    fun openHomeActivity() {
        val intent: Intent
        intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    override fun onStart() {
        super.onStart()
        viewModel.startSplashViewTimer()
    }

    override fun onStop() {
        super.onStop()
        viewModel.stopSplashViewTimer()
    }
}
