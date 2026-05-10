a-  Raisons de ce choix
- Regrouper les requêtes pour réduire le trafic sur le réseau local.

- Contourner des restrictions de pare-feu (un seul serveur autorisé à sortir).

- Centraliser la logique de découverte.

- Faciliter la mise en cache des résultats.

b- Syntaxe de la requête
La requête est une ligne de texte envoyée par le client au serveur (port 1026). Elle peut prendre l’une des deux formes suivantes :
1. Requête simple (une seule machine):
<adresse> : une adresse IPv4 (ex: 192.168.2.5) ou un nom d’hôte résolvable .
Exemple : 192.168.2.10
2. Requête avec plage d’adresses
<base_adresse> : une adresse IPv4 dont le dernier octet est remplacé par 1 (ou laissé tel quel mais interprété comme début de plage). 
On peut aussi définir plus précisément :
Format : a.b.c.d-e où a.b.c est le préfixe fixe, d est l’octet de début, e est l’octet de fin (inclus).
Les octets d et e sont compris entre 0 et 255, avec d ≤ e.
