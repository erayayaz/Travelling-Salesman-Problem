import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    ArrayList<Integer> cityID = new ArrayList<Integer>();
    ArrayList<Integer> cityX = new ArrayList<Integer>();
    ArrayList<Integer> cityY = new ArrayList<Integer>();
    public static void main(String[] args) throws FileNotFoundException {
	    reading();
    }
    public static void reading() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        
        while (sc.hasNextLine()) {
            Scanner src = new Scanner(sc.nextLine());
            while (src.hasNext()) {
                if (src.hasNextInt()) {
                    int a=src.nextInt();
                    int b=src.nextInt();
                    int c=src.nextInt();

                } else
                    src.next();

            }
        }
    }
}
