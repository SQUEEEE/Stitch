package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.gnome.gdk.Event;
import org.gnome.gtk.Alignment;
import org.gnome.gtk.Button;
import org.gnome.gtk.CheckMenuItem;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.HBox;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuBar;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.Statusbar;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;

import testing.Pattern;


public class Stitchtest extends Window{
	
	Stitch[] pattern;
	public String tool;
	int nostitches;
	
	public Stitchtest(int rows, int stitches){
		setTitle("Stitch");

		this.pattern = initUI(rows, stitches);
		
		connect(new Window.DeleteEvent(){
			public boolean onDeleteEvent(Widget source, Event event){
				Gtk.mainQuit();
				return false;
			}
		});
		
		setDefaultSize(500, 500);
		setPosition(WindowPosition.CENTER);
		showAll();
		
	}
	
	public Stitch[] initUI(int rows, int stitches){
		VBox vbox = new VBox(false, 0);
		
		HBox[] hboxes = new HBox[rows];
		Alignment[] haligns = new Alignment[rows];
		
		nostitches = stitches*rows;
		
		final Stitch[] pattern = new Stitch[stitches*rows];
		int stitchnr = 0;
		tool = "-";
	
		for(int i=0;i<rows;i++){
			hboxes[i] = new HBox(true, 0);
			haligns[i] = new Alignment(0, 0, 0, 0);
			for(int j=0;j<stitches;j++){
				pattern[stitchnr] = new Stitch(i, j, "      ", tool, hboxes[i]);
				stitchnr++;
			}
			haligns[i].add(hboxes[i]);
			vbox.packStart(haligns[i], false, false, 2);
		}
		
		Button toolButton = new Button("x");
		toolButton.connect(new Button.Clicked(){
			public void onClicked(Button b){
				toolChange(pattern, nostitches, b.getLabel());
			}
		});
		
		Button toolButton2 = new Button("o");
		toolButton2.connect(new Button.Clicked(){
			public void onClicked(Button b){
				toolChange(pattern, nostitches, b.getLabel());
			}
		});
		
		HBox hbox = new HBox(false, 3);
		hbox.add(toolButton);
		hbox.add(toolButton2);
		Alignment halign = new Alignment(1, 0, 0, 0);
		halign.add(hbox);
		vbox.packStart(halign, false, false, 2);
		
        add(vbox);
        
        return pattern;
        
	}
	
	public static Stitch findStitch(Stitch[] stitches, int row, int stitch, int nostitches){
		
		Stitch s = null;
		
		for(int i=0;i<nostitches;i++){
			if(stitches[i].getRow() == row){
				if(stitches[i].getStitch() == stitch){
					s = stitches[i];
				}
			}
		}
		
		return s;
	}
	
	public Stitch[] getPattern(){
		return this.pattern;
	}
	
	public void toolChange(Stitch[] pattern, int nostitches, String tool){
		for(int i=0;i<nostitches;i++){
			pattern[i].changeTool(tool); 
		}
		System.out.println("Tool changed");
	}
	
	public static void main(String[] args){
		
		Gtk.init(args);
		new Stitchtest(4, 10);
		Gtk.main();
	}
}
