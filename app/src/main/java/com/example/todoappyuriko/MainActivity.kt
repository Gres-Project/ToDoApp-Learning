// MainActivity.kt
package com.example.todoappyuriko

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    private lateinit var myDB: MyDatabaseHelper
    private lateinit var taskID: ArrayList<String>
    private lateinit var taskTitle: ArrayList<String>
    private lateinit var taskKeterangan: ArrayList<String>
    private lateinit var taskStatus: ArrayList<String>

    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.addButton)

        myDB = MyDatabaseHelper(this)
        taskID = ArrayList()
        taskTitle = ArrayList()
        taskKeterangan = ArrayList()
        taskStatus = ArrayList()

        // Initialize adapter before loading data
        customAdapter = CustomAdapter(this, taskID, taskTitle, taskKeterangan, taskStatus)
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load data
        storeDataInArray()

        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }


    private fun storeDataInArray() {
        val cursor: Cursor = myDB.TampilkanSemuaTask()
        if (cursor.count == 0) {
            return
        } else{
            while (cursor.moveToNext()) {
                taskID.add(cursor.getString(0))
                taskTitle.add(cursor.getString(1))
                taskKeterangan.add(cursor.getString(2))
                taskStatus.add(cursor.getString(3))

            }
            cursor.close()
        }


    }


}