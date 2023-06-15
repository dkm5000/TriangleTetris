import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class Triomino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Triomino extends Grid
{
    private int max = 6;
    private int min = 0;
    private int type;
    private int xInGrid;
    private int yInGrid;
    private int xImg = 0;
    private int yImg = 0;
    private int rotation0 = 0;
    private int endRotation = 0;
    
    private int level = 0;
    private int lastLevel = 14;
    
    private double countPix = 0; // unused?
    private int countFrames = 0;
    private int xStop = 0;
    private int yStop = 0;
    private int lostLives = 0;
    
    private boolean moving = true;
    
    private boolean leftDown = false;
    private boolean rightDown = false;
    private boolean upDown = false;
    private boolean downDown = false;
    private boolean spaceDown = false;
    
    private boolean emptyBottom = false;
    private boolean emptyTop = false;
    private boolean emptyLeft = false;
    private boolean emptyRight = false;
    
    private boolean firstFrame = true;
    private boolean locked = false;
    
    protected int[][] playField = {
        {0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0}
    };

    private int[][][] triominoes = { 
        {{2, 0, 0, 0, 1, 4, 0, 0}, {2, 3, 1, 0, 0, 0, 0, 0}, {0, 0, 1, 4, 0, 0, 0, 3}, {0, 0, 0, 0, 0, 4, 2, 3}}, 
        {{2, 3, 0, 0, 1, 0, 0, 0}, {0, 3, 1, 4, 0, 0, 0, 0}, {0, 0, 0, 4, 0, 0, 2, 3}, {0, 0, 0, 0, 1, 4, 2, 0}},
        {{0, 0, 2, 0, 0, 4, 1, 0}, {2, 0, 0, 0, 0, 3, 1, 0}, {0, 4, 1, 0, 0, 3, 0, 0}, {0, 4, 2, 0, 0, 0 ,0, 3}},
        {{0, 3, 2, 0, 0, 0, 1, 0}, {0, 0, 0, 4, 0, 3, 1, 0}, {0, 4, 0, 0, 0, 3, 2, 0}, {0, 4, 2, 0, 1, 0, 0, 0}},
        {{0, 4, 2, 0, 0, 0, 1, 0}, {0, 0, 2, 0, 0, 3, 1, 0}, {0, 4, 0, 0, 0, 3, 1, 0}, {0, 4, 2, 0, 0, 3, 0, 0}},
        {{0, 4, 1, 0, 1, 0, 0, 0}, {0, 3, 2, 0, 0, 0, 0, 3}, {0, 0, 0, 4, 0, 4, 1, 0}, {2, 0, 0, 0, 0, 3, 2, 0}},
        {{1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 3, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 4}, {0, 0, 0, 0, 2, 0, 0, 0}}
    };
    
    private int[] endTriomino = {0, 0, 0, 0, 0, 0, 0, 0};
    
    private Color[] colors = {
        new Color(115, 194, 251),
        new Color(164, 130, 255),
        new Color(252, 252, 92),
        new Color(159, 226, 191),
        new Color(255, 177, 98),
        new Color(255, 182, 193),
        new Color(64, 224, 208)
    };
    
    private int[] framesProGridcell = {
        48,                                                 // level 00
        43,
        38,
        33,
        28,
        23,
        18,
        13,
        8,
        6,
        5,
        4,
        3,
        2,
        1                                                   // level 29+
    };
    
    private Triangle[] triangles = {null, null, null};
    
    public Triomino()
    {
        
    }
    
    public Triomino(int w, int h, int[][] currentPF, int currentLevel)
    {
        colWidth = w;
        rowHeight = h;
        playField = currentPF;
        level = currentLevel;
        
        type = (int)Math.floor(Math.random() * (max - min + 1) + min);
        xInGrid = (int)Math.floor(Math.random() * (colNum - 1)) * colWidth + colWidth; // a random column in pixel
        yInGrid = rowHeight;
        endTriomino = triominoes[type][endRotation];
        
        setImage(new GreenfootImage(2 * colWidth, 2 * rowHeight));
        setImage(new GreenfootImage(2 * colWidth, 2 * rowHeight));
        drawTriomino();
    } 
    
    public void drawTriomino()
    {
        triangles[0] = new Triangle();
        triangles[1] = new Triangle();
        triangles[2] = new Triangle();
        xImg = 0;
        yImg = 0;
        
        int trNum = 0; // the current number of recognized triangles of the triomino
            
            for (int i = 0; i < 8; i++)
            {
                if (i % 4 == 1)
                {
                    xImg += colWidth - 1;
                }
                else if (i % 4 == 2)
                {
                    xImg++;
                }
                else if (i % 4 == 3)
                {
                    xImg += colWidth - 1;
                }
                
                if (i == 4)
                {
                    yImg += rowHeight;
                    xImg = 0;
                }
                
                if (endTriomino[i] != 0 && endTriomino[i] % 2 == 0)
                {
                    triangles[trNum] = new Triangle(endTriomino[i], xImg, yImg + rowHeight, colWidth, rowHeight);
                    trNum++;
                }
                else if (endTriomino[i] != 0)
                {
                    triangles[trNum] = new Triangle(endTriomino[i], xImg, yImg, colWidth, rowHeight);
                    trNum++;
                }
            }
    
        getImage().clear();
        
        for (int i = 0; i < triangles.length; i++)
        {
            if (triangles[i].getExist())
            {
                int[] xVertices = {triangles[i].getVertices(0, 0), triangles[i].getVertices(1, 0), triangles[i].getVertices(2, 0)};
                int[] yVertices = {triangles[i].getVertices(0, 1), triangles[i].getVertices(1, 1), triangles[i].getVertices(2, 1)};
                
                getImage().setColor(colors[type]);
                getImage().fillPolygon(xVertices, yVertices, 3);
                getImage().setColor(new Color(255, 255, 255));
                getImage().drawPolygon(xVertices, yVertices, 3); // draw a triangle
            }
        }
    }
    
    public int getXInGrid()
    {
        return xInGrid;
    }
    
    public int getYInGrid()
    {
        return yInGrid;
    }
    
    public void setYInGrid(int newYInGrid)
    {
        yInGrid = newYInGrid;
    }
    
    public boolean getMoving()
    {
        return moving;
    }
    
    public int getLostLives()
    {
        return lostLives;
    }
    
    public int[][] getPlayField()
    {
        return playField;
    }
    
    public void setEndTriomino()
    {
        endTriomino = triominoes[type][getRotation() / 90];
        endRotation = getRotation() / 90;
    }
    
    public int[] getEndTriomino()
    {
        return endTriomino;
    }
    
    public void setEndTriominoElement(int index, int value)
    {
        endTriomino[index] = value;
    }
    
    public boolean getEmptyTop()
    {
        return emptyTop;
    }
    
    public void moveRowDown()
    {
        setLocation(getX(), getY() + rowHeight);
        yInGrid += rowHeight;
    }
    
    public boolean getExist()
    {
        for (int i = 0; i < endTriomino.length; i++)
        {
            if (endTriomino[i] != 0)
            {
                return true;
            }
        }
        return false;
    }
    
    public void checkEmptySide(String side)
    {
        if (side == "bottom")
        {
            emptyBottom = false;
            if (triominoes[type][getRotation() / 90][4] + triominoes[type][getRotation() / 90][5] 
                + triominoes[type][getRotation() / 90][6] + triominoes[type][getRotation() / 90][7] == 0)
            {
                emptyBottom = true;
            }
        }
        if (side == "top")
        {
            emptyTop = false;
            if (triominoes[type][getRotation() / 90][0] + triominoes[type][getRotation() / 90][1] 
                + triominoes[type][getRotation() / 90][2] + triominoes[type][getRotation() / 90][3] == 0)
            {
                emptyTop = true;
            }
        }
        if (side == "left")
        {
            emptyLeft = false;
            if (triominoes[type][getRotation() / 90][0] + triominoes[type][getRotation() / 90][1] 
                + triominoes[type][getRotation() / 90][4] + triominoes[type][getRotation() / 90][5] == 0)
            {
                emptyLeft = true;
            }
        }
        if (side == "right")
        {
            emptyRight = false;
            if (triominoes[type][getRotation() / 90][2] + triominoes[type][getRotation() / 90][3] 
                + triominoes[type][getRotation() / 90][6] + triominoes[type][getRotation() / 90][7] == 0)
            {
                emptyRight = true;
            }
        }
    }
    
    public void stopPositionUpdate()
    {
        int rowsToCheck = rowNum + 1;
     
        if (emptyBottom)
        {
            rowsToCheck++;
        }
        
        
        for (int i = toPlayFieldRow(yInGrid); i < rowsToCheck; i++)   
        {
            if (checkPosition(toPlayFieldCol(xInGrid), i, getRotation()))
            {
                xStop = getX();
                yStop = toWorldY((i - 1) * rowHeight); //-1
            }
        }
    }
    
    public boolean checkPosition(int newColPlayField, int newRowPlayFieldCenter, int newRotation)
    {
        int newRotationType = newRotation / 90;
        int startingCol = newColPlayField;
        int newRowPlayField = newRowPlayFieldCenter - 1; // 1
        
        for (int j = 0; j < triominoes[type][newRotationType].length; j++)
        {
            if (triominoes[type][newRotationType][j] != 0 &&
                (playField[newRowPlayField][newColPlayField] != 0 ||
                (newColPlayField % 2 == 0 && playField[newRowPlayField][newColPlayField + 1] != 0 && 
                    triominoes[type][newRotationType][j] + playField[newRowPlayField][newColPlayField + 1] != 5) ||
                (newColPlayField % 2 == 1 && playField[newRowPlayField][newColPlayField - 1] != 0 && 
                    triominoes[type][newRotationType][j] + playField[newRowPlayField][newColPlayField - 1] != 5)))
            {
                return false;
            }
            
            newColPlayField++;
            if (j == 3)
            {
                newColPlayField = startingCol;
                newRowPlayField++;
            }
        }
        return true;
    }
    
    public boolean stop()
    {
        if (getY() >= yStop)
        {
            return true;
        }
        return false;
    }
    
    private void playFieldUpdate()
    {
        int colPlayField = toPlayFieldCol(toGridX(getX()));
        int startingCol = colPlayField;
        int rowPlayField = toPlayFieldRow(toGridY(getY())) - 1; //1
        int rotationType = getRotation() / 90;
        
        for (int j = 0; j < triominoes[type][rotationType].length; j++)
        {
            if (triominoes[type][rotationType][j] != 0)
            {
                playField[rowPlayField][colPlayField] = triominoes[type][rotationType][j];
            }
            
            colPlayField++;
            if(j == 3)
            {
                colPlayField = startingCol;
                rowPlayField++;
            }
        }
    }
    
    public void checkBounds(String direction)
    {
        if (direction == "left" && (emptyLeft && xInGrid == 0 || !emptyLeft && xInGrid == colWidth))
        {
            if (emptyRight)
            {
                xInGrid = width + colWidth;
            }
            else
            {
                xInGrid = width;
            }
            
            lostLives++;
        }
        else if (direction == "right" && (emptyRight && xInGrid == width || !emptyRight && xInGrid == width - colWidth))
        {
            if (emptyLeft)
            {
                xInGrid = - colWidth;
            }
            else
            {
                xInGrid = 0;
            }
            
            lostLives++;
        }
    }
    
    public void move()
    {
        countFrames++;
        if (countFrames >= framesProGridcell[level])
        {
            setLocation(getX(), getY() + rowHeight);
            yInGrid += rowHeight;
            countFrames = 0;
        }
    }

    public void react()
    {
        if (leftDown != Greenfoot.isKeyDown("left"))
        {
            leftDown = !leftDown;
            if (leftDown)
            {
                checkBounds("left");
                if (checkPosition(toPlayFieldCol(xInGrid - colWidth),
                                          toPlayFieldRow(yInGrid),
                                          getRotation()))
                {
                    xInGrid -= colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    stopPositionUpdate();
                }
            }
        }
        if (rightDown != Greenfoot.isKeyDown("right"))
        {
            rightDown = !rightDown;
            if (rightDown)
            {
                checkBounds("right");
                if (checkPosition(toPlayFieldCol(xInGrid + colWidth),
                                          toPlayFieldRow(yInGrid),
                                          getRotation()))
                {
                    xInGrid += colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    stopPositionUpdate();
                }
            }
        }
        if (upDown != Greenfoot.isKeyDown("up"))
        {
            upDown = !upDown;
            if (upDown)
            {
                if (emptyLeft && xInGrid == 0 &&
                    checkPosition(toPlayFieldCol(toGridX(getX()) + colWidth),
                                          toPlayFieldRow(toGridY(getY())),
                                          (360 + (getRotation() - 90) % 360) % 360))
                {
                    xInGrid += colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    
                    setRotation((360 + (getRotation() - 90) % 360) % 360);
                    
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
                else if (emptyRight && xInGrid == width &&
                    checkPosition(toPlayFieldCol(toGridX(getX()) - colWidth),
                                          toPlayFieldRow(toGridY(getY())),
                                          (360 + (getRotation() - 90) % 360) % 360))
                {
                    xInGrid -= colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    
                    setRotation((360 + (getRotation() - 90) % 360) % 360);
                    
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
                else if (checkPosition(toPlayFieldCol(toGridX(getX())),
                                          toPlayFieldRow(toGridY(getY())),
                                          (360 + (getRotation() - 90) % 360) % 360))
                {
                    setRotation((360 + (getRotation() - 90) % 360) % 360);
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
            }
        }
        if (downDown != Greenfoot.isKeyDown("down")) 
        {
            downDown = !downDown;
            if (downDown)
            {
                if (emptyLeft && xInGrid == 0 &&
                    checkPosition(toPlayFieldCol(toGridX(getX()) + colWidth),
                                          toPlayFieldRow(toGridY(getY())),
                                          (getRotation() + 90)% 360))
                {
                    xInGrid += colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    
                    setRotation((getRotation() + 90)% 360);
                    
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
                else if (emptyRight && xInGrid == width &&
                    checkPosition(toPlayFieldCol(toGridX(getX()) - colWidth),
                                          toPlayFieldRow(toGridY(getY())),
                                          (getRotation() + 90)% 360))
                {
                    xInGrid -= colWidth;
                    setLocation(toWorldX(xInGrid), getY());
                    
                    setRotation((getRotation() + 90)% 360);
                    
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
                else if (checkPosition(toPlayFieldCol(toGridX(getX())),
                                          toPlayFieldRow(toGridY(getY())),
                                          (getRotation() + 90)% 360))
                {
                    setRotation((getRotation() + 90)% 360);
                    checkEmptySide("bottom");
                    checkEmptySide("left");
                    checkEmptySide("right");
                    stopPositionUpdate();
                }
            }
        }
        if (spaceDown != Greenfoot.isKeyDown("space"))
        {
            spaceDown = !spaceDown;
            if (spaceDown)
            {
                level = lastLevel; // adjust speed
            }
        }
    }
    
    public void act()
    {
        if (firstFrame)
        {
            countFrames = 0; // check if nesseccary
            checkEmptySide("bottom");
            checkEmptySide("left");
            checkEmptySide("right");
            stopPositionUpdate();
            firstFrame = false;
        }
        if (((getY() < gridBottom - getImage().getHeight() / 2) || 
            (emptyBottom && getY() < gridBottom)) &&
            !stop())
        {
            react();
            move();
        }
        else if (moving)
        {
            if ((yStop < gridBottom - getImage().getHeight() / 2) || (emptyBottom && yStop < gridBottom))
            {
                setLocation(xStop, yStop);
                xInGrid = toGridX(xStop);
                yInGrid = toGridY(yStop);
            }
            Greenfoot.playSound("Falling.wav");
            moving = false;
            playFieldUpdate();  // update it in the grid later!!!
            setEndTriomino();
        }
    }
}
