package uz.gita.a4pic1wordinkotlin.mvp.views

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import uz.gita.a4pic1wordinkotlin.R

class WinActivity : DialogFragment(R.layout.dialog_layout) {
    companion object {
        lateinit var listener: View.OnClickListener
        lateinit var listener_back: View.OnClickListener
        lateinit var answer: String
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
//        val imgRotate = view.findViewById<ImageView>(R.id.imgRotation)
//        imgRotate.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_animation))
        setAnswer(answer)
        setListener()
        setListenerBack()
    }

    private fun setAnswer(answerText: String) {
        val container: RelativeLayout? = view?.findViewById(R.id.answersContainer)
        for (i in 0 until container!!.childCount) {
            if (container.getChildAt(i) is TextView) {
                if (i >= answerText.length) {
                    container.getChildAt(i).visibility = View.GONE
                } else {
                    container.getChildAt(i).visibility = View.VISIBLE
                    container.getChildAt(i).tag = i
                    (container.getChildAt(i) as TextView).text = answerText[i].toString()
                }
            }
        }
    }

    private fun setListener() {
        val backBtn = view?.findViewById<TextView>(R.id.btnNextQuestion)
        backBtn?.setOnClickListener(listener)
    }
    private fun setListenerBack() {
        val backBtn = view?.findViewById<TextView>(R.id.btnQuitQuestion)
        backBtn?.setOnClickListener(listener_back)
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.attributes = params
    }
}

