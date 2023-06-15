import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grid extends Actor
{
    protected int xMin = 0;
    protected int width = 280;
    protected int yMin = 0;
    protected int height = 560;
    
    public  int xGridCenter = 300;
    public int yGridCenter = 350;
    public int gridBottom = yGridCenter + height / 2;
    
    protected int colNum = 8;
    protected int rowNum = 16;
    
    protected int rowHeight = height / rowNum;
    protected int colWidth = width / colNum;
    
    private int reachedRow = 16;
    
    //GreenfootImage playFieldImage;
    
    protected int[][] playField = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    
    public Grid()
    {
        xGridCenter = 300;
        yGridCenter = 350;
        gridBottom = yGridCenter + height / 2;
    }
    
    public Grid(int differ) 
    {
        GreenfootImage gridImage = new GreenfootImage(width, height);
        GreenfootImage playFieldImage = new GreenfootImage(width, height);
        
        gridImage.setColor(new Color(0, 0, 0));
        gridImage.fill();
        
        gridImage.setColor(new Color(255, 255, 255));
        
        for (int y = 0; y < height; y += rowHeight)
        {
            gridImage.drawLine(0, y, width - 1, y);
        }
        
        for (int x = 0; x < width; x += colWidth) 
        {
            gridImage.drawLine(x, 0, x, height - 1);
        }
        
        setImage(gridImage);
    }
    
    public boolean checkPlayField()
    {
        for (int i = 0; i < playField[1].length; i++)
        {
            if (playField[1][i] != 0)
            {
                return false;
            }
        }
        return true;
    }
    
    public Vector getFullRows()
    {
        Vector fullRows = new Vector(0);
        boolean exist = true;
        
        for (int i = rowNum; i >= reachedRow; i--)
        {
            exist = true;
            
            for (int j = 2; j < playField[i].length - 2; j++)
            {
                if (playField[i][j] == 0)
                {
                    exist = false;
                }
            }
            
            if(exist)
            {
                fullRows.addElement(i);
            }
        }
        
        return fullRows;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getRowHeight()
    {
        return rowHeight;
    }
    
    public int getColWidth()
    {
        return colWidth;
    }
    
    public int[][] getPlayField()
    {
        return playField;
    }
    
    public void setPlayField(int[][] newPF)
    {
        playField = newPF;
        checkReachedRow();
    }
    
    public void checkReachedRow()
    {
        boolean found = false;
        
        for (int i = rowNum; i > 0 && !found; i--)
        {
            for (int j = 0; j < playField[i].length && !found; j++)
            {
                if (i < reachedRow && playField[i][j] != 0)
                {
                    reachedRow = i;
                    found = true;
                }
            }
        }
    }
    
    /*public void drawTriangle(int type, int xInGrid, int yInGrid)
    {
        Triangle myTriangle = new Triangle();
    }*/
    
    public void deletePlayFieldRow(int row)
    {
        for (int i = row - 1; i >= 0; i--)
        {
            for (int j = 0; j < playField[i].length; j++)
            {
                playField[i + 1][j] = playField[i][j];
            }
        }
    }
    
    protected int toGridX(int xInWorld)
    {
        return xInWorld - xGridCenter + width / 2;
    }
    
    protected int toGridY(int yInWorld)
    {
        return yInWorld - yGridCenter + height / 2; //wrong xGC
    }
    
    protected int toWorldY(int yInGrid)
    {
        return yInGrid + yGridCenter - height / 2;
    }

    protected int toWorldX(int xInGrid)
    {
        return xInGrid + xGridCenter - width / 2;
    }
    
    protected int toPlayFieldCol(int xInGrid)
    {
        return 2 * (xInGrid - colWidth) / colWidth + 2;
    }
    
    protected int toPlayFieldRow(int yInGrid)
    {
        return (yInGrid - rowHeight) / rowHeight + 2; //??? +1
    }
    
    /**
     * Act - do whatever the Grid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
