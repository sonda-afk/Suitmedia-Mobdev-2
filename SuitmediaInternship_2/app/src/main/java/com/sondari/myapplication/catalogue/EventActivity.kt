package com.sondari.myapplication.catalogue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sondari.myapplication.DataDummy
import com.sondari.myapplication.R
import com.sondari.myapplication.adapter.AdapterEvent
import com.sondari.myapplication.api.data.ModelEvent
import com.sondari.myapplication.databinding.ActivityEventBinding
import com.sondari.myapplication.maps.MapsFragment

class EventActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityEventBinding
    private lateinit var adapterEvent : AdapterEvent
    private val resultCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maps.setOnClickListener(this)
        binding.back.setOnClickListener(this)

        showRecycleViewEvent()
    }

    private fun showRecycleViewEvent() {

        adapterEvent = AdapterEvent()
        adapterEvent.setEvent(DataDummy.generateDataDummy())

        binding.rvEvent.apply {
            layoutManager = LinearLayoutManager(this@EventActivity)
            setHasFixedSize(true)
            adapter = adapterEvent

            adapterEvent.setOnItemClickCallback(object : AdapterEvent.OnItemClickCallback {
                override fun onItemClicked(data: ModelEvent) {
                    val resultIntent = Intent()
                    resultIntent.putExtra("EVENT NAME", data.name)
                    setResult(resultCode, resultIntent)
                    finish()
                }

            })

        }


    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.back ->{
                finish()
            }
            R.id.maps->{
                val intent = Intent(this, MapsFragment::class.java)
                startActivity(intent)
            }
        }
    }
}