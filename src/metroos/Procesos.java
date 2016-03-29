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
    
    // ------ mover procsos dentro de mv y mRam de manera ordenada a la memoria ram
    public void moverProcesos(Procesos[] Memoria,Procesos[] MemoriaR){
        int j;
        for (int i = 0; i < MemoriaR.length; i++) 
        {
            if(MemoriaR[i] == null)
            {
                j = i;
                do{
                    if (MemoriaR[j] != null) {
                        MemoriaR[i] = MemoriaR[j];
                        
                    }
                    j++;
                }while (MemoriaR[j] == null || j == MemoriaR.length);
                MemoriaR[j] = null;
            }
            if (MemoriaR[i] == null) {
                j = 0;
                do{
                    if (Memoria[j] != null) {
                        MemoriaR[i] = Memoria[j];
                        
                    }
                    j++;
                }while (MemoriaR[j] == null || j == Memoria.length );
                Memoria[j] = null;
            }
        }
        
    }
    
    
    // Crea Proceso 
    public void crearProceso(int calcularPagina, Procesos[] Memoria,Procesos[] MemoriaR, Procesos P)
    {
        int cont = 0;
        int contR = 0;
        //---------- Se revisan ambos vectores de las memorias para contar espacios disponibles
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
        //--------- Se evaluan las 3 posibilidades de insercion, todo RAM, dividido y todo Virtual
        if (contR >= calcularPagina) {
            for (int i = 0; i < MemoriaR.length; i++)
            {
                if(calcularPagina > 0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
                {
                    MemoriaR[i] = P;
                    calcularPagina--;
                }
            }
            System.out.println("Proceso creado exitosamente");
            
        }else if(contR >= (calcularPagina / 2)  && (cont + contR) >= calcularPagina){
            for (int i = 0; i < MemoriaR.length; i++)
            {
                if(calcularPagina > 0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
                {
                    MemoriaR[i] = P;
                    calcularPagina--;
                }
            }
            for (int i = 0; i < Memoria.length; i++)
            {
                if(calcularPagina>0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
                {
                    Memoria[i] = P;
                    calcularPagina--;
                }
            }
            System.out.println("Proceso creado exitosamente");
        } else if (cont >= calcularPagina){
            for (int i = 0; i < Memoria.length; i++)
            {
                if(calcularPagina>0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
                {
                    Memoria[i] = P;
                    calcularPagina--;
                }
            }
            System.out.println("Proceso creado exitosamente");
        }else{
            System.out.println("No hay suficiente memoria");
        }
        
//        
//        
//        if(cont >= calcularPagina)                      // Validacion de Espacio en Memoria
//        {
//            for (int i = 0; i < Memoria.length; i++)
//            {
//                if(calcularPagina>0 && Memoria[i] == null)  // Asignacion de procesos a espacios vacios
//                {
//                    Memoria[i] = P;
//                    calcularPagina--;
//                }
//                else
//                {
////                    Memoria[i] = null;
//                }
//            }
//        }
//        else
//        {
//            System.out.println("No hay suficiente memoria");
//        }
//        
//        System.out.println("Proceso creado exitosamente");
        
    }  
}
