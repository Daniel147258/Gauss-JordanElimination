import java.io.FileNotFoundException;

public class main {
    public static void main(String args[]) throws FileNotFoundException {
        GaussJordan g =  new GaussJordan(2,5);
        g.nacitajMaticuZoSuboru("C:\\Users\\danie\\IdeaProjects\\GaussJordan elimination\\src\\Priklad1.txt");
        g.GaussJordan();
    }
}
