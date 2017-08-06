package objects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	public static final int SIZE = 52;
	public static final String RANKS[] = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", 
									"Nine", "Ten", "Jack", "Queen", "King"};
	public static final String SUITS[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
	public ArrayList<Card> cards, drawCards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		this.drawCards = new ArrayList<Card>();
		init();
	}
	
	public void init(){
		int j = 0;
		for(int i = 0; i < SIZE; i++){
			Card card = new Card();
			cards.add(card);
			for(int numrank = 0; numrank < 13; numrank++){
				cards.get(i).setRank(RANKS[numrank]);
			}
			if((i == 13) || (i == 26) || (i == 39)){
				cards.get(i).setSuit(SUITS[j]);
				j++;
			}
		}
	}
	
	public void shuffle(){
		Random rand = new Random();
		for(int i = 0; i < cards.size(); i++){
			int randomindex = rand.nextInt(SIZE);
			swap(i, randomindex);
		}
	}
	
	public void swap(int origIndex, int nextIndex){
		Card card = cards.get(origIndex);
		cards.set(origIndex, cards.get(nextIndex));
		cards.set(nextIndex, card);
	}
	
	public Card draw(boolean withReplacement){
		Random rand = new Random();
		int nextCard = rand.nextInt(SIZE);
		Card card;
		if(withReplacement){
			card =  cards.get(nextCard);
		}
		else{
			card =  cards.get(nextCard);
			while(isDrawn(card)){
				nextCard = rand.nextInt(SIZE);
				card = cards.get(nextCard);
			}
			drawCards.add(card);
		}
		
		return card;
	}
	
	public boolean isDrawn(Card card){
		boolean isDrawn = false;
		for(int i=0; i < drawCards.size(); i++){
			if(drawCards.get(i) == card)
				isDrawn = true;
		}
		
		return isDrawn;
	}
	
	public void removeCard(){
		
	}
	
}
