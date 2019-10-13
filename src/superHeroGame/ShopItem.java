package superHeroGame;

import javax.swing.ImageIcon;

public class ShopItem {

	String name;
	String description;
	ImageIcon image,purImage;
	double cost;
	Ability abab;
	
	public ShopItem(String name, String description, ImageIcon image, double cost,Ability abab){
		this.name = name;
		this.description = description;
		this.image = image;
		this.cost = cost;
		this.abab = abab;
		
		
	}

	public Ability getAbility() {
		return abab;
	}

	public void setAbility(Ability abab) {
		this.abab = abab;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	
	
}
