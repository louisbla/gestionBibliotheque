package Controller;

import View.FrameMain;
import View.PanelLivre;

public class ControllerLivre{	
	 public ControllerLivre(ControllerManager controller, FrameMain frame) {
	        PanelLivre p=new PanelLivre();

	        frame.getDesktopContainer().add(p);
	        frame.getDesktopContainer().updateUI();
	    }
}
