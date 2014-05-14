/*
 * Copyright 2011 Hichem et Eric Tremblay.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * I N F 2170 TP2-Partie1-Sudoku.
 *
 * Logiciel qui donne trois choix a l'utilisateur:
 * 1- Afficher sa grille de sudoku dans un jolie tableau
 * 2- Verifier si sa grille a ete correctement remplie
 * 3- Decelez et afficher les erreur contenue dans la grille.
 * 
 * @author (Eric Tremblay) 
 * @version (8 mars 2011)
 *
 * (TREE13057906)
 * (tremblay_eric@live.ca)
 */

public class sudoku {

    /**
     * Detecte et affiche les erreurs dans les 9 regions de la grille 9x9(Si la grille ne contient aucune erreur alors une grille d'espace sera affichee).
     * Et tout cela dans un jolie tableau.
     * @param grille : grille de sudoku 9x9 (tableau d'entiers).
     * @param grilleFausse: grille 9x9 remplie d'espace (tableau de String " ").
     * @param ligne :entier qui indique la valeur x (la ligne) ex; (x,y).
     * @param colonne :entier qui indique la valeur y(la colonne) ex: (x,y).
     * @param carreau : entier correspondant a quelle postion de la region est situe le chiffre correspondant au coordonnees (x,y).
     * Evite lebordement de la region(un debordement de la region ne signifie pas necessairement un debordement de la grille).
     * ex:  *---*---*---*  ces 3 groupe 9 chiffres representent 3 region d'une grille, dans chacune des regions(contenant tous les meme series de chiffre) 
     *      |123|123|123|  le chiffre 1 correspond au carreau 0, le chiffre 2 au carreau 1, le chiffre 3 au carreau 2 etc...
     *      |456|456|456|  ce paramatre est essentiel car il sert a eviter les debordement, car pour tester si le chiffre 1 se repete nous devons le comparer
     *      |789|789|789|  avec les chiffre suivant de sa region, par contre certains tests utile pour comparer un chiffre carreau 0 ne seront pas utilisees
     *      *---*---*---*  pour tester d'autre chiffre de carreau different. 
     */
    public static String[][]analyseCase(int grille[][],String grilleFausse[][],int ligne,int colonne,int carreau){

        /*
         * Deux test qui devront etre executes seulement si le carreau est different de 2 et de 5, sinon il y aura debordement de la region(un debordement de la 
         * region provoquera un plantage du programme s'il y a aussi debordement du tableau ou sinon provoquera des erreurs dans les resultats en effectuant
         * des test sur des valeur (x,y) situees en dehors de la region verifiee).
         */
        if(carreau != 2 && carreau != 5){

            /*
             *test: (0,0)vs(0,1),(0,1)vs(0,2),(1,0)vs(1,1),(1,1)vs(1,2),(2,0)vs(2,1),(2,1)vs(2,2)
             *      (0,3)vs(0,4),(0,4)vs(0,5),(1,3)vs(1,4),(1,4)vs(1,5),(2,3)vs(2,4),(2,4)vs(2,5)
             *      ............................................................................
             *      ............................................................................
             *      ....................................................(8,6)vs(8,7),(8,7)vs(8,8)
             */
            if(grille[ligne][colonne]==grille[ligne][colonne + 1]){

                grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                grilleFausse[ligne][colonne + 1] = String.valueOf(grille[ligne][colonne + 1]);

            }

            /*
             * test devant etre effectue lorsque la valeur de carreau est differente de 4 et 7 sinon il y aura debordement.
             */
            if(carreau != 1 && carreau != 4 && carreau != 7){

                /*
                 * test: (0,0)vs(0,2),(1,0)vs(1,2),(2,0)vs(2,2)
                 * ...........................................
                 * ..................,(7,6)vs(7,8),(8,6)vs(8,7)
                 */
                if(grille[ligne][colonne]==grille[ligne][colonne + 2]){

                    grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                    grilleFausse[ligne][colonne + 2] = String.valueOf(grille[ligne][colonne + 2]);

                }

            }

        }

        /*
         * ps: pour le reste de la methode les conditions de debordement seront signalees comme suit : ***eviter debordement***
         *     et quelques specifications seront ajoutees quand au cumulatif des conditions precedentes lorsque juge necessaire(lorsque les if
         *     sont tres long et qu'il faut remonter beaucoup plus haut pour voir les autres conditions.).
         */

        if(carreau != 6 && carreau!= 7){

            //***eviter debordement***
            if(carreau == 1 || carreau == 2 || carreau == 4 || carreau == 5){

                //***eviter debordement***
                if(carreau == 2 || carreau == 5){

                    /*
                     * test: (0,2)vs(1,0),(1,2)vs(2,0)
                     *       (0,5)vs(1,3),(1,5)vs(2,3)
                     *        .......................
                     *       (6,8)vs(7,6),(7,8)vs(8,6)
                     */
                    if(grille[ligne][colonne]==grille[ligne + 1][colonne - 2]){

                        grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                        grilleFausse[ligne + 1][colonne - 2] = String.valueOf(grille[ligne + 1][colonne - 2]);

                    }

                }

                /*tests: (0,1)vs(1,0),(0,2)vs(1,1),(1,1)vs(2,0),(1,2)vs(2,1)
                 *        .................................................
                 *        .................................................
                 *       (6,7)vs(7,6),(6,8)vs(7,7),(7,7)vs(8,6),(7,8)vs(8,7)
                 */
                if(grille[ligne][colonne]==grille[ligne + 1][colonne - 1]){

                    grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                    grilleFausse[ligne + 1][colonne - 1] = String.valueOf(grille[ligne + 1][colonne - 1]);

                }

            }

            /*
             * test: toutes les coordonnees permise dans le if (donc carreau 6 et 7 sont exclus) avec la coordonnees de la meme colonne sur la ligne suivante.
             * ex:(0,0)vs(1,0),(0,1)vs(2,1).
             */
            if(grille[ligne][colonne]==grille[ligne + 1][colonne] ){

                grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                grilleFausse[ligne + 1][colonne] = String.valueOf(grille[ligne + 1][colonne]);

            }

            /*
             * ***eviter debordement***
             * *** on entre si carreau != 5,6,7.
             */
            if(carreau != 5){

                //***eviter debordement***
                if(carreau != 2){

                    /*
                     * test: (0,0)vs(1,1),(0,1)vs(1,2)
                     *        .......................
                     *       (7,6)vs(8,7),(7,7)vs(8,8)
                     */
                    if(grille[ligne][colonne]==grille[ligne + 1][colonne + 1]){

                        grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                        grilleFausse[ligne + 1][colonne + 1] = String.valueOf(grille[ligne + 1][colonne + 1]);

                    }

                }

                /* 
                 * ***eviter debordement***
                 * *** on entre si carreau != 4,5,6,7.
                 */
                if(carreau != 4){

                    /*
                     * ***eviter debordement***
                     * *** on entre si carreau != 1,2,4,5,6,7.
                     */
                    if(carreau != 1 && carreau != 2){

                        /*
                         * test: (0,0)vs(1,2),(1,0)vs(2,2)
                         *        .......................
                         *       (6,6)vs(7,8),(7,6)vs(8,8)
                         */
                        if(grille[ligne][colonne]==grille[ligne + 1][colonne + 2]){

                            grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                            grilleFausse[ligne + 1][colonne + 2] = String.valueOf(grille[ligne + 1][colonne + 2]);
                        }

                    }

                    /* 
                     * ***eviter debordement***
                     */
                    if(carreau == 1 || carreau == 2){

                        /* 
                         * ***eviter debordement***
                         */
                        if(carreau ==2){

                            /*
                             * test: (0,2)vs(2,0),(0,5)vs(2,3),(0,7)vs(2,5)
                             *        ....................................
                             *       (6,2)vs(8,0),(6,5)vs(8,3),(6,8)vs(8,6)
                             */
                            if(grille[ligne][colonne]==grille[ligne + 2][colonne - 2]){

                                grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                                grilleFausse[ligne + 1][colonne - 2] = String.valueOf(grille[ligne + 1][colonne - 2]);

                            }

                        }

                        /*
                         * test: (0,1)vs(2,0),(0,2)vs(2,1),(0,4)vs(2,3),(0,5)vs(2,4)(0,7)vs(2,6),(0,8)vs(2,7)
                         *       (3,1)vs(5,0),(3,2)vs(5,1)(3,4)vs(5,3),(3,5)vs(5,4),(3,7)vs(5,6),(3,8)vs(5,7)
                         *       (6,1)vs(8,0),(6,2)vs(8,1),(6,4)vs(8,3),(6,5)vs(8,4)(6,7)vs(8,6),(6,8)vs(8,7)
                         */
                        if(grille[ligne][colonne]==grille[ligne + 2][colonne - 1]){

                            grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                            grilleFausse[ligne + 1][colonne - 1] = String.valueOf(grille[ligne + 1][colonne - 1]);

                        }

                    }

                    /*
                     * ***eviter debordement***
                     * ***Onn entre si carreau != 3,4,5,6,7
                     */
                    if(carreau != 3){

                        if(grille[ligne][colonne] == grille[ligne + 2][colonne]){

                            grilleFausse[ligne][colonne] = String.valueOf(grille[ligne + 2][colonne]);
                            grilleFausse[ligne + 2][colonne] = String.valueOf(grille[ligne + 2][colonne]);

                        }

                        /*
                         * ***eviter debordement***
                         * ***on entrera que lorsque carreau egallera les valeurs 0,1,.
                         */
                        if(carreau != 2){

                            /*
                             * test: (0,0)vs(2,1),(0,1)vs(2,2),(0,3)vs(2,4),(0,4)vs(2,5),(0,6)vs(2,7),(0,7)vs(2,8)
                             *        .................................................
                             *       (6,0)vs(8,1),(6,1)vs(8,2),(6,3)vs(8,5),(6,4)vs(8,5),(6,6)vs(8,7),(6,7)vs(8,8)
                             */
                            if(grille[ligne][colonne]==grille[ligne + 2][colonne + 1]){

                                grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                                grilleFausse[ligne + 2][colonne + 1] = String.valueOf(grille[ligne + 2][colonne + 1]);

                            }

                            /*
                             * ***eviter debordement***
                             * ***on entre seulement lorsque carreau == 0.
                             * test: (0,0)vs(2,2),(0,3)vs(2,5),(0,6)vs(2,8)
                             *       (3,0)vs(5,2),(3,3)vs(5,5),(3,6)vs(5,8)
                             *       (6,0)vs(8,2),(6,3)vs(8,5),(6,6)vs(8,8)
                             */
                            if(carreau != 1){

                                if(grille[ligne][colonne]==grille[ligne + 2][colonne + 2]){

                                    grilleFausse[ligne][colonne] = String.valueOf(grille[ligne][colonne]);
                                    grilleFausse[ligne + 2][colonne + 2] = String.valueOf(grille[ligne + 2][colonne + 2]);

                                }

                            }

                        }

                    }

                }

            }

        }

        return grilleFausse;
    }

    public static void main (String[] params) {
        /*
         * Declaration du tableau et des variables.
         * grille-----------------------------> une matrice 9x9 fesant office de grille de sudoku.
         * ligneString------------------------> variable qui contiendra les serie de chiffres entrees au clavier (une a la fois)
         * choix -----------------------------> le choix du menu.
         * compteurLigne et compteurColonne---> deux cumulateur qui permettront de determiner quand inserer un '|'.
         * 
         */
        int[][]grille = new int[9][9];
        String ligneString ="";
        int choix;
        int compteurLigne = 0;
        int compteurColonne = 0;

        /*
         * Message d'invite et debut du programme.
         */

        System.out.println("Entrer 9 ligne de 9 chiffres  en prenant soins d'appuyer sur enter a la fin de chaque ligne.");

        /*
         * Boucle for qui demandera a lutilisateur d'entrer une serie de 9 chiffres (compris de 1 a 9), cette demande sera effectuee 9 fois.
         */

        for(int i = 0; i < grille.length;i++){
            
            do{
                
                ligneString = Clavier.lireString();
                /*
                 * On peut entrer ce que l'on veut dans ce Clavier.lireString et il ne plantera pas, par contre si autre chose que des chiffres sont entrer 
                 * la sondittion suivante(while) va planter.
                 */
            }while(Integer.parseInt(ligneString) > 999999999 || Integer.parseInt(ligneString) < 111111111 || Integer.parseInt(ligneString) < 0);
            
            for(int j = 0; j < grille.length;j++){

                /*
                 * cette boucle commence par extraire le charactere du string en position i , transforme ce caractere en String (temporaire),
                 * pour ensuite re-convertir ce String(temporaire) en entier afin de pourvoir le placer dans la grille de sudoku.
                 */

                String temporaire = Character.toString(ligneString.charAt(j));
                grille[i][j] = Integer.parseInt(temporaire);

            }

        }

        /*
         * Affichage du menu et selection du choix.
         */

        System.out.println("Faite votre choix");
        System.out.println("1- Afficher la grille dans un tableau");
        System.out.println("2- Verifier la grille");
        System.out.println("3- Afficher les chiffres conflictuels");

        do{
            
            choix = Clavier.lireInt(); //Clavier.lireInt provoquera un crash du logiciel si autre chose qu'un entier est entre.

        }while(choix != 1 && choix != 2 && choix != 3);
        /*
         * lorsque l'utilisateur choisit d'afficher la grille. 
         */
        if(choix == 1){

            System.out.println("+---+---+---+");
            /*
             * Apres avoir insere l'entete (+---+---+---+), la boucle suivante determine a quels endroits les symboles '|' et les autres entete doivent 
             * etre inseres.
             */
            for(int i = 0; i < grille.length;i++){
                /*
                 *A chaque 3 ligne une autre "entete" est place (que j'appelle entete meme si certaines d'entre elles seront situees dans la grille).
                 */
                if(compteurLigne == 3){
                    System.out.println("+---+---+---+");
                    compteurLigne = 0;
                }

                //insertion du premier symbole '|' d'une ligne.
                System.out.print("|");
                compteurColonne = 0;

                for (int j = 0; j < grille.length;j++){
                    // Un symbole '|' est insere a chaque 3 colonne (compteur de colonne == 3) et remise a zero du compteur.
                    if(compteurColonne == 3){
                        System.out.print("|");
                        compteurColonne = 0;
                    }
                    System.out.print(grille[i][j]);//Affichage de l'entier en postion (i,j).
                    ++compteurColonne;
                }

                ++compteurLigne;//servira pour le if au debut de la grosse boucle.
                System.out.println("|");//Insertion d'un symbole '|' en fin de ligne.
            }

            System.out.println("+---+---+---+");//Affichage de la derniere entete.

            /*
             * Lorsque l'utilisateur a choisit de verifier si la grille a ete correctement remplie.
             */

        }else if (choix == 2 || choix == 3){

            String[][]grilleFausse = new String [9][9];

            //cette boucle for remplie le tableau grilleFausse avec des espaces.
            for(int i = 0; i < grilleFausse.length; i++){

                for(int j = 0; j < grilleFausse.length;j ++){

                    grilleFausse[i][j] = " ";
                }

            }

            /* *******************************************************************************************************************************************
             * 
             *                                                      ANALYSE DES LIGNES DE LA GRILLE.
             * 
             * ********************************************************************************************************************************************
             */

            /*
             * La grosse boucle for qui suit renferme 9 petites boucles for.
             * La grande gere un qui 'i' indique la ligne qui est traitee.
             * chaques petites boucles gere un 'j' qui represente les colonnes suivants la colonne traitee par la boucle (la colonne traitee apparait dans 
             * les conditions du if imbrique dans la petote boucle) ex: if(grille[][0] == ...)
             */

            for (int i = 0; i < grille.length;i++){

                //test le chiffre de la ligne i de la colonne 0.
                for(int j = 1;j<grille.length;j++){

                    if(grille[i][0] == grille[i][j]){

                        grilleFausse[i][0] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 1.
                for(int j = 2;j<grille.length;j++){

                    if(grille[i][1] == grille[i][j]){

                        grilleFausse[i][1] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 2.
                for(int j = 3;j<grille.length;j++){

                    if(grille[i][2] == grille[i][j]){

                        grilleFausse[i][2] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 3.
                for(int j = 4;j<grille.length;j++){

                    if(grille[i][3] == grille[i][j]){

                        grilleFausse[i][3] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 4.
                for(int j = 5;j<grille.length;j++){

                    if(grille[i][4] == grille[i][j]){

                        grilleFausse[i][4] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 5.
                for(int j = 6;j<grille.length;j++){

                    if(grille[i][5] == grille[i][j]){

                        grilleFausse[i][5] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 6.
                for(int j = 7;j<grille.length;j++){

                    if(grille[i][6] == grille[i][j]){

                        grilleFausse[i][6] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 7.
                for(int j = 8;j<grille.length;j++){

                    if(grille[i][7] == grille[i][j]){

                        grilleFausse[i][7] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test le chiffre de la ligne i de la colonne 8.
                for(int j = 7;j > 0;j--){

                    if(grille[i][8] == grille[i][j]){

                        grilleFausse[i][8] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

            }

            /*
             * ********************************************************************************************************************************************
             * 
             *                                                      ANALYSE DES COLONNES DE LA GRILLE.
             * 
             * ********************************************************************************************************************************************
             */

            /*
             * L'analyse des colonnes de la grille s'effectue de facon similaire a celle des ligne (voir plus haut).
             */

            //grande boucle ou j represente la colonne traitee.
            for (int j = 0; j < grille.length;j++){

                //test la ligne 0 de la colonne j.
                for(int i = 1;i<grille.length;i++){

                    if(grille[0][j] == grille[i][j]){

                        grilleFausse[0][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 1 de la colonne j.
                for(int i = 2;i<grille.length;i++){

                    if(grille[1][j] == grille[i][j]){

                        grilleFausse[1][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 2 de la colonne j.
                for(int i = 3;i<grille.length;i++){

                    if(grille[2][j] == grille[i][j]){

                        grilleFausse[2][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 3 de la colone j.
                for(int i = 4;i<grille.length;i++){

                    if(grille[3][j] == grille[i][j]){

                        grilleFausse[3][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 4 de la colonne j.
                for(int i = 5;i<grille.length;i++){

                    if(grille[4][j] == grille[i][j]){

                        grilleFausse[4][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 5 dela colonne j.
                for(int i = 6;i<grille.length;i++){

                    if(grille[5][j] == grille[i][j]){

                        grilleFausse[5][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 6 de la colonne j.
                for(int i = 7;i<grille.length;i++){

                    if(grille[6][j] == grille[i][j]){

                        grilleFausse[6][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 7 de la colonne j.
                for(int i = 8;i<grille.length;i++){

                    if(grille[7][j] == grille[i][j]){

                        grilleFausse[7][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

                //test la ligne 8 de la colonne j.
                for(int i = 7;i > 0;i--){

                    if(grille[8][j] == grille[i][j]){

                        grilleFausse[8][j] = String.valueOf(grille[i][j]);
                        grilleFausse[i][j] = String.valueOf(grille[i][j]);

                    }

                }

            }

            /*
             * ********************************************************************************************************************************************
             * 
             *                                                      ANALYSE LES SOUS-MATRICES 3x3.
             * 
             * ********************************************************************************************************************************************
             */

            /*
             * Serie de test qui sert a verifier si la premiere sous-matrice est valide.
             * (0,0),(0,1),(0,2)
             * (1,0),(1,1),(1,2)
             * (2,0),(2,1),(2,2)
             */

            analyseCase(grille,grilleFausse,0,0,0);
            analyseCase(grille,grilleFausse,0,1,1);
            analyseCase(grille,grilleFausse,0,2,2);
            analyseCase(grille,grilleFausse,1,0,3);
            analyseCase(grille,grilleFausse,1,1,4);
            analyseCase(grille,grilleFausse,1,2,5);
            analyseCase(grille,grilleFausse,2,0,6);
            analyseCase(grille,grilleFausse,2,1,7);

            /*
             * Serie de test qui sert a verifier si la deuxieme sous-matrice est valide.
             * (0,3),(0,4),(0,5)
             * (1,3),(1,4),(1,5)
             * (2,3),(2,4),(2,5)
             */

            analyseCase(grille,grilleFausse,0,3,0);
            analyseCase(grille,grilleFausse,0,4,1);
            analyseCase(grille,grilleFausse,0,5,2);
            analyseCase(grille,grilleFausse,1,3,3);
            analyseCase(grille,grilleFausse,1,4,4);
            analyseCase(grille,grilleFausse,1,5,5);
            analyseCase(grille,grilleFausse,2,3,6);
            analyseCase(grille,grilleFausse,2,4,7);

            /*
             * Serie de test qui sert a verifier si la troisieme sous-matrice est valide.
             * (0,6),(0,7),(0,8)
             * (1,6),(1,7),(1,8)
             * (2,6),(2,7),(2,8)
             */

            analyseCase(grille,grilleFausse,0,6,0);
            analyseCase(grille,grilleFausse,0,7,1);
            analyseCase(grille,grilleFausse,0,8,2);
            analyseCase(grille,grilleFausse,1,6,3);
            analyseCase(grille,grilleFausse,1,7,4);
            analyseCase(grille,grilleFausse,1,8,5);
            analyseCase(grille,grilleFausse,2,6,6);
            analyseCase(grille,grilleFausse,2,7,7);

            /*
             * Serie de test qui sert a verifier si la quatrieme sous-matrice est valide.
             * (3,0),(3,1),(3,2)
             * (4,0),(4,1),(4,2)
             * (5,0),(5,1),(5,2)
             */

            analyseCase(grille,grilleFausse,3,0,0);
            analyseCase(grille,grilleFausse,3,1,1);
            analyseCase(grille,grilleFausse,3,2,2);
            analyseCase(grille,grilleFausse,4,0,3);
            analyseCase(grille,grilleFausse,4,1,4);
            analyseCase(grille,grilleFausse,4,2,5);
            analyseCase(grille,grilleFausse,5,0,6);
            analyseCase(grille,grilleFausse,5,1,7);

            /*
             * Serie de test qui sert a verifier si la cinquieme sous-matrice est valide.
             * (3,3),(3,4),(3,5)
             * (4,3),(4,4),(4,5)
             * (5,3),(5,4),(5,5)
             */

            analyseCase(grille,grilleFausse,3,3,0);
            analyseCase(grille,grilleFausse,3,4,1);
            analyseCase(grille,grilleFausse,3,5,2);
            analyseCase(grille,grilleFausse,4,3,3);
            analyseCase(grille,grilleFausse,4,4,4);
            analyseCase(grille,grilleFausse,4,5,5);
            analyseCase(grille,grilleFausse,5,3,6);
            analyseCase(grille,grilleFausse,5,4,7);

            /*
             * Serie de test qui sert a verifier si la sixieme sous-matrice est valide.
             * (3,6),(3,7),(3,8)
             * (4,6),(4,7),(4,8)
             * (5,6),(5,7),(5,8)
             */

            analyseCase(grille,grilleFausse,3,6,0);
            analyseCase(grille,grilleFausse,3,7,1);
            analyseCase(grille,grilleFausse,3,8,2);
            analyseCase(grille,grilleFausse,4,6,3);
            analyseCase(grille,grilleFausse,4,7,4);
            analyseCase(grille,grilleFausse,4,8,5);
            analyseCase(grille,grilleFausse,5,6,6);
            analyseCase(grille,grilleFausse,5,7,7);

            /*
             * Serie de test qui sert a verifier si la septieme sous-matrice est valide.
             * (6,0),(6,1),(6,2)
             * (7,0),(7,1),(7,2)
             * (8,0),(8,1),(8,2)
             */

            analyseCase(grille,grilleFausse,6,0,0);
            analyseCase(grille,grilleFausse,6,1,1);
            analyseCase(grille,grilleFausse,6,2,2);
            analyseCase(grille,grilleFausse,7,0,3);
            analyseCase(grille,grilleFausse,7,1,4);
            analyseCase(grille,grilleFausse,7,2,5);
            analyseCase(grille,grilleFausse,8,0,6);
            analyseCase(grille,grilleFausse,8,1,7);

            /*
             * Serie de test qui sert a verifier si la huitieme sous-matrice est valide.
             * (6,3),(6,4),(6,5)
             * (7,3),(7,4),(7,5)
             * (8,3),(8,4),(8,5)
             */

            analyseCase(grille,grilleFausse,6,3,0);
            analyseCase(grille,grilleFausse,6,4,1);
            analyseCase(grille,grilleFausse,6,5,2);
            analyseCase(grille,grilleFausse,7,3,3);
            analyseCase(grille,grilleFausse,7,4,4);
            analyseCase(grille,grilleFausse,7,5,5);
            analyseCase(grille,grilleFausse,8,3,6);
            analyseCase(grille,grilleFausse,8,4,7);

            /*
             * Serie de test qui sert a verifier si la neuvieme sous-matrice est valide.
             * (6,6),(6,7),(6,8)
             * (7,6),(7,7),(7,8)
             * (8,6),(8,7),(8,8)
             */

            analyseCase(grille,grilleFausse,6,6,0);
            analyseCase(grille,grilleFausse,6,7,1);
            analyseCase(grille,grilleFausse,6,8,2);
            analyseCase(grille,grilleFausse,7,6,3);
            analyseCase(grille,grilleFausse,7,7,4);
            analyseCase(grille,grilleFausse,7,8,5);
            analyseCase(grille,grilleFausse,8,6,6);
            analyseCase(grille,grilleFausse,8,7,7);

            // si on desire seulement savoir si la grille est gagnante ou perdante.
            if(choix == 2){

                int grilleGagnante = 0;//varialble sentinelle , si elle passe au dessu de 0 alors la grille est perdante.

                // boucle for imbiquee qui verifie que toute les case de la grilleFausse contiennent ne contiennent aucune variable (un String espace " ")
                for(int i = 0;i < grilleFausse.length;i++){

                    for(int j = 0;j < grilleFausse.length;j++){

                        //si la case contient autre chose qu'un espace (donce un chiffre sous forme de String) alors la grille est pardante.
                        if(grilleFausse[i][j] != " "){
                            ++grilleGagnante;
                        }

                    }

                }

                //affichage affiche selon la valeur de la sentinelle grilleFausse.
                if(grilleGagnante == 0){

                    System.out.println("Grille Gagnante  :)");

                }else if(grilleGagnante > 0){

                    System.out.println("GrillePerdante  :(");

                }

            }else if(choix == 3){

                System.out.println("+---+---+---+");
                /*
                 * Apres avoir insere l'entete (+---+---+---+), la boucle suivante determine a quels endroits les symboles '|' et les autres entete doivent 
                 * etre inseres.
                 */
                for(int i = 0; i < grilleFausse.length;i++){
                    /*
                     *A chaque 3 ligne une autre "entete" est place (que j'appelle entete meme si certaines d'entre elles seront situees dans la grille).
                     */
                    if(compteurLigne == 3){
                        System.out.println("+---+---+---+");
                        compteurLigne = 0;
                    }

                    //insertion du premier symbole '|' d'une ligne.
                    System.out.print("|");
                    compteurColonne = 0;

                    for (int j = 0; j < grilleFausse.length;j++){
                        // Un symbole '|' est insere a chaque 3 colonne (compteur de colonne == 3) et remise a zero du compteur.
                        if(compteurColonne == 3){
                            System.out.print("|");
                            compteurColonne = 0;
                        }
                        System.out.print(grilleFausse[i][j]);//Affichage de l'entier en postion (i,j).
                        ++compteurColonne;
                    }

                    ++compteurLigne;//servira pour le if au debut de la grosse boucle.
                    System.out.println("|");//Insertion d'un symbole '|' en fin de ligne.
                }

                System.out.println("+---+---+---+");//Affichage de la derniere entete.

                int grilleGagnante = 0;//varialble sentinelle , si elle passe au dessu de 0 alors la grille est perdante.

                // boucle for imbiquee qui verifie que toute les case de la grilleFausse contiennent ne contiennent aucune variable (un String espace " ")
                for(int i = 0;i < grilleFausse.length;i++){

                    for(int j = 0;j < grilleFausse.length;j++){

                        //si la case contient autre chose qu'un espace (donce un chiffre sous forme de String) alors la grille est pardante.
                        if(grilleFausse[i][j] != " "){
                            ++grilleGagnante;
                        }

                    }

                }

                //affichage affiche selon la valeur de la sentinelle grilleFausse.
                if(grilleGagnante == 0){

                    System.out.println("Grille Gagnante  :)");

                }else if(grilleGagnante > 0){

                    System.out.println("GrillePerdante  :(");

                }

            }

        }

    } // main

} // sudoku
