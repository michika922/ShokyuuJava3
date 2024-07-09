package meiro;


	import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
	
	public class MyFrame extends JFrame {
	   private Maze maze;
	   public MyFrame() {
	       setTitle("Maze Generator");
	       setSize(800, 800);
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       setLocationRelativeTo(null);
	       maze = new Maze(15, 15);
	       add(new MazePanel());
	       addKeyListener(new KeyAdapter() {
	           @Override
	           public void keyPressed(KeyEvent e) {
	               if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	                   maze.generateMaze();
	                   repaint();
	               }
	           }
	       });
	       setVisible(true);
	   }
	   private class MazePanel extends JPanel {
	       @Override
	       protected void paintComponent(Graphics g) {
	           super.paintComponent(g);
	           int cellSize = Math.min(getWidth() / maze.getWidth(), getHeight() / maze.getHeight());
	           for (int y = 0; y < maze.getHeight(); y++) {
	               for (int x = 0; x < maze.getWidth(); x++) {
	                   switch (maze.getCell(x, y)) {
	                       case Maze.START:
	                           g.setColor(Color.BLUE);
	                           break;
	                       case Maze.GOAL:
	                           g.setColor(Color.RED);
	                           break;
	                       case Maze.WALL:
	                           g.setColor(Color.BLACK);
	                           break;
	                       case Maze.PATH:
	                           g.setColor(Color.WHITE);
	                           break;
	                   }
	                   g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
	               }
	           }
	       }
	   }
	   public static void main(String[] args) {
	       new MyFrame();
	   }
	} 
	 

