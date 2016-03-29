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
            System.out.println(Memoria[i]+" "+i);
        }
        
    }
    
    
    
    public void mostrarMemoria(Procesos[] Memoria)
    {
        for (int i = 0; i < Memoria.length ; i++) 
        {
            if (Memoria[i] != null)
            {
                System.out.println("["+Memoria[i].getNombre()+"]");
            }
            else
            {
                System.out.println("["+Memoria[i]+"]");
            }
        }
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
    
    public void eliminarProceso(Procesos[] Memoria, String nombre)
    {
        int cont=1;
        boolean proc = false;
        for (int i = 0; i < Memoria.length ; i++) 
        {
            if (nombre.equals(Memoria[i].getNombre())) 
            {
                System.out.println("entro al if "+cont);
                Memoria[i]=null;
                proc = true;
                cont ++;
            }
            System.out.println("repite en for: "+cont);
        } // Por aca esta el error, la impresion del contador sirve
        if(proc == true)
        {
            System.out.println("El proceso "+nombre+" fue eliminado exitosamente");
        }
        else
        {
            System.out.println("No se encontro proceso con ese nombre");
        }
        
        
    }
    
}
