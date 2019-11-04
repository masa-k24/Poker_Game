/* Project 2 
 * Team Members: Masashi Kawabata, SungBeom Park
 * 
 * We pledge we did not collaborate with others in this project. 
 */

public class Deck {
   public static final int NUMCARDS = 52;// Constant for a deck of cards 

   private Card[] deckOfCards;         // Contains 52 cards
   private int currentCard;            // deal THIS card in deck

   public Deck( )
   {
      deckOfCards = new Card[NUMCARDS];

      int i = 0;

      for ( int suit = 1; suit <= 4; suit++ )
         for ( int rank = 1; rank <= 13; rank++ )
             deckOfCards[i++] = new Card(suit, rank);

      currentCard = 0;
   }

   public void shuffleDeck(int n)
   {
      int x, y, z;

      for ( z = 0; z < n; z++ ){
	  x = (int) ( NUMCARDS * Math.random() );  // Pick 2 random cards in the deck
	  y = (int) ( NUMCARDS * Math.random() );

	  Card temp = deckOfCards[x]; // temp will hold onto the card of int x
	  deckOfCards[x] = deckOfCards[y]; // position x card will be replaced to position y card
	  deckOfCards[y] = temp;	// position y will be replaced to temp card 
      }

      currentCard = 0;   // Reset current card to deal
   }

   public Card dealHands() {
      while( currentCard < NUMCARDS ){
      
         return ( deckOfCards[ currentCard++ ] );// adds the card into the array 
      }
      return null;
   }
   
   public String toString(){
      String s = "";
      int z;

      z = 0;
      for ( int i = 0; i < 4; i++ )
      {
         for ( int j = 1; j <= 13; j++ )
             s += (deckOfCards[z++] + "  ");

         s += "\n";
      }
      return ( s );
   }
   
}