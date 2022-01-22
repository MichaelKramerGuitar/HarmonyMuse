package HarmonyMuse;

public class Main {

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
        Chord chord = rawData.classify(rawData);
        System.out.println(chord);

        String[] data1 = new String[]{"f", "a-", "d-"}; // simulate input data from Actor
        Note[] notes1 = new Note[data1.length];
        for (int i = 0; i < data1.length; i++){
            try{
                notes1[i] = new Note(data1[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData1 = new ChordBuilder(notes1); // build a chord from input data
        Chord chord1 = rawData1.classify(rawData1);
        System.out.println(chord1);

        String[] data2 = new String[]{"b", "e", "g#"}; // simulate input data from Actor
        Note[] notes2 = new Note[data2.length];
        for (int i = 0; i < data2.length; i++){
            try{
                notes2[i] = new Note(data2[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData2 = new ChordBuilder(notes2); // build a chord from input data
        Chord chord2 = rawData2.classify(rawData2);
        System.out.println(chord2);

        String[] data3 = new String[]{"b", "e", "d"}; // simulate input data from Actor
        Note[] notes3 = new Note[data3.length];
        for (int i = 0; i < data3.length; i++){
            try{
                notes3[i] = new Note(data3[i]);
            } catch (IllegalArgumentException e){
                System.out.println(e);
            }
        }
        ChordBuilder rawData3 = new ChordBuilder(notes3); // build a chord from input data
        Chord chord3 = rawData3.classify(rawData3);
        System.out.println(chord3);
    }

}
