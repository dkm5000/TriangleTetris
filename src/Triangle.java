import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Triangle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Triangle extends Triomino
{
    private int type = 0;
    private int xRightAngle = 0;
    private int yRightAngle = 0;
    private int[][] vertices = { {0, 0}, {0, 0}, {0, 0} };
    private boolean exist = false;
    
    public Triangle()
    {

    }
    
    public Triangle(int t, int x, int y, int w, int h)
    {
        exist = true;
        type = t;
        xRightAngle = x;
        yRightAngle = y;
        colWidth = w;
        rowHeight = h;
        setVertices();
    }
        
    public void setVertices()
    {
        int x0 = 0;
        int y0 = 0;
            
        if (type == 1)
        {
            x0 = 0;
            y0 = 0;
        }
        else if (type == 2)
        {
            x0 = 0;
            y0 = rowHeight - 1;
        }
        else if (type == 3)
        {
            x0 = colWidth - 1;
            y0 = 0;
        }
        else
        {
            x0 = colWidth - 1;
            y0 = rowHeight - 1;
        }
        
        vertices[0][0] = xRightAngle;
        vertices[0][1] = yRightAngle;
        vertices[1][0] = xRightAngle + (x0 + colWidth - 1) % (2 * colWidth - 2) - x0;
        vertices[1][1] = yRightAngle;
        vertices[2][0] = xRightAngle;
        vertices[2][1] = yRightAngle + (y0 + rowHeight - 1) % (2 * rowHeight - 2) - y0;
    }
    
    public int getVertices(int num, int coord)
    {
        return vertices[num][coord];
    }
    
    public boolean getExist()
    {
        return exist;
    }
    
    /**
     * Act - do whatever the Triangle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}

