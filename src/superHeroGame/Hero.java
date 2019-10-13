package superHeroGame;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Hero extends Character{

	public Hero(String characterName, ImageIcon characterImage,double cash,String characterStatus,ImageIcon superIcon,ImageIcon changedIcon) 
	{
		super(characterName, characterImage, cash, characterStatus, superIcon, changedIcon);

	}

	public Color getTextColor() 
	{
		Color textColor = new Color(0,138,235);
		
		return textColor;
	}
		
	public boolean charType()
	{
		boolean charType = true;
			
		return charType;
	}
		
		
	
	
}
