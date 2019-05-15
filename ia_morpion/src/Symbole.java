
// TODO: Auto-generated Javadoc
/**
 * Énumération des symboles possibles pour le jeu
 * Aucun symbole n'a été choisi pour représenter la case vide. Ce cas est représenté par un pointeur nul (choix de conception)
 */
public enum Symbole {
	
	/** Symbole du joueur 1 (ID 0) */
	X("X"),
	
	/** Symbole du joueur 2 (ID 1) */
	O("O"),
	
	/** Symbole du joueur 3 (ID 2) */
	E("E"),
	
	/** Symbole du joueur 4 (ID 3) */
	W("W"),
	
	/** Symbole de la case vide */
	VIDE(" ");
	
	/** Représentation sous la forme d'une chaîne de caractères associée à un symbole */
	private String affichage;
	
	/**
	 * Constructeur
	 *
	 * @param affichage Chaîne de caractères associée au symbole
	 */
	private Symbole(String affichage) {
		this.affichage = affichage;
	}
	
	@Override
	public String toString() {
		return affichage;
	}
}
