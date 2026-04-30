package com.example.rangecalculator2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Global constant for petrol price (e.g., R22.50)
    private val petrolPrice = 22.50
    private val tankSize = 15.0  // 15 Liter tank

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find all views (like lecturer's findViewById)
        val edtFuelAmount = findViewById<EditText>(R.id.edtFuelAmount)
        val edtConsumption = findViewById<EditText>(R.id.edtConsumption)
        val btnCalculateRange = findViewById<Button>(R.id.btnCalculateRange)
        val tvResults = findViewById<TextView>(R.id.tvResults)

        // Set click listener for button
        btnCalculateRange.setOnClickListener {
            // Get text from EditText fields
            val fuelStr = edtFuelAmount.text.toString()
            val consStr = edtConsumption.text.toString()

            // Check if both fields have values (not empty)
            if (fuelStr.isNotEmpty() && consStr.isNotEmpty()) {
                // Convert strings to numbers safely
                val fuel = fuelStr.toDoubleOrNull()
                val consumption = consStr.toDoubleOrNull()

                // Check if numbers are valid and positive
                if (fuel != null && consumption != null && fuel > 0 && consumption > 0) {

                    // Call calculateRange function
                    val range = calculateRange(fuel, consumption)

                    // Calculate liters needed to fill tank
                    val litersNeeded = tankSize - fuel

                    // Call calculateRefillCost function
                    val cost = calculateRefillCost(litersNeeded)

                    // Display results to user
                    tvResults.text = "Range: $range km\nRefill Cost: R$cost"

                } else {
                    // Error for negative or zero values
                    tvResults.text = "Please enter valid values. Fuel and Consumption must be greater than 0."
                }
            } else {
                // Error for empty fields
                tvResults.text = "Don't be a chop, enter some values!"
            }
        }
    }

    // Function to calculate range
    private fun calculateRange(fuel: Double, consumption: Double): Double {
        // Range = fuel × consumption (km per litre)
        return fuel * consumption
    }

    // Function to calculate refill cost
    private fun calculateRefillCost(litersNeeded: Double): Double {
        // Cost = liters needed × price per liter
        return litersNeeded * petrolPrice
    }
}
