package com.sanxynet.vpd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sanxynet.vpd.db.UserDatabase
import com.sanxynet.vpd.db.UserEntity


class AddUserViewModel(app: Application): AndroidViewModel(app) {

    lateinit var allUsers : MutableLiveData<List<UserEntity>>

    init{
        allUsers = MutableLiveData()
        getLast()
    }

    fun getLastUsersObservers(): MutableLiveData<List<UserEntity>> {

        getLast()
        return allUsers
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = UserDatabase.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
    }



    fun getLast() {
        val userDao = UserDatabase.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getLast()

        allUsers.postValue(list)
    }

}