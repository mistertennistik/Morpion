import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ParcoursLargeur extends JoueurIA {

static int PROFONDEUR_MAX;

private List<List<Etat>> SDD;
private Etat etatInit;
private List<Action> actionsPossiblesDepuisInit;
private int profondeurCourante;
private Action meilleureAction;
private double meilleureHeuristique;


    /**
     * Constructeur
     *
     * @param nom nom du joueur
     */
    public ParcoursLargeur(String nom) {
        super(nom);
    }

    @Override
    public Action choisirAction(Etat etat) throws Exception {
        profondeurCourante = 0;
        etatInit = etat;
        meilleureHeuristique = -1;
        actionsPossiblesDepuisInit = etatInit.actionsPossibles();
        SDD = new ArrayList<>(actionsPossiblesDepuisInit.size());
        initSdd();
        while(profondeurCourante < PROFONDEUR_MAX){
            ParcoursLargeur();
        }
        return meilleureAction;
    }



    void initSdd(){
        List<Action> actionList = actionsPossiblesDepuisInit;
        Etat etatTmp = etatInit.clone();

        List<Etat> listTmp = new ArrayList<>();
        for (Action action : actionList) {
            etatTmp.jouer(action);
            listTmp.add(etatTmp);
            SDD.add(listTmp);
        }

    }
        /**
         * Affiche l'arbre selon un parcours en largeur
         */
        public void ParcoursLargeur() {
            profondeurCourante++;
            List<Action> actionList;
            List<Etat> listTmp;
            Etat etatTmp;
            double hTemp;
            for (List<Etat> list : SDD) {
                listTmp = new ArrayList<>();
                for (Etat etat : list) {
                    actionList = etat.actionsPossibles();

                    for (Action action : actionList) {
                        etatTmp = etat.clone();
                        etatTmp.jouer(action);
                        hTemp = heuristique(etatTmp);
                        if(hTemp > meilleureHeuristique){
                            meilleureHeuristique = hTemp;
                            meilleureAction = actionsPossiblesDepuisInit.get(SDD.indexOf(list));
                        }
                        listTmp.add(etatTmp);
                    }
                    SDD.add(SDD.indexOf(list),listTmp);


                }

            }

        }


    double heuristique(Etat e){

        return 0.0;
    }

}
