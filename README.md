⚠️CE PROJET A ÉTÉ RÉALISÉ DANS UN CADRE UNIVERSITAIRE. IL A ÉTÉ DÉPOSÉ SUR CE GIT POUR CORRECTION, LE CODE PEUT DONC CONTENIR DES ERREURS⚠️

# Projet RPG Graphique

## Déroulement global du jeu

Ce projet a été réalisé a l'aide du projet RPG en Console précédent, il reprend une grande partie du code, mais adapte certaines class à la contrainte graphique.</br>

Le jeu s'articule de manière très simple, les interfaces graphiques nécéssaire à chaque actions apparaissent quand c'est nécéssaire. </br>
On commence d'abord par entrer le nom de son personnage, on choisit ensuite sa caste, parmi Elf, Guerrier et Sorcier(La caste par défaut est Elf). 
 Chacune d'entre elles possèdent des stats différentes. </br>
On affiche ensuite les stats du joueur. On démarre enfin à l'aide du bouton "START", le jeu. </br>
Le jeu se termine lorsque le joueur atteint la case "5", qui se situe à son opposé sur la map.</br>

## La map

La map est une JPanel, dans lequel, à l'aide d'une boucle on ajoute des JLabel contenant les cases de la map. </br>

Le joueur se déplace sur une matrice de 6 par 6, les conventions lui sont données à l'affichage de la map. </br>
Les voici : </br>
0-> C'est une case vide, le joueur peut se déplacer dessus mais aucun évenement n'y est lié.</br>
1-> Le joueur ne peut se déplacer, c'est un mur</br>
2-> La case où le joueur se trouve</br>
3-> Un obstacle simple à détruire</br>
5-> La destination final, à atteindre pour gagner</br>
6-> Un shop</br>
Le joueur doit simplement cliquer sur la case voulu pour se déplacer (malheureusement je n'ai pas réussi a implémenté un test qui vérifie si la case séléctionnée est bien une case adjacente au joueur)
En fonction de la case visée, un évenement se déclenche (shop, simple d'obstacle).</br>
Une fois que le joueur s'est déplacé sur une case, celle-ci reste 2, quand il l'a quitée.</br>

## Les combats

Le combat propose trois options au joueur.</br>
"Attack" -> Attaque simplement l'adversaire</br>
"Heal" -> Heal le joueur se heal d'un nombre fixe de points de vie (5)</br>
"Heal mana" -> Restaure un nombre fixe de point de mana (10)</br>

A la fin de chaque combat avec un monstre le joueur gagne 25 points d'expèrience, et 100 pièces d'or.</br>

## Le joueur

Le joueur peut monter de niveaux, en ayant acquis 100 points d'expèriences.</br>
Une montée de niveau signifie un gain de stats fixe pour lui (augmentation de l'attaque, des pv, ...)</br>
Seul la montée de niveau peut augmenter la défense du joueur, l'attaque peut être accrue avec l'achat d'arme dans le shop et la montée de niveau.</br>

## Les shop

Les shop sont tous identiques.</br>
Quand on entre sur la case 6, le shop s'ouvre, et nous propose une liste d'arme fixe.</br>
Les statistiques de chaque arme sont écrites, ainsi que leurs prix.</br>
L'arme voulue peut être séléctionnée simplement en entrant son numéro. Elle s'équipe ensuite automatiquement.</br>

## Disclaimer

Dans le code il existe des statistiques resistance magique et magic damage. Le programme n'a pas été optimisé pour leur usage. Elles sont présentes pour potentiellement
être utlisées dans le prochain projet.</br>

Les dêgats, la mana, l'argent, ainsi que l'ensemble des statistiques doivent aussi être optimisés comme dans un vrai RPG, pour améliorer l'expèrience utilsateur
.</br>



