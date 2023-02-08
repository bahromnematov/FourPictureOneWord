package uz.gita.a4pic1wordinkotlin.data.model

data class QuestionData constructor(
    val imageQuestions: MutableList<Int>,
    val answer: String,
    val variant: String
)
