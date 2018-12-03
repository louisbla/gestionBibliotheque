package Controller;

import View.FrameMain;
import View.PanelEmprunt;
import View.PanelUtilisateurs;

public class ControllerEmprunts {

	public ControllerEmprunts(ControllerManager controller, FrameMain frame) {
		PanelEmprunt p=new PanelEmprunt();

        frame.getDesktopContainer().add(p);
        frame.getDesktopContainer().updateUI();
    }
}
