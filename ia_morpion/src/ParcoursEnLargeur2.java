import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ParcoursEnLargeur2 extends JoueurIA {


Etat etatInit;
Heuristique calcHeuristique;
int meilleureHeuristique;

//Action meilleureAction;

static int nbAppel = 0;

    public ParcoursEnLargeur2(String nom) throws Exception {
        super(nom);

        calcHeuristique = new Heuristique();
        meilleureHeuristique = -10000;
    }

    public void parcours() throws Exception {
        nbAppel++;
        System.out.println(nbAppel++);
        Etat etatSuivantCourant = etatInit.clone();
        int heuristiqueCourante;

        List<Action> listeDesActionsPossibles = etatInit.actionsPossibles();



        for (Action actionPossible : listeDesActionsPossibles) {
            etatSuivantCourant.jouer(actionPossible);
            heuristiqueCourante = calcHeuristique.calculerGlo(etatSuivantCourant.getPlateau(),Symbole.values()[etatSuivantCourant.getIdJoueurCourant()]);

            if(heuristiqueCourante>= meilleureHeuristique){
                meilleureHeuristique = heuristiqueCourante;
                actionMemorisee = actionPossible;
            }

            etatSuivantCourant = etatInit.clone();
        }


    }

    @Override
    public Action choisirAction(Etat etat) throws Exception {
        etatInit = etat;
        actionMemorisee = etatInit.actionsPossibles().get(0); // au cas où...
        meilleureHeuristique = -10000;
        parcours();
        return actionMemorisee;
    }
}