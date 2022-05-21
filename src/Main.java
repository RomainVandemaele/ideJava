public class Main {

    public static Integer[] mergeArrays(Integer[] tab1, Integer[] tab2) {
        int i = 0,j = 0,k =0;
        Integer[] sortedTab = new Integer[tab1.length + tab2.length];

        while ( i<tab1.length && j< tab2.length) {
            if ( tab1[i] <= tab2[j]) {
                sortedTab[k] = tab1[i];
                i++;
            }else {
                sortedTab[k] = tab2[j];
                j++;
            }
            k++;
        }
        while (i < tab1.length) {
            sortedTab[k] = tab1[i];
            i++;k++;
        }
        while (j < tab2.length ) {
            sortedTab[k] = tab2[j];
            j++;k++;
        }

        return sortedTab;
    }

    public static void mergeSortInc(Integer[] tab, int l,int r) {
        final int n = tab.length;
        //tableau de tableau avec pour commencer n tableaux de 1 puis n/2 tableau de  2 ( + 1 de 1) ...
    }
    public static Integer[] mergeSort(Integer[] tab, int l,int r) {
        if( l!=r ) {
            int half = l + (r-l)/2;
            Integer[] tab1 = mergeSort(tab,l,half);
            Integer[] tab2 = mergeSort(tab,half+1,r);

            Integer[] sortedTab = mergeArrays(tab1,tab2);
            return sortedTab;
        }else {
            Integer[] newTab = {tab[l]};
            return newTab;
        }

    }

    public static void insertionSort(Integer[] tab) {
        for ( int i = 1; i < tab.length; i++) {
            Integer number = tab[i];
            int j = i-1;
            while( j >=0 && tab[j] > number) {
                tab[j+1] = tab[j];
                j--;
            }
            tab[j+1] = number;
        }
    }

    public static Integer[] insertionSortV2(Integer[] tab1, Integer[] tab2) {
        final int n1 = tab1.length;
        final int n2 = tab2.length;
        Integer[] tab = new Integer[n1 + n2];
        //int k =0;

        for ( int i = 0; i < tab.length; i++) {
            Integer number;
            if(i < n1 ) {
                number = tab1[i];
            }else {
                number = tab2[i-n1];
            }
            int j = i-1;
            while( j >=0 && tab[j] > number) {
                tab[j+1] = tab[j];
                j--;
            }
            tab[j+1] = number;
        }
        return tab;
    }

    public static void bubbleSort(Integer[] tab) {

        for (int i =0;i< tab.length-1;++i) {
            for (int j=0;j< tab.length-1-i;++j) {
                if( tab[j] > tab[j+1] ) {
                    int temp = tab[j];
                    tab[j] = tab[j+1];
                    tab[j+1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        Integer[] tab = {1,34,-1,45,5,1,-85,-4,10};
        Integer[] tab1 = {1,34,-1,45,5};
        Integer[] tab2 = {1,-85,-4,10};
        mergeSortInc(tab,0,tab.length-1);
        //bubbleSort(tab);
        for (int i=0;i < tab.length;i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println("Hello world!");
    }
}