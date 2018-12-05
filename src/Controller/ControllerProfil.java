package Controller;

import View.FrameMain;
import View.PanelProfil;

public class ControllerProfil{

	 public ControllerProfil(ControllerManager controller, FrameMain frame) {
	        PanelProfil p = new PanelProfil();

	        frame.getDesktopContainer().add(p);
	        frame.getDesktopContainer().updateUI();
	    }
}
