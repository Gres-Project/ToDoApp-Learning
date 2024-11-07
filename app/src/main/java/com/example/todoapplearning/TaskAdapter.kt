package com.example.todoapplearning

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplearning.databinding.CardviewBinding

class TaskAdapter(
    private var tasks: List<TaskModel>,
    context: Context
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val db: MyDatabaseHelper = MyDatabaseHelper(context)

    class TaskViewHolder(val binding: CardviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = CardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        with(holder.binding) {
            textViewTaskID.text = task.id.toString()
            textViewTaskTitle.text = task.title
            textViewTaskKeterangan.text = task.keterangan
            textViewTaskStatus.text = task.status

            buttonEdit.setOnClickListener {
                val intent = Intent(root.context, UpdateTask::class.java).apply {
                    putExtra("task_ID", task.id)
                }
                root.context.startActivity(intent)
            }

            buttonDelete.setOnClickListener {
                db.deleteTask(task.id)
                refreshData(db.getAllTask())
                Toast.makeText(root.context, "Task Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun refreshData(newTasks: List<TaskModel>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
