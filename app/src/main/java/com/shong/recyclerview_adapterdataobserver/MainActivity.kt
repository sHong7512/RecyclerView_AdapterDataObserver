package com.shong.recyclerview_adapterdataobserver

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shong.recyclerview_adapterdataobserver.databinding.ActivityMainBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName + "_sHong"
    val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val MAX_UPDATE_COUNT = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val contentAdapter = ContentAdapter(mutableListOf())
        binding.contentRV.adapter = contentAdapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        val observer: RecyclerView.AdapterDataObserver = object : RecyclerView.AdapterDataObserver() {

            // when update use < notifyDataSetChanged() >
            override fun onChanged() {
                super.onChanged()
                Log.d(TAG,"onChanged()")
                MainScope().launch {
                    for(i in 0 until MAX_UPDATE_COUNT){
                        Log.d(TAG,"onChanged() < lastVisiblePosition: ${linearLayoutManager.findLastVisibleItemPosition()} MaxPosition: ${contentAdapter.itemCount - 1} >")
                        if(linearLayoutManager.findLastVisibleItemPosition() == contentAdapter.itemCount - 1) break
                        binding.contentRV.smoothScrollToPosition(contentAdapter.itemCount - 1)
                        delay(100L)
                    }
                }
            }
            // when update use < notifyItemInserted(contentList.size - 1) >
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                Log.d(TAG,"onItemRangeInserted()")
                MainScope().launch {
                    for(i in 0 until MAX_UPDATE_COUNT){
                        Log.d(TAG,"onItemRangeInserted() < lastVisiblePosition: ${linearLayoutManager.findLastVisibleItemPosition()} MaxPosition: ${contentAdapter.itemCount - 1} >")
                        if(linearLayoutManager.findLastVisibleItemPosition() == contentAdapter.itemCount - 1) break
                        binding.contentRV.smoothScrollToPosition(contentAdapter.itemCount - 1)
                        delay(100L)
                    }
                }
            }

        }
        contentAdapter.registerAdapterDataObserver(observer)
        binding.contentRV.layoutManager = linearLayoutManager

        binding.addTextButton.setOnClickListener {
            val str = binding.editText.text.toString()
            if(str.isEmpty()){
                Log.d(TAG, "editText is Empty!")
                return@setOnClickListener
            }
            binding.editText.setText("")
            contentAdapter.addList(str)
        }

    }
}