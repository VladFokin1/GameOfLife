/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameoflife;

import java.io.Serializable;


/**
 *
 * @author fokin
 */
public class Cell implements Serializable{
    
    public enum States{DEAD, ALIVE};
    
    private States currentState;
    private States newState;
    
    public Cell() {
        this.currentState = States.DEAD;
    }
    
    /**
     * Sets new state (DEAD or ALIVE) for this cell
     * @param newState is a new state for this cell
     */
    public void setNewState(States newState) {
        this.newState = newState;
    }
    
    /**
     * Confirms new state for this cell
     */
    public void updateState() {
        currentState  = newState;
    }
    
    /**
     * 
     * @return current state of cell
     */
    public States getState() {
        return currentState;
    }
    
    /**
     * Check if cell is ALIVE
     * @return true if cell ALIVE and false if cell DEAD
     */
    public boolean isAlive() {
        return currentState != States.DEAD;
    }
    
}
