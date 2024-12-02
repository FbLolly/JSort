package algPkg;

import mainPkg.JSort;

public class ShellSort extends Sort{
	public ShellSort(int elementNum) {
		super(elementNum);
	}
	
	@Override
	public void tick(JSort jsort) {
		int temp, ii;

		this.startingOperations(jsort);
		
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
		
		super.exitOperations(jsort);
	}
}
//Copyright 2024 FbLolly
