package com.template.androidtemplate.ui.sheet

import android.app.Dialog
import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.card.MaterialCardView
import com.template.androidtemplate.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ErrorDialogFragment constructor(private val selectedLanguage: String) :
    BottomSheetDialogFragment(), View.OnClickListener {

    private var dialog: BottomSheetDialog? = null
    private var contextThemeWrapper: ContextThemeWrapper? = null
    private var nepaliLCardView: MaterialCardView? = null
    private var englishCardView: MaterialCardView? = null

    companion object {

        fun newInstance(userSelectedLanguage: String) = ErrorDialogFragment(userSelectedLanguage)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contextThemeWrapper = ContextThemeWrapper(activity, R.style.BottomSheetTheme)
        val view: View = inflater.cloneInContext(contextThemeWrapper)
            .inflate(R.layout.dialog_error_fragment, container, true)
        initaliseView(view)
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        dialog!!.setCancelable(true)
        dialog!!.setCanceledOnTouchOutside(true)

        //this is setPosition is for testing window parameters
//        setPosition(dialog!!)

        return dialog as BottomSheetDialog
    }


    private fun setPosition(dialog: BottomSheetDialog) {

        val window = dialog.window
        if (window != null){
            val params = window.attributes
            params.verticalMargin = 0.8f
            params.gravity = Gravity.TOP or Gravity.START
            params.x = 1000 //x position
            params.y = 1000 //y position
            window.attributes = params

        }

    }


    private fun initaliseView(view: View) {
        englishCardView = view.findViewById(R.id.english_layout)
        nepaliLCardView = view.findViewById(R.id.logout_layout)


        englishCardView!!.setOnClickListener(this)
        nepaliLCardView!!.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.english_layout -> if (dialog != null) {


                dialog!!.dismiss()
            }
            R.id.logout_layout -> if (dialog != null) {

                dialog!!.dismiss()
            }
        }
    }


}