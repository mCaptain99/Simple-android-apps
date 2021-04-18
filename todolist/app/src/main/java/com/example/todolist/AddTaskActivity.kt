package com.example.todolist

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.databinding.AddTaskBinding
import java.util.*


class AddTaskActivity : AppCompatActivity() {
    private lateinit var binding: AddTaskBinding
    private var selectedType = 9
    private var date = Calendar.getInstance()


    private var taskTypes = listOf(TaskType.FOOD, TaskType.MEETING, TaskType.LEARNING,
        TaskType.HOME, TaskType.MEDICAL, TaskType.PET, TaskType.TRAVEL, TaskType.SHOPPING, TaskType.OTHER)
    private lateinit var priorities: Map<Int, Priority>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =AddTaskBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        view.findViewById<ImageButton>(R.id.btn9).setBackgroundResource(R.color.grey)
        binding.priorityPicker.check(binding.radioLow.id)
        priorities = mapOf(binding.radioHigh.id to Priority.HIGH, binding.radioMiddle.id to
                Priority.MIDDLE, binding.radioLow.id to Priority.LOW)
    }

    fun selectType(view : View){
        for(i in 1..9){
            val btn = binding.selectTypeTable.findViewWithTag<ImageButton>(i.toString())
            btn.setBackgroundResource(R.color.white)
            if(btn.id == view.id){
                selectedType = i
                btn.setBackgroundResource(R.color.grey)
            }
        }
    }

    fun showDatePicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            val prettyDate = Utils.toDateString(dayOfMonth, monthOfYear, year)
            binding.editDatePicker
                .setText(prettyDate)
            date.set(Calendar.YEAR, year)
            date.set(Calendar.MONTH, monthOfYear)
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        }, year, month, day)
        dpd.show()
    }

    fun showTimePicker(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)
        val dpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hour, minute ->

            val prettyTime = Utils.toTimeString(hour, minute)
            binding.editTimePicker
                .setText(prettyTime)
            date.set(Calendar.HOUR, hour)
            date.set(Calendar.MINUTE, minute)

        }, hour, minute, true)
        dpd.show()
    }

    fun createTask(view: View){
        val description = binding.descriptionInput.text.toString()
        val taskType = taskTypes[selectedType - 1]
        val priority = priorities[binding.priorityPicker.checkedRadioButtonId]
        val timeStr = binding.timeInput.text.toString()
        val time = if (!timeStr.isEmpty()) Integer.valueOf(timeStr) else 1
        val task = priority?.let { Task(0, description, date, time, it, taskType) }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("task", task)
        setResult(RESULT_OK, intent)
        finish()
    }

}