
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MouseInput implements MouseListener {
	private static int mx=0;
	private static int my=0;
	private static int tempx=0;
	private static int tempy=0;
	private static boolean onhold=false;
	private static boolean inside=false;
	private int index;
	public int index2=100;
	private static long timer=0;
	MiddleCards middlecard;
	BufferedImageLoader loader ;
	int round1=0;
	@Override
	public void mouseClicked(MouseEvent e) {


	}

	// einai to tick, epilegei karta kai th metaferei
	public void mousePotition(Player player,Graphics g,MiddleCards middlecard) throws IOException {
		this.middlecard=middlecard;



		int mouseY = MouseInfo.getPointerInfo().getLocation().y;
		int mouseX = MouseInfo.getPointerInfo().getLocation().x;

		if(onhold && !inside) {
			for(int i=0;i<6;i++) {
				if(!inside) {
					if(player.getX(i)<mx-tempx+mouseX && player.getX(i)+72>+mx-tempx+mouseX  ) {
						if(player.getY(i)<my-tempy+mouseY && player.getY(i)+72>my-tempy+mouseY  ) {
							inside=true;
							index=i;
							if(mx+26-tempx+mouseX<Game.WIDTH*Game.SCALE && mx-tempx+mouseX>32)
								player.setX(mx-36-tempx+mouseX,i);
							if(my+32-tempy+mouseY<Game.HEIGHT*Game.SCALE && my-tempy+mouseY>46)
								player.setY(my-48-tempy+mouseY,i);
						}}}}

		}else if(onhold && inside) {
			if(mx+26-tempx+mouseX<Game.WIDTH*Game.SCALE && mx-tempx+mouseX>32)
				player.setX(mx-36-tempx+mouseX,index);
			if(my+32-tempy+mouseY<Game.HEIGHT*Game.SCALE && my-tempy+mouseY>46)
				player.setY(my-48-tempy+mouseY,index);			
		}




		if(System.currentTimeMillis()-timer<100 ) {
			if(mx-tempx+mouseX>Game.WIDTH*Game.SCALE/2-36 && mx-tempx+mouseX<Game.WIDTH*Game.SCALE/2+36 
					&& (my-tempy+mouseY>Game.HEIGHT*Game.SCALE/2-48 && my-tempy+mouseY<Game.HEIGHT*Game.SCALE/2+48)) {
				releaseCard( player,my-tempy+mouseY,mx-tempx+mouseX, index,middlecard);
				index=10;
			}
		}


	}









	public void releaseCard(Players play,int x,int y,int index,MiddleCards middlecard) {
		try {

			middlecard.tick(play,index,x,y);
		}catch(Exception e) {
		}
		play.setY(-100, index);
		play.setX(-100, index);



		play.setCardsInHand(play.getCardsInHand()-1);
		Game.yourTurn=!Game.yourTurn;

		if(play.getbot()) {

			play.setX(100,index);
			play.setY(100,index);

		}

	}







	public void render(Graphics g) {
	}



	public void mousePressed(MouseEvent e) {



		mx=e.getX();
		my=e.getY();
		if(my<=Game.WIDTH*Game.SCALE && mx<=Game.WIDTH*Game.SCALE) {
			if(mx>=0 && my>=0) {
				onhold=true;
				tempx= MouseInfo.getPointerInfo().getLocation().x;
				tempy = MouseInfo.getPointerInfo().getLocation().y;

			}
		}
	}





	@Override
	public void mouseReleased(MouseEvent e) {
		onhold=false;
		inside=false;
		timer = System.currentTimeMillis();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}







}


