package com.example.lk_week9

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    val fromAlbum=1
    lateinit var bitmap: Bitmap
    lateinit var lightnessBinder:LkService.LightnessBinder
    lateinit var lkReceiver: LkReceiver
    private val connection=object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            lightnessBinder=service as LkService.LightnessBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter=IntentFilter()
        intentFilter.addAction("com.example.lk_week9.renewImage")
        lkReceiver=LkReceiver()
        registerReceiver(lkReceiver,intentFilter)
        Log.d("Main",packageName)
        val intent=Intent(this,LkService::class.java)
        bindService(intent,connection,Context.BIND_AUTO_CREATE)
        val selectImage:Button=findViewById(R.id.selectImage)
        selectImage.setOnClickListener {
            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="image/*"
            startActivityForResult(intent,fromAlbum)
        }
        val alterlightness:Button=findViewById(R.id.alterlightness)
        val seekBar:SeekBar=findViewById(R.id.seekBar)
        alterlightness.setOnClickListener {
            bitmap=lightnessBinder.alterLightness(this,bitmap,seekBar.progress.toDouble()/50)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            fromAlbum->{
                if(resultCode==Activity.RESULT_OK&&data!=null){
                    data.data?.let { uri->
                        val bitmap1=getBitmapFromUri(uri)
                        if(bitmap1!=null)
                            bitmap=bitmap1
                        val image:ImageView=findViewById(R.id.image)
                        image.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(lkReceiver)
    }

    private fun getBitmapFromUri(uri: Uri)=contentResolver
        .openFileDescriptor(uri,"r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }
    inner class LkReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d("Main","received")
            val image:ImageView=findViewById(R.id.image)
            image.setImageBitmap(bitmap)
        }
    }
}
