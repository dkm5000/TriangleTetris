import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Reset here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Reset extends Actor
{
    private int labelWidth = 120;
    private int labelHeight = 50;
    
    private int xText = 20;
    private int yText = 30;
    
    private int font = 30;
    
    public Reset()
    {
        setImage(new GreenfootImage("Button.jpg"));
        
        getImage().scale(labelWidth, labelHeight);
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("Reset", xText, yText);
    }
    /**
     * Act - do whatever the Reset wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
