package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Classe base que implementa o padrão Prototype
public abstract class ViewPrototype extends JFrame implements Cloneable {
    
    public ViewPrototype(String title) {
        setTitle(title);
        setResizable(false);
        setLocationRelativeTo((Component)null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        this.setSize(600, 600);
        
        // Definir uma cor de fundo suave que combina com o tema de manicure
        getContentPane().setBackground(new Color(255, 240, 245)); // Rosa claro

        // Painel com imagem de fundo
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fundo = new ImageIcon("C:\\Users\\Glêisson\\Desktop\\ProjoetoManicure\\ProjetoManicureEpedicure\\src\\main\\java\\View\\Imagens\\PlanoDeFundo1.jpg");
                g.drawImage(fundo.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);
        setVisible(true);
    }

    // Método abstrato para configurar componentes específicos de cada View
    protected abstract void initComponents();

    // Implementação do método clone
    @Override
    public ViewPrototype clone() {
        try {
            return (ViewPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    
    // Método utilitário para adicionar Label e Field
    protected void addLabelAndField(String labelText, JComponent component, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 80, 25);
        component.setBounds(x + 90, y, width, height);
        add(label);
        add(component);
    }
}
