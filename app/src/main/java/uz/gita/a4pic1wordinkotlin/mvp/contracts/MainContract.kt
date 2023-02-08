package uz.gita.a4pic1wordinkotlin.mvp.contracts

interface MainContract {
    interface Model {
        fun getCurrentAnsweringPos():Int
        fun getCurrentQuestionImages():List<Int>
    }

    interface Presenter {
        fun loadCurrentQuestion()
        fun startGameActivity()
        fun startAboutActivity()
    }

    interface View {
        fun setImagesToView(images:List<Int>)
        fun setAnsweringPos(pos:Int)

        fun goToGameActivity()
        fun goToAboutActivity()
    }
}