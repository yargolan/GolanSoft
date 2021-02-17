package com.ygsoft.applications;

/**
 * Created by yarongolan on 1/28/16.
 */
public class Hanoi {

    static int index = 1;

    public static void main(String[] args) {
        int nDisks = 8;
        doTowers(nDisks, 'A', 'B', 'C');
    }


    public static void doTowers(int topN, char from, char inter, char to) {

        if (topN == 1)
        {
            System.out.println (String.format ("%d: Disk [%d] from [%s] to [%s]", index, 1, from, to));
            index++;
        }
        else
        {
            doTowers(topN - 1, from, to, inter);
            System.out.println (String.format ("%d: Disk [%d] from [%s] to [%s]", index, topN, from, to));
            index++;
            doTowers(topN - 1, inter, from, to);
        }
    }
}
