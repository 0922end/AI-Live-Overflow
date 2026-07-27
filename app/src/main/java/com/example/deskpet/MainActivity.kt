package com.example.deskpet

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.deskpet.service.OverlayService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            checkAndStart()
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            stopService(Intent(this, OverlayService::class.java))
        }
    }

    private fun checkAndStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            !Settings.canDrawOverlays(this)
        ) {
            AlertDialog.Builder(this)
                .setTitle("需要悬浮窗权限")
                .setMessage("桌宠需要悬浮窗权限才能显示在屏幕上")
                .setPositiveButton("去授权") { _, _ ->
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivity(intent)
                }
                .setNegativeButton("取消", null)
                .show()
        } else {
            startService(Intent(this, OverlayService::class.java))
        }
    }
}
