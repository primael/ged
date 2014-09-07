DbUnitTest
==========
Par Primaël BRUANT.

Version au 07/09/2014.

Ce document présente la façon d'utiliser __DbUnitTest__ pour gérer et créer des tests. 

Installation avec maven
-----------------------
###Pré-requis###
Avoir installer DbUnitTest dans son répository local.
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
Vous devez dans un premier temps déclarer dans votre test le @Rule Junit4:
```java

	@Rule
	public NimrodDbRule dbUnitRule = new NimrodDbRule(__Class d'appel__, __Driver de la bdd___,
			__URL de connexion__, __utilisateur__, __password__);
```

Ensuite sur vos méthode de test, ajouter les annotations :

 - @Schema => Pour l'éxecution d'un script
 - @Data => Pour l'insertion d'un jeu de données (supporte actuellement le format JSON et XML)
 - @ExpectedData => Pour la vérification entre les données obtenues et les données attendues.
 
Lancer votre test, that's it!
