import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Actor
{
    private int currentScore = 0;
    private int currentLines = 0;
    private int currentLevel = 0;
    
    private int xLabel = 250;
    private int yLabel = 200;
    private int scoreFont = 35;
    private int font = 25;
    
    private int xScore = 0;
    private int yScore = 35;
    
    private int xLines = 10;
    private int yLines = 95;
    
    private int xLevel = 10;
    private int yLevel = 135;
    
    private int[] linesBeforeUpgrade = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 100, 100, 100, 100, 100, 100, 
                                        110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 200, 200, 200};
    private int[] pointsPerLines = {100, 300, 500};
    private int countLevelLines = 0;
    
    public Score() 
    {
        setImage(new GreenfootImage(xLabel, yLabel));
        
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, scoreFont));
        getImage().drawString("Score: " + Integer.toString(currentScore), xScore, yScore);
        
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("Lines: " + Integer.toString(currentLines), xLines, yLines);
        getImage().drawString("Level: " + Integer.toString(currentLevel), xLevel, yLevel);
    }
    
    public void setScoreLabel(int addedRows)
    {
        getImage().clear();
        setScore(pointsPerLines[addedRows - 1] * (currentLevel + 1));
        setLines(addedRows);
        
        countLevelLines += addedRows;
        if (countLevelLines == linesBeforeUpgrade[currentLevel])
        {
            setLevel(1);
            countLevelLines = 0;
        }
    }
    
    public void setScore(int addToScore) 
    {
        currentScore += addToScore;
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, scoreFont));
        getImage().drawString("Score: " + Integer.toString(currentScore), xScore, yScore);
    }
    
    public void setLevel(int addToLevel) 
    {
        currentLevel += addToLevel;
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("Level: " + Integer.toString(currentLevel), xLevel, yLevel);
    }
    
    public void setLines(int addToLines) 
    {
        currentLines += addToLines;
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("Level: " + Integer.toString(currentLines), xLines, yLines);
    }
    
    public int getLevel()
    {
        return currentLevel;
    }
    
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
}
