import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));
        
        // Initialize buttons
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if (checkLine(buttons[i][0].getText(), buttons[i][1].getText(), buttons[i][2].getText()) ||
                checkLine(buttons[0][i].getText(), buttons[1][i].getText(), buttons[2][i].getText())) {
                return true;
            }
        }
        
        // Check diagonals
        if (checkLine(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) ||
            checkLine(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean checkLine(String s1, String s2, String s3) {
        return !s1.equals("") && s1.equals(s2) && s2.equals(s3);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}

