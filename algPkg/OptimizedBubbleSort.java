package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class OptimizedBubbleSort extends Sort{

	public OptimizedBubbleSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	public void tick(JSort jsort, Defines defs) {
		boolean sorted = false;
		int max = this.size-1;
		
		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		while (!sorted) {
			sorted = true;
			
			for (int i = 0; i < max; i++) {
				if (array[i] > array[i+1]) {
					sorted = false;
					this.swap(i, i+1);
					
					if (i+1 == max) {
						max = i+1;
					}
					
					this.sortingUtils(jsort, i);
				}
			}
		}
		
		super.tick(jsort, defs);
	}

}
