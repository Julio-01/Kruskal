package kruskal;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Archivo {
    
    String ruta; // La ruta del archivo
    File archivo; // El archivo
    Grafica grafica = new Grafica();


    /* 
    * Constructor del Archivo que cargaremos
    * @param ruta - la ruta del archivo
    */
    public Archivo(String ruta) {
        this.ruta = ruta;
        this.archivo = new File(this.ruta);
    }


    /* 
    * Metodo que lee el archivo y separa las secuencias con su elemento a buscar.
    * La separacion de elementos sera por medio de una coma y la separacion de las secuencias sera
    * por medio de salto de linea.
    */
    public void leerArchivo() {
        try {
            FileReader fr = new FileReader(this.archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int repeticiones = 0;
            while ((linea = br.readLine()) != null) {
                String[] elementos = linea.split(" ");
                for (int i = 0; i < elementos.length; i++) {
                    // Agregaremos los vertices de la primer linea
                    if(repeticiones == 0){
                        System.out.println("\n---Agregando vertices\n");
                        String[] vertices = elementos[i].split(",");
                        for(int j = 0; j < vertices.length; j++){
                            Integer numero = Integer.parseInt(vertices[j]);
                            System.out.println("Agregando el vertice: " + numero);
                            grafica.agregarVertice(numero);
                        }
                        repeticiones++;
                    }else{
                    // Agregamos los aristas de las demas lineas
                        if(repeticiones == 1){
                            System.out.println("\n\n---Agregando aristas\n");
                            repeticiones++;
                        }
                        String[] aristas = elementos[i].split(":");
                        agregarAristas(aristas);
                    }

                }
            }
            br.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        System.out.println("\n===================================== GRAFICA =====================================\n");
        System.out.println("# Los vertices de nuestra grafica son:\n");
        grafica.toStringVertice();
        System.out.println("\n# Las aristas de nuestra grafica son:\n");
        grafica.toStringArista();
        System.out.println("\n===================================== ALGORITMO =====================================\n");
        Kruskal kruskal = new Kruskal(grafica);
        kruskal.kruskal();
    }


    /* 
    * Metodo que divide la cadena en la informacion que necesitamos: los vertices y el peso. 
    * @param aristas - la cadena de la arista sin dividir.
    */
    public void agregarAristas(String[] aristas){
        if(aristas.length != 2){
            System.err.println("Error al agregar las aristas. Escriba la arista valida. Ejemplo: \"1,2\"");
            System.exit(0);
        }
        for(int i = 0; i < aristas.length; i = i+2){
            String[] vertices = aristas[i].split(",");
            agregarVertices(vertices, Integer.parseInt(aristas[i+1]));
        }
    }


    /* 
    * Metodo que divide la cadena en la informacion que necesitamos: vertice origen y vertice destino.
    * Posteriormente agregamos la arista con su respectiva informacion: vertice origen, vertice destino y peso.
    * @param vertices - la cadena de vertices sin dividir.
    * @param peso - el peso de la arista
    */
    public void agregarVertices(String[] vertices, int peso){
        if(vertices.length != 2){
            System.err.println("Error al agregar los vertices. Escriba el vertice valido continuo de una coma \",\".");
            System.exit(0);
        }
        for(int i = 0; i < vertices.length; i = i+2){
            Integer vertice1 = Integer.parseInt(vertices[i]);
            Integer vertice2 = Integer.parseInt(vertices[i+1]);
            if(vertice1 <= vertice2){
                grafica.agregarArista(vertice1, vertice2, peso);
            }else{
                grafica.agregarArista(vertice2, vertice1, peso);
            }
        }
    }
}