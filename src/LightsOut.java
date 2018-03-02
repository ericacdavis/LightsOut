import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class LightsOut extends JFrame{
	private static final int GRIDSIZE = 3;
	LightButton lightboard [][]= new LightButton [GRIDSIZE][GRIDSIZE];
	
	public LightsOut() {
		initGUI();
		
		setTitle("Lights Out Game     Creator Erica Davis");
		setSize(700, 200); //pixels
		setResizable(true);
		pack();
		setLocationRelativeTo(null); //centers on screen, do this after packing but before visible

			
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void initGUI() {
		JPanel titlePanel = new JPanel();
		add(titlePanel, BorderLayout.PAGE_START);
		titlePanel.setBackground(Color.BLACK);
		JLabel titleLabel = new JLabel("Lights out Game");
		titlePanel.add(titleLabel);
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //left or right
		Font titleFont = new Font("Times New Roman", Font.BOLD, 50);
		titleLabel.setForeground(new Color(83, 22, 226));
		titleLabel.setFont(titleFont);
		
		//center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout (GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		for (int r = 0; r< GRIDSIZE; r++) {
			for (int c = 0; c < GRIDSIZE; c++) {
			lightboard[r][c] = new LightButton (r,c);
			lightboard[r][c].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LightButton button = (LightButton) e.getSource();
					int row = button.getRow();
					int col = button.getCol();
					buttonClicked(row,col);
				}
			});
			centerPanel.add(lightboard[r][c]);
			}	
		}
		boardSetup();
	}

	public void boardSetup() {
		System.out.println("in boardSetup");
		for(int r=0; r< GRIDSIZE; r++) {
			for(int c=0; c< GRIDSIZE; c++) {
				lightboard[r][c].reset();
			}
		}	
		lightboard[1][1].toggle();
		randomClick();
	}
	
	public void buttonClicked(int r, int c) {
		lightboard[r][c].toggle();
		if (check(r-1, c)) {
			lightboard[r-1][c].toggle();
		}
		if (check(r, c+1)) {
			lightboard[r][c+1].toggle();
		}
		if (check(r+1, c)) {
			lightboard[r+1][c].toggle();
		}
		if (check(r, c-1)) {
			lightboard[r][c-1].toggle();
		}
		if (lightboard[1][1].getIsOn() && !lightboard[0][1].getIsOn() && !lightboard[0][2].getIsOn()&& !lightboard[1][0].getIsOn()
				&& !lightboard[0][0].getIsOn()&& !lightboard[1][2].getIsOn()&& !lightboard[2][0].getIsOn()&& !lightboard[2][1].getIsOn()
				&& !lightboard[2][2].getIsOn()) {
			String message= "You won!";
			promptForNewGame(message);
		}
	}
	private boolean check(int r, int c) {
		if (r > -1 && r < GRIDSIZE && c > -1 && c < GRIDSIZE) {
			return true;
		}
		else {
			return false;
		}
	}
	public void promptForNewGame(String message) {
		int option = JOptionPane.showConfirmDialog(this, message, "Play Again?", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.YES_OPTION) {
			boardSetup();
		}else {
			System.exit(0);
		}
	}

	public void randomClick() {
		Random rand = new Random();
		int pickRow;
		int pickCol;
		for (int i=0; i <3; i++) {
			pickRow = rand.nextInt(GRIDSIZE);
			pickCol = rand.nextInt(GRIDSIZE);
			lightboard[pickRow][pickCol].toggle();
			if (check(pickRow-1, pickCol)) {
				lightboard[pickRow-1][pickCol].toggle();
			}
			if (check(pickRow, pickCol+1)) {
				lightboard[pickRow][pickCol+1].toggle();
			}
			if (check(pickRow+1, pickCol)) {
				lightboard[pickRow+1][pickCol].toggle();
			}
			if (check(pickRow, pickCol-1)) {
				lightboard[pickRow][pickCol-1].toggle();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new LightsOut();
            }   
        });
	}

}