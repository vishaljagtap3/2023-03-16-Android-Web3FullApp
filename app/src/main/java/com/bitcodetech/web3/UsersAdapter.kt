package com.bitcodetech.web3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.web3.databinding.UserViewBinding
import com.squareup.picasso.Picasso

class UsersAdapter(
    private val users : ArrayList<User>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding : UserViewBinding

        init {
            binding = UserViewBinding.bind(view)
        }
    }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        /*holder.binding.txtUserEmail.text = user.email
        holder.binding.txtUserName.text = "${user.firstName} ${user.lastName}"*/

        holder.binding.user = user

        Picasso.get()
            .load(user.avatar)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.imgUser)
    }
}