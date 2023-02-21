package com.example.app1


import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity(), View.OnClickListener  {
    //Create variables to hold the three strings

    private var mFirstName: String? = null
    private var mLastName: String? = null
    private var mMiddleName: String? = null

    private var modulesList: HashMap<String, Class<out AppCompatActivity>>? = null


    //Create variables for the UI elements that we need to control
    private var mEtFirstName: EditText? = null
    private var mEtLastName: EditText? = null
    private var mSubmit: Button? = null
    private var mEtMiddleName: EditText? = null
    private var photoButton: Button? = null
    private var mIvPic: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Get the button
        mSubmit = findViewById(R.id.button_submit)
        mSubmit!!.setOnClickListener(this)

        photoButton = findViewById(R.id.photo_button)
        photoButton!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {
                mEtFirstName = findViewById(R.id.et_Fname)
                mFirstName = mEtFirstName!!.text.toString()

                mEtMiddleName = findViewById(R.id.et_Mname)
                mMiddleName = mEtMiddleName!!.text.toString()

                mEtLastName = findViewById(R.id.et_Lname)
                mLastName = mEtLastName!!.text.toString()


                if (mFirstName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a first name!", Toast.LENGTH_SHORT)
                        .show()
                }
                if (mMiddleName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a middle name!", Toast.LENGTH_SHORT)
                        .show()
                }
                if (mLastName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a last name!", Toast.LENGTH_SHORT)
                        .show()
                }

                if (mFirstName.isNullOrBlank() || mLastName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a name first!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    //Reward them for submitting their names
                    Toast.makeText(this@MainActivity, "Good job!", Toast.LENGTH_SHORT).show()


                    val messageIntent = Intent(this, SecondPage::class.java)
                    messageIntent.putExtra("ET_FIRST", mFirstName)
                    messageIntent.putExtra("ET_LAST", mLastName)
                    messageIntent.putExtra("ET_MIDDLE", mMiddleName)
                    this.startActivity(messageIntent)
                }



            }
            R.id.photo_button -> {
                //The button press should open a camera
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    cameraActivity.launch(cameraIntent)
                } catch (ex: ActivityNotFoundException) {

                }
            }
        }
    }
    private val cameraActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                mIvPic = findViewById<View>(R.id.thumbnail_photo) as ImageView

                if (Build.VERSION.SDK_INT >= 33) {
                    val thumbnailImage =
                        result.data!!.getParcelableExtra("data", Bitmap::class.java)
                    mIvPic!!.setImageBitmap(thumbnailImage)
                } else {
                    val thumbnailImage = result.data!!.getParcelableExtra<Bitmap>("data")
                    mIvPic!!.setImageBitmap(thumbnailImage)
                }


            }
        }

}