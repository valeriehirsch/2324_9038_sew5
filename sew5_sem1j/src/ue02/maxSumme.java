import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class maxSumme {
    static List<Integer> geld = new ArrayList<>(Arrays.asList(200,100,50,20,10,5,2,1));
    public static void main(String[] args) {

        String angabe3 = "";
        String[] split = angabe3.split("\n");
        int[][] a = new int[split.length][];

        for (int i = 0; i < a.length; i++) {
            String[] curNbrs = split[i].split(" ");
            a[i] = new int[curNbrs.length];
            for (int j = 0; j < curNbrs.length; j++) {
                a[i][j] = Integer.parseInt(curNbrs[j]);
            }
        }


    }


    private static int getMax(int zeile, int spalte, int[][] input) {
        if (zeile == input.length) {
            return 0;
        }
        int a = getMax(zeile + 1, spalte, input);
        int b = getMax(zeile + 1, spalte + 1, input);

        if (a > b) {
            return a + input[zeile][spalte];
        } else {
            return b + input[zeile][spalte];
        }
    }




    public static int myeuler(ArrayList<Integer> curCoins, int target, int firstCoin) {
        if (target == 0) {
            return 1;
        }
        int ct = 0;
        for (Integer coin : curCoins) {
            if (coin <= target) {
                if (coin <= firstCoin) {
                    ct += myeuler(curCoins, target - coin, coin);
                }
            }
        }
        return ct;
    }

}
