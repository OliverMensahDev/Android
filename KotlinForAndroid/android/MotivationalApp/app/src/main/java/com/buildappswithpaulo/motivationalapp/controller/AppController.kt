package com.buildappswithpaulo.motivationalapp.controller

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by paulodichone on 2/9/18.
 */
class AppController : Application() {
    private var mRequestQueue: RequestQueue? = null

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return this.mRequestQueue!!
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    //    public ImageLoader getImageLoader() {
    //        getRequestQueue();
    //        if (mImageLoader == null) {
    //            mImageLoader = new ImageLoader(this.mRequestQueue,
    //                    new LruBitmapCache());
    //        }
    //        return this.mImageLoader;
    //    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {
        val TAG = AppController::class.java
                .simpleName
        @get:Synchronized
        var instance: AppController? = null
            private set
    }
}
