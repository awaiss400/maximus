package com.stone.maximustech

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.stone.maximustech.R
import com.stone.maximustech.databinding.ActivityMainBinding
import com.stone.maximustech.databinding.ActivitySplachBinding.inflate
import com.stone.maximustech.model.MyViewModel
import com.stone.maximustech.network.ApiStates
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var databinding:ActivityMainBinding
    val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      databinding=ActivityMainBinding.inflate(layoutInflater)
  setContentView(databinding.root)

        databinding.buttonrefresh.setOnClickListener {
            viewModel.getfact()
        }
        when (val result = viewModel.todolistresponse.value) {
            is ApiStates.Success -> {
                databinding.progressBars.visibility=View.GONE
                databinding.mycard.visibility=View.VISIBLE
                Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                databinding.tvTextss.text=result.data.fact
            }
            is ApiStates.Failure->{
databinding.tvTextss.text="ERROR"
                databinding.progressBars.visibility=View.GONE
                Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
            }
            is ApiStates.Loading->{
             databinding.mycard.visibility=View.GONE
                databinding.progressBars.visibility=View.VISIBLE
                Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onBackPressed() {
        val mBuilder = AlertDialog.Builder(this)
            .setTitle("Confirm")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes", null)
            .setNegativeButton("No", null)
            .show()
        val mPositiveButton = mBuilder.getButton(AlertDialog.BUTTON_POSITIVE)
        mPositiveButton.setOnClickListener {
            exitProcess(0)
        }
    }
}