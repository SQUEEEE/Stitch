package general;

import org.gnome.gtk.Button;
import org.gnome.gtk.HBox;


public class Stitch{
	Button button;
	int row;
	int stitch;
	String draw;
	
	public Stitch(int r, int s, String d, HBox h){
		
		this.row = r;
		this.stitch = s;
		this.draw = d;
		this.button = new Button(this.draw);
		
		
		this.button.connect(new Button.Clicked() {

			public void onClicked(Button b) {
				System.out.println("Klickad");
				//b.setLabel("x");
			}
		});
		
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

}
