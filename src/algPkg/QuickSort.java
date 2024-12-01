package algPkg;

import mainPkg.JSort;

public class QuickSort extends Sort{
	int pivot, i, pi;

	public QuickSort(int elementNum) {
		super(elementNum);
	}

	private int partition(int low, int high, JSort jsort) {
		pivot = array[high];
		i = low - 1;
		
		for (int ii = low; ii <= high-1; ii++) {
			if (array[ii] < pivot) {
				i++;
				swap(i, ii);
			}
			
			this.sortingUtils(jsort, ii);
		}
		
		swap(i+1, high);
		return i+1;
	}
	
	private void quickSort(int low, int high, JSort jsort) {
		if (low < high) {
			pi = partition(low, high, jsort);
			
			quickSort(low, pi-1, jsort);
			quickSort(pi+1, high, jsort);
			
			this.sortingUtils(jsort, pi);
		}
	}
	
	@Override
	public void tick(JSort jsort) {
		this.startingOperations(jsort);
		
		
		quickSort(0, this.size-1, jsort);
		this.exitOperations(jsort);
	}
}
