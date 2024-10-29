package com.example.gmaillayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.content.res.AppCompatResources
import java.util.Arrays
import java.util.Collections
import java.util.Random
import java.util.Stack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gmails = mutableListOf<GmailModel>()
        repeat(10) {
            val name = randomString(10)
            val msg = randomString(100)
            val avatar = name[0]
            val hour = getRandomNumber(0,23)
            val minute = getRandomNumber(0,59)
            var time: String
            if (hour < 10)
                time = "0$hour:"
            else
                time = "$hour:"
            if (minute < 10)
                time += "0$minute"
            else
                time += "$minute"

            val id = resources.getIdentifier(avatar.lowercase().toString()+"_foreground", "drawable", packageName)
            gmails.add(
                GmailModel(
                    AppCompatResources.getDrawable(this.baseContext, id), getRandomColor(),
                    name, msg, time, false
                )
            )
        }

        val adapter = GmailAdapter(gmails)

        val listGmails = findViewById<ListView>(R.id.list)
        listGmails.adapter = adapter
    }

    fun randomString(stringLength: Int): String {
        val list = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray()
        var randomS = ""
        for (i in 1..stringLength) {
            randomS += list[getRandomNumber(0, list.size - 1)]
        }
        return randomS
    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return Random().nextInt((max - min) + 1) + min
    }

    private val recycle: Stack<Int> = Stack()
    private val colors: Stack<Int> = Stack()
    init {
        recycle.addAll(
            Arrays.asList(
                // ARGB hex to int >> (0xFFEE5670.toInt(),...)
                -0xbbcca, -0x16e19d, -0x63d850, -0x98c549,
                -0xc0ae4b, -0xde690d, -0xfc560c, -0xff432c,
                -0xff6978, -0xb350b0, -0x743cb6, -0x3223c7,
                -0x14c5, -0x3ef9, -0x6800, -0xa8de,
                -0x86aab8, -0x616162, -0x9f8275, -0xcccccd
            )
        )
    }

    fun getRandomColor(): Int {
        if (colors.size == 0)
            while (!recycle.isEmpty()) colors.push(recycle.pop())
        Collections.shuffle(colors)
        val c = colors.pop()
        recycle.push(c)
        return c
    }
}