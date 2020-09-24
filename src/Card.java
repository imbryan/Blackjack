import javax.swing.*;

public class Card{
	private String cardValue;
	private String cardSuit;
	private ImageIcon cardImage;

	private String cardIndex;

	public Card(){
		cardValue="";
		cardSuit="";
	}

	public Card(String cv, String cs, String ci){
		cardIndex=cv;
		if(cv.equals("11")||cv.equals("12")||cv.equals("13")){
			cardValue="10";
		}
		else{
			cardValue=cv;
		}

		cardSuit=cs;

		cardImage=new ImageIcon(ci);

	}

	public String toString(){
		if(cardIndex.equals("1")){
		return "Ace of "+cardSuit;
		}
		else if(cardIndex.equals("11")){
		return "Jack of "+cardSuit;
		}
		else if(cardIndex.equals("12")){
		return "Queen of "+cardSuit;
		}
		else if(cardIndex.equals("13")){
		return "King of "+cardSuit;
		}
		else{
		return cardValue+" of "+cardSuit;
		}
	}

	public ImageIcon getCardImage(){
		return cardImage;
	}

	public void setCardImage(String ci){
		cardImage=new ImageIcon(ci);
	}

	public int getCardValue(){
		if (cardValue.equals("J") || cardValue.equals("Q") || cardValue.equals("K")) {
			return 10;
		}
		else if (cardValue.equals("A")) {
			return 1;
		}
		else {
		return Integer.parseInt(cardValue); 
		}
	}

	public String getCardSuit(){
		return cardSuit;
	}

	public int getCardIndex(){
		return Integer.parseInt(cardIndex);
	}

}