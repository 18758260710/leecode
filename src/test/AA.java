package test;

import java.util.Objects;

public class AA extends Car{
    @Override
    void stop() {

    }

    Integer age;

    public AA(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AA aa = (AA) o;
        return Objects.equals(age, aa.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age);
    }

    public static void main(String[] args) {
        int h=0x7aaaffff;
        System.out.println(h);
        int HASH_BITS = 0x7fffffff;
        System.out.println(Integer.toBinaryString(HASH_BITS));
        System.out.println((h ^ (h >>> 16)) & HASH_BITS);
    }
}
