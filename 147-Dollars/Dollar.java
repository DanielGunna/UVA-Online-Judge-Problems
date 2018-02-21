import java.util.Scanner;
 
public class Dollar {
 
    private static int currency[] = { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5 };
 
    public static int ways_number;
 
    public static void calculate(int value, int pos) {
        if (value == 0) {
            ways_number++;
        } else {
            for (int i = pos; i < currency.length; i++) {
                if (value >= currency[i]) {
                    calculate(value - currency[i], i);
                }
            }
        }
    }
 
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        while (stdin.hasNextLine()) {
            String temp = stdin.nextLine();
            int in = (int) (Double.parseDouble(temp) * 100);
            ways_number = 0;
            if(in == 0)
                break;
            calculate(in, 0);
            System.out.printf("%6s%17d\n", temp, ways_number);
        }
        stdin.close();
    }
}
