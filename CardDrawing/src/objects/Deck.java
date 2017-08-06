package objects;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	public static final int SIZE = 52;
	public static final int SUIT_SIZE = 13;
	public static final int RANK_SIZE = 4;
	public static final int RANKS[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
	public static final String SUITS[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
	public ArrayList<Card> cards, drawCards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		this.drawCards = new ArrayList<Card>();
		init();
	}
	
	public void init(){
		for(int j = 0; j < RANK_SIZE; j++) {
			for(int i = 0; i < SUIT_SIZE; i++){
				Card card = new Card();
				card.setRank(RANKS[i]);
				card.setSuit(SUITS[j]);
				cards.add(card);
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
	
	public ArrayList<Card> draw(int num, boolean withReplacement){
		drawCards.clear();
		Card card;
		for(int i = 0; i < num; i++) {			
			if(withReplacement){
				card = getRandomCard();
				drawCards.add(card);
			} else {
				card = getRandomCard();
				while(!isDrawn(card))
					drawCards.add(card);
			}
		}
		return drawCards;
	}
	
	public Card getRandomCard() {
		Random rand = new Random();
		int nextCard = rand.nextInt(SIZE);
		Card card = cards.get(nextCard);
		return card;
	}
	
	public boolean isDrawn(Card card){
		return drawCards.contains(card);
	}
		
}
