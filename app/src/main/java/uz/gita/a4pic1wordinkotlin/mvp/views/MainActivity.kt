package uz.gita.a4pic1wordinkotlin.mvp.views

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import uz.gita.a4pic1wordinkotlin.R
import uz.gita.a4pic1wordinkotlin.mvp.contracts.MainContract
import uz.gita.a4pic1wordinkotlin.mvp.presenters.MainPresenter
import uz.gita.a4pic1wordinkotlin.utils.Constants


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var playButton: AppCompatButton
    private lateinit var aboutButton: AppCompatButton
    private lateinit var exitButton: AppCompatButton
    private lateinit var questionsImg: MutableList<ImageView>
    private lateinit var currentQuestionPos: AppCompatTextView
    var mediaClick: MediaPlayer? = null


    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaClick = MediaPlayer()
        mediaClick = MediaPlayer.create(this, R.raw.click5)

        presenter = MainPresenter(this)
        findViews()
        connectClickActions()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCurrentQuestion()
    }

    private fun findViews() {
        questionsImg = ArrayList()

        questionsImg.add(findViewById(R.id.image1))
        questionsImg.add(findViewById(R.id.image2))
        questionsImg.add(findViewById(R.id.image3))
        questionsImg.add(findViewById(R.id.image4))

        currentQuestionPos = findViewById(R.id.textQuestionPos)

        playButton = findViewById(R.id.buttonPlay)
        aboutButton = findViewById(R.id.buttonAbout)
//        exitButton = findViewById(R.id.butt)
    }

    private fun connectClickActions() {
        playButton.setOnClickListener {
            mediaClick?.start()
            presenter.startGameActivity()
        }

        aboutButton.setOnClickListener {
            mediaClick?.start()
            presenter.startAboutActivity()
        }
//        exitButton.setOnClickListener{
//            finish()
//        }
    }

    override fun setImagesToView(images: List<Int>) {
        for (i in 0 until Constants.MAX_IMAGES.value) {
            questionsImg[i].setImageResource(images[i])
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setAnsweringPos(pos: Int) {
        currentQuestionPos.text = (pos).toString()
    }

    override fun goToGameActivity() {
        startActivity(Intent(this, GameActivity::class.java))
    }

    override fun goToAboutActivity() {
        startActivity(Intent(this, AboutActivity::class.java))
    }
}