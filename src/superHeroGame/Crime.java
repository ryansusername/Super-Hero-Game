package superHeroGame;

public class Crime{ 


String details;
double randCash;


	public Crime(String details, double randCash)
	{
		this.details = details;
		this.randCash = randCash;
		
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public double getRandCash() {
		return randCash;
	}


	public void setRandCash(double randCash) {
		this.randCash = randCash;
	}
	
	
	
}