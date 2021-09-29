package com.finalProject.myapplication.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.finalProject.myapplication.Adapters.DataAdapter
import com.finalProject.myapplication.ClickableItem
import com.finalProject.myapplication.Models.DataModel
import com.finalProject.myapplication.Models.DataP
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show
import com.google.android.gms.location.LocationServices

class ResturantFragment : Fragment(),ClickableItem {


    lateinit var dataAdapter: DataAdapter
    lateinit var myData : DataModel
    lateinit var recyclerView: RecyclerView
    lateinit var myView : View
    lateinit var progressBar: ProgressBar

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

            progressBar.visibility = View.GONE
            myData=it
            install()
        })

        myViewModel.errors.observe(viewLifecycleOwner, Observer {
            requireActivity().show(
                it,
                0
            )
            fetchLocation(30.55258,31.00904)
        })
    }


    private fun initView(view: View){
        recyclerView=view.findViewById(R.id.resturant_recycler)
        progressBar= view.findViewById(R.id.progress)
    }

    private fun install(){
        dataAdapter = DataAdapter(myData,requireContext(),this)
        recyclerView.adapter = dataAdapter
    }

    override fun getPosition(pos: Int, l:Int) {
        when(l){
            1->{
                goToProfile(pos)
            }
            2->{
                goToGoogleMap(pos)
            }
            3 -> {
                fetchLocation(myData.data.get(pos).restaurant_lat.toDouble(),myData.data.get(pos).restaurant_long.toDouble())
            }
        }

    }

    private fun goToProfile(pos: Int){
        val action = ResturantFragmentDirections.actionResturantFragmentToProfileFragment(DataP(
                myData.data.get(pos).image,
                myData.data.get(pos).name,
                myData.data.get(pos).products))
        Navigation.findNavController(myView).navigate(action)
    }

    private fun goToGoogleMap(pos: Int){
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=${myData.data.get(pos).restaurant_lat},${myData.data.get(pos).restaurant_long}"))
        intent.setPackage("com.google.android.apps.maps")

        //check if user have google map app or not
        if (intent.resolveActivity(requireActivity().packageManager)!=null)
            requireActivity().startActivity(intent)
    }

    private fun goToMap(dLat:Double , dLng : Double,mLat:Double , mLng : Double){
        val intent = Intent(requireContext(),MapActivity::class.java)
        intent.putExtra("desLocationLat",dLat)
        intent.putExtra("desLocationLng",dLng)
        intent.putExtra("myLocationLat",mLat)
        intent.putExtra("myLocationLng",mLng)
        requireActivity().startActivity(intent)
    }

    private fun fetchLocation(dLat:Double , dLng : Double) {
        val currentLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        val task = currentLocation.lastLocation
        if (ActivityCompat.checkSelfPermission(requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        task.addOnSuccessListener {
            goToMap(dLat,dLng,it.latitude,it.longitude)
        }

    }
}