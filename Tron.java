/*
 *Tron.java
 *Will Jarvis-Cross and Namashi Sivaram
 *This program creates a user friendly recreation of the classic tron game. 
 *Two players control their line as they try to navigate the board. The goal 
 *of the game is to stay alive longer than your opponent. You can do this by 
 *playing safe or going for the kill. When the user runs the game, they are 
 *greeted with a menu where you can look at the instructions and press play. 
 *The game is made more interesting with power ups which can change your speed, 
 *size, and even give you invincibility for a short period of time. Each player 
 *has 3 lives and when they run out of lives, the game ends.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Tron extends JFrame implements ActionListener, KeyListener{//This class creates the actual tron game and all the parameters
	Timer myTimer;
	GamePanel game;

    public Tron() {
		super("TRON LEGACY OF THE FALLEN HEROES OF THE NORDIC TRIBE");//Don't ask why we chose this title
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,700);
		myTimer = new Timer(10, this);	 // trigger every 100 ms
		game = new GamePanel();//creating the GamePanel
		add(game);
		setResizable(false);
		new TronMenu(this);//creates a menu
    }

	public void actionPerformed(ActionEvent evt){//the game is constantly refreshing and repainting
		if(game!= null){
			System.out.println("R");
			game.refresh();
			game.repaint();
		}
	}
	public void start(){
		myTimer.start();//timer starts which means the game starts
		setVisible(true);//now the users can see the actual game
	}
	public void keyTyped(KeyEvent e) {}//checks to see if something was typed

    public void keyPressed(KeyEvent e) {//checks to see if a key was pressed
    	game.setKey(e.getKeyCode(),true);
    }
    public void keyReleased(KeyEvent e) {//checks to see if a key was released
    	game.setKey(e.getKeyCode(),false);
    }
    public static void main(String[] arguments) {//this creates the Tron game
		Tron frame = new Tron();
    }
}

//===============================================================================================
//===============================================================================================
/*
TronMenu
Will Jarvis-Cross and Namashi Sivaram
This class acts as a menu screen and an instructions screen. 
The menu is the first thing the user sees and gives them the 
option to look at the instructions or go straight to the game.
*/
class TronMenu extends JFrame implements ActionListener{//This class acts as a menu screen and an instructions screen. 
														//The menu is the first thing the user sees and gives them the 
														//option to look at the instructions or go straight to the game.
	private Tron me3;//A Tron object
	JButton playBtn = new JButton("PLAY");//The play button on the menu and the instructions screen
	JButton instructBtn = new JButton("INSTRUCTIONS");//The instructions button that leads to the instructions screen
	static int menuNum=1;//1 means it is on the menu and 2 means the instruction screen

	public TronMenu(Tron m){//this creates the menu and the instructions screen when needed and it takes in a Tron object
		super ("TRON LEGACY OF THE FALLEN HEROES OF THE NORDIC TRIBE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize (800, 610);
		me3 = m;
		playBtn.addActionListener(this);
		instructBtn.addActionListener(this);
		ImageIcon back = new ImageIcon("MENUBACK.jpg");//background for the menu
		JLabel backLabel = new JLabel(back);//backLabel is the diplay area for the menuback image 
		JLayeredPane mPage=new JLayeredPane();// LayeredPane allows me to control what shows on top
		mPage.setLayout(null);
		backLabel.setSize(800,600);
		backLabel.setLocation(0,0);
		mPage.add(backLabel,1);// The last number controls what's on top
		
		if (menuNum==1){//displaying the menu screen										
			playBtn.setSize(200,60);//adding play button
			playBtn.setLocation(425,400);
			mPage.add(playBtn,0);
			
			instructBtn.setSize(200,60);//adding instructions button
			instructBtn.setLocation(150,400);
			mPage.add(instructBtn,0);
			
			//I got all the following fonts from a website and downloaded the image
			ImageIcon title1 = new ImageIcon("tronTitle1.png");//It's a long title so I had to break it up into two 
															   //different images and display the second below the first.
															   //This is the first part 
			JLabel titleLabel1 = new JLabel(title1);
			titleLabel1.setSize(800,40);
			titleLabel1.setLocation(-5,50);
			mPage.add(titleLabel1,0);
			
			ImageIcon title2 = new ImageIcon("tronTitle2.png");//this is the second part of the title
			JLabel titleLabel2 = new JLabel(title2);
			titleLabel2.setSize(800,40);
			titleLabel2.setLocation(5,100);
			mPage.add(titleLabel2,0);
		}
		else{//This is for the instructions screen
			playBtn.setSize(200,60);//The play button is still there but its centered now
			playBtn.setLocation(300,420);
			mPage.add(playBtn,0);
			
			ImageIcon instructions = new ImageIcon("instructions.png");//This is the title for the instructions screen. It reads, "INSTRUCTIONS".
			JLabel instructionsLabel = new JLabel(instructions);
			instructionsLabel.setSize(800,140);
			instructionsLabel.setLocation(-5,0);
			mPage.add(instructionsLabel,0);
			
			ImageIcon p1Font = new ImageIcon("p1Font.png");//This is a text that reads, "PLAYER 1"
			JLabel p1FontLabel = new JLabel(p1Font);
			p1FontLabel.setSize(400,140);
			p1FontLabel.setLocation(-30,220);
			mPage.add(p1FontLabel,0);
			
			ImageIcon p2Font = new ImageIcon("p2Font.png");//This is a text that reads, "PLAYER 2"
			JLabel p2FontLabel = new JLabel(p2Font);
			p2FontLabel.setSize(400,140);
			p2FontLabel.setLocation(410,220);
			mPage.add(p2FontLabel,0);
			
			ImageIcon wasd = new ImageIcon("wasd.png");//under where it says player 1, a picture of the wasd keys are added to tell the user what keys to use
			JLabel wasdLabel = new JLabel(wasd);
			wasdLabel.setSize(400,190);
			wasdLabel.setLocation(-30,280);
			mPage.add(wasdLabel,0);
			
			ImageIcon arrows = new ImageIcon("arrowKeys.png");//under where it says player 2, a picture of the arrow keys are added to tell the user what keys to use
			JLabel arrowsLabel = new JLabel(arrows);
			arrowsLabel.setSize(400,190);
			arrowsLabel.setLocation(410,280);
			mPage.add(arrowsLabel,0);
		}
		add(mPage);//adding the mPage with all the pictures and buttons to the screen
		setVisible(true);//so the user can see the game
	}

    public void actionPerformed(ActionEvent evt) {//I use this to check if one of the buttons was pressed
    	if(evt.getSource()==playBtn){//if they press play
	    	setVisible(false);//the menu is now invisible
			me3.start();//calls a method which starts the tron game
    	}
    	else if(evt.getSource()==instructBtn){//if they press the instructions button
    		menuNum=2;//means the instructions page will be displayed when it gets called
    		setVisible(false);//the menu is now invisible
    		new TronMenu(me3);//making a new TronMenu and this time it is the instructions page
    		
    	}
		
    }
}
//=================================================================================================
//=================================================================================================

/*
GamePanel
Will Jarvis-Cross and Namashi Sivaram

This class creates the game portion of Tron. Both users are 
able to battle it out in an intense game where each player 
has 3 lives. If a player runs into one of the lines or goes 
out of bounds then they die. Power ups randomly appear on 
the stage which give the players advantages. After the game ends, 
the players can rematch.
*/

class GamePanel extends JPanel{
	private TronRacer player1,player2;//these are the 2 players
	private int mousex,mousey;//location of the mouse
	private boolean[] keys;//used to see what keys are pressed
	private int[][] boardtrack=new int[800][600];//2D list of all the pixels on the board
	Image tronBack,starim,large,small,clear,speedup,overlay,heart,pressSpace,p1win,p2win,playagain,tie;
	final static int RIGHT=1;//directions
	final static int LEFT=2;
	final static int UP=3;
	final static int DOWN=4;
	int framecount=0;//counts the frames used in certain parts of code
	private PowerUPs power=new PowerUPs();//creates a new PowerUPs
	private Random rand=new Random();//used to create a random number
	int chancemod=0;//chance modifer that will increase chances of item drops
	int despawnframe=0;//counter for power up despawn
		


	public GamePanel(){


		player1=new TronRacer(Color.red,200,300);
		player2=new TronRacer(Color.blue,605,300);
		player1.setDir(RIGHT);//set default directions
		player2.setDir(LEFT);
		tronBack=new ImageIcon("tronBack.png").getImage();
		starim=new ImageIcon("star.png").getImage();
		large=new ImageIcon("large.png").getImage();
		small=new ImageIcon("small.png").getImage();
		clear=new ImageIcon("clear.png").getImage();
		speedup=new ImageIcon("speedup.png").getImage();
		overlay=new ImageIcon("overlay.png").getImage();
		heart=new ImageIcon("heart.png").getImage();
		pressSpace=new ImageIcon("pressSpace.png").getImage();
		p1win=new ImageIcon("p1 win.png").getImage();
		p2win=new ImageIcon("p2 win.png").getImage();
		playagain=new ImageIcon("play again.png").getImage();
		tie=new ImageIcon("tie.png").getImage();
		
		keys=new boolean[KeyEvent.KEY_LAST+1];
		setSize(800,700);
	}
	public void setKey(int k, boolean v) {//sets key from frame for input
    	keys[k] = v;
    }
    //REFRESH
    //handles input from keyboard from the user as well as placing of powerups
    public void refresh() {

    	if(player1.getAlive()&&player2.getAlive()){//if the game is still going
	    	if(keys[KeyEvent.VK_D] && player1.getDir()!=LEFT){//makes p1s direction right if it isnt going left
				player1.setDir(RIGHT);
			}
			else if(keys[KeyEvent.VK_A] && player1.getDir()!=RIGHT){//makes p1s direction left if it isnt going right
				player1.setDir(LEFT);
			}
			else if(keys[KeyEvent.VK_W] && player1.getDir()!=DOWN){//makes p1s direction up if it isnt going down
				player1.setDir(UP);
			}
			else if(keys[KeyEvent.VK_S] && player1.getDir()!=UP){//makes p1s direction down if it isnt going up
				player1.setDir(DOWN);
			}

	    	if(keys[KeyEvent.VK_RIGHT] && player2.getDir()!=LEFT){////makes p2s direction right if it isnt going left
				player2.setDir(RIGHT);
			}
			else if(keys[KeyEvent.VK_LEFT] && player2.getDir()!=RIGHT){//makes p2s direction left if it isnt going right
				player2.setDir(LEFT);
			}
			else if(keys[KeyEvent.VK_UP] && player2.getDir()!=DOWN){//makes p2s direction up if it isnt going down
				player2.setDir(UP);
			}
			else if(keys[KeyEvent.VK_DOWN] && player2.getDir()!=UP){//makes p1s direction down if it isnt going up
				player2.setDir(DOWN);
			}
			collide();//checks what both players hit on the grid
			movepiece();//actually moves the racer on the grid
			//power integration
			if (!player1.getPower().equals("")||!player2.getPower().equals("")){//if a power is active
	    		framecount+=1;//adds to the frame timer for power deactivation
	    		if (framecount==250){// if the powers time is over
	    			player1.clearpowers();// removes the powers from the players and resets the timer
	    			player2.clearpowers();
	    			framecount=0;
	    		}
	    	}
	    	else if(framecount==0&& power.placed()==false){//otherwise if no powers are active and a power isnt placed
	    		chancemod+=10;//adds to the chance modifier
	    		int placedchance=rand.nextInt(5200-Math.min(chancemod,5195));//gets a number between 0 and 6000-chancemod as to have an increasing spawn chance every frame
	    		if (placedchance==5){
	    			boardtrack=power.placepowerup("star",boardtrack);//places a star powerup on the board
	    			chancemod=0;//resets chance mod
	    		}
	    		else if (placedchance==4){
	    			boardtrack=power.placepowerup("large",boardtrack);//places a large powerup on the board
	    			chancemod=0;//resets chance mod
	    		}
	    		else if (placedchance==3){
	    			boardtrack=power.placepowerup("small",boardtrack);//places a small powerup on the board
	    			chancemod=0;//resets chance mod
	    		}
	    		else if (placedchance==2){
	    			boardtrack=power.placepowerup("clear",boardtrack);//places a clear powerup on the board
	    			chancemod=0;//resets chance mod
	    		}
	    		else if (placedchance==1){
	    			boardtrack=power.placepowerup("speed",boardtrack);//places a speed powerup on the board
	    			chancemod=0;//resets chance mod
	    		}
	
	    	}
	    	if(power.placed()){//if a power is placed on the board but no one as picked it up
	    		despawnframe+=1;//adds to the power despawn counter
	    		if (despawnframe==500){//if 500 frames have passed since item spawn and no one has picked it up
	    			boardtrack=power.removepowers(boardtrack);//removes the power
	    			power.clearPowers();//makes them all not placed
	    			despawnframe=0;//resets despawn timer
	    		}
	    	}
    	}
    	else{//if a player has died
    		if(keys[KeyEvent.VK_SPACE]){//if they hit space bar
    			//resets all tronracer and board attributes back to default
	    		player1.setTronX(200);
	    		player1.setTronY(300);
	    		player2.setTronX(605);
	    		player2.setTronY(300);
	    		player1.setSize(5);
	    		player2.setSize(5);
	    		player1.setPower("clear",true);
	    		player2.setPower("clear",true);
	    		player1.clearpowers();
	    		player2.clearpowers();
	    		player1.setDir(RIGHT);
	    		player2.setDir(LEFT);
	    		clearBoard();
	    		power.clearPowers();
    			if (player1.getLives()>=1&&player2.getLives()>=1){//if both players have lives left
    				player1.setAlive(true);//resets them back to alive
	    			player2.setAlive(true);
	    			
	    			
	    			
    			}
    			else{//otherwise if someone is out of lives
    				player1.setLives(3);//resets thier lives to 3 and makes them alive to start a new game
    				player2.setLives(3);
    				player1.setAlive(true);
	    			player2.setAlive(true);
    			}
    		}
    		
    	}
    	
    	






    }
    public void movepiece(){//This is responsible for moving where the player's piece will be drawn. It takes into account the direction and speed of the player

	   	if (player1.getDir()==RIGHT){//these 4 if and else ifs move the player 1 piece's location based on the direction and speed
	   		player1.setTronX(player1.getTronX()+player1.getSpeed());
	   	}
	   	else if (player1.getDir()==LEFT){
	   		player1.setTronX(player1.getTronX()-player1.getSpeed());
	   	}
	   	else if (player1.getDir()==UP){
	   		player1.setTronY(player1.getTronY()-player1.getSpeed());
	   	}
	   	else if (player1.getDir()==DOWN){
	   		player1.setTronY(player1.getTronY()+player1.getSpeed());
	   	}

	   	if (player2.getDir()==RIGHT){//these 4 if and else ifs move the player 2 piece's location based on the direction and speed
	   		player2.setTronX(player2.getTronX()+player2.getSpeed());
	   	}
	   	else if (player2.getDir()==LEFT){
	   		player2.setTronX(player2.getTronX()-player2.getSpeed());
	   	}
	   	else if (player2.getDir()==UP){
	   		player2.setTronY(player2.getTronY()-player2.getSpeed());
	   	}
	   	else if (player2.getDir()==DOWN){
	   		player2.setTronY(player2.getTronY()+player2.getSpeed());
	   	}


    	Block p1b=new Block(player1.getTronX(),player1.getTronY(),player1.getSize());//creates a new Block which holds the information of where the current location of player 1 is and its size
    	player1.addBlock(p1b);//adds the previously created block to all its other blocks
    	Block p2b=new Block(player2.getTronX(),player2.getTronY(),player2.getSize());//creates a new Block which holds the information of where the current location of player 2 is and its size
    	player2.addBlock(p2b);//adds the previously created block to all its other blocks

    }

    public void paintComponent(Graphics g){//This is responsible for drawing the images and rectangles needed for the game
    	if (player1.getAlive()==true && player2.getAlive()==true){//only draws everything if both players are alive
    		g.drawImage(tronBack,0,0,this);//draws the background for the game

    		g.setColor(player1.getTronColor());//setting the player's colour
    		for (int i=0;i<player1.getBlocksSize();i++){//this loop draws all the squares for the line player 1 has created
    			//it goes through everything that was put in the block arraylist and draws all the squares it has been over which forms a line
        		g.fillRect(player1.getBlock(i).getBlockx(),player1.getBlock(i).getBlocky(),player1.getBlock(i).getSize(),player1.getBlock(i).getSize());
    		}
        	g.setColor(player2.getTronColor());//setting the second player's colour
        	for (int k=0;k<player2.getBlocksSize();k++){//this loop draws all the squares for the line player 2 has created
        		//it goes through everything that was put in the block arraylist and draws all the squares it has been over which forms a line
        		g.fillRect(player2.getBlock(k).getBlockx(),player2.getBlock(k).getBlocky(),player2.getBlock(k).getSize(),player2.getBlock(k).getSize());
        	}
        	if (power.placed()){//if there's a power up somewhere on the grid
        		if (power.starPlaced()){//if it's a star then it draws the star power up image in a randomly determined spot
        			g.drawImage(starim,power.getX(),power.getY(),this);
        		}
        		else if(power.largePlaced()){//if it's a increase size power up then it draws the large power up image in a randomly determined spot
        			g.drawImage(large,power.getX(),power.getY(),this);
        		}
        		else if(power.smallPlaced()){//if it's a decrease size power up then it draws the small power up image in a randomly determined spot
        			g.drawImage(small,power.getX(),power.getY(),this);
        		}
        		else if(power.clearPlaced()){//if it's a clear grid power up then it draws the clear power up image in a randomly determined spot
        			g.drawImage(clear,power.getX(),power.getY(),this);
        		}
        		else if(power.speedPlaced()){//if it's a increase speed power up then it draws the speed power up image in a randomly determined spot
        			g.drawImage(speedup,power.getX(),power.getY(),this);
        		}
        	}
        	
        	g.drawImage(overlay,0,570,this);//draws image which shows
			
			for (int j=0;j<player1.getLives();j++){//this draws red hearts for player 1 to show how many lives they have left
        		g.drawImage(heart,240+j*30,578,this);
        	}
        	for (int j=0;j<player2.getLives();j++){//this draws red hearts for player 2 to show how many lives they have left
        		g.drawImage(heart,670+j*30,578,this);
        	}
        	
        	if (player1.getPower().equals("star")){//These if and else ifs draw the power up symbol for player 1 (if there is a current power up being used) on the overlay to show the user what power they have
        		g.drawImage(starim,240,610,this);
        	}
        	else if (player1.getPower().equals("large")){
        		g.drawImage(large,240,610,this);
        	}
        	else if (player1.getPower().equals("small")){
        		g.drawImage(small,240,610,this);
        	}
        	else if (player1.getPower().equals("speed")){
        		g.drawImage(speedup,240,610,this);
        	}

        	
        	if (player2.getPower().equals("star")){//These if and else ifs draw the power up symbol for player 2 (if there is a current power up being used) on the overlay to show the user what power they have
        		g.drawImage(starim,670,610,this);
        	}
        	else if (player2.getPower().equals("large")){
        		g.drawImage(large,670,610,this);
        	}
        	else if (player2.getPower().equals("small")){
        		g.drawImage(small,670,610,this);
        	}
        	else if (player2.getPower().equals("speed")){
        		g.drawImage(speedup,670,610,this);
        	}
		
    	}
    	if (player1.getAlive()==false||player2.getAlive()==false){//if atleast 1 player is dead
    		if (player1.getLives()>=1&&player2.getLives()>=1){//they both have to have more than 1 life left
    			g.drawImage(pressSpace,30,300,this);//draws image that says "PRESS SPACE"
    		}
    		else{
    			if (player1.getLives()==0&&player2.getLives()==0){//they both have no lives left so it is a tie game
    				g.drawImage(tie,300,100,this);
    			}
    			else if (player1.getLives()==0){//player 1 has no lives left
    				g.drawImage(p2win,20,100,this);	
    			}
    			else if(player2.getLives()==0){//player 2 has no lives left
    				g.drawImage(p1win,20,100,this);	
    			}
    			g.drawImage(playagain,30,200,this);//offers users to play another game
    			g.drawImage(pressSpace,30,300,this);
    		}
    	}
    }
    /*COLLIDE
     *handles whether a tron racer hits a line or border or if it gets a powerup
     */
    public void collide(){
    	//sets a tron racer to dead if it crosses the game borders
    	if (player1.getTronX()<player1.getSize()||player1.getTronX()+player1.getSize()>790 || player1.getTronY()<player1.getSize() || player1.getTronY()>570-player1.getSize()){
    		player1.setAlive(false);
    	}
    	if (player2.getTronX()<player2.getSize() || player2.getTronX()+player2.getSize()>790 || player2.getTronY()<player2.getSize() || player2.getTronY()>570-player2.getSize()){
    		player2.setAlive(false);
    	}
    	if (player1.getAlive()){// if player 1 is still alive

	    	for (int i=0;i<player1.getSize();i++){//checks evey pixel of the front of the tron racer (front is dependent on the way you are going)

	    		if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==1&&!player1.getPower().equals("star")){// if the pixel is on another line and the racer doesnt have the
	    			player1.setAlive(false);																			   // star powerup(invinciblity) sets it to dead
	    		}
	    		if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==2){//if the pixel lands on a space with a 2 (meaning it is an empty space with a star on it (0(empty)+2=2 instead of 1(taken)+2=3)
	    			boardtrack=power.removepowers(boardtrack);//removes the now taken power from the board
	    			power.clearPowers();//sets all the placed powers to not placed
	    			player1.setPower("star",true);//gives player 1 the star power 
	    			despawnframe=0;//resets the power up despawn frame timer back to 0
	    		}
	    		else if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==4){//if the pixel lands on a space with a 4 (meaning it is an empty space with a largeup on it (0(empty)+4=4 instead of 1(taken)+4=5)
	    			boardtrack=power.removepowers(boardtrack);//removes the now taken large up from the board
	    			player1.setSize(15);//sets the players size to 15 as per the large power up
	    			power.clearPowers();//sets all placed powers to not placed
	    			player1.setPower("large",true);//gives the player 1 the large power up
	    			despawnframe=0;////resets the power up despawn frame timer back to 0
	    		}
	    		else if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==6){//if the pixel lands on a space with a 6 (meaning it is an empty space with a smallpwr on it (0(empty)+6=6 instead of 1(taken)+6=7)
	    			boardtrack=power.removepowers(boardtrack);//removes powers from board
	    			player1.setSize(3);//sets the players size to 3 as per the small power
	    			power.clearPowers();//clears placed powers
	    			player1.setPower("small",true);// gives the player 1 the small power
	    			despawnframe=0;// resets despawn frames
	    		}
	    		else if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==8){//if the pixel lands on a space with a 8 (meaning it is an empty space with a clear pwr on it (0(empty)+8=8 instead of 1(taken)+8=9)
	    			boardtrack=power.removepowers(boardtrack);// removes powers from the board
	    			player1.setPower("clear",true);//uses the clear power on p1(empties all its blocks(lines))
	    			player2.setPower("clear",true);//uses the clear power on p2(empties all its blocks(lines))
	    			clearBoard();//makes all spaces on the board a 0(empty)
	    			power.clearPowers();//clears all placed powers
	    			despawnframe=0;//resets despawn frames
	    		}
	    		else if (boardtrack[player1.getFront()[i][0]][player1.getFront()[i][1]]==10){//if the pixel lands on a space with a 10 (meaning it is an empty space with a speedup on it (0(empty)+10=10 instead of 1(taken)+10=11)
	    			boardtrack=power.removepowers(boardtrack);//removes powers from board
	    			power.clearPowers();//clears all placed powers
	    			player1.setPower("speed",true);//gives p1 a speed up power
	    			despawnframe=0;//resets the despawn counter
	    		}
	    		for (int j=0;j<player1.getSize();j++){//loop that places all the pixels of the racer on the grid
	    			boardtrack[player1.getTronX()+i][player1.getTronY()+j]=1;
	    		}

	    	}
	    	
    	}
    	if (player2.getAlive()){// if player 2 is alive
    	// does and checks for the exact same things as the code above for player 1 except for player 2

	    	for (int i=0;i<player2.getSize();i++){

	    		if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==1&&!player2.getPower().equals("star")){
	    			player2.setAlive(false);
	    		}
	    	 	if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==2){
	    			boardtrack=power.removepowers(boardtrack);
	    			power.clearPowers();
	    			player2.setPower("star",true);
	    			despawnframe=0;
	    		}
	    		else if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==4){
	    			boardtrack=power.removepowers(boardtrack);
	    			player2.setSize(15);
	    			power.clearPowers();
	    			player2.setPower("large",true);
	    			despawnframe=0;
	    		}
	    		else if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==6){
	    			boardtrack=power.removepowers(boardtrack);
	    			player2.setSize(3);
	    			power.clearPowers();
	    			player2.setPower("small",true);
	    			despawnframe=0;
	    		}
	    		else if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==8){
	    			boardtrack=power.removepowers(boardtrack);
	    			player2.setPower("clear",true);
	    			player1.setPower("clear",true);
	    			clearBoard();
	    			power.clearPowers();
	    			despawnframe=0;
	    			
	    		}
	    		else if (boardtrack[player2.getFront()[i][0]][player2.getFront()[i][1]]==10){
	    			boardtrack=power.removepowers(boardtrack);
	    			power.clearPowers();
	    			player2.setPower("speed",true);
	    			despawnframe=0;
	    		}
	    		for (int j=0;j<player2.getSize();j++){
	    			boardtrack[player2.getTronX()+i][player2.getTronY()+j]=1;
	    		}
	    	}
    	}
    	if (player1.getAlive()==false){player1.setLives(player1.getLives()-1);}//subracts a life from whoever lost
    	if (player2.getAlive()==false){player2.setLives(player2.getLives()-1);}
    	
    }
    private void clearBoard(){//method that fills the board back up with 0 (clears it)
    	for (int i = 0; i < boardtrack.length; i++){
        	Arrays.fill(boardtrack[i],0);
    	}
    }


    // ------------ MouseListener ------------------------------------------
    


    // ---------- MouseMotionListener ------------------------------------------



}
