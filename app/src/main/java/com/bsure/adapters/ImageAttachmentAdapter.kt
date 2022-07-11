package com.bsure.adapters
import android.content.Context
import android.content.Intent
import android.text.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bsure.R
import com.bsure.Utils
import com.bsure.dialog.ImageViewDialog
import com.bsure.models.AssetData
import com.bsure.models.CategoryDetailsBean
import java.util.*


class   ImageAttachmentAdapter(val context: Context, val mData: ArrayList<CategoryDetailsBean>,private val uploadInterFace: UploadInterFace) : RecyclerView.Adapter<ImageAttachmentAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = inflater.inflate(R.layout.itemview_atachment, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bean:CategoryDetailsBean=mData[position]
        Log.d("srini","onBindViewHolder"+bean.subCategoryName)

        Log.d("srini","onBindViewHolder"+bean.parameterValue)

        holder.btn_attachFile.setOnClickListener(){
            uploadInterFace.upload(bean,position)
        }
        if(!TextUtils.isEmpty(bean.parameterValue)){
            holder.preview.visibility=View.VISIBLE
            holder.preview.setOnClickListener(){
                val dailog = ImageViewDialog(context, bean.parameterValue)
                dailog.show()
            }
        }
        else{
            holder.preview.visibility=View.GONE
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
        var btn_attachFile: Button = itemView.findViewById(R.id.btn_attachFile)
        var preview: TextView =itemView.findViewById(R.id.preview)

    }

    fun setImageUrl(url:String){
        mData[0].parameterValue=url
        notifyDataSetChanged()
    }



}
interface UploadInterFace{
    fun upload(bean:CategoryDetailsBean,position:Int);
}