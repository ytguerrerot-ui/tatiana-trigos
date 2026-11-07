

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

    }
}

