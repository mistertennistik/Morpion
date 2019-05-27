import java.util.ArrayList;
import java.util.List;

public class JoueurIAAetoile extends JoueurIA {


    List<Etat> OPEN;
    List<Etat> CLOSE;


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
        OPEN = new ArrayList<>();
        OPEN.add(etat);
        CLOSE = new ArrayList<>();
        return null;
    }


    public Action Aetoile(){

        while (!OPEN.isEmpty()){
            Etat X = OPEN.remove(0);
            if(utilite(X)==1){
                //return path from start to X
            }else {
                for (Action actionsPossible : X.actionsPossibles()) {
                    
                }
            }

        }



        return null;

    }



    private int utilite(Etat e){
        Situation situation = e.situationCourante();
        if(situation instanceof Victoire){
            if (((Victoire) situation).getVainqueur().equals(this)){
                return 1;
            }
        }else if(situation instanceof Egalite){
            return 0;
        }
        return -1;


    }


}
