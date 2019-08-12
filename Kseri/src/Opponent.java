import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Opponent implements Players{
	public static int PlayerPoints=0;
	private int[] xCard;
	private int[] yCard;
	public BufferedImage[] opponentCard;
	BufferedImageLoader loader;
	Random r=new Random();
	public String[] cardname;
	private int CardsInHand;
	MiddleCards middleCards;
	final int[] A={0,1,2,3,4,5};
	int nextone;
	private int cardsTaken=0;

	public Opponent( Game game,Deck deck,MiddleCards middleCards) throws IOException {
		this.middleCards=middleCards;
		nextone=0;
		CardsInHand=6;
		loader =new BufferedImageLoader();
		cardname= new String[6];
		opponentCard=new BufferedImage[6];
		xCard=new int[6];
		yCard=new int[6];
		int round=0;
		handPotition() ;
		
		//Shuffle.shuffleArray(A);
	
	
		
		getHand(deck,round);
		
		
	}

	public void handPotition() {
		xCard[0]=400;
		xCard[1]=450;
		xCard[2]=500;
		xCard[3]=550;
		xCard[4]=600;
		xCard[5]=650;	
		for(int i=0;i<6;i++) {
			yCard[i]=100;
		}
	}


public void getHand(Deck deck,int round) throws IOException{
	for(int i=0;i<6;i++) {
		cardname[i]=deck.CardDeckName(deck.getDeck(i+round*6*2+6+4));
		opponentCard[i]=loader.loadImage(deck.CardDeckName(52));

		
}
}
	
	public void tick() {
		int index=A[nextone];
		//mouse.releaseCard(this ,200,200,index);
		try {
//		for(int yside=100;yside<Game.HEIGHT*Game.SCALE/2+r.nextInt(96)-48;yside++)	{
		middleCards.tick(this, index,Game.HEIGHT*Game.SCALE/2+r.nextInt(96)-48,Game.WIDTH*Game.SCALE/2+r.nextInt(72)-36);
//		}
		this.setCardsInHand(getCardsInHand()-1);
		setX(-100,index);
		}catch(Exception e) {
			
		}
		nextone++;
		}

	public void render(Graphics g) {
		for(int i=0;i<6;i++) {
		g.drawImage(opponentCard[i],(int)xCard[i],(int)yCard[i],null);
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

public void setCardname(String x,int index) {
this.cardname[index]=x;
}
public String getCardname(int index) {
	return this.cardname[index];
}




public void setCardsInHand(int CardsInHand) {
this.CardsInHand=CardsInHand;
}
public int getCardsInHand() {
	return CardsInHand;
}



@Override
public boolean getbot() {
	return true;
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
	return "opponent";
}

}
