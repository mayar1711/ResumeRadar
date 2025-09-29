package com.example.resumeradar.utility

import android.content.Context
import android.net.Uri
import java.io.File

fun uriToFile(uri: Uri, context: Context): File {
    val inputStream = context.contentResolver.openInputStream(uri)!!
    val file = File(context.cacheDir, "cv_${System.currentTimeMillis()}.pdf")
    file.outputStream().use { outputStream ->
        inputStream.copyTo(outputStream)
    }
    return file
}
