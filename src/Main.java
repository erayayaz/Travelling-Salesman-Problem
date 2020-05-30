import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<City> route = new ArrayList<>();
    static ArrayList<City> cities = new ArrayList<>();


    public static int findNeighbour(City c) {
        int cX = c.getX();
        int cY = c.getY();
        int index = c.getId();
        double minDist = 100000;
        int neighbourIndex = 0;
        int count = 0;
        for (int i = 0; i < cities.size(); i++) {
            if (i == index || cities.get(i).getVisited() == 1)
                continue;
            double distance = Math.sqrt(Math.pow(cX - cities.get(i).getX(), 2) + Math.pow(cY - cities.get(i).getY(), 2));
            if (count == 0) {
                minDist = distance;
                neighbourIndex = i;
                count++;
            } else if (distance < minDist) {
                minDist = distance;
                neighbourIndex = i;

            }
        }
        return neighbourIndex;
    }

    public static void createRoute() {
        route.add(cities.get(0));
        cities.get(0).setVisited(1);
        int i = 0;
        while (route.size() < cities.size()) {
            int nI = findNeighbour(cities.get(i));
            route.add(cities.get(nI));
            cities.get(nI).setVisited(1);
            i = nI;
        }
    }

    public static void reading() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/morsi/IdeaProjects/Travelling-Salesman-Problem/input.txt"));

        while (sc.hasNextLine()) {
            Scanner src = new Scanner(sc.nextLine());
            while (src.hasNext()) {
                if (src.hasNextInt()) {
                    int id = src.nextInt();
                    int x = src.nextInt();
                    int y = src.nextInt();
                    City city = new City(id, x, y);
                    cities.add(city);

                } else
                    src.next();

            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        reading();
        createRoute();
    }
}
