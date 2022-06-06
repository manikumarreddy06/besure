package com.myproject.livwell

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproject.livwell.models.*
import com.myproject.livwell.retrofitUtil.RetrofitClient
import kotlinx.android.synthetic.main.activity_dynamic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

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

                prepareListView(response.body()!!.categoryDetailsBean)
            }

            override fun onFailure(call: Call<AssetsConfigResponse>, t: Throwable) {
                Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private var textBoxAdapterList = ArrayList<TextBoxAdapter>()

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

            when (item.dataType) {

                "TEXT" -> {
                    textBoxList.add(item)
                }
                "textarea" -> {
                    textBoxList.add(item)
                }


            }



            if (textBoxList.isNotEmpty()) {
                val adapter = TextBoxAdapter(this, textBoxList)
                rvTextBox.adapter = adapter
                rvTextBox.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                //rvTextBox.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
                textBoxAdapterList.add(adapter)
                rvTextBox.visibility = View.VISIBLE
            }



            dataContainer.addView(view)
        }


    }

    private fun validateForm() {
        var finalList = ArrayList<AssetData>()
        var textBoxError = false
        if (textBoxAdapterList.size == 0) textBoxError = true
        for (adapter in textBoxAdapterList) {
                textBoxError = adapter.validate()
                if (textBoxError)
                    finalList.addAll(adapter.getData())
                else break
        }
        if (textBoxError)  {
            updatedAssetData(finalList)
        }

    }

    private fun updatedAssetData(finalList: ArrayList<AssetData>) {

        Utils.showDialog(this@DynamicFormActivity,"loading")
        var data=SaveAssetRequest()
        data.assetId=assetId
        data.userId="1"
        data.assetsList=finalList


        val call = RetrofitClient.getInstance().apiinterface().saveAssetData(data)
        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                Utils.hideDialog()
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Toast.makeText(this@DynamicFormActivity, "failure", Toast.LENGTH_SHORT).show()
                Utils.hideDialog()
            }
        })


    }

}