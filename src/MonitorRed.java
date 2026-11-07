import java.util.Scanner;

public class MonitorRed {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int cantidad = leerCantidadMediciones(entrada);
        int[] tiempos = cargarTiempos(entrada, cantidad);

        int medicionesEntre20y200 = contarValoresEnRango(tiempos, 20, 200);
        int medicionesMayor200 = contarMayoresQue(tiempos, 200);
        int tiempoTotal = sumarTiempos(tiempos);
        double promedio = calcularPromedio(tiempoTotal, cantidad);
        int menorTiempo = buscarMenorTiempo(tiempos);
        int medicionesInferioresAlPromedio = contarMenoresQue(tiempos, promedio);
        int primeraPosicionMenor = buscarPrimeraPosicionMenor(tiempos, menorTiempo);

        mostrarResumenFinal(
                tiempos,
                medicionesEntre20y200,
                medicionesMayor200,
                tiempoTotal,
                promedio,
                menorTiempo,
                medicionesInferioresAlPromedio,
                primeraPosicionMenor
        );
    }

    public static int leerCantidadMediciones(Scanner entrada) {
        int cantidad;

        do {
            System.out.print("Ingrese la cantidad de mediciones (entre 3 y 20): ");
            while (!entrada.hasNextInt()) {
                System.out.println("Dato inválido. Debe ingresar un número entero.");
                entrada.next();
                System.out.print("Ingrese la cantidad de mediciones (entre 3 y 20): ");
            }

            cantidad = entrada.nextInt();

            if (cantidad < 3 || cantidad > 20) {
                System.out.println("Cantidad inválida. Debe estar entre 3 y 20.");
            }
        } while (cantidad < 3 || cantidad > 20);

        return cantidad;
    }

    public static boolean esTiempoValido(int tiempo) {
        return tiempo >= 20 && tiempo <= 500;
    }

    public static int[] cargarTiempos(Scanner entrada, int cantidad) {
        int[] tiempos = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            