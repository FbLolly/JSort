package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class ShellSort extends Sort{
	public ShellSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		int temp, ii;

		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		for (int gap = this.size/2; gap > 0; gap /= 2) {
			for (int i = gap; i < this.size; i++) {
				temp = array[i];
				
				for (ii = i; ii >= gap && array[ii - gap] > temp; ii -= gap) {
					array[ii] = array[ii-gap];
					
					if (this.sortingUtils(jsort, ii)) return;
				}
				
				array[ii] = temp;
			}
		}
		
		super.tick(jsort, defs);
	}
}
