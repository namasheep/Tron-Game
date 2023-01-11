/*
 *PowerUPs.java
 *Will Jarvis-Cross and Namashi Sivaram
 *This class manages the placement of powerups on the tron map. It keeps track
 *of which power up is placed, placement of powerups and the removeval of powerups on the board.
*/
import java.util.*;
public class PowerUPs{
	//used to tell which power up is placed
	private boolean star=false;
	private boolean small=false;
	private boolean large=false;
	private boolean clear=false;
	private boolean speed=false;
	private Random rand=new Random();//initialize random
	private int xpos,ypos;//x and y postition of powerup on board
	int starcounter=0;
  	public PowerUPs(){//constructor

  	}
  	//method that takes in a power up and a grid and places the powerup on the grid
  	public int[][] placepowerup(String type,int[][] grid){
  		xpos=rand.nextInt(799-60)+30;//gets a random xpos within the grids length
  		ypos=rand.nextInt(599-60)+30;//gets a random ypos in the grids height
  		//uses the random x and y pos for the top left corner than
  		//adds a certain amount to each pos in a 30x30 square from the corner
  		//adds an amount in order to still recognize spaces with lines in them and 
  		//avoid player from getting powerups where lines are.
  		//then makes the specified powerups flag true
  		if (type.equals("star")){
  			for (int i=0;i<30;i++){
  				for (int j=0;j<30;j++){
  					grid[xpos+i][ypos+j]+=2;
  				}
  			}
  			star=true;
  		}
  		if (type.equals("large")){
  			for (int i=0;i<30;i++){
  				for (int j=0;j<30;j++){
  					grid[xpos+i][ypos+j]+=4;
  				}
  			}
  			large=true;
  		}
  		if (type.equals("small")){
  			for (int i=0;i<30;i++){
  				for (int j=0;j<30;j++){
  					grid[xpos+i][ypos+j]+=6;
  				}
  			}
  			small=true;
  		}
  		if (type.equals("clear")){
  			for (int i=0;i<30;i++){
  				for (int j=0;j<30;j++){
  					grid[xpos+i][ypos+j]+=8;
  				}
  			}
  			clear=true;
  		}
  		if (type.equals("speed")){
  			for (int i=0;i<30;i++){
  				for (int j=0;j<30;j++){
  					grid[xpos+i][ypos+j]+=10;
  				}
  			}
  			speed=true;
  		}
  		return grid;//returns modified grid

  	}
  	//removes the powerup from the taken in grid then returns it
  	public int[][] removepowers(int[][] grid){
  		//starts from the stored topleft coordinates then
  		//subtracts a certain amount depending on the 
  		//previously active powerup, undoing it.
  		for (int i=0;i<30;i++){
  			for (int j=0;j<30;j++){
  				if (star){
  					grid[xpos+i][ypos+j]-=2;
  				}
  				if (large){
  					grid[xpos+i][ypos+j]-=4;
  				}
  				if (small){
  					grid[xpos+i][ypos+j]-=6;
  				}
  				if (clear){
  					grid[xpos+i][ypos+j]-=8;
  				}
  				if (speed){
  					grid[xpos+i][ypos+j]-=10;
  				}
  			}
  		}
  		return grid;//returns modified grid
  	}
  	public void clearPowers(){//resets all powerups to a "not placed" status
  		star=false;
  		large=false;
  		small=false;
  		clear=false;
  		speed=false;
  	}
  	public boolean placed(){// returns true if any powerups is currently placed
  		if (star||small||large||clear||speed){
  			return true;
  		}
  		return false;
  	}
  	//returns the x and y positions of the placed powerup
  	public int getX(){
  		return xpos;
  	}
  	public int getY(){
  		return ypos;
  	}
  	//returns the status of placed (true) or not placed (false) of the specified power up
  	public boolean starPlaced(){
  		return star;
  	}
  	public boolean largePlaced(){
  		return large;
  	}
  	public boolean smallPlaced(){
  		return small;
  	}
  	public boolean clearPlaced(){
  		return clear;
  	}
  	public boolean speedPlaced(){
  		return speed;
  	}
}