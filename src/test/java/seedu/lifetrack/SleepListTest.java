package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.sleep.sleeplist.SleepList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepListTest {
    @Test
    public void testDeleteSleepValidIndex(){
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-11-03");
        sleepList.addSleep("sleep add 8 d/2024-12-10");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 1");
        assertEquals(initialSize - 1, sleepList.getSize());
    }
    @Test
    public void testDeleteSleepInvalidIndex() {
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/110324");
        sleepList.addSleep("sleep add 8");
        int initialSize = sleepList.getSize();
        sleepList.deleteSleep("sleep delete 5"); // Index out of bounds
        sleepList.deleteSleep("sleep delete -1");
        assertEquals(initialSize, sleepList.getSize());
    }
    @Test
    public void testPrintSleepListEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t Your sleep list is empty. Add new entries to populate your list :)" +
                lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    public void testPrintSleepListNonEmpty() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-03-11");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testPrintSleepListMultipleEntries() {
        String lineSeparator = System.lineSeparator();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        SleepList sleepList = new SleepList();
        sleepList.addSleep("sleep add 7.5 d/2024-03-11");
        sleepList.addSleep("sleep add 8.0 d/2024-05-28");
        sleepList.addSleep("sleep add 4.2 d/2024-06-15");
        sleepList.printSleepList();
        System.setOut(System.out);
        String expectedOutput = "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Date: 2024-05-28, Duration: 8.0 hours" + lineSeparator +
                "\t The following entry has been added to your sleep list!" + lineSeparator +
                "\t \t Date: 2024-06-15, Duration: 4.2 hours" + lineSeparator +
                "\t Your Sleep List:" + lineSeparator +
                "\t 1. \t Date: 2024-03-11, Duration: 7.5 hours" + lineSeparator +
                "\t 2. \t Date: 2024-05-28, Duration: 8.0 hours" + lineSeparator +
                "\t 3. \t Date: 2024-06-15, Duration: 4.2 hours" + lineSeparator;
        assertEquals(expectedOutput, outputStream.toString());
        assertEquals(3, sleepList.getSize());
    }
}
