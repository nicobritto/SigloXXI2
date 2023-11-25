package Entrega2;

import javax.swing.*;
import java.util.ArrayList;

public class Usuario extends Persona{

    private int dni;
    private String nombre;

    private String apellido;

    private ArrayList<Libro> librosAlquilados;



    public Usuario(int dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.librosAlquilados=new ArrayList<Libro>();
    }

    public Usuario() {
    }

    public void alquilar(Libro libro ){
        try {
            libro.restarUnLibro();
            this.librosAlquilados.add(libro);

        }catch (RuntimeException e){
            System.out.println(e.getCause());
        }

    }
    public void devolverLibro(Libro libro) {
        for (int i = 0; i < this.librosAlquilados.size(); i++) {
            Libro libro2 = this.librosAlquilados.get(i);
            if (libro2.getIsbn() == libro.getIsbn()) {
                this.librosAlquilados.remove(i);
                break;
            }
        }
    }

    public void   registrarUsuario() {
        //dni nombre apellido
        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        String apellido = JOptionPane.showInputDialog("Ingrese apellido");

        while (nombre.isBlank() || apellido.isBlank()) {
            nombre = JOptionPane.showInputDialog("Ingrese nuevamente el nombre verifique que no este vacio");
            apellido = JOptionPane.showInputDialog("Ingrese nuevamente apellido verifique que no este vacio");
        }
        int dni = Integer.parseInt(ingresoDelDni());

       this.nombre=nombre;
       this.apellido=apellido;
        this.librosAlquilados=new ArrayList<Libro>();
        this.dni=dni;


    }

    public static String ingresoDelDni() {
        String dni;
        do {
            dni = JOptionPane.showInputDialog("Ingrese DNI de 8 dígitos");
        } while (!dni.matches("\\d{8}"));
        return dni;
    }



    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Libro> getLibrosAlquilados() {
        return librosAlquilados;
    }

    public void setLibrosAlquilados(ArrayList<Libro> librosAlquilados) {
        this.librosAlquilados = librosAlquilados;
    }

    @Override
    public void notificionInforme() {
        //----------------------------------------------------- funcion Stream -----------------------------------------------------
        long cantidadLibrosRegistrados = librosAlquilados.stream().count();

        System.out.println("Se Genera el Siguiente informe: \n el usuario "+
        this.nombre +"apellido : "+this.apellido  + "\n tiene alquilados  " +  cantidadLibrosRegistrados +" Libros \n"
        );

    }
}
