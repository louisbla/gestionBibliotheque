package Controller;

import View.FrameMain;
import View.PanelUtilisateurs;

public class ControllerUtilisateurs {

	public ControllerUtilisateurs(ControllerManager controller, FrameMain frame) {
		PanelUtilisateurs p=new PanelUtilisateurs();

        frame.getDesktopContainer().add(p);
        frame.getDesktopContainer().updateUI();
    }
}
