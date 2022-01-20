package HarmonyMuse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    Note note;
    Note note1;

    @BeforeEach
    void setUp() {
        Note note = new Note("C#");
        this.note = note;
        Note note1 = new Note("G--");
        this.note1 = note1;

    }

    @Test
    void TestNoteConstructor(){
        Assertions.assertEquals("c#", note.getName());
        Assertions.assertEquals(1, note.getIntValue());
        Assertions.assertEquals("g--", note1.getName());
        Assertions.assertEquals(5, note1.getIntValue());
    }

    @Test
    void TestExceptionThrown(){
        String name = "CC"; // arbitrary Illegal note name
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Note(name);});
        assertEquals("Could not create note from input->>> " + name, thrown.getMessage());

        String name1 = "H"; // arbitrary Illegal note name1
        Exception thrown1 = assertThrows(IllegalArgumentException.class, () -> {
            new Note(name1);});
        assertEquals("Could not create note from input->>> " + name1, thrown1.getMessage());

        String name2 = "@#f"; // arbitrary Illegal note name2
        Exception thrown2 = assertThrows(IllegalArgumentException.class, () -> {
            new Note(name2);});
        assertEquals("Could not create note from input->>> " + name2, thrown2.getMessage());

    }

}
