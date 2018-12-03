package Controller;

import View.FrameMain;

public class ControllerManager {

    private FrameMain frame;

    private ControllerAccueil ca;
    //private ControlleurLivre cl;
    private ControllerUtilisateurs cu;

    /////////////////////////////////////////////////////

    public ControllerManager() {
        frame = new FrameMain(this);

        accueil();

        frame.setVisible(true);
    }

    public void clear() {
		frame.getDesktopContainer().removeAll();

		ca = null;
		//cl = null;
		cu = null;
	}
    /////////////////////////////////////////////////////

    public void accueil() {
        ca = new ControllerAccueil(this, frame);
    }
    
    public void utilisateurs() {
    	cu = new ControllerUtilisateurs(this, frame);
    }


}
