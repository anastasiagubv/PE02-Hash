# Part 5: Proves de la pràctica

## Prova 1: Dos usuaris diferents amb la mateixa contrasenya

**Què s'ha fet:** registrar dos usuaris ('anna' i 'pau') amb la mateixa contrasenya ('1234').

**Resultat:**

```
Usuari guardat correctament!
Usuari: anna
Salt: FE526E1D12B030102C4ACB4BA3CB48F4
Hash: 023F6A0B578A33F74CC82E95E708B284B24CDCCA456DF79FD83D12C4E062991D

Usuari guardat correctament!
Usuari: pau
Salt: 65D76AAAB9508EA203A8262FFA91D654
Hash: 127F39B86AD2777E794CD621DF3069DD2D99E147AB99F90978438A700036131F
```

**Conclusió:** encara que tots dos usuaris tenen la mateixa contrasenya, **els hashes són diferents**. Cada usuari té una salt aleatòria diferent i el hash es calcula sobre 'contrasenya + salt', de manera que el resultat canvia completament.

---

## Prova 2: Iniciar sessió amb una contrasenya correcta

**Què s'ha fet:** iniciar sessió amb l'usuari 'anna' i la contrasenya correcta ('1234').

**Resultat:**

```
Accés permès. Benvingut/da, anna
```

**Conclusió:** el programa recupera la salt guardada de l'usuari, calcula el hash de la contrasenya introduïda amb aquesta salt i, com que coincideix amb el hash guardat, **permet l'accés**.

---

## Prova 3: Iniciar sessió amb una contrasenya incorrecta

**Què s'ha fet:** iniciar sessió amb l'usuari `anna` i una contrasenya incorrecta (`0000`).

**Resultat:**

```
Error: contrasenya incorrecta
```

**Conclusió:** amb una contrasenya diferent, el hash calculat no coincideix amb el guardat i el programa **denega l'accés** amb un missatge d'error.

També s'ha comprovat que, si s'introdueix un usuari que no existeix, el programa mostra `Error: l'usuari no està registrat` i ni tan sols demana la contrasenya.

---

## Prova 4: Utilitzar una salt diferent de la guardada

**Què s'ha fet:** amb l'opció 5 del menú, iniciar sessió amb l'usuari `anna` i la contrasenya **correcta** (`1234`), però calculant el hash amb una salt nova en lloc de la salt guardada.

**Resultat:**

```
Salt guardat: FE526E1D12B030102C4ACB4BA3CB48F4
Salt diferent: 1F35E030DB4B4172D71C931BE8B3D644
Hash guardat: 023F6A0B578A33F74CC82E95E708B284B24CDCCA456DF79FD83D12C4E062991D
Hash calculat: 0F3171B756C1416A99F66B4C238CA437A97A45E4C5FC56429567E9418BFC468B
Accés denegat: amb una salt diferent el hash no coincideix
```

**Conclusió:** encara que la contrasenya és correcta, en fer servir una salt diferent el hash resultant és totalment diferent del guardat, i **no es permet l'accés**. Per verificar una contrasenya cal fer servir exactament la mateixa salt que es va utilitzar en el registre.

---

## Per què les salts han de ser diferents i per què cal conservar-les?

Les salts han de ser diferents perquè, sense elles, dos usuaris amb la mateixa contrasenya tindrien el mateix hash, i un atacant podria esbrinar-la fàcilment amb taules de hashes precalculats (*rainbow tables*). Amb una salt aleatòria per a cada usuari, la mateixa contrasenya genera hashes diferents (prova 1).

Cal conservar la salt de cada usuari perquè un hash no es pot desfer: per verificar la contrasenya s'ha de tornar a calcular el hash amb la mateixa salt del registre. Amb una salt diferent el hash no coincideix encara que la contrasenya sigui correcta (prova 4).
