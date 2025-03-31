import java.util.Scanner;
public class B6_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the height of the triangle: ");
        int n = input.nextInt();
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n - i; j++){
                System.out.print(" ");
            }
            for (int k = 1; k <= 2*i - 1; k++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.exit(0);
    }
}
