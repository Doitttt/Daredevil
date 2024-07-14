package com.nyonz.daredevil

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.nyonz.daredevil.databinding.ActivityMainBinding
import com.nyonz.daredevil.viewmodel.SuperheroViewModel
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SuperheroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accessToken = "97fc4e432d519a234d81dcebea1e5eb6"
        val superheroId = 201

        viewModel.fetchSuperhero(accessToken, superheroId)

        viewModel.superhero.observe(this, Observer { superhero ->
            if (superhero != null) {
                Log.d("MainActivity", "Superhero data received: $superhero")
                val imageView: ImageView = binding.root.findViewById(R.id.ivSuperheroImage)
                // Use Glide to load the image
                Glide.with(this)
                    .load(superhero.image.url)
                    .apply(RequestOptions().override(500, 500).placeholder(R.drawable.ic_launcher_foreground))
                    .transform(CircleCrop())
                    .into(imageView)
                binding.biographyCard.findViewById<TextView>(R.id.tvFullName).text = "Full Name: ${superhero.biography.fullName}"
                binding.biographyCard.findViewById<TextView>(R.id.tvFirstAppearance).text = "First Appearance: ${superhero.biography.firstAppearance}"
                binding.biographyCard.findViewById<TextView>(R.id.tvAlignment).text = "Alignment: ${superhero.biography.alignment}"
                binding.biographyCard.findViewById<TextView>(R.id.tvRelatives).text = "Relatives: ${superhero.connections.relatives}"
                binding.biographyCard.findViewById<TextView>(R.id.tvGroup).text = "Grouop Affiliation: ${superhero.connections.groupaffiliation}"
                binding.powerstatsCard.findViewById<TextView>(R.id.tvIntelligence).text = "Intelligence: ${superhero.powerstats.intelligence}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbIntelligence).progress = superhero.powerstats.intelligence ?: 0
                binding.powerstatsCard.findViewById<TextView>(R.id.tvStrength).text = "Strength: ${superhero.powerstats.strength}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbStrength).progress = superhero.powerstats.strength ?: 0
                binding.powerstatsCard.findViewById<TextView>(R.id.tvPower).text = "Power: ${superhero.powerstats.power}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbPower).progress = superhero.powerstats.power ?: 0
                binding.powerstatsCard.findViewById<TextView>(R.id.tvDurability).text = "Durability: ${superhero.powerstats.durability}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbDurability).progress = superhero.powerstats.durability ?: 0
                binding.powerstatsCard.findViewById<TextView>(R.id.tvSpeed).text = "Speed: ${superhero.powerstats.speed}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbSpeed).progress = superhero.powerstats.speed ?: 0
                binding.powerstatsCard.findViewById<TextView>(R.id.tvCombat).text = "Combat: ${superhero.powerstats.combat}"
                binding.powerstatsCard.findViewById<ProgressBar>(R.id.pbCombat).progress = superhero.powerstats.combat ?: 0
                binding.appearanceCard.findViewById<TextView>(R.id.tvGender).text = "Gender: ${superhero.appearance.gender}"
                binding.appearanceCard.findViewById<TextView>(R.id.tvRace).text = "Race: ${superhero.appearance.race}"
                binding.appearanceCard.findViewById<TextView>(R.id.tvHeight).text = "Height: ${superhero.appearance.height}"
                binding.appearanceCard.findViewById<TextView>(R.id.tvWeight).text = "Weight: ${superhero.appearance.weight}"
                binding.appearanceCard.findViewById<TextView>(R.id.tvEyeColor).text = "Eye Color: ${superhero.appearance.eyecolor}"
                binding.appearanceCard.findViewById<TextView>(R.id.tvHairColor).text = "Hair Color: ${superhero.appearance.haircolor}"
            } else {
                Log.d("MainActivity", "No superhero data received")
            }
        })
    }
}