package problems;

public class P88_MergeSortedArray {
    //my solution
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i=m+n-1;
        int a=m-1;
        int b=n-1;
        while (i>=0){
            if (a<0){
                nums1[i]=nums2[b];
                b--;
                i--;
            }else if (b<0){
                //没必要
                nums1[i]=nums1[a];
                a--;
                i--;
            }else {
                if (nums1[a]<nums2[b]){
                    nums1[i]=nums2[b];
                    b--;
                    i--;
                }else {
                    nums1[i]=nums1[a];
                    a--;
                    i--;
                }
            }
        }
    }

    //more simple
    public void merge2(int A[], int m, int B[], int n) {
        int i=m-1, j=n-1, k=m+n-1;
        while (i>-1 && j>-1) A[k--]= (A[i]>B[j]) ? A[i--] : B[j--];
        while (j>-1)         A[k--]=B[j--];
    }
}
