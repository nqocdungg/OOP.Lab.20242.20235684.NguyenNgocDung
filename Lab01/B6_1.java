import javax.swing.JOptionPane;
public class B6_1 {
    public static void main(String[] args) {
        int option = JOptionPane.showOptionDialog(null, "Do you want to change to the first class ticket?", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "Yes");
        JOptionPane.showMessageDialog(null, "You've chosen: " + (option == 0 ? "Yes" : "No"));
        System.exit(0);
    }
}
