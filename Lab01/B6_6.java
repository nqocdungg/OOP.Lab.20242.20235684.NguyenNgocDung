import java.util.Scanner;
public class B6_6{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int r = input.nextInt();
        System.out.print("Enter the number of columns: ");
        int c = input.nextInt();
        int[][] matrix1 = new int[r][c];
        int[][] matrix2 = new int[r][c];
        int[][] matrix_result = new int[r][c];
        System.out.println("Enter matrix 1: ");
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                matrix1[i][j] = input.nextInt();
            }
        }
        System.out.println("Enter matrix 2: ");
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                matrix2[i][j] = input.nextInt();
            }
        }
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                matrix_result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        System.out.println("Result: ");
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                System.out.print(matrix_result[i][j] + " ");
            }
            System.out.println();
        }
        input.close();
        System.exit(0);
    }
}