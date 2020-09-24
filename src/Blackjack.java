import java.util.*;
import java.io.*;
import javax.swing.*;
public class Blackjack{

static Player players[] = new Player[5000];
static int pcount = 0;

private static Blackjack instance;

public static Blackjack getInstance(){
	if(instance==null){
		instance=new Blackjack();
	}
	return instance;
}

public Blackjack(){
	try {
		File playerslist = new File("players.txt");
		if(playerslist.exists()==false){
			playerslist.createNewFile();
		}

		Scanner s2 = new Scanner(playerslist);
			while(s2.hasNextLine()){
				String username = s2.nextLine();
				String password = s2.nextLine();
				int chips       = Integer.parseInt(s2.nextLine());
				String accounttype = s2.nextLine();
				players[pcount] = new Player(username,password,chips,accounttype);
				pcount++;
			}
	}catch(Exception e){
		System.out.println(e);
	}
}

public void save(){
	try{
		File playerslist = new File("players.txt");
		FileWriter fw = new FileWriter(playerslist, false);
		PrintWriter pw = new PrintWriter(fw);
		for(int i=0;i<pcount;i++){
			pw.println(players[i].getPlayerName());
			pw.println(players[i].getPassWord());
			pw.println(players[i].getChips());
			pw.println(players[i].getAccountType());
		}
		pw.close();
	}catch(Exception e){
		System.out.println(e);
	}
}

public int getPCount(){
	return pcount;
}

public void registerPlayer(String un, String pw, int ch, String at){
	players[pcount]=new Player(un,pw,ch,at);
	pcount++;
	save();
}

public int getPlayerIndex(String un){
	for(int i=0;i<pcount;i++){
		if(players[i].getPlayerName().equals(un)){
			return i;
		}
	}
	return 1337;
}

public boolean loginCheck(String un,String pw){
	for(int i=0;i<pcount;i++){
		if(players[i].getPlayerName().equals(un)&&players[i].getPassWord().equals(pw)){
			return true;
		}
	}
	return false;
}

public boolean doesAccountExist(String un){
	for(int i=0;i<pcount;i++){
		if(players[i].getPlayerName().equals(un)){
			return true;
		}
	}
	return false;
}

public int findPlayer(String un){
	for(int i=0;i<pcount;i++){
		if (players[i].getPlayerName().equals(un)){
			return i;
		}
	}
	return 1337;
}

public int getPlayersChips(String un){
	for(int i=0;i<pcount;i++){
		if (players[i].getPlayerName().equals(un)){
			return players[i].getChips();
		}
	}
	return 1337;
}

public void subtractChips(String un,int bet){
	for(int i=0;i<pcount;i++){
		if(players[i].getPlayerName().equals(un)){
			int tempChips=players[i].getChips()-bet;
			players[i].setChips(tempChips);
		}
	}

	save();
}

public void addChips(String un,int pot){
	for(int i=0;i<pcount;i++){
		if(players[i].getPlayerName().equals(un)){
			int tempChips =players[i].getChips()+pot;
			players[i].setChips(tempChips);
		}
	}

	save();
}


public void shuffleDeck(Card deck[]){
	Random r = new Random();

	for (int i=0;i<deck.length;i++){
		Card temp ;
		int slot1 = i;
		int slot2 = r.nextInt(deck.length);

		temp=deck[slot2];
		deck[slot2]=deck[slot1];
		deck[slot1]=temp;
	}
}

public void proxyAddCard(String un, Card c){
	players[getPlayerIndex(un)].addCard(c);
}

public int proxyGetTotal(String un){
	return players[getPlayerIndex(un)].getTotal();
}

public void proxyClearHand(String un){
	players[getPlayerIndex(un)].clearHand();
}

public static void main(String args[]){

/*
	Scanner s = new Scanner(System.in);

for (int i=0;i<13;i++){
	deck[i]=new Card(Integer.toString(i+1), "Diamonds");
	deck[i+13]=new Card(Integer.toString(i+1), "Clubs");
	deck[i+26]=new Card(Integer.toString(i+1), "Spades");
	deck[i+39]=new Card(Integer.toString(i+1), "Hearts");
}

int nameSession = 1;
int gameSession = 1;
while (nameSession==1){

int pot = 0;


System.out.println("Sign in with your username");
String name = s.nextLine();
coins = 500;
while (gameSession==1){
int bust = 0;
Player one = new Player();
one.setPlayerName(name);


Player dealer = new Player();
//String dealerString = "Dealer";
dealer.setPlayerName("Dealer");

System.out.println("Would you like to 1- Play, or 2- Exit Game Session");
int firstWall = s.nextInt();

if (firstWall==1){
shuffleDeck(deck);
System.out.println("Deck has been shuffled");
int bet = 0;
while(true){
System.out.println("How much are you betting (You have "+coins+" coins)");

bet = s.nextInt();
if(bet<1||bet>coins){
	System.out.println("Invalid bet. Try again");
}
else{
	break;
}
}

coins=coins-bet;
pot=bet+bet;


int top = 0;
one.addCard(deck[top]);
top++;

//Dealer's first card
dealer.addCard(deck[top]);
System.out.println("");
System.out.println("Dealer's face up card is:");
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
System.out.println(deck[top]);
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
int handIndex = 0;
System.out.println("Dealer's total: "+dealer.getValueOfNthCard(handIndex));
top++;

one.addCard(deck[top]);
top++;

//Dealer's face-down card
dealer.addCard(deck[top]);
top++;

System.out.println("");
System.out.println("You have been dealt:");
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");

one.printCards();
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");

System.out.println("Current Total: "+one.getTotal());
System.out.println("");
if (one.getTotal()==21){
	System.out.println("BLACKJACK!");
}

System.out.println("Pot: "+pot);
int thisWhile = 0;
while(thisWhile==0){

System.out.println("1- Hit, or 2- Stand");
int secondWall = s.nextInt();
if (secondWall==1) {
	System.out.println("------------------------------------------------");
	System.out.println("Dealer's face up card is:");
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");

	System.out.println(dealer.getNthCard(handIndex));
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	System.out.println("Dealer's total: "+dealer.getValueOfNthCard(handIndex));
	System.out.println("");
	System.out.println("HIT!");
	one.addCard(deck[top]);
	top++;
	System.out.println("You have:");
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	one.printCards();
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	System.out.println("Current Total: "+one.getTotal());
	System.out.println("");

	if (one.getTotal()>21){
		thisWhile=1;
		bust=1;
	}
	else {
		System.out.println("Would you like to...");
	}
}
else if (secondWall==2) {
	System.out.println("You have decided to stand");
	System.out.println("You have:");
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	one.printCards();
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	System.out.println("Current Total: "+one.getTotal());
	System.out.println("");
	thisWhile=1;
}
else {
	System.out.println("You didn't enter a valid option");
}

//End ThisWhile
}

if (bust==0){
System.out.println("");
System.out.println("Dealer's turn. Dealer has:");
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
dealer.printCards();
System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
System.out.println("Dealer's total: "+dealer.getTotal());
System.out.println("");

while (dealer.getTotal()<17) {
	dealer.addCard(deck[top]);
	System.out.println("Dealer draws "+deck[top]+". Dealer has:");
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	dealer.printCards();
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	System.out.println("Dealer's total: "+dealer.getTotal());
	top++;

	if (dealer.getTotal()>21) {
		System.out.println("BUST!");
	}

}

while (){
	dealer.addCard(deck[top]);
	System.out.println("Dealer draws "+deck[top]+". Dealer has:");
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	dealer.printCards();
	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	System.out.println("Dealer's total: "+dealer.getTotal());
	System.out.println("");
	top++;

	if (dealer.getTotal()>21) {
		System.out.println("BUST!");
	}
}


//End Player Bust Check
}

else {
	System.out.println("BUST!");
}

if (dealer.getTotal()==one.getTotal() || (dealer.getTotal()>one.getTotal() && dealer.getTotal()<=21) || bust==1){
	System.out.println("Dealer wins");
}
else {
	System.out.println("Player wins");
	coins=pot+coins;
	System.out.println("You won: "+(pot-bet)+" coins on a bet of "+bet+". You now have "+coins+" coins");
}


//End First Wall
}


else if (firstWall==2){
	break;
}
else {
	System.out.println("Not a valid option. Try again");
}

//End gameSession While
}

System.out.println("You have reached the end of the game. Would you like to 1- Start a new game, or 2- Exit?");
nameSession=s.nextInt();
s.nextLine();

//End nameSession While
}

System.out.println("Thank u for playing");
*/
//End Main
}
//End Class
}