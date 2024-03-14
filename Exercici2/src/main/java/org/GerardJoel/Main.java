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

            // Leer datos del archivo y calcula los promedios
            double[] mitjas = calculaMitja(f);

            // Mostrar promedios en la pantalla
            System.out.printf("Mitja Anys: %.2f\n", mitjas[0]);
            System.out.printf("Mitja Altura: %.2f\n", mitjas[1]);

            // Escribir promedios archivo salida
            escribirMitjaNouf(nouf, mitjas);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, el programa ha explotat");
        }
    }

    // Función para calcular promedios y mostrar nombres
    // Lo del throws me lo ha puesto intelij, pero parece que funciona.
    public static double[] calculaMitja(File f) throws FileNotFoundException {
        Scanner scanner = new Scanner(f);

        int totalAnys = 0;
        double totalAltura = 0;
        int compte = 0; // Contador

        // Lee el archivo
        while (scanner.hasNext()) {
            String name = scanner.next();
            int anys = scanner.nextInt();
            double altura = scanner.nextDouble();

            // Mostrar nombres
            System.out.println(name);

            // Calcular sumas
            totalAnys += anys;
            totalAltura += altura;
            compte++;
        }

        // Calcular los promedios
        double mitjaAnys = (double) totalAnys / compte;
        double mitjaAltura = totalAltura / compte;

        // Retornar promedios en un array
        return new double[]{mitjaAnys, mitjaAltura};
    }

    // Función para escribir promedios en un archivo
    public static void escribirMitjaNouf(File outputFile, double[] mitja) {
        try (PrintWriter w = new PrintWriter(outputFile)) {
            // Escribir promedios en el archivo de salida
            // lo de %.2f coge y hace que el float se haga 2 decimales, luego lo explico mejor.
            w.printf("Mitja de Anys: %.2f\n", mitja[0]);
            w.printf("Mitja de Altura: %.2f\n", mitja[1]);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, el programa ha explotat");
        }
    }
}

