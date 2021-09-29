package com.finalProject.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finalProject.myapplication.Adapters.ProfileDataAdapter
import com.finalProject.myapplication.Models.Data
import com.finalProject.myapplication.Models.DataP
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : Fragment() {

    val args: ProfileFragmentArgs by navArgs()
    lateinit var image : CircleImageView
    lateinit var name : TextView
    lateinit var product : TextView
    lateinit var data: DataP

    lateinit var myRecyclerView: RecyclerView
    lateinit var productAdapter:ProfileDataAdapter
    lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view = view)

        data=args.data
        Glide.with(View(context)).load(data.image).placeholder(R.drawable.ic_launcher_background).into(image)
        name.text=data.name

        if (data.products.isEmpty()){
            product.text="No Products"
        }else {
            install()
        }
    }

    private fun initView(view:View){
        myRecyclerView=view.findViewById(R.id.profile_recycler)
        name=view.findViewById(R.id.profile_name)
        product=view.findViewById(R.id.profile_product)
        image=view.findViewById(R.id.profile_image)
    }
    private fun install(){
        layoutManager= GridLayoutManager(requireContext(),2)
        productAdapter= ProfileDataAdapter(data.products,requireContext())
        myRecyclerView.adapter=productAdapter
        myRecyclerView.layoutManager=layoutManager
    }
}