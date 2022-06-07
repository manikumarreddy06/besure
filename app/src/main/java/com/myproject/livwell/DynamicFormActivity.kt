package com.myproject.livwell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproject.livwell.models.AssetsConfigResponse
import com.myproject.livwell.models.CategoryDetailsBean
import com.myproject.livwell.retrofitUtil.RetrofitClient
import kotlinx.android.synthetic.main.activity_dynamic.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

public class DynamicFormActivity : AppCompatActivity(){

    private var assetId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
        assetId=intent.getStringExtra("asset_id")
        getAssestsConfiguration()
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

}