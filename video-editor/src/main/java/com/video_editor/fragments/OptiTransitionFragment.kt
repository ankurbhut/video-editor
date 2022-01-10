package com.video_editor.fragments

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.video_editor.OptiVideoEditor
import com.video_editor.R
import com.video_editor.adapter.OptiTransitionAdapter
import com.video_editor.interfaces.OptiFFMpegCallback
import com.video_editor.interfaces.OptiFilterListener
import com.video_editor.utils.OptiConstant
import com.video_editor.utils.OptiUtils
import java.io.File
import java.util.*

class OptiTransitionFragment : BottomSheetDialogFragment(), OptiFilterListener, OptiFFMpegCallback {

    private var tagName: String = OptiTransitionFragment::class.java.simpleName
    private lateinit var rootView: View
    private lateinit var linearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager
    private lateinit var rvTransition: androidx.recyclerview.widget.RecyclerView
    private lateinit var ivClose: ImageView
    private lateinit var ivDone: ImageView
    private var videoFile: File? = null
    private var helper: OptiBaseCreatorDialogFragment.CallBacks? = null
    private var transitionList: ArrayList<String> = ArrayList()
    private lateinit var optiTransitionAdapter: OptiTransitionAdapter
    private var selectedTransition: String? = null
    private var mContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.opti_fragment_transition, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTransition = rootView.findViewById(R.id.rvTransition)
        ivClose = rootView.findViewById(R.id.iv_close)
        ivDone = rootView.findViewById(R.id.iv_done)
        linearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(activity!!.applicationContext)

        mContext = context

        ivClose.setOnClickListener {
            dismiss()
        }

        ivDone.setOnClickListener {
            optiTransitionAdapter.setTransition()

            if (selectedTransition != null) {
                dismiss()

                when (selectedTransition) {
                    "Fade in/out" -> {
                        applyTransitionAction()
                    }
                }
            }
        }

        linearLayoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        rvTransition.layoutManager = linearLayoutManager

        transitionList.add("Fade in/out")

        optiTransitionAdapter = OptiTransitionAdapter(transitionList, activity!!.applicationContext, this)
        rvTransition.adapter = optiTransitionAdapter
        optiTransitionAdapter.notifyDataSetChanged()
    }

    private fun applyTransitionAction() {
        //output file is generated and send to video processing
        val outputFile = OptiUtils.createVideoFile(context!!)
        Log.v(tagName, "outputFile: ${outputFile.absolutePath}")

        OptiVideoEditor.with(context!!)
            .setType(OptiConstant.VIDEO_TRANSITION)
            .setFile(videoFile!!)
             //.setFilter(command)
            .setOutputPath(outputFile.path)
            .setCallback(this)
            .main()

        helper?.showLoading(true)
    }

    override fun selectedFilter(filter: String) { //here transition
        selectedTransition = filter
    }

    fun setHelper(helper: OptiBaseCreatorDialogFragment.CallBacks) {
        this.helper = helper
    }

    fun setFilePathFromSource(file: File) {
        videoFile = file
    }

    override fun onProgress(progress: String) {
        Log.v(tagName, "onProgress()")
    }

    override fun onSuccess(convertedFile: File, type: String) {
        Log.v(tagName, "onSuccess()")
        helper?.showLoading(false)
        helper?.onFileProcessed(convertedFile)
    }

    override fun onFailure(error: Exception) {
        Log.v(tagName, "onFailure() ${error.localizedMessage}")
        Toast.makeText(mContext, "Video processing failed", Toast.LENGTH_LONG).show()
        helper?.showLoading(false)
    }

    override fun onNotAvailable(error: Exception) {
        Log.v(tagName, "onNotAvailable() ${error.localizedMessage}")
    }

    override fun onFinish() {
        Log.v(tagName, "onFinish()")
        helper?.showLoading(false)
    }
}