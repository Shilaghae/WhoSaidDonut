package com.whosaiddonut.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.whosaiddonut.R

class DonutView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG);
    private val roundPaint = Paint(Paint.ANTI_ALIAS_FLAG);
    private var rectF = RectF()
    private var externalRectF = RectF()
    private var upperTextPaint = TextPaint()
    private var scoreTextPaint = TextPaint()
    private var bottomTextPaint = TextPaint()
    private val strokeWidth = 15f
    private val externalStrokeWidth = 6f
    private val startAngle = -90

    companion object {
        private val padding = 30
        private var maxScore = 0f
        private const val animationDuration : Long = 1000
    }

    var score = 0f
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    init {
        val attributes = context.theme.obtainStyledAttributes(attributeSet, R.styleable.DonutView, 0, 0)
        try {
            val donutProgressIndicatorColor = attributes.getColor(R.styleable.DonutView_donutViewScoreIndicatorColor,
                    Color.YELLOW)
            val textColor = attributes.getColor(R.styleable.DonutView_donutViewTextColor, Color.GRAY)
            val donutTextSize = attributes.getDimensionPixelSize(R.styleable.DonutView_donutViewTextSize,
                    resources.getDimensionPixelSize(R.dimen.donut_text_textSize))
            val scoreTextSize = attributes.getDimensionPixelSize(R.styleable.DonutView_donutViewScoreSize,
                    resources.getDimensionPixelSize(R.dimen.donut_score_textSize))
            val newMaxScore = attributes.getFloat(R.styleable.DonutView_donutViewMaxScore, maxScore)

            maxScore = newMaxScore
            foregroundPaint.color = donutProgressIndicatorColor
            upperTextPaint.color = textColor
            scoreTextPaint.color = donutProgressIndicatorColor
            bottomTextPaint.color = textColor

            upperTextPaint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    donutTextSize.toFloat(),
                    resources.displayMetrics)
            scoreTextPaint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    scoreTextSize.toFloat(),
                    resources.displayMetrics)
            bottomTextPaint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    donutTextSize.toFloat(),
                    resources.displayMetrics)

        } finally {
            attributes.recycle()
        }

        roundPaint.color = Color.LTGRAY
        roundPaint.style = Paint.Style.STROKE
        roundPaint.strokeWidth = externalStrokeWidth
        foregroundPaint.style = Paint.Style.STROKE
        foregroundPaint.strokeWidth = strokeWidth
        foregroundPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val angle = 360 * score / maxScore
        canvas.drawOval(externalRectF, roundPaint)
        canvas.drawArc(rectF, startAngle.toFloat(), angle, false, foregroundPaint)

        val fa = scoreTextPaint.getFontMetrics()
        val heightScoreText = fa.descent - fa.ascent
        val min = Math.min(measuredHeight, measuredWidth)

        val yourCreditScore = resources.getString(R.string.your_credit_score_is)
        canvas.drawText(yourCreditScore,
                (min - upperTextPaint.measureText(yourCreditScore)) / 2.0f,
                ((min / 2.0f) - (heightScoreText / 2)) - padding,
                upperTextPaint)

        val scoreToString = score.toInt().toString()
        canvas.drawText(scoreToString,
                (min - scoreTextPaint.measureText(scoreToString)) / 2.0f,
                ((min / 2.0f) + scoreTextPaint.descent()),
                scoreTextPaint)

        val outOf = resources.getString(R.string.total_credit_score, maxScore.toInt().toString())
        canvas.drawText(outOf,
                (min - bottomTextPaint.measureText(outOf)) / 2.0f,
                ((min / 2.0f) + (heightScoreText / 2)) + padding,
                bottomTextPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = Math.min(width, height)
        externalRectF.set(
                externalStrokeWidth / 2,
                externalStrokeWidth / 2,
                min - externalStrokeWidth / 2,
                min - externalStrokeWidth / 2)
        rectF.set(
                strokeWidth / 2 + externalStrokeWidth * 2,
                strokeWidth / 2 + externalStrokeWidth * 2,
                min - strokeWidth / 2 - externalStrokeWidth * 2,
                min - strokeWidth / 2 - externalStrokeWidth * 2)
        setMeasuredDimension((min + externalStrokeWidth * 2).toInt(), (min + externalStrokeWidth * 2).toInt())
    }

    fun updateScoreWithAnimation(score: Float) {

        val objectAnimator = ObjectAnimator.ofFloat(this, "score", score)
        objectAnimator.duration = animationDuration
        objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator.start()
    }
}