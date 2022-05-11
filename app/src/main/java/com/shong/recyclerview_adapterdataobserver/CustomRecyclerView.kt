package com.shong.recyclerview_adapterdataobserver

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

//Fade 하단 제거 리싸이클러뷰
class CustomRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    //하단 Alpha 제거
    override fun getBottomFadingEdgeStrength(): Float {
        return 0f
    }

    //상단 Alpha 제거
//    override fun getTopFadingEdgeStrength(): Float {
//        return 0f
//    }
}