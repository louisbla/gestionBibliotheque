package Controller;

import View.FrameMain;
import user.*;

public class ControllerManager {

	public static Utilisateur utilisateur;
	
    private FrameMain frame;

    private ControllerAccueil ca;
    private ControllerLivre cl;
    private ControllerUtilisateurs cu;
    private ControllerEmprunts ce;

    /////////////////////////////////////////////////////

    public ControllerManager() {
        frame = new FrameMain(this);

        accueil();

        frame.setVisible(true);
        
        utilisateur = new Utilisateur();
    }

    public void clear() {
		frame.getDesktopContainer().removeAll();

		ca = null;
		cl = null;
		cu = null;
		ce = null;
	}
    /////////////////////////////////////////////////////

    public void accueil() {
        ca = new ControllerAccueil(this, frame);
    }
    
    public void utilisateurs() {
    	cu = new ControllerUtilisateurs(this, frame);
    }
    
    public void emprunts() {
    	ce = new ControllerEmprunts(this, frame);
    }

	public void livre() {
		cl = new ControllerLivre(this, frame);

	}

	////////////// utilisateur ///////////////////////////
	
	public void login (String identifiant, String mdp) {
	
	}
}
