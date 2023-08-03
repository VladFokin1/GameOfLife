/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gameoflife;
import com.mycompany.gameoflife.Cell.States;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.io.*;


/**
 *
 * @author fokin
 */
public class Board extends javax.swing.JFrame implements Configuration{

    Cell[][] grid;
    boolean play;
    Image offScrImg;
    Graphics offScrGraph;
    Timer time;
    TimerTask task;
    
    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        
        ImageIcon icon = new ImageIcon("pics/icon.png");
        
        setTitle("Game Of Life");
        setIconImage(icon.getImage());
        setBounds(0, 0, UI_WIDTH, UI_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        btn_play.setIcon(new ImageIcon("pics/play.png"));
        btn_nextGen.setIcon(new ImageIcon("pics/nextGen.png"));
        
        grid = new Cell[G_ROWS][G_COLS];
        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                grid[i][j] = new Cell();
            }
        }
        
        offScrImg = createImage(play_area.getWidth(), play_area.getHeight());
        offScrGraph = offScrImg.getGraphics();
        
        //update of generation
        time = new Timer();
        task  = new TimerTask() {
            @Override
            public void  run() {
                if(play) {
                   nextGen();
                }     
            }     
        };
        time.scheduleAtFixedRate(task, 0, PERIOD);
    }
    
    /**
     * Decides fate of cell on (i, j) position
     * @param i row of cell
     * @param j colomn of cell
     * @return a new State for this cell
     */
    private States fate(int i, int j) {
        int neighb = 0;
        if (j > 0) {
            if (grid[i][j-1].isAlive()) neighb++;
            if (i>0) if(grid[i-1][j-1].isAlive()) neighb++;
            if (i<G_ROWS-1) if(grid[i+1][j-1].isAlive()) neighb++;
        }
        if (j < G_COLS-1) {
            if (grid[i][j+1].isAlive()) neighb++;
            if (i>0) if(grid[i-1][j+1].isAlive()) neighb++;
            if (i<G_ROWS-1) if(grid[i+1][j+1].isAlive()) neighb++;
        }
        if (i>0) if(grid[i-1][j].isAlive()) neighb++;
        if (i<G_ROWS-1) if(grid[i+1][j].isAlive()) neighb++;
        if (neighb == 3) return States.ALIVE;
        if (neighb == 2 && grid[i][j].isAlive()) return States.ALIVE;
        return States.DEAD;
    }
    
    /**
     * Repaints a play area (need for filling alive cells)
     */
    private void myRepaint() {
        //repaint a background
        offScrGraph.setColor(play_area.getBackground());
        offScrGraph.fillRect(0, 0, play_area.getWidth(), play_area.getHeight());
        
        //fill with green color alive cell
        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                if (grid[i][j].isAlive()) {
                    offScrGraph.setColor(Color.GREEN);
                    int x = j*play_area.getWidth()/G_COLS;
                    int y = i*play_area.getHeight()/G_ROWS;
                    offScrGraph.fillRect(x, y, play_area.getWidth()/G_COLS+1, play_area.getHeight()/G_ROWS+1);
                }
            }
        }
        
        offScrGraph.setColor(new Color(40,40,40));
        for (int i = 1; i < G_ROWS; i++) {
            int y = i*play_area.getHeight()/G_ROWS;
            offScrGraph.drawLine(0, y, play_area.getWidth(), y);
        }
        for (int j = 1; j < G_COLS; j++) {
            int x = j*play_area.getWidth()/G_COLS;
            offScrGraph.drawLine(x, 0, x, play_area.getHeight());
        }
        
        play_area.getGraphics().drawImage(offScrImg, 0, 0, play_area);
    }
    
    /**
     * Moves to the next generation
     */
    private void nextGen() {
        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                grid[i][j].setNewState(fate(i,j));
            }
        }
        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                grid[i][j].updateState();
            }
        }
        myRepaint();
    }
    
    /**
     * Fills or clears cell under mouse
     * LMB fills cell
     * RMB clears cell
     * @param event Mouse event of object
     */
    private void underMouseAction(java.awt.event.MouseEvent event) {
        try {
            int j = G_COLS * event.getX() / play_area.getWidth();
            int i = G_ROWS * event.getY() / play_area.getHeight();
            if(SwingUtilities.isLeftMouseButton(event)){
                grid[i][j].setNewState(States.ALIVE);
            } else {
                grid[i][j].setNewState(States.DEAD);
            }
            grid[i][j].updateState();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        
        myRepaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        play_area = new javax.swing.JPanel();
        btn_play = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_nextGen = new javax.swing.JButton();
        slider_fill = new javax.swing.JSlider();
        btn_fill = new javax.swing.JButton();
        label_percent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        play_area.setBackground(new java.awt.Color(0, 0, 0));
        play_area.setForeground(new java.awt.Color(40, 40, 40));
        play_area.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                play_areaMouseDragged(evt);
            }
        });
        play_area.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                play_areaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout play_areaLayout = new javax.swing.GroupLayout(play_area);
        play_area.setLayout(play_areaLayout);
        play_areaLayout.setHorizontalGroup(
            play_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        play_areaLayout.setVerticalGroup(
            play_areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        btn_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_playActionPerformed(evt);
            }
        });

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_nextGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextGenActionPerformed(evt);
            }
        });

        slider_fill.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slider_fillStateChanged(evt);
            }
        });

        btn_fill.setText("Fill");
        btn_fill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fillActionPerformed(evt);
            }
        });

        label_percent.setText("50%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_nextGen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(btn_fill)
                .addGap(15, 15, 15)
                .addComponent(slider_fill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_percent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(btn_reset)
                .addContainerGap())
            .addComponent(play_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_reset)
                            .addComponent(label_percent)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_nextGen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(slider_fill, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btn_fill)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(play_area, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_playActionPerformed
        play = !play;
        if (play) {
            btn_play.setIcon(new ImageIcon("pics/pause.png"));
            btn_nextGen.setEnabled(false);
        }
        else {
            btn_play.setIcon(new ImageIcon("pics/play.png"));
            btn_nextGen.setEnabled(true);
        }
        myRepaint();
    }//GEN-LAST:event_btn_playActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                grid[i][j].setNewState(States.DEAD);
                grid[i][j].updateState();
            }
        }
        myRepaint();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void play_areaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_play_areaMouseDragged
        underMouseAction(evt);
    }//GEN-LAST:event_play_areaMouseDragged

    private void btn_nextGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextGenActionPerformed
        nextGen();
    }//GEN-LAST:event_btn_nextGenActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("SAVE.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Save saving = new Save(grid);
            oos.writeObject(saving);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        FileInputStream fis;
        try {
            fis = new FileInputStream("SAVE.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Save opening = (Save)ois.readObject();
            grid = opening.saved_grid;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }//GEN-LAST:event_formWindowOpened

    private void slider_fillStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slider_fillStateChanged
        label_percent.setText("" + slider_fill.getValue()+"%");
    }//GEN-LAST:event_slider_fillStateChanged

    private void btn_fillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fillActionPerformed
        float percent = (float)slider_fill.getValue()/100;

        for (int i = 0; i < G_ROWS; i++) {
            for (int j = 0; j < G_COLS; j++) {
                double rand = Math.random();
                if (rand <= percent) {
                    grid[i][j].setNewState(States.ALIVE);
                }
                grid[i][j].updateState();
            }
        }
        myRepaint();
        
    }//GEN-LAST:event_btn_fillActionPerformed

    private void play_areaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_play_areaMousePressed
        underMouseAction(evt);
    }//GEN-LAST:event_play_areaMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_fill;
    private javax.swing.JButton btn_nextGen;
    private javax.swing.JButton btn_play;
    private javax.swing.JButton btn_reset;
    private javax.swing.JLabel label_percent;
    private javax.swing.JPanel play_area;
    private javax.swing.JSlider slider_fill;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        Board life = new Board();
    }
}
