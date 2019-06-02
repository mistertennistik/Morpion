public class PapaIAFinal extends JoueurIA {


    private Action meilleureAction = null;
    private Etat etatInit= null;
    private int PROFONDEUR_MAX ;
    private boolean flag = true;
    private Heuristique calcHeuristique;

    /**
     * Constructeur
     *
     * @param nom nom du joueur
     */
    public PapaIAFinal(String nom) throws Exception {
        super(nom);
        calcHeuristique = new Heuristique();
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




    /**
     * Algo alpha-beta adapté avec une heuristique
     *
     * @param e etat en cours d'évaluation
     * @param alpha
     * @param beta
     * @param prof profondeur de parcours
     */
    public int alphaBeta(Etat e, int alpha, int beta,int prof){


        if(isTerminal(e)){
            return utilite(e);
        }else if(prof==PROFONDEUR_MAX){
            return calcHeuristique.calculerGlo(e.getPlateau(),Symbole.values()[this.getID()]);
        }
        else{// si l'état n'est pas terminal
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

    /**
     * Donne l'utilité d'un état terminal.
     *
     * @param e etat terminal
     */
    private int utilite(Etat e){
        Situation situation = e.situationCourante();
        if(situation instanceof Victoire){
            if (((Victoire) situation).getVainqueur().equals(this)){
                return 10000;
            }
        }else if(situation instanceof Egalite){
            return 0;
        }
        return -10000;


    }


}
