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
public class Save implements Serializable{
    
    Cell[][] saved_grid;//Cell massive that will be saved
    
    public Save(Cell[][] for_save) {
        saved_grid = for_save;
    }
}

