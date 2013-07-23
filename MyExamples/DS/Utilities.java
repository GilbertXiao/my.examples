public class Utilities
{
	private boolean isPrime(int n)      // is n prime? 
	{ 
		for(int j=2; (j*j <= n); j++)    // for all j 
			if( n % j == 0)               // divides evenly by j? 
				return false;              // yes, so not prime 
		return true;                     // no, so prime 
	}
}