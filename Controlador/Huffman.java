package Controlador;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Huffman {

    private int longitud;
    private ArrayList<Nodo> letras; //Nodos de las letras
    private final ArrayList<Character> codigo;
    private String cadena;
    private String cadenaHuffman;
    private int[][] table;
    private int numFilas;
    private int numColumnas;

    public Huffman() {
        cadena = "";
        cadenaHuffman = "";
        codigo = new ArrayList<>();
        letras = new ArrayList<>();
    }

    public void iniciar(String cadena) {
        longitud = cadena.length();
        this.cadena = cadena;
        cadenaHuffman = cadena;
    }

    public void setFrecuencias() {
        String aux;
        for (int i = 0; i < longitud; i++) {
            char carac = cadena.charAt(i);

            if (carac != '¿') {
                Nodo nod = new Nodo(carac);
                letras.add(nod);
                for (int j = 0; j < longitud; j++) {
                    char carac2 = cadena.charAt(j);
                    // System.out.println(carac + " " + carac2);
                    if (carac == carac2) {
                        nod.aumentarFrecuencia();
                    }
                }
                aux = cadena.replace(carac, '¿');
                cadena = aux;
            }
        }
    }

    public void mostrar() {
        for (int i = 0; i <= letras.size() - 1; i++) {
            Nodo a = letras.get(i);
            System.out.println(a.getCaracter() + " " + a.getFrecuencia());
        }
    }

    public void crearTablar() {
        numFilas = 6;
        numColumnas = 2 * letras.size() - 1;
        table = new int[numFilas][numColumnas];
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                table[i][j] = 0;
            }
        }
        //En esta parte le damos los datos iniciales a la tabla
        for (int i = 0; i < letras.size(); i++) {
            table[0][i] = i;
            table[1][i] = (int) letras.get(i).getCaracter();
            table[2][i] = letras.get(i).getFrecuencia();
            table[4][i] = -1;
            table[5][i] = -1;
        }

    }

    public int calcularMenor() {
        int resultado = -1;
        for (int i = 0; i < numColumnas; i++) {
            if (table[3][i] == 0 && resultado == -1) {
                resultado = table[2][i];
            }
        }

        for (int i = 0; i < numColumnas; i++) {
            //Mira las frecuencias pero verifica que la casilla de padre este vacia y que la frecuencia se hac diferente de 0
            if (table[2][i] < resultado && table[3][i] == 0 && table[2][i] != 0) {
                resultado = table[2][i];
            }
        }
        return resultado;
    }

    //Este Metodo llena la tabla con el proceso aprendido en clase 
    public void llenar() {

        int tam = letras.size(), menor1, menor2, posHijo1, posHijo2;
        for (int i = tam; i < numColumnas; i++) {
            //Calculamos los menores
            menor1 = calcularMenor();
            posHijo1 = cambiarPadre(menor1, i);

            menor2 = calcularMenor();
            posHijo2 = cambiarPadre(menor2, i);
            //Ponemos la suma de los menores en la fila correspondiente del padre
            table[2][i] = menor1 + menor2;

            //Ubicamos los hijos izquierdo  y derecho
            if (menor1 <= menor2) {
                table[4][i] = posHijo1;
                table[5][i] = posHijo2;
            } else {
                table[4][i] = posHijo2;
                table[5][i] = posHijo1;
            }
        }
    }

    private int cambiarPadre(int menor1, int padre) {
        table[0][padre] = padre;//Le ponemos el numero correspondiente al padre
        for (int i = 0; i < numColumnas; i++) {
            int val = table[2][i];
            if (val == menor1 && table[3][i] == 0) {
                table[3][i] = padre;
                return i;
            }
        }
        return -1;
    }

    //Genera el codigo Huffman
    //La variable dato especifica la columna en la que se encuentra el padre del arbol
    // Es un metodo recursivo
    public void generarCodigosHuffman(int dato, String code) {
        if (dato != -1) {
            if (table[4][dato] != -1 || table[5][dato] != -1) {
                // System.out.print(" ,Hijo Izquierdo: " + table[4][dato]);
                // System.out.print(" ,Hijo Derecho: " + table[5][dato]);
            } else {
                System.out.print("Letra: " + (char) table[1][dato]);
                System.out.print(" ,Huffman: " + code);
                System.out.println("");
                cadenaHuffman = cadenaHuffman.replaceAll(Pattern.quote((char) table[1][dato] + ""), code);
                
            }
            generarCodigosHuffman(table[4][dato], code + "0");
            generarCodigosHuffman(table[5][dato], code + "1");
        }
    }

    public void decodificarMensaje(ArrayList<Character> codigo, int num) {
        //System.out.println(num + " numero");
        try {

            if (table[1][num] != 0) {
                cadena = cadena + (char) table[1][num];
                //    System.out.println((char) table[1][num] + " letra");
                if (codigo.isEmpty()) {
                    //   System.out.println("listo");
                } else {
                    decodificarMensaje(codigo, table[0].length - 1);
                }
            } else if (codigo.get(0) == '1') {
                //   System.out.println("derecha");
                codigo.remove(0);
                // System.out.println(codigo + " codigo");
                decodificarMensaje(codigo, table[5][num]);
            } else if (codigo.get(0) == '0') {
                // System.out.println("izquierda");
                codigo.remove(0);
                // System.out.println(codigo + " codigo");
                decodificarMensaje(codigo, table[4][num]);
            } else {
                // System.out.println("Error en el mensaje");
            }
        } catch (Exception ex) {
            System.out.println("El mensaje desencriptado no concuerda o no esta completo");
        }

    }

    public void recibirCodigo(String codigoRecibido) {
        this.cadenaHuffman = codigoRecibido;
        for (char c : cadenaHuffman.toCharArray()) {
            this.codigo.add(c);
        }
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void mostrarTabla() {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (i == 1) {
                    System.out.print((char) table[i][j] + "\t");
                } else if (table[i][j] == -1) {
                    System.out.print("0" + "\t");
                } else {
                    System.out.print(table[i][j] + "\t");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public String matrizToString() {
        String matriz = "";
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (i == 1) {
                    matriz += (char) table[i][j] + "\t";
                } else if (table[i][j] == -1) {
                    matriz += "0" + "\t";
                } else {
                    matriz += table[i][j] + "\t";
                }
            }
            matriz += "\n";
        }
        return matriz;
    }

    public void decodificarMensaje() {
        int num = table[0].length - 1;
        decodificarMensaje(this.codigo, num);
    }

    public int getNumColumnas() {
        return numColumnas;
    }

    public String getCadenaHuffman() {
        return cadenaHuffman;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int[][] getTable() {
        return table;
    }
    

}
