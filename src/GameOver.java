import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    private int labelWidth = 500;
    private int labelHeight = 300;
    
    private int xText = 0;
    private int yText = 150;
    
    private int font = 70;
    
    public GameOver()
    {
        setImage(new GreenfootImage(labelWidth, labelHeight));
        
        getImage().setColor(new Color(106, 13, 173));
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("GAME OVER", xText, yText);
    }
    /**
     * Act - do whatever the GameOver wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
