package uz.gita.a4pic1wordinkotlin.mvp.models

import uz.gita.a4pic1wordinkotlin.domain.Repository
import uz.gita.a4pic1wordinkotlin.mvp.contracts.MainContract

class MainModel: MainContract.Model {
    private val repository: Repository = Repository.getInstance()

    override fun getCurrentAnsweringPos()= repository.getAnsweringPos()


    override fun getCurrentQuestionImages(): List<Int> {
        return repository.getCurrentQuestionData().imageQuestions
    }
}