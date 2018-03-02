import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class LightButton extends JButton{
private static int SIZE = 50;
boolean isOn = false;
private int row;
private int col;

public LightButton(int r,int c) {
	setBackground(Color.BLACK);
	row = r;
	col = c;
	Dimension size = new Dimension(SIZE,SIZE);
	setPreferredSize(size);
}
public int getRow() {
	return row;
}
public int getCol() {
	return col;
}
public boolean getIsOn() {
	return isOn;
}

public void reset() {
	isOn= false;
	setBackground(Color.black);
}

public void toggle() {
	if (isOn == true){
		setBackground(Color.black);
		isOn= false;
	}
	else{
		setBackground(Color.white);
		isOn= true;
		}
	}
}
