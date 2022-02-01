package com.example.simplletodo


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    var listOfTasks= mutableListOf<String>()
    lateinit var adapter: TaskToDo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskToDo.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // remove the item from the list
                listOfTasks.removeAt(position)
                //Notify the adapter that our data
                adapter.notifyDataSetChanged()

                saveItems()
            }


        }


        loadItems()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = TaskToDo(listOfTasks, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val inputTestFile = findViewById<EditText>(R.id.addTaskField)

        findViewById<Button>(R.id.button).setOnClickListener {
            // Group the text the user has inputtes
            val userInputtedTask = inputTestFile.text.toString()


            //Add the string to our list of tasks Listoftasks
            listOfTasks.add(userInputtedTask)

            //Notify the adapter that our data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            //Reset text field
            inputTestFile.setText("")
         saveItems()
        }
    }

// save the data
fun getDatafile(): File {
    // every line is gong to have repersent task
    return File(filesDir,"data.txt")
}

    //save the data by writing from a file
    fun  loadItems(){
        try{
        listOfTasks= FileUtils.readLines(getDatafile(), Charset.defaultCharset())
        } catch (inException:IOException){
            inException.printStackTrace()
        }

    }
    //create a method to get the file we need
    fun saveItems(){
        try{
            org.apache.commons.io.FileUtils.writeLines(getDatafile(), listOfTasks)
        }catch (inException: IOException){
          inException.printStackTrace()
        }

    }
    // save the item by writing them into our data file

}