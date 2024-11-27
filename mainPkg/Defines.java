package mainPkg;

import java.awt.Color;

public class Defines {
	public int FPS = 60;
	public int sortingFPS = 2400;
	public int divisor = 1;
	public final int width = 1920;
	public final int height = 1080;
	public final int buttonAnimationSpeed = 6;
	public final int buttons = 12;
	
	public final int fontSize = 35;
	
	public final int defaultSliderWidth = 50;
	
	public final int buttonWidth = 400;
	public final int buttonHeight = 50;
	public String[] btns;
	
	public Color rosePurple;
	
	public Defines() {
		btns = new String[buttons];
		btns[0] = "selectionSort";
		btns[1] = "bubbleSort";
		btns[2] = "insertionSort";
		btns[3] = "cocktailShakerSort";
		btns[4] = "oddEvenSort";
		btns[5] = "shellSort";
		btns[6] = "radixSort";
		btns[7] = "countingSort";
		btns[8] = "doubleSelectionSort";
		btns[9] = "optimizedBubbleSort";
		btns[10] = "settings";
		btns[11] = "quit";
		
		rosePurple = new Color(186,85,211);
	}
}
