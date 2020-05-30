public class City {
    private int x, y;
    private int id;
    private int visited;

    public City(int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.visited = 0;
    }

    public City() {

    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
