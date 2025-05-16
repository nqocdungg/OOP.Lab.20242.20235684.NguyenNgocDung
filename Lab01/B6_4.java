import java.util.Scanner;
public class B6_4 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String monthStr;
        int year, month;
        while (true){
            System.out.print("Enter a month: ");
            monthStr = input.next();
            month = MonthToNumber(monthStr);
            if (month != 0) break;
            System.out.print("Invalid input! Please enter again.\n");
        }
        while (true){
            System.out.print("Enter a year: ");
            if (input.hasNextInt()){
                year = input.nextInt();
                if (year >= 1000){
                    System.out.println("Number of days of the month: " + CalculateDays(month, year));
                    break;
                }
            }
            System.out.print("Invalid input! Please enter again.\n");
        }
        System.exit(0);
    }
    private static int MonthToNumber(String m){
        m = m.toLowerCase();
        switch(m){
            case "january": case "jan.": case "jan": case "1": return 1;
            case "february": case "feb.": case "feb": case "2": return 2;
            case "march": case "mar.": case "mar": case "3": return 3;
            case "april": case "apr.": case "apr": case "4": return 4;
            case "may": case "5": return 5;
            case "june": case "jun.": case "jun": case "6": return 6;
            case "july": case "jul.": case "jul": case "7": return 7;
            case "august": case "aug.": case "aug": case "8": return 8;
            case "september": case "sep.": case "sep": case "9": return 9;
            case "october": case "oct.": case "oct": case "10": return 10;
            case "november": case "nov.": case "nov": case "11": return 11;
            case "december": case "dec.": case "dec": case "12": return 12;
            default: return 0;
        }
    }
    private static int CalculateDays(int m, int y){
        switch (m){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: return 31;
            case 4: case 6: case 9: case 11: return 30;
            case 2: 
                if (LeapYear(y) == 1) return 29;
                else return 28;
            default: return 0;
        }
    }
    private static int LeapYear(int y){
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0))
            return 1;
        return 0;
    }
}
