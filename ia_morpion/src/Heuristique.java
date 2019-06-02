public class Heuristique {


    static int NB_POSSIBILITES_DE_GAGNER_4 = 10;

    int[][][] POSS_POUR_GAGNER = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{1, 0}, {1, 1}, {1, 2}, {1, 3}},
            {{2, 0}, {2, 1}, {2, 2}, {2, 3}},
            {{3, 0}, {3, 1}, {3, 2}, {3, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 1}, {1, 1}, {2, 1}, {3, 1}},
            {{0, 2}, {1, 2}, {2, 2}, {3, 2}},
            {{0, 3}, {1, 3}, {2, 3}, {3, 3}},
            {{0, 0}, {1, 1}, {2, 2}, {3, 3}},
            {{0, 3}, {1, 2}, {2, 1}, {3, 0}}

    };


    int[][] Heuristic_Array = {
            {0, -10, -100, -1000, -10000},
            {1, 0, 0, 0, 0},
            {10, 0, 0, 0, 0},
            {100, 0, 0, 0, 0},
            {1000, 0, 0, 0, 0},
    };

    Plateau plateau;
    Symbole symboleIA;
    int taillePlateau;


    public Heuristique() throws Exception {
    }

    //pas utilisé
    public int evaluer(Etat t) {
        plateau = t.getPlateau();
        taillePlateau = plateau.getTaille();
        int idJoueurCourant = t.getIdJoueurCourant();
        symboleIA = Symbole.values()[idJoueurCourant];

        return 0;
    }

    public int calculerGlo(Plateau plateau, Symbole symboleIA) {
        int i=0,j=0;
        int heuristique=0;
        int taillePlateau = plateau.getTaille();
        while(i+4<=taillePlateau){
            while(j+4<=taillePlateau){
                heuristique = heuristique + calculerPour4(plateau,symboleIA, i,j);
                j++;
            }
            j=0;
            i++;
        }

        return heuristique;
    }

    public int calculerPour4(Plateau plateau, Symbole symbole_joueur_courant, int debutX, int debutY) {
        Symbole player = symbole_joueur_courant;
        Symbole piece;
        int players, others, t = 0;
        int i, j;
        for (i = 0; i < NB_POSSIBILITES_DE_GAGNER_4; i++) {
            players=0;
            others = 0;
            for (j = 0; j < 4; j++) {
                piece = plateau.getCase(POSS_POUR_GAGNER[i][j][0] + debutX, POSS_POUR_GAGNER[i][j][1] + debutY);
                //System.out.println("PIECES : "+ piece);
                if (piece.equals(symbole_joueur_courant)){
                    players++;
                    //System.out.println("PLAYERS = "+players);
            }
                else if (!piece.equals(Symbole.VIDE)) {
                    others++;
                }


            }
            t += Heuristic_Array[players][others];
            //System.out.println("i : "+i+" t : "+t);
        }

        return t;
    }

    //tests
    public static void main(String[] args) throws Exception {
        Plateau p = new Plateau(5);
        Heuristique h = new Heuristique();
        p.setCase(0,0,Symbole.X);
        p.setCase(0,1,Symbole.X);
        p.setCase(0,2,Symbole.X);
        p.setCase(0,3,Symbole.VIDE);
        p.setCase(0,4,Symbole.VIDE);

        p.setCase(1,0,Symbole.VIDE);
        p.setCase(1,1,Symbole.VIDE);
        p.setCase(1,2,Symbole.VIDE);
        p.setCase(1,3,Symbole.VIDE);
        p.setCase(1,4,Symbole.VIDE);

        p.setCase(2,0,Symbole.VIDE);
        p.setCase(2,1,Symbole.VIDE);
        p.setCase(2,2,Symbole.VIDE);
        p.setCase(2,3,Symbole.VIDE);
        p.setCase(2,4,Symbole.VIDE);

        p.setCase(3,0,Symbole.X);
        p.setCase(3,1,Symbole.O);
        p.setCase(3,2,Symbole.O);
        p.setCase(3,3,Symbole.O);
        p.setCase(3,4,Symbole.VIDE);

        p.setCase(4,0,Symbole.X);
        p.setCase(4,1,Symbole.O);
        p.setCase(4,2,Symbole.O);
        p.setCase(4,3,Symbole.O);
        p.setCase(4,4,Symbole.VIDE);


        int tmp = h.calculerGlo(p,Symbole.X);

        System.out.println(p.toString());

        //int tmp =  h.calculerPour4(p,Symbole.X,0,0);
        System.out.println(tmp);
    }
}