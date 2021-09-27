package com.finalProject.myapplication

import android.app.Activity
import android.widget.Toast

fun Activity.show(msg : String,len : Int){
    when(len){
        0->{
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
        1->{
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        }
    }
}