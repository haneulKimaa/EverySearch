package com.example.everysearch1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.main.Search
import com.example.main.SearchAdapter
import com.example.main.SearchAdapter_org
import kotlinx.android.synthetic.main.activity_bookmark.*
import kotlinx.android.synthetic.main.activity_bookmark.autoCompleteTextView2
import kotlinx.android.synthetic.main.activity_bookmark.button2
import kotlinx.android.synthetic.main.activity_bookmark.editText
import kotlinx.android.synthetic.main.activity_search_result.*
import org.json.JSONObject


class searchResult_org : AppCompatActivity() {
    private lateinit  var adapter: SearchAdapter_org

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result_org)

        val list = ArrayList<Search>()
        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager
        adapter = SearchAdapter_org(this,list)
        recyclerView.adapter = adapter

        val intent = intent ?: return
        var txtName = intent.getStringExtra(orgSearch.keyword)


        editText.text=txtName



        val assetManager = resources.assets
        val inputStream = assetManager.open("Find.json")
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("Key")



        fun addArray( editText:String){
            if(list.isNotEmpty()){
                list.clear()
            }
            for (i in 0 until jArray.length()) {

                if (jArray.getJSONObject(i).getString("기관명").contains(editText)) {
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )


                }
                else if(jArray.getJSONObject(i).getString("부서").contains(editText)){
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )
                }
                else if(jArray.getJSONObject(i).getString("직급").contains(editText)){
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )
                }
                else if(jArray.getJSONObject(i).getString("이름").contains(editText)){
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )
                }
                else if(jArray.getJSONObject(i).getString("업무안내").contains(editText)){
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )
                }
                else if(jArray.getJSONObject(i).getString("번호").contains(editText)){
                    list.add(
                        Search(
                            jArray.getJSONObject(i).getString("기관명"),
                            jArray.getJSONObject(i).getString("부서"),
                            jArray.getJSONObject(i).getString("직급"),
                            jArray.getJSONObject(i).getString("이름"),
                            jArray.getJSONObject(i).getString("업무안내"),
                            jArray.getJSONObject(i).getString("번호"),
                            jArray.getJSONObject(i).getString("즐겨찾기")
                        )
                    )
                }


            }
        }
        addArray(txtName)
        autoCompleteTextView2.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                list.clear()
                editText.text=autoCompleteTextView2.text.toString()//try
                adapter.notifyDataSetChanged()
                addArray(autoCompleteTextView2.text.toString())

                return@OnKeyListener true
            }
            else
                false
        })


        button2.setOnClickListener{
            finish()
        }
    }
}
