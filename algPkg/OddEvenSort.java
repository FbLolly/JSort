package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class OddEvenSort extends Sort {
	public OddEvenSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		boolean sorted = false;

		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		while (!sorted) {
			sorted = true;
			
			for (int i = 1; i <= this.size-2; i += 2) {
				if (array[i] > array[i+1]) {
					swap(i, i+1);
					sorted = false;
				}
				
				if (this.sortingUtils(jsort, i)) return;
			}
			
			for (int i = 0; i <= this.size-2; i += 2) {
				if (array[i] > array[i+1]) {
					swap(i, i+1);
					sorted = false;
				}
				
				if (this.sortingUtils(jsort, i)) return;
			}
		}
		
		super.tick(jsort, defs);
	}
}
