package com.bsure

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bsure.models.BaseResponse
import com.bsure.models.NomineeRequest
import com.bsure.models.RelationResponseBean
import com.bsure.models.RelationsResponse
import com.bsure.retrofitUtil.RetrofitClient
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_nominees.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class nomineesAdditionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var data:RelationResponseBean?=null
    lateinit var relationList:List<RelationsResponse>
    var selectedPosition:Int=-1
    val PDF: Int = 0
    lateinit var uri: Uri
    lateinit var mStorage: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nominees)

        mStorage = FirebaseStorage.getInstance().getReference("Nominee ID Proofs")

        btn_attachFile.setOnClickListener (View.OnClickListener {
            view:View?-> val intent = Intent()
            intent.setType("pdf/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"),PDF)

        })

        getNomineeRelations()
        btn_nomineesavedata.setOnClickListener(){
            saveData()
            upload()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val uriTxt = findViewById<View>(R.id.uriTxt) as TextView
        if(resultCode== RESULT_OK){
            if(requestCode==PDF){
                uri= data!!.data!!
                uriTxt.text = uri.toString()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload(){
        var s: String = nomineePhoneNo.text.toString()
        var mReference = mStorage.child(s)
//        var mReference = mStorage.child(uri.lastPathSegment!!)
        try{
            mReference.putFile(uri).addOnSuccessListener{
                    taskSnapshot: UploadTask.TaskSnapshot->var url= taskSnapshot.getStorage().getDownloadUrl();
                    // url has download referance
//                val dwnTxt = findViewById<View>(R.id.dwnTxt) as TextView
//                dwnTxt.text = url.toString()
                Toast.makeText(this,"Successfully Uploaded",Toast.LENGTH_LONG).show()
                val i = Intent(this@nomineesAdditionActivity, MainActivity::class.java)
                startActivity(i)
            }
        }catch (e:Exception){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }





    private fun saveData() {
        var nomineeName=et_nomineename.text.toString();
        var nomineeMobileNumber=nomineePhoneNo.text.toString();

        if(TextUtils.isEmpty(nomineeName)){
            Utils.toast("nominee name can`t be empty",this)
        }
        else if(TextUtils.isEmpty(nomineeMobileNumber)){
            Utils.toast("nominee mobile can`t be empty",this)
        }
        else if(selectedPosition==-1){
            Utils.toast("nominee name can`t be empty",this)
        }
        else{
            var relationId=relationList.get(selectedPosition).relationId

            var request:NomineeRequest= NomineeRequest()

            request.userId = PreferenceManager.instance(this)[PreferenceManager.USER_ID, null]
            request.userNomineeMobileNumber=nomineeMobileNumber
            request.userNomineeName=nomineeName
            request.userNomineeRelationId= relationId.toString()

            val call = RetrofitClient.getInstance().apiinterface().addNomineeDetails(request)
            call.enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    if(data!!.isvalid) {
                        Utils.toast("nominee added successfully",this@nomineesAdditionActivity)
                        finish()
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Toast.makeText(this@nomineesAdditionActivity, "failure", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun updateRelationS() {
        relationList=data!!.relationsResponse
        val outlet: MutableList<String> = ArrayList()
        outlet.add("Select")
        if (relationList !=null && relationList.isNotEmpty()) {
            for (item in relationList)
                outlet.add(item.relationName)

            val adapter = ArrayAdapter<String>(
                this@nomineesAdditionActivity,
                android.R.layout.simple_spinner_dropdown_item,
                outlet
            )
            spStatus.adapter = adapter
            Utils.Companion.setHeightForSpinner(spStatus, outlet)
            spStatus.onItemSelectedListener = this
        } else {
            Utils.toast("relations list is empty", this)
        }
    }

    private fun getNomineeRelations() {
        val call = RetrofitClient.getInstance().apiinterface().relationList
        call.enqueue(object : Callback<RelationResponseBean> {
            override fun onResponse(
                call: Call<RelationResponseBean>,
                response: Response<RelationResponseBean>
            ) {
                data =response.body()
                if(data!!.isvalid) {
                    updateRelationS()
                }
            }

            override fun onFailure(call: Call<RelationResponseBean>, t: Throwable) {
                Toast.makeText(this@nomineesAdditionActivity, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.spStatus -> {

                if (position != 0) selectedPosition=position-1
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}