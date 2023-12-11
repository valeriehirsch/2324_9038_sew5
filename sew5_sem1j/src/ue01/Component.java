package ue01;

/**
 * BSP 1
 */
public abstract class Component {


    protected Input[] allInputs;
    protected Output[] allOutputs;
    protected boolean[] valueInputs;

    /**
     * @param anzahlIn  wieviele Inputs angelegt werden
     * @param anzahlOut wieviele Outputs angelegt werden
     */
    public Component(int anzahlIn, int anzahlOut) {
        allInputs = new Input[anzahlIn];
        for (int i = 0; i < allInputs.length; i++)
            allInputs[i] = new Input();
        allOutputs = new Output[anzahlOut];
        for (int i = 0; i < allOutputs.length; i++)
            allOutputs[i] = new Output();
        valueInputs = new boolean[anzahlIn];
    }


    /**
     * rechnet den neuen Zustand aus
     */
    public abstract void phase2();

    /**
     * @param other          anderer Component
     * @param myPinInput     meine Input Nr
     * @param otherPinOutput andere Output Nr
     */
    public void connect(Component other, int myPinInput, int otherPinOutput) {
        allInputs[myPinInput].connect(other.allOutputs[otherPinOutput]);
    }

    /**
     * setzt alle die Values der Inputs in das dazugehörige Array
     */
    public void phase1() {

        for (int i = 0; i < valueInputs.length; i++) {
            valueInputs[i] = allInputs[i].otherOutput.value; // TODO; get
        }


    }

}