package uz.gita.a4pic1wordinkotlin.mvp.models

import uz.gita.a4pic1wordinkotlin.data.model.QuestionData
import uz.gita.a4pic1wordinkotlin.domain.Repository
import uz.gita.a4pic1wordinkotlin.mvp.contracts.GameContract

class GameModel : GameContract.Model {
    private val repository = Repository.getInstance()

    override fun questionData(): QuestionData =
        repository.getCurrentQuestionData()

    override fun currentQuestionPos(): Int =
        repository.getCurrentQuestionPos()

    override fun saveCurrentAnsweringPos(pos: Int) {
        repository.saveAnsweringPos(pos)
    }

    override fun getCurrentAnsweringPos(): Int {
        return repository.getAnsweringPos()
    }

    override fun saveCurrentQuestionPos(pos: Int) =
        repository.saveCurrentQuestionByPos(pos)

    override fun getQuestionsCount(): Int {
       return repository.getQuestionsCount()
    }

    override fun shuffleQuestions() {
        repository.shuffleQuestionData()
    }

    override fun saveCoinCount(count: Int) {
        repository.saveCoinCount(count)
    }

    override fun getCoinCount(): Int {
        return repository.getCoinCount()
    }
}