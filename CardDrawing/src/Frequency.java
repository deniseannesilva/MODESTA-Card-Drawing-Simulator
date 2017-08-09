
public class Frequency {
	private int value, count;
	
	public Frequency(int value) {
		this.value = value;
		count = 0;
	}
	
	public void incrementCount() {
		count++;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
