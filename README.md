


<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
FIRST COMMIT : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<









11111111111111111111111111111111111111111111111111111111111111111111111111111111

  Exercise N° 1: Create the ClippingExample Project

  In this exercise you are going to create the ClippingExample project, set the
  content view to ClippedView & create ClippedView.

  1. Create a Kotlin project called ClippingExample with the Empty Activity
     template. Use com.example.android for the package name prefix.

  2. Open MainActivity.kt.

  //TODO 1.1
  3. In the onCreate() method, replace the default content view and set the
     content view to a new instance of ClippedView. This will be your custom view
     for the clipping examples that you will create next.

        setContentView(ClippedView(this))

  //TODO 1.2
  4. At the same level as MainActivity.kt, create a new Kotlin file and class for
     a custom view called ClippedView that extends View. The rest of your work
     will all be inside this ClippedView. The @JvmOverloads annotation instructs
     the Kotlin compiler to generate overloads for this function that substitute
     default parameter values.

        class ClippedView @JvmOverloads constructor(
           context: Context,
           attrs: AttributeSet? = null,
           defStyleAttr: Int = 0
        ) : View(context, attrs, defStyleAttr) {
        }

11111111111111111111111111111111111111111111111111111111111111111111111111111111









22222222222222222222222222222222222222222222222222222222222222222222222222222222

  Exercise N°2: Add dimensions and strings resources

  In this exercise you will add dimensions and string resources.

  //TODO 1.3
  1. Define the dimensions that you'll be using for the clipped views in a new
     resources file in res/values/dimens.xml. These default dimensions are
     hardcoded and sized to fit on a pretty small screen. There are dimensions
     for a clipping rectangle, some insets and offsets, and text styling.

        <?xml version="1.0" encoding="utf-8"?>
        <resources>
        <dimen name="clipRectRight">90dp</dimen>
        <dimen name="clipRectBottom">90dp</dimen>
        <dimen name="clipRectTop">0dp</dimen>
        <dimen name="clipRectLeft">0dp</dimen>

        <dimen name="rectInset">8dp</dimen>
        <dimen name="smallRectOffset">40dp</dimen>

        <dimen name="circleRadius">30dp</dimen>
        <dimen name="textOffset">20dp</dimen>
        <dimen name="strokeWidth">4dp</dimen>

        <dimen name="textSize">18sp</dimen>
        </resources>

     For the app to look good on a bigger screen (and to easier see details),
     you can create a dimens file with bigger values that only applies to bigger
     screens.

  //TODO 1.4
  2. In Android Studio, right-click on the values folder and choose
     New > Values resource file.

  3. In the New Resource File dialog, call the file dimens.

  4. In Available qualifiers, select Smallest Screen Width and click
     the >> button to add it to the Chosen qualifiers.

  5. Enter 480 into the Smallest screen width box and click OK. The file should
     show in your values folder–– like right here. (dimens.xml (sw480dp))

  6. Replace the default contents of the values-sw480dp/dimens.xml file with the
     given dimensions below.

        <?xml version="1.0" encoding="utf-8"?>
        <resources>
           <dimen name="clipRectRight">120dp</dimen>
           <dimen name="clipRectBottom">120dp</dimen>

           <dimen name="rectInset">10dp</dimen>
           <dimen name="smallRectOffset">50dp</dimen>

           <dimen name="circleRadius">40dp</dimen>
           <dimen name="textOffset">25dp</dimen>
           <dimen name="strokeWidth">6dp</dimen>
        </resources>

  //TODO 1.5
  7. In strings.xml, add the following strings, also provided for you. These will
     be used to display text on the canvas.

        <string name="clipping">Clipping</string>
        <string name="translated">translated text</string>
        <string name="skewed">"Skewed and "</string>

22222222222222222222222222222222222222222222222222222222222222222222222222222222









33333333333333333333333333333333333333333333333333333333333333333333333333333333

  Exercise N°3: Create and initialize a Paint and a Path object


  In this exercise you will create and initialize a Paint and a Path Object.

  1. Switch to the ClippedView class.

  //TODO 1.6
  2. In ClippedView define a variable paint of a Paint.
     a. Enable anti-aliasing,      
     b. and use the stroke width and
     c. text size defined in the dimensions.

        private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
        }

  //TODO 1.7
  3. In ClippedView, create and initialize a path variable of a Path to store
     locally the path of what has been drawn. Import android.graphics.Path.

        private val path = Path()

33333333333333333333333333333333333333333333333333333333333333333333333333333333









44444444444444444444444444444444444444444444444444444444444444444444444444444444

  Exercise N°4: Set up the shapes

  In this exercise you will set up the shapes.

  //TODO 1.8      
  1. In ClippedView, below the path, add variables for dimensions for a clipping
     rectangle around the whole set of shapes.

        private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)
        private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
        private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
        private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)

  //TODO 1.9
  2. Add variables for the inset of a rectangle and the offset of a small
     rectangle.

        private val rectInset = resources.getDimension(R.dimen.rectInset)
        private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)

  //TODO 1.10
  3. Add a variable for the radius of a circle. This is the circle that is drawn
     inside the rectangle.

        private val circleRadius = resources.getDimension(R.dimen.circleRadius)

  //TODO 1.11
  4. Add an offset and a text size for text that is drawn inside the rectangle.
        private val textOffset = resources.getDimension(R.dimen.textOffset)
        private val textSize = resources.getDimension(R.dimen.textSize)

44444444444444444444444444444444444444444444444444444444444444444444444444444444









55555555555555555555555555555555555555555555555555555555555555555555555555555555

  Exercise N°5: Set up row and column locations

  In this exercise you will set up row and column locations.

  //TODO 1.12      
  1. Set up the coordinates for two columns.

        private val columnOne = rectInset
        private val columnTwo = columnOne + rectInset + clipRectRight

  //TODO 1.13
  2. Add the coordinates for each row, including the final row for the
     transformed text.

        private val rowOne = rectInset
        private val rowTwo = rowOne + rectInset + clipRectBottom
        private val rowThree = rowTwo + rectInset + clipRectBottom
        private val rowFour = rowThree + rectInset + clipRectBottom
        private val textRow = rowFour + (1.5f * clipRectBottom)

  3. Run your app. The app should open with a blank white screen below the name
     of the app.

        (NOTHING ON THE SCREEN; LIKE A NEW PROJECT WITHOUT THE HELLO WORLD! TEXT)

55555555555555555555555555555555555555555555555555555555555555555555555555555555









66666666666666666666666666666666666666666666666666666666666666666666666666666666

  Exercise N°6: Override onDraw()

  In this exercise you will override onDraw().

  //TODO 1.14
  1. Override onDraw() and call a function for each shape you are drawing.

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

  //TODO 1.15
  2. Create stubs for each of the drawing functions so that the code will
     continue to compile.

        private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
        }
        private fun drawDifferenceClippingExample(canvas: Canvas) {
        }
        private fun drawCircularClippingExample(canvas: Canvas) {
        }
        private fun drawIntersectionClippingExample(canvas: Canvas) {
        }
        private fun drawCombinedClippingExample(canvas: Canvas) {
        }
        private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
        }
        private fun drawOutsideClippingExample(canvas: Canvas) {
        }
        private fun drawTranslatedTextExample(canvas: Canvas) {
        }
        private fun drawSkewedTextExample(canvas: Canvas) {
        }
        private fun drawQuickRejectExample(canvas: Canvas) {
        }

66666666666666666666666666666666666666666666666666666666666666666666666666666666









<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
SECOND COMMIT : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<









77777777777777777777777777777777777777777777777777777777777777777777777777777777

  Exercise N°7: Create the drawClippedRectangle() Method

  In this exercise you will create the drawClippedRectangle() method.

  //TODO 2.1
  1. Create a drawClippedRectangle() method that takes an argument canvas of
     type Canvas.

        private fun drawClippedRectangle(canvas: Canvas) {
            }

  //TODO 2.2
  2. Inside the drawClippedRectangle() method, set the boundaries of the
     clipping rectangle for the whole shape. Apply a clipping rectangle that
     constrains to drawing only the square.

        canvas.clipRect(
          clipRectLeft,clipRectTop,
          clipRectRight,clipRectBottom
        )

  The Canvas.clipRect(left, top, right, bottom) method reduces the region of
  the screen that future draw operations can write to. It sets the clipping
  boundaries to be the spatial intersection of the current clipping rectangle
  and the rectangle passed into clipRect(). There are a lot of variants of the
  clipRect() method that accept different forms of regions and allow different
  operations on the clipping rectangle. Check the linked documentation
  to learn more.

  //TODO 2.3
  3. Add code to fill the canvas with white color. Only the region inside
     the clipping rectangle will be filled!

        canvas.drawColor(Color.WHITE)

  //TODO 2.4
  4. Change the color to red and draw a diagonal line inside the clipping
     rectangle.

        paint.color = Color.RED
        canvas.drawLine(
           clipRectLeft,clipRectTop,
           clipRectRight,clipRectBottom,paint
        )

  //TODO 2.5
  5. Set the color to green and draw a circle inside the clipping rectangle.

        paint.color = Color.GREEN
        canvas.drawCircle(
           circleRadius,clipRectBottom - circleRadius,
           circleRadius,paint
        )

  //TODO 2.6
  6. Set the color to blue and draw text aligned with the right edge of the
     clipping rectangle. Use Canvas.drawText() to draw text.

        paint.color = Color.BLUE
        // Align the RIGHT side of the text with the origin.
        paint.textSize = textSize
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(
           context.getString(R.string.clipping),
           clipRectRight,textOffset,paint
           )

  Note: The Paint.Align property specifies which side of the text to align
  to the origin (not which side of the origin the text goes, or where in the
  region it is aligned!). Aligning the right side of the text to the origin
  places it on the left of the origin.

77777777777777777777777777777777777777777777777777777777777777777777777777777777









88888888888888888888888888888888888888888888888888888888888888888888888888888888

  Exercise N°8: Implement drawBackAndUnclippedRectangle()

  In this exercise you will Implement the
  drawBackAndUnclippedRectangle() method.

  //TODO 2.7
  1. To see the drawClippedRectangle() method in action, draw the first unclipped
     rectangle by implementing the drawUnclippedRectangle() method as shown below.

      a. Save the canvas.

      b. Translate to the first row and column position.

      c. Draw by calling drawClippedRectangle().

      d. Then restore the canvas to its previous state.

        private fun drawBackAndUnclippedRectangle(canvas: Canvas){
           canvas.drawColor(Color.GRAY)
           canvas.save()
           canvas.translate(columnOne,rowOne)
           drawClippedRectangle(canvas)
           canvas.restore()
        }

  2. Run your app. You should see the first white rectangle with its
     circle, red line, and text on a gray background.

     (Main screen with a rectangle in the top-left side)

88888888888888888888888888888888888888888888888888888888888888888888888888888888


What does canvas.save() do?
Saves the current state of the canvas.

What does canvas.restore() do?
Restores the previous state of the canvas that was saved.


99999999999999999999999999999999999999999999999999999999999999999999999999999999

  Exercise N°9: Implement drawDifferenceClippingExample()

  //TODO 2.8
  1. Here is the code:

      private fun drawDifferenceClippingExample(canvas: Canvas) {
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

  The code does the following:

  2.  Save the canvas.

  3. Translate the origin of the canvas into open space to the first row,
     second column, to the right of the first rectangle.

  4. Apply two clipping rectangles. The DIFFERENCE operator subtracts the
     second rectangle from the first one.

  Note: The method clipRect(float, float, float, float, Region.Op.DIFFERENCE)
  was deprecated in API level 26. The recommended alternative method is
  clipOutRect(float, float, float, float), which is currently available in API
  level 26 and higher. So be sure to check the SDK version and use the
  appropriate API.

  5. Call the drawClippedRectangle() method to draw the modified canvas.

  6. Restore the canvas state.

  7. Run your app and it should look like this. (Two colums with a clipped
     canvas in the second column)

99999999999999999999999999999999999999999999999999999999999999999999999999999999









10101010101010101010101010101010101010101010101010101010101010101010101010101010

  Exercise N°10: Implement drawCircularClippingExample()

  //TODO 2.9
  1. Here is the code:

      private fun drawCircularClippingExample(canvas: Canvas) {

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
  Note: The method clipPath(path, Region.Op.DIFFERENCE) was deprecated in API
  level 26. The recommended alternative method is clipOutPath(Path), which is
  currently available in API level 26 and higher. So be sure to check the SDK
  version and use the appropriate API.

10101010101010101010101010101010101010101010101010101010101010101010101010101010

11111111111111111111111111111111111111111111111111111111111111111111111111111111

Exercise N°11: Implement drawIntersectionClippingExample()

  //TODO 2.10
  1. Here is the code:

      private fun drawIntersectionClippingExample(canvas: Canvas) {
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

      Note: The method clipRect(float, float, float, float, Region.Op.INTERSECT)
      was deprecated in API level 26. The recommended alternative method is
      clipRect(float, float, float, float), which is currently available in
      API level 26 and higher. So be sure to check the SDK version and
      use the appropriate API.

      When you run your app it should look similar to this:

      (Main Screen with four images, 2 per column)

11111111111111111111111111111111111111111111111111111111111111111111111111111111

12121212121212121212121212121212121212121212121212121212121212121212121212121212

Exercise N°12: Implement drawCombinedClippingExample
  //TODO 2.11
  1. Here is the code:

      private fun drawCombinedClippingExample(canvas: Canvas) {
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

      When you run your app it should look like this:
      (MAIN SCREEN WITH 5 Images)

12121212121212121212121212121212121212121212121212121212121212121212121212121212

13131313131313131313131313131313131313131313131313131313131313131313131313131313

Exercie N°13: Implement drawRoundedRectangleClippingExample

  //TODO 2.12
  1. At the class level, create and initialize a rectangle variable. RectF is a
   class that holds rectangle coordinates in floating point.

      private var rectF = RectF(
         rectInset,
         rectInset,
         clipRectRight - rectInset,
         clipRectBottom - rectInset
      )

  //TODO 2.13
  2. Inside the class, implement the function drawRoundedRectangleClippingExample().

      private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
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

What's happening in the code:

    * The addRoundRect() function takes a rectangle.

    * Values for the x and y values of the corner radius.

    * The direction to wind the round-rectangle's contour.

    * Path.Direction specifies how closed shapes (e.g. rects, ovals) are oriented when they are added to a path. CCW stands for counter-clockwise.

When you run your app it should look like this:
(Main Screen with 6 Images)

13131313131313131313131313131313131313131313131313131313131313131313131313131313

14141414141414141414141414141414141414141414141414141414141414141414141414141414

Exercise N°14: Implement drawOutsideClippingExample()

//TODO 2.14
  1. Here is the code:

      private fun drawOutsideClippingExample(canvas: Canvas) {
         canvas.save()
         canvas.translate(columnOne,rowFour)
         canvas.clipRect(2 * rectInset,2 * rectInset,
             clipRectRight - 2 * rectInset,
             clipRectBottom - 2 * rectInset)
         drawClippedRectangle(canvas)
         canvas.restore()
      }

When you run your app it should look like this:

(Main Screen with 7 images)

14141414141414141414141414141414141414141414141414141414141414141414141414141414

15151515151515151515151515151515151515151515151515151515151515151515151515151515

Exercise N° 15: Implement drawTranslatedTextExample()

//TODO 2.15
1. Implement the function below.

      private fun drawTranslatedTextExample(canvas: Canvas) {
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

2. Run your app to see the translated text.


15151515151515151515151515151515151515151515151515151515151515151515151515151515

16161616161616161616161616161616161616161616161616161616161616161616161616161616

Exercise N°16: Implement drawSkewedTextExample()

  //TODO 2.16
  1. Create the function below in ClippedView.
      private fun drawSkewedTextExample(canvas: Canvas) {
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

2. Run your app to see the skewed text drawn before the translated text:

   Important: When you use View classes provided by the Android system, the
   system clips views for you to minimize overdraw. When you use custom View
   classes and override the onDraw() method, clipping what you draw becomes
   your responsibility.

16161616161616161616161616161616161616161616161616161616161616161616161616161616

17171717171717171717171717171717171717171717171717171717171717171717171717171717

  quickReject()
  The quickReject() method allows you to check whether a specified rectangle
  or path would lie completely outside the currently visible regions, after all
  transformations have been applied.

  The quickReject() method is incredibly useful when you are constructing more
  complex drawings and need to do so as fast as possible. With
  quickReject(), you can decide efficiently which objects you do not have to
  draw at all, and there is no need to write your own intersection logic.

  The quickReject() method returns true if the rectangle or path would not be
  visible at all on the screen. For partial overlaps, you still have to do
  your own checking.
  The EdgeType is either AA (Antialiased: Treat edges by rounding-out, because
  they may be antialiased) or BW (Black-White: Treat edges by just rounding to
  nearest pixel boundary) for just rounding to the nearest pixel.
  There are several versions of quickReject(), and you can also find them
  in the documentation.


  In this exercise, you are going to draw in a new row, below the text, and
  inside the clipRect, as before.

  You first call quickReject() with a rectangle inClipRectangle, that overlaps
  with clipRect. So quickReject() returns false, clipRect is filled with BLACK,
  and the inClipRectangle rectangle is drawn.

  Then change the code and call quickReject(), with notInClipRectangle.
  quickReject() now returns true, and clipRect is filled with WHITE, and
  notInClipRectangle is not drawn.

  When you have complex drawings, this can quickly tell you, which shapes are
  completely outside the clipping region, and for which you may have to do
  additional calculations, and drawing, because they are partially or fully
  inside the clipping region.

  Exercise N° 17: quickReject

  //TODO 2.17
  1. At the top-level, create a variable for the y coordinates of an
     additional row.

      private val rejectRow = rowFour + rectInset + 2*clipRectBottom

  2. Add the following drawQuickRejectExample() function to ClippedView, and
     uncomment its invocation in onDraw(). Read the code, as it contains
     everything you need to know to use quickReject().

      private fun drawQuickRejectExample(canvas: Canvas) {
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

  3. Run your app, and you will see a black rectangle, which is the filled
     clipping region, and parts of the inClipRectangle, because the two
     rectangles overlap, so quickReject() returns false and inClipRectangle
     is drawn.

  4. Change the code to run quickReject() against notInClipRectangle. Now
     quickReject() returns true and the clipping region is filled with white.


17171717171717171717171717171717171717171717171717171717171717171717171717171717
