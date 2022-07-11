package com.bsure.adapters
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bsure.Utils
import com.bsure.models.CategoryDetailsBean
import java.util.*

import com.bsure.R
import com.bsure.models.AssetData


class DropDownAdapter(val context: Context, val mData: ArrayList<CategoryDetailsBean>) :RecyclerView.Adapter<DropDownAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = inflater.inflate(R.layout.itemview_dropdown, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bean:CategoryDetailsBean=mData[position]
        holder.tvTitle.text = bean.subCategoryName



        var outlet: MutableList<String> = ArrayList()
        outlet.add("select")
        if(!TextUtils.isEmpty(bean.values)) {
            outlet= bean.values!!.split(",").toTypedArray().toMutableList()
        }



        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,outlet )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.spStatus.adapter = adapter


        holder.spStatus.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent:AdapterView<*>, view: View, p: Int, id: Long){
                // Display the selected item text on text view
                if(p!=0) {

                    if(parent.getItemAtPosition(p).toString().equals("Others",false)){
                        holder.llOthers.visibility=View.VISIBLE
                    }
                    else{
                        holder.llOthers.visibility=View.GONE
                    }
                    bean.parameterValue = parent.getItemAtPosition(p).toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        holder.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length > 0) {
                    bean.otherTextField= s.toString()
                } else {
                    bean.otherTextField = ""
                }
            }
        })


    }

    fun getData(): List<AssetData> {
        val finalList = ArrayList<AssetData>()

        for (item in mData) {
            val single = AssetData()
            single.id = item.subCategoryId
            single.value=item.parameterValue
            if(item.parameterValue.equals("Others", false))
            {
                single.value=item.otherTextField
            }
            else{
                single.value=item.parameterValue
            }
            finalList.add(single)

        }
        return finalList

    }

    fun validate():Boolean{
        for (item in mData) {
            if (item.madantory!=null  && item.madantory.equals("true",false)  && (item.parameterValue.isNullOrEmpty() || item.parameterValue.equals("select", false))) {
                Utils.toast("Please enter value for " + item.subCategoryName, context)
                return false
            }
            if (item.madantory!=null  && item.madantory.equals("true",false) && (item.parameterValue.isNullOrEmpty() || item.parameterValue.equals("Others", false)) && TextUtils.isEmpty(item.otherTextField)) {
                Utils.toast("Please enter value in others section " + item.subCategoryName, context)
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
        var spStatus: Spinner = itemView.findViewById(R.id.spStatus)
        var etInput: EditText = itemView.findViewById(R.id.etInput)
        var llOthers: LinearLayout = itemView.findViewById(R.id.containerOthers)


    }
}