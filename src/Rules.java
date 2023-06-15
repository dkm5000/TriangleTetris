import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rules here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rules extends Actor
{
    private int labelWidth = 460;
    private int labelHeight = 250;
    
    private int xTitle = 30;
    private int yTitle = 50;

    private int xRules = 10;
    private int yRules = 115;
    
    private int fontTitle = 40;
    private int font = 25;
    
    public Rules()
    {
        setImage(new GreenfootImage(labelWidth, labelHeight));
        getImage().drawRect(0, 0, labelWidth - 1, labelHeight - 1);
        
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, fontTitle));
        getImage().drawString("Triangle Tetris", xTitle, yTitle);
        
        getImage().setFont(new Font("Comic Sans MS Bold", false, false, font));
        getImage().drawString("'left'          move left", xRules, yRules);
        getImage().drawString("'right'         move right", xRules, yRules + font);
        getImage().drawString("'up'            rotate clockwise", xRules, yRules + 2 * font);
        getImage().drawString("'down'         rotate anticlockwise", xRules, yRules + 3 * font);
        getImage().drawString("'space'        drop", xRules, yRules + 4 * font);
    }
    
    /**
     * Act - do whatever the Rules wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
