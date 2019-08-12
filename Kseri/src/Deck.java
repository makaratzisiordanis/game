import java.util.*;

public class Deck {
	private int[] solutionArray;
private String[] card = new String[59];
HashMap<String, Character> cardId=new HashMap<String, Character>();
HashMap<String, String> cardId2=new HashMap<String, String>();
char[] pictureOfCards = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
String[] shapeOfCards = {"spade   ", "hearts  ", "diamonds", "clubs   "};

	public Deck(){ 
		solutionArray = new int[52];
		for(int i=0;i<52;i++) {
			solutionArray[i]=i;
		}
		
		
		
		Shuffle.shuffleArray(solutionArray);
		for (int i = 0; i < solutionArray.length; i++)
	    {
	    }
		  CardDeckAnimation();
		  CreateHashMap();
	}



	public int getDeck(int number){
		return solutionArray[number];
	}



public void CardDeckAnimation() {
	card[0]= "/card_00.png";
	 StringBuffer s;
	for(int i=1;i<10;i++) {
		card[i]="";
	 s = new StringBuffer(card[0]);
	 s.append(card[i]);
	 s.replace(7, 8, String.valueOf(i));
	 card[i]= s.toString();
	}
	
	for(int i=10;i<52;i++) {
		card[i]="";
	  s = new StringBuffer(card[0]);
	 s.append(card[i]);
	 s.replace(6, 8, String.valueOf(i));
	 card[i]= s.toString();
	}
	card[52]="/card_back.png";
	card[53]="/card_joker_black.png";
	card[54]="/card_joker_red.png";
	card[55]="/card_placeholder.png";
	

}

public String CardDeckName(int index) {
	return  card[index];
	
}
public Character CardDeckId(String name) {
	return  cardId.get(name);
}


public String CardDeckId2(String name) {
	return  cardId2.get(name);
}
public void CreateHashMap() {
	for(int j=0;j<4;j++)
	for(int i=0;i<13;i++) {
		cardId.put(card[j*13+i],pictureOfCards[i]);
		cardId2.put(card[j*13+i],shapeOfCards[j]);
	}
}

}

