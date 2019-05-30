import java.util.List;

public class PaPaIA extends JoueurIA {

    //pour le parcours en Largeur
    Etat etatInit;
    Heuristique calcHeuristique;
    int meilleureHeuristique;

    //pour Alpha-Beta
    static int PROFONDEUR_MAX = 3;

    /**
     * Constructeur
     *
     * @param nom nom du joueur
     */
    public PaPaIA(String nom) throws Exception {
        super(nom);
        calcHeuristique = new Heuristique();
        meilleureHeuristique = -10000;
    }



    @Override
    public Action choisirAction(Etat etat) throws Exception {
        if(etat.getPlateau().getTaille()>3){
            etatInit = etat;
            actionMemorisee = etatInit.actionsPossibles().get(0); // au cas où...
            meilleureHeuristique = -10000;
            parcours();
            return actionMemorisee;
        }else{
            etatInit = etat;
            alphaBeta(etat, -9999,+9999, 0);
            return actionMemorisee;

        }

    }


    public void parcours() throws Exception {

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


    public int alphaBeta(Etat e, int alpha, int beta,int prof){


        if(isTerminal(e)){
            return utilite(e,prof);
        }else{// si l'état n'est pas terminal
            if(isTypeMax(e)){
                //System.out.println("le joueur est "+e.getIdJoueurCourant());
                for(int j=0 ; (j<e.actionsPossibles().size()) && (alpha < beta) && prof<PROFONDEUR_MAX ; j++) {


                    Etat nouvelEtat = e.clone();
                    nouvelEtat.jouer(nouvelEtat.actionsPossibles().get(j));
                    nouvelEtat.setIdJoueurCourant(nouvelEtat.getIdJoueurCourant() + 1);


                    int newAlpha = Math.max(alphaBeta(nouvelEtat, alpha, beta, prof+1), alpha);
                    //System.out.println("new alpha = " + newAlpha + " , alpha = " + alpha);
                    if (newAlpha > alpha) {
                        alpha = newAlpha;
                        if(e.equals(etatInit)){
                            actionMemorisee = e.actionsPossibles().get(j);
                        }


                    }


                }
                return alpha;
            }else{
                if(isTypeMin(e)){
                    for(int j=0 ; j<e.actionsPossibles().size() && (alpha<beta) && prof<PROFONDEUR_MAX; j++){
                        Etat nouvelEtat = e.clone();

                        nouvelEtat.jouer(nouvelEtat.actionsPossibles().get(j));
                        nouvelEtat.setIdJoueurCourant(nouvelEtat.getIdJoueurCourant()+1);


                        beta  = Math.min(alphaBeta(nouvelEtat, alpha, beta, prof+1),beta);
                    }
                    return beta;
                }
            }
        }

        return -12; // inutile en réalité
    }

    private boolean isTerminal(Etat e) {
        return !(e.situationCourante() instanceof EnCours);
    }


    private boolean isTypeMin(Etat e) {
        return !(e.getIdJoueurCourant()== this.getID());
    }

    private boolean isTypeMax(Etat e) {

        return e.getIdJoueurCourant()== this.getID();
    }


    private int utilite(Etat e, int prof){
        Situation situation = e.situationCourante();
        if(situation instanceof Victoire){
            if (((Victoire) situation).getVainqueur().equals(this)){
                return 10*(PROFONDEUR_MAX-prof);
            }
        }else if(situation instanceof Egalite){
            return 0;
        }
        return -10*(PROFONDEUR_MAX-prof);


    }




}
