package com.example.drawer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
//import java.util.logging.Handler


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    lateinit var homeFrag : HomeFragment
    lateinit var proFrag : ProfileFragment
    lateinit var hisFrag : HistoryFragment
    lateinit var setFrag : SettingFragment
    //lateinit var logoutFrag : LogoutFragment

    lateinit var NewsFrag : NewsImportantFragment

    lateinit private var api: API
    lateinit var session:sessionUser
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        session = sessionUser(applicationContext)
        var user:HashMap<String, String> = session.getUserDetail()
        Log.e("TESTSESSION", "SESSION" + user)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val actionBar = supportActionBar
        actionBar!!.title = "CASDCO19"

        val drawerToggle : ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            (R.string.close), (R.string.open)

        ) {

        }

        val nav_view : NavigationView = findViewById(R.id.nav_view)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)


        val handler = Handler()
        handler.postDelayed(
            Runnable {  },
            5000
        )


        homeFrag = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFrag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

      /*NewsFrag = NewsImportantFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,NewsFrag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()*/

        api = API.retrofitBuild()
        //getNews()

        /*val fragPro = ProfileFragment()
        supportFragmentManager.beginTransaction().replace(R.id.textviewPro,fragPro).commit()*/

    }




    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)

        when (menuItem.itemId)
        {
            R.id.home -> {
                homeFrag = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                /*NewsFrag = NewsImportantFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,NewsFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()*/
            }

            R.id.profile -> {
                proFrag = ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, proFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.history -> {
                hisFrag = HistoryFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, hisFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.setting -> {
                setFrag = SettingFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, setFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            R.id.logout -> {

                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, login::class.java)
                startActivity(intent)

                /*logoutFrag = LogoutFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout,logoutFrag)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()*/
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else
        {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.noti_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.noti -> {
                //Toast.makeText(this,"NOTI",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, notifications::class.java)
                startActivity(intent)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }


    }

    /*override fun passDataCom(tv: String) {
        val bundle = Bundle()
        bundle.putString("message",tv)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragPro = ProfileFragment()
        fragPro.arguments = bundle
        transaction.replace(R.id.textviewPro, fragPro)
        transaction.commit()
    }*/


    /*private fun getNews(){
        val call = api.getNews()
        call.enqueue(object: Callback<List<newsData>> {
            override fun onResponse(
                call: Call<List<newsData>>,
                response: Response<List<newsData>>
            ) {
                if(response.isSuccessful)
                {
                    val list = response.body()
                    Log.i("API","--------------- isSuccessful x CHECK ERROR ----------------")

                    for(i in 0 until list!!.size)
                    {
                        val msg = "\n news_id: ${list[i].news_id} \n name: ${list[i].name} \n detail: ${list[i].detail} \n date: ${list[i].date} \n notification_id: ${list[i].notification_id} \n"
                        textView.append(msg)
                    }
                }
            }

            override fun onFailure(call: Call<List<newsData>>, t: Throwable) {
                Log.e("API",t.message+" -*-*-*-*-*-*-*-*-*-*-*-* x CHECK ERROR")
            }

        })
    }*/

}