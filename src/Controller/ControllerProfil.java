package Controller;

import javax.swing.JPanel;

import View.FrameMain;
import View.PanelEmprunt;
import View.PanelEmpruntUtilisateur;
import View.PanelProfil;
import View.PanelProfilVisiteur;
import user.Droit;

public class ControllerProfil{

	public ControllerProfil(ControllerManager controller, FrameMain frame) {

		JPanel p = new JPanel();
		if(controller.utilisateur.getDroit().equals(Droit.visiteur)) p = new PanelProfilVisiteur();
		else p = new PanelProfil();

		frame.getDesktopContainer().add(p);
		frame.getDesktopContainer().updateUI();
	}
}
