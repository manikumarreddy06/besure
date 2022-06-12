package com.bsure


import android.app.ProgressDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow

class Utils {

    companion object {

        private var pDialog: ProgressDialog? = null
        private var toast: Toast? = null

        fun showDialog(context: AppCompatActivity, msg: String, title: String) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog?.setMessage(msg)
                pDialog?.setTitle(title)
                pDialog?.setCancelable(false)
                if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                    pDialog?.show()
                }
            }
        }

        fun showDialog(context: Context, msg: String, title: String) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog?.setMessage(msg)
                pDialog?.setTitle(title)
                pDialog?.setCancelable(false)
                if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                    pDialog?.show()
                }
            }
        }

        fun showDialog(context: Context, msg: String) {
            if (pDialog == null) {
                pDialog = ProgressDialog(context)
                pDialog?.setMessage(msg)
                pDialog?.setTitle("")
                pDialog?.setCancelable(false)
                if (pDialog?.isShowing == false && !(context as AppCompatActivity).isFinishing) {
                    pDialog?.show()
                }
            }
        }

        fun hideDialog() {
            if (pDialog != null) {
                if (pDialog?.isShowing == true) {
                    pDialog?.dismiss()
                    pDialog = null
                }
            }
        }


        fun toast(message: String, context: Context?) {
            val v = LayoutInflater.from(context).inflate(R.layout.layout_custom_toastview, null)
            val textView = v.findViewById<TextView>(R.id.tvToast)
            textView.text = message
            toast = if (toast == null) {
                Toast(context)
            } else {
                toast?.cancel()
                Toast(context)
            }
            toast?.view = v
            toast?.duration = Toast.LENGTH_LONG
            toast?.show()
        }

        fun setHeightForSpinner(spDoctor: Spinner, outlet: MutableList<String>){

            if(outlet.size>6) {
                try {
                    val popup = Spinner::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true

                    // Get private mPopup member variable and try cast to ListPopupWindow
                    val popupWindow = popup.get(spDoctor) as ListPopupWindow

                    // Set popupWindow height to 500px
                    popupWindow.height = 800
                } catch (e: NoClassDefFoundError) {
                    // silently fail...
                } catch (e: ClassCastException) {
                } catch (e: NoSuchFieldException) {
                } catch (e: IllegalAccessException) {
                }
            }

        }

    }
}