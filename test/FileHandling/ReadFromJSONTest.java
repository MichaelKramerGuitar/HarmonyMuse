package FileHandling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadFromJSONTest {

    @Test
    void TestReadFromJSON(){
        ReadFromJSON reader = new ReadFromJSON();
        reader.readJSON("test");
    }
}