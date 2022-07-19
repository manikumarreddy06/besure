package com.bsure

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bsure.adapters.DropDownAdapter
import com.bsure.adapters.ImageAttachmentAdapter
import com.bsure.adapters.UploadInterFace
import com.bsure.models.*
import com.bsure.retrofitUtil.RetrofitClient
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_dynamic.*
import kotlinx.android.synthetic.main.activity_nominees.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class DynamicFormActivity : AppCompatActivity(){

    var assetId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
        assetId=intent.getStringExtra("asset_id")
        getAssestsConfiguration()

        btnSave.setOnClickListener(){
            validateForm()
        }
    }


    private fun getAssestsConfiguration() {
        val call = RetrofitClient.getInstance().apiinterface().getAssestsConfigurations(assetId)
        call.enqueue(object : Callback<AssetsConfigResponse> {
            override fun onResponse(call: Call<AssetsConfigResponse>, response: Response<AssetsConfigResponse>) {

                var bean: AssetsConfigResponse? =response.body()
                if(bean!=null && bean.isvalid) {

                    prepareListView(bean?.categoryDetailsBean)
                }
                else{
                    Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AssetsConfigResponse>, t: Throwable) {
                Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private var textBoxAdapterList = ArrayList<TextBoxAdapter>()
    private var dropDownAdapterList = ArrayList<DropDownAdapter>()

    private var ImageAttachmentAdapterList = ArrayList<ImageAttachmentAdapter>()


    private fun prepareListView(data: List<CategoryDetailsBean> ) {
        //progress.visibility = View.GONE

        dataContainer.removeAllViews()
        textBoxAdapterList.clear()


        for (item in data) {
            val layoutInflater: LayoutInflater = LayoutInflater.from(this)
            val view: View = layoutInflater.inflate(
            R.layout.itemview_textlist_container, dataContainer, false)

            val label = view.findViewById<TextView>(R.id.heading)
            val rvTextBox = view.findViewById<RecyclerView>(R.id.rvTextBox)
            label.text = "sample"

            val textBoxList = ArrayList<CategoryDetailsBean>()
            val dropdownList = ArrayList<CategoryDetailsBean>()

            val  attachmentList = ArrayList<CategoryDetailsBean>()
            Log.d("srini","sri ni"+item.dataType)

            when (item.dataType) {
                "dropdown" -> {
                    dropdownList.add(item)
                }
                "TEXT" -> {
                    textBoxList.add(item)
                }
                "textarea" -> {
                    textBoxList.add(item)
                }
                "VerifyText" -> {
                    textBoxList.add(item)
                }
                "IMAGE" -> {
                    attachmentList.add(item)
                }



            }


            if (dropdownList.isNotEmpty()) {
                val adapter = DropDownAdapter(this, dropdownList)
                rvTextBox.adapter = adapter
                rvTextBox.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //rvTextBox.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                dropDownAdapterList.add(adapter)
                rvTextBox.visibility = View.VISIBLE
            }

            if (textBoxList.isNotEmpty()) {
                val adapter = TextBoxAdapter(this, textBoxList)
                rvTextBox.adapter = adapter
                rvTextBox.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //rvTextBox.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                textBoxAdapterList.add(adapter)
                rvTextBox.visibility = View.VISIBLE
            }

            if (attachmentList.isNotEmpty()) {
                val adapter = ImageAttachmentAdapter(this, attachmentList,object : UploadInterFace{

                    override fun upload(bean:CategoryDetailsBean,position:Int) {
                        imageUpload()
                    }
                })
                rvTextBox.adapter = adapter
                rvTextBox.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //rvTextBox.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                ImageAttachmentAdapterList.add(adapter)
                rvTextBox.visibility = View.VISIBLE
            }






            dataContainer.addView(view)
        }


    }

    private fun validateForm() {
        var finalList = ArrayList<AssetData>()
        var textBoxError = false
        var dropdownError = false
        var imageUploadError = false
        if (textBoxAdapterList.size == 0) textBoxError = true
        if (dropDownAdapterList.size == 0) dropdownError = true
        if (ImageAttachmentAdapterList.size == 0) imageUploadError = true
        for (adapter in dropDownAdapterList) {
            dropdownError = adapter.validate()
            if (dropdownError)
                finalList.addAll(adapter.getData())
            else break
        }
        for (adapter in textBoxAdapterList) {
                textBoxError = adapter.validate()
                if (textBoxError)
                    finalList.addAll(adapter.getData())
                else break
        }

        for (adapter in ImageAttachmentAdapterList) {
            imageUploadError = adapter.validate()
            if (imageUploadError)
                finalList.addAll(adapter.getData())
            else break
        }
        if (textBoxError && dropdownError && imageUploadError)  {
            updatedAssetData(finalList)
        }
        else{
            //Utils.toast("Mandatory fields are missed",this@DynamicFormActivity)
        }

    }

    private fun updatedAssetData(finalList: ArrayList<AssetData>) {

        Utils.showDialog(this@DynamicFormActivity,"loading")
        var data=SaveAssetRequest()
        data.assetId=assetId
        data.userId=PreferenceManager.instance(this@DynamicFormActivity).get(PreferenceManager.USER_ID,null)
        data.assetsList=finalList


        val call = RetrofitClient.getInstance().apiinterface().saveAssetData(data)
        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Utils.hideDialog()
                var data: BaseResponse? =response.body()
                if(data!!.isvalid){
                    Toast.makeText(this@DynamicFormActivity, "data saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()
                Utils.hideDialog()
            }
        })


    }


    //Image upload

    val PDF: Int = 0
    lateinit var uri: Uri
    lateinit var mStorage: StorageReference
    lateinit var attachmentUrl: String

    fun imageUpload(){

        mStorage = FirebaseStorage.getInstance().getReference("Nominee ID Proofs")
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select PDF"),PDF)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK){
            if(requestCode==PDF){
                uri= data!!.data!!
                //uriTxt.text = uri.toString()
                upload()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload(){
        Utils.showDialog(this@DynamicFormActivity,"uploading")
        var s: String = uri.toString()
        var mReference = mStorage.child(s)
        try{
            var uploadTask = mReference.putFile(uri)
            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                mReference.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Utils.hideDialog()
                    val downloadUri = task.result
                    attachmentUrl=task.result.toString()
                    updateImageAdapter(attachmentUrl)
                    //Toast.makeText(this,"Successfully Uploaded"+attachmentUrl,Toast.LENGTH_LONG).show()
                } else {
                    Utils.hideDialog()
                    Toast.makeText(this,"Upload failure",Toast.LENGTH_LONG).show()
                }
            }
        }catch (e:Exception){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }


    }

    private fun updateImageAdapter(attachmentUrl:String) {

        ImageAttachmentAdapterList[0].setImageUrl(attachmentUrl)
    }


}