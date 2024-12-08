package mini;

public class MashineEpsilon {
    public static void main(String[] args) {
        double x = 1.0;
        while ((1.0+(x/2))!=1.0){
            x /= 2.0;
            System.out.println(x);
        }
        System.out.println(x);
    }
}
