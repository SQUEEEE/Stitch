package ui;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;

public class GUItest extends Window{
	public GUItest(){
		setTitle("Stitch");
		
		connect(new Window.DeleteEvent(){
			public boolean onDeleteEvent(Widget source, Event event){
				Gtk.mainQuit();
				return false;
			}
		});
		
		setDefaultSize(400, 300);
		setPosition(WindowPosition.CENTER);
		show();
	}
	
	public static void main(String[] args){
		Gtk.init(args);
		new GUItest();
		Gtk.main();
	}
}
