import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame implements ActionListener{

	private Blackjack bk;
	private Launcher la;

	String username;
	int chips;

	int actualBet;

	JPanel playerSpace = new JPanel();
	JPanel dealerSpace = new JPanel();

	JPanel dealerButtons = new JPanel();
	JPanel playerButtons = new JPanel();
	JButton stand = new JButton("Stand");
	JButton hit = new JButton("Hit");
	JTextField betAmnt=new JTextField("");
	JButton bet=new JButton("Bet");

	JLabel pot = new JLabel(" ");
	JLabel dealerPoints=new JLabel(" ");

	JLabel playerPoints=new JLabel(" ");

	JLabel dealerFirst = new JLabel(" ");
	JLabel dealerSecond = new JLabel(" ");
	JLabel dealerThird = new JLabel(" ");
	JLabel dealerFourth = new JLabel(" ");
	JLabel dealerFifth = new JLabel(" ");

	JLabel playerFirst = new JLabel(" ");
	JLabel playerSecond = new JLabel(" ");
	JLabel playerThird = new JLabel(" ");
	JLabel playerFourth = new JLabel(" ");
	JLabel playerFifth = new JLabel(" ");

	JLabel enterBet = new JLabel("Please enter a bet");

		ImageIcon back=new ImageIcon("Cards/back.png");

	Card deck[] = new Card [52];
	int deckCount = 0;

	Player dealer = new Player();
	int dealerCount = 0;

	int top=0;
	int hits=0;

	int playersIndex;
	public Game(Launcher la, String un, String pw, int ch){
		this.la=la;
		bk=Blackjack.getInstance();
		username=un;
		chips=ch;
		playersIndex=bk.getPlayerIndex(username);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/*
		String betString = JOptionPane.showInputDialog(game, "Enter your bet","Betting Window");
		while(betString==null){
			betString = JOptionPane.showInputDialog(game, "Invalid bet. Try again","Betting Window");
		}
		int bet = Integer.parseInt(betString);
		while(bet>chips||bet<=0){
			betString = JOptionPane.showInputDialog(game, "Invalid bet. Try again.","Betting Window");
			bet = Integer.parseInt(betString);
		}*/

		dealerButtons.add(pot);
		dealerButtons.add(dealerPoints);

		playerButtons.add(betAmnt);
		playerButtons.add(bet);
		bet.addActionListener(this);


		dealerButtons.setLayout(new GridLayout(2,1));
		playerButtons.setLayout(new GridLayout(2,1));


		dealerSpace.setLayout(new GridLayout(1,6));
		playerSpace.setLayout(new GridLayout(1,6));

		enterBet.setText("You have "+chips+" chips. Enter a bet");
		this.add(enterBet);

		playerSpace.add(playerButtons);
		this.add(playerSpace);


		this.setLayout(new GridLayout(2,1));
		this.setSize(300,200);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		/*LEFT OFF HERE 4/22*/
		actualBet=Integer.parseInt(betAmnt.getText());
		if (bet==e.getSource()){
			if(actualBet<=0||actualBet>chips){
				pot.setText("Invalid bet. You have "+chips+" chips. Enter a bet");
				betAmnt.setText("");
			}
			else{
		this.setSize(1200,700);

		this.remove(enterBet);
		playerSpace.remove(playerButtons);
		this.remove(playerSpace);
		dealerSpace.add(dealerFirst);
		dealerSpace.add(dealerSecond);
		dealerSpace.add(dealerThird);
		dealerSpace.add(dealerFourth);
		dealerSpace.add(dealerFifth);
		dealerSpace.add(dealerButtons);
		this.add(dealerSpace);

		playerSpace.add(playerFirst);
		playerSpace.add(playerSecond);
		playerSpace.add(playerThird);
		playerSpace.add(playerFourth);
		playerSpace.add(playerFifth);
		playerSpace.add(playerButtons);
		this.add(playerSpace);


				
			pot.setText("Pot: "+(actualBet*2));
			bk.subtractChips(username,actualBet);
			chips=chips-actualBet;
			playerButtons.remove(betAmnt);
			playerButtons.remove(bet);
			playerButtons.setLayout(new GridLayout(3,1));
			playerButtons.add(playerPoints);
			playerButtons.add(hit);
			hit.addActionListener(this);
			playerButtons.add(stand);
			stand.addActionListener(this);
			this.repaint();
			this.setVisible(true);

			initializeDeck();
			bk.shuffleDeck(deck);

			bk.proxyAddCard(username, deck[top]);
			playerFirst.setIcon(deck[top].getCardImage());
			top++;
			

			dealer.addCard(deck[top]);
			dealerFirst.setIcon(deck[top].getCardImage());
			top++;

			bk.proxyAddCard(username, deck[top]);
			playerSecond.setIcon(deck[top].getCardImage());
			top++;

			dealerPoints.setText("Dealer's Total: "+dealer.getTotal());

			dealer.addCard(deck[top]);
			dealerSecond.setIcon(back);
			top++;

			playerPoints.setText("Player's Total: "+bk.proxyGetTotal(username));
			

			}
		}
		else if (hit==e.getSource()){

			if(hits==0){
			bk.proxyAddCard(username, deck[top]);
			playerThird.setIcon(deck[top].getCardImage());
			top++;
			}
			else if(hits==1){
			bk.proxyAddCard(username, deck[top]);
			playerFourth.setIcon(deck[top].getCardImage());
			top++;
			}
			else if(hits==2){
			bk.proxyAddCard(username, deck[top]);
			playerFifth.setIcon(deck[top].getCardImage());
			top++;
			}

			if(bk.proxyGetTotal(username)>21){
				playerPoints.setText("Player's Total: "+bk.proxyGetTotal(username));
				JOptionPane.showMessageDialog(this, "Bust");
				this.dispose();
			}
			playerPoints.setText("Player's Total: "+bk.proxyGetTotal(username));
			hits++;

		}
		else if(stand==e.getSource()){
			hit.removeActionListener(this);

			dealerSecond.setIcon(deck[3].getCardImage());
			dealerPoints.setText("Dealer's Total: "+dealer.getTotal());
			int dealerCards =2;
			while(dealer.getTotal()<17 || (dealer.getTotal()==17 && bk.proxyGetTotal(username)>dealer.getTotal()) || (dealer.getTotal()==17 && bk.proxyGetTotal(username)==17) || (dealer.getTotal()<21 && dealer.hasAce()==true)){
			
				if(dealer.getTotal()>17){
					break;
				}

			dealer.addCard(deck[top]);

			if(dealerCards==2){
			dealerThird.setIcon(deck[top].getCardImage());
			}

			else if(dealerCards==3){
			dealerFourth.setIcon(deck[top].getCardImage());
			}

			else if(dealerCards==4){
			dealerFifth.setIcon(deck[top].getCardImage());
			}

			top++;
			dealerCards++;
			dealerPoints.setText("Dealer's Total: "+dealer.getTotal()); 

			}





			if(dealer.getTotal()>21){
				JOptionPane.showMessageDialog(this, "Player Wins");
				bk.addChips(username,actualBet*2);
				chips=chips+(actualBet*2);
				this.dispose();
			}
			if(dealer.getTotal()<bk.proxyGetTotal(username)){
				JOptionPane.showMessageDialog(this, "Player Wins");
				bk.addChips(username,actualBet*2);
				chips=chips+(actualBet*2);
				this.dispose();
			}
			if(dealer.getTotal()>17&&dealer.getTotal()==bk.proxyGetTotal(username)){
				JOptionPane.showMessageDialog(this, "Dealer Wins");
				this.dispose();

			if(dealer.getTotal()>bk.proxyGetTotal(username)&&dealer.getTotal()<=21){
				JOptionPane.showMessageDialog(this, "Dealer Wins");
				this.dispose();
			}
			if(dealer.getTotal()>bk.proxyGetTotal(username)){
				JOptionPane.showMessageDialog(this, "Dealer Wins");
				this.dispose();
			}

				/*
				TO DO LIST 4/26
				-clear hand after game.dispose
				-advance game post-stand
				-update launcher after game.dispose
				*/
			}

		}

	}

	public void dispose(){
		la.setChips(chips);


		bk.proxyClearHand(username);
		super.dispose();

		

	}

	public void initializeDeck(){
	ImageIcon img[]=new ImageIcon[52];


for (int i=0;i<13;i++){
	deck[i]=new Card(Integer.toString(i+1), "Diamonds","Cards/Diamonds/"+(i+1)+".png");
	deck[i+13]=new Card(Integer.toString(i+1), "Clubs","Cards/Clubs/"+(i+1)+".png");
	deck[i+26]=new Card(Integer.toString(i+1), "Spades","Cards/Spades/"+(i+1)+".png");
	deck[i+39]=new Card(Integer.toString(i+1), "Hearts","Cards/Hearts/"+(i+1)+".png");
}
}

	public static void main(String args[]){

	}
}