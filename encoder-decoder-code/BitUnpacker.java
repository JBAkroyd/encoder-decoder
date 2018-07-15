//Name: Jordan Akroyd   ID: 1280741
//Name: Jade Myers      ID: 1287182

import java.lang.Math;
import java.util.*;

class BitUnpacker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int startPhrase = 256;
        int totalPhrase = startPhrase;
        int leftShiftAmount = 9;
        long binStr = 0;

        //while there is something to read
        while (sc.hasNextLine()) {
            //get the binary string
            binStr = Long.parseLong(sc.nextLine());

            //while the binary string isnt empty
            while (binStr != 0) {

                //get the phrase number using the binary string and the left shift amount
                long phrNum = GetPhraseNumber(binStr, leftShiftAmount);
                //print the phrase number
                System.out.println(phrNum);
                //shift the binary string by the left shift amount
                binStr <<= leftShiftAmount;
                //and the binary string with a long full of bits
                binStr &= (long) (Math.pow(2, 63));

                //if the phrase number is a reset, reset the variables
                if (phrNum == 256) {
                    startPhrase = 256;
                    totalPhrase = startPhrase;
                    leftShiftAmount = 9;
                }

                totalPhrase++;

                //if the start phrase is half the total phrase increase the bits to encode
                if ((startPhrase * 2) == totalPhrase) {
                    startPhrase = totalPhrase;
                    leftShiftAmount++;
                }
            }
        }
    }

    //gets the phrase number using the binary string and the shift amount
    public static long GetPhraseNumber(long binStr, int shiftAmount) {
        //create a long full of bits
        long fullBits = (long) Math.pow(2, 63);
        long fillBits = fullBits;
        //shift fill bits by the length of the binary string - the shift amount
        fillBits <<= (63 - shiftAmount);
        //phrase number is the and between the fill bits and the binary string
        long phrNum = fillBits & binStr;
        //right shift to move the bits off the end
        phrNum >>= (63 - shiftAmount);
        return (long) phrNum;
    }
}
