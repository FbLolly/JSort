package algPkg;

import mainPkg.JSort;

public class DoubleSelectionSort extends Sort{

	public DoubleSelectionSort(int elementNum) {
		super(elementNum);
	}
	
	public void tick(JSort jsort) {
		int minIdx = 0, maxIdx = 0, max;

		this.startingOperations(jsort);
		
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

		super.exitOperations(jsort);
	}
	
}
