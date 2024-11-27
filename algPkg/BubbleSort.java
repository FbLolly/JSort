package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class BubbleSort extends Sort{
	public BubbleSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		for (int i = 0; i < this.size; i++) {
			for (int ii = 0; ii < this.size-1; ii++) {
				if (array[ii] > array[ii+1]) {
					this.swap(ii, ii+1);
				}
				
				if (this.sortingUtils(jsort, ii)) return;
			}
		}
		
		super.tick(jsort, defs);
	}
}
