package org.GerardJoel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

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
            escriureMitjaNouf(nouf, mitjes);
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
        /*
        El 'throw' serveix per evitar haver de posar el try-catch. En intentar posar el try-catch vam tenir problemes per fer que el programa pogués
        retornar la mitjana d'anys i alçada, així que vam optar per utilitzar el 'throw'.
        El que fa és llençar IOException amb double[] mitja = calculaMitja(f); sense la necessitat de crear un try-catch dins de la pròpia funció.
        (Sugerencia de IntelliJ).
         */
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

        // Retornar mitjanes en un array
        return new double[]{mitjaAnys, mitjaAltura};
    }

    // Funció per escriure mitjanes en un arxiu

    /**
     * Aquesta funció escriu les mitjanes d'edat i alçada a l'arxiu de sortida.
     * @param nouf
     * @param mitja
     */
    public static void escriureMitjaNouf(File nouf, double[] mitja) {
        try (PrintWriter w = new PrintWriter(nouf)) {
            // el '%.2f' fa que el nombre de punt flotant sigui de 2 decimals.
            w.printf("Mitja d'Anys: %.2f\n", mitja[0]);
            w.printf("Mitja d'Altura: %.2f\n", mitja[1]);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, el programa ha explotat2");
        }
    }
}


