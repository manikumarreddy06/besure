package com.bsure

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsure.models.UserAsset
import com.bsure.models.UserAssetResponseBean
import com.bsure.retrofitUtil.RetrofitClient
import kotlinx.android.synthetic.main.activity_empt2.btn_add
import kotlinx.android.synthetic.main.activity_empt2.tvdontadd
import kotlinx.android.synthetic.main.activity_empty4.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssetListActivity : AppCompatActivity() {
    var assetId:String?=null
    var assetName:String?=null
    override fun onResume() {
        super.onResume()
        getAssestsData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty4)

        assetId=intent.getStringExtra("asset_id")
        assetName=intent.getStringExtra("asset_name")

        pageTitle.text=assetName
        addAccount.setOnClickListener(){
            val i = Intent(this@AssetListActivity, DynamicFormActivity::class.java)
            i.putExtra("asset_id", assetId)
            i.putExtra("asset_name", assetName)
            startActivity(i)
        }
        btn_add.setOnClickListener(View.OnClickListener {
            val i = Intent(this@AssetListActivity, DynamicFormActivity::class.java)
            i.putExtra("asset_id", assetId)
            i.putExtra("asset_name", assetName)
            startActivity(i)
        })

        tvdontadd.setOnClickListener(){
            finish();
        }
    }




    private fun getAssestsData() {
        var userAsset:UserAsset= UserAsset()
        userAsset.assetId=assetId
        userAsset.setUserId(PreferenceManager.instance(this).get(PreferenceManager.USER_ID,null)
        )
        val call = RetrofitClient.getInstance().apiinterface().getUserLevelAssetsByCategory(userAsset)
        call.enqueue(object : Callback<UserAssetResponseBean> {
            override fun onResponse(
                call: Call<UserAssetResponseBean>,
                response: Response<UserAssetResponseBean>
            ) {
                var data: UserAssetResponseBean? =response.body()
                if(data!!.isvalid) {
                    adapterupdate(data.userAssets)
                }
                else{
                    noDataVisible()
                }

            }

            override fun onFailure(call: Call<UserAssetResponseBean>, t: Throwable) {
                Toast.makeText(this@AssetListActivity, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun noDataVisible() {
        llNodata.visibility=View.VISIBLE
    }

    private fun adapterupdate(res: List<UserAsset>) {
        llNodata.visibility=View.GONE
        val adapter = AssetsAdapter(this, res)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }
}