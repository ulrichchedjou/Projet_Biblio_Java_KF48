package com.java_course;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Bibliotheque {
    private final List<Livre> livres;
    List<Membres> membres;
    private final List<Emprunt> emprunts;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.membres = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        if (livre != null && !livres.contains(livre)) {
            livres.add(livre);
            System.out.println("Livre ajouté: " + livre.getTitle());
        } else {
            System.out.println("Le livre existe déjà ou est invalide.");
        }
    }

    public Livre rechercherLivre(String isbn) {
        for (Livre livre : livres) {
            if (livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> rechercherLivreParTitre(String title) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getTitle().equalsIgnoreCase(title)) {
                result.add(livre);
            }
        }
        return result;
    }

    public List<Livre> rechercherLivreParAuteur(String author) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getAuthor().equalsIgnoreCase(author)) {
                result.add(livre);
            }
        }
        return result;
    }

    public List<Livre> rechercherLivreParCategorie(String categorie) {
        List<Livre> result = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getCategorie().equalsIgnoreCase(categorie)) {
                result.add(livre);
            }
        }
        return result;
    }

    public void inscrireMembre(Membres membre) {
        if (membre != null && !membres.contains(membre)) {
            membres.add(membre);
            System.out.println("Membre inscrit: " + membre.getName());
        } else {
            System.out.println("Le membre existe déjà ou est invalide.");
        }
    }

    public void supprimerMembre(Membres membre) {
        if (membres.remove(membre)) {
            System.out.println("Membre supprimé: " + membre.getName());
        } else {
            System.out.println("Le membre n'existe pas.");
        }
    }

    public List<Membres> getMembres() {
        return membres;
    }

    public void gererEmprunt(Livre livre, Membres membre, Date dueDate) {
        if (livre.isAvailable()) {
            livre.setAvailable(false);
            Emprunt emprunt = new Emprunt(livre, membre, dueDate);
            emprunts.add(emprunt);
            membre.borrowBook(livre.getTitle());
        }
    }

    public void retournerLivre(Livre livre, Membres membre) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getBorrowedBook().equals(livre) && emprunt.getMember().equals(membre)) {
                emprunt.returnBook();
                double penalty = emprunt.calculatePenalty();
                if (penalty > 0) {
                    System.out.println("Pénalité pour retard: " + penalty + " euros.");
                } else {
                    System.out.println("Livre retourné sans pénalité.");
                }
                return;
            }
        }
        System.out.println("Aucun emprunt correspondant trouvé.");
    }

    public void afficherLivresDisponibles() {
        System.out.println("Livres disponibles:");
        for (Livre livre : livres) {
            if (livre.isAvailable()) {
                System.out.println("- " + livre.getTitle() + " par " + livre.getAuthor());
            }
        }
    }

    public void supprimerLivre(String isbn) {
        Livre livreToRemove = rechercherLivre(isbn);
        if (livreToRemove != null && livreToRemove.isAvailable()) {
            livres.remove(livreToRemove);
            System.out.println("Livre supprimé: " + livreToRemove.getTitle());
        } else {
            System.out.println("Livre non trouvé ou actuellement emprunté.");
        }
    }
}
