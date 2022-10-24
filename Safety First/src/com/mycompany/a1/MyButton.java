package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

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
}
