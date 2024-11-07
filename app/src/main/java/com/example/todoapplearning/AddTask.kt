package com.example.todoapplearning

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoapplearning.databinding.ActivityAddTaskBinding

class AddTask : AppCompatActivity() {
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabaseHelper(this)

        binding.buttonSimpan.setOnClickListener{
            val title = binding.editTextTasksTitle.text.toString()
            val keterangan = binding.editTextKeterangan.text.toString()
            val status = binding.editTextStatus.text.toString()

            val task = TaskModel(0,title, keterangan, status)
            db.addTask(task)
            finish()
            Toast.makeText(this,"Task Saved", Toast.LENGTH_SHORT).show()
        }
    }

}