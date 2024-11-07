package com.example.todoappyuriko

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    private lateinit var editTextTaskTitle: EditText
    private lateinit var editTextKeteranganTask: EditText
    private lateinit var editTextStatus: EditText
    private lateinit var buttonTambah: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        editTextTaskTitle = findViewById(R.id.editTextTaskTitle)
        editTextKeteranganTask = findViewById(R.id.editTextKeteranganTask)
        editTextStatus = findViewById(R.id.editTextStatus)
        buttonTambah = findViewById(R.id.buttonTambah)

        buttonTambah.setOnClickListener {
            val taskTitle = editTextTaskTitle.text.toString().trim()
            val taskKeterangan = editTextKeteranganTask.text.toString().trim()
            val taskStatus = editTextStatus.text.toString().trim()

            if (taskTitle.isNotEmpty() && taskKeterangan.isNotEmpty() && taskStatus.isNotEmpty()) {
                val myDB = MyDatabaseHelper(this@AddActivity)
                myDB.tambahTask(taskTitle, taskKeterangan, taskStatus)

                Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show()

                // Close AddActivity and return to MainActivity
                finish()
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
