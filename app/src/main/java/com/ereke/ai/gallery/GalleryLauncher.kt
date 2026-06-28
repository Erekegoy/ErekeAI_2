package com.ereke.ai.gallery

import android.net.Uri

object GalleryLauncher {

    private var callback: ((Uri?) -> Unit)? = null

    fun setCallback(listener: (Uri?) -> Unit) {
        callback = listener
    }

    fun onImageSelected(uri: Uri?) {
        callback?.invoke(uri)
    }
}
