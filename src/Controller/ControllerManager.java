package Controller;

import View.FrameMain;

public class ControllerManager {

    private FrameMain frame;

    private ControllerAccueil ca;
    private ControllerLivre cl;

    /////////////////////////////////////////////////////

    public ControllerManager() {
        frame = new FrameMain(this);

        accueil();

        frame.setVisible(true);
    }

    /////////////////////////////////////////////////////

    public void accueil() {
        ca = new ControllerAccueil(this, frame);
    }

	public void livre() {
		cl = new ControllerLivre(this, frame);
		
	}

	public void clear() {
		frame.getDesktopContainer().removeAll();

		ca = null;
		cl = null;
	}

}
