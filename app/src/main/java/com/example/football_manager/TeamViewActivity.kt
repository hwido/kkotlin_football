package com.example.football_manager

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import coil.load
import coil.transform.CircleCropTransformation
import com.example.football_manager.databinding.ActivityViewBinding

class TeamViewActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewBinding
//    private val CAMERA_REQUEST_CODE = 1
//    private val GALLERY_REQUEST_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToIndividualButton.setOnClickListener {
            goToIndividualMaker()
        }

//        //when you click on the image
//        binding.profileImageArea.setOnClickListener {
//            val pictureDialog = AlertDialog.Builder(this)
//            pictureDialog.setTitle("Select Action")
//            val pictureDialogItem = arrayOf("Select photo from Gallery",
//                "Capture photo from Camera")
//            pictureDialog.setItems(pictureDialogItem) { dialog, which ->
//
//                when (which) {
//                    0 -> gallery()
//                    1 -> camera()
//                }
//            }
//
//            pictureDialog.show()
//        }
    }
    private fun goToIndividualMaker(){
        val intent = Intent(baseContext, IndividualActivity::class.java)
        startActivity(intent)
    }

//    private fun gallery() {
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.type = "image/*"
//        startActivityForResult(intent, GALLERY_REQUEST_CODE)
//    }
//
//    private fun camera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(intent, CAMERA_REQUEST_CODE)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == Activity.RESULT_OK) {
//
//            when (requestCode) {
//
//                CAMERA_REQUEST_CODE -> {
//
//                    val bitmap = data?.extras?.get("data") as Bitmap
//
//                    //we are using coroutine image loader (coil)
//                    binding.profileImageArea.load(bitmap) {
//                        crossfade(true)
//                        crossfade(1000)
//                        transformations(CircleCropTransformation())
//                    }
//                }
//
//                GALLERY_REQUEST_CODE -> {
//
//                    binding.profileImageArea.load(data?.data) {
//                        crossfade(true)
//                        crossfade(1000)
//                        transformations(CircleCropTransformation())
//                    }
//
//                }
//            }
//
//        }
//
//    }


}
