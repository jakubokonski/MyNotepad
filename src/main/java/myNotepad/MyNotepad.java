package myNotepad;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyNotepad extends JFrame{

    private JTextArea textArea = new JTextArea(20, 60);
    private JFileChooser fileChooser = new JFileChooser();

    private MyNotepad() {
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
        fileChooser.setFileFilter(txtFilter);

        add(scrollPane);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        menuBar.add(file);

        file.add(Open);
        file.add(Save);
        file.addSeparator();
        file.add(Exit);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private Action Open = new AbstractAction("Open") {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                openFile(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    };

    private Action Save = new AbstractAction("Save") {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveFile();
        }
    };

    private Action Exit = new AbstractAction("Exit") {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    private void openFile(String fileName) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileName);
            textArea.read(fileReader, null);
            fileReader.close();
            setTitle(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                textArea.write(fileWriter);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MyNotepad();
    }
}
