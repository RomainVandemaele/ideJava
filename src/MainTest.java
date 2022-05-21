import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void mergeArrays() {
        Integer[] tab = {1,34,-1,45,5,1,-85,-4,10};
        Integer[] newTab = Main.mergeSort(tab,0,tab.length-1);
        assertEquals(-85,newTab[0]);
        assertEquals(45,newTab[newTab.length-1]);
    }

    @org.junit.jupiter.api.Test
    void mergeSort() {
    }

    @org.junit.jupiter.api.Test
    void insertionSort() {
    }

    @org.junit.jupiter.api.Test
    void insertionSortV2() {
    }
}