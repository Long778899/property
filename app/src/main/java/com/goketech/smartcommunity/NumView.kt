package com.goketech.smartcommunity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class NumView : AppCompatImageView {
    //要显示的数量数量
    private var num = 0
    //红色圆圈的半径
    private var radius: Float = 0.toFloat()
    //圆圈内数字的半径
    private var textSize: Float = 0.toFloat()
    //右边和上边内边距
    var paddingRight: Int? = 0
    var paddingTop: Int? = 0
    constructor(context: Context): super(context){

    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs){

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs, defStyleAttr){

    }



    //设置显示的数量
    fun setNum(num: Int) {
        this.num = num
        //重新绘制画布
        invalidate()
    }



    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (num > 0) {
            //初始化半径
            radius = (width / 2).toFloat()
            //初始化字体大小
            textSize = if (num < 10) radius + 5 else radius
            //初始化边距
            paddingRight = getPaddingRight()
            paddingTop = getPaddingTop()
            //初始化画笔
            val paint = Paint()
            //设置抗锯齿
            paint.isAntiAlias = true
            //设置颜色为红色0xffff4444
            paint.color = resources.getColor(R.color.brown)
            //设置填充样式为充满
            paint.style = Paint.Style.FILL
            //画圆
            canvas!!.drawCircle(
                width.toFloat() - radius - (paddingRight!! / 2).toFloat(),
                radius + paddingTop!! / 2,
                radius,
                paint
            )
            //设置颜色为白色
            paint.color = -0x1
            //设置字体大小
            paint.textSize = textSize
            //画数字
            canvas!!.drawText(
                "" + if (num < 99) num else 99,
                if (num < 10)
                    width.toFloat() - radius - textSize / 4 - (paddingRight!! / 2).toFloat()
                else
                    width.toFloat() - radius - textSize / 2 - (paddingRight!! / 2).toFloat(),
                radius + textSize / 3 + (paddingTop!!/ 2).toFloat(), paint
            )
        }
    }
}