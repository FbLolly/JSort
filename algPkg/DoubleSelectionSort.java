package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class DoubleSelectionSort extends Sort{

	public DoubleSelectionSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	public void tick(JSort jsort, Defines defs) {
		int minIdx = 0, maxIdx = 0, max;
		
		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		for (int i = 0, ii = this.size-1; i < ii; i++, ii--) {
			minIdx = i; maxIdx = i; max = array[i];
			
			for (int iii = i; iii <= ii; iii++) {
				if (array[iii] > array[maxIdx]) {
					maxIdx = iii;
					max = array[iii];
				}else if (array[iii] < array[minIdx]) {
					minIdx = iii;
				}
				
				this.sortingUtils(jsort, iii);
			}
			
			this.swap(minIdx, i);
			
			if (array[minIdx] == max) 
				this.swap(ii, minIdx);
			else
				this.swap(ii, maxIdx);
		}
		
		super.tick(jsort, defs);
	}
	
}
