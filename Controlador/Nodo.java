package Controlador;

public class Nodo {
    private char caracter;
    private int frecuencia;
    private String codigoHuffman;

    public Nodo(char letra) {
        caracter = letra;
        frecuencia= 0;
    }
    public void aumentarFrecuencia(){
        frecuencia++;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getCodigoHuffman() {
        return codigoHuffman;
    }

    public void setCodigoHuffman(String codigoHuffman) {
        this.codigoHuffman = codigoHuffman;
    }
    
}
