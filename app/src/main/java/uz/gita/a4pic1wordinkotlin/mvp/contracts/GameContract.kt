package uz.gita.a4pic1wordinkotlin.mvp.contracts

import android.content.Context
import android.widget.TextView
import uz.gita.a4pic1wordinkotlin.data.model.QuestionData

interface GameContract {

    interface Model {
        fun questionData(): QuestionData
        fun currentQuestionPos(): Int
        fun saveCurrentAnsweringPos(pos: Int)
        fun getCurrentAnsweringPos(): Int
        fun saveCurrentQuestionPos(pos: Int)
        fun getQuestionsCount(): Int
        fun shuffleQuestions()
        fun saveCoinCount(count: Int)
        fun getCoinCount():Int
    }

    interface Presenter {
        fun loadCurrentQuestion()
        fun answerBtnClick(clickedPosition: Int)
        fun variantBtnClick(clickedPosition: Int)
        fun finishActivity()
        fun setCoinCount()
        fun saveCoinCount(count : Int)
        fun loadNextQuestion()
        fun btnAddLetterClick()
        fun btnDeleteLettersClick()
        fun setCurrentAnsweringPos()
    }

    interface View {
        fun showQuestionImagesToView(questionImages: List<Int>)

        fun showVariantsToView(variant: String)
        fun setVisibilityToAnswer(answerLength: Int)
        fun clearOldData()
        fun closeActivity()
        fun showDialog(answer : String)

        fun setTextToAnswer(pos: Int, letter: String)
        fun setButtonsInvisible()
        fun setEnabledVariantByPos(pos: Int, state: Boolean)

        fun setCurrentAnsweringPos(int: Int)

        fun wrongAnswerAnimation()

        fun setWrongColorToAnswers()
        fun setCorrectColorToAnswers(pos: Int)
        fun setDefaultColorToAnswers()

        fun setCoinCount(count : Int)

        fun getAnswerButton(): MutableList<TextView>
        fun getVariantButton() : MutableList<TextView>
        fun setDefaultBGColorToAnswers()
        fun showDialogs(title:String)
        fun showDialogHint(answer : String)
    }
}