package com.example.android.clippingexample

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View

//TODO 1.2
class ClippedView @JvmOverloads constructor(context: Context,
                                            attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    //TODO 1.6
    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        //stroke means "trazo" in spanish
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    //TODO 1.7
    private val path = Path()

    //TODO 1.8
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

    //TODO 1.9
    private val rectInset = resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)

    //TODO 1.10
    private val circleRadius = resources.getDimension(R.dimen.circleRadius)

    //TODO 1.11
    private val textOffset = resources.getDimension(R.dimen.textOffset)
    private val textSize = resources.getDimension(R.dimen.textSize)

    //TODO 1.12
    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight

    //TODO 1.13
    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (1.5f * clipRectBottom)

    //TODO 2.13
    private var rectF = RectF(
            rectInset,
            rectInset,
            clipRectRight - rectInset,
            clipRectBottom - rectInset
    )

    //TODO 2.17
    private val rejectRow = rowFour + rectInset + 2*clipRectBottom

    //TODO 1.14
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        // drawQuickRejectExample(canvas)
    }

    //TODO 2.1
    private fun drawClippedRectangle(canvas: Canvas) {

        //TODO 2.2
        canvas.clipRect(
                clipRectLeft,clipRectTop,
                clipRectRight,clipRectBottom
        )

        //TODO 2.3
        canvas.drawColor(Color.WHITE)

        //TODO 2.4
        paint.color = Color.RED
        canvas.drawLine(
                clipRectLeft,clipRectTop,
                clipRectRight,clipRectBottom,paint
        )

        //TODO 2.5
        paint.color = Color.GREEN
        canvas.drawCircle(
                circleRadius,clipRectBottom - circleRadius,
                circleRadius,paint
        )

        //TODO 2.6
        paint.color = Color.BLUE
        // Align the RIGHT side of the text with the origin.
        paint.textSize = textSize
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(
                context.getString(R.string.clipping),
                clipRectRight,textOffset,paint
        )
    }

    //TODO 1.15
    private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
        //TODO 2.7
        canvas.drawColor(Color.GRAY)
        canvas.save()
        canvas.translate(columnOne,rowOne)
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    private fun drawDifferenceClippingExample(canvas: Canvas) {
        //TODO 2.8
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(columnTwo,rowOne)
        // Use the subtraction of two clipping rectangles to create a frame.
        canvas.clipRect(
                2 * rectInset,2 * rectInset,
                clipRectRight - 2 * rectInset,
                clipRectBottom - 2 * rectInset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .DIFFERENCE) was deprecated in API level 26. The recommended
        // alternative method is clipOutRect(float, float, float, float),
        // which is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            canvas.clipRect(
                    4 * rectInset,4 * rectInset,
                    clipRectRight - 4 * rectInset,
                    clipRectBottom - 4 * rectInset,
                    Region.Op.DIFFERENCE
            )
        else {
            canvas.clipOutRect(
                    4 * rectInset,4 * rectInset,
                    clipRectRight - 4 * rectInset,
                    clipRectBottom - 4 * rectInset
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    private fun drawCircularClippingExample(canvas: Canvas) {
        //TODO 2.9
        canvas.save()
        canvas.translate(columnOne, rowTwo)
        // Clears any lines and curves from the path but unlike reset(),
        // keeps the internal data structure for faster reuse.
        path.rewind()
        path.addCircle(
                circleRadius,clipRectBottom - circleRadius,
                circleRadius,Path.Direction.CCW
        )
        // The method clipPath(path, Region.Op.DIFFERENCE) was deprecated in
        // API level 26. The recommended alternative method is
        // clipOutPath(Path), which is currently available in
        // API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    private fun drawIntersectionClippingExample(canvas: Canvas) {
        //TODO 2.10
            canvas.save()
            canvas.translate(columnTwo,rowTwo)
            canvas.clipRect(
                    clipRectLeft,clipRectTop,
                    clipRectRight - smallRectOffset,
                    clipRectBottom - smallRectOffset
            )
            // The method clipRect(float, float, float, float, Region.Op
            // .INTERSECT) was deprecated in API level 26. The recommended
            // alternative method is clipRect(float, float, float, float), which
            // is currently available in API level 26 and higher.
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                canvas.clipRect(
                        clipRectLeft + smallRectOffset,
                        clipRectTop + smallRectOffset,
                        clipRectRight,clipRectBottom,
                        Region.Op.INTERSECT
                )
            } else {
                canvas.clipRect(
                        clipRectLeft + smallRectOffset,
                        clipRectTop + smallRectOffset,
                        clipRectRight,clipRectBottom
                )
            }
            drawClippedRectangle(canvas)
            canvas.restore()
        }
    private fun drawCombinedClippingExample(canvas: Canvas) {
        //TODO 2.11
        canvas.save()
        canvas.translate(columnOne, rowThree)
        path.rewind()
        path.addCircle(
                clipRectLeft + rectInset + circleRadius,
                clipRectTop + circleRadius + rectInset,
                circleRadius,Path.Direction.CCW
        )
        path.addRect(
                clipRectRight / 2 - circleRadius,
                clipRectTop + circleRadius + rectInset,
                clipRectRight / 2 + circleRadius,
                clipRectBottom - rectInset,Path.Direction.CCW
        )
        canvas.clipPath(path)
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
        //TODO 2.13
        canvas.save()
        canvas.translate(columnTwo,rowThree)
        path.rewind()
        path.addRoundRect(
                rectF,clipRectRight / 4,
                clipRectRight / 4, Path.Direction.CCW
        )
        canvas.clipPath(path)
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    private fun drawOutsideClippingExample(canvas: Canvas) {
        //TODO 2.14
        canvas.save()
        canvas.translate(columnOne,rowFour)
        canvas.clipRect(2 * rectInset,2 * rectInset,
                clipRectRight - 2 * rectInset,
                clipRectBottom - 2 * rectInset)
        drawClippedRectangle(canvas)
        canvas.restore()

    }
    private fun drawTranslatedTextExample(canvas: Canvas) {
        //TODO 2.15
        canvas.save()
        paint.color = Color.GREEN
        // Align the RIGHT side of the text with the origin.
        paint.textAlign = Paint.Align.LEFT
        // Apply transformation to canvas.
        canvas.translate(columnTwo,textRow)
        // Draw text.
        canvas.drawText(context.getString(R.string.translated),
                clipRectLeft,clipRectTop,paint)
        canvas.restore()
    }
    private fun drawSkewedTextExample(canvas: Canvas) {
        //TODO 2.16
        canvas.save()
        paint.color = Color.YELLOW
        paint.textAlign = Paint.Align.RIGHT
        // Position text.
        canvas.translate(columnTwo, textRow)
        // Apply skew transformation.
        canvas.skew(0.2f, 0.3f)
        canvas.drawText(context.getString(R.string.skewed),
                clipRectLeft, clipRectTop, paint)
        canvas.restore()
    }
    private fun drawQuickRejectExample(canvas: Canvas) {
     //TODO 2.18
        val inClipRectangle = RectF(clipRectRight / 2,
                clipRectBottom / 2,
                clipRectRight * 2,
                clipRectBottom * 2)

        val notInClipRectangle = RectF(RectF(clipRectRight+1,
                clipRectBottom+1,
                clipRectRight * 2,
                clipRectBottom * 2))

        canvas.save()
        canvas.translate(columnOne, rejectRow)
        canvas.clipRect(
                clipRectLeft,clipRectTop,
                clipRectRight,clipRectBottom
        )
        if (canvas.quickReject(
                        inClipRectangle, Canvas.EdgeType.AA)) {
            canvas.drawColor(Color.WHITE)
        }
        else {
            canvas.drawColor(Color.BLACK)
            canvas.drawRect(inClipRectangle, paint
            )
        }
        canvas.restore()
    }


}