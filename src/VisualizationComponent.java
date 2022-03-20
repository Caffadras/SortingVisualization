import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class VisualizationComponent extends JPanel{
	private final int SCREEN_HEIGHT = 512; 
	private final int SCREEN_WIDTH = 1024; 
	private int delayMilis = 1; 
	private int swapCount = 0; 
	private int comparisonsCount = 0; 
	int [] array;
	int [] colors;
	

	ArrayList<Integer> toUnmark = new ArrayList<>(20); 
	private int lineWidth; 
		
	private String algoName;
	public VisualizationComponent(){
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
	}
	
	public void initialize(int length) {
		swapCount = 0; 
		comparisonsCount =0; 
		createArray(length);
		generateArray();
		repaint(); 
		delay(1000);
		shuffle();
		repaint();
		delay(1000);
	}
	
	public void swap(int idx1, int idx2) {
		if (idx1 < 0 || idx2 < 0 || idx1 > array.length || idx2> array.length) 
			throw new ArrayIndexOutOfBoundsException(); 
		
		colors[idx1] = 2; 
		colors[idx2] = 2;
		toUnmark.add(idx1);
		toUnmark.add(idx2);
		int temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
		delay();
		repaint();
		++swapCount;
	}
	
	public int compare(int idx1, int idx2) {
		if (idx1 < 0 || idx2 < 0 || idx1 > array.length || idx2> array.length) 
			throw new ArrayIndexOutOfBoundsException(); 	
		colors[idx1] = 2; 
		colors[idx2] = 2; 
		toUnmark.add(idx1);
		toUnmark.add(idx2);
		delay();
		repaint();
		++comparisonsCount;
		return Integer.compare(array[idx1], array[idx2]);
	}
	public int get(int idx) {
		if (idx < 0 || idx > array.length) 
			throw new ArrayIndexOutOfBoundsException(); 
		return array[idx];
	}
	
	public void set(int idx, int value) {
		if (idx < 0 || idx > array.length) 
			throw new ArrayIndexOutOfBoundsException(); 
		array[idx] = value;
	}
	public void incrementComparisons() {
		++comparisonsCount;
	}
	
	public int length() {
		return array.length;
	}
	
	public void setDelay(int newDelay) {
		delayMilis = newDelay;
	}
	
	public void setAlgoName(String name) {
		algoName = name;
	}
	
	private void createArray(int length) {
		array = new int[length];
		colors = new int[length];
		lineWidth = SCREEN_WIDTH / length; 
	}
	
	private void generateArray() {
		for (int i=0; i<array.length; ++i) {
			array[i] = i*(SCREEN_HEIGHT / array.length);
		}
		Arrays.fill(colors, 1);
	}

	private void shuffle() {
		Random rand = new Random(); 
		int lastIndex = array.length -1; 
		while (lastIndex > 0) {
			int randIndex = rand.nextInt(lastIndex);
			int temp = array[lastIndex];
			array[lastIndex] = array[randIndex]; 
			array[randIndex] = temp; 
			--lastIndex;
			
			colors[randIndex] = 2; 
			colors[lastIndex] = 2;
			toUnmark.add(randIndex);
			toUnmark.add(lastIndex);
			repaint();
			delay((1000 / array.length) + 1);
		}
	}
	
	public void delay() {
		delay(delayMilis);
	}
	
	public void delay(int time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public void check() {
		colors[0] = 3;
		for (int i=0; i<array.length-1; ++i) {
			colors[i+1] = 2;
			if (array[i] < array[i + 1]) {
				repaint(); 
				delay((1000 / array.length) + 1);
				colors[i+1] = 3;
			}
			
		}
		repaint();
	}
	
	private void unmark() {
		for(int i =0; i<toUnmark.size(); ++i) {
			Integer index = toUnmark.get(i);
			
			//now toUnmark is effectively thread safe
			if (index != null) {
				colors[index] =1; 				
			}
		}
		toUnmark.clear();
	}
	private Color getColor(int idx) {
		switch(idx) {
		case 1: return Color.white;
		case 2: return Color.red;
		case 3: return Color.green;
		}
		
		throw new IllegalArgumentException();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
			
		//drawing string
		g.setFont(new Font("Monospace", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString(algoName + " - " + comparisonsCount + " comparisons, " + swapCount + " swaps, " + delayMilis + " ms delay", 
				0, g.getFont().getSize());
		
		//drawing lines
		for(int i=0;i<array.length*lineWidth; i+=lineWidth) {
			g.setColor(getColor(colors[i/lineWidth]));
			g.fillRect(i, SCREEN_HEIGHT-array[i/lineWidth], lineWidth, SCREEN_HEIGHT-(SCREEN_HEIGHT-array[i/lineWidth]));
		}
		unmark();
	}
	
}
