package es.uvigo.esei.pro2.ui;

import es.uvigo.esei.pro2.core.Bibliografia;
import es.uvigo.esei.pro2.core.Referencia;
import es.uvigo.esei.pro2.core.DocumentoWeb;
import es.uvigo.esei.pro2.core.Fecha;
import es.uvigo.esei.pro2.core.ArticuloRevista;
import es.uvigo.esei.pro2.core.Libro;

import java.util.Scanner;

/**
 * Interfaz de lin. de comando
 */
public class Ilc {

    /**
     * Realiza el reparto de la funcionalidad ler = lee, evalua, repite
     */

    public abstract class BiblioException extends Exception {

        public BiblioException() {
        }

        //Constructor that accepts a message
        public BiblioException(String message) {
            super(message);
        }
    }

    public void ler() {
        int op;

        // Lee el num. max. de referencias
        int maxReferencias = leeNum("Num. max. referencias: ");

        // Prepara
        Bibliografia coleccion = new Bibliografia(maxReferencias);

        // Bucle ppal
        do {
            System.out.println("\nGestión bibliográfica");

            op = menu(coleccion);

            try {
                switch (op) {
                    case 0:
                        System.out.println("Fin.");
                        break;
                    case 1:
                        insertaReferencia(coleccion);
                        break;
                    case 2:
                        modificaReferencia(coleccion);
                        break;
                    case 3:
                        eliminaReferencia(coleccion);
                        break;
                    case 4:
                        System.out.println(coleccion.toString());
                        break;
                    case 5:
                        leeReferenciaPorTipo(coleccion);
                        break;

                    default:
                        System.out.println("No es correcta esa opción ( "
                                + op + " )");
                }
            } catch (Exception e) {
                System.err.println("\nERROR: " + e.getMessage());
            }
        } while (op != 0);

    }

    /**
     * Lee un num. de teclado
     *
     * @param msg El mensaje a visualizar.
     * @return El num., como entero
     */
    private int leeNum(String msg) {
        boolean repite;
        int toret = 0;
        Scanner teclado = new Scanner(System.in);

        do {
            repite = false;
            System.out.print(msg);

            try {
                toret = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException exc) {
                repite = true;
            }
        } while (repite);

        return toret;
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private int menu(Bibliografia coleccion) {
        int toret;

        do {
            System.out.println("Número de referencias bibliográficas: "
                    + coleccion.getNumReferencias()
                    + " / " + coleccion.getMaxReferencias());
            System.out.println(
                    "\n1. Inserta nueva referencia\n"
                    + "2. Modifica referencia\n"
                    + "3. Elimina referencia\n"
                    + "4. Listar referencias\n"
                    + "5. Listar referencias por tipo\n"
                    + "0. Salir\n");
            toret = leeNum("Selecciona: ");
        } while (toret < 0
                && toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Crea una nueva referencia y la inserta en la coleccion
     *
     * @param coleccion La coleccion en la que se inserta la referencia.
     */
    private void insertaReferencia(Bibliografia coleccion) throws Exception {
        char ref;
        Scanner teclado = new Scanner(System.in);
        do {
            ref = leeCaracter("Introduzca que tipo de Referencia va a añadir (A, L, D): ");
        } while ((ref != 'L') && (ref != 'A') && (ref != 'D'));
        if (ref == 'A') {
            ArticuloRevista r = new ArticuloRevista("", "", 0, 1, 2, 3, "", "", 0);
            modificaArticulo(r);
            coleccion.inserta(r);
        } else {
            if (ref == 'L') {
                Libro r = new Libro("", "", Libro.TipoFormato.EBOOK, "", "", 0);
                modificaLibro(r);
                coleccion.inserta(r);
            } else {
                if (ref == 'D') {
                    Fecha f = new Fecha(0, 0, 0);
                    DocumentoWeb r = new DocumentoWeb("", f, "", "", 0);
                    modificaWeb(r);
                    coleccion.inserta(r);
                }
            }
        }

    }

    /**
     * Borra una referencia por su num.
     *
     * @param coleccion La coleccion en el que se elimina la referencia
     */
    private void eliminaReferencia(Bibliografia coleccion) throws Exception {
        coleccion.elimina(leeNumReferencia(coleccion));
    }

    /**
     * Modifica una referencia existente.
     *
     * @param coleccion La coleccion de la cual modificar una referencia.
     */
    private void modificaReferencia(Bibliografia coleccion) throws Exception {
        if (coleccion.getNumReferencias() > 0) {
            this.modificaReferencia(coleccion.get(leeNumReferencia(coleccion)));
        } else {
            System.out.println("La coleccion no contiene referencias.");
        }
    }

    private void modificaReferencia(Referencia r) {
        String info;
        char tipoReferencia;
        Scanner teclado = new Scanner(System.in);

        // Autores
        System.out.print("Autores de la referencia ");
        if (r.getAutores().length() > 0) {
            System.out.print("[" + r.getAutores() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setAutores(info);
        }

        // Titulo
        System.out.print("Titulo de la referencia ");
        if (r.getTitulo().length() > 0) {
            System.out.print("[" + r.getTitulo() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setTitulo(info);
        }

        // Ano
        System.out.print("Ano de la referencia ");
        if (r.getAno() > 0) {
            System.out.print("[" + r.getAno() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setAno(Integer.parseInt(info));
        }

    }

    private void modificaWeb(DocumentoWeb r) {
        String info;
        Scanner teclado = new Scanner(System.in);

        modificaReferencia(r);

        //Url//
        System.out.print("Url ");
        if (r.getUrl().length() > 0) {
            System.out.print("[" + r.getUrl() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setUrl(info);
        }

        //Fecha//
        System.out.print("Fecha");

        System.out.print("Introduzca un dia: ");
        int d = teclado.nextInt();
        System.out.print("Introduzca un mes: ");
        int m = teclado.nextInt();
        System.out.print("Introduzca un año: ");
        int a = teclado.nextInt();
        Fecha f = new Fecha(d, m, a);
        r.setFechaConsulta(f);
    }

    private void modificaArticulo(ArticuloRevista r) {
        String info;
        int num;
        Scanner teclado = new Scanner(System.in);

        modificaReferencia(r);

        //Titulo Revista//
        System.out.print("Titulo Revista: ");
        if (r.getTituloRevista().length() > 0) {
            System.out.print("[" + r.getTituloRevista() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setTituloRevista(info);
        }

        //Doi//
        System.out.print("Doi Revista: ");
        if (r.getDoi().length() > 0) {
            System.out.print("[" + r.getDoi() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setDoi(info);
        }

        //Volumen//
        System.out.print("Volumen: ");
        if (r.getVolumen() == 0) {
            System.out.print("[" + r.getVolumen() + "]");
        }
        System.out.print(": ");
        num = teclado.nextInt();

        if (num > 0) {
            r.setVolumen(num);
        }
        //Numero//
        System.out.print("Num: ");
        if (r.getNumero() == 0) {
            System.out.print("[" + r.getNumero() + "]");
        }
        System.out.print(": ");
        num = teclado.nextInt();

        if (num > 0) {
            r.setNumero(num);
        }
        //Pag. Inicio//
        System.out.print("Pagina Inicio: ");
        if (r.getPagInicio() == 0) {
            System.out.print("[" + r.getPagInicio() + "]");
        }
        System.out.print(": ");
        num = teclado.nextInt();

        if (num > 0) {
            r.setPagInicio(num);
        }

        //Pag. Fin//
        System.out.print("Pagina Fin: ");
        if (r.getPagFin() >= 0) {
            System.out.print("[" + r.getPagFin() + "]");
        }
        System.out.print(": ");
        num = teclado.nextInt();

        if (num > 0) {
            r.setPagFin(num);
        }
    }

    private void modificaLibro(Libro r) {
        String info;
        char tipoFormato;
        Scanner teclado = new Scanner(System.in);

        modificaReferencia(r);

        System.out.print("Editorial ");
        if (r.getEditorial().length() > 0) {
            System.out.print("[" + r.getEditorial() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setEditorial(info);
        }

        System.out.print("ISBN ");
        if (r.getIsbn().length() > 0) {
            System.out.print("[" + r.getIsbn() + "]");
        }
        System.out.print(": ");
        info = teclado.nextLine().trim();

        if (info.length() > 0) {
            r.setIsbn(info);
        }

        // Tipo de Formato          
        do {
            tipoFormato = leeCaracter("Introduce el tipo de formato (P: papel, E: Ebook): ");
        } while ((tipoFormato != 'P') && (tipoFormato != 'E'));

        switch (tipoFormato) {
            case 'P':
                r.setFormato(Libro.TipoFormato.PAPEL);
                break;
            case 'E':
                r.setFormato(Libro.TipoFormato.EBOOK);
                break;

        }

    }

    /**
     * Lee del teclado un nuevo num. de referencia
     *
     * @param coleccion La colección de la que se obtiene el max.
     * @return el num. de referencias, como entero.
     */
    private int leeNumReferencia(Bibliografia coleccion) {
        final int numReferencias = coleccion.getNumReferencias();
        int toret;

        toret = leeNum("Introduzca num. de referencia (1..." + numReferencias + "): ");

        return toret - 1;
    }

    /**
     * Lee un caracter del teclado
     *
     * @param men Mensaje a visualizar
     * @return el caracter introducido por el usuario
     */
    private char leeCaracter(String men) {
        Scanner teclado = new Scanner(System.in);

        System.out.print(men);
        return (teclado.nextLine().trim().charAt(0));
    }

    private void leeReferenciaPorTipo(Bibliografia coleccion) {
        final int numReferencias = coleccion.getNumReferencias();
        char info;
        do {
            info = leeCaracter("Introduce el tipo de Referencia a leer (L: libro, A: artículo revista, D: documento web): ");
        } while ((info != 'L') && (info != 'A') && (info != 'D'));
        try {
            if (info == 'L') {
                for (int i = 0; i < numReferencias; i++) {

                    if (coleccion.get(i) instanceof Libro) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(coleccion.get(i).toString());
                    }
                }

            } else {
                if (info == 'A') {
                    for (int i = 0; i < numReferencias; i++) {
                        if (coleccion.get(i) instanceof ArticuloRevista) {
                            System.out.print((i + 1) + ". ");
                            System.out.println(coleccion.get(i).toString());
                        }
                    }

                } else {
                    if (info == 'D') {
                        for (int i = 0; i < numReferencias; i++) {
                            if (coleccion.get(i) instanceof DocumentoWeb) {
                                System.out.print((i + 1) + ". ");
                                System.out.println(coleccion.get(i).toString());
                            }
                        }
                    }

                }
            }
        } catch (Exception exc) {
            System.err.println("ARROR");
        }
    }
}
