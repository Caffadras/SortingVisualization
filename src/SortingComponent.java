
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
	
}
