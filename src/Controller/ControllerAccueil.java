package Controller;

import View.FrameMain;
import View.PanelAccueil;

public class ControllerAccueil {

    public ControllerAccueil(ControllerManager controller, FrameMain frame) {
        PanelAccueil p=new PanelAccueil();

        frame.getDesktopContainer().add(p);
        frame.getDesktopContainer().updateUI();
    }
}
