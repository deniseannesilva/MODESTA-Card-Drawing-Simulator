package objects;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Deck {
	public static final int SIZE = 52;
	public static final int SUIT_SIZE = 13;
	public static final int RANK_SIZE = 4;
	public static final int RANKS[] = {1,2,3,4,5,6,7,8,9,10,11,12,13};
	public static final String SUITS[] = {"Clubs", "Diamonds", "Hearts", "Spades"};
	public static final String CARDPATH[] = {"/res/cards/ace_of_clubs.png", "/res/cards/2_of_clubs.png", "/res/cards/3_of_clubs.png",
	"/res/cards/4_of_clubs.png", "/res/cards/5_of_clubs.png", "/res/cards/6_of_clubs.png", "/res/cards/7_of_clubs.png",
	"/res/cards/8_of_clubs.png", "/res/cards/9_of_clubs.png", "/res/cards/10_of_clubs.png", "/res/cards/jack_of_clubs.png",
	"/res/cards/queen_of_clubs.png", "/res/cards/king_of_clubs.png", "/res/cards/ace_of_diamonds.png", "/res/cards/2_of_diamonds.png", 
	"/res/cards/3_of_diamonds.png", "/res/cards/4_of_diamonds.png", "/res/cards/5_of_diamonds.png", "/res/cards/6_of_diamonds.png", 
	"/res/cards/7_of_diamonds.png", "/res/cards/8_of_diamonds.png", "/res/cards/9_of_diamonds.png", 
	"/res/cards/10_of_diamonds.png", "/res/cards/jack_of_diamonds.png", "/res/cards/queen_of_diamonds.png", 
	"/res/cards/king_of_diamonds.png", "/res/cards/ace_of_hearts.png", "/res/cards/2_of_hearts.png", 
	"/res/cards/3_of_hearts.png", "/res/cards/4_of_hearts.png", "/res/cards/5_of_hearts.png", "/res/cards/6_of_hearts.png", 
	"/res/cards/7_of_hearts.png", "/res/cards/8_of_hearts.png", "/res/cards/9_of_hearts.png", 
	"/res/cards/10_of_hearts.png", "/res/cards/jack_of_hearts.png", "/res/cards/queen_of_hearts.png", 
	"/res/cards/king_of_hearts.png", "/res/cards/ace_of_spades.png", "/res/cards/2_of_spades.png", 
	"/res/cards/3_of_spades.png", "/res/cards/4_of_spades.png", "/res/cards/5_of_spades.png", "/res/cards/6_of_spades.png", 
	"/res/cards/7_of_spades.png", "/res/cards/8_of_spades.png", "/res/cards/9_of_spades.png", 
	"/res/cards/10_of_spades.png", "/res/cards/jack_of_spades.png", "/res/cards/queen_of_spades.png", 
	"/res/cards/king_of_spades.png"};
	
	public ArrayList<Card> cards, drawCards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		this.drawCards = new ArrayList<Card>();
		init();
	}
	
	public void init(){
		int k = 0;
		for(int j = 0; j < RANK_SIZE; j++) {
			for(int i = 0; i < SUIT_SIZE; i++){
				Card card = new Card();
				card.setRank(RANKS[i]);
				card.setSuit(SUITS[j]);
				card.setIcon(new ImageIcon(this.getClass().getResource(CARDPATH[k])));
				k++;
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
	
	public void doneDrawing() {
		drawCards.clear();
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
				while(!isDrawn(card)) {
					drawCards.add(card);
				}
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
