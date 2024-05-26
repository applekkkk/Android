package com.example.lk_week8

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView

class MainActivity_Lk : AppCompatActivity() {
    private val mediaPlayer=MediaPlayer()
    var state=0
    val IMAGE=0
    val AUDIO=1
    val VIDEO=2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val browse:Button=findViewById(R.id.browse)
        val play:Button=findViewById(R.id.play)
        val pause:Button=findViewById(R.id.pause)
        val replay:Button=findViewById(R.id.replay)
        val video:VideoView=findViewById(R.id.video)
        val image:ImageView=findViewById(R.id.image)
        image.setImageResource(R.drawable.ic_launcher_foreground)
        browse.setOnClickListener {
            val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="*/*"
            startActivityForResult(intent,1)
        }
        play.setOnClickListener {
            if(state==AUDIO){
                if(!mediaPlayer.isPlaying)
                    mediaPlayer.start()
            }else if(state==VIDEO){
                if(!video.isPlaying)
                    video.start()
            }
        }
        pause.setOnClickListener {
            if(state==AUDIO){
                if(mediaPlayer.isPlaying)
                    mediaPlayer.pause()
                else
                    mediaPlayer.start()
            }else if(state==VIDEO){
                if(video.isPlaying)
                    video.pause()
                else
                    video.start()
            }
        }
        replay.setOnClickListener {
            if(state==AUDIO){
                mediaPlayer.seekTo(0)
                mediaPlayer.start()
            }else if(state==VIDEO){
                video.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(state==AUDIO){
            mediaPlayer.stop()
            mediaPlayer.release()
        }else if(state==VIDEO){
            val video:VideoView=findViewById(R.id.video)
            video.suspend()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val video:VideoView=findViewById(R.id.video)
        val image:ImageView=findViewById(R.id.image)
        when(requestCode){
            1->{
                if(resultCode==Activity.RESULT_OK&&data!=null){
                    data.data?.let {
                        val filename=it.pathSegments[1]
                        Log.d("Main",filename)
                        val lastDot = filename.lastIndexOf(":")
                        val extension=filename.substring(0,lastDot).uppercase()
                        Log.d("Main",extension)
                        if(extension=="VIDEO"){
                            video.setVideoURI(it)
                            image.visibility=View.INVISIBLE
                            video.visibility=View.VISIBLE
                            state=VIDEO
                        }else if(extension=="IMAGE"){
                            image.setImageURI(it)
                            video.visibility=View.INVISIBLE
                            image.visibility=View.VISIBLE
                            state=IMAGE
                        }else if(extension=="AUDIO"){
                            video.visibility=View.INVISIBLE
                            image.visibility=View.VISIBLE
                            image.setImageResource(R.drawable.sound)
                            state=AUDIO
                            mediaPlayer.setDataSource(this,it)
                            mediaPlayer.prepare()
                        }
                    }
                }
            }
        }
    }
}