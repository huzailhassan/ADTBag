import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link ResizeableArrayBag}. Uses JUnit5 for testing.
 */
public class ArrayBagTest {
    
    public static final String[] TEST_STRING_ARRAY_1 = {"a", "b", "c", "d", "e"};
    public static final String[] TEST_STRING_ARRAY_2 = {"F", "G", "H", "I", "J"};
    
    public static void main(String[] args) {
        System.out.println("Using JUnit5 for testing. See repository readme for details on how to run tests.");
    }
    
    @Test
    public void union_NoCommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(10);
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.union(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }
    
    @Test
    public void union_CommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(10);
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_1) {
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.union(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }
    
    @Test
    public void intersection_NoCommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(10);
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
        }
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
        }
        BagInterface<String> newBag = bag1.intersection(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }
    
    @Test
    public void intersection_CommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(10);
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            bag2.add(s);
            expectedOutput.add(s);
        }
        BagInterface<String> newBag = bag1.intersection(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }

    @Test
    public void difference_NoCommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_1) {
            bag1.add(s);
            //Expected output should be the first bag because there were no related in bag2. 
            //So add the 5 from first array
            expectedOutput.add(s);
        }
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_2) {
            bag2.add(s);
        }
        BagInterface<String> newBag = bag1.difference(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }

    @Test
    public void difference_CommonElements() {
        BagInterface<String> bag1 = new ResizeableArrayBag<>(5);
        BagInterface<String> expectedOutput = new ResizeableArrayBag<>(0);
        for (String s : TEST_STRING_ARRAY_1) {
            //All are related so at the end, all will be gone. 
            //Do not add expected output
            bag1.add(s);
        }
        BagInterface<String> bag2 = new ResizeableArrayBag<>(5);
        for (String s : TEST_STRING_ARRAY_1) {
            //Do not add expected output
            bag2.add(s);
        }
        BagInterface<String> newBag = bag1.difference(bag2);
        assertArrayEquals(expectedOutput.toArray(), newBag.toArray());
    }
    
} 
