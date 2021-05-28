package com.template.androidtemplate.ui.main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.template.androidtemplate.R
import com.template.androidtemplate.ui.sheet.ErrorDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View =
            inflater.inflate(R.layout.fragment_home, container, false)


        initView(rootView)
        return rootView
    }

    private fun initView(rootView: View) {

        val button:Button = rootView.findViewById(R.id.btn_press)
        button.setOnClickListener{

            showErrorDialog()

        }

    }


    private fun showErrorDialog() {

        val languageDialogFragment = ErrorDialogFragment.newInstance("english")
        languageDialogFragment.show(requireFragmentManager(), "language")


    }



}