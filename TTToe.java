package Lecture23;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTToe extends JFrame implements ActionListener {

	public static final int BOARD_SIZE = 3;

	public static enum GameStatus {
		Xwins, Zwins, Incompelete, Tie;
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];

	boolean crossTurn = true;

	TTToe() {
		super.setSize(800, 800);
		super.setTitle("Tic Tac Toe Game");
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				JButton button = new JButton("");
				buttons[i][j] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}

		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();
		makeMove(clickedButton);
		GameStatus gs = getGameStatus();
		if (gs == GameStatus.Incompelete) {
			return;
		}
		declareWinner(gs);

		int input = JOptionPane.showConfirmDialog(this, "Would you like to restart");
		if (input == JOptionPane.YES_OPTION) {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int k = 0; k < BOARD_SIZE; k++) {
					buttons[i][k].setText("");
				}
			}
			crossTurn = true;
		} else {
			super.dispose();
		}

	}

	private void makeMove(JButton clickedButton) {

		String buttonText = clickedButton.getText();
		if (buttonText.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Try!");
			return;
		}

		if (crossTurn) {
			clickedButton.setText("X");
		} else {
			clickedButton.setText("O");
		}
		crossTurn = !crossTurn;

	}

	private GameStatus getGameStatus() {
		int flagX = 0;
		int flagO = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			int ctrX = 0;
			int ctrO = 0;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (buttons[i][j].getText() == "X") {
					ctrX++;
				}
				if (buttons[i][j].getText() == "O") {
					ctrO++;
				}
			}
			if (ctrX == BOARD_SIZE) {
				flagX = 1;
			} else if (ctrO == BOARD_SIZE) {
				flagO = 1;
			}
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			int ctrX = 0;
			int ctrO = 0;
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (buttons[j][i].getText() == "X") {
					ctrX++;
				}
				if (buttons[j][i].getText() == "O") {
					ctrO++;
				}
			}
			if (ctrX == BOARD_SIZE) {
				flagX = 1;
			} else if (ctrO == BOARD_SIZE) {
				flagO = 1;
			}
		}
		int ctrX = 0;
		int ctrO = 0;
		int j = 0;

		for (int i = 0; i < BOARD_SIZE; i++) {
			if (buttons[i][j].getText() == "X") {
				ctrX++;
			}
			if (buttons[i][j].getText() == "O") {
				ctrO++;
			}
			if (ctrX == BOARD_SIZE) {
				flagX = 1;
			} else if (ctrO == BOARD_SIZE) {
				flagO = 1;
			}
			j++;

		}
		ctrX = 0;
		ctrO = 0;
		j = 0;
		for (int i = BOARD_SIZE - 1; i >= 0; i--) {
			if (buttons[i][j].getText() == "X") {
				ctrX++;
			}
			if (buttons[i][j].getText() == "O") {
				ctrO++;
			}
			if (ctrX == BOARD_SIZE) {
				flagX = 1;
			} else if (ctrO == BOARD_SIZE) {
				flagO = 1;
			}
			j++;
		}
		if (flagX == 1) {
			return GameStatus.Xwins;
		} else if (flagO == 1) {
			return GameStatus.Zwins;
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int k = 0; k < BOARD_SIZE; k++) {
				if (buttons[i][k].getText() == "") {
					return GameStatus.Incompelete;
				}
			}
		}
		return GameStatus.Tie;
	}

	private void declareWinner(GameStatus gs) {
		if (gs == GameStatus.Xwins) {
			JOptionPane.showMessageDialog(this, "X player won");
		} else if (gs == GameStatus.Zwins) {
			JOptionPane.showMessageDialog(this, "Z player won");
		} else {
			JOptionPane.showMessageDialog(this, "the game tied");
		}

	}

}
