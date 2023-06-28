public class GaussJordan {
    private int pocetRiadkov;
    private int pocetStlpcov;
    private boolean maRiesenie;
    private int[] indexPivota;
    private double[][] matica;

    public GaussJordan(int pocetRiadkov, int pocetStlpcov) throws ArithmeticException
    {
        if(pocetRiadkov > 0 && pocetStlpcov > 0) {
            this.pocetRiadkov = pocetRiadkov;
            this.pocetStlpcov = pocetStlpcov;
        } else {
                throw  new ArithmeticException("Zadane pocty riadkov a stlpcov neboli vascsie ako 0 ");
        }
        this.matica = new double[this.pocetRiadkov][this.pocetStlpcov];
        this.indexPivota = new int[this.pocetStlpcov];
        this.maRiesenie = true;
    }

    public void GaussJordan ()
    {
        // (i) je stale definovany pre riadok matice
        // (j) je stale definovany pre stlpec matice

        for (int i = 0; i < this.pocetRiadkov; i++){
            int pokracuj = 0;
            boolean nulovyRiadok = true;

            for (int j = 0; j < this.pocetStlpcov; j++){
                if(matica[i][j] != 0){
                    this.indexPivota[i] = j;
                    nulovyRiadok = false;
                    break;
                }
            }

            if(nulovyRiadok){
                this.indexPivota[i] = 0;
            }
            if (this.indexPivota[i] == this.pocetStlpcov - 1){
                this.maRiesenie = false;
                break;
            }
            double pivot = this.matica[i][this.indexPivota[i]];
            if (pivot != 1 && pivot != 0) {
                for (int j = 0; j < this.pocetStlpcov; j++){
                    this.matica[i][j] /= pivot;
                }
            }

            for(pokracuj = i + 1; pokracuj < this.pocetRiadkov; pokracuj++){
                double nasobitel = -this.matica[pokracuj][this.indexPivota[i]];
                for(int j = 0; j < this.pocetStlpcov; j++){
                    this.matica[pokracuj][j] += this.matica[i][j] * nasobitel;
                }
            }
        }
        System.out.println("Matica po uprave:");
        this.VypisMaticu();
        System.out.print("Riesenie matice je nasledovne:");
        this.vyhodnotRiesenie();
    }
    private int pocetNenulovychRiadkov(){
        int nenulove = 0;
        for(int i = 0; i < this.pocetRiadkov; i++){
            for(int j = 0; j < this.pocetStlpcov; j++){
                if(this.matica[i][j] != 0){
                    nenulove++;
                    break;
                }
            }
        }
        return nenulove;
    }
    private void vyhodnotRiesenie(){
        int pocetPremennych = this.pocetStlpcov - 1;
        if(maRiesenie){
            int a = pocetPremennych - this.pocetNenulovychRiadkov();
            if(a == 0){
                System.out.println("Matica ma prave jedno riesenie");
            }
            if(a > 0 ){
                System.out.println("Matica ma nekonecne vela rieseni");
            }
        }
        else {
            System.out.println("Matica nema riesenie ");
        }
    }

    public void VypisMaticu(){
        String a = "";
        for(int i = 0; i < this.pocetRiadkov; i++){
            for(int j = 0; j < this.pocetStlpcov; j++){
                a += String.format("%7.2f", this.matica[i][j]);
                if(j == this.pocetStlpcov - 2){
                    a += "|";
                }
            }
            a += "\n";
            }
        System.out.println(a);
    }

    public void nacitajMaticuZoSuboru(String nazovSuboru) throws java.io.FileNotFoundException
    {
        java.util.Scanner nacitaj = new java.util.Scanner(new  java.io.File(nazovSuboru));
        this.pocetRiadkov = nacitaj.nextInt();
        this.pocetStlpcov = nacitaj.nextInt();
        this.pocetStlpcov++;
        this.matica = new double[this.pocetRiadkov][this.pocetStlpcov];
        for(int i = 0; i < this.pocetRiadkov; i++){
            for(int j = 0; j < this.pocetStlpcov; j++){
                this.matica[i][j] = nacitaj.nextDouble();
            }
        }
        nacitaj.close();
        System.out.println("Nacitana Matica: ");
        this.VypisMaticu();
    }

    public String toString(){
        String matica = "";
        for(int i = 0; i < this.pocetRiadkov; i++) {
            for (int j = 0; j < this.pocetStlpcov; j++) {
                matica += String.format("%7.2f  ", this.matica[i][j]);
            }
            matica += "\n";
        }
        return matica;
    }

    public void nacitajZKlavesnice(){
        java.util.Scanner nacitaj =  new  java.util.Scanner(System.in);
        System.out.println("Zadaj pocet riadkov: ");
        this.pocetRiadkov = nacitaj.nextInt();
        System.out.println("Zadaj pocet Stlpcov: ");
        this.pocetStlpcov = nacitaj.nextInt();
        this.matica = new double[this.pocetRiadkov][this.pocetStlpcov];
        for(int i = 0; i < this.pocetRiadkov; i++) {
            for (int j = 0; j < this.pocetStlpcov; j++) {
                System.out.println("Zadaj cisla pre riadok " + (i+1));
                this.matica[i][j] = nacitaj.nextDouble();
            }
        }
        System.out.println("Nacitana Matica: ");
        this.VypisMaticu();
    }
}
