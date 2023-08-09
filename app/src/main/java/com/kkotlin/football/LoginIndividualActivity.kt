package com.kkotlin.football

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.hwido.football.R
import com.hwido.football.databinding.LoginIndividualactivityBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

class LoginIndividualActivity : AppCompatActivity() {
    private lateinit var binding : LoginIndividualactivityBinding

    private var fbAuth : FirebaseAuth? = null
    private var fbFirestore : FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginIndividualactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        //val check = findViewById<EditText>(R.id.login_individualactivity_check)
        val finalButton = findViewById<Button>(R.id.login_individualactivity_finalButton)

        finalButton.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("membership_personal")
            val birth = findViewById<EditText>(R.id.login_individualactivity_birth)?.text.toString()
            val foot = findViewById<EditText>(R.id.login_individualactivity_foot).text.toString()
            val height = findViewById<EditText>(R.id.login_individualactivity_height).text.toString()
            val name = findViewById<EditText>(R.id.login_individualactivity_name).text.toString()
            val position = findViewById<EditText>(R.id.login_individualactivity_position).text.toString()
            val team = findViewById<EditText>(R.id.login_individualactivity_team).text.toString()
            //val teamButton = findViewById<Button>(R.id.login_individualactivity_teamButton)
            val weight = findViewById<EditText>(R.id.login_individualactivity_weight).text.toString()

            val data = LoginMemberPersonalData(birth, foot, height, name, position, team, weight)

            Log.d(data.birth, data.birth.toString())

            myRef
                .push()
                .setValue(data)

            // 회원가입 이후, 로그인 페이지로 돌아간다
            val intent = Intent(this, LoginMainpage::class.java)
            startActivity(intent)

        }

        binding.changeProfileTakePictureIndividual.setOnClickListener {
            cameraCheckPermission()
        }
        binding.changeProfileGalleryPictureIndividual.setOnClickListener {
            galleryCheckPermission()
        }

        binding.individualPicture.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItem = arrayOf(
                "Select Photo from Gallery",
                "Capture photo from Camera"
            )
            pictureDialog.setItems(pictureDialogItem) { dialog, which ->
                when (which) {
                    0 -> gallery()
                    1 -> camera()
                }
            }
            pictureDialog.show()
        }

    }

    //when you click on the image
    //이미지 클릭시 갤러리롤 갈껀지 카메라로 갈건지 선택하게 함

    //갤러리 단일허락_ ReadMedia
    private fun galleryCheckPermission(){
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_MEDIA_IMAGES
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(report: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(this@LoginIndividualActivity , "You have denied the storage permission to select image",
                    Toast.LENGTH_SHORT).show()

                showRotationalDialogForPermission()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {
                showRotationalDialogForPermission()
            }

        }).onSameThread().check()
//            .onSameThread().check()  //이건 어캐 쓰는겨??
    }


    //갤러리들어가는 함수
    private fun gallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityResult.launch(intent)
    }
    //갤러리 이미지 가져노는 함수
    private val galleryActivityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null) {
            //we are using coroutine image loader (coil)
            binding.individualPicture.load(it.data?.data) {
                crossfade(true)
                crossfade(0)//사진나타나는 시간초
                transformations(RoundedCornersTransformation(100f))
            }

        }
    }

    //카메라 허락 다중 허락 read_media, camera
    private fun cameraCheckPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report.let {
                        //카메라 허락 받으면 카메라 킨다
                        //처음 허락 받았을때말고 다음에는 허락 없이가나 그럼?
                        //암튼 버튼 눌렀을떄 어떤 기능이 지금 실행이 안여기
                        if (report!!.areAllPermissionsGranted()) {
                            //여기를 못들어옴
                            Toast.makeText(
                                this@LoginIndividualActivity,
                                "Permission Granted",
                                Toast.LENGTH_SHORT
                            ).show()
                            camera()
                        }//여쪽으로 못들어오는것 같은데
                        else {
                            Toast.makeText(
                                this@LoginIndividualActivity,
                                "onePermission notGranted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }

                //거절했을 때 다시 허락 설정하러  설정앱으로 가게해줌
                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    showRotationalDialogForPermission()
                }
            }
            ).onSameThread().check()
    }


    //카메라
    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //사라짐 결과를 되돌려 받는 방법
        cameraActivityResult.launch(intent)
    }

    private val cameraActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {

                val extras = it.data!!.extras

                val bitmap = extras?.get("data") as Bitmap
                //we are using coroutine image loader (coil)
                binding.individualPicture.load(bitmap) {
                    crossfade(true)
                    crossfade(0)//사진나타나는 시간초 fadeout
                    transformations(RoundedCornersTransformation(100f))//둥글게둥글게
                }

            }

        }


    //거절했는데 다시 누르면 설정가서 바꾸라
    private fun showRotationalDialogForPermission() {
        AlertDialog.Builder(this)
            .setMessage(
                "It looks like you have turned off permissions"
                        + "required for this feature. It can be enable under App settings!!!"
            )


            //세팅으로 간다
            .setPositiveButton("Go To SETTINGS") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            //_-> 이게 뭐야
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

}