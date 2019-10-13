package superHeroGame;

//Imports
import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;

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
import javax.swing.JProgressBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class GameInterface {

//Declare interface variable
	private JFrame frame;
	
//Controlling card panel, uses cardLayout 
	private JPanel containerPanel;
	
//Panels set in container, use gridBagLayout
	
	private JPanel mainPanel,heroVillanPanel,heroVillanPanel2, difficultyPanel,startPanel;
	
///Sub Panels (Start)
	private JPanel startSubA;

//Sub Panels (Main)
	private JPanel mainSubA,mainSubB,mainSubC,mainSubD;
	
//SubPanels (heroVillanPanel)
	private JPanel heroSubA,heroSubB,heroSubC;
	
//SubPanels (heroVillanPanel2)
	private JPanel hero2SubA,hero2SubB,hero2SubC;
	
//SubPanels (difficultyPanel)
	private JPanel difficultyA,difficultyB;
	
//Extra Panels
	private JPanel inventory;
	
//Image Icons 	
	private JLabel mainSuperImage;
	private JLabel mainChangedImage;

	private ImageIcon easy,medium,hard;
	private ImageIcon genericHero,genericVillan;
	private ImageIcon map1,map2,map3,map4,map5,map6,map7,map8;
	
//Character Icons 	
	private ImageIcon batIcon,superIcon,ironIcon;
	private ImageIcon venIcon,ivyIcon,jokerIcon;


//Evil/Good Character Images
	
	private JLabel charName,charStatus;
	private JLabel lblgenericHero,lblgenericVillan;
	private JLabel imgMap1,imgMap2,imgMap3,imgMap4;
	private JLabel char1Image, char2Image,char3Image;
	
	private JLabel mainCharacterImage;
	
	
	private JLabel selectedCharacterImage,characterDiff;
	
	private JLabel energyNumber,statusNumber;
	
//These will be populated according to which class the user chooses 
	
	private Timer randomCrime,updateEnergyBar,runCrime;
	private Timer updateSleep;
	private JButton sleep,wake,btnChooseVillan;
	
//Progress Bars
	private JProgressBar energy,status,crimeA,crimeB,crimeC,crimeD;
	private JLabel energyLbl,statusLbl,difficultyLbl;
	
//Layouts	
	private GridBagLayout gridBagLayout;
	private GridBagConstraints componentContraints,panelConstraints;
	private CardLayout cardLayout;
	
//This is the value returned whether each button is pressed in the crime event 
	private int returnValue;
	
	private int energyTime = 500;
	private int sleepTime = 500;
	private int crimeTime = 100;
	private int statusIncrease = 10;
	private final int CONSTANT_RATE = 100;
	
//Initial Values for variables in game
	private int statusValue;
	private double money;
	private int energyDecrease;
	
	
//Values changed with shop items	
	private int extraMoney = 0;
	
//File name for saving	
	private String fileName = "src/GameSave.txt";

	private JLabel moneyLabel;
	private String endGamestats,endGame; 
	private String[] statuses;
	
	private boolean isHero;
	
	private Difficulty difficulty;
	private Ability ability;
	
	private Crime newCrime;
	private Character yourCharacter;
	private Character character1,character2,character3;
	private ArrayList<Crime> crimes,crimesVill; 
	private MouseListener crimeListener;
	private Color heroColor;
	private Color villColor;
	
	ArrayList<ShopItem> items;
	ArrayList<JLabel> inventories;
	
	
	
	
//SOME CRIME STUFF
	private ImageIcon crimeIcon;
	private String getUserName;
	final private String HEROCRIME = "Fight Crime";
	final private String VILLANCRIME = "Commit Crime";
	final private String IGNORECRIME = "Ignore";
	
	private ShopPanel shopPanel;
	
	Hero myHero;
	Villan myVillan;

	private ImageIcon superIconS;
	private ImageIcon ironIconS;
	private ImageIcon batIconS;

	private ImageIcon jokerIconS;
	private ImageIcon venIconS;
	private ImageIcon ivyIconS;

	private ImageIcon superIconE;
	private ImageIcon ironIconE;
	private ImageIcon batIconE;

	private ImageIcon jokerIconG;
	private ImageIcon venIconG;
	private ImageIcon ivyIconG;

	private JLabel char1SuperImage;
	private JLabel char2SuperImage;
	private JLabel char3SuperImage;

	private JLabel char1EvilImage;
	private JLabel char2EvilImage;
	private JLabel char3EvilImage;

	private JPanel difficultyC;
		

	 
	
//Interface constructor
	public GameInterface(){
		
		Font myFont = new Font("Helvetica", Font.PLAIN, 20);
		heroColor = new Color(0,138,235);
		villColor = new Color(209,88,91);
		
		statuses = new String[]{"Hero","Villain","Sleeping","Super Hero","Super Villain","Human"};
		
		
		
		
		shopPanel = new ShopPanel();
		items = shopPanel.getShopItems();

		componentContraints = new GridBagConstraints();
		panelConstraints = new GridBagConstraints();
		
//Frame parameters
		frame = new JFrame("Superhero Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1100,680);
		frame.setLocationRelativeTo(null);
	
//Layout for subPanels
		gridBagLayout = new GridBagLayout();	
		

//Container Panel setup
		cardLayout = new CardLayout();
		containerPanel = new JPanel();
		containerPanel.setLayout(cardLayout);
		containerPanel.setSize(frame.getSize());
		cardLayout.show(containerPanel,"Container");

		
//Setting Character icons (Linking to files)
		superIcon = new ImageIcon("Images/SuperManImg.png");
		ironIcon = new ImageIcon("Images/IronManImg.png");
		batIcon = new ImageIcon("Images/BatmanImg.png");
				
		jokerIcon = new ImageIcon("Images/JokerImg.png");
		venIcon = new ImageIcon("Images/VenomImg.png");
		ivyIcon = new ImageIcon("Images/ivyImg.png");

		
//Special Character images	
	//Super	
	
		superIconS = new ImageIcon("Images/SuperSuperManImg.png");
		ironIconS = new ImageIcon("Images/SuperIronManImg.png");
		batIconS = new ImageIcon("Images/SuperBatmanImg.png");
		
		jokerIconS = new ImageIcon("gifs/SuperJoker.gif");
		venIconS = new ImageIcon("gifs/SuperVenom.gif");
		ivyIconS = new ImageIcon("gifs/SuperIvy.gif");
		
	//Evil/Good Change
		
		superIconE = new ImageIcon("gifs/EvilSuperMan.gif");
		ironIconE = new ImageIcon("gifs/EvilIronMan.gif");
		batIconE = new ImageIcon("gifs/EvilBatMan.gif");
		
		jokerIconG = new ImageIcon("Images/GoodJoker.png");
		venIconG = new ImageIcon("Images/GoodVenom.png");
		ivyIconG = new ImageIcon("Images/GoodIvy.png");
		

//Map Images
		map1 = new ImageIcon("Images/map1_hero_img.png");
		map2= new ImageIcon("Images/map2_hero_img.png");
		map3 = new ImageIcon("Images/map3_hero_img.png");
		map4 = new ImageIcon("Images/map4_hero_img.png");
		
		map5 = new ImageIcon("Images/map1_villian_img.png");
		map6= new ImageIcon("Images/map2_villian_img.png");
		map7 = new ImageIcon("Images/map3_villian_img.png");
		map8 = new ImageIcon("Images/map4_villian_img.png");
		
//Declaring labels that will hold character image icons			
		char1Image = new JLabel();
		char2Image = new JLabel();
		char3Image = new JLabel();
		
		char1SuperImage = new JLabel();
		char2SuperImage = new JLabel();
		char3SuperImage = new JLabel();
		char1EvilImage = new JLabel();
		char2EvilImage = new JLabel();
		char3EvilImage = new JLabel();
		
		mainSuperImage = new JLabel();
		mainChangedImage = new JLabel();
		selectedCharacterImage = new JLabel();	
		characterDiff = new JLabel();

//Crime Array populating 
		crimes = new ArrayList<Crime>();
		crimes.add(0,new Crime("Someones robbing a bank", randomInteger(50,100)));
		crimes.add(1,new Crime("Someone didn't declare their taxable income", randomInteger(50,100)));
		crimes.add(2,new Crime("Atleast 60g of Pick&Mix has been stolen", randomInteger(50,60)));
		crimes.add(3,new Crime("Someones wearing double denim of the same colour", randomInteger(50,100)));
		crimes.add(4,new Crime("A liquor store is being held at gun point", randomInteger(50,100)));
		crimes.add(5,new Crime("A nickleback concert has just been announced", randomInteger(100,200)));
		crimes.add(6,new Crime("Those shoes with that dress? Really?", randomInteger(100,200)));
		crimes.add(7,new Crime("Hey that guy just stole my wallet!", randomInteger(50,100)));
		crimes.add(8,new Crime("We've finally had it with north korea, do something", randomInteger(200,250)));
		crimes.add(9,new Crime("Someones set fire to a bin", randomInteger(10,40)));
		
		crimesVill = new ArrayList<Crime>();
		crimesVill.add(0,new Crime("You should rob a bank", randomInteger(50,100)));
		crimesVill.add(1,new Crime("There's a small shop with dangerously low security here", randomInteger(50,100)));
		crimesVill.add(2,new Crime("Steal some pick & mix", randomInteger(50,60)));
		crimesVill.add(3,new Crime("Commit fashion suicide and wear double denim", randomInteger(50,100)));
		crimesVill.add(4,new Crime("Hold up a liquor store at gun point", randomInteger(50,100)));
		crimesVill.add(5,new Crime("Make it so that Nickleback headline Glastonbury", randomInteger(100,200)));
		crimesVill.add(6,new Crime("Those shoes with that dress? Really?", randomInteger(100,200)));
		crimesVill.add(7,new Crime("Hey,Steal that guys wallet!", randomInteger(50,100)));
		crimesVill.add(8,new Crime("Help North Korea", randomInteger(200,250)));
		crimesVill.add(9,new Crime("Set fire to a bin", randomInteger(10,40)));
		
		
		
		
		
//StartPanel Setup (Contains 2 sub panels)
		startPanel = new JPanel();
		startPanel.setSize(containerPanel.getSize());
		
		JLabel lblTitleImage = new JLabel();
		lblTitleImage.setHorizontalAlignment(JLabel.CENTER);
		
		
		lblTitleImage.setIcon(new ImageIcon("Images/Title.png"));
		JLabel lblName = new JLabel();
		JLabel lblName2 = new JLabel();
		
			startSubA = new JPanel();
			
				String text = "<html><b><center>Welcome to the Game.</center></b><br/>Please select if you want to start a new game or load an exisitng one.<html>";
				
				JLabel welcomeText = new JLabel(text);
				welcomeText.setHorizontalAlignment(JLabel.CENTER);
				welcomeText.setFont(myFont);
				welcomeText.setForeground(new Color(244,244,244));
				
				JButton newGame = new JButton("New Game");
				newGame.setForeground(new Color(244,244,244));
				newGame.setBackground(new Color(48,169,153));
				
				
				newGame.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e){
						try {
							buttonSound(new File("sounds/pop.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							
							e1.printStackTrace();
						}
						
						getUserName = JOptionPane.showInputDialog("Please enter a new name for your character:");
						
						cardLayout.next(containerPanel);
	
						lblName.setText(getUserName);
						lblName2.setText(getUserName);

					}
					
				});
		
				JButton loadGame = new JButton("Load Game");
				loadGame.setForeground(new Color(244,244,244));
				loadGame.setBackground(new Color(17,62,64));
				loadGame.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							buttonSound(new File("sounds/bow.ogg.wav"));
							buttonSound(new File("sounds/The_Eagles_-_Hotel_California_8-bit_NES_Version_.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							
							e1.printStackTrace();
						}
						
						readFromFile();
						JOptionPane.showMessageDialog(mainPanel,"Are you ready for this?");
						
						runEnergyBar();
						runNewCrime(imgMap1, crimeA);

						cardLayout.last(containerPanel);
						cardLayout.previous(containerPanel);
						
					}
					
				});
				
				
			addComponent(0,0,3,1,1,1,lblTitleImage,startSubA);	
			addComponent(0,1,3,1,1,1,welcomeText,startSubA);
			addComponent(0,2,1,1,1,1,newGame,startSubA);
			addComponent(0,3,1,1,1,1,loadGame,startSubA);

		addPanel(0,0,1,1,1,1,startSubA,startPanel);
		
		
		
		

		
	
//HeroVillan Selection setup (Contains 3 Panels)
		heroVillanPanel = new JPanel();
		heroVillanPanel.setSize(containerPanel.getSize());
		
			heroSubA = new JPanel();
				JLabel lblHVTitleA = new JLabel("Choose Your Path");
				lblHVTitleA.setFont(myFont);
				
				lblName.setFont(myFont);
				lblName.setHorizontalAlignment(JLabel.RIGHT);
			
			addComponent(0,0,1,1,1,1,lblHVTitleA,heroSubA);
			addComponent(1,0,1,1,1,1,lblName,heroSubA);

			
//Colour Varying JLabels
			JLabel titleB = new JLabel("<html><b>Stats:</b></html>");
			titleB.setFont(myFont);
			titleB.setForeground(new Color(244,244,244));
		
			energyLbl = new JLabel("Energy");
			energyLbl.setFont(myFont);
			energyLbl.setForeground(new Color(244,244,244));
			
			statusLbl = new JLabel("Current Status");
			statusLbl.setFont(myFont);
			statusLbl.setForeground(new Color(244,244,244));
				
			charName = new JLabel();
			charName.setFont(myFont);
			
			charStatus = new JLabel();
			charStatus.setFont(myFont);
			
			moneyLabel = new JLabel("Your Cash: £" +String.valueOf(money));
			moneyLabel.setFont(myFont);
			moneyLabel.setForeground(new Color(244,244,244));
			
			difficultyLbl = new JLabel();
			difficultyLbl.setFont(myFont);
			difficultyLbl.setForeground(new Color(244,244,244));
		
			heroSubB = new JPanel();

				btnChooseVillan = new JButton("Next");
				btnChooseVillan.setEnabled(false);
				
//Selecting villain or Hero				
				genericHero = new ImageIcon("Images/heroSelect_img.png");
				genericVillan = new ImageIcon("Images/villanSelect_img.png");
				
				lblgenericHero = new JLabel(genericHero,JLabel.CENTER);
				lblgenericHero.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						lblgenericHero.setIcon(new ImageIcon("Images/heroSelect02_img.png"));
						
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						lblgenericHero.setIcon(genericHero);
					}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {
						
						try {
							buttonSound(new File("sounds/pop.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
//Set images to heros						
						char1Image.setIcon(superIcon);
						char2Image.setIcon(batIcon);
						char3Image.setIcon(ironIcon);
						
						char1SuperImage.setIcon(superIconS);
						char2SuperImage.setIcon(batIconS);
						char3SuperImage.setIcon(ironIconS);
						
						char1EvilImage.setIcon(superIconE);
						char2EvilImage.setIcon(batIconE);
						char3EvilImage.setIcon(ironIconE);
						
						btnChooseVillan.setForeground(new Color(244,244,244));
						btnChooseVillan.setBackground(new Color(27,103,129));
						btnChooseVillan.setEnabled(true);
						
						genericVillan = new ImageIcon("Images/villanSelect_img.png");
						genericHero = new ImageIcon("Images/heroSelect02_img.png");
						
						imgMap1.setIcon(map1);
						imgMap2.setIcon(map2);
						imgMap3.setIcon(map3);
						imgMap4.setIcon(map4);
						
						isHero = true;
						
						setTextColor(energyLbl);
						setTextColor(statusLbl);
						setTextColor(titleB);
						
					}
					
				});
				 
			
				
				lblgenericVillan = new JLabel(genericVillan,JLabel.CENTER);
				lblgenericVillan.addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent arg0) {}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						lblgenericVillan.setIcon(new ImageIcon("Images/villanSelect2_img.png"));
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						lblgenericVillan.setIcon(genericVillan);						
					}

					@Override
					public void mousePressed(MouseEvent arg0){} 
					

					@Override
					public void mouseReleased(MouseEvent arg0) {
						
						try {
							buttonSound(new File("sounds/pop.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
//Set images to villains						
						char1Image.setIcon(venIcon);
						char2Image.setIcon(jokerIcon);
						char3Image.setIcon(ivyIcon);
						
						char1SuperImage.setIcon(venIconS); 
						char2SuperImage.setIcon(jokerIconS);
						char3SuperImage.setIcon(ivyIconS);
						
						char1EvilImage.setIcon(venIconG);
						char2EvilImage.setIcon(jokerIconG);
						char3EvilImage.setIcon(ivyIconG);
						
						btnChooseVillan.setEnabled(true);
						btnChooseVillan.setForeground(new Color(244,244,244));
						btnChooseVillan.setBackground(new Color(149,17,20));
						
						genericVillan = new ImageIcon("Images/villanSelect2_img.png");
						genericHero = new ImageIcon("Images/heroSelect_img.png");
						
						imgMap1.setIcon(map5);
						imgMap2.setIcon(map6);
						imgMap3.setIcon(map7);
						imgMap4.setIcon(map8);
						
						
						isHero = false;
						setTextColor(energyLbl);
						setTextColor(statusLbl);
						setTextColor(titleB);
					}
					
				});
				
				
			addComponent(0,0,1,1,1,1,lblgenericHero,heroSubB);	
			addComponent(1,0,1,1,1,1,lblgenericVillan,heroSubB);	
		
			
			heroSubC = new JPanel();
	
				btnChooseVillan.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							buttonSound(new File("sounds/bow.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							
							e1.printStackTrace();
						}
				
						cardLayout.next(containerPanel);
					}
					
				});
			
			addComponent(0,0,1,1,1,1,btnChooseVillan,heroSubC);	

			
//Add panels to heroVillan Panel			
		addPanel(0,0,1,1,0.1,0.2,heroSubA,heroVillanPanel);	
		addPanel(0,1,1,1,0.8,1,heroSubB,heroVillanPanel);	
		addPanel(0,2,1,1,0.8,1,heroSubC,heroVillanPanel);	
		
	
			
//HeroVillan Selection setup (Contains 3 Panels)
		heroVillanPanel2 = new JPanel();
		heroVillanPanel2.setSize(containerPanel.getSize());
		
		
			hero2SubA = new JPanel();
				
				JLabel lblHVTitle2 = new JLabel("Choose Your Character");
				lblHVTitle2.setFont(myFont);
				lblHVTitle2.setForeground(new Color(244,244,244));
			
				lblName2.setFont(myFont);
				lblName2.setForeground(new Color(244,244,244));
				lblName2.setHorizontalAlignment(JLabel.RIGHT);
				
			addComponent(0,0,1,1,1,1,lblHVTitle2,hero2SubA);
			addComponent(1,0,1,1,1,1,lblName2,hero2SubA);
			
//Selecting Character Image				
			hero2SubB = new JPanel();
		
				char1Image.setHorizontalAlignment(JLabel.CENTER);
				char2Image.setHorizontalAlignment(JLabel.CENTER);
				char3Image.setHorizontalAlignment(JLabel.CENTER);
				
				char1Image.setVerticalAlignment(JLabel.CENTER);
				char2Image.setVerticalAlignment(JLabel.CENTER);
				char3Image.setVerticalAlignment(JLabel.CENTER);
				
				JButton btnContinue = new JButton("Continue");
				btnContinue.setForeground(new Color(244,244,244));
				btnContinue.setBackground(new Color(127,127,127));
				
				addMouseListenerToCharacterImage(char1Image,btnContinue,char1SuperImage,char1EvilImage);
				addMouseListenerToCharacterImage(char2Image,btnContinue,char2SuperImage,char2EvilImage);
				addMouseListenerToCharacterImage(char3Image,btnContinue,char3SuperImage,char3EvilImage);
				
			addComponent(0,0, 1,1, 1,1, char1Image,hero2SubB);
			addComponent(1,0, 1,1, 1,1, char2Image,hero2SubB);
			addComponent(2,0, 1,1, 1,1, char3Image,hero2SubB);
				
			
			hero2SubC = new JPanel();
			
				
				btnContinue.setEnabled(false);
				btnContinue.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							buttonSound(new File("sounds/bow.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						cardLayout.next(containerPanel);

					
					}
				
				});
			
			addComponent(0,0,1,1,1,1, btnContinue,hero2SubC);
			
			
			
		addPanel(0,0, 1,1, 1,0.1, hero2SubA,heroVillanPanel2);
		addPanel(0,1, 1,1, 1,0.8, hero2SubB,heroVillanPanel2);
		addPanel(0,2, 1,1, 1,0.2, hero2SubC,heroVillanPanel2);

//Difficulty selection panel setup(Contains 2 panels)
		
		difficultyPanel = new JPanel();
		difficultyPanel.setSize(containerPanel.getSize());

//Title area of panel
			difficultyA = new JPanel();
				JLabel lblDiffTitle = new JLabel("Choose Your Difficulty");
				lblDiffTitle.setFont(myFont);
				lblDiffTitle.setForeground(new Color(244,244,244));
				
			addComponent(0,0, 2,1, 1,1, lblDiffTitle,difficultyA);

//Image area of panel		
			difficultyB = new JPanel();
			difficultyC = new JPanel();	
				
				easy = new ImageIcon("Images/easy_img.png");
				medium = new ImageIcon("Images/medium_img.png");
				hard = new ImageIcon("Images/hard_img.png");
				
				String easyDes ="<html><p><b>Easy Difficulty:</b><br />For those who live life about 3 miles away from the edge.<br /> The sort of difficulty you can choose,<br />  leave the room, boil the kettle, take a bus to nearest town&amp; fall in love, settle down and start a family and you will still probably win.<br /> But who's going to love you with that <br />decision?<br /></p></html>";

				String medDes = "<html><p><b>Medium Difficulty:</b><br />This is where it's at. The sweet spot. Right. Freakin. There.<br />Where the &quot;Whatevers&quot; <br />hang out and discuss the weather Here you can never be judged because it's just so damn <br />normal. This is where you want to be.<br /></p></html>";

				String hardDes ="<html><p><b>Hard Difficulty:</b><br />You're probably a psychopath on some level.<br />Seriously though; <br />do you like to be in pain?<br /> because that's what's hard coded into this difficulty.<br/>Brutal, unforgiving, pain.<br/>  I've seen people die doing this.<br/></p></html>";                                                                                                                     
				
				JLabel difficultyDescriptionE = new JLabel(easyDes);
				JLabel difficultyDescriptionM = new JLabel(medDes);
				JLabel difficultyDescriptionH = new JLabel(hardDes);
				
				difficultyDescriptionE.setVisible(false);
				difficultyDescriptionM.setVisible(false);
				difficultyDescriptionH.setVisible(false);
			
				
				difficultyDescriptionE.setFont(myFont);
				difficultyDescriptionE.setForeground(new Color(244,244,244));
				
				difficultyDescriptionM.setFont(myFont);
				difficultyDescriptionM.setForeground(new Color(244,244,244));
				
				difficultyDescriptionH.setFont(myFont);
				difficultyDescriptionH.setForeground(new Color(244,244,244));
			
					JLabel lblEasyImg = new JLabel(easy,JLabel.CENTER);
				
					lblEasyImg.setAlignmentX(JLabel.CENTER);
					lblEasyImg.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent arg0) {
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);
						
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);
						}

						@Override
						public void mouseReleased(MouseEvent arg0) 
						{	
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
							difficultyDescriptionE.setVisible(true);
							difficultyDescriptionM.setVisible(false);
							difficultyDescriptionH.setVisible(false);
							
							difficultyLbl.setText("Difficulty: Easy");
							difficultyDescriptionE.setForeground(new Color(244,244,244));
							
							setupDifficulty(Difficulty.EASY,returnHeroVillan());
						}
					});
				
				
			
					JLabel lblMediumImg = new JLabel(medium,JLabel.CENTER);
				
					lblMediumImg.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent arg0) {
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);	
						}
						
						@Override
						public void mousePressed(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);
						}

						@Override
						public void mouseReleased(MouseEvent arg0) 
						{
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
							
							difficultyDescriptionE.setVisible(false);
							difficultyDescriptionM.setVisible(true);
							difficultyDescriptionH.setVisible(false);
							
							difficultyLbl.setText("Difficulty: Medium");

							setupDifficulty(Difficulty.MEDIUM,returnHeroVillan());
							difficultyDescriptionM.setForeground(new Color(244,244,244));
						}
				});
				
				
				
					JLabel lblHardImg = new JLabel(hard,JLabel.CENTER);
				
					lblHardImg.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent arg0) {
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
						
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);
						
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder(null);
						}

						@Override
						public void mouseReleased(MouseEvent arg0) 
						{
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		
							Object obj = arg0.getSource();
							((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
							
							difficultyDescriptionE.setVisible(false);
							difficultyDescriptionM.setVisible(false);
							difficultyDescriptionH.setVisible(true);
							
							difficultyLbl.setText("Difficulty: Hard");

							setupDifficulty(Difficulty.HARD,returnHeroVillan());
							difficultyDescriptionH.setForeground(new Color(244,244,244));
						}
						
					});
					
					JButton continu = new JButton("Start Game");
					continu.setForeground(new Color(244,244,244));
					continu.setBackground(new Color(127,127,127));
					
					
					continu.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							try {
								buttonSound(new File("sounds/bow.ogg.wav"));
								buttonSound(new File("sounds/The_Eagles_-_Hotel_California_8-bit_NES_Version_.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							cardLayout.next(containerPanel);
							JOptionPane.showMessageDialog(mainPanel,"Are you ready for this?");
							
							runEnergyBar();
							runNewCrime(imgMap1, crimeA);
							
							charName.setText("Your Name: " + returnHeroVillan().getCharacterName());
							charName.setVerticalAlignment(JLabel.TOP);
							
							if(isHero == true)
							{
								returnHeroVillan().setCharacterStatus(statuses[0]);
								charStatus.setText(returnHeroVillan().getCharacterStatus());

							}
							else
							{
								returnHeroVillan().setCharacterStatus(statuses[1]);
								charStatus.setText(returnHeroVillan().getCharacterStatus());
							}
							
							

							
							charStatus.setVerticalAlignment(JLabel.TOP);
							moneyLabel.setText("Your Cash: " + String.valueOf(returnHeroVillan().getCash()));
							moneyLabel.setVerticalAlignment(JLabel.TOP);
						}
						
					});
				
				
				addComponent(0,0,1,3,0.3,1,characterDiff,difficultyB);	
				addComponent(1,0,1,1,1,1,lblEasyImg,difficultyB);
				addComponent(1,1,1,1,1,1,lblMediumImg,difficultyB);
				addComponent(1,2,1,1,1,1,lblHardImg,difficultyB);
				
				addComponent(2,0,1,1,1,1,difficultyDescriptionE,difficultyB);
				addComponent(2,1,1,1,1,1,difficultyDescriptionM,difficultyB);
				addComponent(2,2,1,1,1,1,difficultyDescriptionH,difficultyB);
				
				addComponent(1,1,1,1,1,1,continu,difficultyC);
	
			addPanel(0,0,1,1,1,0.2,difficultyA,difficultyPanel);
			addPanel(0,1,1,1,1,0.8,difficultyB,difficultyPanel);
			addPanel(0,2,1,1,1,0.2,difficultyC,difficultyPanel);
		
	
//MainPanel setup (Contains 4 sub panels)
			mainPanel = new JPanel();
			mainPanel.setSize(containerPanel.getSize());
				

//Character Image Panel
				mainSubA = new JPanel();
				//mainSubA.setBorder((BorderFactory.createLineBorder(Color.BLACK, 3)));

				mainCharacterImage = new JLabel();
				
				
				charName.setForeground(new Color(244,244,244));
				charName.setVerticalAlignment(JLabel.TOP);
			
				charStatus.setForeground(new Color(244,244,244));
				charStatus.setVerticalAlignment(JLabel.TOP);
				charStatus.setHorizontalAlignment(JLabel.CENTER);
				
				moneyLabel.setForeground(new Color(244,244,244));
				moneyLabel.setVerticalAlignment(JLabel.TOP);
				
				addComponent(0,0,1,1,1,0.1,charStatus,mainSubA);
				addComponent(0,1,1,1,1,0.1,charName,mainSubA);
				addComponent(0,2,1,1,1,0.8,mainCharacterImage,mainSubA);
				
				
				
				
//Energy & Status Panel	
				mainSubB = new JPanel();
	
					energyLbl.setVerticalAlignment(JLabel.BOTTOM);			
					statusLbl.setVerticalAlignment(JLabel.BOTTOM);
					
					
					
					wake = new JButton("Wake");
					wake.setForeground(new Color(244,244,244));
					wake.setBackground(new Color(127,127,127));
					
					wake.setEnabled(false);
					wake.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							
							sleep.setEnabled(true);
							wake.setEnabled(false);
							runEnergyBar();
							updateSleep.cancel();
						}
						
					});
					
						
					sleep = new JButton("Sleep");
					sleep.setForeground(new Color(244,244,244));
					sleep.setBackground(new Color(127,127,127));
					
					
					sleep.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							runSleeping(sleepTime);
							wake.setEnabled(true);
							sleep.setEnabled(false);
							
					
						}
							
					});
					
					energy = new JProgressBar(0,100);
					energy.setStringPainted(false);
					energy.setValue(100);
					energy.setForeground(new Color(68,149,64));
					energy.setBorderPainted(false);
					
					
					
					energyNumber = new JLabel(String.valueOf(energy.getValue()));
					energyNumber.setFont(myFont);
					energyNumber.setForeground(new Color(244,244,244));
					energyNumber.setVerticalAlignment(JLabel.BOTTOM);
					energyNumber.setHorizontalAlignment(JLabel.RIGHT);
				
					status = new JProgressBar(0,200);
					status.setStringPainted(false);
					status.setValue(statusValue);
					status.setBorderPainted(false);
					
					if(isHero == true)
					{
						status.setForeground(new Color(166,0,0));
						status.setBackground(new Color(68,149,64));
					}
					else
					{
						status.setForeground(new Color(68,149,64));
						status.setBackground(new Color(166,0,0));
					}
					
					statusNumber = new JLabel(String.valueOf(status.getValue()));
					statusNumber.setFont(myFont);
					statusNumber.setForeground(new Color(244,244,244));
					statusNumber.setVerticalAlignment(JLabel.BOTTOM);
					statusNumber.setHorizontalAlignment(JLabel.RIGHT);
						
					
				addComponent(0,0,2,1,1,0.01,titleB,mainSubB);
				
				
				addComponent(0,2,2,1,1,0.01,moneyLabel,mainSubB);
				addComponent(0,3,2,1,1,0.01,difficultyLbl,mainSubB);
				
				
				
				addComponent(0,4,1,1,1,0.1,energyLbl,mainSubB);
				addComponent(1,4,1,1,1,0.1,energyNumber,mainSubB);
					
				addComponent(0,5,2,1,1,0.04,energy,mainSubB);
				addComponent(0,6,1,1,1,0.1,statusLbl,mainSubB);
				addComponent(1,6,1,1,1,0.1,statusNumber,mainSubB);
				addComponent(0,7,2,1,1,0.04,status,mainSubB);
					
				addComponent(0,8,1,1,0.7,0.02,sleep,mainSubB);
				addComponent(1,8,1,1,0.2,0.02,wake,mainSubB);
					
					
					
				
					
//Shop Panel			
				mainSubC = new JPanel();
	
				shopPanel.getExit().addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						crimeA.setForeground(new Color(147,198,198));
						crimeA.setBorderPainted(false);
						
						setPurchasedImages();
						moneyLabel.setText(String.valueOf(shopPanel.getCash()));
						
						runEnergyBar();
						

					}
					
				});
				
				
					JButton btnVstShop = new JButton("Visit Shop");
					btnVstShop.setForeground(new Color(244,244,244));
					btnVstShop.setBackground(new Color(127,127,127));
					
					
					btnVstShop.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							shopPanel.show();
							shopPanel.setCash(returnHeroVillan().getCash());
							updateEnergyBar.cancel();
						
						}
						
					});
					
					JButton btnInventory = new JButton("Inventory");
					btnInventory.setForeground(new Color(244,244,244));
					btnInventory.setBackground(new Color(127,127,127));
					
					btnInventory.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent arg0) 
						{
							
							try {
								buttonSound(new File("sounds/pop.ogg.wav"));
							} catch (UnsupportedAudioFileException | IOException
									| LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
							updateEnergyBar.cancel();
							cardLayout.next(containerPanel);
						}
						
					});
				
				
				addComponent(0,0,1,1,1,1,btnVstShop,mainSubC);	
				addComponent(1,0,1,1,1,1,btnInventory,mainSubC);	


		//Map & Crime Panel				
				mainSubD = new JPanel();
				mainSubD.setBackground(new Color(102,0,0));
					
		
					crimeA = new JProgressBar();
					
					setupProgressBar(crimeA,false,0,100,100);
					crimeA.setForeground(new Color(147,198,198));
					crimeA.setBorderPainted(false);
					
					
					imgMap1 = new JLabel();
					imgMap2 = new JLabel();
					imgMap3 = new JLabel();
					imgMap4 = new JLabel();
					
					imgMap1.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					imgMap2.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					imgMap3.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
					imgMap4.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
	
					imgMap1.setVerticalAlignment(JLabel.CENTER);
					imgMap1.setHorizontalAlignment(JLabel.CENTER);
					
					imgMap2.setVerticalAlignment(JLabel.CENTER);
					imgMap2.setHorizontalAlignment(JLabel.CENTER);
					
					imgMap3.setVerticalAlignment(JLabel.CENTER);
					imgMap3.setHorizontalAlignment(JLabel.CENTER);
					
					imgMap4.setVerticalAlignment(JLabel.CENTER);
					imgMap4.setHorizontalAlignment(JLabel.CENTER);			
					
				
						
				addComponent(0,0, 1,1, 1,1, imgMap1,mainSubD);				
				addComponent(1,0, 1,1, 1,1, imgMap2,mainSubD);	
				addComponent(0,1, 1,1, 1,1, imgMap3,mainSubD);	
				addComponent(1,1, 1,1, 1,1, imgMap4,mainSubD);			
				addComponent(0,2, 2,1, 1,0.1, crimeA,mainSubD);		
					
					
			addPanel(0,0,1,1,0.3,0.2,mainSubA,mainPanel);
			addPanel(1,0,2,1,0.6,0.4,mainSubB,mainPanel);
			addPanel(0,3,3,1,1,0.25,mainSubC,mainPanel);
			addPanel(4,0,4,4,0.1,0.1,mainSubD,mainPanel);
			
			
//Inventory Panel	
		inventory = new JPanel();
		inventory.setSize(containerPanel.getSize());
			JPanel inventorySub = new JPanel();
			JPanel inventorySubB = new JPanel();
			JPanel inventorySubC = new JPanel();
			
				JLabel titleI = new JLabel("Your Inventory");
				titleI.setFont(myFont);
				titleI.setForeground(new Color(244,244,244));
				
				
				JLabel inventory1 = new JLabel();
				JLabel inventory2 = new JLabel();
				JLabel inventory3 = new JLabel();
				JLabel inventory4 = new JLabel();
				JLabel inventory5 = new JLabel();
				JLabel inventory6 = new JLabel();
				
				
				inventories = new ArrayList<JLabel>();
				inventories.add(inventory1);
				inventories.add(inventory2);
				inventories.add(inventory3);
				inventories.add(inventory4);
				inventories.add(inventory5);
				inventories.add(inventory6);
				
				JButton back = new JButton("Back");
				back.setForeground(new Color(244,244,244));
				back.setBackground(new Color(127,127,127));
				
				
				back.addActionListener(new ActionListener(){
				
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							buttonSound(new File("sounds/pop.ogg.wav"));
						} catch (UnsupportedAudioFileException | IOException
								| LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						runEnergyBar();
						cardLayout.previous(containerPanel);
						crimeA.setForeground(new Color(147,198,198));

					}
					
				});
				
				JButton quit = new JButton("Quit Game");
				quit.setForeground(new Color(244,244,244));
				quit.setBackground(new Color(127,127,127));
				
				quit.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int returnvul = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
						if(returnvul == JOptionPane.NO_OPTION)
						{
							
						}
						else if(returnvul == JOptionPane.YES_OPTION)
						{
							
							endGame();
						}
						
						
					}
					
				});
				
				
				
				JButton save = new JButton("Save Game");
				save.setForeground(new Color(244,244,244));
				save.setBackground(new Color(127,127,127));
				
				save.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						writeToFile();
						JOptionPane.showMessageDialog(null, "Game Saved!");
	
					}
				});
				
				
				
					addComponent(0,1,1,1,1,1,inventory1,inventorySub);
					addComponent(1,1,1,1,1,1,inventory2,inventorySub);
					addComponent(2,1,1,1,1,1,inventory3,inventorySub);
					addComponent(0,2,1,1,1,1,inventory4,inventorySub);
					addComponent(1,2,1,1,1,1,inventory5,inventorySub);
					addComponent(2,2,1,1,1,1,inventory6,inventorySub);
				
					addComponent(0,0,1,1,1,1,back,inventorySubB);
					addComponent(1,0,1,1,1,1,quit,inventorySubB);
					addComponent(2,0,1,1,1,1,save,inventorySubB);
				
					addComponent(0,0,3,1,1,1,titleI,inventorySubC);
				
					
				addPanel(0,0,1,1,1,0.2,inventorySubC,inventory);
				addPanel(0,1,1,1,0.3,0.8,inventorySub,inventory);
				addPanel(0,2,1,1,0.3,0.2,inventorySubB,inventory);
				


//Add panels to container (In order heroVillan,heroVillan2,mainPanel)
		
		containerPanel.add(startPanel);
		containerPanel.add(heroVillanPanel);
		containerPanel.add(heroVillanPanel2);
		containerPanel.add(difficultyPanel);
		containerPanel.add(mainPanel);
		containerPanel.add(inventory);
		
//Adding to frame
		frame.add(containerPanel);
		frame.setVisible(true);

	}
	
	
	
	
//METHODS
	
//Sets colour of text depending on hero or villain	
	public void setTextColor(JLabel jLbl)
	{
		if(isHero == true)
		{
			jLbl.setForeground(heroColor);
		}
		else if(isHero == false)
		{
			jLbl.setForeground(villColor);
		}
		
	}
	
//Sets up progress selected progress bar	
	public void setupProgressBar(JProgressBar b,boolean yes,int val1,int val2,int setVal)
	{
		b.setMinimum(val1);
		b.setMaximum(val2);
		
		b.setStringPainted(yes);
		b.setValue(setVal);
	}

//Runs results of crime
	private void crimeResults()
	{
		String heroVill;
		
		if(isHero == true)
		{
			heroVill = "Hero";
		}
		else
		{
			heroVill = "Villian";
		}
		
		double randMoneyInt = newCrime.getRandCash();
		
		String actCrimeMessage = "You acted on the crime, you have gained: ";
		String ignoreCrimeMessage = "You call yourself a " + heroVill +", " + getUserName + " your status has decreased by: 15xp";
			
			
			if(returnValue == JOptionPane.NO_OPTION )//Act on crime
			{
				returnHeroVillan().setCash(returnHeroVillan().getCash() + money + randMoneyInt + extraMoney); 
				moneyLabel.setText("Your Cash: £" +String.valueOf(returnHeroVillan().getCash()));
				status.setValue(status.getValue() + statusIncrease);
				statusNumber.setText(String.valueOf(status.getValue()));

				try {
					buttonSound(new File("sounds/orb.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				energy.setValue(energy.getValue()-energyDecrease);
				JOptionPane.showMessageDialog(null,actCrimeMessage + "\n£"+ randMoneyInt + "\nand your status has increased by: \n10xp");	
			}
			else if(returnValue == JOptionPane.YES_OPTION)
			{
				status.setValue(status.getValue()-15);
				statusNumber.setText(String.valueOf(status.getValue()));

				JOptionPane.showMessageDialog(null,ignoreCrimeMessage);
			}
			
			
			if(status.getValue() >= 25 && status.getValue() < 180)
			{
				if(isHero == true)
				{
					returnHeroVillan().setCharacterStatus(statuses[0]);
				}
				else
				{
					returnHeroVillan().setCharacterStatus(statuses[1]);
				}
				
				charStatus.setText(returnHeroVillan().getCharacterStatus());
			}
			else if(status.getValue() >= 180)
			{
				
				if(isHero == true)
				{
					returnHeroVillan().setCharacterStatus(statuses[3]);
				}
				else
				{
					returnHeroVillan().setCharacterStatus(statuses[4]);
				}
				
				charStatus.setText(returnHeroVillan().getCharacterStatus());
				mainCharacterImage.setIcon(mainSuperImage.getIcon());
			}
			else if(status.getValue() <=25)
			{
				
				
				if(isHero ==true)
				{
					returnHeroVillan().setCharacterStatus(statuses[1]);
				}
				else
				{
					returnHeroVillan().setCharacterStatus(statuses[0]);
				}
				
				charStatus.setText(returnHeroVillan().getCharacterStatus());
				mainCharacterImage.setIcon(mainChangedImage.getIcon());
			}
	//	
	}
	
	
//Runs a new crime instance	using timer
	public void runNewCrime(JLabel J,JProgressBar JB)
	{
		randomCrime = new Timer();
		randomCrime.schedule(new TimerTask(){
	
			public void run()
			{
				newCrime(J, JB);	
			}
		}, randomInteger(800,3000));
	}
	
//Creates a new crime
	public void newCrime(JLabel crimeLabel,JProgressBar crimeBar)
	{
		
		crimeLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		
		MouseListener crimeListener = new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				try {
					buttonSound(new File("sounds/bow.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				crimeOnClick(crimeLabel,crimeBar);
			}
			
		};
		
		crimeLabel.addMouseListener(crimeListener);
		runCrimeProgressBar(crimeBar,chooseCrimeArea());
	}
	
//Runs events when crime area is clicked
	public void crimeOnClick(JLabel crimeLbl,JProgressBar crimeProgressB)
	{	
		
		String doAction = "";
		String ignore = IGNORECRIME;
	
			if(isHero == true)
			{
				doAction = HEROCRIME;
			}
			else
			{
				doAction = VILLANCRIME;
			}
		
			
		if(isHero == true)
		{
			newCrime = crimes.get(randomInteger(0,9));
		}
		else 
		{
			newCrime = crimesVill.get(randomInteger(0,9));
		}
		
		Object[] crimeOptionsArray = {ignore,doAction};
		
			if(sleep.isEnabled( )== false)
			{
				try {
					buttonSound(new File("sounds/bass.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"You cant act on a crime you're asleep, wake up.");
			}
			else if(energy.getValue() < 20)
			{
				try {
					buttonSound(new File("sounds/bass.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"You're energy's not high enough. Sleep for a bit, quick!");
			}	
			else
			{	
			returnValue = JOptionPane.showOptionDialog(null,newCrime.getDetails(), "Choose what to do",
													   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, 
													   crimeIcon, crimeOptionsArray, crimeOptionsArray[0]);
			
		
			crimeResults();
			stopCrime(crimeLbl,crimeProgressB);
			runNewCrime(chooseCrimeArea(), crimeProgressB);
		}
	}
	
//Stops & resets crime instance	
	public void stopCrime(JLabel c,JProgressBar b)
	{
		c.setBorder(null);
		
		MouseListener[] crimeListeners = c.getMouseListeners();
				
				for(int i = 0;i<crimeListeners.length;i++)
				{
					c.removeMouseListener(crimeListeners[i]);
				}
		
		runCrime.cancel();
		b.setValue(b.getMaximum());
		crimeA.setForeground(new Color(147,198,198));
	}
	
	public void stopEnergy()
	{
		updateEnergyBar.cancel();
	}
	
	
	

//Runs crime progress bar
	public void runCrimeProgressBar(JProgressBar crimeBa,JLabel crimeL )
	{
		runCrime = new Timer();
		runCrime.scheduleAtFixedRate(new TimerTask(){
		
			public void run()
			{
				updateCrimeBar(crimeL,crimeBa);
			}
			
		}, CONSTANT_RATE, crimeTime);
	}
	
//Only one crime at a time, one area at a time	
	public JLabel chooseCrimeArea(){
		
		JLabel chosenLabel = new JLabel();
		
		int rand = randomInteger(1, 4);
		
			if(rand == 1 )
			{
				chosenLabel = imgMap1;
			}
			
			else if(rand == 2 )
			{
				chosenLabel = imgMap2;
			}
		
			else if(rand ==3)
			{
				chosenLabel = imgMap3;
			}
			
			else if(rand ==4 )
			{
				chosenLabel = imgMap4;
			}
		
		return chosenLabel;
	}
	
	
	
//Runs energy bar
	
	public void runEnergyBar()
	{
		updateEnergyBar = new Timer();
		updateEnergyBar.scheduleAtFixedRate(new TimerTask()
		{
			public void run()
			{
				updateProgress(energy);
				energyNumber.setText(String.valueOf(energy.getValue()));
				
			}
			
		},100 ,energyTime );
	}
	
	
//Runs Sleeping timer	
	public void runSleeping(int sleepTime)
	{
		updateSleep = new Timer();
		updateSleep.scheduleAtFixedRate(new TimerTask(){
		
			public void run()
			{
				sleep(energy);
				updateEnergyBar.cancel();
				energyNumber.setText(String.valueOf(energy.getValue()));
			}
			
		},100 ,sleepTime );
	
	}

	
	
//Creates random integer between 2 integers
//Creates a random number 	
	public int randomInteger(int min,int max)
	{
		Random randomInt = new Random();
		int randomNumber = randomInt.nextInt((max-min) + 1) + min;
		
		return  randomNumber;
	}

//Adds component under GridBagLayout
	private void addComponent(int x, int y, int gridWidth, int gridHeight,double weightX, double weightY,  JComponent component, JPanel pan)
	{	
		componentContraints.anchor = GridBagConstraints.CENTER;
		componentContraints.fill = GridBagConstraints.BOTH;
		componentContraints.insets = new Insets(5,5,5,5);
		componentContraints.gridx = x;
		componentContraints.gridy = y;
		componentContraints.gridwidth = gridWidth;
		componentContraints.gridheight = gridHeight;
		componentContraints.weightx = weightX;
		componentContraints.weighty = weightY;
	
	    pan.setLayout(gridBagLayout);
	    pan.add(component, componentContraints);
	     
	}

//Adds panel under GridBagLayout
	private void addPanel(int x, int y, int gridWidth, int gridHeight,double weightX, double weightY, JPanel p,JPanel parent)
	{  
		
		panelConstraints.anchor = GridBagConstraints.CENTER;
		panelConstraints.fill = GridBagConstraints.BOTH;		
		panelConstraints.gridx = x;
		panelConstraints.gridy = y;
		panelConstraints.gridwidth = gridWidth;
		panelConstraints.gridheight = gridHeight;
		panelConstraints.weightx = weightX;
		panelConstraints.weighty = weightY;
		
		p.setBackground(new Color(51,51,51));
	   p.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
	    p.setLayout(gridBagLayout);
	    parent.setLayout(gridBagLayout);
	    parent.add(p, panelConstraints);
	   
	}

//Adds Listeners to character images
	/*Adds listener to JLabel
	 *Enables next panel button 
	 *Creates new character 
	 *Takes into account hero or villain using boolean 
	 */
	 
	public void addMouseListenerToCharacterImage(JLabel image,JButton cntnu,JLabel supe,JLabel evil)
	{
		
		image.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			
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
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) 
			{
				
				try {
					buttonSound(new File("sounds/pop.ogg.wav"));
				} catch (UnsupportedAudioFileException | IOException
						| LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				Object obj = arg0.getSource();
				((JComponent) obj).setBorder((BorderFactory.createEtchedBorder()));
				
				if(isHero == true){
				
					myHero = new Hero(getUserName, (ImageIcon) image.getIcon(), money,statuses[0],(ImageIcon)supe.getIcon(),(ImageIcon)evil.getIcon());
					selectedCharacterImage.setIcon(myHero.getCharacterImage());
					characterDiff.setIcon(myHero.getCharacterImage());
					mainCharacterImage.setIcon(myHero.getCharacterImage());
					mainCharacterImage.setText(myHero.getCharacterName());
					
					mainSuperImage.setIcon(myHero.getSuperIcon());
					mainChangedImage.setIcon(myHero.getChangedIcon());
					
				}
				else
				{
					myVillan = new Villan(getUserName, (ImageIcon) image.getIcon(), money,statuses[0],(ImageIcon)supe.getIcon(),(ImageIcon)evil.getIcon());
					selectedCharacterImage.setIcon(myVillan.getCharacterImage());
					characterDiff.setIcon(myVillan.getCharacterImage());
					mainCharacterImage.setIcon(myVillan.getCharacterImage());
					mainCharacterImage.setText(myVillan.getCharacterName());
					
					mainSuperImage.setIcon(myVillan.getSuperIcon());
					mainChangedImage.setIcon(myVillan.getChangedIcon());

					
				}
			
				
				selectedCharacterImage.setHorizontalAlignment(JLabel.CENTER);
				selectedCharacterImage.setVerticalAlignment(JLabel.CENTER);
				
				characterDiff.setHorizontalAlignment(JLabel.CENTER);
				characterDiff.setVerticalAlignment(JLabel.CENTER);
				
				mainCharacterImage.setHorizontalAlignment(JLabel.CENTER);
				mainCharacterImage.setVerticalAlignment(JLabel.TOP);
			
				cntnu.setEnabled(true);
				
				
			}
			
		});
	}
	 

	public Character returnHeroVillan(){
		
		if(isHero == true)
		{
			return myHero;
		}
		else
		{
			return myVillan;
		}
		
		
	}
	
	
//Method implements End Game  
//Updates crime progress bar using timer
	
	public void updateCrimeBar(JLabel cLbl, JProgressBar b)
	{
		b.setValue(b.getValue()-1);
		b.setValue(b.getValue());
		
	
			if(b.getValue()>b.getMaximum())
			{
				b.setValue(b.getMaximum());
			}
			if(b.getValue()< b.getMinimum())
			{
				b.setValue(b.getMinimum());  	
				
			}
			
			if(b.getValue() == 0)
			{
				runCrime.cancel();
				stopCrime(imgMap1,crimeA);
				stopCrime(imgMap2,crimeA);
				stopCrime(imgMap3,crimeA);
				stopCrime(imgMap4,crimeA);
				
				runNewCrime(chooseCrimeArea(), b);
			}
			
			if(b.getValue() > 80)
			{
				b.setForeground(new Color(68,149,64));
			}
			if(b.getValue() > 60 && b.getValue() < 80)
			{
				b.setForeground(new Color(213,192,51));
			}
			if(b.getValue() > 40 && b.getValue() < 60)
			{
				b.setForeground(new Color(216,115,48));
			}
			if(b.getValue() > 30 && b.getValue() < 40)
			{
				b.setForeground(new Color(216,48,48));
			}
			if(b.getValue() >=0  && b.getValue() < 30)
			{
				b.setForeground(new Color(117,21,21));
			}
			
	}
	
//Separate for energy
//Updates progress bar, implemented through timer	
	public void updateProgress(JProgressBar b)
	{
		b.setValue(b.getValue()-1);
		b.setValue(b.getValue());
		
		endGame = "You have lost the game!";
		endGamestats = "Your end stats are: final cash: " + moneyLabel.getText() + " Final Form: " + returnHeroVillan().getCharacterStatus(); //get cash & hero value
			
			if(b.getValue()>b.getMaximum())
			{
				b.setValue(b.getMaximum());
			}
			if(b.getValue()< b.getMinimum())
			{
				b.setValue(b.getMinimum());			
			}
			if(b.getValue() ==b.getMinimum())
			{
//End Game clause				
				updateEnergyBar.cancel();
				
				b.setValue(b.getMaximum());
				stopCrime(imgMap1,crimeA);
				stopCrime(imgMap2,crimeA);
				stopCrime(imgMap3,crimeA);
				stopCrime(imgMap4,crimeA);
		
				returnHeroVillan().setCharacterStatus(statuses[5]);
				returnHeroVillan().setCharacterImage(new ImageIcon("Images/human.png"));
		
				endGame();
				
			}
			
			if(b.getValue() > 80)
			{
				b.setForeground(new Color(68,149,64));
			}
			if(b.getValue() > 60 && b.getValue() < 80)
			{
				b.setForeground(new Color(213,192,51));
			}
			if(b.getValue() > 40 && b.getValue() < 60)
			{
				b.setForeground(new Color(216,115,48));
			}
			if(b.getValue() > 30 && b.getValue() < 40)
			{
				b.setForeground(new Color(216,48,48));
			}
			if(b.getValue() >=0  && b.getValue() < 30)
			{
				b.setForeground(new Color(117,21,21));
			}
			
			
	}

////Updates progress bar during sleep
	public void sleep(JProgressBar jb)
	{
		jb.setValue(jb.getValue()+1);
		jb.setValue(jb.getValue());
		
			if(jb.getValue()>jb.getMaximum())
			{
				jb.setValue(jb.getMaximum());
			}
			if(jb.getValue()< jb.getMinimum())
			{
				jb.setValue(jb.getMinimum());			
			}
	}
		
//End Game method 	
	public void endGame()
	{
		
		JOptionPane.showMessageDialog(frame, endGame + endGamestats, "End Game", JOptionPane.PLAIN_MESSAGE,returnHeroVillan().getCharacterImage());
		cardLayout.first(containerPanel);	
		
		mainCharacterImage.setIcon(null);
		charName.setText(null);
		charStatus.setText(null);
		status.setValue(100);
		statusNumber.setText(String.valueOf(status.getValue()));
		
		moneyLabel.setText(null);
		difficultyLbl.setText(null);
		
		stopCrime(imgMap1, crimeA);
		stopCrime(imgMap2, crimeA);
		stopCrime(imgMap3, crimeA);
		stopCrime(imgMap4, crimeA);
		
		stopEnergy();
		
		
		
	}


//Sets ShopItem images into mainPanel
	public void setPurchasedImages() 
	{
			for(int i = 0;i < items.size(); i++)
			{
				inventories.get(i).setIcon(items.get(i).getImage());
				setPowerUp(items.get(i).getAbility());
			}
			
	}
	
	
	
	
//Sets abilities for items that have been purchased
	public void setPowerUp(Ability abb)
	{
		ability = abb;
		
		switch(abb){
		
		case FAST_SLEEP:
			sleepTime = 160;
		break;
		
		case SLOW_ENERGY:
			energyTime = 600;
		break;
		
		case SLOW_CRIME:
			crimeTime = 120;
		break;
		
		case MORE_MONEY:
			extraMoney = 100;
		break;
		
		case MORE_STATUS:
			statusIncrease = 20;
		break;
		
		case INSTANTSUPERSTATUS:
			status.setValue(180);
			statusNumber.setText(String.valueOf(status.getValue()));
		break;
		}
	}

	
	
//Sets up difficulty for game
	public void setupDifficulty(Difficulty diff,Character cha)
	{
		difficulty = diff;
		switch (diff){
		case EASY:
			
			cha.setCash(4000);
			status.setValue(120);
			statusNumber.setText(String.valueOf(status.getValue()));
			energyDecrease = 10;
			
		break;
		
		case MEDIUM:
			cha.setCash(200);
			status.setValue(100);
			statusNumber.setText(String.valueOf(status.getValue()));
			energyDecrease = 20;
		
		break;
		
		case HARD:
			cha.setCash(0);
			status.setValue(40);
			statusNumber.setText(String.valueOf(status.getValue()));
			energyDecrease = 35;

		}
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
	
	public void writeToFile()
	{
		Path filePath = Paths.get(fileName);
		File file = filePath.toFile();
		
		try(PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(file))))
		{
			
			toFile.println(charName.getText());
			toFile.println(charStatus.getText());
			toFile.print(mainCharacterImage.getIcon()+ "\n");
			toFile.println((int) money);
			toFile.println(status.getValue());
			
			for(int i = 0; i< inventories.size();i++)
			{
				toFile.print(inventories.get(i).getIcon() + "\n");
			}
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
						
		
		
	}

	
	public void readFromFile()
	{
		try(BufferedReader in = new BufferedReader(new FileReader(fileName)))
		{
			String characterName =in.readLine();
			charName.setText(characterName);
			System.out.println(characterName);
			
			
			String characterStatus = in.readLine();
			charStatus.setText(characterStatus);
			System.out.println(characterStatus);
			
			String characterImg = in.readLine();
			System.out.println(characterImg);
			mainCharacterImage.setIcon(new ImageIcon(characterImg));
			
			double monies = in.read();
			moneyLabel.setText("Your Cash: £" + String.valueOf(monies));
			System.out.println(monies);
			
			int stat = in.read();
			status.setValue(stat);
			System.out.println(stat);
			
			for(int i = 0; i< inventories.size();i++)
			{	
				String invenImage = in.readLine();
				inventories.get(i).setIcon(new ImageIcon(invenImage)); 
			}

			

		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}


	
