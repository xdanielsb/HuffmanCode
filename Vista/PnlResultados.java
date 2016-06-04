package Vista;

;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;



public class PnlResultados extends JPanel {

    private JTextArea container;
    private JLabel lblResultado;

    private Ventana ventana;

    public PnlResultados(Ventana i) {
        this.ventana = i;
        this.setBorder(new TitledBorder("RESULTADOS"));
        container = new JTextArea("");
        JScrollPane sp2 = new JScrollPane(container);
        lblResultado = new JLabel();
        setLayout(new GridLayout(1, 1));
        add(sp2, BorderLayout.WEST);
        setVisible(true);
    }

    public void setLblResultado(String lblResultado) {
        this.container.setText(lblResultado);
    }

}
