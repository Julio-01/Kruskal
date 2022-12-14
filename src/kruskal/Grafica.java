package kruskal;

import java.util.ArrayList;

public class Grafica{


    public class Vertice{

        private int elemento; // El elemento del vertice

        /* 
        * Constructor del Vertice
        * @param elemento - el elemento del vertice
        */
        private Vertice(int elemento){
            this.elemento = elemento;
        }

        /* 
        * Metodo para obtener el elemento del vertice
        * @return int - el elemento del vertice
        */
        public int getElemento(){
            return elemento;
        }
    }


    public class Arista{

        private Vertice origen, destino; // Vertice origen y destino
        private int peso; // Peso de la arista


        /* 
        * Constructor de la Arista
        * @param origen - el vertice donde inicia
        * @param destino - el vertice donde termina
        */
        private Arista(Vertice origen, Vertice destino, int peso){
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }


        /* 
        * Metodo para obtener el vertice origen
        * @return Vertice - el vertice origen
        */
        public Vertice getOrigen(){
            return origen;
        }


        /* 
        * Metodo para obtener el vertice destino
        * @return Vertice - el vertice destino
        */
        public Vertice getDestino(){
            return destino;
        }


        /* 
        * Metodo para obtener el elemento del vertice origen
        * @return int - el elemento del vertice origen
        */
        public int getPeso(){
            return peso;
        }


        /* 
        * Metodo para imprimir los datos de la arista: origen, destino y peso
        */
        public void imprimirArista(){
            int origen = getOrigen().getElemento();
            int destino = getDestino().getElemento();
            System.out.println(origen + ", " + destino + " : " + getPeso());
        }

    }

    
    ArrayList<Vertice> vertices; //Los vertices de la grafica
    ArrayList<Arista> aristas; //Las aristas de la grafica

    /* 
    * Constructor de la Grafica
    */
    public Grafica(){
        this.vertices = new ArrayList<Vertice>();
        this.aristas = new ArrayList<Arista>();
    }


    /* 
    * Metodo obtiene cuantos vertices hay en la grafica
    * @return int - numero de vertices de la grafica
    */
    public int getVerticesSize(){
        return vertices.size();
    }


    /* 
    * Metodo para obtener los vertices de la grafica.
    * @return ArrayList<Vertice> - lista de vertices de la grafica
    */
    public ArrayList<Vertice> getVertices(){
        return vertices;
    }


    /* 
    * Metodo para obtener las aristas de la grafica.
    * @return ArrayList<Arista> - la lista de aristas de la grafica
    */
    public ArrayList<Arista> getAristas(){
        return aristas;
    }


    /* 
    * Metodo para agregar vertices a la grafica
    * @param elemento - el elemento a agregar
    */
    public void agregarVertice(int elemento){
        Vertice nuevo = new Vertice(elemento);
        if(vertices.contains(nuevo)){
            System.out.println("El vertice (" + elemento + ") ya esta agregado. Lo omitiremos.");
        }else{
            vertices.add(nuevo);
        }
    }

    /* 
    * Metodo para agregar aristas a la grafica
    * @param elemento1 - el vertice origen a conectar
    * @param elemento2 - el vertice destino a conectar
    * @param peso - el peso de la arista
    */
    public void agregarArista(int elemento1, int elemento2, int peso){
        if(peso < 0){
            System.err.println("Agrega aristas con pesos positivos");
            System.exit(1);
        }else{
            Vertice origen = new Vertice(elemento1);
            Vertice destino = new Vertice(elemento2);
            if(contieneVertice(origen) && contieneVertice(destino)){
                Arista nuevo = new Arista(origen, destino, peso);
                if(contieneArista(nuevo)){
                    System.out.println("La arista (" + elemento1 + ", " + elemento2 + ") ya esta agregado. Lo omitiremos.");
                }else{
                    aristas.add(nuevo);
                }
            }else{
                System.out.println("Agrega aristas con vertices que ya existan");
            }
        }
    }


    /* 
    * Metodo que regresa si contiene la grafica el vertice.
    * @param vertice - el vertice a buscar
    * @return true si lo contiene, false en otro caso
    */
    public boolean contieneVertice(Vertice vertice){
        int v = vertice.getElemento();
        for(int i = 0; i < vertices.size(); i++){
            Vertice comp = vertices.get(i);
            if(v == comp.getElemento()){
                return true;
            }
        }
        return false;
    }


    /* 
    * Metodo que regresa si contiene la grafica la arista.
    * @param arista - la arista a buscar
    * @return true si lo contiene, false en otro caso
    */
    public boolean contieneArista(Arista arista){
        int v1 = arista.getOrigen().getElemento();
        int v2 = arista.getDestino().getElemento();
        for(int i = 0; i < aristas.size(); i++){
            Arista comp = aristas.get(i);
            int c1 = comp.getOrigen().getElemento();
            int c2 = comp.getDestino().getElemento();
            if(v1 == c1 && v2 == c2)
                return true;
        }
        return false;
    }


    /* 
    * Metodo para imprimir la cadena de los vertices de la grafica
    */
    public void toStringVertice(){
        for(int i = 0; i < vertices.size(); i++){
            Vertice vertice = vertices.get(i);
            int n = vertice.getElemento();
            System.out.println("- " + n);
        }
    }


    /* 
    * Metodo para imprimir la cadena de las aristas de la grafica
    */
    public void toStringArista(){
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