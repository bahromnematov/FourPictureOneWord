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

class CoinHintActivity : DialogFragment(R.layout.dialog_custom_coin_hint) {
    companion object {
        lateinit var listener_coin_hint: View.OnClickListener
        lateinit var listener_back_coin_hint: View.OnClickListener
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        setListener()
        setListenerBack()
    }


    private fun setListener() {
        val backBtn = view?.findViewById<TextView>(R.id.buttonFirstHint)
        backBtn?.setOnClickListener(listener_coin_hint)
    }
    private fun setListenerBack() {
        val backBtn = view?.findViewById<TextView>(R.id.buttonSecondHint)
        backBtn?.setOnClickListener(listener_back_coin_hint)
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }
}

