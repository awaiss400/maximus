package com.stone.maximustech

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContentProviderCompat.requireContext
import com.stone.maximustech.model.MyViewModel
import com.stone.maximustech.network.ApiStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
val viewModel:MyViewModel by viewModels()
    var text:TextView=findViewById(R.id.tvText)
    var progressbar:ProgressBar=findViewById(R.id.progressBars)
    val btn:Button=findViewById(R.id.buttonrefresh)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getdata()
        btn.setOnClickListener {
            viewModel.getfact()
        }
    }

    private fun getdata() {
        when (val result = viewModel.todolistresponse.value) {
            is ApiStates.Sucess -> {
                progressbar.visibility=View.GONE
               text.text=result.data.toString()
            }
            is ApiStates.Failure->{
                progressbar.visibility=View.GONE
                Toast.makeText(this, "Network Error", Toast.LENGTH_SHORT).show()
            }
            is ApiStates.Loading->{
                progressbar.visibility=View.VISIBLE
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
