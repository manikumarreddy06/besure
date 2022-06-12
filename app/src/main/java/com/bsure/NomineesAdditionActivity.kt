package com.bsure

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bsure.models.BaseResponse
import com.bsure.models.NomineeRequest
import com.bsure.models.RelationResponseBean
import com.bsure.models.RelationsResponse
import com.bsure.retrofitUtil.RetrofitClient
import kotlinx.android.synthetic.main.activity_nominees.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NomineesAdditionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var data:RelationResponseBean?=null
    lateinit var relationList:List<RelationsResponse>
    var selectedPosition:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nominees)


        getNomineeRelations()


        btn_nomineesavedata.setOnClickListener(){
            saveData()
        }
    }

    private fun saveData() {

        var nomineeName=et_nomineename.text.toString();
        var nomineeMobileNumber=nomineephone.text.toString();

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
                        Utils.toast("nominee added successfully",this@NomineesAdditionActivity)
                        finish()
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Toast.makeText(this@NomineesAdditionActivity, "failure", Toast.LENGTH_SHORT).show()
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
                this@NomineesAdditionActivity,
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
                Toast.makeText(this@NomineesAdditionActivity, "failure", Toast.LENGTH_SHORT).show()
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