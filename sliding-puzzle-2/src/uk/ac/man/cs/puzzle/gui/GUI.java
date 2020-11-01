package uk.ac.man.cs.puzzle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.man.cs.puzzle.logic.Model;

public class GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Model puzzleModel;
	private GraphicsPanel puzzleGraphics;
	private int ROWS;
	private int COLS;
	static JLabel currentMovesLabel;

	public GUI(int rows, int cols) {
		// Create a button. Add a listener to it.
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new NewGameAction());

		// Create move counter components
		JLabel moveCounterLabel = new JLabel("Moves: ", JLabel.LEADING);
		currentMovesLabel = new JLabel("_", JLabel.CENTER);
		currentMovesLabel.setText(String.valueOf("0"));
		
		// Create control panel
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(newGameButton);

		// Create graphics panel
		ROWS = rows;
		COLS = cols;
		puzzleModel = new Model(ROWS, COLS);
		puzzleGraphics = new GraphicsPanel(puzzleModel, rows, cols);
		
		// Create move counter panel
		JPanel gameMoveCounterPanel = new JPanel();
		gameMoveCounterPanel.setLayout(new FlowLayout());
		gameMoveCounterPanel.add(moveCounterLabel);
		gameMoveCounterPanel.add(currentMovesLabel);

		// Set the layout and add the components
		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.NORTH);
		this.add(puzzleGraphics, BorderLayout.CENTER);
		this.add(gameMoveCounterPanel, BorderLayout.SOUTH);
	}

	Model getPuzzleModel() {
		return puzzleModel;
	}

	GraphicsPanel getGraphicsPanel() {
		return puzzleGraphics;
	}

	public class NewGameAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			puzzleModel.reset();
			puzzleModel.shuffle();
			puzzleGraphics.repaint();
			puzzleGraphics.setBackground(Color.black);
			currentMovesLabel.setText(String.valueOf("0"));
			
			
		}
	}
}
