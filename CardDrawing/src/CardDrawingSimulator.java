import java.util.ArrayList;
import java.util.Scanner;

import objects.Deck;

public class CardDrawingSimulator {
	
	int numCards, numTrials;
	Scanner sc;
	Deck deck;
	
	public CardDrawingSimulator(){
		init();
		start();
	}
	
	public void init(){
		numCards = 0;
		numTrials = 0;
		deck = new Deck();
	}
	
	public void start(){
		sc = new Scanner(System.in);
		
		System.out.println("Please input number of cards (1 to 5): ");
		numCards = sc.nextInt();
		System.out.println("Please enter number of trials: ");
		numTrials = sc.nextInt();
		
		deck.shuffle();
		logFile(numCards, numTrials, deck);
	}
	
	public void logFile(int numCards, int numTrials, Deck deck){
		for(int i = 0; i < numTrials; i++){
			
		}
	}
}
