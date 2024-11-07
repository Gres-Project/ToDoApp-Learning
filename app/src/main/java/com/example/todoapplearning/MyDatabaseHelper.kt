package com.example.todoapplearning

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "ToDoDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "ToDoTable"

        private const val COLUMN_ID = "taskID"
        private const val COLUMN_TITLE = "taskTitle"
        private const val COLUMN_KETERANGAN: String = "taskKeterangan"
        private const val COLUMN_STATUS: String = "taskStatus"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_KETERANGAN TEXT, $COLUMN_STATUS TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun addTask(task: TaskModel){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_KETERANGAN, task.keterangan)
            put(COLUMN_STATUS, task.status)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllTask(): List<TaskModel>{
        val taskList = mutableListOf<TaskModel>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val keterangan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KETERANGAN))
            val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))

            val task = TaskModel(id, title, keterangan, status)
            taskList.add(task)
        }
        cursor.close()
        db.close()
        return taskList
    }

    fun updateTask(task: TaskModel){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_KETERANGAN, task.keterangan)
            put(COLUMN_STATUS, task.status)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(task.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getTaskByID(taskID: Int): TaskModel{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $taskID"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val keterangan = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_KETERANGAN))
        val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))

        cursor.close()
        db.close()
        return TaskModel(id, title, keterangan, status)
    }

    fun deleteTask(taskID: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID =?"
        val whereArgs = arrayOf(taskID.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

}