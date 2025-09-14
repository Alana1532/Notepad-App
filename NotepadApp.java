import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class NotepadApp extends JFrame implements ActionListener {

    JTextArea textArea;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem, saveItem, exitItem;

    public NotepadApp() {
        // Frame setup
        setTitle("Notepad - Simple");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text area
        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        // Menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        // Add menu items
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        // Event handling
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openItem) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = "";
                    String content = "";
                    while ((line = br.readLine()) != null) {
                        content += line + "\n";   // build the text
                    }
                    textArea.setText(content);
                    br.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error while opening file");
                }
            }
        }

        else if (e.getSource() == saveItem) {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error while saving file");
                }
            }
        }

        else if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new NotepadApp();
    }
}

