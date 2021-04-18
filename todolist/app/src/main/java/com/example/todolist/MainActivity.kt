package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var taskList: ArrayList<Task> = ArrayList<Task>()
    private lateinit var recyclerView: RecyclerView
    private var sortBy: String = "date"
    private var filter: String = "all"
    private lateinit var db : AppDatabase
    private lateinit var taskDao : TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        db = AppDatabase.getInstance(this)!!
        taskDao = db.taskDao()
        taskList.addAll(taskDao.getAll())
        recyclerView = binding.taskList
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(taskList, "all", taskDao)
        setContentView(view)
        binding.btnSortByDate.setBackgroundResource(R.color.grey)
        binding.btnSortByPriority.setBackgroundResource(R.color.white)
        binding.btnSortByType.setBackgroundResource(R.color.white)
        binding.btnShowOnlyTomorrow.setBackgroundResource(R.color.white)
        binding.btnShowOnlyToday.setBackgroundResource(R.color.white)
        binding.btnShowAll.setBackgroundResource(R.color.grey)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            val task: Task = data?.getSerializableExtra("task") as Task
            taskList.add(0, task)
            taskDao.insertAll(task)
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            putSerializable("taskList", taskList)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.run {
            taskList = getSerializable("taskList") as ArrayList<Task>
            recyclerView.adapter = CustomAdapter(taskList, filter, taskDao)
        }
    }

    private fun sort(){
        if(sortBy == "date"){
            taskList.sortWith(TaskComparators.dateComparator)
        }
        else if(sortBy == "priority"){
            taskList.sortWith(TaskComparators.priorityComparator)
            taskList.reverse()
        }
        else if(sortBy == "type"){
            taskList.sortWith(TaskComparators.typeComparator)
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun addTask(view: View){
        val intent = Intent(this, AddTaskActivity::class.java)
        startActivityForResult(intent, 123)
    }

    fun sortByDate(view: View) {
        sortBy = "date"
        sort()
        binding.btnSortByDate.setBackgroundResource(R.color.grey)
        binding.btnSortByPriority.setBackgroundResource(R.color.white)
        binding.btnSortByType.setBackgroundResource(R.color.white)

    }
    fun sortByPriority(view: View) {
        sortBy = "priority"
        sort()
        binding.btnSortByPriority.setBackgroundResource(R.color.grey)
        binding.btnSortByDate.setBackgroundResource(R.color.white)
        binding.btnSortByType.setBackgroundResource(R.color.white)
    }
    fun sortByType(view: View) {
        sortBy = "type"
        sort()
        binding.btnSortByType.setBackgroundResource(R.color.grey)
        binding.btnSortByDate.setBackgroundResource(R.color.white)
        binding.btnSortByPriority.setBackgroundResource(R.color.white)
    }
    fun showOnlyToday(view: View){
        (recyclerView.adapter as CustomAdapter).filter = "today"
        filter = "today"
        binding.btnShowOnlyTomorrow.setBackgroundResource(R.color.white)
        binding.btnShowOnlyToday.setBackgroundResource(R.color.grey)
        binding.btnShowAll.setBackgroundResource(R.color.white)
    }
    fun showAll(view: View){
        (recyclerView.adapter as CustomAdapter).filter = "all"
        filter = "all"
        binding.btnShowOnlyTomorrow.setBackgroundResource(R.color.white)
        binding.btnShowOnlyToday.setBackgroundResource(R.color.white)
        binding.btnShowAll.setBackgroundResource(R.color.grey)
    }
    fun showOnlyTomorrow(view: View){
        (recyclerView.adapter as CustomAdapter).filter = "tomorrow"
        filter = "tomorrow"
        binding.btnShowOnlyTomorrow.setBackgroundResource(R.color.grey)
        binding.btnShowOnlyToday.setBackgroundResource(R.color.white)
        binding.btnShowAll.setBackgroundResource(R.color.white)
    }

}