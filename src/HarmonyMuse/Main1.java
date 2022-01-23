package HarmonyMuse;
// DO NOT USE 1/22/2022
import java.util.Arrays;

public class Main1 {

    public static void main(String[] args) {

        String[] data = new String[]{"C", "E", "G"}; // simulate input data from Actor
        Note[] notes = new Note[data.length];
        for (int i = 0; i < data.length; i++){
            try{
                notes[i] = new Note(data[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData = new ChordBuilder(notes); // build a chord from input data
        System.out.println("Expect null: " + rawData.getQuality());
        System.out.println(rawData); // calls ChordBuilder.toString()
        MajorTriad majTriad = new MajorTriad(rawData); // chord turns out to be classified as MajorTriad
        System.out.println();
        System.out.println(majTriad);

        Note root =  majTriad.getRoot(); // test MajorTriad.getRoot()
        Note third = majTriad.getThird(); // test MajorTriad.getThird()
        Note fifth = majTriad.getFifth(); // test MajorTriad.getFifth()
        System.out.println();
        System.out.println(root); // calls Note.toString()
        System.out.println(third);
        System.out.println(fifth);

        String[] data1 = new String[]{"a", "c", "e"}; // simulate input data from Actor
        Note[] notes1 = new Note[data1.length];
        for (int i = 0; i < data1.length; i++){
            try{
                notes1[i] = new Note(data1[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData1 = new ChordBuilder(notes1); // build a chord from input data
        MinorTriad minTriad = new MinorTriad(rawData1); // chord turns out to be classified as MinorTriad

        System.out.println();
        System.out.println(minTriad);

        Note minRoot = minTriad.getRoot(); // test MinorTriad.getRoot()
        Note minThird = minTriad.getThird(); // test MinorTriad.getRoot()
        Note minFifth = minTriad.getFifth(); // test MinorTriad.getRoot()
        System.out.println();
        System.out.println("Here's my root: " + minRoot + ", and here's my intValue: " + minRoot.getIntValue());
        System.out.println(minThird);
        System.out.println(minFifth);

        String[] data2 = new String[]{"e-", "g", "b-", "d-"}; // simulate input data
        Note[] notes2 = new Note[data2.length];
        for (int i = 0; i < data2.length; i++){
            try{
                notes2[i] = new Note(data2[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData2 = new ChordBuilder(notes2); // build a chord from input data
        DominantSeventhChord dom = new DominantSeventhChord(rawData2); // turns out to be DominantSeventh Chord

        System.out.println();
        System.out.println(dom);

        // test getter methods for DominantSeventhChord
        Note domRoot = dom.getRoot();
        Note domThird = dom.getThird();
        Note domFifth = dom.getFifth();
        Note domSeventh = dom.getSeventh();

        System.out.println();
        System.out.println(domRoot);
        System.out.println(domThird);
        System.out.println(domFifth);
        System.out.println(domSeventh);


        Integer[] i = new Integer[]{1, 2, 3};
        Integer[] j = new Integer[]{1, 2, 3};
        System.out.println("Testing Integer arrays == : " + (Arrays.equals(i, j)));
    }
}
