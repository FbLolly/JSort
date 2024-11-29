package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class CountingSort extends Sort{
	public CountingSort(int elementNum) {
		super(elementNum);
	}

	@Override
	public void tick(JSort jsort) {
		int[] output = new int[this.size+1];


		this.startingOperations(jsort);
		
		int max = getMax();
		int[] count = new int[max+1];
		
		for (int i = 0; i < this.size; i++) {
			count[array[i]]++;

            if (this.sortingUtils(jsort, i)) return;
		}
		
		for (int i = 1; i <= max; i++) {
			count[i] += count[i-1];
		}
		
		for (int i = this.size-1; i >= 0; i--) {
			output[count[array[i]] - 1] = array[i];
			count[array[i]]--;

			if (this.sortingUtils(jsort, i)) return;
		}
		
		for (int i = 0; i < this.size; i++) {
			array[i] = output[i];

			if (this.sortingUtils(jsort, i)) return;
		}

		super.exitOperations(jsort);
	}
}
