package com.finalProject.myapplication.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show

class LoginActivity : AppCompatActivity() ,View.OnClickListener {

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var loginBtn : Button
    lateinit var sharedPreferences : SharedPreferences
    lateinit var edit : SharedPreferences.Editor

    val EMAIL = "appssquare@gmail.org"
    val PASSWORD = "123456789"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences= getSharedPreferences("name",0)
        val sEmail:String = sharedPreferences.getString("email","no").toString()
        val sPass:String = sharedPreferences.getString("pass","no").toString()
        if (sEmail.equals(EMAIL)&&sPass.equals(PASSWORD)){
            goToMain()
        }

        initView()
        loginBtn.setOnClickListener(this)
    }

    private fun initView() {
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn= findViewById(R.id.login_btn)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.login_btn ->{
                val mEmail = email.text.toString().trim()
                val mPass = password.text.toString().trim()
                val x: Int = checkValidation(mEmail,mPass)
                when(x){
                    0->{
                        show(
                                "Please Enter Valid Email"
                                ,1
                        )

                    }
                    1->{
                        show(
                                "Please Enter Your Email"
                                ,1
                        )
                        email.setError("Please Enter Your Email")

                    }
                    2->{
                        show(
                                "Please Enter Your Password"
                                ,0
                        )
                        password.setError("Please Enter Your Password")

                    }
                    3->{
                        show(
                                "Login Succesfully"
                                ,0
                        )
                        edit = sharedPreferences.edit()
                        edit.putString("email",mEmail)
                        edit.putString("pass",mPass)
                        edit.commit()
                        goToMain()
                    }

                    4->{
                        show(
                                "Wrong Password"
                                ,0
                        )
                    }
                    5->{
                        show(
                                "Wrong Email"
                                ,0
                        )
                    }
                }
            }
        }
    }


    private fun goToMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun checkValidation(email: String, pass: String): Int {
        var x = 0
        if (email.equals(""))
            x = 1
        else if (pass.equals(""))
            x = 2
        else if (email.equals(EMAIL)){
            if (pass.equals(PASSWORD))
                x=3
            else
                x=4
        }
        else if(!email.equals(EMAIL))
            x =5
        return x
    }
}