package com.example.todoapplearning

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapplearning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MyDatabaseHelper
    private lateinit var taskAdapater: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabaseHelper(this)
        taskAdapater = TaskAdapter(db.getAllTask(), this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = taskAdapater

        binding.buttonTambah.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        taskAdapater.refreshData(db.getAllTask())
    }
}



