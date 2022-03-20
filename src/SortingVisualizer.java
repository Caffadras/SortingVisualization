import javax.swing.JFrame;

public class SortingVisualizer {
	private JFrame frame;
	private VisualizationComponent visualization;
	private SortingComponent sorting;
	
	public SortingVisualizer() {
		JFrame frame = new JFrame();
		frame.setTitle("Sorting Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 512);
		frame.setResizable(false);

		visualization = new VisualizationComponent(); 
		frame.add(visualization);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		sorting = new SortingComponent(visualization);
	}
	
	
	public void bubbleSortDemonstration() {
		int size = 64;
		visualization.setAlgoName("Bubble Sort (" + size + " elements)");
		visualization.setDelay(1);
		visualization.initialize(size);
		sorting.bubbleSort();
		visualization.check();
	}
	
	public void selectionSortDemostration() {
		int size = 64;
		visualization.setAlgoName("Selection Sort (" + size + " elements)");
		visualization.setDelay(3);
		visualization.initialize(size);
		sorting.selectionSort();
		visualization.check();
	}
	
	public void quickSortDemostration() {
		int size = 512;
		visualization.setAlgoName("Quick Sort (" + size + " elements)");
		visualization.setDelay(5);
		visualization.initialize(size);
		sorting.quickSort(0, visualization.length() - 1);
		visualization.check();
	}
	
}
