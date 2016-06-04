package Vista;

import Controlador.*;

import Model.Archivo;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PnlAcciones extends JPanel implements ActionListener {

    private JButton cargar;
    private JButton codificar;
    private JButton decodificar;
    private JButton limpiar;
    private Ventana ventana;
    private Huffman huf;
    private Archivo arc;


    public PnlAcciones(Ventana i) {
        this.setBorder(new TitledBorder("Acciones"));
        huf = new Huffman();
        arc = new Archivo();
        this.ventana = i;
        cargar = new JButton("Cargar");
        cargar.addActionListener(this);
        codificar = new JButton("Codificar");
        codificar.addActionListener(this);
        decodificar = new JButton("Decodificar");
        decodificar.addActionListener(this);
        decodificar.addActionListener(this);
        limpiar = new JButton("Limpiar");
        limpiar.addActionListener(this);

        setLayout(new GridLayout(1, 5));
        add(cargar);
        add(codificar);
        add(decodificar);
        add(limpiar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        float ganancia = 0;
        if (e.getSource() == cargar) {
            try {
                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                fc.showOpenDialog(fc);
                String path = fc.getSelectedFile().getPath();
                String text = arc.readFile(path);
                ventana.setCadena(text);
            } catch (Exception ex) {
                System.out.println("No se cargo ningun archivo ");
            }

        }
        if (e.getSource() == codificar) {
            huf.iniciar(ventana.getCadena());
            huf.setFrecuencias(); //Hallamos las frecuencias
            huf.crearTablar(); //Creamos la tabla
            huf.llenar(); //Llenamos la tabla
            huf.generarCodigosHuffman(huf.getNumColumnas() - 1, ""); //Buscamos la pos del padre y generamos los codigos de huffman
            ventana.setMatriz(huf.matrizToString());
            ventana.setHuffman(huf.getCadenaHuffman()); //Mostramos los resultados
            ganancia = (float) (((ventana.getCadena().length() * 8) - huf.getCadenaHuffman().length()) * 100) / (ventana.getCadena().length() * 8);
            ventana.setLblResultado("La ganancia total es de: " + ganancia + "%");
            //ventana.setCadena("");
        }
        if (e.getSource() == decodificar) {
            huf.setCadena("");
            huf.recibirCodigo(ventana.getHuffman());
            huf.decodificarMensaje();
            ventana.setLblResultado("El mensaje decodificado es: " + huf.getCadena());
        }
        if (e.getSource() == limpiar) {
            ventana.setCadena("");
            ventana.setHuffman("");
            ventana.setLblResultado("");
        }
    }

}
