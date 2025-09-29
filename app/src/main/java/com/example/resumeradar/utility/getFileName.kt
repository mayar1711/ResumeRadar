package com.example.resumeradar.utility

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns

fun Context.getFileName(uri: Uri): String? {
    var name: String? = null
    val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (index >= 0) {
                name = it.getString(index)
            }
        }
    }
    return name
}
