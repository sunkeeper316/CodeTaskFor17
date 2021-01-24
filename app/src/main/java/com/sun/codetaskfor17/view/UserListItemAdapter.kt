package com.sun.codetaskfor17.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sun.codetaskfor17.R
import com.sun.codetaskfor17.model.UserData


class UserListItemAdapter(val activity: Activity)  : RecyclerView.Adapter<UserListItemAdapter.UserItemViewHolder>() {

    private var userdataList : MutableList<UserData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_user, parent, false))

    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val userdata = userdataList.get(position)
        holder.tv_name.text = userdata.login
        Glide.with(activity)
                .load(userdata.avatarUrl)
                .into(holder.iv_user);
    }

    override fun getItemCount(): Int {
        return userdataList.size
    }

    fun setUserList(userdataList : MutableList<UserData>){
        this.userdataList = userdataList
        notifyDataSetChanged()
    }
    fun addUserList(userdataList : MutableList<UserData>){
        this.userdataList.addAll(userdataList)
        this.userdataList.forEach {
            
        }
        notifyDataSetChanged()
    }
    fun removeAll(){
        this.userdataList.clear()
        notifyDataSetChanged()
    }
    inner class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        val iv_user = itemView.findViewById<ImageView>(R.id.iv_user)
    }
}