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

public class Jeton {

    //Attributs :
     private String couleur; // :    couleur    affectée    au    joueur

    //Méthodes :
    public Jeton (String couleur) {
        //:    constructeur    initialisant    la    couleur    du    jeton avec    le    paramètre
        this.couleur = couleur;
    }

    public String lireCouleur(){
        //:renvoie la    couleur    du    jeton 
        return this.couleur; //je suis dans la classe, je n'ai pas besoin de mettre "Jeton."
    }

    @Override
    public boolean equals(Object e){

        boolean res = false;

        if ((e != null) && (e.getClass() == this.getClass())){
            Jeton j = (Jeton) e;
            if (j.lireCouleur() == null){
                // System.out.println("couleur du jeton k+1 est null");
            }
            else if (this.lireCouleur() == null){
                // System.out.println("couleur du jeton k est null");
            }
            else{
                // System.out.println("la couleur du jeton k+1 est : "+j.lireCouleur());
                // System.out.println("la couleur du jeton k   est : "+this.lireCouleur());
                res = j.lireCouleur().equals(this.lireCouleur());
            }
        }
        return res;
    }
}