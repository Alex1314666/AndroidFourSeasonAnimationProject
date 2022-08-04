package yiz.example.myapplication

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.icu.text.SimpleDateFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.renderscript.Sampler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var wheelImageView: ImageView
    lateinit var cloudImageView: ImageView
    lateinit var sunImageView: ImageView
    lateinit var birdImageView: ImageView
    lateinit var botFragLinearLayout: LinearLayout
    lateinit var topFragmentLayout: FrameLayout
    lateinit var botFragImageViewSeason:ImageView

    lateinit var animaRotate: Animation
    lateinit var animaMove:Animation
    var botBackgroundAnim: ValueAnimator? = null
    lateinit var topBackgroundAnim:ValueAnimator
    lateinit var animFadeIn: Animation
    var mediaPlayer:MediaPlayer? = null

    private val handler =  Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var currentTime:Date = Calendar.getInstance().getTime();
        Log.i("Time", currentTime.toString())

    }

    var r:Runnable = object:Runnable{
        override fun run() {
            Log.i("Test", "hi")
        }

    }

    fun onStartClick(view: View) {
        // Top Frag
        cloudImageView = findViewById(R.id.TopFragImageViewCloud)
        sunImageView = findViewById(R.id.TopFragImageViewSun)
        birdImageView = findViewById(R.id.TopFragImageViewVBirds)
        topFragmentLayout = findViewById(R.id.TopFragmentLayout)

        animaMove = AnimationUtils.loadAnimation(applicationContext,R.anim.movecloud)
        cloudImageView.startAnimation(animaMove)
        animaMove = AnimationUtils.loadAnimation(applicationContext,R.anim.movesun)
        sunImageView.startAnimation(animaMove)
        animaMove = AnimationUtils.loadAnimation(applicationContext,R.anim.movebird)
        birdImageView.startAnimation(animaMove)

        topBackgroundAnim = ObjectAnimator.ofObject(topFragmentLayout, "backgroundColor", ArgbEvaluator(),getColor(R.color.lightblue), getColor(R.color.darkblue))
        topBackgroundAnim.setDuration(2000)
        topBackgroundAnim.repeatCount = ValueAnimator.INFINITE;
        topBackgroundAnim.repeatMode = ValueAnimator.RESTART;
        topBackgroundAnim.start()


        // Bot Frag
        wheelImageView = findViewById(R.id.BotFragImageViewWheel)
        animaRotate = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
        wheelImageView.startAnimation(animaRotate)

        botFragLinearLayout = findViewById(R.id.BotFragLinearLayout)

        var countLoop = 1

        handler.post(object : Runnable{
            override fun run() {

                when(countLoop % 4){
                    1 -> {
                        spring()
                    }
                    2 -> {
                        summer()
                    }
                    3 -> {
                        autumn()
                    }
                    0 -> {
                        winter()
                    }
                }
                countLoop++
                handler.postDelayed(this,15000)
            }
        })


    }

    fun spring(){
        botBackgroundAnim?.cancel()
        botBackgroundAnim = ObjectAnimator.ofObject(botFragLinearLayout, "backgroundColor", ArgbEvaluator(),getColor(R.color.orangered), getColor(R.color.darkseagreen))
        botBackgroundAnim?.setDuration(15000)
        botBackgroundAnim?.repeatCount = 0
        botBackgroundAnim?.start()

        stopMusic()
        mediaPlayer = MediaPlayer.create(this,R.raw.spring_song)
        mediaPlayer?.start()
        botFragImageViewSeason = findViewById(R.id.BotFragImageViewSeason)
        animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        botFragImageViewSeason.setImageResource(R.drawable.spring)
        botFragImageViewSeason.startAnimation(animFadeIn)
    }

    fun summer(){
        botBackgroundAnim?.cancel()
        botBackgroundAnim = ObjectAnimator.ofObject(botFragLinearLayout, "backgroundColor", ArgbEvaluator(), getColor(R.color.darkseagreen), getColor(R.color.yellow))
        botBackgroundAnim?.setDuration(15000)
        botBackgroundAnim?.repeatCount = 0
        botBackgroundAnim?.start()

        stopMusic()
        mediaPlayer = MediaPlayer.create(this,R.raw.summer_song)
        mediaPlayer?.start()
        botFragImageViewSeason = findViewById(R.id.BotFragImageViewSeason)
        animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        botFragImageViewSeason.setImageResource(R.drawable.summer)
        botFragImageViewSeason.startAnimation(animFadeIn)
    }

    fun autumn(){
        botBackgroundAnim?.cancel()
        botBackgroundAnim = ObjectAnimator.ofObject(botFragLinearLayout, "backgroundColor", ArgbEvaluator(), getColor(R.color.yellow), getColor(R.color.white))
        botBackgroundAnim?.setDuration(15000)
        botBackgroundAnim?.repeatCount = 0
        botBackgroundAnim?.start()

        stopMusic()
        mediaPlayer = MediaPlayer.create(this,R.raw.autumn_song)
        mediaPlayer?.start()
        botFragImageViewSeason = findViewById(R.id.BotFragImageViewSeason)
        animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        botFragImageViewSeason.setImageResource(R.drawable.autumn)
        botFragImageViewSeason.startAnimation(animFadeIn)
    }

    fun winter(){
        botBackgroundAnim?.cancel()
        botBackgroundAnim = ObjectAnimator.ofObject(botFragLinearLayout, "backgroundColor", ArgbEvaluator(), getColor(R.color.white), getColor(R.color.orangered))
        botBackgroundAnim?.setDuration(15000)
        botBackgroundAnim?.repeatCount = 0
        botBackgroundAnim?.start()

        stopMusic()
        mediaPlayer = MediaPlayer.create(this,R.raw.winter_song)
        mediaPlayer?.start()
        botFragImageViewSeason = findViewById(R.id.BotFragImageViewSeason)
        animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        botFragImageViewSeason.setImageResource(R.drawable.winter)
        botFragImageViewSeason.startAnimation(animFadeIn)
    }

    fun stopMusic(){
        if(mediaPlayer?.isPlaying == true){
            mediaPlayer?.stop()
        }
    }


    fun onStopClick(view: View) {
        // Top Frag
        cloudImageView.clearAnimation()
        sunImageView.clearAnimation()
        birdImageView.clearAnimation()

        topBackgroundAnim.cancel()
        topFragmentLayout.setBackgroundColor(getColor(R.color.lightblue))

        // Bot Frag
        wheelImageView.clearAnimation()
        botBackgroundAnim?.cancel()
        botFragLinearLayout.setBackgroundColor(getColor(R.color.orangered))

        stopMusic()
        botFragImageViewSeason = findViewById(R.id.BotFragImageViewSeason)
        botFragImageViewSeason.setImageResource(R.drawable.spring)
        botFragImageViewSeason.clearAnimation()

        handler.removeMessages(0)
    }
}