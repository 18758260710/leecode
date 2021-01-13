package problems;

/**
 * Created by Administrator on 2020/1/21.
 */
public class P223_RectangleArea {
    //相加后减覆盖面积 5ms
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        return (int)((long)(C - A) * (D - B) + (long)(G - E) * (H - F) - Math.max(0, (long)Math.min(C, G) - Math.max(A, E)) * Math.max(0, (long)Math.min(D, H) - Math.max(B, F)));
    }

    public static void main(String[] args) {
        System.out.println(new P223_RectangleArea().computeArea(-1500000001,0,-1500000000,1,1500000000,0,1500000001,1));
    }

    //用判断来写 更详细 3ms
    public int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int reactArea = 0;
        if(A < C && B < D && E < G && F < H) {
            int width = 0;
            int height = 0;
            if(A <= E && E <C) {
                int min = Math.min(C,G);
                width = min - E;
            }else if(E < A && A < G) {
                int min = Math.min(C, G);
                width = min - A;
            }

            if(B <= F && F < D) {
                int min = Math.min(D, H);
                height = min - F;
            }else if(F < B && B < H) {
                int min = Math.min(D, H);
                height = min - B;
            }
            reactArea =  width * height;
        }
        int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
        return totalArea - reactArea;
    }
}
