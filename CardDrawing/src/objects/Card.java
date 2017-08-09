package objects;

import javax.swing.ImageIcon;

public class Card implements Cloneable{

	public String suit;
	public int rank;
	public ImageIcon icon;
	
	public Card(){
		
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  

	public Card(int rank, String suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
}
