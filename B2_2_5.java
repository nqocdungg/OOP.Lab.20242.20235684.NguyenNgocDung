import javax.swing.JOptionPane;
public class B2_2_5 {
    public static void main(String[] args){
        String strnum1, strnum2;
        strnum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
        strnum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
        double num1 = Double.parseDouble(strnum1);
        double num2 = Double.parseDouble(strnum2);
        double sum = num1 + num2;
        double diff = Math.abs(num1-num2);
        double product = num1 * num2;
        String Noti;
        if (num2 == 0) Noti = "Not valid";
        else Noti = (double)num1/num2 + "";
        JOptionPane.showMessageDialog(null, "Sum: " + sum + "\nDifference: " + diff + "\nProduct: " + product + "\nQuotient: " + Noti, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}