package metroos;

import java.util.Scanner;



public class MetroOS {

    Scanner sc = new Scanner(System.in);
    Memoria RAM = new Memoria();
    Memoria Virtual = new Memoria();
    Procesos[] memoria ;
    ListaSuspendidos Suspendidos = new ListaSuspendidos();
    int proc = 0;
    int susp = 0;
    
    
    
    public int opcionesMenu()
    {
        
        
        System.out.println("==== MetroOS ====");
        System.out.println("\n");
        System.out.println("1. Crear Proceso\n"
                + "2. Suspender Proceso\n"
                + "3. Bloquear Proceso\n"
                + "4. Desbloquear Proceso\n"
                + "5. Eliminar Proces\n"
                + "6. Reanudar procesos suspendidos\n"
                + "7. Activar proceso\n"
                + "8. Estadisticas\n"
                + "9. Direccion Logica -> Direccion Fisica\n"
                + "0. Terminar\n");
        System.out.println("Introduzca su opcion:");
        int opcion = sc.nextInt();
        return opcion;
    }
    
    public void Menu()
    {
        boolean term = true;
        while(term){
                switch(opcionesMenu())
            {
                case 1: 
                    proc++;
                    asignarProcesos();
                    break;

                case 2:
                    susp++;
                    asignarSuspender();
                    break;

                case 3: 
                    bloquearProceso();
                    break;
                case 4:
                    desbloquear();
                    break;

                case 5:
                     eliminarProcesos();
                    break;

                case 6:
                    susp--;
                    reanudar();
                    break;
                case 7:
                    reanudar();
                    break;
                case 8:
                    estadisticas();
                    break;
                case 9: 
                    System.out.println("No pudimos convertir las direcciones");
                    break;
                case 0:
                    term = false;
                    break;

                default: System.out.println("Opcion Invalida");
                    break;
            }
        }
        
        
                
    }
    
    public void estadisticas(){
        System.out.println("\n\n********************************************************");
        System.out.println("Numero de procesos creados: "+proc);
        System.out.println("Numero de marcos de pagina utilizados: "+ RAM.getMarcoPag(RAM.getMemoria()));
        System.out.println("Memoria disponible: "+ (RAM.getMemoriaDisp(RAM.getMemoria(), Virtual.getMemoria()) * RAM.getFragmentacion())+" KB");
        System.out.println("Numero de paginas utilizadas: "+ Virtual.getMarcoPag(Virtual.getMemoria()));
        System.out.println("Procesos suspendidos: "+ susp);
        System.out.println("********************************************************\n");
    }
    
    public void bloquearProceso(){
        System.out.println("Introduzca el nombre del proceso a bloquear: ");
        String nom = sc.next();
        RAM.bloquear(RAM.getMemoria(), nom);
        Virtual.bloquear(Virtual.getMemoria(), nom);
        
        print();
    }
    public void desbloquear(){
        System.out.println("Introduzca el nombre del proceso a desbloquear: ");
        String nom = sc.next();
        RAM.desbloquear(RAM.getMemoria(), nom);
        Virtual.desbloquear(Virtual.getMemoria(), nom);
        
        print();
    }
    
    public void definirMemoria()    //Creacion de Memoria Principal, Virtual y Fragmentacion
    {
        //Potencias de 2 para manejar mejor direcciones en Binario
        System.out.println("Introduzca un numero N para que esta sea la potencia de 2 deseada: Ej -> 2^N");
        System.out.println("Tamaño de Memoria Principal (KB):");
        double ram = Math.pow(2, sc.nextInt());
        System.out.println("Tamaño de Memoria Virtual (KB)");
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
        
        Virtual.setMemoria(Virtual.asignarTamano(virtualInt, memoria, fragInt));
        Virtual.llenarMemoria(Virtual.getMemoria()); 
        
        Menu();
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
        print();
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
        print();
    }
    
    public void asignarSuspender()
    {
        System.out.println("Indique el procesos que quiere suspender: ");
        String nom = sc.next();
        RAM.Suspender(RAM.getMemoria(), Virtual.getMemoria(), nom);
        
        print();
    }
    
    public void reanudar(){
        System.out.println("Indique cual proceso quiere reanudar: ");
        String nom = sc.next();
        // Hay que hacer la busqueda en memoria del proceso con ese nombre
        int aux = Virtual.eliminarProceso(Virtual.getMemoria(), nom);
        
        Procesos proc = new Procesos();
        proc.setNombre(nom);
        proc.crearProceso(proc.calcularPagina(Virtual.getFragmentacion(), aux), Virtual.getMemoria(),RAM.getMemoria() , proc);
        
        print();
        
    }
    
    public void print(){
                     
                    System.out.print("\n\n[proceso normal] ");
                    System.out.print((char)27 + "[46m"+ "[proceso suspendido y bloqueado] " + (char)27 + "[0m");
                    System.out.print("["+(char)27 + "[35m"+"proceso suspendido" + (char)27 + "[0m"+"] ");
                    System.out.println("["+(char)27 + "[31m"+"proceso bloqueado4"
                            + "6"
                            + "2"
                            + "1"
                            + "" + (char)27 + "[0m"+"] ");
                    
        System.out.println("\n==========================================================================================\n");
        System.out.println("Memoria principal:");
        RAM.mostrarMemoria(RAM.getMemoria());        
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Memoria virtual: ");
        Virtual.mostrarMemoria(Virtual.getMemoria());
        System.out.println("\n==========================================================================================\n");
        
    }
    
    
    public static void main(String[] args) {
        MetroOS m = new MetroOS();
        m.definirMemoria();
    }
    
}
