package Entrega2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        ArrayList personasRegistradas = new ArrayList<>();


        String opcion;
        int opctionInt;
        do {
            Menu();
            System.out.print("Ingrese su opcion");
            opctionInt =ingresarNumero("Ingrese una opcion");


            switch (opctionInt) {
                case 1:
                    // CREANDO EL LIBRO
                    Libro libro = new Libro();
                    libro.crearLibro();
                    //SE AGREGA EL LIBRO A LA BIBLIOTECA
                    biblioteca.agregarLibro(libro);
                    System.out.println("_ Libro agregado la biblioteca queda de la siguiente manera ");
                    biblioteca.mostrarLibros();
                    System.out.println("----------------------------------------------------------------------------------------");
                    break;
                case 2:
                    int isbn = ingresarNumero(" Ingrese el isbn del libro a eliminar ");
                    try {
                        biblioteca.eliminarLibroPorIsbn(isbn);
                        System.out.println("  Biblioteca queda de la siguiente manera ");
                        biblioteca.mostrarLibros();
                        System.out.println("----------------------------------------------------------------------------------------");
                    } catch (Exception e) {
                        System.out.printf(e.getMessage());
                    }

                    break;
                case 3:
// -------------------------CREAR EL USUARIO---------------------------------------------
                    Usuario usuario = new Usuario();
                    usuario.registrarUsuario();
                    personasRegistradas.add(usuario);
                    int opcion2;
                    do {
 // ------------------------INICIA EL MENU DEL USUARIO AALQUILAR O DEVOLVER ---------------------------------------------
                        Menu2();
                        opcion2 = ingresarNumero(" ingrese una opcion ");
                        switch (opcion2) {
                            case 1:
                                //ALQUILAR LIBRO
                                try {
                                    if (biblioteca.getBiblioteca().size() > 0) {
                                        biblioteca.mostrarLibros();
                                        int codigo = ingresarNumero(" Ingrese un codigo de libro que seria el isbn ");
                                        biblioteca.alquilarLibro(codigo, usuario, biblioteca);
                                        biblioteca.mostrarLibros();

                                    } else {
                                        System.out.println("biblioteca vacia");
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                //DEVOLVER LIBRO
                                System.out.println("libro alquilados por el usuario " + usuario.getLibrosAlquilados());
                                int codigo = ingresarNumero("Ingrese un codigo de libro que desea devolver");
                                try {
                                    biblioteca.devolverLibroABiblioteca(biblioteca, codigo, usuario);
                                    System.out.println("libro alquilados por el usuario " + usuario.getLibrosAlquilados());
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                                System.out.println(usuario.getLibrosAlquilados() + "-----------------------------");
                                break;
                            case 3:
                                //GENERAR INFORME

                                usuario.notificionInforme();
                                break;

                            default:
                                System.out.println("Opción no válida. Por favor, elija una opción válida.");
                        }


                    } while (opcion2 != 4);
                    break;
                case 4:
                    // buscar POR TITULO NOMBRE O AUTOR
                    biblioteca.mostrarLibros();
                    String bucarPor = JOptionPane.showInputDialog("ingrese el dato");
                    biblioteca.buscarLibroPor(bucarPor);
                    break;
                case 5:
                    // MOSTRAR LA BIBLIOTECA
                    biblioteca.mostrarLibros();

                    break;

                case 7:
                    // SALIR
                    System.out.println("Gracias  ");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida");
            }
        } while (opctionInt != 7);

        scanner.close();
    }


    private static void Menu() {
        System.out.println("\n--- Menú Biblioteca ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Eliminar libro");
        System.out.println("3. Registrar usuario");
        System.out.println("4. Buscar por título, autor o género ingrese alguno de los 3 título autor o género");
        System.out.println("5. Mostar la biblioteca");

    }

    public static int ingresarNumero(String mje) {
        int salir=0;
        do {
            String opcion = JOptionPane.showInputDialog(mje);
            if(esNumero(opcion)){
                int opctionInt = Integer.parseInt(opcion);
                return opctionInt;
            }
        }while (salir!=0);

        return salir;
    }

    public static boolean esNumero(Object variable) {
        try {
            Integer.parseInt(variable.toString());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void Menu2() {
        System.out.println("\n--- Menú usuarios ---");
        System.out.println("1.  Alquilar libros.");
        System.out.println("2.  Devolver los libros");
        System.out.println("3.  Generar Informe de Usuario");
        System.out.println("4.  Salir");

    }




}
