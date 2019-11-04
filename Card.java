/* Project 2 
 * Team Members: Masashi Kawabata, SungBeom Park
 * 
 * We pledge we did not collaborate with others in this project. 
 */


public class Card {
	
	/*
	 * The asterisk is created so that the positioning is easier
	 * For example, by having two asterisks on rank, I could call number 2 with the same index number [2].
	 */
	
	private static final String[] Suit = { "*", "diamonds", "clubs", "hearts", "spades"};
	private static final String[] Rank = { "*", "*", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	private int cardSuit;
	private int cardRank;
	
	public Card( int suit, int rank )
	{
	   if ( rank == 1 )
	      cardRank = 14;     // Ace has the highest rank, give the score of 14.
	   else
	      cardRank = rank;
	
	   cardSuit = suit;
	}
	
	public int getSuit()
	{
	   return cardSuit;         
	}
	
	public int getRank()
	{
	   return cardRank ;
	}
	
	public String toString()
	{
	   return ( Rank[cardRank] + " of " + Suit[cardSuit] );
	}
}