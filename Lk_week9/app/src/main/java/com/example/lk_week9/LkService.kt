package com.example.lk_week9

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.Binder
import android.os.IBinder
import android.util.Log

class LkService : Service() {
    val lightnessBinder=LightnessBinder()
    class LightnessBinder():Binder(){
        fun alterLightness(activity: Activity, bitmap: Bitmap,lightness:Double):Bitmap{
            Log.d("Service","changeLightness")
            val newbitmap=bitmap.applyGammaCorrection(lightness)
            val intent=Intent("com.example.lk_week9.renewImage")
            intent.setPackage(activity.packageName)
            activity.sendBroadcast(intent)
            return newbitmap
        }
    }
    override fun onBind(intent: Intent): IBinder {
        return lightnessBinder
    }


}
