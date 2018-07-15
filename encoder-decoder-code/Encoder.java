//Name: Jordan Akroyd   ID: 1280741
//Name: Jade Myers      ID: 1287182

import java.util.*;
import java.util.Arrays;
import java.lang.Math;

class Encoder {

    //index of a pattern
    static int phrase = 0;

    //create a MultiwayTrie node which hold its phrase number and its child
    public static class MultiwayTrie {

        public int phrase;
        private int value;
        private MultiwayTrie nextSibling;
        private MultiwayTrie child;

        //constructs the multiway trie node
        public MultiwayTrie(int value_, int phrase_) {
            phrase = phrase_;
            value = value_;
        }

        //finds a nodes child node
        public MultiwayTrie findChild(int value, int phrase) {
            MultiwayTrie tempChild = child;
            while (tempChild != null && tempChild.value != value) {
                tempChild = tempChild.nextSibling;
            }
            //if child is null create the child
            if (tempChild == null) {
                tempChild = new MultiwayTrie(value, phrase);
                tempChild.nextSibling = child;
                child = tempChild;
                return null;
            } else {
                return tempChild; // if we found a child with a value matching
            }
        }

        //returns a nodes phrase
        public int getPhrase() {
            return phrase;
        }

        //returns a nodes child
        public MultiwayTrie getChild() {
            return child;
        }
    }

    public static void main(String[] args) {

        try {
            //if the wrong number of arguments is displayed return and tell them what they did wrong
            if (args.length != 1) {
                System.err.println("Wrong number of arguments: java Encoder <Size of Dictionary>");
                return;
            }

            //maxiumum dictionary size (maximum bits)
            long maxDictSize = (long) Math.pow(2, Long.parseLong(args[0]));
            //creates an array of multiwaytrie nodes to accomadate for
            // the first 256 ascii characters and a reset character
            MultiwayTrie[] rootTrie = new MultiwayTrie[257];
            //fills the root trie with multiway trie nodes that have the
            // value and phrase as an increasing number to 256
            Arrays.setAll(rootTrie, value -> {
                MultiwayTrie temp = new MultiwayTrie(value, phrase);
                phrase++;
                return temp;
            });

            //character to read in
            int character = System.in.read();
            //their previous phrase number
            int prevPhrase = 0;

            while (true) {
                //make a multiwaytrie node for a character to see if its there
                MultiwayTrie parent = rootTrie[character];

                //while parent isn't null
                while (parent != null) {
                    //the previous gets the phrase of the parent
                    prevPhrase = parent.getPhrase();
                    //get the next character
                    character = System.in.read(); //reads next a
                    //if the child of the parent is there return the new parent otherwise create child and return null
                    parent = parent.findChild(character, phrase);
                }
                //when end of file break loop
                if (character == -1)
                    break;
                //prints the previous phrase
                System.out.println(prevPhrase);
                //increase phrase size
                phrase++;
                //if the phrase size is equal to the dictionary size reset the dictionary
                if (phrase == maxDictSize) {
                    phrase = 0;
                    Arrays.setAll(rootTrie, value -> {
                        MultiwayTrie temp = new MultiwayTrie(value, phrase);
                        phrase++;
                        return temp;
                    });
                    System.out.println(256);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

