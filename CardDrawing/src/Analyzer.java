import java.util.ArrayList;

import objects.Trial;


public class Analyzer {
	private ArrayList<Trial> trials;
	private ArrayList<Frequency> values;
	private ArrayList<Integer> freq;
	private ArrayList<String> label;
	
	public Analyzer(ArrayList<Trial> trials) {
		values = new ArrayList<Frequency>();
		freq = new ArrayList<Integer>();
		label = new ArrayList<String>();
		this.trials = trials;
	}
	
	public void getFrequencyCount() {
		for(int i = 0; i < trials.size(); i++) {
			if(label.contains(trials.get(i).getSumWith()+"")) {
				
			}
		}
	}
}
