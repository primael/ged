DbUnitTest
==========
Par Prima�l BRUANT.

Version au 07/09/2014.

Ce document pr�sente la fa�on d'utiliser __DbUnitTest__ pour g�rer et cr�er des tests. 

Installation avec maven
-----------------------
###Pr�-requis###
Avoir installer DbUnitTest dans son r�pository local.
###Installation dans un projet###
 ```xml
 <dependency>
 	<groupId>fr.nimrod.info</groupId>
	<artifactId>dbUnitTest</artifactId>
	<version>1.0</version>
	<scope>test</scope>
</dependency>
```
###Utilisation dans un test###
Vous devez dans un premier temps d�clarer dans votre test le @Rule Junit4:
```java

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(__Class d'appel__, __Driver de la bdd___,
			__URL de connexion__, __utilisateur__, __password__);
```

Ensuite sur vos m�thode de test, ajouter les annotations :

 - @Schema => Pour l'�xecution d'un script
 - @Data => Pour l'insertion d'un jeu de donn�es (supporte actuellement le format JSON et XML)
 - @ExpectedData => Pour la v�rification entre les donn�es obtenues et les donn�es attendues.
 
h1 @Schema h1
 
Annotation permettant l'import d'un script sql (ie. la cr�ation d'une table)
 
```java
 	@Schema({"utilisateur.sql", "role.sql"})
```
 
 h1 @Data h1
 
 Annation permettant l'ajout de donn�es.
 
 ```java
 	@Data({"utilisateur.xml", "role.json"})
```
 Actuellement seul les formats json et xml sont support�s.
 
Lancer votre test, that's it!
