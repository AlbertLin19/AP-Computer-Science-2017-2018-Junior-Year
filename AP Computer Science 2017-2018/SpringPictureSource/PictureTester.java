/**
 * This class will help you test the Picture class.
 * 
 */
public class PictureTester 
{
	/** Main method for testing. */
	public static void main(String[] args) 
	{
		Picture ship = new Picture("Ship in a bottle HD.jpg");
		ship.explore();
		ship.blur();
		ship.blur();
		ship.blur();
		ship.saturate(50);
		ship.vignette(150);
		ship.increaseExposure(120);
		ship.explore();
//		
//		Picture beach = new Picture("beach.jpg");
//	// show the pic
//	beach.keepOnlyBlue();
////		
//	Picture negateBeach = new Picture("beach.jpg");
//	negateBeach.negate();
//	negateBeach.explore();
//	beach.explore();
//		
//	Picture grayScale =  new Picture("beach.jpg");
//	grayScale.grayscale();
//grayScale.explore();
////	// pick your pic here
//	Picture colorPickerV = new Picture("Color Picker.jpg");
//		// show the pic
//	colorPickerV.mirrorVertical();
//	colorPickerV.explore();
//		
//		Picture colorPickerH = new Picture("Color Picker.jpg");
//		// show the pic
//		colorPickerH.mirrorHorizontal();;
//		colorPickerH.explore();
//			
////			Picture colorPickerHBotToTop = new Picture("Color Picker.jpg");
////			// show the pic
////			colorPickerHBotToTop.mirrorHorizontalBotToTop();;
////			colorPickerHBotToTop.explore();
////			
////			Picture colorPicker = new Picture("Color Picker.jpg");
////			// show the pic
////				colorPicker.explore();
////				
//				Picture colorPickerDiagonal = new Picture("Ship in a bottle HD.jpg");
//				// show the pic
//				colorPickerDiagonal.mirrorDiagonal();;
//				colorPickerDiagonal.explore();
//		
////		Picture temple = new Picture("temple.jpg");
//		temple.mirrorTemple();
//		temple.explore();
////		
//	Picture moon = new Picture("collage1Moon.jpg");
//	Picture pigeons = new Picture("collage2Pigeons.jpg");
//	Picture astronaut = new Picture("collage3Astronaut.jpg");
//	Picture tree = new Picture("collage4Tree.jpg");
//	Picture bear = new Picture("collage5Bear.jpg");
//	moon.createCollage(pigeons, astronaut, tree, bear);
//	moon.explore();
//		Picture seagull = new Picture("seagull.jpg");
//		seagull.mirrorGull();
//		seagull.explore();
//		
////		Picture quarterColorPicker = new Picture("Color Picker.jpg");
////		// show the pic
////		quarterColorPicker.shrinkToQuarter();
////		quarterColorPicker.explore();
//		
//		Picture bottle = new Picture("Ship in a bottle HD.jpg");
//		// show the pic
//		bottle.mirrorVertical();
//		bottle.explore();
//		
//	Picture water = new Picture("water.jpg");
//		water.fixUnderwater();
//	water.explore();
///		
//		Picture orig = new Picture("water.jpg");
//		orig.explore();
////		Picture quarterBottle = bottle.shrinkToQuarter();
//		// show the pic
//		quarterBottle.explore();
	}
}