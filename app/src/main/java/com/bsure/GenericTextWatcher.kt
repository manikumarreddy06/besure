package com.bsure

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText

class GenericTextWatcher constructor(
    private val currentView: EditText,
    private val nextView: EditText?,
    private val mContext: Context
) :
    TextWatcher {

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        if (nextView != null && text.length == 1) {
            nextView.requestFocus()
        } else if (text.isEmpty()) {
            currentView.setCompoundDrawablesWithIntrinsicBounds(
                mContext.resources.getDrawable(R.drawable.otpwithboxes),
                null,
                null,
                null
            )
        }
        if (text.length > 1) {
            currentView.setText(text[text.length - 1].toString())
            currentView.setSelection(1)
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
        // TODO Auto-generated method stub
        currentView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
        // TODO Auto-generated method stub
    }

}

class GenericKeyEvent(
    private val currentView: EditText,
    private val previousView: EditText?
) :
    View.OnKeyListener {
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL &&  currentView.id != R.id.otp_edit_box1 && currentView.text.toString()
                .isEmpty()
        ) {
            previousView!!.text = null
            previousView.requestFocus()
            return true
        }
        return false
    }
}
