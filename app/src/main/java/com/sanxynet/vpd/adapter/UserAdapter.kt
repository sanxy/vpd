package com.sanxynet.vpd.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanxynet.vpd.R
import com.sanxynet.vpd.db.UserEntity


class UserAdapter(val listener: RowClickListener): RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    var items  = ArrayList<UserEntity>()

    fun setListData(data: ArrayList<UserEntity>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return MyViewHolder(inflater, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }


    class MyViewHolder(itemView: View, val listener: RowClickListener): RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val email: TextView
        val username: TextView

        fun bind(data: UserEntity) {
            name.text = "Name: "+data.name

            email.text = "Email: "+data.email

            username.text = "Username: "+data.username

        }

        init {
            // get the reference of item view's
            name = itemView.findViewById(R.id.tv_name)
            email = itemView.findViewById(R.id.tv_email)
            username = itemView.findViewById(R.id.tv_username)

        }
    }


    interface RowClickListener{
        fun onItemClickListener(user: UserEntity)
    }
}