import java.util.ArrayList;
import java.util.Scanner;

import objects.Card;
import objects.Deck;

public class CardDrawingSimulator {
	public static final String FILENAME = "CardDrawLog.txt";
	public static final String FILENAME_CSV = "CardDrawLogCSV.csv";
	Logger log;
	int numCards, numTrials;
	int from, to;
	Scanner sc;
	Deck deck;
	Boolean validDraw, validTrial;
	
	public CardDrawingSimulator(Logger log){
		this.log = log;
		init();
	}
	
	public void init(){
		validDraw = true;
		validTrial = true;
		numCards = 0;
		numTrials = 0;
		deck = new Deck();
	}
	
	public void start(){
		sc = new Scanner(System.in);
//		while(validDraw) {
//			System.out.print("Please input number of cards (1 to 5): ");
//			numCards = sc.nextInt();
//			if(validateDraw(numCards))
//				validDraw = true;
//			else validDraw = false;
//		}
//		
//		while(validTrial) {
//			System.out.print("Please enter number of trials (10 to 100000): ");
//			numTrials = sc.nextInt();
//			if(validateTrials(numTrials))
//				validTrial = true;
//			else validTrial = false;
//		}
		
		System.out.print("From what trials: ");
		from = sc.nextInt();
		System.out.print("To what trials: ");
		to = sc.nextInt();
		for(int i = from; i <= to; i++) {
			for(int j = 1; j <= 5; j++) {
				numTrials = i;
				numCards = j;
				deck.shuffle();
				drawWith(numCards, numTrials);
				drawWithout(numCards, numTrials);
			}
		}
		System.out.println("done");
	}
	
	public boolean validateDraw(int x) {
		if(x > 5 || x < 1) {
			System.out.println("Invalid Input. Try Again");
			return true;
		}
		return false;
	}
	
	public boolean validateTrials(int x) {
		if(x > 100000 || x < 1) {
			System.out.println("Invalid Input. Try Again");
			return true;
		}
		return false;
	}
	
	public void drawWith(int numCards, int numTrials) {
		ArrayList<Card> resultCards;
		int sum = 0 ;
//		System.out.println("\nWith Replacement Results");
		log.writeFile(FILENAME, "WITH REPLACEMENT");
		for(int i = 0; i < numTrials; i++) {
			log.writeFile(FILENAME, "====================TRIAL " + (i+1) + "====================");
//			System.out.println("Trial # " + (i+1));
			resultCards = deck.draw(numCards, true);
			sum = getSumCard(resultCards);
			displayTrial(resultCards);
//			System.out.println("Sum: " + sum);
			log.writeCSV(FILENAME_CSV, numTrials, i+1, numCards, sum, true);
		}
	}
	
	public void drawWithout(int numCards, int numTrials) {
		ArrayList<Card> resultCards;
		int sum = 0 ;
//		System.out.println("\nWithout Replacement Results");
		log.writeFile(FILENAME, "WITHOUT REPLACEMENT");
		for(int i = 0; i < numTrials; i++) {
			log.writeFile(FILENAME, "====================TRIAL " + (i+1) + "====================");
//			System.out.println("Trial # " + (i+1));
			resultCards = deck.draw(numCards, false);
			sum = getSumCard(resultCards);
			displayTrial(resultCards);
//			System.out.println("Sum: " + sum);
			log.writeCSV(FILENAME_CSV, numTrials, i+1, numCards, sum, false);
		}
	}
	
	public void displayTrial(ArrayList<Card> result) {
		for(int i = 0; i < result.size(); i++) {
//			System.out.println("Card "+(i+1)+": " + result.get(i).getRank() + " " + result.get(i).getSuit());
			log.writeFile(FILENAME, "Card "+(i+1)+": " + result.get(i).getRank() + " " + result.get(i).getSuit());
		}
	}
	
	public int getSumCard(ArrayList<Card> result) {
		int sum = 0;
		for(int i = 0; i < result.size(); i++) {
			sum += result.get(i).getRank();
		}
		return sum;
	}
}
