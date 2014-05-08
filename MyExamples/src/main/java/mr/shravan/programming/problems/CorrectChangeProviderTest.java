package mr.shravan.programming.problems;


public class CorrectChangeProviderTest {

	public static void main(String... args){
		getCorrectChange(166);
	}
	
    public static Change getCorrectChange(int cents) {
        /*
          Please implement this method to
          take cents as a parameter
          and return an equal amount in dollars and coins using the minimum number of
          coins possible.
          For example: 164 cents = 1 dollar, 2 quarters, 1 dime and 4 cents.
          Return null if the parameter is negative.
          */	  
		  int dollars = (int) Math.ceil(cents/100);
		  int rCents = cents%100;
		  int quarters = (int) Math.ceil(rCents/25);
		  rCents = rCents%25;
		  int dimes = (int) Math.ceil(rCents/10);
		  rCents = rCents%10;
		  int nickles = (int)Math.ceil(rCents/5);
		  rCents = rCents%5;
		  
		  //int cents = rCents;
		  
		  System.out.println("Dinominations for "+cents);
		  System.out.println("dollars for "+dollars);
		  System.out.println("quarters for "+quarters);
		  System.out.println("dimes for "+dimes);
		  System.out.println("nickles for "+nickles);
		  System.out.println("cents for "+rCents);
		  
		  return new Change(dollars,quarters,dimes,nickles,cents);
    }


    // Please do not change this class
    static class Change {
        private final int _dollars;
        private final int _quarters; //25 cents
        private final int _dimes; // 10 cents
        private final int _nickels; // 5 cents
        private final int _cents; // 1 cent


        public Change(int dollars, int quarters, int dimes, int nickels, int cents) {
            _dollars = dollars;
            _quarters = quarters;
            _dimes = dimes;
            _nickels = nickels;
            _cents = cents;
        }


        public int getDollars() {
            return _dollars;
        }


        public int getQuarters() {
            return _quarters;
        }


        public int getDimes() {
            return _dimes;
        }


        public int getNickels() {
            return _nickels;
        }


        public int getCents() {
            return _cents;
        }
    }
}
