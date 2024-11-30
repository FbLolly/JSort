package algPkg;

import mainPkg.JSort;

public class MergeSort extends Sort{
	public MergeSort(int elementNum) {
		super(elementNum);
	}

	private void merge(int l, int m, int r, JSort jsort) {
		int size1 = m - l + 1;
		int size2 = r - m;
		
		int[] arr1 = new int[size1];
		int[] arr2 = new int[size2];
		
		for (int i = 0; i < size1; i++) {
			arr1[i] = array[l + i];
            this.sortingUtils(jsort, i);
		}
		for (int i = 0; i < size2; i++) {
			arr2[i] = array[m + 1 + i];
            this.sortingUtils(jsort, i);
		}
		
		int i = 0, ii = 0;
		
		int k = l;
        while (i < size1 && ii < size2) {
            if (arr1[i] <= arr2[ii]) {
                array[k] = arr1[i];
                i++;
            }
            else {
                array[k] = arr2[ii];
                ii++;
            }
            k++;
            
            this.sortingUtils(jsort, i);
            this.sortingUtils(jsort, ii);
        }
        
        while (i < size1) {
            array[k] = arr1[i];
            i++;
            k++;
            
            this.sortingUtils(jsort, i);
        }
        
        while (ii < size2) {
            array[k] = arr2[ii];
            ii++;
            k++;
            
            this.sortingUtils(jsort, ii);
        }
	}
	
	private void mergeSort(int l, int r, JSort jsort) {
		if (l < r) {
			int m = l + (r - l)/2;
			
			mergeSort(l, m, jsort);
			mergeSort(m+1, r, jsort);
			
			merge(l, m, r, jsort);
		}
	}
	
	@Override
	public void tick(JSort jsort) {
		this.startingOperations(jsort);
		
		mergeSort(0, this.size-1, jsort);
		this.exitOperations(jsort);
	}

}
