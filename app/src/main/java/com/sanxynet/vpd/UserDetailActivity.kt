package com.sanxynet.vpd

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sanxynet.vpd.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    lateinit var viewModel: UserDetailViewModel
    var binding: ActivityUserDetailBinding? = null
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        viewModel = ViewModelProviders.of(this)[UserDetailViewModel::class.java]

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)


        val name = intent.getStringExtra("name")
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val street = intent.getStringExtra("street")
        val suite = intent.getStringExtra("suite")
        val city = intent.getStringExtra("city")
        val zipcode = intent.getStringExtra("zipcode")
        val lat = intent.getStringExtra("lat")
        val lng = intent.getStringExtra("lng")
        val website = intent.getStringExtra("website")
        val companyName = intent.getStringExtra("companyName")
        val catchPhrase = intent.getStringExtra("catchPhrase")
        val bs = intent.getStringExtra("bs")


        binding!!.tvName.text = name
        binding!!.tvUsername.text = "@$username"
        binding!!.tvEmail.text = email
        binding!!.tvPhone.text = phone
        binding!!.tvStreet.text = "$suite $street $city $zipcode"
        binding!!.tvLatitude.text = "Latitude: $lat"
        binding!!.tvLongitude.text = "Longitude: $lng"
        binding!!.tvCompanyName.text = companyName
        binding!!.tvCatchPhrase.text = catchPhrase
        binding!!.tvBs.text = bs
        binding!!.tvWebsite.text = "website: $website"

        binding!!.profileImage.setOnClickListener {

            requestPermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.CAMERA
                )
            )
        }

    }

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                dispatchTakePictureIntent()
            } else
                viewSettings("Go to settings and enable Camera permissions")

        }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding!!.profileImage.setImageBitmap(imageBitmap)
        }
    }

    private fun viewSettings(input: String) {
        val builder1 = AlertDialog.Builder(this)
        builder1.setCancelable(false)
        builder1.setMessage(input)
        builder1.setPositiveButton(
            "Settings"
        ) { dialog: DialogInterface, id: Int ->
            dialog.cancel()
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", this.packageName, null)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        builder1.setNegativeButton(
            "Cancel"
        ) { dialog: DialogInterface, which: Int -> dialog.cancel() }
        val alert11 = builder1.create()
        alert11.show()
    }

}