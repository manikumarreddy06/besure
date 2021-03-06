package com.bsure
import android.content.Context
import android.text.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.bsure.models.AssetData
import com.bsure.models.CategoryDetailsBean
import java.util.*


class   TextBoxAdapter(val context: Context, val mData: ArrayList<CategoryDetailsBean>) : RecyclerView.Adapter<TextBoxAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = inflater.inflate(R.layout.itemview_textbox, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bean:CategoryDetailsBean=mData[position]
        holder.tvTitle.text = bean.subCategoryName
        val type = bean.dataType
        holder.text_input_layout.hint=bean.subCategoryName
        holder.verify_text_input_layout.hint="verify "+bean.subCategoryName








        holder.etInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.length > 0) {
                        mData[position].parameterValue = s.toString()
                    } else {
                        mData[position].parameterValue = ""
                    }
                }
        })

        if(bean.dataType.equals("VerifyText")){
            holder.verify_etInput.visibility=View.VISIBLE
            holder.verify_text_input_layout.visibility=View.VISIBLE

            holder.verify_etInput.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    var temptext = s.toString()
                    if (!TextUtils.isEmpty(mData[position].parameterValue) && mData[position].parameterValue.isNotEmpty()) {
                        if (temptext.equals(mData[position].parameterValue)) {
                            holder.errorMsg.visibility = View.GONE
                        } else {
                            holder.errorMsg.visibility = View.VISIBLE
                        }
                    } else {
                        holder.errorMsg.visibility = View.GONE
                    }

                }
            })
        }
        else{

            holder.verify_etInput.visibility=View.GONE
            holder.verify_text_input_layout.visibility=View.GONE
            holder.errorMsg.visibility=View.GONE
        }



    }

    fun getData(): List<AssetData> {
        val finalList = ArrayList<AssetData>()

        for (item in mData) {
            val single = AssetData()
            single.id = item.subCategoryId
            single.value=item.parameterValue
            finalList.add(single)

        }
        return finalList

    }

    fun validate():Boolean{
        for (item in mData) {
            if (item.madantory!=null  && item.madantory.equals("true",true) && item.parameterValue.isNullOrEmpty()){
                Utils.toast("Please enter value for "+item.subCategoryName,context)
                return false
            }
        }

        return true
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var etInput: EditText = itemView.findViewById(R.id.etInput)
        var text_input_layout: TextInputLayout =itemView.findViewById(R.id.text_input_layout)


        var verify_etInput: EditText = itemView.findViewById(R.id.verify_etInput)
        var verify_text_input_layout: TextInputLayout =itemView.findViewById(R.id.verify_text_input_layout)
        var errorMsg: TextView = itemView.findViewById(R.id.errorMsg)


    }

}