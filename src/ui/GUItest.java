package ui;


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

public class GUItest extends Window{
	
	private Statusbar statusbar;
	private Label label;
	private String tool;
	private String drawlabel;
	private Button draw;

	
	public GUItest(){
		setTitle("Stitch");
		
		initUI(10, 20);
		
		connect(new Window.DeleteEvent(){
			public boolean onDeleteEvent(Widget source, Event event){
				Gtk.mainQuit();
				return false;
			}
		});
		
		setDefaultSize(400, 300);
		setPosition(WindowPosition.CENTER);
		showAll();
	}
	
	private void initUI(int stitches, int rows){
		
		VBox vbox = new VBox(false, 0);
		HBox hbox = new HBox(true, 0);
		HBox hbox2 = new HBox(true, 0);
		
		MenuBar menuBar = new MenuBar();
		MenuItem fileItem = new MenuItem("File");
		menuBar.append(fileItem);
		
		Menu fileMenu = new Menu();
		MenuItem quitItem = new MenuItem("Avsluta");
		
		quitItem.connect(new MenuItem.Activate(){
			
			public void onActivate(MenuItem menuItem){
				Gtk.mainQuit();
			}
		});
		
		fileMenu.append(quitItem);
		fileItem.setSubmenu(fileMenu);
		
		Menu viewmenu = new Menu();
		MenuItem view = new MenuItem("Titta");
		view.setSubmenu(viewmenu);
		
		CheckMenuItem stat = new CheckMenuItem("Se statusbar");
		stat.setActive(true);
		viewmenu.append(stat);
		
		menuBar.append(view);
		
		Button toolselect = new Button("x");
		toolselect.setSizeRequest(20, 15);
		Button toolselect2 = new Button("o");
		toolselect2.setSizeRequest(20, 15);
		
		hbox2.add(toolselect2);
		hbox2.add(toolselect);
		
		final Button[] buttons = new Button[stitches];
		
		drawlabel = "";

		
		for(int i=0;i<stitches;i++){
			buttons[i] = new Button();
		    buttons[i].setLabel("");
			buttons[i].connect(new Button.Clicked(){
				public void onClicked(Button source){
					drawlabel = tool;
					System.out.println("yay");
					source.setLabel(drawlabel);
				}
			});
			hbox.add(buttons[i]);
		}
		
		

	
		label = new Label("Wopwop");
		vbox.add(label);
		
		statusbar = new Statusbar();
		statusbar.setMessage("Klar");
		

		
		stat.connect(new MenuItem.Activate(){
			
			public void onActivate(MenuItem menuItem){
				CheckMenuItem item = (CheckMenuItem) menuItem;
				
				if(item.getActive()){
					statusbar.show();
				}else{
					statusbar.hide();
				}
			}
		});
		
		toolselect.connect(new Button.Clicked(){
			public void onClicked(Button source){
				System.out.println("x");
				statusbar.setMessage("Valt verktyg: x");
				tool = "x";
			}
		});
		
		toolselect2.connect(new Button.Clicked(){
			public void onClicked(Button source){
				System.out.println("o");
				statusbar.setMessage("Valt verktyg: o");
				tool = "o";
			}
		});
		
		
		
		Alignment halign = new Alignment(1, 0, 0, 0);
		Alignment halign2 = new Alignment(1, 0, 0, 0);
        halign.add(hbox);
        halign2.add(hbox2);
        
        vbox.packStart(halign2, false, false, 3);
		vbox.packStart(halign, false, false, 3);
		vbox.packStart(menuBar, false, false, 0);
		vbox.packStart(new Label(""), true, false, 0);
		vbox.packStart(statusbar, false, false, 0);
        
        
		
		add(vbox);	
	} 
	
	public static void main(String[] args){
		
		Gtk.init(args);
		new GUItest();
		Gtk.main();
	}
}
