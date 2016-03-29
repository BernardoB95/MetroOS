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
    
    public void eliminarProceso(Procesos[] Memoria, String nombre)
    {
        for (int i = 0; i < Memoria.length; i++) 
        {
            if(nombre == Memoria[i].getNombre())
            {
                Memoria[i] = null;
            }
        }
    }
    
    public void crearProceso(Procesos[] Memoria, String nombre)
    {
        for (int i = 0; i < Memoria.length; i++) 
        {
            if (Memoria[i] == null) 
            {
                Memoria[i].setNombre(nombre);
            }
        }
    }
    
}
