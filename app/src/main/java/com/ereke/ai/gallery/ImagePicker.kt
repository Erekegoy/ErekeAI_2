package com.ereke.ai.gallery

import android.content.Intent

object ImagePicker {

    fun createIntent(): Intent {
        return Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
    }

}
