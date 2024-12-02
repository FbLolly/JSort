package algPkg;
import mainPkg.JSort;

public class BubbleSort extends Sort{
	public BubbleSort(int elementNum) {
		super(elementNum);
	}
	
	@Override
	public void tick(JSort jsort) {
		this.startingOperations(jsort);
		
		for (int i = 0; i < this.size; i++) {
			for (int ii = 0; ii < this.size-1; ii++) {
				if (array[ii] > array[ii+1]) {
					this.swap(ii, ii+1);
				}
				
				if (this.sortingUtils(jsort, ii)) return;
			}
		}

		super.exitOperations(jsort);
	}
}
//Copyright 2024 FbLolly
