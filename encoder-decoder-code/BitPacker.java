//Name: Jordan Akroyd   ID: 1280741
//Name: Jade Myers      ID: 1287182

import java.util.*;
import java.lang.Math;

class BitPacker {

    public static void main(String[] args) {
        long binStr = 0b0;
        int startPhrase = 256;
        int totalPhrase = startPhrase;
        int leftShiftAmount = 9;
        int firstShift = leftShiftAmount;
        long fillBits = (long) (Math.pow(2, 64));
        long checkBits = fillBits;
        int counter = 0;

        //shift bits to the left by the total length of the long - the shift amount
        checkBits <<= (63 - leftShiftAmount);
        //and this with fill bits to mask
        checkBits &= fillBits;

        Scanner sc = new Scanner(System.in);

        //while the scanner has input
        while (sc.hasNextLine()) {
            //get the phrase number
            int phrNum = Integer.parseInt(sc.nextLine());
            //increase the counter by the left shift amount
            counter += leftShiftAmount;
            //left shift the binary string by the left shift amount
            binStr <<= leftShiftAmount;
            //or the binary string with the phrase number to put it in
            binStr |= phrNum;//Oring phrase number

            //if the phrase number is a reset character, reset variab;es
            if (phrNum == 256) {
                startPhrase = 256;
                totalPhrase = startPhrase;
                leftShiftAmount = 9;
            }

            totalPhrase++;

            //if the startphrase is half of the total phase, increase the amount of bits to encode
            if ((startPhrase * 2) == totalPhrase) {
                // System.out.println("Increasing Bit amount");
                startPhrase = totalPhrase;
                leftShiftAmount++;
            }

            //if the counter and the left shift amount is greater than or equal to the size of the long
            if (counter + leftShiftAmount >= 63) {
                //move the binary string over bu the size of the counter
                binStr <<= (63 - counter);
                //print the binary string
                System.out.println(binStr);
                //reset the binary string
                binStr = 0b0;
                //first shift is now equal to the left shift amount
                firstShift = leftShiftAmount;
                //reset counter
                counter = 0;
            }
        }
        //move the binary string over by the counter
        binStr <<= (63 - counter);
        //print out the binary string
        System.out.println(binStr);
    }
}
