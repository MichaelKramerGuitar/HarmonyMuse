package builders;

import general.containers.Triad;
import classifiers.TriadClassifier;
import utilities.CircularlyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Michael Kramer
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is to facilitate quickly building a concrete triad
 * from within the constructor of a class that extends the
 * ThreeNoteStructure.ConcreteTriad class
 */
public class TriadFactory<T extends Triad> {

    private final Integer[] distances = new Integer[]{3, 5}; // 3rd family, 5th family

    private final Integer[] majorIntervals = new Integer[]{4, 7}; //major third, perfect fifth
    private final Integer[] minorIntervals = new Integer[]{3, 7}; //major third, perfect fifth
    private final Integer[] diminishedIntervals = new Integer[]{3, 6}; //major third, perfect fifth
    private final Integer[] augmentedIntervals = new Integer[]{4, 8}; //major third, perfect fifth
    private final ArrayList<Integer[]> formulas = new ArrayList<Integer[]>(
            Arrays.asList(diminishedIntervals,
                    minorIntervals,
                    majorIntervals,
                    augmentedIntervals));

    public TriadFactory(){}

    /**
     * The purpose of this method is to build a triad given n triad container
     * a Note root and a String quality
     * <p>Precondition: A ConcreteTriad or extending class is being constructed </p>
     * <p>Postcondition: A ConcreteTriad of any type is created and returned</p>
     *
     * @param triad is a generic type triad, empty container to be populated
     *              according to its quality
     * @param root is the Note root of this triad
     * @param quality is the String quality of this triad
     * @return A ConcreteTriad of any type
     */
    public T buildTriad(T triad, Note root, String quality){

        TriadClassifier triadClassifier = new TriadClassifier();

        CircularlyLinkedList cllNames = new CircularlyLinkedList();

        String[] names = new String[]{"a", "b", "c", "d", "e", "f", "g"};
        for(int i = 0; i < names.length; i++){
            cllNames.add(names[i]);
        }

        Integer[] halfSteps = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        CircularlyLinkedList cllIntervals = new CircularlyLinkedList();
        for (int j = 0; j < halfSteps.length; j++){
            cllIntervals.add(halfSteps[j]);
        }

        char n = root.getName().charAt(0); //get the name family of passed root
        /*
        TODO something like if(quality == triadClassifier.getTriadQualities()[2]) this is majorIntervals
         */
        Integer[] distances = getDistances(); // 3rd family, 5th family

        Integer[] intervals = new Integer[2]; // initialize empty container

        if(triad.getQuality() == triadClassifier.getTriadQualities()[0]){ // diminished
            intervals = getFormulas().get(0);
        }
        else if(triad.getQuality() == triadClassifier.getTriadQualities()[1]){ // minor
            intervals = getFormulas().get(1);
        }
        else if(triad.getQuality() == triadClassifier.getTriadQualities()[2]){ // major
            intervals = getFormulas().get(2);
        }
        else if(triad.getQuality() == triadClassifier.getTriadQualities()[3]){ // augmented
            intervals = getFormulas().get(3);
        }

        try {
            Object thrdName = cllNames.distance(Character.toString(n), distances[0]);
            Object fifthName = cllNames.distance(Character.toString(n), distances[1]);

            Map<Integer, ArrayList<String>> notesMap = root.getNotesMap();

            //int thrdIntValue = root.getIntValue() + majorIntervals[0];
            Integer thrdIntValue = (Integer) cllIntervals.distance(root.getIntValue(), intervals[0]);
            Integer fifthIntValue = (Integer) cllIntervals.distance(root.getIntValue(), intervals[1]);

            ArrayList<String> thirdFamily = notesMap.get(thrdIntValue);
            ArrayList<String> fifthFamily = notesMap.get(fifthIntValue);


            for (int i = 0; i < thirdFamily.size(); i++){
                if(thirdFamily.get(i).contains((String) thrdName)){
                    thrdName = thirdFamily.get(i);
                }
            }
            Note third = new Note((String)thrdName);
            triad.setThird(third);


            for (int j = 0; j < fifthFamily.size(); j++){
                if(fifthFamily.get(j).contains((String) fifthName)){
                    fifthName = fifthFamily.get(j);
                }
            }
            Note fifth = new Note((String)fifthName);
            triad.setFifth(fifth);


        }catch (Exception e){
            System.out.println(e);
        }
        return triad;
    }

    /**
     * The purpose of this method is to return the distances Integer[] of this
     * class
     */
    public Integer[] getDistances() {
        return distances;
    }

    public Integer[] getMajorIntervals() {
        return majorIntervals;
    }

    /**
     * The purpose of this method is to return the formulas ArrayList
     * of Integers of this class
     */
    public ArrayList<Integer[]> getFormulas() {
        return formulas;
    }
}
