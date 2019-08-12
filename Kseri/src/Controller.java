import java.util.LinkedList;

import javax.swing.SwingUtilities;

public class Controller {
	MiddleCards middleCards;
	Opponent opponent;
	private Deck deck;
	boolean starting=true;
	String message;
	boolean last=true;
	boolean tof=true;
	boolean end=false;
	LinkedList<String> CardName = new LinkedList<String>(); 
	public Controller(Deck deck,Players player,Opponent opponent,MiddleCards middleCards) {
		this.deck=deck;
		message="";
		this.opponent=opponent;
		this.middleCards=middleCards;
		for(int i=0;i<middleCards.CardName.size();i++) {
			CardName.add(middleCards.getCard(i));
		}
	}


	public void tick(Players player ) {
		if(player.getCardsInHand()!=6)
			starting=false;


		if(!starting) {


			if(!middleCards.isItEmpty()) {
				CardName.clear();
				if((middleCards.CardName.size()==2)) {
					
					for(int i=0;i<middleCards.CardName.size();i++) {
						CardName.add(middleCards.getCard(i));
					}

					if(deck.CardDeckId(CardName.get(CardName.size()-1))==deck.CardDeckId(CardName.get(CardName.size()-2)) && deck.CardDeckId(CardName.get(CardName.size()-1))=='J') {
						player.setPlayerPoints(20);

					} else if(deck.CardDeckId(CardName.get(CardName.size()-1))==deck.CardDeckId(CardName.get(CardName.size()-2))) {
						player.setPlayerPoints(10);
					}
				} 
				
				if(!(middleCards.CardName.size()==1)) {

					for(int i=0;i<middleCards.CardName.size();i++) {
						CardName.add(middleCards.getCard(i));
					}

//					System.out.print("\n\n");
//					for(int i=0;i<CardName.size();i++) {
//						System.out.print(deck.CardDeckId(CardName.get(i))+"\n");
//					}
//					System.out.print("\n\n");



					if(deck.CardDeckId(CardName.get(CardName.size()-1))==deck.CardDeckId(CardName.get(CardName.size()-2)) ||deck.CardDeckId(CardName.get(CardName.size()-1))=='J') {

						for(int i=0;i<middleCards.CardName.size();i++) {
							if(deck.CardDeckId(CardName.get(i))=='A'||deck.CardDeckId(CardName.get(i))=='J') {
								player.setPlayerPoints(1);
							}

							if(deck.CardDeckId2(CardName.get(i))=="diamonds" && deck.CardDeckId(CardName.get(i))=='T') {
								player.setPlayerPoints(3);
							}

							if(deck.CardDeckId2(CardName.get(i))=="clubs   " && deck.CardDeckId(CardName.get(i))=='2') {
								player.setPlayerPoints(2);

							}
						}
						player.setCardsTaken(middleCards.getMiddleCardsSize());
						if(player.getCardsTaken()>26 && tof) {
							player.setPlayerPoints(3);
							tof=false;}
						message =String.format((player.name()+
								" took the cards with "
								+deck.CardDeckId(CardName.get(CardName.size()-1))
								+" of "+  deck.CardDeckId2(CardName.get(CardName.size()-1)) + "\n"));
						 
					
									
						 middleCards.EmptyAll();
						
						
						Game.last=player.name();
						
					}
				}
			}
		}

		
	}
	public void getlastCards(Players player) {

		
			
			if((middleCards.CardName.size()==1)) {
				CardName.clear();
					CardName.add(middleCards.getCard(0));
				
				
				if(deck.CardDeckId(CardName.get(0))=='A'||deck.CardDeckId(CardName.get(0))=='J')
					player.setPlayerPoints(1);
			if(deck.CardDeckId2(CardName.get(0))=="diamonds" && deck.CardDeckId(CardName.get(0))=='T') 
				player.setPlayerPoints(3);
				if(deck.CardDeckId2(CardName.get(0))=="clubs   " && deck.CardDeckId(CardName.get(0))=='2') 
					player.setPlayerPoints(2);
				
				player.setCardsTaken(middleCards.getMiddleCardsSize());

				if(player.getCardsTaken()>26 && tof) {
					player.setPlayerPoints(3);
					tof=false;}

				middleCards.EmptyAll();
				
				
			}else if(!middleCards.isItEmpty()) {
				CardName.clear();
				for(int i=0;i<middleCards.CardName.size();i++) {
					CardName.add(middleCards.getCard(i));
				}
				
				for(int i=0;i<middleCards.CardName.size();i++) {
					if(deck.CardDeckId(CardName.get(i))=='A'||deck.CardDeckId(CardName.get(i))=='J') {
						player.setPlayerPoints(1);
					}

					if(deck.CardDeckId2(CardName.get(i))=="diamonds" && deck.CardDeckId(CardName.get(i))=='T') {
						player.setPlayerPoints(3);
					}

					if(deck.CardDeckId2(CardName.get(i))=="clubs   " && deck.CardDeckId(CardName.get(i))=='2') {
						player.setPlayerPoints(2);

					}
				}
				player.setCardsTaken(middleCards.getMiddleCardsSize());

				if(player.getCardsTaken()>26 && tof) {
					player.setPlayerPoints(3);
					tof=false;}

				middleCards.EmptyAll();
			}
		}
		
	
}
