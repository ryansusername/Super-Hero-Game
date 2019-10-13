package superHeroGame;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;


public class GameInterfaceTester {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException{
	
	
		
		
		
	  //Implementation for running the game		
		Runnable runner = new Runnable()
		{
			public void run()
			{
				
				new GameInterface();
				
			}
		};
//running the thread		
		EventQueue.invokeAndWait(runner);
	}
}
