package com.bitcodetech.web3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bitcodetech.web3.databinding.UsersFragmentBinding
import com.google.gson.Gson

class UsersFragment : Fragment() {

    private lateinit var binding : UsersFragmentBinding
    private lateinit var usersAdapter: UsersAdapter
    private val users : ArrayList<User> = ArrayList<User>()
    private lateinit var requestQueue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsersFragmentBinding.inflate(layoutInflater)

        initRecyclerViewAndAdapter()
        initRequestQueue()
        getUsers(1)

        return binding.root
    }

    private fun initRequestQueue() {
        requestQueue = Volley.newRequestQueue(requireContext())
    }

    private fun getUsers(pageNumber : Int) {
        val req = JsonObjectRequest(
            Method.GET,
            "https://reqres.in/api/users?page=$pageNumber",
            null,
            { jsonObject ->

                Log.e("tag", jsonObject.toString())

                val gson = Gson()

                val usersArray = gson.fromJson<Array<User>>(
                    jsonObject.getJSONArray("data").toString(),
                    Array<User>::class.java
                )

                users.addAll(usersArray)
                usersAdapter.notifyDataSetChanged()
            },
            { error ->
                Log.e("tag", "error")
            }
        )
        requestQueue.add(req)
    }

    private fun initRecyclerViewAndAdapter() {
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        usersAdapter = UsersAdapter(users)
        binding.recyclerUsers.adapter = usersAdapter
    }
}