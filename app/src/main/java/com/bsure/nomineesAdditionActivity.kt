package com.bsure

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bsure.dialog.ImageViewDialog
import com.bsure.models.*
import com.bsure.retrofitUtil.RetrofitClient
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_nominees.*
import kotlinx.android.synthetic.main.activity_nominees.spStatus
import kotlinx.android.synthetic.main.itemview_dropdown.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class nomineesAdditionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener ,CustomSpinner.OnSpinnerEventsListener{
    var data:RelationResponseBean?=null
    lateinit var relationList:List<RelationsResponse>
    var selectedPosition:Int=-1
    val PDF: Int = 0
    lateinit var uri: Uri
    lateinit var mStorage: StorageReference

    var attachmentUrl: String?=null
    lateinit var spinner: CustomSpinner;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nominees)
        spinner=findViewById(R.id.spStatus)


        mStorage = FirebaseStorage.getInstance().getReference("Nominee ID Proofs")

        btn_attachFile.setOnClickListener (View.OnClickListener {
            view:View?-> val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select PDF"),PDF)

        })


        getNomineeRelations()
        btn_nomineesavedata.setOnClickListener(){
            saveData()

        }
        preview.setOnClickListener(){
            val dailog = ImageViewDialog(nomineesAdditionActivity@this, attachmentUrl)
            dailog.show()
        }

        etNomAge.addTextChangedListener(object : TextWatcher {
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
                    var temp=s.toString().toInt()
                    if(temp>18){
                        hideGaurdianDetails()
                    }
                    else{
                        showGaurdianDetails()
                    }
                }
                else{

                }


            }
        })


    }

    private fun showGaurdianDetails() {
        tlGuardianName.visibility=View.VISIBLE
        tlGuardianPhNo.visibility=View.VISIBLE
    }
    private fun hideGaurdianDetails() {
        tlGuardianName.visibility=View.GONE
        tlGuardianPhNo.visibility=View.GONE
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
        Utils.showDialog(this@nomineesAdditionActivity,"uploading")
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
                    preview.visibility=View.VISIBLE
                    //Toast.makeText(this,"Successfully Uploaded"+attachmentUrl,Toast.LENGTH_LONG).show()
                } else {
                    Utils.hideDialog()
                    preview.visibility=View.GONE
                    Toast.makeText(this,"Upload failure",Toast.LENGTH_LONG).show()
                }
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
            Utils.toast("nominee relation can`t be empty",this)
        }
        else{
            var relationId=relationList.get(selectedPosition).relationId

            var request:NomineeRequest= NomineeRequest()

            request.userId = PreferenceManager.instance(this)[PreferenceManager.USER_ID, null]
            request.userNomineeMobileNumber=nomineeMobileNumber
            request.userNomineeName=nomineeName
            request.userNomineeRelationId= relationId.toString()
            request.userNomineeAttachment=attachmentUrl

            request.userGardianName=etGuardianName.text.toString()
            request.userGardianMobileNumber=etGuardianPhNo.text.toString()
            request.userNomineeAge=etNomAge.text.toString()

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
            adapter.setDropDownViewResource(R.layout.spinner_list)
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

    override fun onPopupWindowOpened(spinner: Spinner?) {
        spinner?.setBackground(resources.getDrawable(R.drawable.bg_spinner_relations_up))
    }

    override fun onPopupWindowClosed(spinner: Spinner?) {
        spinner?.setBackground(resources.getDrawable(R.drawable.bg_spinner_relations))
    }


}