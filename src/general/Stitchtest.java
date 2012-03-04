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
		
		final Stitch[] pattern = new Stitch[stitches*rows];
		int stitchnr = 0;
	
		for(int i=0;i<rows;i++){
			hboxes[i] = new HBox(true, 0);
			haligns[i] = new Alignment(0, 0, 0, 0);
			for(int j=0;j<stitches;j++){
				pattern[stitchnr] = new Stitch(i, j, "      ", hboxes[i]);
				stitchnr++;
			}
			haligns[i].add(hboxes[i]);
			vbox.packStart(haligns[i], false, false, 2);
		}
		
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
	
	public static void ask(Stitch [] pattern, int nostitches){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int changeRow = 0;
		int changeStitch = 0;
		String draw = null;
		String line = null;
		
		while(true){

			System.out.print("Vilken rad vill du ändra på? ");
			try{
				line = br.readLine();
				changeRow = Integer.parseInt(line);

			}catch(IOException e){
				System.out.println("Error");
				System.exit(1);
			}
			System.out.print("Vilken maska på rad " + changeRow + " vill du ändra på?  ");
			try{
				line = br.readLine();
				changeStitch = Integer.parseInt(line);
			}catch(IOException e){
				System.exit(1);
			}
		
			System.out.print("Vad vill du ändra maskan till? ");
			try{
				draw = br.readLine();
			
			}catch(IOException e){
				System.exit(1);
			}
		
			System.out.println("Du ville ändra på rad " + changeRow + ", maska " + changeStitch + " till " + draw);
			Stitch s = findStitch(pattern, changeRow, changeStitch, nostitches);
			s.changeDraw(draw);
		}
	}
	
	public Stitch[] getPattern(){
		return this.pattern;
	}
	
	public static void main(String[] args){
		
		Gtk.init(args);
		Stitchtest stitchtest = new Stitchtest(4, 10);
		Gtk.main();
		ask(stitchtest.getPattern(), 40);
	}
}
