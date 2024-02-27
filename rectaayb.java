
import java.util.Scanner;

public class rectaayb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*Entrada del tamaño de la matriz*/
        System.out.print("Ingrese el tamaño de la matriz (máximo 10): ");
        int n = scanner.nextInt();

        // Verificación de la validez del tamaño de la matriz

        if (n <= 0 || n > 10) { 
            System.out.println("Tamaño de matriz no válido.");
            scanner.close();
            return;
        }
        // Creación de la matriz de tamaño n x n
        int[][] matriz = new int[n][n];

        // Coordenadas para la curva de Bézier
        System.out.println("Ingrese las coordenadas para la curva (primer punto):");
        int curvaFila1 = scanner.nextInt() - 1;
        int curvaColumna1 = scanner.nextInt() - 1;

        System.out.println("Ingrese las coordenadas para la curva (segundo punto):");
        int curvaFila2 = scanner.nextInt() - 1;
        int curvaColumna2 = scanner.nextInt() - 1;

        // Verificación de la validez de las coordenadas de la curva

        if (esCoordenadaValida(curvaFila1, curvaColumna1, n) && esCoordenadaValida(curvaFila2, curvaColumna2, n)) {
            // Dibujar curva de Bézier entre los dos puntos de la curva
            dibujarCurvaBezier(matriz, curvaFila1, curvaColumna1, curvaFila2, curvaColumna2);

            // Mostrar el resultado de la curva
            System.out.println("Resultado de la curva:");

            // Imprimir la matriz

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Coordenadas para la curva no válidas.");
        }

        // Reiniciar la matriz para dibujar la recta
        matriz = new int[n][n];

        // Coordenadas para la recta
        System.out.println("Ingrese las coordenadas para la recta (primer punto):");
        int rectaFila1 = scanner.nextInt() - 1;
        int rectaColumna1 = scanner.nextInt() - 1;

        System.out.println("Ingrese las coordenadas para la recta (segundo punto):");
        int rectaFila2 = scanner.nextInt() - 1;
        int rectaColumna2 = scanner.nextInt() - 1;

        if (esCoordenadaValida(rectaFila1, rectaColumna1, n) && esCoordenadaValida(rectaFila2, rectaColumna2, n)) {
            // Dibujar recta entre los dos puntos de la recta
            dibujarRecta(matriz, rectaFila1, rectaColumna1, rectaFila2, rectaColumna2);

            // Mostrar el resultado de la recta
            System.out.println("Resultado de la recta:");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Coordenadas para la recta no válidas.");
        }

        scanner.close();
    }
    // Método para verificar la validez de las coordenadas

    private static boolean esCoordenadaValida(int fila, int columna, int n) {
        return fila >= 0 && fila < n && columna >= 0 && columna < n;
    }

        // Método para dibujar la curva de Bézier en la matriz
    private static void dibujarCurvaBezier(int[][] matriz, int fila1, int columna1, int fila2, int columna2) {
        int steps = 100; // Número de pasos para interpolar la curva
        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            double u = 1 - t;

            // Fórmula de la curva de Bézier
            double x = u * u * columna1 + 2 * u * t * columna1 + t * t * columna2;
            double y = u * u * fila1 + 2 * u * t * fila2 + t * t * fila2;
            
            // Redondear las coordenadas a enteros
            int roundedX = (int) Math.round(x);
            int roundedY = (int) Math.round(y);

            // Verificar que las coordenadas estén dentro de los límites de la matriz
            if (roundedX >= 0 && roundedX < matriz[0].length && roundedY >= 0 && roundedY < matriz.length) {
                matriz[roundedY][roundedX] = 1;
            }
        }
    }
    // Método para dibujar una recta en la matriz
    private static void dibujarRecta(int[][] matriz, int fila1, int columna1, int fila2, int columna2) {
        int steps = 100;
        for (int i = 0; i <= steps; i++) {
            double t = (double) i / steps;
            
            // Fórmula de la interpolación lineal para la recta

            int x = (int) Math.round((1 - t) * columna1 + t * columna2);
            int y = (int) Math.round((1 - t) * fila1 + t * fila2);

            // Verificar que las coordenadas estén dentro de los límites de la matriz
            if (esCoordenadaValida(y, x, matriz.length)) {
                matriz[y][x] = 1; // Usar otro número para la recta
            }
        }
    }
}
