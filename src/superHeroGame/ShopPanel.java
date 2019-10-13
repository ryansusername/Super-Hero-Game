package superHeroGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class ShopPanel{
	
	JFrame shopFrame;
	JPanel shopPanel;
	JPanel shopSubA,shopSubB,shopSubC;
	
	JPanel itemPanel,clothesPanel,foodPanel;
	Font myFont;
	
	
	JLabel item,food,clothes;
	JLabel item1, item2, food1,food2, clothes1,clothes2;
	JLabel itemCost,characterCash,itemName,itemDescription;
	JLabel selectedItemLabel;
	GridBagLayout shopLayout;
	GridBagConstraints shopPanelConstraints;
	GridBagConstraints shopComponentConstraints;
	
	
	Character character;
	ShopItem airStrike,cube;
	ShopFood coffee, chicken;
	ShopClothes productPlc,ralphLaur;
	
	ShopItem selectedItem;
	ArrayList<ShopItem> purchacedItems;
	
	int returnVal;
	double cashValue;
	private JButton btnExit;
	
	public ShopPanel(){
		
		myFont = new Font("Helvetica", Font.PLAIN, 18);
		
		shopPanelConstraints = new GridBagConstraints();
		shopComponentConstraints = new GridBagConstraints();
		purchacedItems = new ArrayList<ShopItem>();
		
		
		shopFrame = new JFrame("Shop");
		shopFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		shopFrame.setSize(1200,700);
		shopFrame.setLocationRelativeTo(null);
		
		shopLayout = new GridBagLayout();
		
		shopPanel = new JPanel();
		shopPanel.setSize(shopFrame.getSize());

		
		
// shopSubA_____________________________________________Check later		
			shopSubA = new JPanel();
				
			
				JLabel shopTitle = new JLabel();
				shopTitle.setText("Super-Shop");
				
				characterCash = new JLabel();
				
				characterCash.setText("Cash: $" + String.valueOf(cashValue)); //character.getCash()
				characterCash.setFont(myFont);
				characterCash.setForeground(new Color(244,244,244));
				
			addComponent(0,0,1,1,1,1,shopTitle,shopSubA);
			
		
		
			shopSubB = new JPanel();
				
				itemName = new JLabel();
				itemDescription = new JLabel();
				itemCost = new JLabel();
				
				
				
				selectedItem = new ShopItem(null, null, null, 0,null);
				
				String descriptionC = "<html>Description: With the help of our sponsors<br/> you can earn more per crime!</html>";
				String descriptionD = "<html>Description: Increases amount of time before <br/>a crime disappears.</html>";
				String descriptionE = "<html>Description: Your best friend, allows you to<br/> sleep easy at night (faster sleep)</html>";
				String descriptionF = "<html>Description: Swag means status. This shirt <br/>gives you a slight increase in status per crime.</html>";
				String descriptionG = "<html>Description: There is much power in the <br/>poultry. Sets your status to near SUPER STATUS</html>";
				String descriptionH = "<html>Description: Energy decreases slower</html>";
				
				
				
				
				
				airStrike = new ShopItem("Air Support",descriptionD,new ImageIcon("Images/airStrike_img.png"),2000,Ability.SLOW_CRIME);
				cube = new ShopItem("Companion Cube",descriptionE,new ImageIcon("Images/cube_img.png"),1000,Ability.FAST_SLEEP);
			
				productPlc = new ShopClothes("Product Placement",descriptionC,new ImageIcon("Images/productPlacement_img.png"),2000,Ability.MORE_MONEY);
				ralphLaur = new ShopClothes("Ralph Lauren Polo",descriptionF,new ImageIcon("Images/ralphLaur_img.png"),3000,Ability.MORE_STATUS);
				
				chicken = new ShopFood("Chicken",descriptionG,new ImageIcon("Images/chicken_img.png"),5000,Ability.INSTANTSUPERSTATUS);
				coffee = new ShopFood("Caffine",descriptionH,new ImageIcon("Images/coffee_img.png"),420,Ability.SLOW_ENERGY);
	
				item1 = new JLabel(airStrike.getImage());
				item2 = new JLabel(cube.getImage());
				
				food1 = new JLabel(chicken.getImage());
				food2 = new JLabel(coffee.getImage());
	
				clothes1 = new JLabel(productPlc.getImage());
				clothes2 = new JLabel(ralphLaur.getImage());
				
				item1.setVisible(false);
				item2.setVisible(false);
				food1.setVisible(false);
				food2.setVisible(false);
				clothes1.setVisible(false);
				clothes2.setVisible(false);
				
				
				
				
				item = new JLabel(new ImageIcon("Images/items_img.png"));
				item.setVerticalAlignment(JLabel.TOP);
				item.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					}

					@Override
					public void mouseExited(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						if((!((JComponent) obj).getBorder().equals(BorderFactory.createEtchedBorder())))
						{
							((JComponent) obj).setBorder(null);
						}
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						
						item1.setVisible(true);
						item2.setVisible(true);
						
						food1.setVisible(false);
						food2.setVisible(false);
						clothes1.setVisible(false);
						clothes2.setVisible(false);
					}
					
				});
				
				
				food = new JLabel(new ImageIcon("Images/food_img.png"));
				food.setVerticalAlignment(JLabel.TOP);
				food.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					}

					@Override
					public void mouseExited(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						if((!((JComponent) obj).getBorder().equals(BorderFactory.createEtchedBorder())))
						{
							((JComponent) obj).setBorder(null);
						}
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						
						food1.setVisible(true);
						food2.setVisible(true);
						
						item1.setVisible(false);
						item2.setVisible(false);
						clothes1.setVisible(false);
						clothes2.setVisible(false);
					}
					
				});
				
				clothes = new JLabel(new ImageIcon("Images/clothing_img.png"));
				clothes.setVerticalAlignment(JLabel.TOP);
				clothes.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					}

					@Override
					public void mouseExited(MouseEvent arg0) 
					{
						Object obj = arg0.getSource();
						if((!((JComponent) obj).getBorder().equals(BorderFactory.createEtchedBorder())))
						{
							((JComponent) obj).setBorder(null);
						}
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						Object obj = arg0.getSource();
						((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						
						clothes1.setVisible(true);
						clothes2.setVisible(true);
						

						item1.setVisible(false);
						item2.setVisible(false);
						food1.setVisible(false);
						food2.setVisible(false);
					}
					
				});
				
				
				
				
				
			
				
				
				addListener(item1, airStrike);
				addListener(item2, cube);
				addListener(food1, chicken);
				addListener(food2, coffee);
				addListener(clothes1, productPlc);
				addListener(clothes2, ralphLaur);
				
				
				
				
				
				
//Adding Images to frame
				addComponent(0,0,1,1,.2,1,item,shopSubB);
				addComponent(1,0,1,1,.2,1,clothes,shopSubB);
				addComponent(2,0,1,1,.2,1,food,shopSubB);
				
				
				
				addComponent(0,1,1,1,.2,1,item1,shopSubB);
				addComponent(0,2,1,1,.2,1,item2,shopSubB);

				
				
				clothesPanel = new JPanel();
				
				addComponent(1,1,1,1,.2,1,clothes1,shopSubB);
				addComponent(1,2,1,1,.2,1,clothes2,shopSubB);
				
				foodPanel = new JPanel();
				
				addComponent(2,1,1,1,.2,1,food1,shopSubB);
				addComponent(2,2,1,1,.2,1,food2,shopSubB);
				
				
				
				
				

//				
//			addComponent(0,0,1,1,1,1,item1,shopSubB);
//			addComponent(1,0,1,1,1,1,item2,shopSubB);
//			addComponent(0,1,1,1,1,1,item3,shopSubB);
//			addComponent(1,1,1,1,1,1,item4,shopSubB);
//			
				
//			
			
			
			shopSubC = new JPanel();
		
				JButton btnPurchase = new JButton ("Purchace Item");
				btnPurchase.setForeground(new Color(244,244,244));
				btnPurchase.setBackground(new Color(127,127,127));
				
				btnPurchase.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
					
						try {
							buttonSound(new File("sounds/bow.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
					buyItem(selectedItem,selectedItemLabel);
					
					
					}
				
				});
			
				btnExit = new JButton("Exit");
				btnExit.setForeground(new Color(244,244,244));
				btnExit.setBackground(new Color(127,127,127));
				
				
				btnExit.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							buttonSound(new File("sounds/bow.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						hide();
						
					}
					
				});
				JLabel s1 = new JLabel();
			
			
				addComponent(0,0,2,1,1,1,characterCash,shopSubC);
				addComponent(0,1,2,1,1,1,itemName,shopSubC);
				addComponent(0,2,2,1,1,1,itemDescription,shopSubC);
				addComponent(0,3,2,1,1,1,itemCost,shopSubC);
				
				addComponent(0,4,1,1,0.01,1,btnPurchase,shopSubC);
				//addComponent(1,4,1,1,1,1,s1,shopSubC);
				addComponent(0,5,1,1,0.01,1,btnExit,shopSubC);
				
				
		
		addPanel(0,0,1,1,1,1,shopSubB,shopPanel);
		
		addPanel(1,0,1,1,1,1,shopSubC,shopPanel);
		
		
		shopFrame.setVisible(false);
		shopFrame.add(shopPanel);

		
	}
	
	
	
//Methods
	
	private void addListener(JLabel lbl,ShopItem item)
	{
		lbl.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
									
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				Object obj = arg0.getSource();
				((JComponent) obj).setBorder((BorderFactory.createLineBorder(Color.BLACK)));
			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				Object obj = arg0.getSource();
				if((!((JComponent) obj).getBorder().equals(BorderFactory.createEtchedBorder())))
				{
					((JComponent) obj).setBorder(null);
				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				Object obj = arg0.getSource();
				((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
				
				try {
					buttonSound(new File("sounds/pop.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			itemName.setText(item.getName());
			itemCost.setText("Cost: $" + String.valueOf(item.getCost()));
			itemDescription.setText(item.getDescription());
			selectedItem = item;
			selectedItemLabel = lbl;
			
			itemName.setForeground(new Color(244,244,244));
			itemName.setFont(myFont);
			
			
			itemCost.setForeground(new Color(244,244,244));
			itemCost.setFont(myFont);
			
			itemDescription.setForeground(new Color(244,244,244));
			itemDescription.setFont(myFont);
			
			
			
			}
								
		});
	}
	
	
	
	
	private void addComponent(int x, int y, int gridWidth, int gridHeight,double weightX, double weightY,  JComponent component, JPanel pan)
	{	
		shopComponentConstraints.anchor = GridBagConstraints.CENTER;
		shopComponentConstraints.fill = GridBagConstraints.BOTH;
		shopComponentConstraints.insets = new Insets(5,5,5,5);
		shopComponentConstraints.gridx = x;
		shopComponentConstraints.gridy = y;
		shopComponentConstraints.gridwidth = gridWidth;
		shopComponentConstraints.gridheight = gridHeight;
		shopComponentConstraints.weightx = weightX;
		shopComponentConstraints.weighty = weightY;
	    
	    pan.setLayout(shopLayout);
	    pan.add(component, shopComponentConstraints);
	     
	}
	
	private void addPanel(int x, int y, int gridWidth, int gridHeight,double weightX, double weightY, JPanel p,JPanel parent)
	{  
		
		
		shopPanelConstraints.anchor = GridBagConstraints.CENTER;
		shopPanelConstraints.fill = GridBagConstraints.BOTH;
		
		shopPanelConstraints.gridx = x;
		shopPanelConstraints.gridy = y;
		shopPanelConstraints.gridwidth = gridWidth;
		shopPanelConstraints.gridheight = gridHeight;
		shopPanelConstraints.weightx = weightX;
		shopPanelConstraints.weighty = weightY;
	    
	  //  c.insets = new Insets(5,5,5,5);
		p.setBackground(new Color(51,51,51));
	    p.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
	    p.setLayout(shopLayout);
	    parent.setLayout(shopLayout);
	    parent.add(p, shopPanelConstraints);
	   
	}
	
	public void show(){
		shopFrame.setVisible(true);
	}
	
	public void hide(){
		shopFrame.setVisible(false);
	}
	
	public void buyItem(ShopItem item,JLabel itemLbl){
		
		item = selectedItem;
		String purchacedNote = "Congratulations! you have unlocked the " + item.getName() ;
		String noMoney = "Sorry, you can't afford me, come back when you have some real cash";
		
		
		
		JFrame option = new JFrame();
		ImageIcon buyIcon = new ImageIcon();
		String yes = "Yes";
		String no = "No";
		Object[] buyOptionsArray = {yes,no};
		
		
		if(cashValue >= item.getCost())
		{
			
			returnVal = JOptionPane.showOptionDialog(option, "Are you sure? It'll cost ya' about " + 
					itemCost.getText(), "Purchace Item",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, buyIcon, buyOptionsArray, buyOptionsArray[0]);
				
			if(returnVal == JOptionPane.YES_OPTION)
			{
				
				try {
					buttonSound(new File("sounds/levelup.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(shopPanel, purchacedNote);
				cashValue = cashValue - item.getCost();
				characterCash.setText("Cash: £" + String.valueOf(cashValue));
				
//Adds purchased item to arraylist
				purchacedItems.add(item);
				
//Removes mouse listeners from selected item when purchased (Can only be purchased once)			
				MouseListener[] list = itemLbl.getMouseListeners();
				for(int i = 0; i<list.length;i++)
				{
					itemLbl.removeMouseListener(list[i]);
				}
//Changing to purchased image				
		
				itemLbl.setIcon(new ImageIcon("Images/purchased_img.png"));
			}
			
			
		}
		else if(cashValue < item.getCost())
		{
			try {
				buttonSound(new File("sounds/bass.ogg.wav"));
			} catch (UnsupportedAudioFileException | IOException
					| LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(shopPanel,noMoney);
			
			
		}
			
		
		
		
	}
	
	public ArrayList<ShopItem> getShopItems(){
		return purchacedItems;
	}
	
	public JFrame getFrame(){
		return shopFrame;
	}
	public JButton getExit()
	{
		return btnExit;
	}
	public void setCash(double monmon)
	{
		cashValue = monmon;
		characterCash.setText("Cash: $" + monmon);
	}
	public double getCash()
	{
		return cashValue;
	}
	
	public void buttonSound(File sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
	
		 try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound.getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	    
	}
	
}
