package ue01;

public class Taster extends Component {


    boolean isPressed = false;

    public Taster() {
        super(0, 1);
    }

    public void press() {
        isPressed = !isPressed;
    }

    /**
     * setzt den out value auf isPressed
     */
    public void phase2() {
        allOutputs[0].value = isPressed;
    }

}
