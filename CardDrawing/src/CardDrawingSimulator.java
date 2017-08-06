import java.util.ArrayList;
import java.util.Scanner;

import objects.Card;
import objects.Deck;

public class CardDrawingSimulator {
	
	public static final String FILENAME = "CardDrawLog.txt";
	Logger log;
	int numCards, numTrials;
	Scanner sc;
	Deck deck;
	
	public CardDrawingSimulator(Logger log){
		this.log = log;
		init();
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
		
		drawCards(numCards, numTrials, deck);
	}
	
	public void drawCards(int numCards, int numTrials, Deck deck){
		ArrayList<Card> wReplacementCards, woReplacementCards;
		for(int i = 0; i < numTrials; i++){
			log.writeFile(FILENAME, "====================TRIAL " + (i+1) + "====================");
			log.writeFile(FILENAME, "WITH REPLACEMENT");
			wReplacementCards = deck.draw(numCards, true);
			logCards(wReplacementCards);
			
			log.writeFile(FILENAME, "\nWITHOUT REPLACMENT");
			woReplacementCards = deck.draw(numCards, false);
			logCards(woReplacementCards);
		}
	}
	
	public void logCards(ArrayList<Card> cards){
		for(int i = 0; i < cards.size(); i++){
			log.writeFile(FILENAME, cards.get(i).getRank() + " " + cards.get(i).getSuit());
		}
	}
}
