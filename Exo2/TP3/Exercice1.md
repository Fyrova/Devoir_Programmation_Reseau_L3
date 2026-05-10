a. Synchronisation des threads du serveur
Le serveur doit gérer une liste de threads (ou de sockets) partagée. Pour éviter les accès concurrents, on utilise un synchronized sur la liste, ou une CopyOnWriteArrayList. Quand un client envoie un message, le serveur parcourt la liste et envoie à chaque client (dans son propre thread, ou dans le même thread de réception).

c. Gérer le départ d’un client
Dans le serveur, on supprime le PrintWriter de la liste et on ferme la socket (vu dans le finally du ClientHandler). On peut aussi détecter la déconnexion par une exception lors de la lecture.