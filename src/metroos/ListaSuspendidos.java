/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metroos;

import java.util.ArrayList;

/**
 *
 * @author Bernardo
 */


public class ListaSuspendidos {
    
    private ArrayList<Procesos> Suspendidos = new ArrayList<Procesos>();    //Vamos a manejar Suspendidos como una lista

    
    //Constructor
    public ListaSuspendidos() {
    }

    
    //Setter & Getter
    public ArrayList<Procesos> getSuspendidos() {
        return Suspendidos;
    }

    public void setSuspendidos(ArrayList<Procesos> Suspendidos) {
        this.Suspendidos = Suspendidos;
    }
    
    
    public void asignarSuspendido(Procesos[] Virtual, Procesos[] RAM, String nombre) //Metodo para sacar de memoria y agregar a lista
    {
        
        for (int i = 0; i < Virtual.length; i++) 
        {
            System.out.println("Entro en el for virtual");
            if (Virtual[i]!=null) 
            {
                System.out.println("Entro en if de Virtual");
                if(nombre.equals(Virtual[i].getNombre()))
                {
                    Suspendidos.add(Virtual[i]);
                    Virtual[i] = null;
                }
            }
            else{}
        }
        for (int i = 0; i < RAM.length; i++) 
        {
            System.out.println("Entro en el for ram");
            if(RAM[i]!= null)       //--> Aca es donde esta dando el error ///// Error puede ser en el for
            {
                System.out.println("Entro en if de RAM");   // Entra en este print
                if(nombre.equals(RAM[i].getNombre()))
                {
                    System.out.println("Entro en segundo if"+i); // Entra en este print
                    Suspendidos.add(RAM[i]);
                    RAM[i]=null;
                }
            }
            else{}
        }
    }
    
    public void mostrarSuspendidos(ListaSuspendidos Suspendidos)
    {
        for (int i = 0; i < Suspendidos.getSuspendidos().size(); i++) 
        {
            System.out.print(" ["+Suspendidos.getSuspendidos().get(i)+"] |");
        }
    }
    
}
