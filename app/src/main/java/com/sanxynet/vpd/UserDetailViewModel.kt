package com.sanxynet.vpd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sanxynet.vpd.db.UserDatabase
import com.sanxynet.vpd.db.UserEntity

class UserDetailViewModel(app: Application): AndroidViewModel(app) {

    lateinit var allUsers : MutableLiveData<List<UserEntity>>

    fun insertUserInfo(entity: UserEntity){
        val userDao = UserDatabase.getAppDatabase(getApplication())?.userDao()
        userDao?.insertUser(entity)
    }


}