

import java.util.Scanner;

public class MonitorRed {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int cantidad = leerCantidadMediciones(entrada);
        int[] tiempos = cargarTiempos(entrada, cantidad);

        int total = sumarTiempos(tiempos);
        double promedio = calcularPromedio(total, cantidad);
        int menor = buscarMenorTiempo(tiempos);

        mostrarResumenFinal(tiempos,
            contarValoresEnRango(tiempos, 20, 200),
            contarMayoresQue(tiempos, 200),
            total, promedio, menor,
            contarMenoresQue(tiempos, promedio),
            buscarPrimeraPosicionMenor(tiempos, menor));

        entrada.close();
    }

    public static int leerCantidadMediciones(Scanner e) {
        int n;
        do {
            System.out.print("Cantidad de mediciones (3 a 20): ");
            while (!e.hasNextInt()) {
                System.out.println("Ingrese un entero.");
                e.next();
            }
            n = e.nextInt();
        } while (n < 3 || n > 20);
        return n;
    }

    public static boolean esTiempoValido(int t) {
        return t >= 20 && t <= 500;
    }

    public static int[] cargarTiempos(Scanner e, int n) {
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.print("Medición " + (i + 1) + ": ");
                while (!e.hasNextInt()) {
                    System.out.println("Ingrese un entero.");
                    e.next();
                }
                t[i] = e.nextInt();
                if (!esTiempoValido(t[i]))
                    System.out.println("Tiempo inválido (20 a 500).");
            } while (!esTiempoValido(t[i]));
        }
        return t;
    }

    public static int sumarTiempos(int[] t) {
        int suma = 0;
        for (int x : t) suma += x;
        return suma;
    }
    
    
    public static int contarValoresEnRango(int[] t, int min, int max) {
        int c = 0;
        for (int x : t)
            if (x >= min && x <= max) c++;
        return c;
    }

    public static int contarMayoresQue(int[] t, int limite) {
        int c = 0;
        for (int x : t)
            if (x > limite) c++;
        return c;
    }

    public static double calcularPromedio(int suma, int n) {
        return (double) suma / n;
    }

    public static int contarMenoresQue(int[] t, double p) {
        int c = 0;
        for (int x : t)
            if (x < p) c++;
        return c;
    }

    public static int buscarMenorTiempo(int[] t) {
        int menor = t[0];
        for (int x : t)
            if (x < menor) menor = x;
        return menor;
    }

    public static int buscarPrimeraPosicionMenor(int[] t, int menor) {
        for (int i = 0; i < t.length; i++)
            if (t[i] == menor) return i + 1;
        return -1;
    }

    public static void mostrarResumenFinal(int[] t, int rango,
            int mayores, int total, double promedio, int menor,
            int inferiores, int posicion) {

        System.out.println("\n RESUMEN FINAL");
        for (int i = 0; i < t.length; i++)
            System.out.println("Medición " + (i + 1) + ": " + t[i] + " ms");

        System.out.println("Entre 20 y 200: " + rango);
        System.out.println("Mayores de 200: " + mayores);
        System.out.println("Total: " + total + " ms");
        System.out.printf("Promedio: %.2f ms%n", promedio);
        System.out.println("Menor tiempo: " + menor + " ms");
        System.out.println("Inferiores al promedio: " + inferiores);
        System.out.println("Primera aparición del menor: Medición " + posicion);

    }
}

