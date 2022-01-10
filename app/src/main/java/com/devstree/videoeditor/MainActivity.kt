package com.devstree.videoeditor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.video_editor.VideoEditorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        val i = Intent(this@MainActivity, VideoEditorActivity::class.java)
        launchVideoEditorActivity.launch(i)
    }

    var launchVideoEditorActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent = result.data ?: return@registerForActivityResult
                val videoUrl = data.getStringExtra(VideoEditorActivity.VIDEO_RESULT_URL)
                    ?: return@registerForActivityResult
                Log.e("launchVideoEditorActivity", videoUrl)
            }
        }
}