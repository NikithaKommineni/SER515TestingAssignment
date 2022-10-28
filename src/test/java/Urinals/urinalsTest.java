//Author:Nikitha
package Urinals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class urinalsTest {
    urinals obj=new urinals();
    @Test
    void main() {
    }

    @Test
    void testgoodString() {
        System.out.println("====== Nikitha Kommineni == TEST ONE EXECUTED =======");
        assertEquals(false,obj.goodString("1100"));
        assertEquals(true,obj.goodString("10010"));
        assertEquals(false,obj.goodString("10a12"));
    }

    @Test
    void testcountUrinals(){
        System.out.println("====== Nikitha Kommineni == TEST TWO EXECUTED =======");
        assertEquals(1,obj.countUrinals("01000"));
        assertEquals(1,obj.countUrinals("10001"));
        assertEquals(3,obj.countUrinals("00000"));
    }

    @Test
    void testopenFile(){
        System.out.println("====== Nikitha Kommineni == TEST THREE EXECUTED =======");
        assertEquals("src/rule.txt",obj.openFile("src/urinals.dat"));
        assertEquals("src/rule1.txt",obj.openFile("src/urinals.dat"));
    }
}