package com.example.jsonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvdate: TextView
    lateinit var spinner: Spinner
    lateinit var edvlaue: EditText
    lateinit var buconvert: Button
    lateinit var tvresult: TextView
    var Currency: Datum? = null
    var input=""
    var n=0.0
    var result: Double = 0.0
    var selected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvdate = findViewById(R.id.tvdate)
        tvresult = findViewById(R.id.tvresult)
        spinner = findViewById(R.id.spinner)
        edvlaue = findViewById(R.id.edvalue)
        buconvert = findViewById(R.id.buconvert)

        val values =
            arrayListOf("ada", "aed", "afn", "all", "ang", "amd", "aoa", "ars", "aud", "awg")

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, values
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapter: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selected = position
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        }

        buconvert.setOnClickListener {

            input = edvlaue.text.toString()
            n=input.toDouble()

            getCurrency(onResult = {
                Currency = it
                tvdate.text = Currency?.date
                when (selected) {
                    0 -> display(calc(Currency?.eur?.ada?.toDouble(),n))
                    1 -> display(calc(Currency?.eur?.aed?.toDouble(),n))
                    2 -> display(calc(Currency?.eur?.afn?.toDouble(),n))
                    3 -> display(calc(Currency?.eur?.all?.toDouble(),n))
                    4 -> display(calc(Currency?.eur?.ang?.toDouble(),n))
                    5 -> display(calc(Currency?.eur?.amd?.toDouble(),n))
                    6 -> display(calc(Currency?.eur?.aoa?.toDouble(),n))
                    7 -> display(calc(Currency?.eur?.ars?.toDouble(),n))
                    8 -> display(calc(Currency?.eur?.aud?.toDouble(),n))
                    9 -> display(calc(Currency?.eur?.awg?.toDouble(),n))
                }
            })

        }
    }

    fun calc(c: Double?,n:Double):Double {
        if (c != null) {
            result =n * c
        }
        return result
    }

    fun display(calc:Double){
        tvresult.text="Result"+result
    }

    private fun getCurrency(onResult: (Datum?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)

        if (apiInterface != null) {
            apiInterface.getCurrency()?.enqueue(object : Callback<Datum?> {
                override fun onResponse(call: Call<Datum?>, response: Response<Datum?>) {
                    onResult(response.body())
                }

                override fun onFailure(call: Call<Datum?>, t: Throwable) {
                    onResult(null)
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}
