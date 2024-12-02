package mainPkg;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JComponent;

public class Defines {
	public static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	public static int FPS = 60;
	public static int sortingFPS = 0;
	public static int menuFPS = 60;
	public static int divisor = 1;
	public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int buttonAnimationSpeed = 6;
	public static final int buttons = 14;
	
	public static final int fontSize = 35;
	
	public static final int defaultSliderWidth = 50;
	
	public static final int buttonWidth = 400;
	public static final int buttonHeight = 50;
	public static String[] btns = {"selectionSort", "bubbleSort", "insertionSort", "cocktailShakerSort", "oddEvenSort",
			"shellSort", "radixSort", "countingSort", "doubleSelectionSort", "optimizedBubbleSort", "quickSort", "mergeSort",
			"settings", "quit"};
	
	public static Color rosePurple = new Color(186, 85, 211);
}
//Copyright 2024 FbLolly
