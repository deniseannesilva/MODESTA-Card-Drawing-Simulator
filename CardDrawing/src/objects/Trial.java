package objects;

import java.util.ArrayList;

public class Trial {
	ArrayList<Card> drawnWith, drawnWithout;
	int sumWith, sumWithout;
	
	public Trial() {}
	
	public int getSumWith() {
		return sumWith;
	}

	public void setSumWith(int sumWith) {
		this.sumWith = sumWith;
	}
	
	public int getSumWithout() {
		return sumWithout;
	}



	public void setSumWithout(int sumWithout) {
		this.sumWithout = sumWithout;
	}

	public ArrayList<Card> getDrawnWith() {
		return drawnWith;
	}

	public void setDrawnWith(ArrayList<Card> drawnWith) {
		this.drawnWith = drawnWith;
	}

	public ArrayList<Card> getDrawnWithout() {
		return drawnWithout;
	}

	public void setDrawnWithout(ArrayList<Card> drawnWithout) {
		this.drawnWithout = drawnWithout;
	}
}
