package Vista;

;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PnlTextos extends JPanel {

    private JTextArea txtCadena;
    private JTextArea txtCadenaHuffman;
    private JLabel lblSeparador;
    private Ventana ventana;
    
    public PnlTextos(Ventana i) {
        this.ventana = i;
        this.setBorder(new TitledBorder("CODIFICACION HUFFMAN"));
        txtCadena = new JTextArea("");
        txtCadena.setColumns(10);
        txtCadena.setRows(1);
        txtCadena.setLineWrap(true);
        txtCadena.setWrapStyleWord(true);
        JScrollPane sp = new JScrollPane(txtCadena); 
        
        lblSeparador = new JLabel("");
                
        txtCadenaHuffman = new JTextArea("");
        txtCadenaHuffman.setColumns(10);
        txtCadenaHuffman.setRows(1);
        txtCadenaHuffman.setLineWrap(true);
        txtCadenaHuffman.setWrapStyleWord(true);
        JScrollPane sp2 = new JScrollPane(txtCadenaHuffman); 

        setLayout(new GridLayout(1, 2));
        add(sp, BorderLayout.WEST);
        add(sp2, BorderLayout.EAST);
        setVisible(true);
    }

    public String getCadena() {   
        return txtCadena.getText();
    }

    public void setCadena(String txtArea1) {
        this.txtCadena.setText(txtArea1);
    }

    public String getHuffman() {
        return txtCadenaHuffman.getText();
    }

    public void setHuffman(String txtArea2) {
        this.txtCadenaHuffman.setText(txtArea2);
    }

    
    


}
