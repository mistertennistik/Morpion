public class JoueurIAAetoile extends JoueurIA {


    /**
     * Constructeur
     *
     * @param nom nom du joueur
     */
    public JoueurIAAetoile(String nom) {
        super(nom);
    }



    @Override
    public Action choisirAction(Etat etat) throws Exception {


        return Aetoile(etat);
    }


    public Action Aetoile(Etat e){



        return null;

    }


}
