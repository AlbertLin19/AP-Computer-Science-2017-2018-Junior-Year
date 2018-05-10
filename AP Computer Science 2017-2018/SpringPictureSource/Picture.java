import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super() child
		 * constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName, height
	 *         and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to shrink the picture to 1/4 size by area */
	public Picture shrinkToQuarter() {
		Picture smallerPic = new Picture(this.getHeight() / 2, this.getWidth() / 2);

		Pixel[][] bigArray = this.getPixels2D();
		Pixel[][] smallArray = smallerPic.getPixels2D();
		for (int row = 0; row < smallArray.length; row++) {
			for (int col = 0; col < smallArray[0].length; col++) {
				smallArray[row][col].setBlue(

						(bigArray[2 * row][2 * col].getBlue() + bigArray[2 * row + 1][2 * col].getBlue()
								+ bigArray[2 * row + 1][2 * col + 1].getBlue()
								+ bigArray[2 * row][2 * col + 1].getBlue()) / 4);
				smallArray[row][col].setRed(

						(bigArray[2 * row][2 * col].getRed() + bigArray[2 * row + 1][2 * col].getRed()
								+ bigArray[2 * row + 1][2 * col + 1].getRed() + bigArray[2 * row][2 * col + 1].getRed())
								/ 4);
				smallArray[row][col].setGreen(

						(bigArray[2 * row][2 * col].getGreen() + bigArray[2 * row + 1][2 * col].getGreen()
								+ bigArray[2 * row + 1][2 * col + 1].getGreen()
								+ bigArray[2 * row][2 * col + 1].getGreen()) / 4);

			}
		}

		return smallerPic;
	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (Pixel pix : pixelRow) {
				pix.setBlue(0);
			}
		}

	}

	public void keepOnlyBlue() {
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (Pixel pix : pixelRow) {
				pix.setRed(0);
				pix.setGreen(0);
			}
		}

	}

	public void negate() {
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (Pixel pix : pixelRow) {

				pix.setBlue(255 - pix.getBlue());
				pix.setRed(255 - pix.getRed());
				pix.setGreen(255 - pix.getGreen());
			}
		}

	}

	public void grayscale() {
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (Pixel pix : pixelRow) {

				int avg = (pix.getBlue() + pix.getRed() + pix.getGreen()) / 3;

				pix.setBlue(avg);
				pix.setRed(avg);
				pix.setGreen(avg);
			}
		}

	}

	public void fixUnderwater() {
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (Pixel pix : pixelRow) {

				if (pix.getRed() > 30 || pix.getBlue() < 160) {
					pix.setBlue(0);
					pix.setGreen(0);
					pix.setRed(0);
				}
			}
		}

	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of the
	 * picture from left to right
	 */
	public void mirrorVertical() {
		int middle = this.getWidth() / 2;
		Pixel[][] picArray = this.getPixels2D();
		for (Pixel[] pixelRow : picArray) {
			for (int i = 0; i < middle; i++) {

				pixelRow[i + middle].setColor(pixelRow[middle - i].getColor());

			}
		}
	}

	public void mirrorHorizontal() {
		int middle = this.getHeight() / 2;
		Pixel[][] picArray = this.getPixels2D();
		for (int row = 0; row < middle; row++) {
			for (int col = 0; col < picArray[row].length; col++) {

				picArray[row + middle][col].setColor(picArray[middle - row][col].getColor());

			}
		}
	}

	public void mirrorHorizontalBotToTop() {
		int middle = this.getHeight() / 2;
		Pixel[][] picArray = this.getPixels2D();
		for (int row = 0; row < middle; row++) {
			for (int col = 0; col < picArray[row].length; col++) {

				picArray[middle - row][col].setColor(picArray[row + middle][col].getColor());

			}
		}
	}

	public void mirrorDiagonal() {

		Pixel[][] picArray = this.getPixels2D();
		int squareLength = 0;
		if (picArray.length < picArray[0].length) {
			squareLength = picArray.length;
		} else {
			squareLength = picArray[0].length;
		}
		for (int row = 0; row < squareLength; row++) {
			for (int col = 0; col < squareLength; col++) {

				picArray[row][col].setColor(picArray[col][row].getColor());

			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int middle = this.getWidth() / 2;
		Pixel[][] picArray = this.getPixels2D();
		for (int row = 28; row < 98; row++) {
			for (int col = 0; col < middle - 28; col++) {

				picArray[row][col + middle].setColor(picArray[row][middle - col].getColor());

			}
		}

	}

	public void mirrorArms() {
		int middle = 187;
		Pixel[][] picArray = this.getPixels2D();
		for (int row = 160; row < middle; row++) {
			for (int col = 106; col < 170; col++) {

				picArray[middle + middle - row][col].setColor(picArray[row][col].getColor());

			}
		}

		int secondMiddle = 196;
		for (int row = 160; row < secondMiddle; row++) {
			for (int col = 237; col < 296; col++) {

				picArray[secondMiddle + secondMiddle - row][col].setColor(picArray[row][col].getColor());

			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in the
	 * current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {

	}

	/** Method to create a collage of several pictures */
	public void createCollage(Picture pigeons, Picture astronaut, Picture tree, Picture bear) {
		
		Picture pigeonsMirrored = pigeons.shrinkToQuarter().shrinkToQuarter();
		pigeonsMirrored.mirrorVertical();
		pigeons = pigeons.shrinkToQuarter().shrinkToQuarter();
		bear = bear.shrinkToQuarter();

		chromakeyBlueOn(tree, 100, 300);
		chromakeyOn(astronaut, 200, 400);
		chromakeyOn(pigeons, 400, 300);
		chromakeyOn(pigeons, 600, 120);
		chromakeyOn(pigeonsMirrored, 400, 1050);
		chromakeyOn(bear, 400, 200);
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist) {
					leftPixel.setColor(Color.BLACK);
				} else {
					leftPixel.setColor(Color.WHITE);
				}
			}
		}
	}

	public void mirrorGull() {
		int middle = 367;
		Pixel[][] picArray = this.getPixels2D();
		for (int row = 228; row < 328; row++) {
			for (int col = 232; col < middle; col++) {

				picArray[row][middle + middle - col].setColor(picArray[row][col].getColor());

			}
		}

		int secondMiddle = 196;
		for (int row = 160; row < secondMiddle; row++) {
			for (int col = 237; col < 296; col++) {

				picArray[secondMiddle + secondMiddle - row][col].setColor(picArray[row][col].getColor());

			}
		}
	}

	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel botPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		Color botColor = null;
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				botPixel = pixels[row + 1][col];
				rightColor = rightPixel.getColor();
				botColor = botPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist && leftPixel.colorDistance(botColor) > edgeDist) {
					leftPixel.setColor(Color.BLACK);
				} else {
					leftPixel.setColor(Color.WHITE);
				}
			}
		}

	}

	/**
	 * Method to replace the blue background with
	 * 
	 * the pixels in the newBack picture
	 * 
	 * @param newBack
	 *            the picture to copy from
	 * 
	 */

	public void chromakey(Picture newBack) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] backPixels = newBack.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				if (pixels[row][col].colorDistance(Color.green) < 180) {
					pixels[row][col].setColor(backPixels[row][col].getColor());
				}
			}
		}
	}
	
	/*
	 * I just made this for the sake of being able to superimpose an image onto another through chromakey
	 * slightly altered version of the chromakey method
	 */
	public void chromakeyOn(Picture newFront, int startRow, int startCol) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] frontPixels = newFront.getPixels2D();
		for (int row = 0; row < frontPixels.length; row++) {
			for (int col = 0; col < frontPixels[0].length; col++) {
				if (frontPixels[row][col].colorDistance(Color.green) > 180) {
					pixels[row+startRow][col+startCol].setColor(frontPixels[row][col].getColor());
				}
			}
		}
	}
	
	public void chromakeyBlueOn(Picture newFront, int startRow, int startCol) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] frontPixels = newFront.getPixels2D();
		for (int row = 0; row < frontPixels.length; row++) {
			for (int col = 0; col < frontPixels[0].length; col++) {
				if (frontPixels[row][col].colorDistance(Color.blue) > 180) {
					pixels[row+startRow][col+startCol].setColor(frontPixels[row][col].getColor());
				}
			}
		}
	}

	public void copy(Picture fromPic, int toStartRow, int toStartCol, int fromStartRow, int fromStartCol, int width, int height) {
		  Pixel[][] pixels = this.getPixels2D();
		  Pixel[][] newPixels = fromPic.getPixels2D();
		  for (int row = toStartRow; row < toStartRow+height && row < pixels.length; row++) {
			  for (int col = toStartCol; col < toStartCol+width && col < pixels[row].length; col++) {
				  pixels[row][col].setColor(newPixels[fromStartRow+(row-toStartRow)][fromStartCol+(col-toStartCol)].getColor());
			  }
		  }
			
	  }
	
	public void saturate(int intensity) {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				Pixel pix = pixels[row][col];
				if (pix.getBlue()>pix.getRed()&&pix.getBlue()>pix.getGreen()) {
					pix.setBlue(pix.getBlue()+intensity);
				} else if (pix.getRed()>pix.getBlue()&&pix.getRed()>pix.getGreen()) {
					pix.setRed(pix.getRed()+intensity);
				} else {
					pix.setGreen(pix.getGreen()+intensity);
				}
				
			}
		}
		
	}
	
	public void vignette(int intensity) {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				pixels[row][col].setBlue(pixels[row][col].getBlue()-(int) (intensity*Math.sqrt((pixels.length/2-row)*(pixels.length/2-row) + (pixels[0].length/2-col)*(pixels[0].length/2-col)))/pixels.length);
				pixels[row][col].setRed(pixels[row][col].getRed()-(int) (intensity*Math.sqrt((pixels.length/2-row)*(pixels.length/2-row) + (pixels[0].length/2-col)*(pixels[0].length/2-col)))/pixels.length);
				pixels[row][col].setGreen(pixels[row][col].getGreen()-(int) (intensity*Math.sqrt((pixels.length/2-row)*(pixels.length/2-row) + (pixels[0].length/2-col)*(pixels[0].length/2-col)))/pixels.length);
				
			}
		}
	}
	
	public void blur() {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel botPixel = null;
		Pixel topPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col-1];
				rightPixel = pixels[row][col + 1];
				botPixel = pixels[row + 1][col];
				topPixel = pixels[row-1][col];
				pixels[row][col].setBlue((leftPixel.getBlue()+rightPixel.getBlue()+topPixel.getBlue()+botPixel.getBlue())/4);
				pixels[row][col].setRed((leftPixel.getRed()+rightPixel.getRed()+topPixel.getRed()+botPixel.getRed())/4);
				pixels[row][col].setGreen((leftPixel.getGreen()+rightPixel.getGreen()+topPixel.getGreen()+botPixel.getGreen())/4);
			}
		}
		
	}
	
	public void increaseExposure(int intensity) {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				pixels[row][col].setBlue(pixels[row][col].getBlue()+intensity);
				pixels[row][col].setRed(pixels[row][col].getRed()+intensity);
				pixels[row][col].setGreen(pixels[row][col].getGreen()+intensity);
				
			}
		}
	}

} // this } is the end of class Picture, put all new methods before this
