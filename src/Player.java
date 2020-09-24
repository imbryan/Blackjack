import javax.swing.*;

public class Player{
	private String userName;
	private String passWord;
	private int chips;
	private String accountType;
	private Card hand[];
	private int total;
	private int hCount;


public Player(){
	userName = "";
	passWord = "";
	chips    = 0;
	accountType = "Normal";
	total = 0;
	hand = new Card [5];
	hCount = 0;

}

public Player(String un, String pw, int ch, String at){
	userName = un;
	passWord = pw;
	chips    = ch;
	accountType = at;
	hand = new Card [5];
}

public Player(String un, String pw, int ch, String at, int to, Card ha[], int hc){
	userName = un;
	passWord = pw;
	chips = ch;
	accountType = at;
	total = to;
	hand = ha;
	hCount = hc;

}

public void clearHand(){
	hand = new Card [5];
	hCount = 0;
	total = 0;
}

public String getAccountType(){
	return accountType;
}

public String getPlayerName(){
	return userName;
}

public String getPassWord(){
	return passWord;
}

public int getChips(){
	return chips;
}

public void setChips(int newchips){
	chips=newchips;
}

public void setPlayerName(String name){
	userName = name;
}


public String toString(){
	return "Cards in Hand "+hand;
}

public void printCards(){
	for (int i=0;i<hCount;i++) {
		if(hand[i].getCardValue()==1){
			System.out.println("Ace of "+hand[i].getCardSuit());
		}
		else if(hand[i].getCardIndex()==11){
			System.out.println("Jack of "+hand[i].getCardSuit());
		}
		else if(hand[i].getCardIndex()==12){
			System.out.println("Queen of "+hand[i].getCardSuit());
		}
		else if(hand[i].getCardIndex()==13){
			System.out.println("King of "+hand[i].getCardSuit());
		}
		else {
			System.out.println(hand[i]);
		}
	}
}

public boolean hasAce(){
	for (int i=0;i<hCount;i++){
		if (hand[i].getCardIndex()==1) {
			return true;
		}
	}
	return false;
}

public int getValueOfNthCard(int handIndex){
	return hand[handIndex].getCardValue();
}

public String getNthCard(int handIndex){
	return hand[handIndex].toString();
}

public void addCard(Card c) {
	hand[hCount]=c;
	hCount++;
}

public int getTotal(){
	int sum = 0;
	for(int i=0;i<hCount; i++){
		if(hand[i].getCardValue() !=1){
			sum += hand[i].getCardValue();
		}
		else if(hand[i].getCardValue()==1){
		int a=sum+1;
		int b=sum+11;
    		if(a<=21 && sum>=11) {
    			sum=a;
    		}   
    		else {
    			sum=b;
    		}

    	}
  


}
	total=sum;
	return total;
}

//end class
}