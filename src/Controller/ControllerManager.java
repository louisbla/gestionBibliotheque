package Controller;

import View.FrameMain;

public class ControllerManager {

    private FrameMain frame;

    private ControllerAccueil ca;

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


}
