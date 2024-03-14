package org.GerardJoel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/*
Aquest programa fa que llegeixi un fitxer, mostri els noms i calculi la mitja de l’edat i de les estatures
(transformant els doubles a decimals %.2f ), mostrant-les per pantalla i desant-les en un altre arxiu de sortida.  L’estructura de l’arxiu es la següent:
Joan 22 1.77
Luís 22 1.80
Pedro 20 1.73
 */

public class Main {
    public static void main(String[] args) {
        try {
            File f = new File("mitja.txt");
            File nouf = new File("noumitja.txt");

            // Llegir dades de l'arxiu i calcular les mitjanes
            double[] mitjes = calculaMitja(f);

            // Mostrar les mitjanes a la pantalla
            System.out.printf("Mitja Anys: %.2f\n", mitjes[0]);
            System.out.printf("Mitja Altura: %.2f\n", mitjes[1]);

            // Escriure les mitjanes a l'arxiu de sortida
            escriureMitjaNouf(nouf, mitjes, f);
        } catch (IOException e) {
            System.out.println("ERROR, el programa ha explotat1");
        }
    }

    /**
     * Funció per calcular la mitjana d'edat i alçada llegides del fitxer.
     * @param f
     * @return new double[]{mitjaAnys, mitjaAltura};
     * @throws IOException
     */
    public static double[] calculaMitja(File f) throws IOException {
        Scanner scanner = new Scanner(f);

        int totalAnys = 0;
        double totalAltura = 0;
        int compte = 0; // Comptador

        // Llegeix l'arxiu
        while (scanner.hasNext()) {
            String name = scanner.next();
            int anys = scanner.nextInt();
            double altura = scanner.nextDouble();

            // Mostrar noms
            System.out.println(name);

            // Calcular sumes
            totalAnys += anys;
            totalAltura += altura;
            compte++;
        }

        // Calcular les mitjanes
        double mitjaAnys = (double) totalAnys / compte;
        double mitjaAltura = totalAltura / compte;

        // Cerrar el Scanner
        scanner.close();

        // Retornar mitjanes en un array
        return new double[]{mitjaAnys, mitjaAltura};
    }

    // Funció per escriure mitjanes en un arxiu

    /**
     * Aquesta funció escriu les mitjanes d'edat i alçada a l'arxiu de sortida.
     * @param nouf
     * @param mitja
     */
    public static void escriureMitjaNouf(File nouf, double[] mitja, File f) {
        try (PrintWriter w = new PrintWriter(nouf)) {
            // Abrir el Scanner per llegir els noms
            Scanner scanner = new Scanner(f);

            // Escriure nombres y mitjanes en l'arxiu de sortida
            while (scanner.hasNext()) {
                String nom = scanner.next();
                // %s fa que el printf sigui tractat com a una cadena de caràcters.
                w.printf("Nom: %s\n", nom);
            }
            // el '%.2f' fa que el nombre de punt flotant sigui de 2 decimals.
            w.printf("Mitja d'Anys: %.2f\n", mitja[0]);
            w.printf("Mitja d'Altura: %.2f\n", mitja[1]);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, el programa ha explotat2");
        }
    }
}



