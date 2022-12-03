package com.mycompany.a1;
import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * Interface that implements methods for selecting Fixed GameObjects
*/
public interface ISelectable {
	public void setSelected(boolean b);
	public boolean isSelected();
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	public void draw(Graphics g, Point pCmpRelPrnt);
}
