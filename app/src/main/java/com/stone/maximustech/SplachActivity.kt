 package com.stone.maximustech

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
 class SplachActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        val image:ImageView=findViewById(R.id.imgsplashs)
        image.alpha=0f
        image.animate().setDuration(1500).alpha(1f).withEndAction{
            val i=Intent(this,MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}