/*
 *Block.java
 *Will Jarvis-Cross and Namashi Sivaram
 *an object that stores its own top left corner coordinates
 *and the blocks size for redrawing everyframe
*/
import java.util.*;
public class Block {
	private int blockx,blocky,size;// x coordinate, y coordinate , size
    public Block(int x,int y,int s) {//constructor
    	blockx=x;
    	blocky=y;
    	size=s;
    }
    
    /**
     * @param args the command line arguments
     */
    public int getBlockx(){//returns the x coordinate (top left)
    	return blockx;
    }
    public int getBlocky(){//returns the y coordinate (top left)
    	return blocky;
    }
    public int getSize(){//returns size(length and width as its a square)
    	return size;
    }
    	
    public boolean collidepoint(int xc,int yc){//returns true if a point is inside the square
    	if (blockx<=xc&&xc<=blockx+size&&blocky<=yc&&yc<=blocky+size){
    		return true;
    	}
    	return false;
    }
}
