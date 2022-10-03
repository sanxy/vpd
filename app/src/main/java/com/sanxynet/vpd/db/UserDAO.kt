package com.sanxynet.vpd.db

import androidx.room.*

@Dao
interface UserDAO
{

    @Query("SELECT * FROM userinfo ORDER BY id ASC")
    fun getAllUserInfo(): List<UserEntity>?

    @Query("SELECT * FROM userinfo ORDER BY id DESC LIMIT 1")
    fun getLast(): List<UserEntity>?


    @Query("DELETE FROM userinfo")
    fun deleteAll()


    @Insert
    fun insertUser(user: UserEntity?)

}