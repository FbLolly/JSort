package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class SelectionSort extends Sort{
	public SelectionSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		int minIdx = 0;

		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		for (int i = 0; i < this.size-1; i++) {
			minIdx = i;
			
			for (int ii = i+1; ii < this.size; ii++) {
				if (array[ii] < array[minIdx]) {
					minIdx = ii;
				}
				
				if (this.sortingUtils(jsort, ii)) return;
			}
			
			this.swap(minIdx, i);
		}
		
		super.tick(jsort, defs);
	}
}
