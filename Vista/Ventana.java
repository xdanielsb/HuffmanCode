package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;


public class Ventana extends JFrame {

    PnlTextos pnlTextos;
    PnlAcciones pnlAcciones;
    PnlMatriz pnlMatriz;
    PnlResultados pnlResultados;
    
    public Ventana() {
        super("Main Animal");    
        setSize(600, 600);
        this.setLocationRelativeTo(null);
        
        pnlTextos = new PnlTextos(this);
        pnlAcciones = new PnlAcciones(this);
        pnlMatriz = new PnlMatriz(this);
        pnlResultados = new PnlResultados(this);
        
        setLayout(new GridLayout(4, 1));

        add(pnlTextos, BorderLayout.PAGE_START);
        add(pnlMatriz, BorderLayout.NORTH);
        add(pnlResultados,BorderLayout.SOUTH);
        add(pnlAcciones, BorderLayout.PAGE_END);
        
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public String getCadena(){
        return pnlTextos.getCadena();
    }

    public void setCadena(String txtArea1) {
        this.pnlTextos.setCadena(txtArea1);
    }

    public String getHuffman() {
        return pnlTextos.getHuffman();
    }

    public void setHuffman(String txtArea2) {
        this.pnlTextos.setHuffman(txtArea2);
    }
    
    public void setMatriz(String txtArea2) {
        this.pnlMatriz.setLblMatriz(txtArea2);
    }
    
    public void setLblResultado(String lblResultado) {
        this.pnlResultados.setLblResultado(lblResultado);
    }   
}
