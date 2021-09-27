package com.finalProject.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.finalProject.myapplication.Adapters.DataAdapter
import com.finalProject.myapplication.ClickableItem
import com.finalProject.myapplication.Models.DataModel
import com.finalProject.myapplication.Models.DataP
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show

class ResturantFragment : Fragment(),ClickableItem {


    lateinit var dataAdapter: DataAdapter
    lateinit var myData : DataModel
    lateinit var recyclerView: RecyclerView
    lateinit var myView : View

    val myViewModel : MyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resturant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view
        initView(view = view)

        myViewModel.getProductFromDatabase()
        myViewModel.productData.observe(viewLifecycleOwner, Observer {

            myData=it
            install()
        })

        myViewModel.errors.observe(viewLifecycleOwner, Observer {
            requireActivity().show(
                it,
                0
            )
        })
    }



    private fun initView(view: View){
        recyclerView=view.findViewById(R.id.resturant_recycler)
    }

    private fun install(){
        dataAdapter = DataAdapter(myData,requireContext(),this)
        recyclerView.adapter = dataAdapter
    }

    override fun getPosition(pos: Int) {
        var name = myData.data.get(pos).name
        var image = myData.data.get(pos).image
        var product = myData.data.get(pos).products
        val action = ResturantFragmentDirections.actionResturantFragmentToProfileFragment(DataP(image,name,product))
        Navigation.findNavController(myView).navigate(action)
    }
}