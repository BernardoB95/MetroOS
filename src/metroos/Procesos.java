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
public class Procesos {
    
    //Atributos
    private String nombre;
    private int tamano;
    
    //Constructor
    public Procesos(String nombre, int tamano)
    {
        this.nombre = nombre;
        this.tamano = tamano;
    }
    
    public Procesos()
    {
        
    }

    //Setter & Getter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    
    //Calcula la cantidad de paginas necesarias
    public int calcularPagina(int tamanoFragmento, int tamanoProceso)
    {       
        int pagina = tamanoProceso / tamanoFragmento;
        
        if(tamanoProceso % tamanoFragmento == 0)
        {
            return pagina;
        }
        else
        {
            int enteroPagina = (int) pagina +1;     //Funcion Parte entera mas uno
            return enteroPagina;
        }
        
    }
    
    // Crea Proceso 
    public void crearProceso(int calcularPagina, Procesos[] Memoria, Procesos P)
    {
        int cont = 0;
        for (int i = 0; i < Memoria.length; i++) 
        {
            if(Memoria[i] == null)
            {
                cont++;
            }
        }
        
        if(cont >= calcularPagina)                      // Validacion de Espacio en Memoria
        {
            for (int i = 0; i < Memoria.length; i++)
            {
                if(calcularPagina>0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
                {
                    Memoria[i] = P;
                    calcularPagina--;
                }
            }
        }
        else
        {
            System.out.println("No hay suficiente memoria");
        }
        
        System.out.println("Proceso creado exitosamente");
        
    }
    
    public void eliminarProceso(Procesos[] Memoria, String nombre)
    {
        boolean proc = false;
        for (int i = 0; i < Memoria.length ; i++) 
        {
            if (nombre == Memoria[i].getNombre()) 
            {
                Memoria[i]=null;
                proc = true;
            }
        }
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
