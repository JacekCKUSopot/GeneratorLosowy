import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GeneratorLosowy extends JFrame {

    // Deklaracja komponentów
    private JLabel lblTytul;
    private JLabel lblWynik;
    private JTextField txtMin;
    private JTextField txtMax;
    private JButton btnLosuj;

    // Konstruktor
    public GeneratorLosowy() {
        // Ustawienia okna
        setTitle("Generator Losowy (BorderLayout)");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Główny panel z układem BorderLayout i marginesami 10px
        JPanel glownyPanel = new JPanel(new BorderLayout(10, 10));
        glownyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === NORTH – Pasek z tytułem ===
        lblTytul = new JLabel("Generator losowy", SwingConstants.CENTER);
        lblTytul.setFont(new Font("Arial", Font.BOLD, 16));
        glownyPanel.add(lblTytul, BorderLayout.NORTH);

        // === CENTER – Duża etykieta wyświetlająca liczbę ===
        lblWynik = new JLabel("?", SwingConstants.CENTER);
        lblWynik.setFont(new Font("Arial", Font.PLAIN, 48));
        glownyPanel.add(lblWynik, BorderLayout.CENTER);

        // === SOUTH – Panel z zakresami i przyciskiem losowania ===
        JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JLabel lblMin = new JLabel("Min:");
        txtMin = new JTextField("1", 5); // Domyślna wartość 1, szerokość 5 kolumn
        txtMin.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblMax = new JLabel("Max:");
        txtMax = new JTextField("100", 5); // Domyślna wartość 100, szerokość 5 kolumn
        txtMax.setFont(new Font("Arial", Font.PLAIN, 14));

        btnLosuj = new JButton("Losuj");
        btnLosuj.setFont(new Font("Arial", Font.BOLD, 14));

        // Dodawanie elementów do dolnego paska
        panelSouth.add(lblMin);
        panelSouth.add(txtMin);
        panelSouth.add(lblMax);
        panelSouth.add(txtMax);
        panelSouth.add(btnLosuj);

        glownyPanel.add(panelSouth, BorderLayout.SOUTH);

        // --- OBSŁUGA LOGIKI (Akcja przycisku "Losuj") ---
        btnLosuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pobranie i parsowanie wartości z pól tekstowych
                    int min = Integer.parseInt(txtMin.getText().trim());
                    int max = Integer.parseInt(txtMax.getText().trim());

                    // Walidacja: czy min nie jest większe od max
                    if (min > max) {
                        JOptionPane.showMessageDialog(GeneratorLosowy.this,
                                "Błąd: Wartość 'Min' nie może być większa niż 'Max'!",
                                "Błąd walidacji",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Losowanie liczby z przedziału [min, max]
                    Random random = new Random();
                    int wylosowanaLiczba = random.nextInt((max - min) + 1) + min;

                    // Wyświetlenie wyniku na środku
                    lblWynik.setText(String.valueOf(wylosowanaLiczba));

                } catch (NumberFormatException ex) {
                    // Obsługa błędu, gdy użytkownik wpisze tekst zamiast liczb lub zostawi puste pole
                    JOptionPane.showMessageDialog(GeneratorLosowy.this,
                            "Błąd: Wprowadź poprawne liczby całkowite do obu pól!",
                            "Błąd formatu danych",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dodanie panelu i wyświetlenie okna
        add(glownyPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji
        new GeneratorLosowy();
    }
}