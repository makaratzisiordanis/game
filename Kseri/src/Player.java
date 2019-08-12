import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player implements Players{
	public  int PlayerPoints=0;
	private int[] xCard;
	private int[] yCard;
	public BufferedImage[] playerCard;
	BufferedImageLoader loader;
	Random r=new Random();
	public String[] cardname;
	private int CardsInHand;
	private int cardsTaken=0;

	public Player( Game game,Deck deck) throws IOException {
		CardsInHand=6;
		loader =new BufferedImageLoader();
		cardname= new String[6];
		playerCard=new BufferedImage[6];
		xCard=new int[6];
		yCard=new int[6];		
		int round=0;
	
		handPotition();
		getHand(deck,round);
	
		
		}
	
	

	
	public void getHand(Deck deck,int round) throws IOException{
		for(int i=0;i<6;i++) {
			cardname[i]=deck.CardDeckName(deck.getDeck(i+round*6*2+4));
			playerCard[i]=loader.loadImage(cardname[i]);
	}
	}
	public void handPotition() {
		xCard[0]=400;
		xCard[1]=450;
		xCard[2]=500;
		xCard[3]=550;
		xCard[4]=600;
		xCard[5]=650;
		for(int i=0;i<6;i++) {
			yCard[i]=600;
		}
	}
	
	

	public void tick() {
		

	}

	public void render(Graphics g) {
		for(int i=0;i<6;i++) {
		g.drawImage(playerCard[i],(int)xCard[i],(int)yCard[i],null);
		}
	}
public void setY(int y,int index) {
this.yCard[index]=y;
}
public void setX(int x,int index) {
this.xCard[index]=x;
}
public int getX(int index) {
	return xCard[index];
}
public int getY(int index) {
	return yCard[index];
}
public void setCardsInHand(int CardsInHand) {
this.CardsInHand=CardsInHand;
}
public int getCardsInHand() {
	return CardsInHand;
}




@Override
public String getCardname(int index) {
	return cardname[index];
	
}




@Override
public boolean getbot() {
	return false;
}




@Override
public void setPlayerPoints(int index) {
	PlayerPoints+=index;
	
}




@Override
public int getPlayerPoints() {
	return PlayerPoints;
}




@Override
public int getCardsTaken() {
	return cardsTaken;
}




@Override
public void setCardsTaken(int x) {
	cardsTaken+=x;
	
}




@Override
public String name() {
	// TODO Auto-generated method stub
	return "player";
}


}


