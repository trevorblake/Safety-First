package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

/**
 * Custom Button class that is used in building 
 * multiple Buttons using the same style
 * @author Trevor Blake
 *
 */
public class MyButton extends Button {

	public MyButton(String title) {
		super(title);
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setPadding(Component.TOP, 4);
		this.getAllStyles().setPadding(Component.BOTTOM, 4);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
	}
	
	public void disable() {
		this.setEnabled(false);
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setFgColor(ColorUtil.BLUE);
	}
	
	public void enable() {
		this.setEnabled(true);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
	}
}
