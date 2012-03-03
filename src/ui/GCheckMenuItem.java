package ui;

import org.gnome.gdk.Event;
import org.gnome.gtk.CheckMenuItem;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuBar;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.Statusbar;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WindowPosition;

public class GCheckMenuItem extends Window {

    private Statusbar statusbar;

    public GCheckMenuItem() {
    
        setTitle("Check menu item");
        
        initUI();
        
        connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    
        setDefaultSize(250, 200);
        setPosition(WindowPosition.CENTER);
        showAll();
    }    
    
    private void initUI() {
    
        VBox vbox = new VBox(false, 0);
        
        MenuBar menuBar = new MenuBar();
        MenuItem fileItem = new MenuItem("File");
        menuBar.append(fileItem);
        
        Menu fileMenu = new Menu();
        MenuItem quitItem = new MenuItem("Quit");
        
        quitItem.connect(new MenuItem.Activate() {

            public void onActivate(MenuItem menuItem) {
                Gtk.mainQuit();
            }
        });
        
        fileMenu.append(quitItem);
        fileItem.setSubmenu(fileMenu);
        
        Menu viewmenu = new Menu();
        MenuItem view = new MenuItem("View");
        view.setSubmenu(viewmenu); 

        CheckMenuItem stat = new CheckMenuItem("View Statusbar");
        stat.setActive(true);
        viewmenu.append(stat);

        menuBar.append(view);

        statusbar = new Statusbar();
        statusbar.setMessage("Ready");

        vbox.packStart(menuBar, false, false, 0);
        vbox.packStart(new Label(""), true, false, 0);
        vbox.packStart(statusbar, false, false, 0);

        stat.connect(new MenuItem.Activate() {

            public void onActivate(MenuItem menuItem) {
                CheckMenuItem item = (CheckMenuItem) menuItem;

                if (item.getActive()) {
                    statusbar.show();
                } else {
                    statusbar.hide();
                }
            }
        });

        add(vbox);
    }
    
    public static void main(String[] args) {
        
        Gtk.init(args);
        new GCheckMenuItem();
        Gtk.main();   
    }
}