package `in`.wizion.statussaver.activity

import `in`.wizion.statussaver.MainActivity
import `in`.wizion.statussaver.R
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //hide supported Action Bar apis which less than 16
        if (Build.VERSION.SDK_INT<16){
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }else{
        //hide the status bar
            val decorView : View = window.decorView
            val uiOptions : Int = View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility=uiOptions
        }

        if (checkInstallation("com.whatsapp")){
            Handler(Looper.myLooper()!!).postDelayed(Runnable {
                kotlin.run {
                    checkPerm()
                }
            },2000)
        }
    }

    public fun  checkPerm(){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> { //If request is cancelled , the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    checkPerm()
                }
            }
            else ->{ //other 'case' lines to check for other
                   //permissions this app might request.
            }
        }
    }

    private fun checkInstallation(uri : String) : Boolean {
        val pm : PackageManager = packageManager
        var app_installed: Boolean
        try {
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES)
            app_installed= true
        }catch (e : PackageManager.NameNotFoundException){
            app_installed=false
        }
        return app_installed
    }

}