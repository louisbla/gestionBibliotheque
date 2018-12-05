package Controller;

import View.FrameMain;
import View.PanelAccueil;
import View.PanelSalle;

public class ControllerAccueil {

    public ControllerAccueil(ControllerManager controller, FrameMain frame) {
        PanelAccueil p=new PanelAccueil(controller);
    	
    	
        frame.getDesktopContainer().add(p);
        frame.getDesktopContainer().updateUI();
    }
}
