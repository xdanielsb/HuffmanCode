package Vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;



public class PnlMatriz extends JPanel {

    private JTextArea txtArea1;
    private JLabel lblMatriz;
    private Ventana ventana;

    public PnlMatriz(Ventana i) {
        this.ventana = i;
        this.setBorder(new TitledBorder("MATRIZ"));
        txtArea1 = new JTextArea("");
        lblMatriz = new JLabel();
        JScrollPane sp = new JScrollPane(txtArea1); 
        setLayout(new GridLayout(1, 3));
        add(sp, BorderLayout.WEST);
        //add(lblMatriz);
        setVisible(true);
    }

    public void setLblMatriz(String lblMatriz) {
        this.txtArea1.setText(lblMatriz);
    }

}
