package com.finalProject.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finalProject.myapplication.Api.Connection
import com.finalProject.myapplication.Models.Data
import com.finalProject.myapplication.Models.DataModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    var productData: MutableLiveData<DataModel> = MutableLiveData()
    var errors: MutableLiveData<String> = MutableLiveData()

    fun getProductFromDatabase() {
        Connection
            .calls
            .getData()
            .enqueue(object : Callback<DataModel> {

                override fun onResponse(
                    call: Call<DataModel>,
                    response: Response<DataModel>
                ) {
                    when (response.code()) {
                        200 -> {
                            var data = response.body()
                            productData.postValue(data)
                        }

                        else -> {
                            val errorJsonString = response?.errorBody()?.string()
                            val json: JSONObject = JSONObject(errorJsonString)
                            val msg = json.getString("message")
                            errors.postValue(msg)
                        }
                    }
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    errors.postValue(t.message)
                }
            })
    }

}