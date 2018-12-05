package Controller;

import javax.swing.JPanel;

import View.FrameMain;
import View.PanelEmprunt;
import View.PanelEmpruntUtilisateur;
import View.PanelUtilisateurs;
import user.Droit;

public class ControllerEmprunts {

	public ControllerEmprunts(ControllerManager controller, FrameMain frame) {
		
		JPanel p = new JPanel();
		
		if(controller.utilisateur.getDroit().equals(Droit.admin)) p = new PanelEmprunt();
		else p = new PanelEmpruntUtilisateur();

        frame.getDesktopContainer().add(p);
        frame.getDesktopContainer().updateUI();
    }
}
