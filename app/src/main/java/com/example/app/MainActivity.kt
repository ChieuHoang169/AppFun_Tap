package com.example.app

import android.app.DownloadManager
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.app.ui.calendar.CalendarFragment
import com.example.app.ui.folders.FoldresFragment
import com.example.app.ui.notes.NotesFragment
import com.example.app.ui.todo.TodoFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {item->
        when(item.itemId){
            R.id.navigation_folders->{
                replacefragment(FoldresFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notes ->{
                replacefragment(NotesFragment())
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_todo ->{
                replacefragment(TodoFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_calendar ->{
                replacefragment(CalendarFragment())
                return@OnNavigationItemSelectedListener  true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)
        replacefragment(FoldresFragment())


    }
    private fun replacefragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_nav_menu,menu)
        val manager =getSystemService(Context.SEARCH_SERVICE)as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView =searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity,"LOng for $query",Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@MainActivity,"LOng for $newText",Toast.LENGTH_SHORT).show()
                return true          }
        })

       return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

}
