package com.vince.mercadolibre.utils.views

import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.vince.mercadolibre.utils.ConstantsHelper.DELAY_PREVENT_TWO_CLICKS

class SafeClickListener(private val onSafeClick: (View) -> Unit) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < DELAY_PREVENT_TWO_CLICKS) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        view?.let {
            trackOnClickView(view)
            onSafeClick(it)
        }
    }

    private fun trackOnClickView(view: View) {
        val resourceEntryName = try {
            view.resources.getResourceEntryName(view.id)
        } catch (t: Throwable) {
            when (view) {
                is Button -> view.text
                is TextView -> view.text
                else -> throw IllegalArgumentException("View is not supported, consider adding it.")
            }
        }
        val message = StringBuilder("click_view: $resourceEntryName")
        Log.d("Click", message.toString())
    }
}
