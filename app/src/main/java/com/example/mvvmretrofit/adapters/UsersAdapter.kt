package com.example.mvvmretrofit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmretrofit.R
import com.example.mvvmretrofit.interfaces.GenericAdapterCallback
import com.example.mvvmretrofit.models.UsersDC


class UsersAdapter(
    var list: List<UsersDC.Data>,
    var context: Context,
    var genericCallback: GenericAdapterCallback
) :
    RecyclerView.Adapter<UsersAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StatusViewHolder {
        val v = LayoutInflater.from(context)
            .inflate(R.layout.users_child_item, parent, false)
        return StatusViewHolder(v)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        holder.user_name.setText("${list.get(position).first_name} ${list.get(position).last_name}")
        holder.user_email.setText(list.get(position).email)
        holder.index_no.setText( (position + 1).toString()  )

        Glide.with(context)
            .load(list.get(position).avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.user_avatar)

        holder.user_child_parent.setOnClickListener {
            genericCallback.getClickedObject(list.get(position) , position, "User")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = list.size

    class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user_avatar: ImageView = itemView.findViewById(R.id.user_avatar)
        val user_name: TextView = itemView.findViewById(R.id.user_name)
        val user_email: TextView = itemView.findViewById(R.id.user_email)
        val index_no: TextView = itemView.findViewById(R.id.index_no)
        val user_child_parent: ConstraintLayout = itemView.findViewById(R.id.user_child_parent)

    }
}
