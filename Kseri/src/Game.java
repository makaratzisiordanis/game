import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;



public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 520;
	public static final int HEIGHT = WIDTH/12 *9;
	public static final int SCALE=2;
	public final String TITLE= "2D Space Game";
	public static String last;
	public StringBuilder Text;

	private boolean running=false;
	private Thread thread;
	private String score;


	private BufferedImage background=null,DeckImage=null,Middle=null,lastCard=null;
	private JTextArea chatWindow;
	BufferedImageLoader loader ;
	private Graphics g;
	private Deck deck;
	private Player player;
	private Opponent opponent;
	private MouseInput mouse;
	private  MiddleCards middleCards;
	private Controller controller;
	static int round=0;
	public static boolean yourTurn=true;
	public void init() throws IOException {


		this.addMouseListener(new MouseInput());
		deck=new Deck();
		mouse= new MouseInput();
		loader =new BufferedImageLoader();
		middleCards =new MiddleCards(deck);

		try {
			background=loader.loadImage("/background2.png");
			DeckImage=loader.loadImage("/card_back.png");
			Middle=loader.loadImage("/card_placeholder.png");
			lastCard=loader.loadImage(deck.CardDeckName(deck.getDeck(51)));

		}catch(IOException e) {
			e.printStackTrace();
		}
		opponent=new Opponent(this,deck,middleCards);
		player=new Player(this,deck);
		controller=new Controller( deck,player, opponent, middleCards);

		
		score=String.valueOf(player.getPlayerPoints())+"-"+String.valueOf(opponent.getPlayerPoints());

		

	}








	private synchronized void start() {
		if (running)
			return;
		running =true;
		thread =new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if(!running)
			return;

		running =false;
		try {
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();

		}
		System.exit(1);
	}




	public void run() {
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long lastTime=System.nanoTime();
		final double amountOfTicks=60.0;
		double ns = 1000000000/amountOfTicks;
		double delta =0;
		int updates=0;
		int frames=0;
		long timer = System.currentTimeMillis();

		while(running) {
			long now = System.nanoTime();
			delta+= (now- lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				try {
					tick();
				} catch (IOException e) {
					e.printStackTrace();
				}
				updates++;
				delta--;
			}
			render();
			frames++;

			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				//	System.out.println(updates+" Ticks, Fps "+frames);
				updates=0;
				frames=0;

			}
		}
		stop();
	}
	public void tick() throws IOException {

		if(!(opponent.getCardsInHand()==0) || !(player.getCardsInHand()==0)   ) {

			if(yourTurn) {
				try {
					mouse.mousePotition(player,g,middleCards);
				}catch(Exception e) {}
				player.tick();
				controller.tick(player);
			}else {
				opponent.tick();
				yourTurn=!yourTurn;
				controller.tick(opponent);
			}

		}else if(round!=3)   { 

			round++;
			mouse.index2=10;
			player.getHand(deck,round);
			opponent.getHand(deck,round);
			player.setCardsInHand(6);
			opponent.setCardsInHand(6);
			player.handPotition();
			opponent.handPotition();
			opponent.nextone=0;


		}else if(round==3) {

			if(last=="player") {
				controller.getlastCards(player);
			}else {
				controller.getlastCards(opponent);
			}
			System.out.print("\n\nThe end of this round");
			System.out.printf("\nPlayer took %s cards: " ,player.getCardsTaken());
			System.out.printf("\nOpponent took %s cards: " ,opponent.getCardsTaken());
		
		
						player.setCardsTaken(-player.getCardsTaken());
						opponent.setCardsTaken(-opponent.getCardsTaken());
						controller.tof=true;
						
			
			round=-1;
			System.out.print("\nPlayer points :"+player.getPlayerPoints());
			System.out.print("\nOpponent points :"+opponent.getPlayerPoints()+"\n");

			deck=new Deck();
			middleCards.firstFour(deck);
			lastCard=loader.loadImage(deck.CardDeckName(deck.getDeck(51)));
			score=String.valueOf(player.getPlayerPoints())+"-"+String.valueOf(opponent.getPlayerPoints());


			if(player.getPlayerPoints()>100 && player.getPlayerPoints()>opponent.getPlayerPoints())
				System.out.print("You won\n");
			if(opponent.getPlayerPoints()>100 && opponent.getPlayerPoints()>player.getPlayerPoints())
				System.out.print("You lost\n");
			}

		


	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		g = bs.getDrawGraphics();
		g.drawImage(background,0,0,null);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("Player vs Opponent", 40, 70);
		g.drawString(""+score, 40, 100);
		g.drawString(""+controller.message, 40, 750);
		if(round!=3) {
		g.drawImage(lastCard,(int) (WIDTH*SCALE*1.5/2-72/2-15),HEIGHT*SCALE/2-96/2,null);
		g.drawImage(DeckImage,(int) (WIDTH*SCALE*1.5/2-72/2),HEIGHT*SCALE/2-96/2,null);
		}
		
		g.drawImage(Middle,(int) (WIDTH*SCALE/2-72/2),HEIGHT*SCALE/2-96/2,null);
		

		mouse.render(g);
		opponent.render(g);
		middleCards.render(g);
		player.render(g);



		g.dispose();//diagrafei
		bs.show();//  pairnoume buffer nmz

	}







	public static void main (String args[]) {
		Game game=new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		JFrame frame =new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();  //extend of the window class,not JFrame. Packs everything together
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	
	}
















}
