package com.example.todoapplearning

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todoapplearning.databinding.ActivityUpdateTaskBinding

class UpdateTask : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: MyDatabaseHelper
    private var taskID: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MyDatabaseHelper(this)

        taskID = intent.getIntExtra("task_ID", -1)
        if (taskID == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskID)
        binding.updateTaskTitle.setText(task.title)
        binding.updateTaskKeterangan.setText(task.keterangan)
        binding.updateTaskStatus.setText(task.status)

        binding.buttonUpdate.setOnClickListener {
            val newTitle = binding.updateTaskTitle.text.toString()
            val newKeterangan = binding.updateTaskKeterangan.text.toString()
            val newStatus = binding.updateTaskStatus.text.toString()

            val updateTask = TaskModel(taskID, newTitle, newKeterangan, newStatus)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changed Saved", Toast.LENGTH_SHORT).show()
        }

    }
}