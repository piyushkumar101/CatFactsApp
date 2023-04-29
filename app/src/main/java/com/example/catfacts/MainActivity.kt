package com.example.catfacts

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.catfacts.ui.theme.CatfactsTheme
import com.example.catfacts.ui.theme.fact
import org.json.JSONObject.NULL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    lateinit var textView:TextView
    lateinit var refreshButton: Button
    var count=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getfact(0);

        textView=findViewById<TextView>(R.id.textview)
        refreshButton=findViewById<Button>(R.id.refreshButton)
        refreshButton.setOnClickListener {
            count++;
            if(count <= 8) {
                getfact(count)
            }
            else if(count >8){
                getfact1(count)
            }
        }
    }

    fun getfact(count :Int) {
        var fact = service.serviceInstance.getfacts(1)
        fact.enqueue(object : Callback<fact> {
            override fun onResponse(call: Call<fact>, response: Response<fact>) {
                var fact = response.body()

                    if(fact!=null) {
                        textView.text =fact.data[count % fact.data.size].fact.toString()
                    }


            }

            override fun onFailure(call: Call<fact>, t: Throwable) {
                Log.d("piyush", "Error in fetching facts", t)
            }
        })
    }
    fun getfact1(count :Int) {
        var fact = service.serviceInstance.getfacts(2)
        fact.enqueue(object : Callback<fact> {
            override fun onResponse(call: Call<fact>, response: Response<fact>) {
                var fact = response.body()

                if (fact != null) {
                    textView.text =fact.data[count % fact.data.size].fact.toString()
                }


            }

            override fun onFailure(call: Call<fact>, t: Throwable) {
                Log.d("piyush", "Error in fetching facts", t)
            }
        })
    }


}