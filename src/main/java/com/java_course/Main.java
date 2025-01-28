package com.java_course;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            do {
                System.out.println("\n=== Menu ===");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Supprimer un livre");
                System.out.println("3. Afficher les livres disponibles");
                System.out.println("4. Inscrire un membre");
                System.out.println("5. Emprunter un livre");
                System.out.println("6. Retourner un livre");
                System.out.println("7. Rechercher un livre par ISBN");
                System.out.println("8. Rechercher un livre par titre");
                System.out.println("9. Rechercher un livre par auteur");
                System.out.println("10. Rechercher un livre par catégorie");
                System.out.println("11. Supprimer un membre");
                System.out.println("0. Quitter");
                System.out.print("Choisissez une option: ");

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1 -> {
                            System.out.print("Titre du livre: ");
                            String title = scanner.nextLine();
                            System.out.print("Auteur du livre: ");
                            String author = scanner.nextLine();
                            System.out.print("ISBN du livre: ");
                            String isbn = scanner.nextLine();
                            System.out.print("Catégorie du livre: ");
                            String categorie = scanner.nextLine();
                            Livre livre = new Livre(title, author, isbn, categorie);
                            bibliotheque.ajouterLivre(livre);
                        }
                        case 2 -> {
                            System.out.print("ISBN du livre à supprimer: ");
                            String isbnToRemove = scanner.nextLine();
                            bibliotheque.supprimerLivre(isbnToRemove);
                        }
                        case 3 -> bibliotheque.afficherLivresDisponibles();
                        case 4 -> {
                            System.out.print("ID du membre: ");
                            String memberId = scanner.nextLine();
                            System.out.print("Email du membre: ");
                            String email = scanner.nextLine();
                            System.out.print("Nom du membre: ");
                            String name = scanner.nextLine();
                            Membres membre = new Membres(memberId, email, name);
                            bibliotheque.inscrireMembre(membre);
                        }
                        case 5 -> {
                            System.out.print("ISBN du livre à emprunter: ");
                            String isbnToBorrow = scanner.nextLine();
                            Livre livreToBorrow = bibliotheque.rechercherLivre(isbnToBorrow);
                            if (livreToBorrow != null && livreToBorrow.isAvailable()) {
                                System.out.print("ID du membre: ");
                                String memberIdForBorrow = scanner.nextLine();
                                Membres membreForBorrow = null;
                                for (Membres m : bibliotheque.getMembres()) {
                                    if (m.getMemberId().equals(memberIdForBorrow)) {
                                        membreForBorrow = m;
                                        break;
                                    }
                                }
                                if (membreForBorrow != null) {
                                    bibliotheque.gererEmprunt(livreToBorrow, membreForBorrow, new Date());
                                } else {
                                    System.out.println("Membre non trouvé.");
                                }
                            } else {
                                System.out.println("Livre non disponible ou non trouvé.");
                            }
                        }
                        case 6 -> {
                            System.out.print("ISBN du livre à retourner: ");
                            String isbnToReturn = scanner.nextLine();
                            Livre livreToReturn = bibliotheque.rechercherLivre(isbnToReturn);
                            if (livreToReturn != null && !livreToReturn.isAvailable()) {
                                System.out.print("ID du membre: ");
                                String memberIdForReturn = scanner.nextLine();
                                Membres membreForReturn = null;
                                for (Membres m : bibliotheque.getMembres()) {
                                    if (m.getMemberId().equals(memberIdForReturn)) {
                                        membreForReturn = m;
                                        break;
                                    }
                                }
                                if (membreForReturn != null) {
                                    bibliotheque.retournerLivre(livreToReturn, membreForReturn);
                                } else {
                                    System.out.println("Membre non trouvé.");
                                }
                            } else {
                                System.out.println("Livre non emprunté ou non trouvé.");
                            }
                        }
                        case 7 -> {
                            System.out.print("ISBN du livre à rechercher: ");
                            String isbnToSearch = scanner.nextLine();
                            Livre foundLivre = bibliotheque.rechercherLivre(isbnToSearch);
                            if (foundLivre != null) {
                                System.out.println("Livre trouvé: " + foundLivre.getTitle());
                            } else {
                                System.out.println("Livre non trouvé.");
                            }
                        }
                        case 8 -> {
                            System.out.print("Titre du livre à rechercher: ");
                            String titleToSearch = scanner.nextLine();
                            var foundLivres = bibliotheque.rechercherLivreParTitre(titleToSearch);
                            if (!foundLivres.isEmpty()) {
                                System.out.println("Livres trouvés:");
                                for (Livre l : foundLivres) {
                                    System.out.println("- " + l.getTitle() + " par " + l.getAuthor());
                                }
                            } else {
                                System.out.println("Aucun livre trouvé.");
                            }
                        }
                        case 9 -> {
                            System.out.print("Auteur du livre à rechercher: ");
                            String authorToSearch = scanner.nextLine();
                            var foundLivres = bibliotheque.rechercherLivreParAuteur(authorToSearch);
                            if (!foundLivres.isEmpty()) {
                                System.out.println("Livres trouvés:");
                                for (Livre l : foundLivres) {
                                    System.out.println("- " + l.getTitle() + " par " + l.getAuthor());
                                }
                            } else {
                                System.out.println("Aucun livre trouvé.");
                            }
                        }
                        case 10 -> {
                            System.out.print("Catégorie du livre à rechercher: ");
                            String categorieToSearch = scanner.nextLine();
                            var foundLivres = bibliotheque.rechercherLivreParCategorie(categorieToSearch);
                            if (!foundLivres.isEmpty()) {
                                System.out.println("Livres trouvés:");
                                for (Livre l : foundLivres) {
                                    System.out.println("- " + l.getTitle() + " par " + l.getAuthor());
                                }
                            } else {
                                System.out.println("Aucun livre trouvé.");
                            }
                        }
                        case 11 -> {
                            System.out.print("ID du membre à supprimer: ");
                            String memberIdToRemove = scanner.nextLine();
                            Membres membreToRemove = null;
                            for (Membres m : bibliotheque.getMembres()) {
                                if (m.getMemberId().equals(memberIdToRemove)) {
                                    membreToRemove = m;
                                    break;
                                }
                            }
                            if (membreToRemove != null) {
                                bibliotheque.supprimerMembre(membreToRemove);
                            } else {
                                System.out.println("Membre non trouvé.");
                            }
                        }
                        case 0 -> System.out.println("Au revoir!");
                        default -> System.out.println("Option invalide.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                    scanner.nextLine(); // Clear invalid input
                }
            } while (true);
        }
    }
}