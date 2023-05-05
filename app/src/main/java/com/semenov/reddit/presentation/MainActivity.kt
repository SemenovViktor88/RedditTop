package com.semenov.reddit.presentation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.semenov.reddit.R
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.presentation.main.MainFragment


class MainActivity : AppCompatActivity() {

	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		supportFragmentManager.beginTransaction().
			add(R.id.frameLayoutMainActivity, MainFragment()).commit()
	}
}
