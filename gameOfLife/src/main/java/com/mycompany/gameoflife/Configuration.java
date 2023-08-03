/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameoflife;

/**
 *
 * @author fokin
 */
public interface Configuration {
    final int UI_WIDTH = 1200;// start width of UI
    final int UI_HEIGHT = 750;// start height of UI
    
    int G_ROWS = 97;//number of rows in grid
    int G_COLS = 170;//number of columns in grid
    
    int PERIOD = 125;//period of generetion update
}
