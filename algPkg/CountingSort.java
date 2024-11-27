package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class CountingSort extends Sort{
	public CountingSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}

	@Override
	public void tick(JSort jsort, Defines defs) {
		int[] output = new int[this.size+1];
		int max = getMax();
		int[] count = new int[max+1];

		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
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
		
		super.tick(jsort, defs);
	}
}
