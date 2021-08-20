package com.example.main


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.INotificationSideChannel
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.everysearch1.R
import com.example.everysearch1.searchResult
import kotlinx.android.synthetic.main.searchresultitemfix.view.*


class SearchAdapter(val context: Context,private var items: ArrayList<Search>): RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    companion object{
        var callNum:String="0"
    }


    override fun getItemCount() = items.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            SearchAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.searchresultitemfix, parent, false)
        return SearchAdapter.ViewHolder(inflatedView)
    }

    fun setItems(list: ArrayList<Search>) {
        this.items=list
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bind(listener: View.OnClickListener, item: Search) {
            view.agency.text=item.agency
            view.department.text=item.department
            view.name.text=item.name
            view.task.text=item.task
            view.number.text=item.number
            callNum=item.number
            view.setOnClickListener(listener)

        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v:View= holder.itemView
        var position2=holder.adapterPosition
        holder.setIsRecyclable(false)
        val item = items.get(position2)
        val listener = View.OnClickListener {it ->
            val callIntent= Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+callNum))
            callIntent.putExtra(callNum, callNum)
            context.startActivity(callIntent)
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }

    }

}