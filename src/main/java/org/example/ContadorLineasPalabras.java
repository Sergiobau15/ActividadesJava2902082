package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class ContadorLineasPalabras {

    static int palabrasTotales;
    static int lineasTotales;
    static int oracionesTotales;
    static String palabraMasFrecuente;
    static double longitudPromedio;

    public static void main(String[] args) {
        // VARIABLE PARA LLEVAR EL CONTADOR DE PALABRAS
        palabrasTotales = 0;

        // VARIABLE PARA LLEVAR EL CONTADOR DE LINEAS
        lineasTotales = 0;

        // VARIABLE PARA LLEVAR EL CONTADOR DE ORACIONES
        oracionesTotales = 0;

        // OBJETO ARCHIVO CON LA RUTA DEL ARCHIVO A LEER
        File archivo = new File("C:\\Users\\Aprendiz\\Desktop\\txt\\holamundo.txt");

        // LLAMADA AL METODO PARA CONTAR LAS PALABRAS Y LAS LINEAS
        contarPalabrasLineas(archivo);
    }

    // METODO QUE LEE EL ARCHIVO Y CUENTA LAS PALABRAS Y LAS LINEAS
    public static void contarPalabrasLineas(File archivo) {
        try {
            // SI EXISTE EL ARCHIVO
            if(archivo.exists()) {
                // ABRE LECTURA
                BufferedReader archivoLeer = new BufferedReader(new FileReader(archivo));

                String lineaLeida;

                // MAPA PARA CONTAR LA FRECUENCIA DE LAS PALABRAS
                Map<String, Integer> frecuenciaPalabras = new HashMap<>();

                // MIENTRAS LA LINEA LEIDA NO SEA NULL
                while((lineaLeida = archivoLeer.readLine()) != null) {
                    System.out.println(lineaLeida);

                    // CONTAMOS LAS PALABRAS Y ORACIONES DE LA LINEA
                    StringTokenizer st = new StringTokenizer(lineaLeida);
                    String[] oraciones = lineaLeida.split("[.!?]"); //esta linea divide el texto en oraciones individuales basadas en los signos de puntuacion comunes que indican el final de una oracion en ingles

                    // AUMENTA EL CONTADOR DE LINEAS Y DE PALABRAS
                    lineasTotales = lineasTotales + 1;
                    palabrasTotales = palabrasTotales + st.countTokens();
                    oracionesTotales += oraciones.length;

                    // CONTAR FRECUENCIA DE PALABRAS
                    while (st.hasMoreTokens()) {
                        String palabra = st.nextToken().replaceAll("[^a-zA-Z]", "").toLowerCase();
                        frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                    }
                }

                // ENCONTRAR PALABRA MÁS FRECUENTE
                int maxFrecuencia = 0;
                for (Map.Entry<String, Integer> entry : frecuenciaPalabras.entrySet()) {
                    if (entry.getValue() > maxFrecuencia) {
                        maxFrecuencia = entry.getValue();
                        palabraMasFrecuente = entry.getKey();
                    }
                }

                // CALCULAR LONGITUD PROMEDIO DE LAS PALABRAS
                longitudPromedio = (double) palabrasTotales / lineasTotales;

                System.out.println("");
                System.out.println("Numero de palabras: " + palabrasTotales);
                System.out.println("Lineas totales: " + lineasTotales);
                System.out.println("Número total de oraciones: " + oracionesTotales);
                System.out.println("Palabra más frecuente: " + palabraMasFrecuente);
                System.out.println("Longitud promedio de las palabras: " + longitudPromedio);


                // CIERRA LA LECTURA
                archivoLeer.close();
            } else {
                System.out.println("NO EXISTE EL ARCHIVO");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}