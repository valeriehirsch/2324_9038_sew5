package ue01;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        doFlipFlop();
        //doAND();
    }

    private static void doFlipFlop() {

        Taster reset = new Taster();
        Taster set = new Taster();
        LED led1 = new LED();
        FlipFlop flip = new FlipFlop();

        List<Component> alle = List.of(reset, set, flip, led1);

        flip.connect(reset, 0, 0);
        flip.connect(set, 1, 0);
        led1.connect(flip, 0, 0);

        for (int i = 1; i <= 5; i++) {
            switch (i) {
                case 1:
                    reset.press();
                case 2:
                    set.press();

            }


            for (Component c : alle) {
                c.phase1();
            }
            for (Component c2 : alle) {
                c2.phase2();
            }

            System.out.println("LED1:" + led1.isShining);
        }
    }

}