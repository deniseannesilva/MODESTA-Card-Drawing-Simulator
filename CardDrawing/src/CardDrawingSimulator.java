import java.util.ArrayList;
import java.util.Scanner;

import objects.Card;
import objects.Deck;

public class CardDrawingSimulator {
	
	Logger log;
	int numCards, numTrials;
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
		while(validDraw) {
			System.out.print("Please input number of cards (1 to 5): ");
			numCards = sc.nextInt();
			if(validateDraw(numCards))
				validDraw = true;
			else validDraw = false;
		}
		
		while(validTrial) {
			System.out.print("Please enter number of trials (10 to 100000): ");
			numTrials = sc.nextInt();
			if(validateTrials(numTrials))
				validTrial = true;
			else validTrial = false;
		}

		deck.shuffle();
		drawWith(numCards, numTrials);
		drawWithout(numCards, numTrials);
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
		System.out.println("\nWith Replacement Results");
		for(int i = 0; i < numTrials; i++) {
			System.out.println("Trial # " + (i+1));
			resultCards = deck.draw(numCards, true);
			displayTrial(resultCards);
		}
	}
	
	public void drawWithout(int numCards, int numTrials) {
		ArrayList<Card> resultCards;
		System.out.println("\nWithout Replacement Results");
		for(int i = 0; i < numTrials; i++) {
			System.out.println("Trial # " + (i+1));
			resultCards = deck.draw(numCards, false);
			displayTrial(resultCards);
		}
	}
	
	public void displayTrial(ArrayList<Card> result) {
		for(int i = 0; i < result.size(); i++) {
			System.out.println("Card "+(i+1)+": " + result.get(i).getRank() + " " + result.get(i).getSuit());
		}
	}
}
