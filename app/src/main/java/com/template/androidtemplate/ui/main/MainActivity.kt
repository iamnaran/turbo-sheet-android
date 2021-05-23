package com.template.androidtemplate.ui.main

import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.template.androidtemplate.R
import com.template.androidtemplate.data.model.Photos
import com.template.androidtemplate.ui.photos.adapter.PhotosAdapter
import com.template.androidtemplate.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_bottom_sheet_container.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setUpRecyclerView()
        doObserveWork()


    }

    private fun initViews() {

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navController = Navigation.findNavController(this, R.id.container)

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_notification, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        //        bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        val bottomSheetParent = findViewById<ConstraintLayout>(R.id.bottom_sheet_parent)

        val mBottomSheetBehaviour = BottomSheetBehavior.from(bottomSheetParent)


        val tv = TypedValue()
        if (theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
            val actionBarHeight = TypedValue.complexToDimensionPixelSize(
                tv.data,
                resources.displayMetrics
            )
            mBottomSheetBehaviour.peekHeight = actionBarHeight + actionBarHeight
        }


    }


    private fun setUpRecyclerView() {

        recycler_view_bottom_sheet.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )
        photosAdapter = PhotosAdapter()
        recycler_view_bottom_sheet.adapter = photosAdapter

    }


    private fun doObserveWork() {


        mainViewModel.getPhotosFeed().observe(this, Observer {

            when (it.status) {

                Status.SUCCESS -> {


                    val gson: Gson = Gson()
                    renderPhotosList(it.data!!)


                }

                Status.ERROR -> {


                }

                Status.LOADING -> {


                }


            }

        })


    }

    private fun renderPhotosList(photosList: List<Photos>) {

        photosAdapter.addData(photosList)
        photosAdapter.notifyDataSetChanged()

    }
}