package com.sanxynet.vpd

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sanxynet.vpd.databinding.ActivityAddUserBinding
import com.sanxynet.vpd.db.UserEntity
import timber.log.Timber

class AddUserActivity : AppCompatActivity() {

    var binding: ActivityAddUserBinding? = null
    lateinit var viewModel: AddUserViewModel

    var sUsername: String? = null
    var sFullName: String? = null
    var sEmail: String? = null
    var sStreet:String? = null
    var sSuite:String? = null
    var sZipCode:String? = null
    var sCity:String? = null
    var sLatitude:String? = null
    var sLongitude: String? = null
    var sPhone: String? = null
    var sWebsite: String? = null
    var sCompanyName: String? = ""
    var sCatchPhrase:String? = ""
    var sBs: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        viewModel = ViewModelProviders.of(this)[AddUserViewModel::class.java]



        binding!!.saveProfile.setOnClickListener {
            if (validate()){
                var lastId = 0
                //get last id in room db
                viewModel.getLastUsersObservers().observe(this, Observer {

                    ArrayList(it).forEach {
                        Timber.i("Empty state 111111:: ${it.id}")
                        lastId = it.id
                    }
                })
                //save data to room db
                val userEntity = UserEntity()
                userEntity.id = lastId+1
                userEntity.name = sFullName.toString()
                userEntity.username = sUsername.toString()
                userEntity.email = sEmail.toString()
                userEntity.phone = sPhone.toString()
                userEntity.website = sWebsite.toString()

                userEntity.street = sStreet.toString()
                userEntity.suite = sSuite.toString()
                userEntity.city = sCity.toString()
                userEntity.zipcode = sZipCode.toString()
                userEntity.lat = sLatitude.toString()
                userEntity.lng = sLongitude.toString()
                userEntity.companyName =sCompanyName.toString()
                userEntity.catchPhrase =sCatchPhrase.toString()
                userEntity.bs = sBs.toString()

                viewModel.insertUserInfo(userEntity)

                //go back to MainActivity
                Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
                finish()


            }else{
                Timber.i("Empty state:: ")

            }


        }
    }

    fun validate(): Boolean {
        var valid = true
        sFullName = binding!!.userFullName.text.toString()
        sUsername = binding!!.username.text.toString()
        sEmail = binding!!.email.text.toString()
        sPhone = binding!!.userPhoneNumber.text.toString()
        sSuite = binding!!.suite.text.toString()
        sStreet = binding!!.street.text.toString()
        sCity = binding!!.city.text.toString()
        sZipCode = binding!!.zipCode.text.toString()
        sLatitude = binding!!.latitude.text.toString()
        sLongitude = binding!!.longitude.text.toString()
        sWebsite = binding!!.website.text.toString()
        sCompanyName = binding!!.companyName.text.toString()
        sCatchPhrase = binding!!.catchPhrase.text.toString()
        sBs = binding!!.bs.text.toString()

        if (sFullName!!.isEmpty()) {
            binding!!.userFullName.error = "Name is Empty"
            valid = false
        } else {
            binding!!.userFullName.error = null
        }
        if (sUsername!!.isEmpty()) {
            binding!!.username.error = "BVN is Empty"
            valid = false
        } else {
            binding!!.username.error = null
        }
        if (sPhone!!.isEmpty()) {
            binding!!.userPhoneNumber.error = "Mobile Number is Empty"
            valid = false
        } else {
            binding!!.userPhoneNumber.error = null
        }
        if (sEmail!!.isEmpty()) {
            binding!!.email.error = "Email is Empty"
            valid = false
        } else {
            binding!!.email.error = null
        }
        if (sSuite!!.isEmpty()) {
            binding!!.suite.error = "Suite is Empty"
            valid = false
        } else {
            binding!!.suite.error = null
        }
        if (sStreet!!.isEmpty()) {
            binding!!.street.error = "Street is Empty"
            valid = false
        } else {
            binding!!.street.error = null
        }
        if (sCity!!.isEmpty()) {
            binding!!.city.error = "City is Empty"
            valid = false
        } else {
            binding!!.city.error = null
        }
        if (sZipCode!!.isEmpty()) {
            binding!!.zipCode.error = "Zip code is Empty"
            valid = false
        } else {
            binding!!.zipCode.error = null
        }
        if (sLatitude!!.isEmpty()) {
            binding!!.latitude.error = "Latitude is Empty"
            valid = false
        } else {
            binding!!.latitude.error = null
        }
        if (sLongitude!!.isEmpty()) {
            binding!!.longitude.error = "Longitude is Empty"
            valid = false
        } else {
            binding!!.longitude.error = null
        }
        if (sWebsite!!.isEmpty()) {
            binding!!.website.error = "Website is Empty"
            valid = false
        } else {
            binding!!.website.error = null
        }
        if (sCompanyName!!.isEmpty()) {
            binding!!.companyName.error = "Company name is Empty"
            valid = false
        } else {
            binding!!.companyName.error = null
        }
        if (sCatchPhrase!!.isEmpty()) {
            binding!!.catchPhrase.error = "CatchPhrase  is Empty"
            valid = false
        } else {
            binding!!.catchPhrase.error = null
        }
        if (sBs!!.isEmpty()) {
            binding!!.bs.error = "Bs  is Empty"
            valid = false
        } else {
            binding!!.bs.error = null
        }
        return valid
    }

}