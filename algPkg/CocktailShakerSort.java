package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class CocktailShakerSort extends Sort{

	public CocktailShakerSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		boolean swapped = true;
		int start = 0, end = this.size-1;
		
		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		while (swapped) {
			swapped = false;
			
			for (int i = 0; i < end; i++) {
				if (array[i] > array[i+1]) {
					swap(i, i+1);
					swapped = true;
				}
				
				if (this.sortingUtils(jsort, i)) return;
			}
			
			if (!swapped)
				break;
			
			swapped = false;
			end -= 1;
			
			for (int i = end-1; i >= start; i -= 1) {
				if (array[i] > array[i+1]) {
					swap(i, i+1);
					swapped = true;
				}
				
				if (this.sortingUtils(jsort, i)) return;
			}
			
			start += 1;
		}
		
		super.tick(jsort, defs);
	}

}
