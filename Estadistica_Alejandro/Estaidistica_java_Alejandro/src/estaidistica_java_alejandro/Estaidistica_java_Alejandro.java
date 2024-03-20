package estaidistica_java_alejandro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Estaidistica_java_Alejandro {
    public static void main(String[] args) throws FileNotFoundException {
        //Leer numeros del archivo
        List<Double> numeros = new ArrayList<>();
        Scanner scanner = new Scanner(new File("C:\\vegeta.txt"));
        while (scanner.hasNextDouble()) {
            numeros.add(scanner.nextDouble());
        }
        scanner.close();
        
        // Calcular el rango
        double min = Collections.min(numeros);
        double max = Collections.max(numeros);
        double rango = max - min;
        int n = numeros.size();

        Scanner teclado = new Scanner(System.in);
       
        //calcular el numero de clases
        System.out.println("Elige una opcion para determinar el numero de clases:");
        System.out.println("1. De 5 en 5");
        System.out.println("2. k=(1+log2(n))");
        System.out.println("3. Raiz cuadrada de n");
        System.out.println("4. Manual");
        int op = teclado.nextInt();
        
        //Declarar variables
        int numClases;
        double intervalo;
        
        //calcular el numero de clases
        switch (op) {
            case 1:
                numClases = 5;
                break;
            case 2:
                numClases = (int) Math.ceil(1 + Math.log(n) / Math.log(2));
                break;
            case 3:
                numClases = (int) Math.ceil(Math.sqrt(n));
                break;
            case 4:
                System.out.println("Ingrese el numero de clases:");
                numClases = teclado.nextInt();
                break;
            default:
                System.out.println("Opcion no valida");
                return;
        }
        
        // Calcular los intervalos de clase y las marcas de clase
        intervalo = rango / numClases;
        int ir = (int) Math.ceil(intervalo);

        double[] Li_1 = new double[numClases];
        double[] Li = new double[numClases];
        double[] Xi = new double[numClases];
        
        //limte inferior y superior
        for (int i = 0; i < numClases; i++) {
            Li_1[i] = min + i * ir;
            Li[i] = Li_1[i] + ir;
            Xi[i] = (Li_1[i] + Li[i]) / 2;
        }
        //frecuencia absoluta (ni)
        int[] ni = new int[numClases];
        for (double num : numeros) {
            for (int i = 0; i < numClases; i++) {
                if (num >= Li_1[i] && num < Li[i]) {
                    ni[i]++;
                    break;
                }
            }
        }
        //Frecuencia absoluta acumulada Ni
        double[] Ni = new double[numClases];
        Ni[0] = ni[0];
        for (int i = 1; i < numClases; i++) {
            Ni[i] = Ni[i - 1] + ni[i];
        }
        //hi calcular en porcentajes
        double[] hi = new double[numClases];
        for (int i = 0; i < numClases; i++) {
            hi[i] = (double) ni[i] / n * 100;
        }
        //HI frecuencia absoluta acumulada
        double[] Hi = new double[numClases];
        Hi[0] = hi[0];
        for (int i = 1; i < numClases; i++) {
            Hi[i] = Hi[i - 1] + hi[i];
        }
        // Imprimir los resultados
        System.out.println("Maximo: " + max);
        System.out.println("Minimo: " + min);
        System.out.println("Rango: " + rango);
        System.out.println("El tamanio del intervalo es (sin redondear): " + intervalo);
        System.out.println("El tamanio del intervalo es: " + ir);
        System.out.println("|N.clases|\tLi-1-Li| \tXi\tni\tNi\thi\tHi");

        for (int i = 0; i < numClases; i++) {
            System.out.printf("%d\t\t[%.2f\t%.2f)\t%.2f\t%d\t%.2f\t%.2f\t%.2f%n", i + 1, Li_1[i], Li[i], Xi[i], ni[i],
                    Ni[i], hi[i], Hi[i]);
        } 
    }
}
