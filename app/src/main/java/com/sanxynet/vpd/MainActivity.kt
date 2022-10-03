package com.sanxynet.vpd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.sanxynet.vpd.adapter.UserAdapter
import com.sanxynet.vpd.databinding.ActivityMainBinding
import com.sanxynet.vpd.databinding.ActivityUserDetailBinding
import com.sanxynet.vpd.db.UserEntity
import org.json.JSONArray
import org.json.JSONException
import timber.log.Timber
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), UserAdapter.RowClickListener {

    lateinit var viewModel: MainActivityViewModel
    lateinit var userAdapter: UserAdapter
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        binding!!.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            userAdapter = UserAdapter(this@MainActivity)
            binding!!.recyclerView.adapter = userAdapter

//            val divider = DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
//            addItemDecoration(divider)
        }

    binding!!.errorTextLastTransactions.text = "Loading user data"

        viewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        viewModel.getAllUsersObservers().observe(this, Observer {
            userAdapter.setListData(ArrayList(it))
            binding!!.errorTextLastTransactions.visibility = View.GONE
            binding!!.recyclerView.visibility = View.VISIBLE
            userAdapter.notifyDataSetChanged()
        })

        binding!!.addUser.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUserActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onItemClickListener(user: UserEntity) {
        Timber.i("User checking each user::::: %s", user.name)

        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
        intent.putExtra("name", user.name)
        intent.putExtra("username", user.username)
        intent.putExtra("email", user.email)
        intent.putExtra("street", user.street)
        intent.putExtra("suite", user.suite)
        intent.putExtra("city", user.city)
        intent.putExtra("zipcode", user.zipcode)
        intent.putExtra("lat", user.lat)
        intent.putExtra("lng", user.lng)
        intent.putExtra("phone", user.phone)
        intent.putExtra("website", user.website)
        intent.putExtra("companyName", user.companyName)
        intent.putExtra("catchPhrase", user.catchPhrase)
        intent.putExtra("bs", user.bs)

        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUsersObservers().observe(this, Observer {
            userAdapter.setListData(ArrayList(it))
            userAdapter.notifyDataSetChanged()
        })
    }
}