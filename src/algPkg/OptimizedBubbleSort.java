package algPkg;

import mainPkg.JSort;

public class OptimizedBubbleSort extends Sort{

	public OptimizedBubbleSort(int elementNum) {
		super(elementNum);
	}
	
	public void tick(JSort jsort) {
		boolean sorted = false;
		int max = this.size-1;

		this.startingOperations(jsort);
		
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

		super.exitOperations(jsort);
	}

}
