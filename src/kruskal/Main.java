package kruskal;

public class Main{
    public static void main(String[] args){
        if(args.length == 1){
            String ruta = args[0];
            Archivo archivo = new Archivo(ruta);
            System.out.print("\n\n===================================== ALGORITMO KRUSKAL =====================================\n\n");
            archivo.leerArchivo();
        }else{
            System.err.println("Agregue un archivo .txt");
        }
    }
}