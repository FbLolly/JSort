package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class InsertionSort extends Sort{
	public InsertionSort(int elementNum) {
		super(elementNum);
	}

	@Override
	public void tick(JSort jsort) {
		int ii, key;

		this.startingOperations(jsort);
		
		for (int i = 1; i < this.size; i++) {
			ii = i-1;
			key = array[i];
			
			while (ii >= 0 && array[ii] > key) {
				array[ii+1] = array[ii];
				ii -= 1;
				
				if (ii >= 0)
					if (this.sortingUtils(jsort, ii)) return;
				else
					if (this.sortingUtils(jsort, ii+1)) return;
			}
			
			array[ii+1] = key;
		}

		super.exitOperations(jsort);
	}
}
