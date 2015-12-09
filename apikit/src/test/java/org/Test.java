package org;

/**
 * @author zuoge85 on 15/12/8
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(String.class.getName());
        System.out.println(String.class.getSimpleName());


        System.out.println(Abc.class.getName());
        System.out.println(Abc.class.getSimpleName());

        System.out.println(Abc1.class.getName());
        System.out.println(Abc1.class.getSimpleName());

        class Abc2 {

        }

        System.out.println(Abc2.class.getName());
        System.out.println(Abc2.class.getSimpleName());

    }

    private static class Abc {

    }

    private class Abc1 {

    }
}
