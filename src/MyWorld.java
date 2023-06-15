import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public  int xGridCenter = 300;
    public int yGridCenter = 350;
    
    private int xScoreLabel = 650;
    private int yScoreLabel = 150;
    
    private int xRulesLabel = 730;
    private int yRulesLabel = 500;
    
    private int xGameOver = 750;
    private int yGameOver = 350;
    
    private int xStartButton = 70;
    private int yStartButton = 200;
    
    private int xResetButton = 70;
    private int yResetButton = 270;
    
    private boolean started = false;
    private boolean ended = false;
    
    public Vector fullRows = null;
    
    public Score myScore = new Score();
    public Rules myRules = new Rules();
    public Grid myGrid = new Grid(0);
    public Start myStartButton = new Start();
    public Reset myResetButton = new Reset();
    public Vector<Triomino> triominoesVec = new Vector<Triomino>(0);
    
    public MyWorld()
    {    
        // Create a new world with 1000x700 cells with a cell size of 1x1 pixels.
        super(1000, 700, 1);
        
        addObject(myScore, xScoreLabel, yScoreLabel); 
        addObject(myRules, xRulesLabel, yRulesLabel);
        addObject(myGrid, xGridCenter, yGridCenter);
        addObject(myStartButton, xStartButton, yStartButton);
        addObject(myResetButton, xResetButton, yResetButton);
    }
    
    public void deleteFullRows()
    {
        myScore.setScoreLabel(fullRows.size());
        
        for (int i = 0; i < fullRows.size(); i++)
        {
            int j = 0;
            
            Greenfoot.playSound("ClearLine.wav");
            
            while (j < triominoesVec.size())
            {
                if (myGrid.toPlayFieldRow(triominoesVec.elementAt(j).getYInGrid()) == (int)fullRows.elementAt(i) + 1)
                {
                    for (int k = 0; k < 4; k++) 
                    {
                        triominoesVec.elementAt(j).setEndTriominoElement(k, 0);
                        triominoesVec.elementAt(j).setRotation(0);
                        triominoesVec.elementAt(j).drawTriomino();
                    }
                }
                if (myGrid.toPlayFieldRow(triominoesVec.elementAt(j).getYInGrid()) == (int)fullRows.elementAt(i))
                {
                    for (int k = 4; k < 8; k++) 
                    {
                        triominoesVec.elementAt(j).setEndTriominoElement(k, 0);
                        triominoesVec.elementAt(j).setRotation(0);
                        triominoesVec.elementAt(j).drawTriomino();
                    }
                }
                
                if (!triominoesVec.elementAt(j).getExist())
                {
                    removeObject(triominoesVec.elementAt(j));
                    triominoesVec.removeElementAt(j);
                    j--;
                }
                 j++;
            }
            
            for (j = 0; j < triominoesVec.size(); j++)
            {
                if (myGrid.toPlayFieldRow(triominoesVec.elementAt(j).getYInGrid()) <= (int)fullRows.elementAt(i))
                {
                    triominoesVec.elementAt(j).moveRowDown();
                }
            }
            myGrid.deletePlayFieldRow((int)fullRows.elementAt(i));
        }
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(myStartButton) && !started)
        {
            started = true;
            
            triominoesVec.addElement(new Triomino(myGrid.getColWidth(), myGrid.getRowHeight(), myGrid.getPlayField(), myScore.getLevel()));
            addObject(triominoesVec.lastElement(), 
                    triominoesVec.lastElement().toWorldX(triominoesVec.lastElement().getXInGrid()),
                    triominoesVec.lastElement().toWorldY(triominoesVec.lastElement().getYInGrid()));
        }
        if (Greenfoot.mouseClicked(myResetButton))
        {
            Greenfoot.setWorld(new MyWorld());
        }
        if (started && !ended)
        {
            if (!triominoesVec.lastElement().getMoving())
            {
                myGrid.setPlayField(triominoesVec.lastElement().getPlayField());
                
                fullRows = myGrid.getFullRows();
                if (!fullRows.isEmpty())
                {
                    deleteFullRows();
                }
                
                if (myGrid.checkPlayField())
                {
                    triominoesVec.addElement(new Triomino(myGrid.getColWidth(), myGrid.getRowHeight(), myGrid.getPlayField(), myScore.getLevel()));
                    addObject(triominoesVec.lastElement(), 
                        triominoesVec.lastElement().toWorldX(triominoesVec.lastElement().getXInGrid()),
                        triominoesVec.lastElement().toWorldY(triominoesVec.lastElement().getYInGrid()));
                }
                else
                {
                    GameOver myGameOver = new GameOver();
                    addObject(myGameOver, xGameOver, yGameOver); 
                }
            }
        }
    }
}
