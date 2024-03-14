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
            // %.2f es per a "l'aproximació", transforma el float a 2 decimals.
            System.out.printf("Mitja Anys: %.2f\n", mitjes[0]);
            System.out.printf("Mitja Altura: %.2f\n", mitjes[1]);

            // Escriure les mitjanes a l'arxiu de sortida
            escriureMitjaNouf(nouf, f);
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


        scanner.close();

        // Retornar mitjanes en un array
        return new double[]{mitjaAnys, mitjaAltura};
    }

    /**
     * Aquesta funció escriu les mitjanes d'edat i alçada a l'arxiu de sortida.
     * També serveix perquè la informació estigui classificada per apartats, on com a capçalera de cada apartat s'indica l'any de la lliga.
     * @param nouf
     * @param f
     */
    public static void escriureMitjaNouf(File nouf, File f) {
        try (PrintWriter w = new PrintWriter(nouf)) {
            Scanner scanner = new Scanner(f);

            // Variable per emmagatzemar l'any de la lliga
            int anyLliga = 0;

            // Llegeix l'any de la lliga
            if (scanner.hasNextLine()) {
                String primeraLinia = scanner.nextLine();
                // \\s+ s'utilitza per trobar un o més caràcters d'espai en blanc consecutius en una cadena de text.
                String[] parts = primeraLinia.split("\\s+");
                if (parts.length > 1) {
                    anyLliga = Integer.parseInt(parts[1]);
                }
            }

            // Escriu les mitjanes a l'arxiu de sortida amb l'any de la lliga com a capçalera
            // El %d-%d és perquè s'esperen dos valors enters, substitueix el que estaria en aquesta posició amb aquests 2 nombres decimals
            w.printf("Lliga %d-%d\n", anyLliga, anyLliga+1);
            while (scanner.hasNext()) {
                String name = scanner.next();
                int anys = scanner.nextInt();
                double altura = scanner.nextDouble();

                // Escriure nom i dades
                // % s es per entrar un valor String i que es mostri.
                w.printf("Nom: %s\n", name);
                // %d Es per entrar un valor decimal.
                w.printf("Anys: %d\n", anys);
                // %.2f es per a "l'aproximació", transforma el float a 2 decimals.
                w.printf("Altura: %.2f\n", altura);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, el programa ha explotat2");
        }
    }
}




