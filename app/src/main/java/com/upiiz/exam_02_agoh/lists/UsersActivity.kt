package com.upiiz.exam_02_agoh

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upiiz.exam_02_agoh.adapters.UserAdapter
import com.upiiz.exam_02_agoh.models.User
import com.upiiz.exam_02_agoh.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchUsers()
    }

    private fun fetchUsers() {
        RetrofitClient.instance.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body()!!
                    userAdapter = UserAdapter(users) { user ->
                        val intent = Intent(this@UsersActivity, PostsActivity::class.java)
                        intent.putExtra("USER_ID", user.id)
                        startActivity(intent)
                    }
                    recyclerView.adapter = userAdapter
                } else {
                    showToast("Error al obtener usuarios")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                showToast("Error de conexión")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
