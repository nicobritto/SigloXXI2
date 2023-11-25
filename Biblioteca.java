package Entrega2;

import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private ArrayList<Libro> biblioteca;

    public Biblioteca() {
        this.biblioteca = new ArrayList<Libro>();
    }

    public ArrayList getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(ArrayList biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void agregarLibro(Libro libro) {
        this.biblioteca.add(libro);
    }


    public void eliminarLibroPorIsbn(int isbn) {
        int índice = -1;
        for (int i = 0; i < biblioteca.size(); i++) {
            Libro librito = biblioteca.get(i);
            if (librito.getIsbn() == isbn) {
                //System.out.println(encontrado);
                índice = i;
                break;
            }
        }
        if (índice!=-1) {
                //libro.restarUnLibro();
                this.biblioteca.remove(índice);

        } else {
            throw new RuntimeException("El libro con el isbn " + isbn + " no existe");
        }

    }

/*
    public void restarUnLibroEnBiblioteca(Libro libro){

        final boolean[] encontrado = {false};

        this.biblioteca.stream()
                .filter(lib -> lib.getIsbn() == libro.getIsbn())
                .forEach(lib -> {
                    System.out.println(lib.getNumeroDeCopias() + " número de copias antes de restar");
                    lib.restarUnLibro();
                    System.out.println(lib.getNumeroDeCopias() + " número de copias después de restar");
                    encontrado[0] = true;

        });
    }
*/
    public void devolverLibroABiblioteca(Biblioteca biblioteca,int codigo,Usuario usuario){
    Libro libro=new Libro();
        if( libro.buscarLibro(biblioteca.getBiblioteca(),codigo).isPresent()){
            this.biblioteca.forEach(lib -> {
                if (lib.getIsbn() == codigo) {
                    lib.devolverLibro();
                    usuario.devolverLibro(lib);
                }
            });
        }else{
            throw new RuntimeException("Libro no encontrado");
        }
    }
    public void buscarLibroPor(String buscarPor){
//------------------------Funcion Stream ----------------------------------------------------------------------
        List<Libro> librosEncontrados = this.biblioteca.stream()
                .filter(libro ->libro.getNombnre().toLowerCase().contains(buscarPor.toLowerCase()) ||
                                libro.getGenero().toLowerCase().contains(buscarPor.toLowerCase()) ||
                                libro.getGenero().toLowerCase().contains(buscarPor.toLowerCase())
                )
                .collect(Collectors.toList());

        if (!librosEncontrados.isEmpty()) {
            System.out.println("Libros encontrados " + buscarPor );
//------------------------Funcion Stream ----------------------------------------------------------------------
            librosEncontrados.forEach(System.out::println);
         /*   for (Libro libro : librosEncontrados) {
                System.out.println(libro.getNombnre() + " - " + libro.getGenero() + "-"+libro.getIsbn());
            }*/
        } else {
            System.out.println("No se encontraron libros " + buscarPor );
        }

    }

    public void alquilarLibro(int isbn,Usuario usuario,Biblioteca biblioteca){
        Libro libro=new Libro();

        if( libro.buscarLibro(biblioteca.getBiblioteca(),isbn).isPresent()){
           for (int i=0;i<biblioteca.getBiblioteca().size();i++){
               Libro lib = this.biblioteca.get(i);
               if (lib.getIsbn() == isbn) {
                   usuario.getLibrosAlquilados().add(lib);
                   lib.restarUnLibro();
                    break;
               }
           }
        }else{
            throw new RuntimeException("Libro no encontrado");
        }

    }

    public void mostrarLibros() {

        if(this.biblioteca.size()>0){
            //----------------------------programacion funciona----------------------------------------------------
            biblioteca.forEach(System.out::println);
        }else{
            System.out.println("la biblioteca no tiene libros");
        }


    }


}
