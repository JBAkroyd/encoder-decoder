//Name: Jordan Akroyd   ID: 1280741
//Name: Jade Myers      ID: 1287182

import java.util.*;

class Decoder {

    //creates an item which has the pattern and its predecessors phrase number
    public static class Item {
        public String pattern;
        public int predecessor;

        public Item(String pattern_, int predecessor_) {
            pattern = pattern_;
            predecessor = predecessor_;
        }
    }

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            //starts phrase number at 256 (all characters and reset)
            int phrase = 256;
            //creates a dictionary
            ArrayList<Item> dictionary = new ArrayList<Item>();
            for (char cha = '\0'; cha <= '\u0100'; cha++) {
                Item item = new Item(cha + "", -1);
                dictionary.add(item);
            }

            int phraseNumber = Integer.parseInt(sc.nextLine());
            int prevNumber = phraseNumber;

            //if there is a line in the scanner
            while (sc.hasNextLine()) {

                phraseNumber = Integer.parseInt(sc.nextLine());

                //if there is a reset character
                if (phraseNumber == 256) {
                    //print the pattern of the previous phrase
                    Item prevPhrase = dictionary.get(prevNumber);
                    System.out.print(prevPhrase.pattern);

                    //reset the dictionary and other variables
                    dictionary = new ArrayList<Item>();
                    for (char cha = '\0'; cha <= '\u0100'; cha++) {
                        Item item = new Item(cha + "", -1);
                        dictionary.add(item);
                    }

                    phraseNumber = Integer.parseInt(sc.nextLine());
                    phrase = 256;
                    prevNumber = phraseNumber;
                    phraseNumber = Integer.parseInt(sc.nextLine());
                }
                //temp is the item in the dictionary at the index of previous number
                Item temp = dictionary.get(prevNumber);
                Item item;

                //if phrase number is greater than or equal to the dictionary size
                if (phraseNumber >= dictionary.size()) {
                    //create a new item with the pattern of the item and the first character of the pattern
                    item = new Item(temp.pattern + temp.pattern.charAt(0), prevNumber);
                } else {
                    //create a new item which is the pattern and the character at the start of the pattern
                    // at the phrase number
                    item = new Item(temp.pattern + dictionary.get(phraseNumber).pattern.charAt(0), prevNumber);
                }

                System.out.print(temp.pattern);
                dictionary.add(item);
                phrase++;
                prevNumber = phraseNumber;
            }
            //print final pattern
            System.out.println(dictionary.get(prevNumber).pattern);
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
