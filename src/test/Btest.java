package test;

import java.util.Comparator;

public class Btest {
    static class mycom implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            return 1;
        }
    }



//    public static void main(String[] args) {
//        TreeMap<Object,String> aa = new TreeMap<Object,String>(new mycom());
//        aa.put(1,"1");
//        aa.put(2,"2");
//        aa.put(3,"3");
//        aa.put(null,"4");
//        aa.put(null,"5");
//        System.out.println(aa);
//        System.out.println(aa.get(2));
//        TreeSet<Integer> a= new TreeSet<>();
//        HashSet<Integer> a= new HashSet<>();
//        for (int i=0;i<800;i+=64){
//            a.add(i);
//
//        }

//        TreeSet<AA> a= new TreeSet<>();
//        AA aa = new AA(1);
//        AA bb = new AA(1);
//        a.add(aa);
//        a.add(bb);
//        System.out.println(a.size());


//        Iterator iterator = a.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
//a.remove(0);
//a.remove(128);
//        Iterator<Integer> iterator = a.iterator();
//        System.out.println(iterator.next());
//        a.remove(3);
//        System.out.println(a);
//    }



        public static void main(String[] args) {
            Integer i1 = 40;
            Integer i2 = 40;
            Integer i3 = 0;
            Integer i4 = new Integer(40);
            Integer i5 = new Integer(40);
            Integer i6 = new Integer(0);

            System.out.println("i1=i2\t" + (i1 == i2));
            System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
            System.out.println("i4=i5\t" + (i4 == i5));
            System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));

            System.out.println();
        }

        public static void  change (String str){
            str = new String("def");
        }

}
