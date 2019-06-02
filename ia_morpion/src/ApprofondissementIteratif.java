public class ApprofondissementIteratif extends JoueurIA {


    Action meilleureAction = null;
    Etat etatInit= null;

     int PROFONDEUR_MAX ;
     boolean flag = true;

    /**
     * Constructeur
     *
     * @param nom nom du joueur
     */
    public ApprofondissementIteratif(String nom) {
        super(nom);
    }

    @Override
    public Action choisirAction(Etat etat) throws Exception {
        etatInit = etat;
        meilleureAction=null;
        PROFONDEUR_MAX=0;
        while(flag){
            alphaBeta(etat, -9999,+9999, 0);
            actionMemorisee = meilleureAction;
            PROFONDEUR_MAX++;
        }

        return actionMemorisee;
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
                            meilleureAction = e.actionsPossibles().get(j);
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
