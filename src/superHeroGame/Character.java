package superHeroGame;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Character {

//Declared Variables
	ImageIcon characterImage,superIcon,changedIcon;
	JLabel lblCharacterImage;
	double cash;
	String characterName;
	boolean isHero;
	String characterStatus;
	
//Constructor	
	public Character(String characterName,ImageIcon characterImage,double cash, String characterStatus,ImageIcon superIcon,ImageIcon changedIcon){
		this.characterName = characterName;
		this.characterImage = characterImage;
		this.cash = cash;
		this.characterStatus = characterStatus;
		this.superIcon = superIcon;
		this.changedIcon = changedIcon;
		
	}
	



public ImageIcon getSuperIcon() {
		return superIcon;
	}




	public void setSuperIcon(ImageIcon superIcon) {
		this.superIcon = superIcon;
	}




	public ImageIcon getChangedIcon() {
		return changedIcon;
	}




	public void setChangedIcon(ImageIcon changedIcon) {
		this.changedIcon = changedIcon;
	}




public String getCharacterStatus() {
		return characterStatus;
	}




	public void setCharacterStatus(String characterStatus) {
		this.characterStatus = characterStatus;
	}




public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	//Methods (Getters & Setters)
	public ImageIcon getCharacterImage() {
		return characterImage;
	}

	public void setCharacterImage(ImageIcon characterImage) {
		this.characterImage = characterImage;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}


	
	
}
