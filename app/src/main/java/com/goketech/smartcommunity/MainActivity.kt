package com.goketech.smartcommunity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.goketech.smartcommunity.view.home.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var homeFragment: HomeFragment
    //    lateinit var neighborFragment: NeighborFragment()
//    lateinit var acticityFragment:ActicityFragment()
//    lateinit var myFragment: MyFragment()
    //private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { tm ->
        var bt = supportFragmentManager.beginTransaction()
        when (tm.itemId) {

            R.id.navigation_home -> {

                bt.replace(R.id.layout_page, homeFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_neighbor -> {

                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_acticity -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_my -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initFragment()

    }

    fun initFragment() {

        homeFragment = HomeFragment()
//        var neighborFragment = NeighborFragment()
//        var acticityFragment = ActicityFragment()
//        var myFragment = MyFragment()
        var bt = supportFragmentManager.beginTransaction()
        bt.replace(R.id.layout_page, homeFragment).commit()
    }
}
