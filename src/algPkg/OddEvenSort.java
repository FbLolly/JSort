package algPkg;

import mainPkg.JSort;

public class OddEvenSort extends Sort {
	public OddEvenSort(int elementNum) {
		super(elementNum);
	}
	
	@Override
	public void tick(JSort jsort) {
		boolean sorted = false;

		this.startingOperations(jsort);
		
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

		super.exitOperations(jsort);
	}
}
//Copyright 2024 FbLolly
