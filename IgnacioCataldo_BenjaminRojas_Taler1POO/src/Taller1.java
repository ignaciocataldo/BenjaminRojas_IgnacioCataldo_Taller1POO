// NOMBRES: BENJAMIN ROJAS / IGNACIO CATALDO
// RUT: 22.086.016-7 / 21.944.808-2
// CARRERAS: ICCI / ICCI
// Taller N°1 POO

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Taller1 {
	
	//Menu admin
	static void menuAdmin(Scanner opcion, double[][] matrizMetricas, int cantidadExperimentos, String[] metricas) {
		int opcionMenu;
		do {
			System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1) Ver la matriz completa de métricas");
            System.out.println("2) Identificar el experimento con mejor F1-Score");
            System.out.println("3) Calcular promedio global de cada métrica");
            System.out.println("4) Comparar dos experimentos lado a lado");
            System.out.println("5) SALIR");
            System.out.print("Elige opción: ");
            opcionMenu = opcion.nextInt();
           
            if (opcionMenu == 1) {
                for(int filas = 0; filas < cantidadExperimentos; filas++) {
                	for(int columnas = 0; columnas < cantidadExperimentos; columnas++) {
                		System.out.print(matrizMetricas[filas][columnas] + " ");
                	}
                	System.out.println("");
                }
            } else if (opcionMenu == 2) {
            	double mejorF1score = -1;
            	int indiceMejorExperimento = -1;
            	for (int i = 0; i < cantidadExperimentos; i++) {
                    double f1score = matrizMetricas[i][indiceF1score];
                    if (f1score > mejorF1score) {
                    	mejorF1score = f1score;
                    	indiceMejorExperimento = i;
                    }
            	}
            	 System.out.println("El mejor F1-Score es: " + mejorF1score);
            	    System.out.println("Corresponde al experimento numero: " + (indiceMejorExperimento + 1));
            } else if (opcionMenu == 3) {
            	double sumaAccuracy = 0;
                double sumaPrecision = 0;
                double sumaRecall = 0;
                double sumaF1Score = 0;
                
                for (int i = 0; i < cantidadExperimentos; i++) {
                    sumaAccuracy += matrizMetricas[i][indiceAcurracy];  // Suma Accuracy
                    sumaPrecision += matrizMetricas[i][indicePrecision];  // Suma Precision
                    sumaRecall += matrizMetricas[i][indiceRecall];  // Suma Recall
                    sumaF1Score += matrizMetricas[i][indiceF1score];  // Suma F1-Score
                }
                double promedioAccuracy = sumaAccuracy / cantidadExperimentos;
                double promedioPrecision = sumaPrecision / cantidadExperimentos;
                double promedioRecall = sumaRecall / cantidadExperimentos;
                double promedioF1Score = sumaF1Score / cantidadExperimentos;
                System.out.println("Promedio global de Accuracy: " + promedioAccuracy);
                System.out.println("Promedio global de Precision: " + promedioPrecision);
                System.out.println("Promedio global de Recall: " + promedioRecall);
                System.out.println("Promedio global de F1-Score: " + promedioF1Score);
            } else if (opcionMenu == 4) {
            	Scanner exp = new Scanner(System.in);
            	System.out.print("Numero de exprimento 1: ");
            	int experimento1 = exp.nextInt();
            	
            	System.out.print("Numero de exprimento 2: ");
            	int experimento2 = exp.nextInt();
            	System.out.println("              Ex1         Ex2");
            	for(int columnas = 0; columnas < 4; columnas++) {
                	System.out.println(metricas[columnas] + "  :     " + matrizMetricas[experimento1-1][columnas] + " " + matrizMetricas[experimento1-1][columnas]);
                }
            }
		} while (opcionMenu != 5);
	}
	
	//Menu Usuario
	static void menuUsuario(Scanner opcion, int [][] matrizConfusion, int cantidadExperimentos, double[][] matrizMetricas, String[] experimentos, String[] metricas) {
        int opcionMenu;
        do {
            System.out.println("\n--- MENU USUARIO ---");
            System.out.println("1) Ver lista de experimentos");
            System.out.println("2) Mostrar matriz de confusión de un experimento");
            System.out.println("3) Ver métricas de un experimento");
            System.out.println("4) Ver promedio de Accuracy de todos los modelos");
            System.out.println("5) SALIR");
            System.out.print("Elige opción: ");
            opcionMenu = opcion.nextInt();

            if (opcionMenu == 1) {
            	for(int i = 0; i < cantidadExperimentos; i++) {
            		System.out.println("Exp" + (i+1) + " - " + experimentos[i]);
            	}
            } else if (opcionMenu == 2) {
            	 for(int filas = 0; filas < cantidadExperimentos; filas++) {
                 	for(int columnas = 0; columnas < cantidadExperimentos; columnas++) {
                 		System.out.print(matrizConfusion[filas][columnas] + " ");
                 	}
                 	System.out.println("");
                 }
            } else if (opcionMenu == 3) {
            	System.out.print("Número de experimento: ");
                int experimentoNumero = opcion.nextInt() - 1;
                System.out.println("Métricas del experimento: " + (experimentoNumero+1));
                for(int columnas = 0; columnas < 4; columnas++) {	
                	System.out.print(metricas[columnas] + ": "+ matrizMetricas[experimentoNumero][columnas] + " ");
                }
                
            } else if (opcionMenu == 4) {
            	double suma = 0;
            	for (int i = 0; i < cantidadExperimentos; i++) {
            		suma += matrizMetricas[i][indiceAcurracy];
            	}
            	double promedio = suma / cantidadExperimentos;
            	System.out.println("Promedio de Accuracy de todos los modelos: " + promedio);
            }
        } while (opcionMenu != 5);
    }
    // Indices de las columnas para la matriz confusin
    static final int indiceTruePositives = 0;
    static final int indiceFalsePositives = 1;
    static final int indiceTrueNegatives = 2;
    static final int indiceFalseNegatives = 3;
    
    // Indices de las columnas para la matriz de metricas
    static final int indiceAcurracy = 0;
    static final int indicePrecision = 1;
    static final int indiceRecall = 2;
    static final int indiceF1score = 3;
    
    // Funcion que agrega los valores a la matriz confusion
    static void agregarValores(int real, int predicho, int[][] matrizConfusion, int indiceExperimento) {
    	if (real == 1 && predicho == 1) {
    		matrizConfusion[indiceExperimento][indiceTruePositives]++;
    	} else if (real == 0 && predicho == 1) {
    		matrizConfusion[indiceExperimento][indiceFalsePositives]++;
    	} else if (real == 0 && predicho == 0) {
    		matrizConfusion[indiceExperimento][indiceTrueNegatives]++;
    	} else if (real == 1 && predicho == 0) {
    		matrizConfusion[indiceExperimento][indiceFalseNegatives]++;
    	}
    }
    
    // Funcion para calcular las metricas de la matriz metricas
    static void calcularMetricas(int[][] matrizConfusion, double[][]matrizMetricas, int cantidadExperimentos) {
    	for (int i = 0; i < cantidadExperimentos; i++) {
    		double truePositives = matrizConfusion[i][indiceTruePositives];
    		double falsePositives = matrizConfusion[i][indiceFalsePositives];
    		double trueNegatives = matrizConfusion[i][indiceTrueNegatives];
    		double falseNegatives = matrizConfusion[i][indiceFalseNegatives];
    		
    		double accuracy = (truePositives + trueNegatives) / (truePositives + falsePositives + trueNegatives +falseNegatives);
    		double precision = truePositives / (truePositives + falsePositives);
    		double recall = truePositives / (truePositives + falseNegatives);
    		double f1score = 2 * (precision * recall) / (precision + recall);
    		
    		matrizMetricas[i][indiceAcurracy] = accuracy;
    		matrizMetricas[i][indicePrecision] = precision;
    		matrizMetricas[i][indiceRecall] = recall;
    		matrizMetricas[i][indiceF1score] = f1score;
    		
    		
    	}
    }
    
    
    // Leo el archivo de experimentos para obtener la cantidad de experimentos
    public static void main(String[] args) throws FileNotFoundException{
    	Scanner archivoExperimentos = new Scanner(new File("experimentos.txt"));
    	int cantidadExperimentos = 0;
    	
    	while(archivoExperimentos.hasNextLine()) {
    		archivoExperimentos.nextLine();
    		cantidadExperimentos++;
    	}
    	String experimentos[] = new String[cantidadExperimentos];
    	
    	Scanner archivoExperimentos2 = new Scanner(new File("experimentos.txt"));
    	int indiceModelo = 0;
    	while(archivoExperimentos2.hasNextLine()) {
    		String[] partes = archivoExperimentos2.nextLine().split(";");
    		experimentos[indiceModelo] = partes[1]; 
    		indiceModelo++;
    		
    	}
    	archivoExperimentos.close();
    	archivoExperimentos2.close();
    	// creo la matriz confusion con la cantidad de experimentos y columnas fijas de cada valor ya que son 4
    	int[][] matrizConfusion = new int[cantidadExperimentos][4];
    	double[][] matrizMetricas = new double[cantidadExperimentos][4];
    	String[] metricas = new String[4];
    	
    	Scanner archivoPredicciones = new Scanner(new File("predicciones.txt"));
    	
    	while (archivoPredicciones.hasNextLine()) {
    		String[] partes = archivoPredicciones.nextLine().split(";");
    		String experimento = partes[0];
    		int real = Integer.parseInt(partes[1]);
    		int predicho = Integer.parseInt(partes[2]);
    		
    		//separo de la variable experimento el string con el numero y le resto 1 para obtener el indice
    		int indiceExperimento = Integer.parseInt(experimento.replace("Exp", ""))-1;
    		
    		agregarValores(real, predicho, matrizConfusion, indiceExperimento);
    	}
    	
    	int indiceMetrica=0;
		Scanner archivoMetricas = new Scanner(new File("metricas.txt"));
		while(archivoMetricas.hasNextLine()) {
    		String metrica = archivoMetricas.nextLine();
    		metricas[indiceMetrica] = metrica;
    		indiceMetrica++;
    	}
		archivoMetricas.close();
		archivoPredicciones.close();
    	calcularMetricas(matrizConfusion, matrizMetricas, cantidadExperimentos);
    	
    	Scanner opcion = new Scanner(System.in);
    	int opcionMenu;
    	
    	do {
    		System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1) Menú Admin");
            System.out.println("2) Menú Usuario");
            System.out.println("3) SALIR");
            System.out.print("Elige opción: ");
            opcionMenu = opcion.nextInt();

            if (opcionMenu == 1) {
                menuAdmin(opcion, matrizMetricas, cantidadExperimentos, metricas);
            } else if (opcionMenu == 2) {
                menuUsuario(opcion, matrizConfusion, cantidadExperimentos, matrizMetricas, experimentos, metricas);
            }
    	} while (opcionMenu != 3);
    		System.out.println("Saliendo del programa...");
        opcion.close();
    		
    }
}
