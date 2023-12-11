package ue01;

import java.util.Arrays;

public class LED extends Component {

    boolean isShining = false;


    public LED() {
        super(1, 0);
    }

    /**
     * setz value bei led je nach output
     */
    public void phase1() {
        System.out.println();
        isShining = allInputs[0].otherOutput.value;
    }

    @Override
    public void phase2() {

    }
}
