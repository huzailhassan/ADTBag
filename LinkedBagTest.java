import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LinkedBagTest {
    
    public static final String[] TEST_STRING_ARRAY_1 = {"a", "b", "c", "d", "e"};
    public static final String[] TEST_STRING_ARRAY_2 = {"F", "G", "H", "I", "J"};
    
    public static void main(String[] args) {
    }

    @Test
    public void union_NoCommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.union(bag2);
        assertEquals(expectedOutput, newBag);
    }

    @Test
    public void union_CommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.union(bag2);
        assertEquals(expectedOutput, newBag);
    }

    @Test
    public void intersection_NoCommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
        }
        BagInterface<String> bag2 = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
        }
        BagInterface<String> newBag = bag1.intersection(bag2);
        assertEquals(expectedOutput, newBag);
    }

    @Test
    public void intersection_CommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.intersection(bag2);
        assertEquals(expectedOutput, newBag);
    }

    @Test
    public void difference_NoCommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            //Expected output should be the first bag because there were no related in bag2. 
            //So add the 5 from first array
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
        }
        assertEquals(expectedOutput, bag1);
    }

    @Test
    public void difference_CommonElements() {
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> expectedOutput = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            //All are related so at the end, all will be gone. 
            //Do not add expected output
            bag1.add(s);
        }
        BagInterface<String> bag2 = new LinkedBag<>();
        for (String s : TEST_STRING_ARRAY_1) {
            //Do not add expected output
            bag2.add(s);
        }
        BagInterface<String> newBag = bag1.difference(bag2);
        assertEquals(expectedOutput, newBag);
    }

}
