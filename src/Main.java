import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose a sorting algorithm to visuzlize: ");
		System.out.println("1 - Bubble Sort");
		System.out.println("2 - Selection Sort");
		System.out.println("3 - In-Place Merge Sort");
		System.out.println("4 - Quick Sort");
		int option = scanner.nextInt();
		while (option < 1 || option > 4) {
			System.out.println("Wrong input. Enter between 1 and 4");
			option = scanner.nextInt();
		}
		SortingVisualizer visualizer = new SortingVisualizer();
		
		switch(option) {
		case 1: visualizer.bubbleSortDemonstration();
				break; 
				
		case 2: visualizer.selectionSortDemostration();
				break;
				
		case 3: visualizer.inPlaceMergeSortDemostration();
				break;
				
		case 4: visualizer.quickSortDemostration();
				break;
		}
	}
	
	public static void drawArray() {
		
	}
	
}
