import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class MiddleCards {
	private static String CardUp;
	private BufferedImage middle=null;
	BufferedImageLoader loader ;
	LinkedList<Integer> gx = new LinkedList<Integer>(); 
	LinkedList<Integer> gy = new LinkedList<Integer>(); 
	LinkedList<String> CardName = new LinkedList<String>(); 
	String[] cardname=new String[4];

	public MiddleCards(Deck deck)  {

		int x=Game.HEIGHT*Game.SCALE/2;
		int y=Game.WIDTH*Game.SCALE/2-36;
		loader =new BufferedImageLoader();
		for(int i=0;i<4;i++) {
			deck.getDeck(i);

			cardname[i]=deck.CardDeckName(deck.getDeck(i));

			try {
				middle=loader.loadImage((cardname[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			gx.add(x);
			gy.add(y+=10);
			CardName.add(	cardname[i]);
		}

	}

	public void tick(Players player,int index,int x,int y) throws IOException {

		CardUp=player.getCardname(index);
		middle=loader.loadImage((CardUp));
		gx.add(x);
		gy.add(y);
		CardName.add(CardUp);
		
	}
	public String getCard(int i){
		return CardName.get(i);
	}
	public LinkedList<String>  getCardAll(){
		return CardName;
	}
	public int getMiddleCardsSize(){
		return CardName.size();
	}
	
	public boolean isItEmpty() {
		return CardName.isEmpty();
	}
	
	public void EmptyAll() {
		CardName.clear();
		gx.clear();
		gy.clear();
	}



	public void render(Graphics g)  {
		for(int i=0;i<gx.size();i++) {
			try {
				middle=loader.loadImage((CardName.get(i)));

			}catch(Exception e) {
			}
			g.drawImage(middle,gy.get(i)-36,gx.get(i)-48,null);
			
			
		}
	}

	public void firstFour(Deck deck) {
		int x=Game.HEIGHT*Game.SCALE/2;
		int y=Game.WIDTH*Game.SCALE/2-36;
		for(int i=0;i<4;i++) {
			deck.getDeck(i);

			cardname[i]=deck.CardDeckName(deck.getDeck(i));

			try {
				middle=loader.loadImage((cardname[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}

			gx.add(x);
			gy.add(y+=10);
			CardName.add(	cardname[i]);
		}
	}



}


