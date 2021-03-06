package com.video_editor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.video_editor.fragments.OptiMasterProcessorFragment

class VideoEditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.main_screen)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, OptiMasterProcessorFragment()).commit()
    }


    companion object {
        const val VIDEO_RESULT_URL = "video_result_url"
    }
}
