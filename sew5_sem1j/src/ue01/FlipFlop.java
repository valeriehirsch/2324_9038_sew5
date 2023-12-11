package ue01;

import java.util.HashMap;
import java.util.List;

public class FlipFlop extends Component {

    State f = State.LOW;

    public FlipFlop() {
        super(2, 2);
    }

    /**
     * setzt State und gibt je nach State dann den jeweiligen value in die Output Liste
     */
    @Override
    public void phase2() {
        f = f.calcState(valueInputs[0], valueInputs[1]);
        allOutputs[0].value = f.isHigh();
        allOutputs[1].value = !f.isHigh();
    }


    enum State {
        HIGH {
            /**
             * @param S Set
             * @param R Reset
             * @return rechnet mit FLipFlop Logik den State aus
             */
            @Override
            State calcState(boolean S, boolean R) {
                if (!S && R) {
                    return State.LOW;
                } else {
                    return State.HIGH;
                }
            }

            @Override
            boolean isHigh() {
                return true;
            }
        },
        LOW {
            /**
             * @param S Set
             * @param R Reset
             * @return rechnet mit FLipFlop Logik den State aus
             */
            @Override
            State calcState(boolean S, boolean R) {
                if (S && !R) {
                    return State.HIGH;
                } else {
                    return State.LOW;
                }
            }

            @Override
            boolean isHigh() {
                return false;
            }
        };

        /**
         * @param S Set
         * @param R Reset
         * @return rechnet mit FLipFlop Logik den State aus
         */
        abstract State calcState(boolean S, boolean R);

        /**
         * @return gibt einen boolean wert je nach state zurück
         */
        abstract boolean isHigh();


    }


}
