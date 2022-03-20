
public class SortingComponent {
	VisualizationComponent provider; 
	
	public SortingComponent(VisualizationComponent provider) {
		this.provider = provider;
	}
	public void bubbleSort() {
		for (int i= provider.length() - 1; i > 0; --i) {
				for (int j =0; j<i; ++j) {
					 if (provider.compare(j, j+1) > 0) {
						 provider.swap(j, j+1);
					 }
				 }
			}
	}
	
	public void selectionSort() {
        for (int i = 0; i < provider.length()-1; i++) {
            int minIdx = i;
            for (int j = i+1; j < provider.length(); j++) {
            	if (provider.compare(j, minIdx) < 0) {
            		minIdx = j;	                	
            	}	            	
            }
           provider.swap(i, minIdx);
        }
	}
	
	private int partition(int low, int high)
	{
	    int pivot = provider.get(high);
	    int i = (low - 1);
	 
	    for(int j = low; j <= high - 1; j++) {
	    	provider.incrementComparisons();
	        if (provider.get(j) < pivot) {
	            i++;
	            provider.swap(i, j);
	        }
	    }
	    provider.swap(i + 1, high);
	    return (i + 1);
	}
	 
	/* The main function that implements QuickSort
	          arr[] --> Array to be sorted,
	          low --> Starting index,
	          high --> Ending index
	 */
	public void quickSort(int low, int high) {
		provider.incrementComparisons();
		if (low < high) {
	        int pi = partition(low, high);
	        quickSort(low, pi - 1);
	        quickSort(pi + 1, high);
	    }
	}
	
	private void merge(int start, int mid, int end) {
		int start2 = mid + 1;
		
		// If the direct merge is already sorted
		if (provider.compare(mid, start2) <=0 ) {
		  return;
		}
		
		// Two pointers to maintain start
		// of both arrays to merge
		while (start <= mid && start2 <= end) {
		
			// If element 1 is in right place
			if (provider.compare(start, start2) <=0) {
				start++;
			}
			else {
				int value = provider.get(start2);
				int index = start2;
		
				// Shift all the elements between element 1
				// element 2, right by 1.
				while (index != start) {
					 provider.swap(index, index-1);
				     index--;
				}
				provider.set(start, value);
				  // Update all the pointers
				start++;
			    mid++;
			    start2++;
			}
		}
}

	/* l is for left index and r is right index of the
	sub-array of arr to be sorted */
	public void mergeSort(int l, int r)
	{
		if (l < r) {
		
		  // Same as (l + r) / 2, but avoids overflow
		  // for large l and r
		  int m = l + (r - l) / 2;
		
		  // Sort first and second halves
		  mergeSort(l, m);
		  mergeSort(m + 1, r);
		
		  merge(l, m, r);
		}
	}
	
}
