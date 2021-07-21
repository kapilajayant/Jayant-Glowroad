package com.jayant.glowroadjayant.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jayant.glowroadjayant.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.cardLinkedin.setOnClickListener(View.OnClickListener {
            val uri = String.format("https://www.linkedin.com/in/jayant-kapila-632985152/")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        })

        binding.cardPlay.setOnClickListener(View.OnClickListener {
            val uri = String.format("https://play.google.com/store/apps/developer?id=Jayant+Kapila")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        })

        binding.cardMail.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_SENDTO)
            i.type = "message/rfc822"
            //                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"Mittukwatra@gmail.com"});
            i.data = Uri.parse("mailto:kapilajayant@gmail.com")
            i.putExtra(Intent.EXTRA_SUBJECT, "Query mail")
            i.putExtra(
                Intent.EXTRA_TEXT,
                "Hi I have some queries regarding your app and would like to get some help, Thanks"
            )
            try {
                startActivity(Intent.createChooser(i, "Send mail..."))
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    this@AboutActivity,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding.cardGithub.setOnClickListener(View.OnClickListener {
            val uri = String.format("https://github.com/kapilajayant")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        })

    }
}