package com.example.book_shopedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.book_shopedit.MainPage_Fragments.Book_ShopFragment
import com.example.book_shopedit.MainPage_Fragments.Recoder_Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val QR  = QR_Fragment()

    val Book_Shop =  Book_ShopFragment()

    val Recoder  = Recoder_Fragment()

    val Person = Person_Fragment()

    fun showOrAdd(fragment: Fragment, targetTag: String){

        val cacheFragment = supportFragmentManager.findFragmentByTag(targetTag)

        val transaction = supportFragmentManager
            .beginTransaction()

        val currentFragment = supportFragmentManager.primaryNavigationFragment

        if(currentFragment?.tag == targetTag) return

        if(currentFragment!=null)
            transaction.hide(currentFragment)

        if(cacheFragment!=null) {
            transaction.show(cacheFragment)
            transaction.setPrimaryNavigationFragment(cacheFragment)
        }else {
            transaction.add(R.id.Container, fragment, targetTag)
            transaction.setPrimaryNavigationFragment(fragment)
        }

        transaction.commit()

    }

    val Listener = BottomNavigationView.OnNavigationItemSelectedListener{ item ->


        when(item.itemId) {

            R.id.qr -> {

//                 val transaction = supportFragmentManager.beginTransaction()

//                    transaction.replace(R.id.Container, QR).commit()
                showOrAdd(QR, "qr")

                return@OnNavigationItemSelectedListener true

            }


            R.id.bs -> {

//                    val transaction = supportFragmentManager.beginTransaction()
//
//                    transaction.replace(R.id.Container, Book_Shop).commit()
                showOrAdd(Book_Shop, "bs")

                return@OnNavigationItemSelectedListener true

            }

            R.id.rd -> {

//                    val transaction = supportFragmentManager.beginTransaction()
//
//                    transaction.replace(R.id.Container, Recoder).commit()
                showOrAdd(Recoder, "rd")

                return@OnNavigationItemSelectedListener true

            }

            R.id.ps -> {

//                    val transaction = supportFragmentManager.beginTransaction()
//
//                    transaction.replace(R.id.Container, Person).commit()
                showOrAdd(Person, "person")

                return@OnNavigationItemSelectedListener true

            }

        }


        false

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showOrAdd(QR, "qr")

        val navigationView:BottomNavigationView =findViewById(R.id.navigationView)

        navigationView.setOnNavigationItemSelectedListener(Listener)


    }
}
