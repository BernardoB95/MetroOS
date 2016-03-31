/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroos;

/**
 *
 * @author Bernardo
 */
public class Memoria {
    
    private Procesos[] Memoria;
    private int Fragmentacion;
    
    
    //Constructor
    public Memoria(Procesos[] Memoria)
    {
        this.Memoria = Memoria;
    }
    
    public Memoria()
    {
        
    }

    
    // Setter & Getter
    public Procesos[] getMemoria() {
        return Memoria;
    }

    public void setMemoria(Procesos[] Memoria) {
        this.Memoria = Memoria;
    }

    public int getFragmentacion() {
        return Fragmentacion;
    }

    public void setFragmentacion(int Fragmentacion) {
        this.Fragmentacion = Fragmentacion;
    }
    

    
    //Asignacion de Tamaño a Memoria
    public Procesos[] asignarTamano(int Cantidad, Procesos[] Memoria, int fragmentacion)
    {
        int paginas = Cantidad/fragmentacion;
        return Memoria = new Procesos[paginas];
        
    }
    
    
    
    //Llenar la memoria
    public void llenarMemoria(Procesos[] Memoria)
    {
        int paginas = Memoria.length;
        for (int i = 0; i < paginas ; i++) 
        {            
            Memoria[i]=null;
        }
        
    }
    
    
    
    
    public void mostrarMemoria(Procesos[] Memoria)
    {
        
        for (int i = 0; i < Memoria.length ; i++) 
        {
            if (Memoria[i] != null)
            {
                if(Memoria[i].isBloqueado() == true && Memoria[i].isSuspendido() == true){
                    System.out.print((char)27 + "[46m"+ "["+Memoria[i].getNombre()+"] " + (char)27 + "[0m");
                }else if(Memoria[i].isSuspendido() == true){
                    System.out.print("["+(char)27 + "[35m"+Memoria[i].getNombre() + (char)27 + "[0m"+"] ");
                }else if(Memoria[i].isBloqueado() == true){
                    System.out.print("["+(char)27 + "[31m"+Memoria[i].getNombre() + (char)27 + "[0m"+"] ");
                }else{
                    System.out.print("["+Memoria[i].getNombre()+"] ");
                }
            }
            else
            {
                System.out.print("[ ] ");
            }
        }
        System.out.println("");
    }
    
    
    public void crearProceso(Procesos[] Memoria, String nombre) // Desuso
    {
        for (int i = 0; i < Memoria.length; i++) 
        {
            if (Memoria[i] == null) 
            {
                Memoria[i].setNombre(nombre);
            }
        }
    }
    
    public Procesos buscarProceso(Procesos[] Memoria,String nombre)
    {
        Procesos proc = new Procesos();
        for (int i = 0; i < Memoria.length; i++) 
        {
            if(Memoria[i].getNombre() == nombre)
                proc = Memoria[i];
        }
        return proc;
        
        
    }
    
    public int eliminarProceso(Procesos[] Memoria, String nombre)
    {
        int cont=1;
        boolean proc = false;
        int tam = 0;
        for (int i = 0; i < Memoria.length ; i++) 
        {   
            if (Memoria[i] != null) {
                if (nombre.equals(Memoria[i].getNombre())) {
                    tam = Memoria[i].getTamano();
                    Memoria[i]=null;
                    proc = true;
                    cont ++;
                }
            }
        } // Por aca esta el error, la impresion del contador sirve
//        if(proc == true)
//        {
//            System.out.println("El proceso "+nombre+" fue eliminado exitosamente");
//        }
//        else
//        {
//            System.out.println("No se encontro proceso con ese nombre");
//        }
        
        return tam;
    }
    
    public void bloquear(Procesos[] Memoria, String nombre){
        for (int i = 0; i < Memoria.length; i++) {
            if (Memoria[i] != null) {
                if (Memoria[i].getNombre().equals(nombre) && Memoria[i].isSuspendido() == false) {
                    Memoria[i].setBloqueado(true);
                }
            }
            
        }
    }
    public int getMarcoPag(Procesos[] Memoria){
        int cont = 0;
        for (int i = 0; i < Memoria.length; i++) {
            if(Memoria[i] != null){
                cont++;
            }
        }
        return cont;
    }
    
    public int getMemoriaDisp(Procesos[] Memoria, Procesos[] MemoriaR){
        int cont = 0;
        int contR = 0;
        
        for (int i = 0; i < Memoria.length; i++) 
        {
            if(Memoria[i] == null)
            {
                cont++;
            }
        }
        
        for (int i = 0; i < MemoriaR.length; i++) 
        {
            if(MemoriaR[i] == null)
            {
                contR++;
            }
        }
        return (cont + contR);
    }
    
    public void desbloquear ( Procesos[] Memoria, String nombre)
    {
        for (int i = 0; i < Memoria.length; i++) 
        {
            if (Memoria[i] != null) 
            {
                if (Memoria[i].isBloqueado() == true) 
                {
                    Memoria[i].setBloqueado(false);
                }
                else if(Memoria[i].getNombre().equals(nombre) && Memoria[i].isBloqueado() == false)
                {
                    System.out.println("El proceso ["+nombre+"] actualmente se encuentra desbloqueado");
                }
                else
                {
                    System.out.println("No se encontro proceso con ese nombre");
                }
            }
        }
    }
  
//    public void suspender(Procesos[] Memoria, String nombre)
//    {
//        for (int i = 0; i < Memoria.length; i++) 
//        {
//            if(Memoria[i] != null)
//            {
//                if(Memoria[i].getNombre().equals(nombre))
//                {
//                    Memoria[i].setSuspendido(true);
//                    
//                }
//            }
//        }
//    }
    
    public void Suspender (Procesos[] RAM, Procesos [] Virtual, String nombre)
    {
        int cont = 0;
        for (int i = 0; i < RAM.length; i++) 
        {
            if(RAM[i] != null)
            {
                if (RAM[i].getNombre().equals(nombre)) 
                {
                    while(Virtual[cont] != null){
                        cont++;
                    }
                    if(Virtual[cont] == null)
                    {
                      Virtual[cont] = RAM[i];
                      RAM[i] = null;
                        System.out.println("RAM se suspendio en: "+i+" y yahora es: "+RAM[i]);
                      System.out.println("Status "+ Virtual[cont].getNombre());
                      Virtual[cont].setSuspendido(true);
                    }
                    
//                    for (int j = 0; j < Virtual.length; j++) 
//                    {
//                        if(Virtual[j] == null)
//                        {
//                          Virtual[j] = RAM[i];
//                          RAM[i] = null;
//                            System.out.println("Status "+ Virtual[j].getNombre());
//                          Virtual[j].setSuspendido(true);
//                        }
//                    }
                }
            }
        }
    }
}
