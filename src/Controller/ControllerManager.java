package Controller;

import DAO.DBManager;
import View.FrameMain;
import user.*;

public class ControllerManager {

	public static Utilisateur utilisateur;
	
    private FrameMain frame;

    private ControllerAccueil ca;
    private ControllerLivre cl;
    private ControllerUtilisateurs cu;
    private ControllerEmprunts ce;
    private ControllerProfil cp;
    private ControllerSalle cs;

    /////////////////////////////////////////////////////

    public ControllerManager() {
    	utilisateur = new Utilisateur();
        frame = new FrameMain(this);

        accueil();

        frame.setVisible(true);
        
        utilisateur = new Utilisateur();
        //login("BLAL19019408", "azerty");
    }

    public void clear() {
		frame.getDesktopContainer().removeAll();

		ca = null;
		cl = null;
		cu = null;
		ce = null;
		cs = null;
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
	
	public void profil() {
		cp = new ControllerProfil(this, frame);
	}
	
	public void salle() {
		cs = new ControllerSalle(this, frame);
	}

	////////////// utilisateur ///////////////////////////
	
	public void login (String identifiant, String mdp) {
		if(DBManager.tryUserPassword(identifiant, mdp)) {
			System.out.println("combinaison identifiant-password correcte");
			utilisateur = DBManager.getUser(identifiant);
			this.frame.refreshOnglet();
		}
	}
	
	public void logout() {
		utilisateur = new Utilisateur();
		this.frame.refreshOnglet();
	}
	
}
