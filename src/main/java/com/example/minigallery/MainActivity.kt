package com.example.minigallery

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var imageViewGallery: ImageView
    private lateinit var textViewCaption: TextView
    private lateinit var editTextUrl: EditText
    private lateinit var textViewStatus: TextView

    private val pictures = listOf(R.drawable.city_1, R.drawable.city_2, R.drawable.city_3)
    private val captions = listOf(R.string.caption_1, R.string.caption_2, R.string.caption_3)
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewGallery = findViewById(R.id.imageViewGallery)
        textViewCaption = findViewById(R.id.textViewCaption)
        editTextUrl = findViewById(R.id.editTextUrl)
        textViewStatus = findViewById(R.id.textViewStatus)

        findViewById<Button>(R.id.buttonNext).setOnClickListener {
            index = (index + 1) % pictures.size
            showPicture()
        }

        findViewById<Button>(R.id.buttonPrev).setOnClickListener {
            index = (index - 1 + pictures.size) % pictures.size
            showPicture()
        }

        findViewById<Button>(R.id.buttonLoadInternet).setOnClickListener {
            loadFromInternet()
        }
    }

    private fun showPicture() {
        imageViewGallery.setImageResource(pictures[index])
        textViewCaption.setText(captions[index])
        textViewStatus.setText(R.string.status_ready)
    }

    private fun loadFromInternet() {
        val address = editTextUrl.text.toString().trim()
        if (address.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_url, Toast.LENGTH_SHORT).show()
            return
        }

        Glide.with(this)
            .load(address)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_close_clear_cancel)
            .into(imageViewGallery)

        textViewStatus.text = "Загрузка..."
    }
}