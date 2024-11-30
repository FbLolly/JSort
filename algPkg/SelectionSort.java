package algPkg;

import mainPkg.JSort;

public class SelectionSort extends Sort{
	public SelectionSort(int elementNum) {
		super(elementNum);
	}
	
	
	@Override
	public void tick(JSort jsort) {
		int minIdx = 0;

		this.startingOperations(jsort);
		
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
		super.exitOperations(jsort);
	}
}
