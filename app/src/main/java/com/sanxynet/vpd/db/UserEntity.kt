package com.sanxynet.vpd.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userinfo")
//@Entity
class UserEntity {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    val userId : Int = 0
//    @PrimaryKey
//    var userId: Int = 0

//    @ColumnInfo(name ="Id")
    @PrimaryKey
    var id:  Int = 0

    @ColumnInfo(name ="Name")
    var name:  String =""

    @ColumnInfo(name ="Username")
    var username:  String =""

    @ColumnInfo(name ="Email")
    var email:  String =""

    @ColumnInfo(name ="Street")
    var street:  String =""

    @ColumnInfo(name ="Suite")
    var suite:  String =""

    @ColumnInfo(name ="City")
    var city:  String =""

    @ColumnInfo(name ="Zipcode")
    var zipcode:  String =""

    @ColumnInfo(name ="Lat")
    var lat:  String =""

    @ColumnInfo(name ="Lng")
    var lng:  String =""

    @ColumnInfo(name ="Phone")
    var phone:  String =""

    @ColumnInfo(name ="Website")
    var website:  String =""

    @ColumnInfo(name ="CompanyName")
    var companyName:  String =""

    @ColumnInfo(name ="CatchPhrase")
    var catchPhrase:  String =""

    @ColumnInfo(name ="Bs")
    var bs:  String =""

}

