package application.controller.utils;

public class Sum {
    private int a;
    private int b;
    private int sum;

    public Sum(int a, int b) {
        this.a = a;
        this.b = b;
        this.sum = a + b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
