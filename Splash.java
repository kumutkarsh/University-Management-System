package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame implements Runnable {

    Thread t;
    JProgressBar progressBar;
    JLabel loadingText;

    public Splash() {

        // FULL SCREEN MODE
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // FULL SCREEN
        setLayout(new BorderLayout());

        // ==== Load & Scale Background Image to Screen Size ====
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/first.png"));
        Image i2 = i1.getImage().getScaledInstance(screen.width, screen.height, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setLayout(new BorderLayout());
        add(background);

        // ==== Overlay Panel ====
        JPanel overlay = new JPanel();
        overlay.setOpaque(false);
        overlay.setLayout(new BorderLayout());
        overlay.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
        background.add(overlay, BorderLayout.SOUTH);

        // ==== App Title ====
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("University Management System");
        title.setFont(new Font("Serif", Font.BOLD, 40)); // Bigger for full screen
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subTitle = new JLabel("Smart • Fast • Organized");
        subTitle.setFont(new Font("SansSerif", Font.PLAIN, 22));
        subTitle.setForeground(new Color(240, 240, 240));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(title);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(subTitle);
        infoPanel.add(Box.createVerticalStrut(40));

        overlay.add(infoPanel, BorderLayout.NORTH);

        // ==== Loading Section ====
        JPanel loadingPanel = new JPanel();
        loadingPanel.setOpaque(false);
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setPreferredSize(new Dimension(600, 25)); // Full-screen sized bar
        progressBar.setForeground(new Color(0, 180, 0));
        progressBar.setBackground(new Color(255, 255, 255, 120));
        progressBar.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        loadingText = new JLabel("Loading, please wait...");
        loadingText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        loadingText.setForeground(Color.WHITE);

        loadingPanel.add(progressBar);
        loadingPanel.add(Box.createVerticalStrut(10));
        loadingPanel.add(loadingText);

        overlay.add(loadingPanel, BorderLayout.SOUTH);

        // Start loading animation
        startProgressAnimation();

        // Open login after delay
        t = new Thread(this);
        t.start();

        setVisible(true);
    }

    private void startProgressAnimation() {
        Timer timer = new Timer(45, null); // Faster and smoother
        timer.addActionListener(new ActionListener() {
            int value = 0;
            int dotCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                value += 1;
                if (value <= 100) {
                    progressBar.setValue(value);
                }

                // Animate loading dots
                dotCount = (dotCount + 1) % 4;
                loadingText.setText("Loading" + ".".repeat(dotCount));

                if (value >= 100) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(7000); // same delay
            setVisible(false);
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
