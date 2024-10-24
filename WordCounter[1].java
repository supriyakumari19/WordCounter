import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordCounter {

    public static void main(String[] args) {
        // Create the main JFrame with UI settings
        JFrame frame = new JFrame("Word Counter");
        frame.setSize(600, 530);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        
        // Set font and colors
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        Color primaryColor = new Color(45, 140, 240);  // Blue color for accent
        Color backgroundColor = new Color(250, 250, 250); // Light background
        
        // Panel for text area with instructions
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(backgroundColor);
        JLabel instructionLabel = new JLabel("Enter your text below:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setFont(font);
        textPanel.add(instructionLabel, BorderLayout.NORTH);
        
        // JTextArea for input with JScrollPane
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        textPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Create a panel for buttons with modern rounded styles
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor);
        
        JButton countButton = new JButton("Count");
        JButton clearButton = new JButton("Clear");
        styleButton(countButton, primaryColor);
        styleButton(clearButton, new Color(220, 70, 70)); // Red for clear button
        
        buttonPanel.add(countButton);
        buttonPanel.add(clearButton);
        
        // Create a results panel with modern fonts and colors
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(4, 1, 10, 10));
        resultPanel.setBackground(backgroundColor);
        
        JLabel wordCountLabel = new JLabel("Word Count: 0");
        JLabel charCountLabel = new JLabel("Character Count (with spaces): 0");
        JLabel charCountNoSpacesLabel = new JLabel("Character Count (without spaces): 0");
        JLabel sentenceCountLabel = new JLabel("Sentence Count: 0");
        
        styleLabel(wordCountLabel, font);
        styleLabel(charCountLabel, font);
        styleLabel(charCountNoSpacesLabel, font);
        styleLabel(sentenceCountLabel, font);
        
        resultPanel.add(wordCountLabel);
        resultPanel.add(charCountLabel);
        resultPanel.add(charCountNoSpacesLabel);
        resultPanel.add(sentenceCountLabel);
        
        // Add padding and borders for results
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Results"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        // Add panels to the frame
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(resultPanel, BorderLayout.SOUTH);
        
        // Action listener for the count button
        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText().trim();
                
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter some text!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String[] words = text.split("\\s+");
                int wordCount = words.length;
                
                int charCount = text.length();
                int charCountNoSpaces = text.replaceAll("\\s+", "").length();
                
                String[] sentences = text.split("(?<!\\b\\p{Lu}\\p{Ll}\\.)[.!?]\\s+");
                int sentenceCount = sentences.length;
                
                wordCountLabel.setText("Word Count: " + wordCount);
                charCountLabel.setText("Character Count (with spaces): " + charCount);
                charCountNoSpacesLabel.setText("Character Count (without spaces): " + charCountNoSpaces);
                sentenceCountLabel.setText("Sentence Count: " + sentenceCount);
            }
        });
        
        // Action listener for the clear button
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");  // Clear the text area
                wordCountLabel.setText("Word Count: 0");
                charCountLabel.setText("Character Count (with spaces): 0");
                charCountNoSpacesLabel.setText("Character Count (without spaces): 0");
                sentenceCountLabel.setText("Sentence Count: 0");
            }
        });
        
        // Key listener for real-time updates
        textArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = textArea.getText().trim();
                
                if (!text.isEmpty()) {
                    String[] words = text.split("\\s+");
                    int wordCount = words.length;
                    
                    int charCount = text.length();
                    int charCountNoSpaces = text.replaceAll("\\s+", "").length();
                    
                    String[] sentences = text.split("(?<!\\b\\p{Lu}\\p{Ll}\\.)[.!?]\\s+");
                    int sentenceCount = sentences.length;
                    
                    wordCountLabel.setText("Word Count: " + wordCount);
                    charCountLabel.setText("Character Count (with spaces): " + charCount);
                    charCountNoSpacesLabel.setText("Character Count (without spaces): " + charCountNoSpaces);
                    sentenceCountLabel.setText("Sentence Count: " + sentenceCount);
                } else {
                    wordCountLabel.setText("Word Count: 0");
                    charCountLabel.setText("Character Count (with spaces): 0");
                    charCountNoSpacesLabel.setText("Character Count (without spaces): 0");
                    sentenceCountLabel.setText("Sentence Count: 0");
                }
            }
        });
        
        // Show the frame
        frame.setVisible(true);
    }

    // Helper method to style buttons with rounded corners
    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    
    // Helper method to style labels
    private static void styleLabel(JLabel label, Font font) {
        label.setFont(font);
        label.setForeground(new Color(60, 60, 60));
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
