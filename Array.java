import java.util.Random;

/**
 * The Array class keeps track of up to 100 DVDs in an inventory. 
 */
public class Array {

    /**
     * dvd_array contains all the DVDs currently stored in the inventory.
     */
    private DVD[] dvd_array;

    /**
     * array_size is the current number of DVD's in the inventory.
     */
    private int array_size;

    /**
     * Construct an array object.
     */
    public Array() {
        dvd_array = new DVD[100];
        array_size = 0;
    }

    /**
     * Insert a DVD into the inventory.
     * 
     * @param o - A DVD to insert into the array.
     * @return - True if the DVD is successfully inserted, false otherwise.
     */
    public boolean insert(Object o) {
        if ((o == null) || !(o instanceof DVD) || (array_size > 99)) {
            return false;
        }
        DVD dvd = (DVD) o;
        dvd_array[array_size] = dvd;
        array_size++;
        return true;
    }

    /**
     * Delete a DVD from the inventory.
     * 
     * @param o - A DVD to remove from the array.
     * @return - True if the DVD is successfully removed, false otherwise.
     */
    public boolean delete(Object o) {
        if ((o == null) || !(o instanceof DVD)) {
            return false;
        }
        DVD dvd = (DVD) o;
        for (int i = 0; i < array_size; i++) {
            if (dvd_array[i].equals(dvd)) {
                shiftDVDs(i);
                array_size = array_size - 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Find a DVD into the inventory.
     * 
     * @param title - The name of the DVD the user is looking for.
     * @return - If the DVD is in the array, the DVD is returned, otherwise null.
     */
    public DVD search(String title) {
        for (int i = 0; i < array_size; i++) {
            if (dvd_array[i].name.equals(title)) {
                return dvd_array[i];
            }
        }
        return null;
    }

    /**
     * Sort the DVD's in the inventory in alphabetically increasing order by title.
     */
    public void sort() {
        mergeSort(dvd_array, 0, array_size - 1);
    }

    /**
     * Number of DVD's in the inventory.
     * 
     * @return - Current number of DVD's in the array.
     */
    public int inventorySize() {
        return array_size;
    }

    /**
     * All DVDs right of the given index shift one to the left.
     * 
     * @param index_removed - index of deleted DVD.
     */
    private void shiftDVDs(int index_removed) {
        int i = index_removed;
        while (i < array_size - 1) {
            dvd_array[i] = dvd_array[i + 1];
        }
    }

    /**
     * Sort DVD's in increasing alphabetical order.
     * 
     * @param dvds - unsorted array of DVDs.
     * @param low - left most index of DVDs to be sorted.
     * @param high - right most index of DVDs to be sorted.
     */
    private void mergeSort(DVD[] dvds, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(dvds, low, mid);
            mergeSort(dvds, mid + 1, high);
            
            merge(dvds, low, mid, high);
        }
    }

    /**
     * Merge two sorted subarrays.
     * First subarray dvd[low, middle]
     * Second subarray dvd[middle + 1, high]
     * 
     * @param dvds - array of DVDs.
     * @param low - left most index of first subarray.
     * @param middle - right most index of first subarray.
     * @param high - right most index of second subarray.
     */
    private void merge(DVD[] dvds, int low, int middle, int high) {
        
        int first_size = middle - low + 1;
        int second_size = high - middle;
        
        /**
         * Save the subarray info before writing over it in the DVD array.
         */
        DVD[] low_half = new DVD[middle - low + 1];
        for (int i  = 0; i < first_size; i++) {
            low_half[i] = dvds[low + i];
        }
        DVD[] high_half = new DVD[high - middle];
        for (int j = 0; j < second_size; j++) {
            high_half[j] = dvds[middle + 1 + j];
        }

        /**
         * Combine the subarrays in sorted order.
         */
        int i = 0, j = 0, k = low;
        while (i < first_size && j < second_size) {
            if (low_half[i].name.compareTo(high_half[j].name) < 1) {
                dvds[k] = low_half[i];
                i++;
            } else {
                dvds[k] = high_half[j];
                j++;
            }
            k++;
        }

        /**
         * Flush the remaining DVDs if we reach the end of one subarray before the other.
         */
        while(i < first_size) {
            dvds[k] = low_half[i];
            i++;
            k++;
        }
        while(j < second_size) {
            dvds[k] = high_half[j];
            j++;
            k++;
        }
    }

    /* A utility function to print array of size n */
    private void printArray()
    {
        int n = array_size;
        for (int i = 0; i < n; ++i)
            System.out.print(dvd_array[i].name + " ");
        System.out.println();
    }

    // private void printGivenArray(DVD[] arr, int low, int size)
    // {
    //     int n = size + low;
    //     for (int i = low; i < n; ++i)
    //         System.out.print(arr[i].name + " ");
    //     System.out.println();
    // }
 
    // Driver code
    public static void main(String args[])
    {

        Array ob = new Array();

        for (int i = 0; i < 100; i++) {
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 18) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            DVD dvd = new DVD(saltStr, 1, "a", "a");
            ob.insert(dvd);
            System.out.println(saltStr);
        }
        System.out.println("Given Array");
        ob.printArray();
 
        ob.sort();
 
        System.out.println("\nSorted array");
        ob.printArray();
    }
}
