package Entrega2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class Libro {
    private int isbn;

    private String nombnre;

    private int numeroDeCopias;

    private String genero;

    public Libro(int isbn, String nombnre, int numeroDeCopias, String genero) {
        this.isbn = isbn;
        this.nombnre = nombnre;
        this.numeroDeCopias = numeroDeCopias;
        this.genero = genero;
    }

    public Libro() {
    }


    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getNombnre() {
        return nombnre;
    }

    public void setNombnre(String nombnre) {
        this.nombnre = nombnre;
    }

    public int getNumeroDeCopias() {
        return numeroDeCopias;
    }

    public void setNumeroDeCopias(int numeroDeCopias) {
        this.numeroDeCopias = numeroDeCopias;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn=" + isbn +
                ", nombnre='" + nombnre + '\'' +
                ", numeroDeCopias=" + numeroDeCopias +
                ", genero='" + genero + '\'' +
                '}';
    }

    public void crearLibro() {

        int isbnInt =    ingresarNumeroDeLibro("Ingrese isbn");
        String nombre = JOptionPane.showInputDialog("Ingrese nombre");
        int copiasInt =  ingresarNumeroDeLibro("Ingrese N° de copias");
        String genero = JOptionPane.showInputDialog("Ingrese Genero");
        this.isbn = isbnInt;
        this.nombnre = nombre;
        this.numeroDeCopias = copiasInt;
        this.genero = genero;
    }

    public static int ingresarNumeroDeLibro(String mensaje) {
        while (true) {
            try {
                String opcion = JOptionPane.showInputDialog(mensaje);
                int opcionInt = Integer.parseInt(opcion);
                return opcionInt;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            }
        }
    }
    public Optional<Libro> buscarLibro(ArrayList<Libro> biblioteca, int codigo) {
        //----------------------------------------------------- funcion Stream -----------------------------------------------------
        Optional<Libro> libro = biblioteca.stream().filter(libro1 -> libro1.getIsbn() == codigo).findFirst();
        return Optional.ofNullable(libro.orElse(null));
    }



    public void restarUnLibro() {
        if (this.numeroDeCopias > 0) {
            this.numeroDeCopias = this.numeroDeCopias - 1;
        } else {
            throw new RuntimeException("Error  caantidad de libro es 0");
        }
    }

    public void devolverLibro() {
        this.numeroDeCopias = this.numeroDeCopias + 1;
    }


}
