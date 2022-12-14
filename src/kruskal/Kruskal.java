package kruskal;

import java.util.ArrayList;

import kruskal.Grafica.Arista;
import kruskal.Grafica.Vertice;

public class Kruskal{


    private class Conjunto{

        Grafica conjunto; // El conjunto
        Vertice padre; // El padre del conjunto
        int hojas; // El numero de hojas que tiene el padre


        /* 
        * Constructor del Conjunto
        * @param conjunto - el conjunto que contiene los vertices y aristas
        * @param padre - el vertice padre del conjunto
        */
        private Conjunto(Grafica conjunto, Vertice padre){
            this.conjunto = conjunto;
            this.padre = padre;
            this.hojas = 0;
        }


        /* 
        * Metodo que obtiene la grafica del conjunto
        * @return Grafica - la grafica del conjunto
        */
        private Grafica getConjunto(){
            return conjunto;
        }


        /* 
        * Metodo que obtiene el vertice padre
        * @return Vertice - el vertice padre
        */
        private Vertice getPadre(){
            return padre;
        }


        /* 
        * Metodo que obtiene cuantas hojas tiene el conjunto
        * @return int - el numero de hojas del conjunto
        */
        private int getHojas(){
            return hojas;
        }


        /* 
        * Metodo que aumenta el numero de hojas del conjunto
        */
        private void aumentarHojas(){
            this.hojas = conjunto.getVerticesSize() - 1;
        }


        /* 
        * Metodo que agrega un vertice al conjunto
        * @param elemento - el elemento a agregar
        */
        private void agregarVertice(int elemento){
            conjunto.agregarVertice(elemento);
        }


        /* 
        * Metodo que agrega una arista al conjunto
        * @param arista - la arista a agregar
        */
        private void agregarArista(Arista arista){
            int a = arista.getOrigen().getElemento();
            int b = arista.getDestino().getElemento();
            int peso = arista.getPeso();
            conjunto.agregarArista(a , b, peso);
        }


        /* 
        * Metodo que imprime la cadena del conjunto con su informacion: padre, vertices, aristas y hojas.
        */
        private void toStringConjunto(){
            System.out.println("\n-> Padre: " + padre.getElemento());
            System.out.println("\n-> Vertices: ");
            conjunto.toStringVertice();
            System.out.println("\n-> Aristas: ");
            conjunto.toStringArista();
            System.out.println("\n-> Hojas: " + hojas);
        }
    }


    Grafica grafica; // La grafica final
    ArrayList<Conjunto> conjuntos; // Los conjuntos de kruskal
    ArrayList<Arista> aristas; // Las aristas a agregar por kruskal
    ArrayList<Vertice> vertices; // Los vertices a agregar por kruskal


    /* 
    * Constructor de Kruskal
    * Recibiremos una grafica de la cual tomaremos sus vertices y sus aristas.
    * @param grafica - grafica a implementar por kruskal
    */
    public Kruskal(Grafica grafica){
        this.grafica = new Grafica();
        this.conjuntos = new ArrayList<Conjunto>();
        this.aristas = grafica.getAristas();
        this.vertices = grafica.getVertices();
    }


    /* 
    * Metodo que implementa Kruskal. Primero crearemos conjuntos, nuestros conjuntos siempre seran independientes,
    * ordenaremos las aristas para tener primero las de menor peso, si la arista contiene 2 vertices independientes
    * entre si entonces las uniremos, en otro caso omitimos y terminando de comparar todas las aristas o teniendo
    * solo un conjunto, regresaremos la grafica final que esta implementada por kruskal.
    */
    public void kruskal(){
        crearConjuntos();
        aristas = ordenarPeso();
        int size = aristas.size();
        // Iteracion de union de conjuntos
        for(int i = 0; i < size || conjuntos.size() == 2; i++){
            System.out.println("\n----------------------------- ITERACION " + (i+1) + " -----------------------------\n");
            toStringArista();
            Arista arista = aristas.get(0);
            System.out.println("\nTomaremos la arista: ");
            arista.imprimirArista();
            int a = arista.getOrigen().getElemento();
            int b = arista.getDestino().getElemento();
            Conjunto conjunto1 = buscarConjunto(a);
            Conjunto conjunto2 = buscarConjunto(b);
            if(esIndependiente(conjunto1, conjunto2)){
                System.out.println("\nLa union de " + a + " y " + b + " es independiente. Unimos ambos vertices con sus conjuntos.");
                unirConjunto(conjunto1,conjunto2,arista);
                // Impresion de los conjuntos
                for(int j = 0; j < conjuntos.size(); j++){
                    System.out.println("\n\n---- Conjunto " + (j+1) + "----");
                    Conjunto cadena = conjuntos.get(j);
                    cadena.toStringConjunto();
                }
            }else{
                System.out.println("\nLa union de " + a + " y " + b + " no es independiente. Omitimos.");
            }
            aristas.remove(arista);
        }
        // Grafica final
        Conjunto c = conjuntos.get(0);
        System.out.println("\n\n===================================== GRAFICA KRUSKAL =====================================\n\n");
        grafica = c.getConjunto();
        System.out.println("-> Vertices:");
        grafica.toStringVertice();
        System.out.println("\n-> Aristas:");
        grafica.toStringArista();
    }


    /* 
    * Metodo para crear los conjuntos iniciales. Cada vertice sera un conjunto diferente.
    */
    private void crearConjuntos(){
        for(int i = 0; i < vertices.size(); i++){
            Grafica grafica = new Grafica();
            Vertice v = vertices.get(i);
            grafica.agregarVertice(v.getElemento());
            Conjunto conjunto = new Conjunto(grafica, v);
            conjuntos.add(conjunto);
        }
    }


    /* 
    * Metodo para ordenar las aristas por peso, de menor a mayor. 
    * @return ArrayList<Arista> - las aristas ordenadas
    */
    public ArrayList<Arista> ordenarPeso(){
        ArrayList<Arista> ordenado = new ArrayList<Arista>();
        for(int i = 0; i <= getMaximo(aristas); i++){
            for(int j = 0; j < aristas.size(); j++){
                Arista nuevo = aristas.get(j);
                if(nuevo.getPeso() == i){
                    ordenado.add(nuevo);
                }
            }
        }
        return ordenado;
    }


    /* 
    * Metodo para obtener el peso maximo de las aristas.
    * @return int - el peso maximo de las aristas
    */
    public int getMaximo(ArrayList<Arista> lista){
        if(lista.size() == 0 && lista.isEmpty()){
            return 0;
        }else{
            int maximo = 0;
            for(int i = 0; i < lista.size(); i++){
                int peso = lista.get(i).getPeso();
                if(peso > maximo){
                    maximo = peso;
                }
            }
            return maximo;
        }
    }


    /* 
    * Metodo que regresa si 2 conjuntos son independientes
    * @param conjunto1 - el conjunto 1
    * @param conjunto2 - el conjunto 2
    * @return true si son independientes, false en otro caso
    */
    public boolean esIndependiente(Conjunto conjunto1, Conjunto conjunto2){
        Vertice v1 = conjunto1.getPadre();
        Vertice v2 = conjunto2.getPadre();
        if(v1.getElemento() != v2.getElemento()){
            return true;
        }else{
            return false;
        }
    }


    /* 
    * Metodo que busca al conjunto por su elemento. Asumimos que cada conjunto tiene elementos unicos.
    * @param elemento - elemento a buscar
    * @return Conjunto - el conjunto que contiene al elemento
    */
    public Conjunto buscarConjunto(int elemento){
        for(int i = 0; i < conjuntos.size(); i++){
            Conjunto conjunto = conjuntos.get(i);
            Grafica grafica = conjunto.getConjunto();
            ArrayList<Vertice> vertice = grafica.getVertices();
            for(int j = 0; j < vertice.size(); j++){
                Vertice v = vertice.get(j);
                if(v.getElemento() == elemento){
                    return conjunto;
                }
            }
        }
        System.err.println("Sucedio algo raro. No se encontro un conjunto con el elemento " + elemento);
        return null;
    }


    /* 
    * Metodo que une 2 conjuntos dependiendo del numero de hojas, el conjunto que tenga menor hojas se unira
    * al que tiene mayor numero de hojas, modificando el padre del conjunto con menor numero de hojas
    * @param conjunto1 - el conjunto 1
    * @param conjunto2 - el conjunto 2
    * @param arista - la arista a agregar al conjunto con mayor hojas
    */
    public void unirConjunto(Conjunto conjunto1, Conjunto conjunto2, Arista arista){
        if(conjunto1.getHojas() >= conjunto2.getHojas()){
            Grafica grafica = conjunto2.getConjunto();
            ArrayList<Vertice> vertice = grafica.getVertices();
            for(int i = 0; i < vertice.size(); i++){
                conjunto1.agregarVertice(vertice.get(i).getElemento());
            }
            ArrayList<Arista> aristaA = grafica.getAristas();
            for(int i = 0; i < aristaA.size(); i++){
                conjunto1.agregarArista(aristaA.get(i));
            }
            conjunto1.agregarArista(arista);
            conjunto1.aumentarHojas();
            conjuntos.remove(conjunto2);
        }else{
            Grafica grafica= conjunto1.getConjunto();
            ArrayList<Vertice> vertice = grafica.getVertices();
            for(int i = 0; i < vertice.size(); i++){
                conjunto2.agregarVertice(vertice.get(i).getElemento());
            }
            ArrayList<Arista> aristaA = grafica.getAristas();
            for(int i = 0; i < aristaA.size(); i++){
                conjunto2.agregarArista(aristaA.get(i));
            }
            conjunto2.agregarArista(arista);
            conjunto2.aumentarHojas();
            conjuntos.remove(conjunto1);
        }
    }


    /* 
    * Metodo para imprimir la cadena de las aristas ordenada
    */
    public void toStringArista(){
        System.out.println("-> Aristas ordenadas: ");
        for(int i = 0; i < aristas.size(); i++){
            Arista arista = aristas.get(i);
            Vertice origen = arista.getOrigen();
            Vertice destino = arista.getDestino();
            int elemOrigen = origen.getElemento();
            int elemDestino = destino.getElemento();
            System.out.println(elemOrigen + ", " + elemDestino + " : " + arista.getPeso());
        }
    }
}