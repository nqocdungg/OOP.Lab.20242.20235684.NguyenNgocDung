import javax.swing.JOptionPane;
public class B2_2_6 {
    public static void main (String[] args){
        while (true){
            String choice = JOptionPane.showInputDialog(null, "1.Linear Equation\n2.System Of Equations\n3.Quadratic Equation\n4.Exit\nChooose your option: ", "EQUATION SOLVER", JOptionPane.INFORMATION_MESSAGE);
            int opt = Integer.parseInt(choice);
            switch (opt){
                case 1:
                    LinearEquationSolve();
                    break;
                case 2:
                    SystemOfEquationsSolve();
                    break;
                case 3:
                    QuadraticEquationSolve();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Option is invalid.");
            }
        }
    }
    private static void LinearEquationSolve(){
        double a = Double.parseDouble(JOptionPane.showInputDialog("Input a: "));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Input b: "));
        if (a == 0){
            if (b == 0){
                JOptionPane.showMessageDialog(null, "The equation has infinite solutions.");
            }
            else
            JOptionPane.showMessageDialog(null, "The equation has no solution.");
        }
        else {
            JOptionPane.showMessageDialog(null, "Solution of the equation: x = " + (-b/a));
        }
    }
    private static void SystemOfEquationsSolve(){
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Input a11: "));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Input a12: "));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Input b1: "));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Input a21: "));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Input a22: "));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Input b2: "));
        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;
        if ( D == 0 ){
            if (D1 == 0 & D2 == 0)
                JOptionPane.showMessageDialog(null, "The system has infinite solutions.");
            else 
                JOptionPane.showMessageDialog(null, "The system has no solution.");
        }
        else {
            double x = D1/D;
            double y = D2/D;
            JOptionPane.showMessageDialog(null, "Solution of the system: x = " + x + " and y = " + y);
        }
    }
    private static void QuadraticEquationSolve(){
        double a = Double.parseDouble(JOptionPane.showInputDialog("Input a: "));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Input b: "));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Input c: ")); 
        if (a == 0){
            JOptionPane.showMessageDialog(null,"Invalid! a cannot be 0.");
            return;
        }
        double delta = b*b - 4*a*c;
        if (delta < 0){
            JOptionPane.showMessageDialog(null, "The equation has no solution.");
        }
        else if (delta == 0){
            double x = -b/(2*a);
            JOptionPane.showMessageDialog(null, "The equation has a double root: x = " + x);
        }
        else {
            double x1 = (-b + Math.sqrt(delta))/(2*a);
            double x2 = (-b - Math.sqrt(delta))/(2*a);
            JOptionPane.showMessageDialog(null, "The equation has two solutions: x1 = " + x1 + " and x2 = " + x2);
        }
    }
}