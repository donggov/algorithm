package basic;

public class 최대공약수_유클리드호제법 {

    public static void main(String[] args) {
        System.out.println("1, 1 => " + gcd(1, 1));
        System.out.println("2, 5 => " + gcd(2, 5));
        System.out.println("18, 15 => " + gcd(18, 15));
        System.out.println("24, 36 => " + gcd(24, 36));
        System.out.println("12, 18, 15 => " + gcd(12, gcd(18, 15)));
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
