import java.io.FileNotFoundException;

public class main {
    public static void main(String args[]) throws FileNotFoundException {
        GaussJordan g =  new GaussJordan(2,5);
        g.nacitajMaticuZoSuboru("C:\\Users\\Daniel\\IdeaProjects\\Gauss-JordanElimination\\src\\NekonecneVela.txt");
        g.GaussJordan();
    }
}
