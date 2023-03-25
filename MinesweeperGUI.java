import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MinesweeperGUI {
    private static final int SIZE = 10;
    private static final int MINES = 10;
    private static final int SQUARE_SIZE = 40;

    private JButton[][] grid;
    private JFrame frame;
    private JLabel statusLabel;
    private int[][] board;
    private boolean[][] revealed;
    private int numMinesRemaining;

    public MinesweeperGUI() {
        grid = new JButton[SIZE][SIZE];
        board = new int[SIZE][SIZE];
        revealed = new boolean[SIZE][SIZE];
        numMinesRemaining = MINES;

        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                final int r = row;
                final int c = col;
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(r, c);
                    }
                });
                grid[row][col] = button;
                panel.add(button);
            }
        }

        statusLabel = new JLabel();
        updateStatusLabel();

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        initializeBoard();
    }
