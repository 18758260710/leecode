package problems;

public class MedianofTwoSortedArrays_4 {

    //my solution
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int num=length1+length2;
        int[] merge = new int[num];
        int i=0, j = 0;
        for (int k = 0; k < (num)/2+1; k++) {
            if (i == length1||(j < length2&&nums1[i]>nums2[j])) {
                merge[k] = nums2[j];
                j++;
            }else if (j == length2||nums1[i]<=nums2[j]) {
                merge[k] = nums1[i];
                i++;
            }
        }
        return num % 2 == 0 ? (double)(merge[num / 2] + merge[num / 2 - 1]) / 2 : merge[num / 2];
    }

    //official solution   二分法找中间值
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) { // to ensure m<=n
            int[] temp = nums1; nums1 = nums2; nums2 = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j-1] > nums1[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && nums1[i-1] > nums2[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = nums2[j-1]; }
                else if (j == 0) { maxLeft = nums1[i-1]; }
                else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = nums2[j]; }
                else if (j == n) { minRight = nums1[i]; }
                else { minRight = Math.min(nums2[j], nums1[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

}
