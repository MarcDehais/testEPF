/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package superpui4;

/**
 *
 * @author cocol
 */

public class Grille {
    private Cellule [][] grille;
    final static int MAXLIGNE = 6;
    final static int MAXCOLONNE = 7;
    
    public Grille (){
        // matrice de 6 lignes et 7 colonnes
        this.grille = new Cellule [MAXLIGNE][MAXCOLONNE];
         for (int i = 0; i < 6; i++){
             for (int j = 0; j < 7; j++){
                 this.grille[i][j] = new Cellule();
             }
         }
    }


    //je propose grille = new matrice()


    //Méthodes    
    
    public boolean ajouterJetonDansColonne(Jeton Jeton,int num_colonne) {// ajoute	 le	 jeton	 dans	 la	 colonne	 ciblée,	 sur	 la	 cellule	vide	la	plus	basse. Renvoie	faux	si	la	colonne	était	pleine.
        int i=0;
        
        for (i=MAXLIGNE-1; i>=0; i--) {
            if (this.getCellule(i, num_colonne).getJetonCourant() == null) {
                this.getCellule(i, num_colonne).setJetonCourant(Jeton); // Grille [i][7] est de classe cellule
            return true;
            }
        }
        System.out.println("la colonne était pleine");
        return false;
    }


    public boolean etreRemplie() { //renvoie	vrai	si	la	grille	est	pleine
        boolean rep = true;

        int i=0;
        int j=0;
        while (j<MAXCOLONNE) {
            while (i<MAXLIGNE) {
                if (this.getCellule(i, j).getJetonCourant() == null) {
                    rep = false;
                }
                i++;                
            }
            i=0;
            j++;
        }
        return rep;
    }


    public void viderGrille() {//vide	la	grille
        int i=0;
        int j=0;
        while (j<MAXCOLONNE) {
            while (i<MAXLIGNE) {
                this.setCellule(i, j,new Cellule());
                i++;
            }
            i=0;
            j++;
        }
    }


    public void afficherGrilleSurConsole() {//fonction d’affichage de la grille sur	la	console. Doit faire apparaitre les couleurs, et les trous noirs.

        for (int i=0; i < MAXLIGNE; i++) { //à rappeler que la ligne du haut de la grille est de coordonées i=0
            for (int j =0;j < MAXCOLONNE; j++){
                if (this.getCellule(i, j).getJetonCourant() != null)
                {
                    System.out.print(this.lireCouleurDuJeton(i, j).substring(0,1));
                }
                else if (! this.getCellule(i, j).isTrouNoir()){
                    System.out.print("X");
                }
                else if (! this.getCellule(i, j).isDesintegrateur()){
                    System.out.print("$");
                }
                else 
                {
                System.out.print("O");
                }
            }
        System.out.println("\n");  
        }
    }


    public Cellule getCellule(int i, int j){
        return this.grille[i][j];
    }


    public void setCellule(int i, int j, Cellule value){
        this.grille[i][j] = value;
    }


    public boolean celluleOccupee(int num_ligne, int num_colonne)	{//renvoie	vrai	si	la	cellule	de	coordonnées	données	est	occupée	 par	un	jeton.
        return this.getCellule(num_ligne,num_colonne).getJetonCourant() != null;
    }


    public String lireCouleurDuJeton(int num_ligne, int num_colonne) {//renvoie	la	couleur	du	jeton	de	la	cellule	ciblée.
        if (this.getCellule(num_ligne,num_colonne).getJetonCourant() != null) {
            return this.getCellule(num_ligne,num_colonne).getJetonCourant().lireCouleur();
        }
        return null;
    }


    public boolean etreGagne() {//renvoie vrai si la grille est gagnante,c’est-à-dire que 4	pions de meme couleur sont alignés en ligne, en colonne	ou en diagonale.
        boolean lig = verifierLignes();
        boolean col = verifierColonnes();
        boolean d1 = verifierD1();
        boolean d2 = verifierD2();
        return lig || col || d1 || d2;
    }


        private  boolean verifierLignes (){
        for(int num_ligne =0; num_ligne<6;num_ligne++){
            
            int num_colonne = 0;

            if (this.lireCouleurDuJeton(num_ligne,num_colonne) != null){
                int l;
                for (int k=0; k <4; k++ ){
                    l = 6-k;
                    if (this.lireCouleurDuJeton(num_ligne,num_colonne+l-0) != null){
                        if (this.lireCouleurDuJeton(num_ligne,num_colonne+l-1) != null && this.lireCouleurDuJeton(num_ligne,num_colonne+l-0).equals(this.lireCouleurDuJeton(num_ligne,num_colonne+l-1))) {
                            if (this.lireCouleurDuJeton(num_ligne,num_colonne+l-2) != null && this.lireCouleurDuJeton(num_ligne,num_colonne+l-1).equals(this.lireCouleurDuJeton(num_ligne,num_colonne+l-2))) {
                                if (this.lireCouleurDuJeton(num_ligne,num_colonne+l-3) != null && this.lireCouleurDuJeton(num_ligne,num_colonne+l-2).equals(this.lireCouleurDuJeton(num_ligne,num_colonne+l-3))) { 
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean verifierColonnes (){

        for(int num_colonne = 0; num_colonne<7;num_colonne++){

            int num_ligne = 0;

            if (this.lireCouleurDuJeton(5,num_colonne) != null){
                int l;
                for (int k=0; k <3; k++ ){
                    l = 5-k;
                    if (this.lireCouleurDuJeton(num_ligne+l-0,num_colonne) != null) {
                        if (this.lireCouleurDuJeton(num_ligne+l-1,num_colonne) != null && this.lireCouleurDuJeton(num_ligne+l-0,num_colonne).equals(this.lireCouleurDuJeton(num_ligne+l-1,num_colonne))){
                            if (this.lireCouleurDuJeton(num_ligne+l-2,num_colonne) != null && this.lireCouleurDuJeton(num_ligne+l-1,num_colonne).equals(this.lireCouleurDuJeton(num_ligne+l-2,num_colonne))){
                                if (this.lireCouleurDuJeton(num_ligne+l-3,num_colonne) != null && this.lireCouleurDuJeton(num_ligne+k+2,num_colonne).equals(this.lireCouleurDuJeton(num_ligne+l-3,num_colonne))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private boolean verifierD1(){
        for(int num_colonne = 0; num_colonne<4;num_colonne++){

            int num_ligne = 0;

            if (this.lireCouleurDuJeton(5,num_colonne) != null){
                int l;
                for (int k=0; k <3; k++ ){
                    l = 5-k;
                    if (this.lireCouleurDuJeton(num_ligne+l-0,num_colonne) != null) {
                        if (this.lireCouleurDuJeton(num_ligne+l-1,num_colonne+1) != null && this.lireCouleurDuJeton(num_ligne+l-0,num_colonne+0).equals(this.lireCouleurDuJeton(num_ligne+l-1,num_colonne+1))){
                            if (this.lireCouleurDuJeton(num_ligne+l-2,num_colonne+2) != null && this.lireCouleurDuJeton(num_ligne+l-1,num_colonne+1).equals(this.lireCouleurDuJeton(num_ligne+l-2,num_colonne+2))){
                                if (this.lireCouleurDuJeton(num_ligne+l-3,num_colonne+3) != null && this.lireCouleurDuJeton(num_ligne+k+2,num_colonne+2).equals(this.lireCouleurDuJeton(num_ligne+l-3,num_colonne+3))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean verifierD2(){
        for(int num_colonne = 3; num_colonne<7;num_colonne++){

            int num_ligne = 0;

            if (this.lireCouleurDuJeton(5,num_colonne) != null){
                int l;
                for (int k=0; k <3; k++ ){
                    l = 5-k;
                    if (this.lireCouleurDuJeton(num_ligne+l-0,num_colonne) != null) {
                        if (this.lireCouleurDuJeton(num_ligne+l-1,num_colonne-1) != null && this.lireCouleurDuJeton(num_ligne+l-0,num_colonne-0).equals(this.lireCouleurDuJeton(num_ligne+l-1,num_colonne-1))){
                            if (this.lireCouleurDuJeton(num_ligne+l-2,num_colonne-2) != null && this.lireCouleurDuJeton(num_ligne+l-1,num_colonne-1).equals(this.lireCouleurDuJeton(num_ligne+l-2,num_colonne-2))){
                                if (this.lireCouleurDuJeton(num_ligne+l-3,num_colonne-3) != null && this.lireCouleurDuJeton(num_ligne+k+2,num_colonne-2).equals(this.lireCouleurDuJeton(num_ligne+l-3,num_colonne-3))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    public void tasserGrille(int num_ligne,int num_colonne) {//lorsqu’un	jeton	est	capturé	ou	détruit,	tasse	la	grille	en	décalant	de	 une	ligne	les	jetons	situés	au	dessus	de	la	cellule	libérée.
        for (int i=0; i < MAXLIGNE; i++) {
            for (int j =0;j < MAXCOLONNE; j++){
                if (this.getCellule(num_ligne,num_colonne).getJetonCourant() == null && this.getCellule(num_ligne,num_colonne+1).getJetonCourant() != null) {
                    this.getCellule(num_ligne,num_colonne).setJetonCourant(this.getCellule(num_ligne,num_colonne+1).getJetonCourant());
                    this.getCellule(num_ligne,num_colonne+1).setJetonCourant(null);
                }
            }
        }
    }


    public boolean colonneRemplie (int num_colonne ){//!!!!! ajouter un paramètre oublié !!!!! renvoie	vrai	si	la	colonne	est	remplie	(on	ne	peut	y	jouer	un	Jeton)
        //revoir codage ligne
        //revoir s'il faut scanner toute la colonne
        return this.getCellule(0,num_colonne).getJetonCourant() != null;
    }


    public boolean placerTrouNoir(int num_ligne, int num_colonne){//ajoute	 un	 trou	 noir	 à	 l’endroit	 indiqué	 et	 retourne	 vrai	 si	 l’ajout	s’est	bien	passé,	ou	faux	sinon	(exemple :	trou	noir	déjà	présent)
        if (this.getCellule(num_ligne,num_colonne).isTrouNoir() != false){
            System.out.println("Il n'y a de place que pour un seul Trou Noir - le TrouNoir ");
            return false;
        }
        else {
            this.getCellule(num_ligne,num_colonne).setTrouNoir(true);  //à changer si plusieurs éléments sur la même case impossible  
            return true;
        }
    }

    public boolean placerDesintegrateur(int num_ligne, int num_colonne){//ajoute	un	désintégrateur	à	l’endroit	indiqué	et	retourne	 vrai	si	l’ajout	s’est	bien	passé,	ou	faux	sinon	(exemple :	désintégrateur	déjà	présent)

        if (this.getCellule(num_ligne,num_colonne).isDesintegrateur() != false){
            System.out.println("Il n'y a de place que pour un seul Désingueur - le Désingueur ");
            return false;
        }
        else {
            this.getCellule(num_ligne,num_colonne).setDesintegrateur(true);  //à changer si plusieurs éléments sur la même case impossible  
            return true;
        }
    }


    public boolean suppression (int num_ligne, int num_colonne) {//	supprime	 le	 jeton	 de	 la	 cellule	 visée.	 Renvoie	 vrai	 si	 la	 suppression	s’est	bien	déroulée,	ou	faux	autrement	(jeton	absent)
        if (this.getCellule(num_ligne,num_colonne).getJetonCourant() != null){
            this.getCellule(num_ligne,num_colonne).setJetonCourant(null);
            System.out.println("Suppression de jeton réuissi");
            return true;
        }
        else {
            return false;
        }  
    }
}



// public Jeton recupererJeton(int num_ligne, int num_colonne) {//enlève	le	jeton	de	la	cellule	visée	et	renvoie	une	référence	vers	ce	jeton.
// //je c pas encore à quoi ça va servir
// //encours d'élaboration

// // Jeton qqc = null; //pour sauvergarder le jeton.courant de la celulle en question
// // qqc = Grille .getCe