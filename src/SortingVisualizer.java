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
		visualization.setDelay(1);
		visualization.initialize(64);
		sorting.bubbleSort();
		visualization.check();
	}
	
	public void selectionSortDemostration() {
		visualization.setDelay(3);
		visualization.initialize(64);
		sorting.selectionSort();
		visualization.check();
	}
	
	public void quickSortDemostration() {
		visualization.setDelay(5);
		visualization.initialize(512);
		sorting.quickSort(0, visualization.length() - 1);
		visualization.check();
	}
	
}
