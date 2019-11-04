/* Project 2 
 * Team Members: Masashi Kawabata, SungBeom Park
 * 
 * We pledge we did not collaborate with others in this project. 
 */

import java.util.Scanner; 
public class Driver {
   public static void main(String[] args)
   {
      Deck deck;
      
      //Problem 1
      
      deck = new Deck();
      System.out.println("BEFORE SHUFFLE: ");
      System.out.println(deck);

      System.out.println("AFTER SHUFFLE: ");
      deck.shuffleDeck(1000000);// Shuffle 5000 times 
      System.out.println(deck);
      
      //Problem 2
      
      Card[] player1 = new Card[5];
      Card[] player2 = new Card[5];
      Card[] remain = new Card[42];

      int i;
      
      for ( i = 0; i < 5; i++ )
      {
         player1[i] = deck.dealHands();
         player2[i] = deck.dealHands();
      }
      
      for(i = 0; i < 42; i++) {
          remain[i] = deck.dealHands();
      }
      
      System.out.println("PLAYER 1's HAND: ");
      for( i = 0; i < 5; i++) {
         System.out.print( player1[i] + "  ");
      }
      System.out.println();
      System.out.println();


      System.out.println("PLAYER 2's HAND: ");
      for( i = 0; i < 5; i++) {
         System.out.print( player2[i] + "  ");
      }
      System.out.println();
      System.out.println();

      
      System.out.println("REMAINING DECK: ");
      for(i = 0; i < 42; i++) {
    	  System.out.print(remain[i] + "  ");
      }
      
      System.out.println();
      System.out.println();
      
      //Problem 3
      
      System.out.println("PLAYER 1 SCORE:  " +  Hands.evaluateHand(player1) );
      Hands.printHandName(player1);
      
      System.out.println();
      
      System.out.println("PLAYER 2 SCORE: " +  Hands.evaluateHand(player2) );
      Hands.printHandName(player2);
      
      System.out.println();

      if ( Hands.evaluateHand(player1) > Hands.evaluateHand(player2) ) {
         System.out.println("PLAYER 1 wins!");
      }
      else if ( Hands.evaluateHand(player1) < Hands.evaluateHand(player2) ) {
         System.out.println("PLAYER 2 wins!");
      }

      System.out.println();
      
      //Problem 4
      
      Card[] hand_one = new Card [5]; //Player1 hand						
      
      Card[] hand_two = new Card [5]; //Player2 hand
      
      System.out.println ("Enter your first hand manually, consisting of a hand of cards (10 characters) in the form RSRS... R being rank and S being suit");
      
      Scanner s = new Scanner (System.in);
      
      for (int j = 0; j< 5; j++) {

    	  	System.out.println ("Enter hand in form (rs). Rank followed by a comma, followed by suite. For example '4,D': ");
    	  	String user = s.next();
    	  	
    	  	String [] parts = user.split(",");//split method to split the rank and suit
    	  	
    	  	String rank = parts[0];
    	  	String suite = parts[1];
    	     	  	
    	  	int rank_int;
    	  	
    	  	/*
    	  	 * Conditional statements for non numeric ranks
    	  	 * created a local variable which uses the numerical value to use as an input for parseInt method 
    	  	 */
    	  	    	  	
    	  	if (rank.equals("J") || rank.equals(("j"))) {
    	  		
    	  		String joker = "11";
        	  	rank_int = Integer.parseInt(joker);

    	  	}
    	  	else if (rank.equals("Q") || rank.equals(("q"))) { 
    	  		String queen = "12";
        	  	rank_int = Integer.parseInt(queen);
    	  		
    	  	}
    	  	
    	  	else if (rank.equals("K") || rank.equals(("k"))) { 
    	  		String king = "13";
        	  	rank_int = Integer.parseInt(king);
    	  		
    	  	}
    	  	
    	  	else if (rank.equals("A") || rank.equals(("a"))) { 
    	  		String ace = "14";
        	  	rank_int = Integer.parseInt(ace);
    	  		
    	  	}
    	  	
    	  	/*
    	  	 * If the card isn't non-numerical, it simply goes parseInt method without any changes  
    	  	 */
    	  	
    	  	else {
        	  	rank_int = Integer.parseInt(rank);
    	  	}

    	  	int suite_int = 0;
    	  	
    	  	if (suite.equals("D") || suite.equals(("d"))) {
    	  		
    	  		suite_int = 1;
    	  	}
    	  	
    	  	else if (suite.equals("C") || suite.equals(("c"))) { 
    	  		
    	  		suite_int = 2; 
    	  		
    	  	}
    	  	else if (suite.equals("H") || suite.equals(("h"))) { 
    	  		
    	  		suite_int = 3; 
    	  		
    	  	}
    	  	
    	  	else if (suite.equals("S") || suite.equals(("s"))){ 
    	  		suite_int = 4; 	 
    	  	}
    	  	
    	  	hand_one [j] = new Card (suite_int,rank_int);	
    	  	
      }
      
      System.out.println();
      System.out.println();
      
      System.out.println ("Enter your second hand manually, consisting of a hand of cards (10 characters) in the form RSRS... R being rank and S being suit");
 
      
      for (int j = 0; j< 5; j++) {

    	  	System.out.println ("Enter hand in form (rs). Rank followed by a comma, followed by suite. For example '4,D' ");
    	  	String user = s.next();
    	  	
    	  	String [] parts = user.split(",");
    	  	
    	  	String rank = parts[0];
    	  	String suite = parts[1];
    	  	
    	  	int suite_int = 0;
    	  	
    	  	int rank_int;
    	  	
    	  	if (rank.equals("J") || rank.equals(("j"))) {
    	  		String joker = "11";
        	  	rank_int = Integer.parseInt(joker);
    	  	}
    	  	else if (rank.equals("Q") || rank.equals(("q"))) { 
    	  		String queen = "12";
        	  	rank_int = Integer.parseInt(queen);
    	  		
    	  	}
    	  	
    	  	else if (rank.equals("K") || rank.equals(("k"))) { 
    	  		String king = "13";
        	  	rank_int = Integer.parseInt(king);
    	  		
    	  	}
    	  	
    	  	else if (rank.equals("A") || rank.equals(("a"))) { 
    	  		String ace = "14";
        	  	rank_int = Integer.parseInt(ace);
    	  		
    	  	}
    	  	
    	  	else {
        	    rank_int = Integer.parseInt(rank);
    	  	}
    	  	
    	  	suite_int = 0;
    	  	
    	  	
    	  	if (suite.equals("D") || suite.equals(("d"))) {
    	  		
    	  		suite_int = 1;
    	  	}
    	  	
    	  	else if (suite.equals ("C") || suite.equals(("c"))) { 
    	  		
    	  		suite_int = 2; 
    	  		
    	  	}
    	  	else if (suite.equals ("H") || suite.equals(("h"))) { 
    	  		
    	  		suite_int = 3; 
    	  		
    	  	}
    	  	
    	  	else if (suite.equals ("S") || suite.equals(("s"))) { 
    	  		
    	  		suite_int = 4; 
    	  		
    	  	}
    	  	
    	  	hand_two [j] = new Card (suite_int,rank_int);
 	  	
      }
      
      System.out.println("PLAYER 1 SCORE:  " +  Hands.evaluateHand(hand_one) );
      Hands.printHandName(hand_one);
      
      System.out.println();
      
      System.out.println("PLAYER 2 SCORE: " +  Hands.evaluateHand(hand_two) );
      Hands.printHandName(hand_two);
      
      System.out.println();

      if ( Hands.evaluateHand(hand_one) > Hands.evaluateHand(hand_two) ) {
         System.out.println("PLAYER 1 wins!");
      }
      else if ( Hands.evaluateHand(hand_one) < Hands.evaluateHand(hand_two) ) {
         System.out.println("PLAYER 2 wins!");
      }
      else {
         System.out.println("Player hands are a tie");
      }
      System.out.println();

   }
}