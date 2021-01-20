


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
