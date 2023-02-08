package uz.gita.a4pic1wordinkotlin.domain

import uz.gita.a4pic1wordinkotlin.R
import uz.gita.a4pic1wordinkotlin.data.model.QuestionData
import uz.gita.a4pic1wordinkotlin.data.source.MyPref
import java.util.*
import kotlin.collections.ArrayList

class Repository private constructor() {
    private val questionsList: MutableList<QuestionData> = ArrayList()
    private val myPref: MyPref = MyPref.getInstance()

    init {
        initQuestions()
    }

    companion object {
        private lateinit var repository: Repository

        fun getInstance(): Repository {
            if (!this::repository.isInitialized) {
                repository = Repository()
            }
            return repository
        }
    }

    fun saveCurrentQuestionByPos(pos: Int) {
        myPref.saveQuestionPos(pos)
    }

    fun getCurrentQuestionData(): QuestionData =
        questionsList[myPref.getQuestionPos()]

    fun getCurrentQuestionPos(): Int {
        return myPref.getQuestionPos()
    }

    fun shuffleQuestionData() {
        myPref.saveQuestionPos(0)
        questionsList.shuffle()
    }

    fun saveAnsweringPos(pos: Int) {
        myPref.saveAnsweringPos(pos)
    }

    fun getAnsweringPos(): Int {
        return myPref.getAnsweringPos()
    }

    fun getQuestionsCount(): Int {
        return questionsList.size
    }
    fun saveCoinCount(count: Int) {
        myPref.saveCoinCount(count)
    }
    fun getCoinCount():Int{
        return myPref.getCoinCount()
    }


    private fun initQuestions() {
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.work_1,
                    R.drawable.work_2,
                    R.drawable.work_3,
                    R.drawable.work_4
                ), "work", generateVariant("work")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.color_1,
                    R.drawable.color_2,
                    R.drawable.color_3,
                    R.drawable.color_4
                ), "color", generateVariant("color")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.back_1,
                    R.drawable.back_2,
                    R.drawable.back_3,
                    R.drawable.back_4
                ), "back", generateVariant("back")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.box_1,
                    R.drawable.box_2,
                    R.drawable.box_3,
                    R.drawable.box_4
                ), "box", generateVariant("box")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.circus_1,
                    R.drawable.circus_2,
                    R.drawable.circus_3,
                    R.drawable.circus_4
                ), "circus", generateVariant("circus")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.clown_1,
                    R.drawable.clown_2,
                    R.drawable.clown_3,
                    R.drawable.clown_4
                ), "clown", generateVariant("clown")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.cotton_1,
                    R.drawable.cotton_2,
                    R.drawable.cotton_3,
                    R.drawable.cotton_4
                ), "cotton", generateVariant("cotton")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.cowboy_1,
                    R.drawable.cowboy_2,
                    R.drawable.cowboy_3,
                    R.drawable.cowboy_4
                ), "cowboy", generateVariant("cowboy")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.egg_1,
                    R.drawable.egg_2,
                    R.drawable.egg_3,
                    R.drawable.egg_4
                ), "egg", generateVariant("egg")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.family_1,
                    R.drawable.family_2,
                    R.drawable.family_3,
                    R.drawable.family_4
                ), "family", generateVariant("family")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.farm_1,
                    R.drawable.farm_2,
                    R.drawable.farm_3,
                    R.drawable.farm_4
                ), "farm", generateVariant("farm")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.fruit_1,
                    R.drawable.fruit_2,
                    R.drawable.fruit_3,
                    R.drawable.fruit_4
                ), "fruit", generateVariant("fruit")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.gift_1,
                    R.drawable.gift_2,
                    R.drawable.gift_3,
                    R.drawable.gift_4
                ), "gift", generateVariant("gift")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.jump_1,
                    R.drawable.jump_2,
                    R.drawable.jump_3,
                    R.drawable.jump_4
                ), "jump", generateVariant("jump")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.king_1,
                    R.drawable.king_2,
                    R.drawable.king_3,
                    R.drawable.king_4
                ), "king", generateVariant("king")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.letter_1,
                    R.drawable.letter_2,
                    R.drawable.letter_3,
                    R.drawable.letter_4
                ), "letter", generateVariant("letter")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.paint_1,
                    R.drawable.paint_2,
                    R.drawable.paint_3,
                    R.drawable.paint_4
                ), "paint", generateVariant("paint")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.pie_1,
                    R.drawable.pie_2,
                    R.drawable.pie_3,
                    R.drawable.pie_4
                ), "pie", generateVariant("pie")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.pot_1,
                    R.drawable.pot_2,
                    R.drawable.pot_3,
                    R.drawable.pot_4
                ), "pot", generateVariant("pot")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.pull_1,
                    R.drawable.pull_2,
                    R.drawable.pull_3,
                    R.drawable.pull_4
                ), "pull", generateVariant("pull")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.sand_1,
                    R.drawable.sand_2,
                    R.drawable.sand_3,
                    R.drawable.sand_4
                ), "sand", generateVariant("sand")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.snail_1,
                    R.drawable.snail_2,
                    R.drawable.snail_3,
                    R.drawable.snail_4
                ), "snail", generateVariant("snail")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.spring_1,
                    R.drawable.spring_2,
                    R.drawable.spring_3,
                    R.drawable.spring_4
                ), "spring", generateVariant("spring")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.sweet_1,
                    R.drawable.sweet_2,
                    R.drawable.sweet_3,
                    R.drawable.sweet_4
                ), "sweet", generateVariant("sweet")
            )
        )
        questionsList.add(
            QuestionData(
                mutableListOf(
                    R.drawable.tie_1,
                    R.drawable.tie_2,
                    R.drawable.tie_3,
                    R.drawable.tie_4
                ), "tie", generateVariant("tie")
            )
        )
    }

    private fun generateVariant(answer: String): String {
        val variant: MutableList<Char> = ArrayList(12)
        val rd = Random()
        for (i in answer) {
            variant.add(i)
        }
        while (variant.size != 12) {
            var ch: Char
            do {
                ch = (rd.nextInt(26) + 97).toChar()
            } while (answer.contains(ch))
            variant.add(ch)
        }
        variant.shuffle()
        val sb = StringBuilder()
        for (i in variant.indices) {
            sb.append(variant[i])
        }

        return sb.toString()
    }
}

