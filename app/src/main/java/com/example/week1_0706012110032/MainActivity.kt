package com.example.week1_0706012110032

import Adapter.RVAdapter
import Database.GlobalVar
import Interface.CardListener
import Interface.DeleteListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CardListener, DeleteListener {

    private val adapter = RVAdapter(GlobalVar.listDataAnimal, this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setUpRecyclerView()
        listener()
    }

    override fun onResume() {
        super.onResume()

        setUp()
        adapter.notifyDataSetChanged()
    }

    private fun listener() {
        addUpdateAnimal.setOnClickListener {
            val myIntent = Intent(this, AddActivity::class.java)
            
            startActivity(myIntent)
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(baseContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, AddActivity::class.java).apply {
            putExtra("Position", position)
        }

        startActivity(myIntent)
    }

    override fun onCardDelete(position: Int) {
        MaterialAlertDialogBuilder(
            this,
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle(resources.getString(R.string.title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton(resources.getString(R.string.delete)) { dialog, which ->
                GlobalVar.listDataAnimal.removeAt(position)

                setUp()
                adapter.notifyDataSetChanged()

                dialog.cancel()
            }
            .show()
    }

    private fun setUp() {
        if (GlobalVar.listDataAnimal.size != 0) {
            noAnimals_ImageView.visibility = View.INVISIBLE
            noAnimals_TextView.visibility = View.INVISIBLE
        } else {
            noAnimals_ImageView.visibility = View.VISIBLE
            noAnimals_TextView.visibility = View.VISIBLE
        }
    }
}