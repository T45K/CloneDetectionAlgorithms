class Type1 {
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

    public void method2(int i) {
        if (i % 15 == 0) { System.out.println("fizzbuzz"); }
        else if (i % 3 == 0) { System.out.println("fizz"); }
        else if (i % 5 == 0) { System.out.println("buzz"); }
        else { System.out.println(i); }
    }
}
