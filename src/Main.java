import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<City> route = new ArrayList<>();
    static ArrayList<City> newRoute = new ArrayList<>();
    static ArrayList<City> cities = new ArrayList<>();
    static int counter=0;
    static double best=0;

    public static void chosenPoint(){

        for(int i=1;i<route.size()-2;i++){
            for(int j=i+1;j<route.size()-1;j++){


                double distance1=(Math.pow(route.get(i).getX()-route.get(i-1).getX(), 2) + Math.pow(route.get(i).getY() - route.get(i-1).getY(), 2))+
                        (Math.pow(route.get(j).getX()-route.get(j+1).getX(), 2) + Math.pow(route.get(j).getY() - route.get(j+1).getY(), 2));

                double distance2=(Math.pow(route.get(j).getX()-route.get(i-1).getX(), 2) + Math.pow(route.get(j).getY() - route.get(i-1).getY(), 2))+
                        (Math.pow(route.get(i).getX()-route.get(j+1).getX(), 2) + Math.pow(route.get(i).getY() - route.get(j+1).getY(), 2));

                newRoute =opt2Swap(route,i,j);
                double newBest =findTotalDistance(newRoute);

                if(newBest<best){
                    route= newRoute;
                    best=newBest;
                    counter++;
                }
            }
        }
    }

    public static ArrayList<City> opt2Swap(ArrayList<City> routes, int i, int j) {
        ArrayList<City> newPath = new ArrayList<>();
        for(int x=0;x<i;x++){
            newPath.add(routes.get(x));
        }
        for(int y=j;y>=i;y--){
            newPath.add(routes.get(y));
        }

        for(int z=j+1;z< route.size();z++){
            newPath.add(route.get(z));
        }
        return newPath;
    }
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
    public static double findTotalDistance(ArrayList<City> route){
        double sum=0;
        for(int i = 0; i<route.size()-1;i++){
            double distance =  Math.sqrt(Math.pow(route.get(i).getX()-route.get(i+1).getX(), 2) + Math.pow(route.get(i).getY() - route.get(i+1).getY(), 2));
            sum +=distance;
        }
        return sum;
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
        Scanner sc = new Scanner(new File("input.txt"));

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
        for(int i=0;i<route.size();i++){
            System.out.print(route.get(i).getId() + "  ");
        }
        /*length with nearest neigbout algorithm*/
        best=findTotalDistance(route);
        System.out.println();
        System.out.println("Length is " + findTotalDistance(route));


        /*Trying 2-opt algorithm*/
        chosenPoint();
        for(int i=0;i<route.size();i++){
            System.out.print(route.get(i).getId() + "  ");
        }
        /*length with 2-opt algorithm*/
        double last = findTotalDistance(route);
        System.out.println();
        System.out.println("Length is " + findTotalDistance(route));
        System.out.println(counter);

    }
}
