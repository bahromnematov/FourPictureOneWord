package uz.gita.a4pic1wordinkotlin.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import uz.gita.a4pic1wordinkotlin.R
import uz.gita.a4pic1wordinkotlin.mvp.contracts.GameContract


class CustomDialog(
    context: Activity,
    icon: Int = R.drawable.ic_tick,
    title: String = "Title",
    titleFontColor: Int = R.color.colorBlack,
    description: String = "Description",
    descriptionFontColor: Int = R.color.colorGraySeventyPercent,
    buttonFirstText: String = "Done",
    buttonSecondText: String = "Cancel",
    buttonFirstBackgroundColor: Int = R.color.colorAccent,
    buttonFirstFontColor: Int = R.color.colorBlack,
    buttonSecondBackgroundColor: Int = R.color.colorAccent,
    buttonSecondFontColor: Int = R.color.colorBlack,
    dialogBackgroundColor: Int = R.drawable.radius_white_background,
    cancelButton: Boolean = true,
    setCancelable: Boolean = true,
    secondButton: Boolean = true

) : Dialog(context) {
    private lateinit var presenter: GameContract.Presenter

    lateinit var onCancelDialog: () -> Unit
    lateinit var onPerformAction: () -> Unit


//    init {
//        setContentView(R.layout.dialog_custom_coin)
//        val display = DisplayMetrics()
//        context.windowManager.defaultDisplay.getMetrics(display)
//        window!!.setGravity(Gravity.CENTER)
//        window!!.setBackgroundDrawable(
//            ColorDrawable(
//                ContextCompat.getColor(
//                    context,
//                    android.R.color.transparent
//                )
//            )
//        )
//
//        val textViewDescription = findViewById<TextView>(R.id.textViewPopupDescription)
//        val textViewTitle = findViewById<TextView>(R.id.textViewPopupTitle)
//        val buttonFirst = findViewById<Button>(R.id.buttonFirst)
//        val buttonSecond = findViewById<Button>(R.id.buttonSecond)
//        val dialog = findViewById<LinearLayout>(R.id.dialogPopup)
////        val imageViewCancel = findViewById<ImageView>(R.id.imageViewPopupCancel)
//        val imageViewHeader = findViewById<ImageView>(R.id.imageViewPopupDialog)
//
//        dialog.layoutParams.width = ((display.widthPixels / 100) * 95)
//
//        imageViewHeader.setImageResource(icon)
//
//        textViewTitle.text = title
//        textViewTitle.setTextColor(
//            getContext().resources.getColor(
//                titleFontColor
//            )
//        )
//
//        textViewDescription.text = description
//        textViewDescription.setTextColor(
//            getContext().resources.getColor(
//                descriptionFontColor
//            )
//        )
//        buttonFirst.text = buttonFirstText
//        buttonFirst.setTextColor(
//            getContext().resources.getColor(
//                buttonFirstFontColor
//            )
//        )
//        buttonFirst.setBackgroundColor(
//            getContext().resources.getColor(
//                buttonFirstBackgroundColor
//            )
//        )
//
//        buttonSecond.text = buttonSecondText
//        buttonSecond.setTextColor(
//            getContext().resources.getColor(
//                buttonSecondFontColor
//            )
//        )
//        buttonSecond.setBackgroundColor(
//            getContext().resources.getColor(
//                buttonSecondBackgroundColor
//            )
//        )
//
//        dialog.setBackgroundDrawable(
//            ContextCompat.getDrawable(
//                getContext(),
//                dialogBackgroundColor
//            )
//        )
//        if (cancelButton) {
//            imageViewCancel.visibility = View.VISIBLE
//        } else {
//            imageViewCancel.visibility = View.INVISIBLE
//        }
//
//        if (secondButton) {
//            buttonSecond.visibility = View.VISIBLE
//        } else {
//            buttonSecond.visibility = View.GONE
//        }
//
//        setCancelable(setCancelable)
//
//        // set listeners
//
//        imageViewCancel.setOnClickListener { onCancelDialog.invoke() }
//        buttonSecond.setOnClickListener { onCancelDialog.invoke() }
//        buttonFirst.setOnClickListener {presenter.btnDeleteLettersClick() }
//
//    }

}