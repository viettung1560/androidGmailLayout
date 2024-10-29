package com.example.gmaillayout

import android.graphics.drawable.Drawable

data class GmailModel (val drawable: Drawable?, val color: Int, val name: String, val msg: String, val time: String, var star: Boolean){
}