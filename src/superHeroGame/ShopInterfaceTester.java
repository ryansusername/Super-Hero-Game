package superHeroGame;

import java.awt.EventQueue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class ShopInterfaceTester {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException, IOException {
	
	
		
		
		
	  //Implementation for running the game		
		Runnable runner = new Runnable()
		{
			public void run()
			{
				
				ShopPanel pn = new ShopPanel();
				pn.show();
				
			}
		};
//running the thread		
		EventQueue.invokeAndWait(runner);
	}
}