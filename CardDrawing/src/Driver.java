public class Driver {
	public static void main(String[] args) {
		Logger log = new Logger();
		CardDrawingSimulator cd = new CardDrawingSimulator(log);
		cd.start();
	}
}
