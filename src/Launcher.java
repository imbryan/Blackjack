import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Launcher implements ActionListener {
	JFrame win = new JFrame("Blackjack Launcher");

	JPanel loginp = new JPanel();
	JPanel playp   = new JPanel();
	private Blackjack bk;
	String un;
	String pw;
	int chips;


	JLabel namelabel = new JLabel("Username");
	JLabel passwordlabel = new JLabel("Password");
	JTextField username = new JTextField("");
	JPasswordField password = new JPasswordField("");

	JButton register = new JButton("Register");
	JButton login = new JButton("Login");
	JLabel please = new JLabel("Please enter your information");
	JLabel invalid = new JLabel("Invalid username or password, try again");
	JLabel alreadyExists = new JLabel("Account already exists..");

	JButton play = new JButton("Play Blackjack");
	JButton exit = new JButton("Exit Game");
	JButton logout = new JButton("Logout");

	JLabel invalidReg = new JLabel("Cannot register with given information");

		JLabel welcome = new JLabel("Welcome, "+un+"! You have "+chips+" chips.");


	public Launcher(){
		

		bk = Blackjack.getInstance();

		loginp.setLayout(new GridLayout(3,2));
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginp.add(namelabel);
		loginp.add(username);
		loginp.add(passwordlabel);
		loginp.add(password);
		password.setEchoChar('*');
		loginp.add(register);
		register.addActionListener(this);
		loginp.add(login);
		login.addActionListener(this);

		playp.setLayout(new GridLayout(3,1));
		playp.add(play);
		playp.add(logout);
		playp.add(exit);
		play.addActionListener(this);
		logout.addActionListener(this);
		exit.addActionListener(this);

		win.setLayout(new GridLayout(2,1));
		win.add(please);
		win.add(loginp);
		win.setSize(400,250);
		win.setVisible(true);


	}

public void updateFields(){
	un=username.getText();
	char[] a=password.getPassword();
	pw= new String(a);
	chips=bk.getPlayersChips(un);
}

public void setChips(int chi){
	win.setVisible(true);
	chips=chi;
	welcome.setText("Welcome, "+un+"! You have "+chips+" chips.");
}

public void actionPerformed(ActionEvent e){
	if (register==e.getSource()){
		updateFields();
		if(bk.doesAccountExist(un)==false){
			if(!un.equals("")&&!pw.equals("")){
			bk.registerPlayer(un,pw,1000,"Normal");
			updateFields();
			win.remove(invalid);
			win.remove(please);
			win.remove(alreadyExists);
			win.remove(loginp);
			win.remove(invalidReg);

			welcome.setText("Welcome, "+un+"! You have "+chips+" chips.");
			win.add(welcome);

			win.add(playp);
			win.repaint();
			win.setVisible(true);
			}
			else {
				win.remove(please);
				win.remove(alreadyExists);
				win.remove(invalid);

				win.remove(loginp);
				win.add(invalidReg);
				win.add(loginp);
				win.repaint();
				win.setVisible(true);
			}
		}
		else if(bk.doesAccountExist(un)==true){
			win.remove(invalid);
			win.remove(please);
			win.remove(alreadyExists);
			win.remove(loginp);
			win.remove(invalidReg);
			win.add(alreadyExists);
			win.add(loginp);

			win.repaint();
			win.setVisible(true);

			username.setText("");
			password.setText("");
		}

	}
	else if(login==e.getSource()){
		updateFields();

		if(bk.loginCheck(un,pw)==true){
			updateFields();

			win.remove(invalid);
			win.remove(please);
			win.remove(alreadyExists);
			win.remove(loginp);
			win.remove(invalidReg);
			
			welcome.setText("Welcome, "+un+"! You have "+chips+" chips.");
			win.add(welcome);

			
			win.add(playp);

			win.repaint();
			win.setVisible(true);
		}
		else if(bk.loginCheck(un,pw)==false){
			win.remove(invalid);
			win.remove(please);
			win.remove(alreadyExists);
			win.remove(loginp);
			win.remove(invalidReg);
			win.add(invalid);
			win.add(loginp);
			win.repaint();
			win.setVisible(true);

			username.setText("");
			password.setText("");
		}
	}
	else if(exit==e.getSource()){
		System.exit(0);
	}
	else if(logout==e.getSource()){
		win.remove(playp);
		win.remove(welcome);
		win.add(please);
		win.add(loginp);
		win.repaint();
		win.setVisible(true);

		username.setText("");
		password.setText("");
	}

	else if(play==e.getSource()){
		Game app=new Game(this,un,pw,chips);
		win.setVisible(false);
	}
}

public static void main(String args[]){
	Launcher app=new Launcher();
}
}