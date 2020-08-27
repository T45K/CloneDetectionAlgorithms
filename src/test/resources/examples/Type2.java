class Type2 {
    public void method1(int i) {
        if (i % 15 == 0) {
            System.out.println("fizzbuzz");
        } else if (i % 3 == 0) {
            System.out.println("fizz");
        } else if (i % 5 == 0) {
            System.out.println("buzz");
        } else {
            System.out.println(i);
        }
    }

    public void method2(int j) {
        if (j % 15 == 0) {
            System.out.println("fizzbuzz");
        } else if (j % 3 == 0) {
            System.out.println("fizz");
        } else if (j % 5 == 0) {
            System.out.println("buzz");
        } else {
            System.out.println(j);
        }
    }
}
