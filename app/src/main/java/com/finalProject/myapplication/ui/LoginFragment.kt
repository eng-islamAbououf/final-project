package com.finalProject.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show

class LoginFragment : Fragment() ,View.OnClickListener{

    lateinit var email : EditText
    lateinit var password : EditText
    lateinit var loginBtn : Button

    lateinit var myView : View
    val EMAIL = "appssquare@gmail.org"
    val PASSWORD = "123456789"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView=view
        initView(view = view)
        loginBtn.setOnClickListener(this)

    }

    fun initView(view: View){
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        loginBtn= view.findViewById(R.id.login_btn)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.login_btn ->{
                var email = email.text.toString()
                var pass = password.text.toString()
                var x: Int = checkValidation(email,pass)
                when(x){
                    0->{

                        requireActivity().show(
                            "Please Enter Valid Email"
                            ,0
                        )

                    }
                    1->{
                        requireActivity().show(
                            "Please Enter Your Email"
                            ,0
                        )
                    }
                    2->{
                        requireActivity().show(
                            "Please Enter Your Password"
                            ,0
                        )
                    }
                    3->{
                        requireActivity().show(
                            "Login Succesfully"
                            ,0
                        )
                        Navigation.findNavController(myView).navigate(R.id.resturantFragment)
                    }

                    4->{

                        requireActivity().show(
                            "Wrong Password"
                            ,0
                        )
                    }
                    5->{
                        requireActivity().show(
                            "Wrong Email"
                            ,0
                        )
                    }

                }
            }
        }
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