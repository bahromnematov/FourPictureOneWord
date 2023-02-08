package uz.gita.a4pic1wordinkotlin.mvp.presenters

import android.annotation.SuppressLint
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import uz.gita.a4pic1wordinkotlin.data.model.QuestionData
import uz.gita.a4pic1wordinkotlin.mvp.contracts.GameContract
import uz.gita.a4pic1wordinkotlin.mvp.models.GameModel
import uz.gita.a4pic1wordinkotlin.utils.Constants

class GamePresenter constructor(private val gameView: GameContract.View) : GameContract.Presenter {
    private val gameModel: GameContract.Model = GameModel()
    private lateinit var typedAnswers: ArrayList<String>
    private lateinit var typedVariants: ArrayList<Boolean>
    private var isDeleted: Boolean = false

    override fun loadCurrentQuestion() {
        isDeleted = false
        gameView.clearOldData()
        gameView.showQuestionImagesToView(gameModel.questionData().imageQuestions)
        gameView.showVariantsToView(gameModel.questionData().variant)
        gameView.setVisibilityToAnswer(gameModel.questionData().answer.length)
        gameView.setCoinCount(gameModel.getCoinCount())
        setCurrentAnsweringPos()
        initTypedArrays()
    }


    override fun answerBtnClick(clickedPosition: Int) {
        val question: QuestionData = gameModel.questionData()
        val variant: String = question.variant
        val typedLetter: String = typedAnswers[clickedPosition]

        for (i in 0 until Constants.MAX_VARIANTS.value) {
            if (variant[i].toString() == typedLetter && typedVariants[i]) {
                gameView.setEnabledVariantByPos(i, true)
                typedVariants[i] = false
                YoYo.with(Techniques.FlipInY).duration(600).playOn(gameView.getVariantButton()[i])
                typedAnswers[clickedPosition] = null.toString()
                gameView.setTextToAnswer(clickedPosition, "")
                gameView.setDefaultColorToAnswers()
                return
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun btnAddLetterClick() {
        val answerText: String = gameModel.questionData().answer
        if (15<= gameModel.getCoinCount()) {
            for (i in answerText.indices) {
                if (gameView.getAnswerButton()[i].text.toString() == answerText[i].toString()) {
                    continue
                }
                gameView.getAnswerButton()[i].callOnClick()
                for(j in i until answerText.length){
                    if(gameView.getAnswerButton()[j].text.toString() == answerText[i].toString()){
                        gameView.getAnswerButton()[j].callOnClick()
                        break
                    }
                }
                for (j in 0 until gameView.getVariantButton().size) {
                    if (!typedVariants[j] && answerText[i].toString() == gameView.getVariantButton()[j].text.toString()) {
                        gameView.getVariantButton()[j].callOnClick()
                        typedVariants[j] = true
                        gameView.setCorrectColorToAnswers(i)
                        gameView.getAnswerButton()[i].isClickable = false
                        saveCoinCount(gameModel.getCoinCount() - 15)
                        setCoinCount()
                        return
                    }
                }
            }
        }
    }

    override fun btnDeleteLettersClick() {
        val answerText: String = gameModel.questionData().answer
        var isClicked = false
        if (45 <= gameModel.getCoinCount() && !isDeleted) {
            for (i in 0 until gameView.getAnswerButton().size) {
                if (!answerText.contains(gameView.getAnswerButton()[i].text)) {
                    gameView.getAnswerButton()[i].callOnClick()
                }
            }
            for (i in 0 until gameView.getVariantButton().size) {
                if (!answerText.contains(gameView.getVariantButton()[i].text)) {
                    gameView.getVariantButton()[i]
                    gameView.setEnabledVariantByPos(gameView.getVariantButton()[i].tag as Int,false)
                    YoYo.with(Techniques.RubberBand).duration(600).playOn(gameView.getVariantButton()[i])
                    typedVariants[i] = true
                    isClicked = true
                }
            }
            if (isClicked) {
                isDeleted = true
                saveCoinCount(gameModel.getCoinCount() - 45)
                setCoinCount()
            }
        }
    }

    override fun setCurrentAnsweringPos() {
        gameView.setCurrentAnsweringPos(gameModel.getCurrentAnsweringPos())
    }


    override fun variantBtnClick(clickedPosition: Int) {
        val positionAnswer: Int = typedAnswers.indexOf(null.toString())
        if (positionAnswer == -1) {
            return
        }
        val question: QuestionData = gameModel.questionData()

        val variant: String = question.variant
        val letter: String = variant[clickedPosition].toString()
        gameView.setTextToAnswer(positionAnswer, letter)
        YoYo.with(Techniques.Tada).duration(600).playOn(gameView.getAnswerButton()[positionAnswer])
        typedAnswers[positionAnswer] = letter
        gameView.setEnabledVariantByPos(clickedPosition, false)
        typedVariants[clickedPosition] = true
        isWin()
    }


    override fun finishActivity() {
        gameView.closeActivity()
    }

    override fun setCoinCount() {
        gameView.setCoinCount(gameModel.getCoinCount())
    }

    override fun saveCoinCount(count: Int) {
        gameModel.saveCoinCount(count)
    }

    override fun loadNextQuestion() {
        loadCurrentQuestion()
    }


    private fun initTypedArrays() {
        val answerSize: Int = gameModel.questionData().answer.length
        typedAnswers = ArrayList()

        for (i in 0 until answerSize) {
            typedAnswers.add(null.toString())
        }

        typedVariants = ArrayList(Constants.MAX_VARIANTS.value)
        for (i in 0 until Constants.MAX_VARIANTS.value) {
            typedVariants.add(false)
        }

    }

    private fun isWin() {
        val question: QuestionData = gameModel.questionData()
        val answer: String = question.answer
        val sb = StringBuilder()
        for (i in 0 until typedAnswers.size) {
            sb.append(typedAnswers[i])
        }
        val userAnswer: String = sb.toString()

        if (userAnswer == answer) {
            gameView.setButtonsInvisible()
            gameView.showDialog(answer)
            gameModel.saveCoinCount(gameModel.getCoinCount() + 10)
            gameModel.saveCurrentAnsweringPos(gameModel.getCurrentAnsweringPos() + 1)
            saveQuestionPos(gameModel.currentQuestionPos() + 1)
            if (gameModel.currentQuestionPos() == gameModel.getQuestionsCount()) {
                gameModel.shuffleQuestions()
            }
        } else if (userAnswer.length == answer.length) {
            gameView.setWrongColorToAnswers()
            gameView.wrongAnswerAnimation()
        }
    }

    private fun saveQuestionPos(i: Int) {
        gameModel.saveCurrentQuestionPos(i)
    }
}