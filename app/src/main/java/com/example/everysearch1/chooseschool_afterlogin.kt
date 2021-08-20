package com.example.everysearch1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_chooseschool_afterlogin.*
import kotlinx.android.synthetic.main.activity_chooseschool_afterlogin.autoComplete
import org.json.JSONObject

class chooseschool_afterlogin : AppCompatActivity() {
    companion object{
        val schoolname:String="대학교"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chooseschool_afterlogin)

        val ttb = AnimationUtils.loadAnimation(this, R.anim.anim_ttd)
        val alphacontroller = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_up)
        val ttbchange = AnimationUtils.loadAnimation(this, R.anim.anim_ttd_change)
        val chooseschool = findViewById(R.id.textView) as TextView
        val findschool = findViewById(R.id.autoComplete) as AutoCompleteTextView

        chooseschool.startAnimation(alphacontroller)
        findschool.startAnimation(ttb)
        val schlNmArray: ArrayList<String> = ArrayList()

        val assetManager=resources.assets
        val inputStream=assetManager.open("SchlNm.json")
        val jsonString=inputStream.bufferedReader().use{
            it.readText()
        }
        val jObject= JSONObject(jsonString)
        val jArray=jObject.getJSONArray("2018")

        for(i in 0 until  jArray.length()){
            val name:String=jArray.getJSONObject(i).getString("학교명")
            schlNmArray.add(name)
        }
        var schlKrnNm : String =autoComplete.text.toString()
        val textView: AutoCompleteTextView = findViewById(R.id.autoComplete)
        val adapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_dropdown_item_1line, schlNmArray as List<Any?>
        )
        textView.setAdapter(adapter)//autocreatetextview설정
        //오류...
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener { parent: AdapterView<*>,
                                                                             view: View?,
                                                                             position: Int,
                                                                             id: Long ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val nextIntent = Intent(this, mainsearch()::class.java)
            SCHNAME = autoComplete.text.toString()
            startActivity(nextIntent)
        }
        button17.setOnClickListener{
            finish()
        }
    }
}