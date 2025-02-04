package com.cdrussell.casterio.room

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        addTaskButton.setOnClickListener {
            addTask()
        }
        
        taskTitleInput.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if(actionId == IME_ACTION_DONE) {
                addTask()
                taskTitleInput.setText("")
                return@OnEditorActionListener true
            }
            return@OnEditorActionListener false
        })

        taskListAdapter = TaskListAdapter()
        taskList.layoutManager = LinearLayoutManager(this)
        taskList.adapter = taskListAdapter
    }

    private fun addTask() {
        val title = taskTitleInput.text.toString()

        if (title.isBlank()) {
            Snackbar.make(toolbar, "Task title is required", Snackbar.LENGTH_SHORT).show()
            return
        }

        val task = Task(title = title)

        taskListAdapter.addTask(task)
    }
}
