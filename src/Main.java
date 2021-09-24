import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Main extends JFrame {
    
    class App extends JPanel implements MouseListener{
        
        Stage stage;

        public App() {
            setPreferredSize(new Dimension(1000, 720));
            this.addMouseListener(this);
            stage = new Stage();
        
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //stage.mouseClicked(e.getX(), e.getY());
            
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            stage.mouseClicked(e.getX(), e.getY());
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            stage.mouseClicked(e.getX(), e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

    }

    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App canvas = new App();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() throws InterruptedException{
        while (true) {
            this.repaint();
            Thread.sleep(20);
        }
    }
}