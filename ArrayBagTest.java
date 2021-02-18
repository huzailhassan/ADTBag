import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArrayBagTest {
    
    public static final String[] TEST_STRING_ARRAY_1 = {"a", "b", "c", "d", "e"};
    public static final String[] TEST_STRING_ARRAY_2 = {"F", "G", "H", "I", "J"};
    
    public static void main(String[] args) {
//        BagInterface<String> bag1 = new ResizeableArrayBag<String>();
//        bag1.ArrayBag(7);
//        bag1.add("e");
//        bag1.add("d");
//        bag1.add("f");
//        bag1.add("s");
//        bag1.add("c");
//        bag1.add("f");
//        bag1.add("a");
//        BagInterface<String> bag2 = new ResizeableArrayBag<String>();
//        bag2.ArrayBag(6);
//        bag2.add("s");
//        bag2.add("f");
//        bag2.add("g");
//        bag2.add("h");
//        bag2.add("v");
//        bag2.add("a");
//
//        System.out.println(bag2.union(bag1));
        
        // BagInterface<String> bag1 = new ResizeableArrayBag<String>();
        // String[] bag2 = { "a", "b", "c", "d", "e" };
        
        // BagInterface<String> union = new ResizeableArrayBag<String>(bag2)
        // BagInterface<String> everything = bag1.union(bag2);
        // System.out.println(everything);
        
        // BagInterface<String> commonItems = bag1.intersection(bag2);
        // System.out.println(commonItems);
        
        // BagInterface leftOver1 = bag1.difference(bag2);
        // System.out.println(leftOver1);
        
        // BagInterface leftOver2 = bag2.difference(bag1);
        // System.out.println(leftOver2);
        
        // };
        
        
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
    
} 
