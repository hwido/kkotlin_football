package com.hwido.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hwido.football.databinding.LoginTeamactivityBinding

class LoginTeamActivity : AppCompatActivity() {
    private lateinit var binding : LoginTeamactivityBinding
//    private val CAMERA_REQUEST_CODE = 1
//    private val GALLERY_REQUEST_CODE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginTeamactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToIndividualButton.setOnClickListener { // 원래 goToIndividualButton이 아닌, login_teamactivity_goToIndividualButton 사용하려 했으나, 그럴 경우 에러 발생
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
        val intent = Intent(baseContext, LoginIndividualActivity::class.java)
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