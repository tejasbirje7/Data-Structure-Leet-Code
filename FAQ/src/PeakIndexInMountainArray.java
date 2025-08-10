/**
 * If arr[mid] < arr[mid + 1], you’re on the ascending slope, so the peak must be on the right.
 *
 * Otherwise, you’re on the descending slope (or at the peak), so move end to mid.
 *
 * When start == end, you’ve found the peak index.
 */
public class PeakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            // If mid is in the increasing part of the mountain
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1; // Peak is to the right
            } else {
                end = mid; // Peak is at mid or to the left
            }
        }
        // start == end, both pointing to the peak
        return start;
    }

    public static void main(String[] args) {
        PeakIndexInMountainArray sol = new PeakIndexInMountainArray();
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 1, 0})); // 1
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 2, 1, 0})); // 1
        System.out.println(sol.peakIndexInMountainArray(new int[]{0, 10, 15, 2})); // 2
    }
}

