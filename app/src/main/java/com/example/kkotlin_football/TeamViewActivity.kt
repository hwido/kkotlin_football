package com.example.kkotlin_football

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.kkotlin_football.databinding.ActivityViewBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

class TeamViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToIndividualButton.setOnClickListener {
            goToIndividualMaker()
        }

        binding.changeProfileTakePicture.setOnClickListener {
            cameraCheckPermission()
        }
        binding.changeProfileGalleryPicture.setOnClickListener {
            galleryCheckPermission()
        }

        binding.profileImageArea.setOnClickListener {
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

    private fun goToIndividualMaker() {
        val intent = Intent(baseContext, IndividualActivity::class.java)
        startActivity(intent)
    }

    ////////////////////////////////////////////////////////////////


    //when you click on the image
    //이미지 클릭시 갤러리롤 갈껀지 카메라로 갈건지 선택하게 함

    private fun galleryCheckPermission(){
        Dexter.withContext(this).withPermission(
            Manifest.permission.READ_MEDIA_IMAGES
        ).withListener(object : PermissionListener {
            override fun onPermissionGranted(report: PermissionGrantedResponse?) {
                gallery()
            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                Toast.makeText(this@TeamViewActivity , "You have denied the storage permission to select image",
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

    private fun gallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryActivityResult.launch(intent)
    }

    private val galleryActivityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null) {
            //we are using coroutine image loader (coil)
            binding.profileImageArea.load(it.data?.data) {
                crossfade(true)
                crossfade(0)//사진나타나는 시간초
                transformations(RoundedCornersTransformation(100f))
            }

        }
    }

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
                                this@TeamViewActivity,
                                "Permission Granted",
                                Toast.LENGTH_SHORT
                            ).show()
                            camera()
                        }//여쪽으로 못들어오는것 같은데
                        else {
                            Toast.makeText(
                                this@TeamViewActivity,
                                "onePermission notGranted",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }

                //한번 보여 졌는데 거절 눌렀을떄 그리고 그다음에 다시 사진 찍고 싶을떄
                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    showRotationalDialogForPermission()
                }
            }
            ).onSameThread().check()//이건 무슨뜻
    }


        //여기서부터 수정해야된다
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
                    binding.profileImageArea.load(bitmap) {
                        crossfade(true)
                        crossfade(0)//사진나타나는 시간초
                        transformations(RoundedCornersTransformation(100f))
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





    //허락버튼 문제인듯한데..dex permission을 봐야겠다



