DbUnitTest
==========
Par Prima�l BRUANT.
Version au 07/09/2014.
Ce document pr�sente la fa�on d'utiliser __DbUnitTest__ pour g�rer et cr�er des tests. 

Installation avec maven
-----------------------
###Pr�-requis###
Avoir installer DbUnitTest dans son r�pository local
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
