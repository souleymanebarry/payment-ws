# payment-ws


#Design application
Développer une application Spring Boot proposant une API RESTful permettant de faire des
paiements. Il est demandé de développer l'API avec Spring Boot et Spring Webflux
Cette API devra permettre de sauvegarder, lire, modifier dans une base de données MongoDB
des paiements, tels que:
Un paiement est représenté par une classe "Transaction" avec les champs:
- montant
- type de moyen de paiement parmi carte bancaire, carte cadeau et paypal
- statut pouvant être : nouveau, autorisé et capturé
- commande: une liste de lignes de commande, une ligne de commande étant représentée par
une classe OrderLine ayant les champs:
- nom de produit
- quantité
- prix

#Regle de gestion:
- une nouvelle transaction doit se trouver dans l'état "NEW"
- il n'est pas possible de passer le statut de la transaction à "CAPTURED"
si la transaction n'est pas dans l'état "AUTHORIZED"
- il n'est pas possible de modifier le statut d'une transaction "CAPTURED"
- Il n'est pas possible de modifier la commande lors de la modification d'une transaction

#Validation
Afin de valider fonctionnellement l'API, produire un jeu de test représentant le scénario suivant:
- création d'une transaction d'un montant de 54,80 EUR avec une carte bancaire et une commande
contenant 4 paires de gants de ski à 10 EUR pièce, et 1 bonnet en laine à 14,80EUR
- modification de la transaction en passant le statut à autorisé
- modification de la transaction en passant le statut à capturé
- création d'une transaction d'un montant de 208 EUR avec PayPal et une commande contenant
1 vélo à 208 EUR
- récupérer toutes les commandes
