package problems;

public class CompareVersionNumbers_165 {
    //my solution1 1ms
    public int compareVersion(String version1, String version2) {
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int al = a.length;
        int bl = b.length;
        for (int i=0;i<Math.min(al,bl);i++){
            int a1= Integer.parseInt(a[i]);
            int b1= Integer.parseInt(b[i]);
            if (a1>b1)return 1;
            if (a1<b1)return -1;
        }
        if (al>bl){
            for (int i=bl;i<al;i++){
                int a1= Integer.parseInt(a[i]);
                if (a1>0)return 1;
            }
        }else {
            for (int i=al;i<bl;i++){
                int b1= Integer.parseInt(b[i]);
                if (b1>0)return -1;
            }
        }
        return 0;
    }

    //0ms
    public int compareVersion2(String version1, String version2) {
        int i1 = 0, i2 = 0;

        while (i1 < version1.length() || i2 < version2.length()) {
            int n1 = 0, n2 = 0;
            while (i1 < version1.length()) {
                char c = version1.charAt(i1);
                i1++;
                if (c == '.') break;
                n1 = n1 * 10 + Character.getNumericValue(c);
            }

            while (i2 < version2.length()) {
                char c = version2.charAt(i2);
                i2++;
                if (c == '.') break;
                n2 = n2 * 10 + Character.getNumericValue(c);
            }

            if (n1 > n2) return 1;
            else if (n1 < n2) return -1;
        }

        return 0;
    }
}
