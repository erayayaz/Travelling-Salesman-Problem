import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	    reading();
    }
    public static void reading() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Cities c = new Cities(5,4,3);
        
        while (sc.hasNextLine()) {
            Scanner src = new Scanner(sc.nextLine());
            while (src.hasNext()) {
                if (src.hasNextInt()) {
                    System.out.println("a :"+src.nextInt());
                    System.out.println("b :"+src.nextInt());
                    System.out.println("c :"+src.nextInt());
                } else
                    src.next();

            }
        }
    }
}
