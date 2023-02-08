package uz.gita.a4pic1wordinkotlin.mvp.views

import android.app.Dialog
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.a4pic1wordinkotlin.R
import uz.gita.a4pic1wordinkotlin.mvp.contracts.GameContract
import uz.gita.a4pic1wordinkotlin.mvp.presenters.GamePresenter
import uz.gita.a4pic1wordinkotlin.utils.Constants.*
import uz.gita.a4pic1wordinkotlin.utils.CustomDialog

class GameActivity : AppCompatActivity(), GameContract.View {
    private lateinit var presenter: GameContract.Presenter
    private lateinit var questionsImg: MutableList<ImageView>
    private lateinit var variants: MutableList<TextView>
    private lateinit var answers: MutableList<TextView>
    private lateinit var btnAddLetter: ImageView
    private lateinit var btnDeleteLetters: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var txtCurrentGame: TextView
    private lateinit var txtCoinCount: TextView
    var mediaClick: MediaPlayer? = null
    var mediaClick2: MediaPlayer? = null
    var mediaClick3: MediaPlayer? = null
//    val dialog=CustomDialog(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mediaClick = MediaPlayer()
        mediaClick = MediaPlayer.create(this, R.raw.click5)

        mediaClick2 = MediaPlayer()
        mediaClick2 = MediaPlayer.create(this, R.raw.wrong)


        mediaClick3 = MediaPlayer()
        mediaClick3 = MediaPlayer.create(this, R.raw.correct)



        presenter = GamePresenter(this)
        findViews()
        presenter.loadCurrentQuestion()
    }

    private fun findViews() {
        questionsImg = ArrayList(MAX_IMAGES.value)

        questionsImg.add(findViewById(R.id.image1))
        questionsImg.add(findViewById(R.id.image2))
        questionsImg.add(findViewById(R.id.image3))
        questionsImg.add(findViewById(R.id.image4))

        val parentViewGroupOfAnswers = findViewById<LinearLayout>(R.id.answers)
        answers = ArrayList()
        for (i in 0 until MAX_ANSWERS.value) {
            val btn: TextView = parentViewGroupOfAnswers.getChildAt(i) as TextView
            btn.tag = i
            answers.add(btn)
        }
        variants = ArrayList()
        val parentViewGroupOfVariants = findViewById<RelativeLayout>(R.id.variants)
        var count = 0
        for (i in 0 until parentViewGroupOfVariants.childCount) {
            val btn = parentViewGroupOfVariants.getChildAt(i)
            if (btn is TextView) {
                btn.tag = count++
                variants.add(btn)
            }
        }
        btnAddLetter = findViewById(R.id.btnAddLetter)
        btnDeleteLetters = findViewById(R.id.btnDeleteLetter)
        txtCoinCount = findViewById(R.id.txtCoinCount)
        txtCurrentGame = findViewById(R.id.textQuestionPosInGame)
        btnBack = findViewById(R.id.btnBackToMenu)
        addClickActions()
    }

    private fun addClickActions() {
        for (i in 0 until MAX_VARIANTS.value) {
            variants[i].setOnClickListener {
                mediaClick?.start()
                presenter.variantBtnClick(it.tag as Int)
            }
        }

        for (i in 0 until MAX_ANSWERS.value) {
            answers[i].setOnClickListener {
                mediaClick?.start()
                presenter.answerBtnClick(it.tag as Int)
            }
        }
        btnDeleteLetters.setOnClickListener {
            showDialogs("Salom")
            mediaClick?.start()
        }
        btnAddLetter.setOnClickListener {
            mediaClick?.start()
//            presenter.btnAddLetterClick()
            showDialogHint("Salom")
        }
        btnBack.setOnClickListener {
            mediaClick?.start()
            presenter.finishActivity()
        }
    }

    override fun showQuestionImagesToView(questionImages: List<Int>) {
        for (i in 0 until MAX_IMAGES.value) {
            this.questionsImg[i].setImageResource(questionImages[i])
        }
    }

    override fun showVariantsToView(variant: String) {
        for (i in variant.indices) {
            variants[i].text = variant[i].toString()
        }
    }

    override fun setVisibilityToAnswer(answerLength: Int) {
        btnDeleteLetters.visibility = View.VISIBLE
        btnAddLetter.visibility = View.VISIBLE
        for (i in 0 until answerLength) {
            answers[i].visibility = View.VISIBLE
        }

        for (i in answerLength until MAX_ANSWERS.value) {
            answers[i].visibility = View.GONE
        }
    }

    override fun clearOldData() {
        for (answerBtn in answers) {
            answerBtn.text = ""

        }
        setDefaultColorToAnswers()
        for (variantBtn in variants) {
            variantBtn.visibility = View.VISIBLE
            variantBtn.isEnabled = true
        }
        setDefaultBGColorToAnswers()
    }

    override fun closeActivity() {
        finish()
    }

    override fun showDialog(answer: String) {
        mediaClick3?.start()
        val dialog = WinActivity()

        WinActivity.listener = View.OnClickListener {
            presenter.loadNextQuestion()
            presenter.setCurrentAnsweringPos()
            setDefaultBGColorToAnswers()
            dialog.dismiss()
        }
        WinActivity.listener_back=View.OnClickListener {
            finish()

        }
        WinActivity.answer = answer
        dialog.show(supportFragmentManager, "dialog")

    }

    override fun setTextToAnswer(pos: Int, letter: String) {
        answers[pos].isClickable = true
        answers[pos].text = letter
    }

    override fun setButtonsInvisible() {
        for (i in 0 until MAX_ANSWERS.value) {
            answers[i].visibility = View.GONE
        }
        for (i in 0 until MAX_VARIANTS.value) {
            variants[i].visibility = View.GONE
        }
        btnDeleteLetters.visibility = View.GONE
        btnAddLetter.visibility = View.GONE
    }

    override fun setEnabledVariantByPos(pos: Int, state: Boolean) {
        variants[pos].isEnabled = state
    }

    override fun setCurrentAnsweringPos(int: Int) {
        txtCurrentGame.text = int.toString()
    }

    override fun wrongAnswerAnimation() {
        mediaClick2?.start()
        YoYo.with(Techniques.Swing).duration(600).playOn(findViewById(R.id.answers))

    }

    override fun setWrongColorToAnswers() {
        for (i in 0 until MAX_ANSWERS.value) {
            answers[i].setTextColor(resources.getColor(R.color.red));
        }
    }

    override fun setCorrectColorToAnswers(pos: Int) {
        answers[pos].setBackgroundResource(R.drawable.bg_answers_green)
    }

    override fun setDefaultColorToAnswers() {
        for (i in 0 until MAX_ANSWERS.value) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    answers[i].setTextColor(resources.getColor(R.color.white));
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    answers[i].setTextColor(resources.getColor(R.color.black));
                }
            }
        }
    }

    override fun setDefaultBGColorToAnswers() {
        for (i in 0 until MAX_ANSWERS.value) {
            answers[i].setBackgroundResource(R.drawable.bg_answers)
        }
    }

    override fun setCoinCount(count: Int) {
        txtCoinCount.text = count.toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.finishActivity()
    }

    override fun getAnswerButton(): MutableList<TextView> = answers
    override fun getVariantButton(): MutableList<TextView> = variants

    override fun showDialogs(title: String) {
      val dialog=CoinActivity()
        CoinActivity.listener_coin=View.OnClickListener {
            presenter.btnDeleteLettersClick()
            dialog.dismiss()
        }
        CoinActivity.listener_back_coin=View.OnClickListener {
            dialog.dismiss()
        }
        dialog.show(supportFragmentManager, "dialog")

    }

    override fun showDialogHint(answer: String) {
        val dialog=CoinHintActivity()
        CoinHintActivity.listener_coin_hint=View.OnClickListener {
            presenter.btnAddLetterClick()
            dialog.dismiss()
        }
        CoinHintActivity.listener_back_coin_hint=View.OnClickListener {
            dialog.dismiss()
        }
        dialog.show(supportFragmentManager, "dialog")

    }
}