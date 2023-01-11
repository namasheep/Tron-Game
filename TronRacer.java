/*
 TronRacer.java
 Will Jarvis-Cross and Namashi Sivaram
 TronRacer creates the two players Tron uses in the game. 
 It contains all their attributes like position, direction, 
 size and so on. We can set and get during the game.
*/
import java.awt.*;
import java.util.ArrayList;
public class TronRacer {
 	private boolean alive=true;//both players start out alive
 	private int locx,locy,dir,size;//the player's location, the direction their heading, and their size
 	private int[] front=new int[4];//provides the location of the square accounting its direction. It helps when determining a collision
 	private Color color=Color.blue;
 	final int RIGHT=1;//directions
	final int LEFT=2;
	final int UP=3;
	final int DOWN=4;
	private int speed=3;
 	ArrayList<Block> blocks = new ArrayList<Block>();//contains location and size for every square drawn (see Block.java)
 	private boolean[] poweractive=new boolean[5];//shows if the player is using a power ups. Theres 5 total power ups and each is either true or false
 	private int lives=3;//how many lives the player has left

    public TronRacer(Color colorOfLine,int locx,int locy) {//creates the original players with the given settings
    	color=colorOfLine;
    	System.out.println(locx);
    	this.locx=locx;
    	this.locy=locy;
		size=5;
    }
	public int getTronX(){//gets the x-position
		return locx;
	}
	public int getTronY(){//gets the y-position
		return locy;
	}
	public void setTronX(int x){//sets the x-position
		locx=x;
	}
	public void setTronY(int y){//sets the y-position
		locy=y;
	}
	public void setAlive(boolean DorA){//setting alive to true or false
		alive=DorA;
	}
	public boolean getAlive(){//getting the alive status
		return alive;
	}
	public Color getTronColor(){//getting colour of the character
		return color;
	}
	public void setFront(int[] dir){//setting positions of corners of the player's square
		front=dir;
	}
	public void setSize(int sz){//setting size of the square drawn
		size=sz;
	}
	public int getSize(){//getting size of the square drawn
		return size;
	}
	public void addBlock(Block bl){//adding the location and size to blocks which is done for every movement
		blocks.add(bl);
	}
	public Block getBlock(int i){//retrieving the attributes of a certain square on the board
		return blocks.get(i);
	}
	public int[][] getFront(){//returns the location of the corners of the square drawn according to its direction
		int[][] frontlen=new int[size][2];
		if (dir==RIGHT){//if they are going right, then the end of the square is the location plus its size and for y it starts at locy
			for (int i=0;i<size;i++){
				frontlen[i][0]=locx+size+1;
				frontlen[i][1]=locy+i;
			}
		}
		else if (dir==LEFT){//if they're going left, then you start at the x and y location for both
			for (int i=0;i<size;i++){
				frontlen[i][0]=locx-1;
				frontlen[i][1]=locy+i;
			}
		}
		else if (dir==UP){
			for (int i=0;i<size;i++){//if they're going up, then you start at the x and y location for both
				frontlen[i][0]=locx+i;
				frontlen[i][1]=locy-1;
			}
		}
		else if (dir==DOWN){//if they're going down, then you start at locx for the x portion and at the bottom for the y portion
			for (int i=0;i<size;i++){
				frontlen[i][0]=locx+i;
				frontlen[i][1]=locy+size+1;
			}
		}

		return frontlen;//returning the 2D array created
	}
	public void setDir(int d){//setting the direction the player is going
		dir=d;
	}
	public int getDir(){//returning the direction the player is going
		return dir;
	}
	public int getBlocksSize(){//returning the size of the square drawn
		return blocks.size();
	}
	
	public void clearpowers(){//this clears the power ups that the player is using when necessary
		if (size==15){//if your reseting the size powerup we have to reset the player's location so it is not offset
			size=5;
			if (dir==RIGHT){locx+=12;locy+=5;}
			else if(dir==LEFT){locy+=5;}
			else if (dir==DOWN){locy+=12;locx+=5;}
			else if (dir==UP){locx+=5;}
			

		}
		if (size==3){//same thing from when going from a big size to regular. we have to reset the location so it isn't offset
			size=5;
			if (dir==RIGHT||dir==LEFT){locy-=1;}
			else if (dir==DOWN||dir==UP){locx-=1;}
		}
		if (speed==5){//reseting the speed
			speed=3;
		}

		for (int i =0;i<5;i++){//making all the power ups false meaning they are not active
			poweractive[i]=false;
		}
		
	}
	public String getPower(){//checks to see if any of the powers are active
		if (poweractive[0]){
			return "star";
		}
		else if (poweractive[1]){
			return "large";
		}
		else if (poweractive[2]){
			return "small";
		}
		else if (poweractive[3]){
			return "speed";
		}
		else{
			return "";
		}
	}
	public void setPower(String pow, boolean on){//takes what power is being referenced and whether it is true or false, and then it changes poweractive accordingly
		if (pow.equals("star")){//invincible power up
			poweractive[0]=on;
		}
		else if (pow.equals("large")){
			poweractive[1]=on;
			if (poweractive[1]){//depending on which direction your going, the location is changed so you aren't offset
				if (dir==RIGHT||dir==LEFT){locy-=5;}
				else {locx-=5;}
			}
		}
		else if (pow.equals("small")){
			poweractive[2]=on;
			if (poweractive[2]){//depending on which direction your going, the location is changed so you aren't offset
				if (dir==RIGHT||dir==LEFT){locy+=1;}
				else {locx+=1;}
			}
			
		}
		else if (pow.equals("speed")){//moves faster
			poweractive[3]=on;
			if(poweractive[3]){
				speed=5;
			}
		}
		else if (pow.equals("clear")){//removes all the lines from the grid
			blocks.clear();
			
		}
		
	}
	public int getSpeed(){//returns the speed of the player
		return speed;
	}
	public void setLives(int x){//you can set how many lives the player has
		lives=x;
	}
	public int getLives(){//returning the amount of lives the player has
		return lives;
	}
	
}