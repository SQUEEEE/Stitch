package general;

import org.gnome.gtk.Button;

/*
 * Does things when a Stitchbutton is clicked
 * 
 */

public class StitchHandler implements Button.Clicked { 
	int row;
	int stitch;
	String draw;
	String tool;
	
	public StitchHandler(int r, int s, String d, String t){
		this.row = r;
		this.stitch = s;
		this.draw = d;
		this.tool = t;
	}
	
	public void onClicked (Button button){
		button.setLabel(this.tool);
		this.draw = this.tool;
	}
}
