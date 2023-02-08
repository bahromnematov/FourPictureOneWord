package uz.gita.a4pic1wordinkotlin.mvp.presenters

import uz.gita.a4pic1wordinkotlin.mvp.contracts.MainContract
import uz.gita.a4pic1wordinkotlin.mvp.models.MainModel

class MainPresenter constructor(private val mainView: MainContract.View): MainContract.Presenter {
    private val maiModel: MainContract.Model = MainModel()

    override fun loadCurrentQuestion() {
        mainView.setAnsweringPos(maiModel.getCurrentAnsweringPos())
        mainView.setImagesToView(maiModel.getCurrentQuestionImages())
    }

    override fun startGameActivity() {
        mainView.goToGameActivity()
    }

    override fun startAboutActivity() {
        mainView.goToAboutActivity()
    }


}