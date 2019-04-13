# PROJET 3 - METTEZ VOTRE LOGIQUE À L'EPREUVE


## Présentation de l'application 

Mécanisme de recherche d’une combinaison à X chiffres : Plus ou moins.

L'application propose de jouer au jeu du plus ou moins.

Le but du jeu est de deviner ou de faire deviner une combinaison de X chiffres. (entre 2 et 10 chiffres)

Le jeu peut être joué selon 3 modes soit:

Challenger -> Vous devez deviner la combinaison.
Defender -> L'ordinateur doit deviner la combinaison.
Duel -> A tour de rôle, le joueur et l'ordinateur doivent deviner la combinaison adverse avant l'adversaire.

## Installation

Pour installer l'application dans votre IDE, vous pouvez télécharger l'archive du projet puis dans votre IDE cliquez sur
Import > General > Projects from Folder or Archive puis séléctionnez l'archive et enfin cliquez sur Finish.

## Utilisation

Le programme se base sur le fichier config.properties pour configurer le mode developpeur, le nombre de case possible 
pour la combinaison secrete et le nombre d'essai (entre 1 et 6). Il est possible de le modifier puis de relancer 
le programme pour l’appliquer automatiquement.

Le mode développeur permet d'afficher les codes générés pour vérifier le bon fonctionnement. Il peut être configurer
au lancement de l'application en modifiant le parametre par false ou true. 

## Compilation

Le programme, et donc les jeux, s'utilisent en mode console. Pour lancer le jeu :
Ouvrez un terminal, rendez-vous dans le dossier qui contient le projet et lancez cette ligne de commande

```bash
java -jar out/out/artifacts/NomDuJar_jar/NomDuJar.jar
```

Au démarrage, un menu apparaitra et vous indiquera les différentes possibilités.
Il suffira de taper le bon code puis sur Entrée. À la fin de la partie, l'utilisateur peut choisir de rejouer au même
jeu de lancer un autre jeu ou de quitter l'application.

