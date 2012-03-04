package general;

import org.gnome.gtk.Button;
import org.gnome.gtk.HBox;


public class Stitch{
	Button button;
	int row;
	int stitch;
	String draw;
	String tool;
	
	public Stitch(int r, int s, String d, String t, HBox h){
		
		this.row = r;
		this.stitch = s;
		this.draw = d;
		this.tool = t;
		this.button = new Button(this.draw);
		
		
		this.button.connect(new StitchHandler(this.row, this.stitch, this.draw, this.tool));
		
		h.add(this.button);
		
		
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getStitch(){
		return this.stitch;
	}
	
	public String getDraw(){
		return this.draw;
	}
	
	public Button getButton(){
		return this.button;
	}
	
	public void changeDraw(String draw){
		this.draw = draw;
	}
	
	public void changeTool(String tool){
		this.tool = tool;
		this.button.connect(new StitchHandler(this.row, this.stitch, this.draw, this.tool));
	}

}
