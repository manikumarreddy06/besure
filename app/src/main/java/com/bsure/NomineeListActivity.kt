package com.bsure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.view.View
import com.bsure.models.NomineeListResponseBean
import com.bsure.retrofitUtil.RetrofitClient
import android.widget.Toast
import com.bsure.models.NomineeDetailsResponse
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_asset_categories.*
import kotlinx.android.synthetic.main.activity_nomineelist.*
import kotlinx.android.synthetic.main.activity_nomineelist.btn_add
import kotlinx.android.synthetic.main.activity_nomineelist.tvdontadd
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NomineeListActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()
        NomineeData()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nomineelist)
        nomineePageTitle.text="Nominee Details"
        val addNew = findViewById<TextView>(R.id.addAccount)
        addNew.setOnClickListener {
            val i = Intent(this@NomineeListActivity, nomineesAdditionActivity::class.java)
            startActivity(i)
        }

        btn_add.setOnClickListener(View.OnClickListener {
            val i = Intent(this@NomineeListActivity, nomineesAdditionActivity::class.java)

            startActivity(i)
        })

        tvdontadd.setOnClickListener(){
            finish();
        }
    }

    private fun NomineeData() {
        val userId = PreferenceManager.instance(this)[PreferenceManager.USER_ID, null]
        val call = RetrofitClient.getInstance().apiinterface().getNomineeList(userId)
        call.enqueue(object : Callback<NomineeListResponseBean?> {
            override fun onResponse(
                call: Call<NomineeListResponseBean?>,
                response: Response<NomineeListResponseBean?>
            ) {
                val nomineeListResponseBean = response.body()
                if (nomineeListResponseBean!!.isvalid && nomineeListResponseBean!!.nomineeDetailsResponse!=null) {
                    adapterupdate(nomineeListResponseBean!!.nomineeDetailsResponse)
                }
                else{
                    noDataVisible()
                }
            }

            override fun onFailure(call: Call<NomineeListResponseBean?>, t: Throwable) {
                Toast.makeText(this@NomineeListActivity, "failure", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun adapterupdate(categorysReponse: List<NomineeDetailsResponse>) {
        if(categorysReponse!=null && categorysReponse.size>0) {
            val recyclerView = findViewById<RecyclerView>(R.id.rv)
            val adapter = NomineesAdapter(this, categorysReponse)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }
        else{
            noDataVisible()
        }
    }

    private fun noDataVisible() {
        llNodata.visibility= View.VISIBLE
    }
}