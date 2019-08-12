import java.awt.Graphics;

public interface Players {

	int CardsInHand = 0;
	public void tick();
	public void render(Graphics g);
	int getCardsInHand();
	void setCardsInHand(int i);
	String getCardname(int index);
	boolean getbot();
	int getX(int index);
	void setX(int index,int x);
	int getY(int index);
	void setY(int index,int y);
	public void setPlayerPoints(int index);
	public int getPlayerPoints();
	public int getCardsTaken();
	public void setCardsTaken(int x);
	public String name();
}
