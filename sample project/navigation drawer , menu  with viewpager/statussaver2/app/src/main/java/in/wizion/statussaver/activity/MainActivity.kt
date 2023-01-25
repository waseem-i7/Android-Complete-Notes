package `in`.wizion.statussaver

import `in`.wizion.statussaver.fragments.bwa.BWAFragment
import `in`.wizion.statussaver.fragments.wa.WAFragment
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import hotchemi.android.rate.AppRate
import java.io.File


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //code for app rating
        AppRate.with(this)
            .setInstallDays(0) // default 10, 0 means install day.
            .setLaunchTimes(5) // default 10
            .setRemindInterval(10) // default 1
            .setShowLaterButton(true) // default true
            .setDebug(false) // default false
            .setOnClickButtonListener { which ->
                // callback listener.
                Log.d(MainActivity::class.java.name, Integer.toString(which))
            }
            .monitor()
        //Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this)


        //set a toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //stash()

        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView : NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null){
            navigationView.menu.getItem(0).setChecked(true)
            val fragment : Fragment = WAFragment()
            val fm : FragmentManager = supportFragmentManager
            fm.beginTransaction().replace(R.id.framelayout,fragment).commit()
        }
    }

    override fun onBackPressed() {
        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main,menu)
       return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //Handle action bar item clicks here. the action bar will
        //automatically handle clicks on the Home/Up button , so long
        //as you specify a parent activity in AndroidManifest.xml
        val id : Int = item.itemId

        //noinspection simplifiableIfStatement
        if (id == R.id.action_share){
            val intent = Intent("android.intent.action.SEND")
            intent.type = "text/plain"
            intent.putExtra("android.intent.extra.SUBJECT", "Share WApp Status Saver App")
            intent.putExtra(
                "android.intent.extra.TEXT",
                "Try this Awesome App 'WhatsApp Status Saver' which helps you in Saving all the WhatsApp Statuses ..! \nhttps://play.google.com/store/apps/details?id=com.mwi7.saver"
            )
            startActivity(Intent.createChooser(intent, "Share Via"))
            return true
        }
        if (id == R.id.action_help){
            val inflate : View = LayoutInflater.from(this).inflate(R.layout.help,null as ViewGroup?)
            val builder : Builder = Builder(this)
            builder.setView(inflate).setTitle("How To Use?").setPositiveButton("Ok!", { dialog, which -> dialog.dismiss() })
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //Handle navigation view item clicks here
        val id : Int = item.itemId

        if (id==R.id.nav_whatsapp){
            val fragment: Fragment = WAFragment()
            val fm = supportFragmentManager
            fm.beginTransaction().replace(R.id.framelayout, fragment).commit()
        }
        else if (id == R.id.nav_business){
            if (checkInstallation("com.whatsapp.w4b")){
                val fragment: Fragment = BWAFragment()
                val fm = supportFragmentManager
                fm.beginTransaction().replace(R.id.framelayout, fragment).commit()
            }else{
                Toast.makeText(this,"bwhatsup Not Installed", Toast.LENGTH_LONG).show()
            }
        }else{
            AppRate.with(this).showRateDialog(this);
        }

        val drawer : DrawerLayout = findViewById(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun checkInstallation(uri : String) : Boolean{
        val pm : PackageManager = packageManager
        var app_installed : Boolean
        try{
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES)
            app_installed = true
        }catch (e : PackageManager.NameNotFoundException){
            app_installed = false
        }
        return app_installed
    }


    fun stash() {
        var file = File("/storage/emulated/0/WhatsApp/Media/.Statuses/")
        if (!file.isDirectory()) {
            file.mkdirs()
        }
        file = File("/storage/emulated/0/WhatsApp Business/Media/.Statuses/")
        if (!file.isDirectory()) {
            file.mkdirs()
        }
        file = File("/storage/emulated/0/GWAActivity/Media/.Statuses/")
        if (!file.isDirectory()) {
            file.mkdirs()
        }
        File("/storage/emulated/0/StorySaver/").mkdirs()
    }
}

