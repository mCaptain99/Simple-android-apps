package com.example.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CustomAdapter(private val data: MutableList<Task>, filter: String, val taskDao: TaskDao) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var todayDate: Calendar = Calendar.getInstance()
    private var tomorrowDate: Calendar
    var filter: String = filter
    set(value){
        field = value
        notifyDataSetChanged()
    }

    init {
        tomorrowDate = Calendar.getInstance()
        tomorrowDate.set(Calendar.DATE, todayDate.get(Calendar.DATE) + 1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val time: TextView
        val date: TextView
        val description: TextView
        var image: ImageView
        var task: LinearLayout
        var btnDelete: Button

        init{
            time = view.findViewById<TextView>(R.id.time)
            date = view.findViewById<TextView>(R.id.date)
            description = view.findViewById<TextView>(R.id.description)
            image = view.findViewById<ImageView>(R.id.image)
            task = view.findViewById<LinearLayout>(R.id.task)
            btnDelete = view.findViewById<Button>(R.id.btn_delete)
        }

        fun bind(task: Task, index: Int){
            btnDelete.setOnClickListener { deleteTask(index) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (filter == "today" && data[position].date.get(Calendar.DATE) != todayDate.get(Calendar.DATE)) {
            holder.task.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else if (filter == "tomorrow" && data[position].date.get(Calendar.DATE) != tomorrowDate.get(Calendar.DATE)) {
            holder.task.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else {
            holder.task.visibility = View.VISIBLE
            holder.itemView.layoutParams =
                RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            when (data[position].priority) {
                Priority.LOW -> {
                    holder.description.setTextColor(Color.parseColor("#009933"))
                }
                Priority.MIDDLE -> {
                    holder.description.setTextColor(Color.parseColor("#995500"))
                }
                Priority.HIGH -> {
                    holder.description.setTextColor(Color.parseColor("#ff0000"))
                }
            }
            holder.time.text = data[position].time.toString() + " h"
            holder.date.text = Utils.toDateTimeString(data[position].date)
            holder.description.text = data[position].description
            holder.image.setImageResource(data[position].type.image)
            holder.bind(data[position], position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteTask(index: Int) {
        val task = data[index]
        data.removeAt(index)
        taskDao.delete(task)
        notifyDataSetChanged()
    }

}