package metroos;

import java.util.Scanner;



public class MetroOS {

    Scanner sc = new Scanner(System.in);
    Memoria RAM = new Memoria();
    Memoria Virtual = new Memoria();
    Procesos[] memoria ;
    
    public int opcionesMenu()
    {
        
        System.out.println("==== MetroOS ====");
        System.out.println("\n");
        System.out.println("1. Crear Proceso"
                + "2. Detener Proceso"
                + "3. Bloquear Proceso"
                + "4. Eliminar Proces"
                + "5. Direccion Logica -> Direccion Fisica");
        System.out.println("Introduzca su opcion:");
        int opcion = sc.nextInt();
        return opcion;
    }
    
    public void Menu(int opcion)
    {
        switch(opcion)
        {
            case 1: 
                definirProcesos();
                break;
                
            case 2:
                break;
                
            case 3: 
                break;
                
            case 4:
                break;
                
            case 5:
                break;
                
            default: System.out.println("Opcion Invalida");
                break;
        }
                
    }
    
    public void definirMemoria()    //Creacion de Memoria Principal, Virtual y Fragmentacion
    {
        //Potencias de 2 para manejar mejor direcciones en Binario
        System.out.println("Introduzca un numero N para que esta sea la potencia de 2 deseada: Ej -> 2^N");
        System.out.println("Tamaño de Memoria Principal (MB):");
        double ram = Math.pow(2, sc.nextInt());
        System.out.println("Tamaño de Memoria Virtual (GB)");
        double virtual = Math.pow(2, sc.nextInt()); 
        System.out.println("Tamaño de fragmentacion de Memoria (KB)");
        double frag = Math.pow(2, sc.nextInt());
        
        int ramInt = (int) ram;
        int virtualInt = (int) virtual;
        int fragInt = (int) frag;
       
        RAM.setFragmentacion(fragInt);
        Virtual.setFragmentacion(fragInt);
        
      
        RAM.setMemoria(RAM.asignarTamano(ramInt, memoria,fragInt));
        RAM.llenarMemoria(RAM.getMemoria());
        System.out.println("===========================");
        Virtual.setMemoria(Virtual.asignarTamano(virtualInt, memoria, fragInt));
        Virtual.llenarMemoria(Virtual.getMemoria()); 
    }
    
    public Procesos definirProcesos()       // Creacion de Proceso
    {
        Procesos Proceso = new Procesos();
        System.out.println("RECORDATORIO: Los numeros introducidos son potencias de 2");
        System.out.println("Introduzca el tamano del proceso");
        double tamano = Math.pow(2, sc.nextInt());
        int tamanoInt = (int) tamano;
        Proceso.setTamano(tamanoInt);
        System.out.println("Introduzca el nombre del proceso: ");
        Proceso.setNombre(sc.next());
        return Proceso;
        
    }
    
    public void asignarProcesos()
    {
        Procesos proc = definirProcesos();
        proc.crearProceso(proc.calcularPagina(Virtual.getFragmentacion(), proc.getTamano()), Virtual.getMemoria(),RAM.getMemoria() , proc);
        RAM.mostrarMemoria(RAM.getMemoria());
        Virtual.mostrarMemoria(Virtual.getMemoria());
    }
    
    public void eliminarProcesos()
    {
        
        System.out.println("Indique cual proceso quiere eliminar: ");
        String nom = sc.next();
        // Hay que hacer la busqueda en memoria del proceso con ese nombre
        Virtual.eliminarProceso(Virtual.getMemoria(), nom);
        RAM.eliminarProceso(RAM.getMemoria(), nom);
        //Arreglar el NullPointerException
//        System.out.println(Virtual.getMemoria()[0]);
        RAM.mostrarMemoria(RAM.getMemoria());
        Virtual.mostrarMemoria(Virtual.getMemoria());
    }
    
    
    public static void main(String[] args) {
        MetroOS m = new MetroOS();
        
        m.definirMemoria();
        m.asignarProcesos();
        m.asignarProcesos();
        m.eliminarProcesos();
        
    }
    
}
