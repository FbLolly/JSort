package algPkg;

import mainPkg.Defines;
import mainPkg.JSort;

public class RadixSort extends Sort{
	public RadixSort(int elementNum, Defines defs) {
		super(elementNum, defs);
	}
	
	protected void countingSort(JSort jsort, Defines defs, int exp) {
		int[] output = new int[this.size];
		int[] count = new int[10]; //already initialized to 0 (thanks java)
		
		for (int i = 0; i < this.size; i++) {
			count[(array[i] / exp) % 10]++;
			
			if (this.sortingUtils(jsort, i)) return;
		}
		
		for (int i = 1; i < 10; i++) {
			count[i] += count[i-1];
			
			if (this.sortingUtils(jsort, i)) return;
		}
		
		for (int i = this.size-1; i >= 0; i--) {
			output[count[(array[i] / exp) % 10] -1 ] = array[i];
			count[(array[i]/exp)%10]--;
			
			if (this.sortingUtils(jsort, i)) return;
		}
		
		for (int i = 0; i < this.size; i++) {
			array[i] = output[i];
		
			if (this.sortingUtils(jsort, i)) return;
		}
	}
	
	@Override
	public void tick(JSort jsort, Defines defs) {
		int max = this.getMax();

		defs.sortingFPS = (100/defs.divisor)*100;
		defs.FPS = defs.sortingFPS;
		
		for (int exp = 1; max / exp > 0; exp *= 10) {
			this.countingSort(jsort, defs, exp);
		}
		
		super.tick(jsort, defs);
	}
}
