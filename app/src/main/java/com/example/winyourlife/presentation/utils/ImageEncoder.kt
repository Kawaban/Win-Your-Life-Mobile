package com.example.winyourlife.presentation.utils

import android.content.Context
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.InputStream


class ImageEncoder{
    fun encodeImage(image: Uri?, context: Context): ByteArray {
        return try {
            if(image == null) return ByteArray(0)

            val inputStream: InputStream? = context.contentResolver.openInputStream(image)

            val outputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream?.read(buffer).also { bytesRead = it ?: -1 } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }

            inputStream?.close()

            outputStream.toByteArray()

        } catch (e: Exception) {
            println(e.printStackTrace())
            ByteArray(0)
        }
    }
}