/* Project 2 
 * Team Members: Masashi Kawabata, SungBeom Park
 * 
 * We pledge we did not collaborate with others in this project. 
 */


public class Hands {
	
	/*
	 * Score system to include so we can know which player wins 
	 */
	
	public static final int STRAIGHTFLUSH = 10000000; 
	
	public static final int FOUROFAKIND = 9000000; 
	                                          
	public static final int FULLHOUSE = 8000000; 
	
	public static final int FLUSH = 7000000;  
	
	public static final int STRAIGHT = 6000000;   
	
	public static final int THREEOFAKIND = 5000000;    
	
	public static final int TWOPAIRS = 4000000;     
	
	public static final int ONEPAIR = 3000000;      
	                                          
	
	/*
	 * Created a method that checks if the hand classify on any of the hands 
	 */
	
	public static void printHandName(Card[] hand) {
		if ( isFlush(hand) && isStraight(hand) ) {//Has to satisfy both conditions. 
			  System.out.println("The hand is Straight flush!");
		   }
		   else if ( is4s(hand) ) {
			   System.out.println("The hand is Four of a kind.");
		   }
		   else if ( isFullHouse(hand) ) {
			  System.out.println("The hand is Full house.");
		   }
		   else if ( isFlush(hand) ) {
			  System.out.println("The hand is Flush.");
		   }
		   else if ( isStraight(hand) ) {
			  System.out.println("The hand is Straight.");
		   }
		   else if ( is3s(hand) ) {
			  System.out.println("The hand is Three of a kind.");
		   }
		   else if ( is22s(hand) ) {
			  System.out.println("The hand is Two pairs.");
		   }
		   else if ( is2s(hand) ) {
			  System.out.println("The hand is Pair.");
		   }
		   else {
			  System.out.println("The hand is High card.");
		   }
		
	}
	
	public static int evaluateHand(Card[] hand)// The general method to evaluate hands, instead of calling each method one by one. 
	{
	   if ( isFlush(hand) && isStraight(hand) ) {//Has to satisfy both conditions. 
		   return valueStraightFlush(hand);
	   }
	   else if ( is4s(hand) ) {
	      return valueFourOfAKind(hand);
	   }
	   else if ( isFullHouse(hand) ) {
	      return valueFullHouse(hand);
	   }
	   else if ( isFlush(hand) ) {
	      return valueFlush(hand);
	   }
	   else if ( isStraight(hand) ) {
	      return valueStraight(hand);
	   }
	   else if ( is3s(hand) ) {
	      return valueThreeOfAKind(hand);
	   }
	   else if ( is22s(hand) ) {
	      return valueTwoPairs(hand);
	   }
	   else if ( is2s(hand) ) {
	      return valueOnePair(hand);
	   }
	   else {
	      return valueHighCard(hand);
	   }
	}
	
	/*
	 * Valuation Method below, some of the classifications include high card/middle card value so it prevents tie. 
	 */

	public static int valueStraightFlush( Card[] hand ){
		
	   return STRAIGHTFLUSH + valueHighCard(hand);
	}
	
	/* ---------------------------------------------------------
	   valueFourOfAKind(): return value of a 4 of a kind hand
	   In case the two hands have 4 of a kind, get the 3rd card rank and add that into the score system.
	   --------------------------------------------------------- */
	public static int valueFourOfAKind( Card[] hand )
	{
	   sortRank(hand);
	
	   return FOUROFAKIND + hand[2].getRank();
	}
	
	/* -----------------------------------------------------------
	   valueFullHouse(): return value of a Full House hand
	   In case two players get full house, use the 3 of a kind rank to determine score 
	   position [2] will always be 3 of a kind card.
	   ----------------------------------------------------------- */
	public static int valueFullHouse( Card[] hand )
	{
	   sortRank(hand);
	
	   return FULLHOUSE + hand[2].getRank();
	}
	
	
	/* -----------------------------------------------------
	   valueFlush(): return value of a Flush hand
	         value = FLUSH + valueHighCard()
	   ----------------------------------------------------- */
	public static int valueFlush( Card[] hand )
	{
		return FLUSH + valueHighCard(hand);
	}
	
	/* -----------------------------------------------------
	   valueStraight(): return value of a Straight hand
	
	         value = STRAIGHT + valueHighCard()
	   ----------------------------------------------------- */
	public static int valueStraight( Card[] hand )
	{
	   return STRAIGHT + valueHighCard(hand);
	}
	
	/* ---------------------------------------------------------------
	   In case when two players get three of a kind, score system will also look at the ranks 	   
	   hand[2] will always have the three of a kind card. 
	   --------------------------------------------------------------- */
	public static int valueThreeOfAKind( Card[] hand )
	{
	   sortRank(hand);
		   
	   return THREEOFAKIND + hand[2].getRank();
	   
	}
	
	/* -----------------------------------------------------
	   valueTwoPairs(): return value of a Two-Pairs hand
	
	         value = TWOPAIRS
	                + 14*14*HighPairCard
	                + 14*LowPairCard
	                + UnmatchedCard
	   ----------------------------------------------------- */
	public static int valueTwoPairs( Card[] hand )
	{
	   int sco = 0;
	
	   sortRank(hand);
	
	   if ( hand[0].getRank() == hand[1].getRank() &&
	        hand[2].getRank() == hand[3].getRank() )// When the two pairs are the first 4 cards
	      sco = 14*14*hand[2].getRank() + 14*hand[0].getRank() + hand[4].getRank();
	   else if ( hand[0].getRank() == hand[1].getRank() &&
	             hand[3].getRank() == hand[4].getRank() )// Two pairs other than the 3rd card. 
	      sco = 14*14*hand[3].getRank() + 14*hand[0].getRank() + hand[2].getRank();
	   else 
	      sco = 14*14*hand[3].getRank() + 14*hand[1].getRank() + hand[0].getRank();
		   
	   return TWOPAIRS + sco;
	}
	
	/* -----------------------------------------------------
	   valueOnePair(): return value of a One-Pair hand
	
	         value = ONEPAIR 
	                + 14^3*PairCard
	                + 14^2*HighestCard
	                + 14*MiddleCard
	                + LowestCard
	   ----------------------------------------------------- */
	public static int valueOnePair( Card[] hand )
	{
	   int sco = 0;
	
	   sortRank(hand);
	/*
	 * Created all combinations of one pair and calculate the sco for the strength of the rest of the hands 
	 * This is used in case when two players both get one pair.
	 */
	   
	   if ( hand[0].getRank() == hand[1].getRank() ) {
	      sco = 14*14*14*hand[0].getRank() +  
	             + hand[2].getRank() + 14*hand[3].getRank() + 14*14*hand[4].getRank();
	   }
	   else if ( hand[1].getRank() == hand[2].getRank() ) {
	      sco = 14*14*14*hand[1].getRank() +  
	             + hand[0].getRank() + 14*hand[3].getRank() + 14*14*hand[4].getRank();
	   }
	   else if ( hand[2].getRank() == hand[3].getRank() ) {
	      sco = 14*14*14*hand[2].getRank() +  
	             + hand[0].getRank() + 14*hand[1].getRank() + 14*14*hand[4].getRank();
	   }
	   else {
	      sco = 14*14*14*hand[3].getRank() +  
	             + hand[0].getRank() + 14*hand[1].getRank() + 14*14*hand[2].getRank();
	   }
		   
	   return ONEPAIR + sco;
	}
	
	/* -----------------------------------------------------
	   This method is very helpful in case when two hands have equal hands
	   Easier to calculate which player wins 
	   valueHighCard(): return value of a high card hand
	
	         sco =  14^4*highestCard 
	                + 14^3*2ndHighestCard
	                + 14^2*3rdHighestCard
	                + 14^1*4thHighestCard
	                + LowestCard
	   ----------------------------------------------------- */
	public static int valueHighCard( Card[] hand )
	{
	   int sco;
	
	   sortRank(hand);
	
	   sco = hand[0].getRank() + 14* hand[1].getRank() + 14*14* hand[2].getRank() 
	         + 14*14*14* hand[3].getRank() + 14*14*14*14* hand[4].getRank();
	
	   return sco;
	}
	
	/*
	 * 
	 * 
	 * Below this comment code is all of the classification methods (including ordersuit and orderrank for easy implementation.
	 * 
	 * 
	 */

	public static boolean is4s( Card[] hand )
	{
	   boolean choice1, choice2;
	
	   sortRank(hand);//Makes it easier to see if four cards have same rank 
	
	   choice1 = hand[0].getRank() == hand[1].getRank() &&
	        hand[1].getRank() == hand[2].getRank() &&
	        hand[2].getRank() == hand[3].getRank() ;
	
	   choice2 = hand[1].getRank() == hand[2].getRank() &&
	        hand[2].getRank() == hand[3].getRank() &&
	        hand[3].getRank() == hand[4].getRank() ;
	
	   return( choice1 || choice2 );
	}

	public static boolean isFullHouse( Card[] hand )
	{
	   boolean choice1, choice2;
	
	   sortRank(hand);
	
	   choice1 = hand[0].getRank() == hand[1].getRank() &&  // Rank pattern of:  x x x y y
	        hand[1].getRank() == hand[2].getRank() &&
	        hand[3].getRank() == hand[4].getRank();
	
	   choice2 = hand[0].getRank() == hand[1].getRank() &&  //Rank pattern of: x x y y y 
	        hand[2].getRank() == hand[3].getRank() &&
	        hand[3].getRank() == hand[4].getRank();
	
	   return( choice1 || choice2 );
	   
	}
	
	public static boolean isFlush( Card[] hand ) {
		
		   sortSuit(hand);
		
		   return( hand[0].getSuit() == hand[4].getSuit() );   // If the suits of first and last cards are the same, its flush 
		}

	public static boolean isStraight( Card[] hand ){
		   int i, upRank;
		
		   sortRank(hand);
		
		   if ( hand[4].getRank() == 14 )// having ace will give more options to do straight
		   {
		      boolean option1 = hand[0].getRank() == 2 && hand[1].getRank() == 3 && // Order of:  ace, two, three, four, five 
		                  hand[2].getRank() == 4 && hand[3].getRank() == 5 ;
		      
		      boolean option2 = hand[0].getRank() == 10 && hand[1].getRank() == 11 && //Order of: ten, joker, queen, king, ace 
		                  hand[2].getRank() == 12 && hand[3].getRank() == 13 ;
		
		      return ( option1 || option2 );
		   }
		   else
		   {
		      upRank = hand[0].getRank() + 1; // check if its increasing order by 1
		
		      for ( i = 1; i < 5; i++ ){
		         if ( hand[i].getRank() != upRank)
		            return(false); // false if the value does not go up by one        
		         upRank++;
		      }
		      return(true);       
		   }
		}

	public static boolean is3s( Card[] hand )
	{
	   boolean choice1, choice2, choice3;
	
	   if ( is4s(hand) || isFullHouse(hand) )
	      return(false);        // The hand is not 3 of a kind (but good for user)
	
	   sortRank(hand);
	
	   choice1 = hand[0].getRank() == hand[1].getRank() &&
	        hand[1].getRank() == hand[2].getRank() ;
	
	   choice2 = hand[1].getRank() == hand[2].getRank() &&
	        hand[2].getRank() == hand[3].getRank() ;
	
	   choice3 = hand[2].getRank() == hand[3].getRank() &&
	        hand[3].getRank() == hand[4].getRank() ;
	
	   return( choice1 || choice2 || choice3 );
	}

	public static boolean is22s( Card[] hand )
	{
	   boolean choice1, choice2, choice3;
	
	   if ( is4s(hand) || isFullHouse(hand) || is3s(hand) ) {
	      return(false);        // The hand is not 2 pairs (but better)
	   }
	   sortRank(hand);
	
	   choice1 = hand[0].getRank() == hand[1].getRank() &&
	        hand[2].getRank() == hand[3].getRank() ;
	
	   choice2 = hand[0].getRank() == hand[1].getRank() &&
	        hand[3].getRank() == hand[4].getRank() ;
	
	   choice3 = hand[1].getRank() == hand[2].getRank() &&
	        hand[3].getRank() == hand[4].getRank() ;
	
	   return( choice1 || choice2 || choice3 );
	}

	public static boolean is2s( Card[] hand ) {
		
	   boolean choice1, choice2, choice3, choice4;
	
	   if ( is4s(hand) || isFullHouse(hand) || is3s(hand) || is22s(hand) )
	      return(false);        // The hand is not one pair, so won't work (but better for the player) 
	
	   sortRank(hand);
	
	   choice1 = hand[0].getRank() == hand[1].getRank() ;
	   choice2 = hand[1].getRank() == hand[2].getRank() ;
	   choice3 = hand[2].getRank() == hand[3].getRank() ;
	   choice4 = hand[3].getRank() == hand[4].getRank() ;
	
	   return( choice1 || choice2 || choice3 || choice4 );
	}


	
	/*
	 * The two methods below are the base method that are used to order the rank and suit to determine classification easier 
	 */
	
	public static void sortSuit( Card[] hand ) // Easier to find the flush 
	{
	   int x, y, minnum;
	
	   for ( x = 0 ; x < hand.length ; x++ )
	   {
	      minnum = x;   // assuming the first card is minimum
	
	      for ( y = x+1 ; y < hand.length ; y++ )
	      {
	         if ( hand[y].getSuit() < hand[minnum].getSuit() )
	         {
	            minnum = y;    // found a smaller minimum, update min_j     
	         }
	      }
	
	      Card card = hand[x];
	      hand[x] = hand[minnum];
	      hand[minnum] = card;
	   }
	}

	public static void sortRank( Card[] hand )//Easier to find the straight
	{
	   int x, y, minnum;
   
	   for ( x = 0 ; x < hand.length ; x++ )
	   {
	      minnum = x;  // assuming the first card is minimum
	
	      for ( y = x + 1 ; y < hand.length ; y++ )
	      {
	         if ( hand[y].getRank() < hand[minnum].getRank() )
	         {
	            minnum = y;    // found a smaller minimum, update min_j     
	         }
	      }
	
	      Card card = hand[x];
	      hand[x] = hand[minnum];
	      hand[minnum] = card;
	   }
	}



}
