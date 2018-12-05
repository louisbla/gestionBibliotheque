package Controller;
import javax.swing.JPanel;

import View.FrameMain;
import View.PanelProfil;
import View.PanelProfilVisiteur;
import View.PanelSalle;
import user.Droit;

public class ControllerSalle{

	public ControllerSalle(ControllerManager controller, FrameMain frame) {

		PanelSalle p =new PanelSalle();

		frame.getDesktopContainer().add(p);
		frame.getDesktopContainer().updateUI();
	}
}

