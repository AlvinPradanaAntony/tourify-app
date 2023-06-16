package com.devcode.tourifyapp.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ActivityMapsBinding
import com.devcode.tourifyapp.databinding.ItemLayoutBottomDialogMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding
    private lateinit var binding2: ItemLayoutBottomDialogMapBinding
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        setSupportActionBar(binding.toolbarId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Maps"
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fr_map_list) as SupportMapFragment
        try {
            mapFragment.getMapAsync(this)
        } catch (e: Exception) {
            Snackbar.make(binding.root, "Error SetupView: ${e.message}", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setupAction(){
        binding.styleMapButton.setOnClickListener {
            binding2 = ItemLayoutBottomDialogMapBinding.inflate(layoutInflater)
            val bottomSheetDialog = BottomSheetDialog(this@MapsActivity)
            bottomSheetDialog.setContentView(binding2.root)
            bottomSheetDialog.show()

            binding2.styleNormal.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            binding2.styleSatellite.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            binding2.styleTerrain.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            binding2.styleHybrid.setOnClickListener {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setPadding(0,150,0,0)
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        defaultMarker()
        getMyLocation()
        setMapStyle()
    }

    private fun defaultMarker() {
        val lat = intent.getStringExtra(EXTRA_LATITUDE)
        val long = intent.getStringExtra(EXTRA_LONGITUDE)
        val name = intent.getStringExtra(NAME)

        if (lat != null && long != null) {
            val dicodingSpace = LatLng(lat.toDouble(), long.toDouble())
            mMap.addMarker(
                MarkerOptions()
                    .position(dicodingSpace)
                    .title(name)
            )
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dicodingSpace, 15f))
        }
//        val indonesia = LatLng(EXTRA_LATITUDE, EXTRA_LONGITUDE)
//        mMap.addMarker(MarkerOptions().position(indonesia).title("Indonesia"))
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(indonesia, 4f))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            try {
                if (isGranted) {
                    getMyLocation()
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
            mMap.setOnMyLocationButtonClickListener {
                try {
                    val myLocation = mMap.myLocation
                    if (myLocation != null) {
                        val myLatLng = LatLng(myLocation.latitude, myLocation.longitude)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 18f))
                        true
                    } else {
                        Snackbar.make(binding.root, R.string.error_message_location, Snackbar.LENGTH_LONG).show()
                        false
                    }
                } catch (e: Exception) {
                    Snackbar.make(binding.root, R.string.error_message_catch, Snackbar.LENGTH_LONG).show()
                    false
                }
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun setMapStyle() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            try {
                val success = mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
                if (!success) {
                    Snackbar.make(binding.root, R.string.error_message_style, Snackbar.LENGTH_LONG).show()
                }
            } catch (exception: Resources.NotFoundException) {
                Snackbar.make(binding.root, "${R.string.error_message_catch}${exception}", Snackbar.LENGTH_LONG).show()
                Log.e(TAG, R.string.error_message_catch.toString(), exception)
            }
        }
    }

    private fun getAddressName(lat: Double, lon: Double): String? {
        var addressName: String? = null
        val geocoder = Geocoder(this@MapsActivity, Locale.getDefault())
        try {
            val list = geocoder.getFromLocation(lat, lon, 1)
            if (list != null && list.size != 0) {
                addressName = list[0].getAddressLine(0)
                Log.d(TAG, "getAddressName: $addressName")
            }
        } catch (e: IOException) {
            Log.e("GetAddressName", "GetAddressName: $e")
        }
        return addressName
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "MapsActivity"
        private const val EXTRA_LATITUDE = "LAT"
        private const val EXTRA_LONGITUDE = "LONG"
        private const val NAME = "name"
    }
}