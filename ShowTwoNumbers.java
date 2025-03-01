import javax.swing.JOptionPane;
public class ShowTwoNumbers {
    public static void main(String[] args){
        String strnum1, strnum2;
        strnum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
        strnum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
        double num1 = Double.parseDouble(strnum1);
        double num2 = Double.parseDouble(strnum2);
        JOptionPane.showMessageDialog(null, "Sum: " + (num1+num2), "Sum");
        if (num2 == 0) JOptionPane.showMessageDialog(null, "Can not divide");
        System.exit(0);
    }
}