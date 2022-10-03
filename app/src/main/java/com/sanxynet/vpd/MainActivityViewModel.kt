package com.sanxynet.vpd
import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.sanxynet.vpd.db.UserDatabase
import com.sanxynet.vpd.db.UserEntity
import org.json.JSONArray
import org.json.JSONException
import timber.log.Timber
import java.util.concurrent.Executors


class MainActivityViewModel(app: Application): AndroidViewModel(app) {
    lateinit var allUsers : MutableLiveData<List<UserEntity>>


    init{
//        deleteAllData()
        allUsers = MutableLiveData()
        getAllUsers()
    }

    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        downloadUsers()
        getAllUsers()
        return allUsers
    }

    fun getAllUsers() {
        val userDao = UserDatabase.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()

        allUsers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = UserDatabase.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }


    fun deleteAllData(){
        val userDao = UserDatabase.getAppDatabase(getApplication())?.userDao()
        userDao?.deleteAll()
        getAllUsers()
    }

    private fun downloadUsers() {

        Executors.newSingleThreadExecutor().submit {
            // You can perform your task here.
            val (request, response, result) = "https://jsonplaceholder.typicode.com/users"
                .httpGet()
                .header(Headers.CONTENT_TYPE, "application/json")
                .header(Headers.ACCEPT, "application/json")
                .responseString()

            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Timber.i("User download Error:: ${ex}")
                }
                is Result.Success -> {
                    val data = result.get()
                    Timber.i("User download done:::")

                    loadUser(data)

                }
            }

        }

    }

    private fun loadUser(data: String) {

        var filter: JSONArray? = null
        try {

            filter = JSONArray(data)

//             Timber.i("checking all:: %s", filter)
            Timber.i("User checking each user::::: %s", "")

            if (filter.length() > 0) {

                for (i in 0 until filter.length()) {
                    try {
                        val jsonOb = filter.getJSONObject(i)
//                    Timber.i("User checking each:: %s", jsonOb)

                        //save data to room
                        val userEntity = UserEntity()
                        userEntity.id = jsonOb.getInt("id")
                        userEntity.name = jsonOb.getString("name")
                        userEntity.username = jsonOb.getString("username")
                        userEntity.email = jsonOb.getString("email")
                        userEntity.phone = jsonOb.getString("phone")
                        userEntity.website = jsonOb.getString("website")

                        userEntity.street = jsonOb.getJSONObject("address").getString("street")
                        userEntity.suite = jsonOb.getJSONObject("address").getString("suite")
                        userEntity.city = jsonOb.getJSONObject("address").getString("city")
                        userEntity.zipcode =
                            jsonOb.getJSONObject("address").getString("zipcode")

                        userEntity.lat = jsonOb.getJSONObject("address").getJSONObject("geo")
                            .getString("lat")
                        userEntity.lng = jsonOb.getJSONObject("address").getJSONObject("geo")
                            .getString("lng")

                        userEntity.companyName =
                            jsonOb.getJSONObject("company").getString("name")
                        userEntity.catchPhrase =
                            jsonOb.getJSONObject("company").getString("catchPhrase")
                        userEntity.bs = jsonOb.getJSONObject("company").getString("bs")

                       insertUserInfo(userEntity)

                    } catch (e: Exception) {
                        Timber.i("user data Error:: %s", e.message)
                    }
                }

                //fetch Records
                allUsers = MutableLiveData()
                getAllUsers()

            } else {

//                recyclerView!!.visibility = View.GONE
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

}