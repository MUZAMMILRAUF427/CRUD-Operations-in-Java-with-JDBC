package mainapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainApp extends JFrame {
    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Muz@1234";

    private Connection connection;

    // Constructor to set up the GUI
    public MainApp() {
        // Setup the database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Failed to connect to the database!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up the GUI components
        setTitle("Student Management System");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Labels and Text Fields
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(10);

        add(idLabel);
        add(idField);
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);

        // Buttons
        JButton createButton = new JButton("Create");
        JButton readButton = new JButton("Read");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        // Text Area for displaying records
        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        // Action Listeners for buttons
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String email = emailField.getText();

                    String query = "INSERT INTO student (id, name, email) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, email);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        outputArea.setText("Student created successfully!\n");
                    }
                } catch (Exception ex) {
                    outputArea.setText("Error: " + ex.getMessage() + "\n");
                    ex.printStackTrace();
                }
            }
        });

        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "SELECT * FROM student";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    StringBuilder result = new StringBuilder("ID\tName\tEmail\n");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        result.append(id).append("\t").append(name).append("\t").append(email).append("\n");
                    }
                    outputArea.setText(result.toString());
                } catch (Exception ex) {
                    outputArea.setText("Error: " + ex.getMessage() + "\n");
                    ex.printStackTrace();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String email = emailField.getText();

                    String query = "UPDATE student SET name = ?, email = ? WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setInt(3, id);

                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        outputArea.setText("Student updated successfully!\n");
                    }
                } catch (Exception ex) {
                    outputArea.setText("Error: " + ex.getMessage() + "\n");
                    ex.printStackTrace();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());

                    String query = "DELETE FROM student WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id);

                    int rowsDeleted = preparedStatement.executeUpdate();
                    if (rowsDeleted > 0) {
                        outputArea.setText("Student deleted successfully!\n");
                    }
                } catch (Exception ex) {
                    outputArea.setText("Error: " + ex.getMessage() + "\n");
                    ex.printStackTrace();
                }
            }
        });
    }

//     Main method to run the application
    public static void main(String[] args) {
        
//        SwingUtilities.invokeLater(() -> new MovieTicketBookingSystem().setVisible(true));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}
//
//        
//        //SwingUtilities.invokeLater(() -> new BusTicketBookingSystem().setVisible(true));
//
//    }
//}









//
//
//package mainapp;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class MainApp extends JFrame {
//    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Muz@1234";
//
//    private Connection connection;
//
//    // Constructor to set up the GUI
//    public MainApp() {
//        // Setup the database connection
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            if (connection != null) {
//                System.out.println("Database connected successfully!");
//            } else {
//                System.out.println("Failed to connect to the database!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Set up the GUI components
//        setTitle("Student Management System");
//        setSize(600, 500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Header Panel
//        JPanel headerPanel = new JPanel();
//        headerPanel.setBackground(new Color(51, 153, 255));
//        headerPanel.setPreferredSize(new Dimension(600, 50));
//        JLabel headerLabel = new JLabel("Student Management System", JLabel.CENTER);
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        headerLabel.setForeground(Color.WHITE);
//        headerPanel.add(headerLabel);
//        add(headerPanel, BorderLayout.NORTH);
//
//        // Input Panel
//        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JLabel idLabel = new JLabel("ID:");
//        JTextField idField = new JTextField();
//        idField.setPreferredSize(new Dimension(100, 25));
//
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField();
//        nameField.setPreferredSize(new Dimension(100, 25));
//
//        JLabel emailLabel = new JLabel("Email:");
//        JTextField emailField = new JTextField();
//        emailField.setPreferredSize(new Dimension(100, 25));
//
//        inputPanel.add(idLabel);
//        inputPanel.add(idField);
//        inputPanel.add(nameLabel);
//        inputPanel.add(nameField);
//        inputPanel.add(emailLabel);
//        inputPanel.add(emailField);
//
//        add(inputPanel, BorderLayout.CENTER);
//
//        // Button Panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//        buttonPanel.setLayout(new GridLayout(1, 4, 10, 0));
//
//        JButton createButton = new JButton("Create");
//        JButton readButton = new JButton("Read");
//        JButton updateButton = new JButton("Update");
//        JButton deleteButton = new JButton("Delete");
//
//        createButton.setBackground(new Color(46, 204, 113));
//        readButton.setBackground(new Color(52, 152, 219));
//        updateButton.setBackground(new Color(241, 196, 15));
//        deleteButton.setBackground(new Color(231, 76, 60));
//
//        createButton.setForeground(Color.WHITE);
//        readButton.setForeground(Color.WHITE);
//        updateButton.setForeground(Color.WHITE);
//        deleteButton.setForeground(Color.WHITE);
//
//        buttonPanel.add(createButton);
//        buttonPanel.add(readButton);
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);
//
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Output Area
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
//        scrollPane.setPreferredSize(new Dimension(600, 200));
//        add(scrollPane, BorderLayout.EAST);
//
//        // Action Listeners for buttons
//        createButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//                    String name = nameField.getText();
//                    String email = emailField.getText();
//
//                    String query = "INSERT INTO student (id, name, email) VALUES (?, ?, ?)";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setInt(1, id);
//                    preparedStatement.setString(2, name);
//                    preparedStatement.setString(3, email);
//
//                    int rowsInserted = preparedStatement.executeUpdate();
//                    if (rowsInserted > 0) {
//                        outputArea.setText("Student created successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        readButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String query = "SELECT * FROM student";
//                    Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery(query);
//
//                    StringBuilder result = new StringBuilder("ID\tName\tEmail\n");
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String name = resultSet.getString("name");
//                        String email = resultSet.getString("email");
//                        result.append(id).append("\t").append(name).append("\t").append(email).append("\n");
//                    }
//                    outputArea.setText(result.toString());
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//                    String name = nameField.getText();
//                    String email = emailField.getText();
//
//                    String query = "UPDATE student SET name = ?, email = ? WHERE id = ?";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setString(1, name);
//                    preparedStatement.setString(2, email);
//                    preparedStatement.setInt(3, id);
//
//                    int rowsUpdated = preparedStatement.executeUpdate();
//                    if (rowsUpdated > 0) {
//                        outputArea.setText("Student updated successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//
//                    String query = "DELETE FROM student WHERE id = ?";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setInt(1, id);
//
//                    int rowsDeleted = preparedStatement.executeUpdate();
//                    if (rowsDeleted > 0) {
//                        outputArea.setText("Student deleted successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }
//
//    // Main method to run the application
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainApp().setVisible(true);
//            }
//        });
//    }
//}







//package mainapp;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class MainApp extends JFrame {
//    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Muz@1234";
//
//    private Connection connection;
//
//    public MainApp() {
//        // Database connection
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            if (connection != null) {
//                System.out.println("Database connected successfully!");
//            } else {
//                System.out.println("Failed to connect to the database!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Frame setup
//        setTitle("Student Management System");
//        setSize(700, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Header Panel
//        JPanel headerPanel = new JPanel();
//        headerPanel.setBackground(new Color(51, 153, 255));
//        headerPanel.setPreferredSize(new Dimension(700, 50));
//        JLabel headerLabel = new JLabel("Student Management System", JLabel.CENTER);
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        headerLabel.setForeground(Color.WHITE);
//        headerPanel.add(headerLabel);
//        add(headerPanel, BorderLayout.NORTH);
//
//        // Input Panel
//        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JLabel idLabel = new JLabel("ID:");
//        JTextField idField = new JTextField();
//        idField.setPreferredSize(new Dimension(150, 25));
//
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField();
//        nameField.setPreferredSize(new Dimension(150, 25));
//
//        JLabel emailLabel = new JLabel("Email:");
//        JTextField emailField = new JTextField();
//        emailField.setPreferredSize(new Dimension(150, 25));
//
//        inputPanel.add(idLabel);
//        inputPanel.add(idField);
//        inputPanel.add(nameLabel);
//        inputPanel.add(nameField);
//        inputPanel.add(emailLabel);
//        inputPanel.add(emailField);
//
//        add(inputPanel, BorderLayout.CENTER);
//
//        // Button Panel
//        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JButton createButton = new JButton("Create");
//        JButton readButton = new JButton("Read");
//        JButton updateButton = new JButton("Update");
//        JButton deleteButton = new JButton("Delete");
//
//        createButton.setBackground(new Color(46, 204, 113));
//        readButton.setBackground(new Color(52, 152, 219));
//        updateButton.setBackground(new Color(241, 196, 15));
//        deleteButton.setBackground(new Color(231, 76, 60));
//
//        createButton.setForeground(Color.WHITE);
//        readButton.setForeground(Color.WHITE);
//        updateButton.setForeground(Color.WHITE);
//        deleteButton.setForeground(Color.WHITE);
//
//        buttonPanel.add(createButton);
//        buttonPanel.add(readButton);
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);
//
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Output Area
//        JPanel outputPanel = new JPanel(new BorderLayout());
//        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        JTextArea outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//        scrollPane.setPreferredSize(new Dimension(300, 150));
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
//
//        outputPanel.add(scrollPane, BorderLayout.CENTER);
//        add(outputPanel, BorderLayout.EAST);
//
//        // Button Actions
//        createButton.addActionListener(e -> handleCreate(idField, nameField, emailField, outputArea));
//        readButton.addActionListener(e -> handleRead(outputArea));
//        updateButton.addActionListener(e -> handleUpdate(idField, nameField, emailField, outputArea));
//        deleteButton.addActionListener(e -> handleDelete(idField, outputArea));
//    }
//
//    private void handleCreate(JTextField idField, JTextField nameField, JTextField emailField, JTextArea outputArea) {
//        try {
//            int id = Integer.parseInt(idField.getText());
//            String name = nameField.getText();
//            String email = emailField.getText();
//
//            String query = "INSERT INTO student (id, name, email) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, email);
//
//            int rowsInserted = preparedStatement.executeUpdate();
//            outputArea.setText(rowsInserted > 0 ? "Student created successfully!\n" : "No rows inserted.\n");
//        } catch (Exception ex) {
//            outputArea.setText("Error: " + ex.getMessage() + "\n");
//        }
//    }
//
//    private void handleRead(JTextArea outputArea) {
//        try {
//            String query = "SELECT * FROM student";
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//            StringBuilder result = new StringBuilder("ID\tName\tEmail\n");
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//                result.append(id).append("\t").append(name).append("\t").append(email).append("\n");
//            }
//            outputArea.setText(result.toString());
//        } catch (Exception ex) {
//            outputArea.setText("Error: " + ex.getMessage() + "\n");
//        }
//    }
//
//    private void handleUpdate(JTextField idField, JTextField nameField, JTextField emailField, JTextArea outputArea) {
//        try {
//            int id = Integer.parseInt(idField.getText());
//            String name = nameField.getText();
//            String email = emailField.getText();
//
//            String query = "UPDATE student SET name = ?, email = ? WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, email);
//            preparedStatement.setInt(3, id);
//
//            int rowsUpdated = preparedStatement.executeUpdate();
//            outputArea.setText(rowsUpdated > 0 ? "Student updated successfully!\n" : "No rows updated.\n");
//        } catch (Exception ex) {
//            outputArea.setText("Error: " + ex.getMessage() + "\n");
//        }
//    }
//
//    private void handleDelete(JTextField idField, JTextArea outputArea) {
//        try {
//            int id = Integer.parseInt(idField.getText());
//
//            String query = "DELETE FROM student WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, id);
//
//            int rowsDeleted = preparedStatement.executeUpdate();
//            outputArea.setText(rowsDeleted > 0 ? "Student deleted successfully!\n" : "No rows deleted.\n");
//        } catch (Exception ex) {
//            outputArea.setText("Error: " + ex.getMessage() + "\n");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
//    }
//}





//
//
//
//
//
//package mainapp;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.*;
//
//public class MainApp extends JFrame {
//    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Muz@1234";
//
//    private Connection connection;
//
//    // Constructor to set up the GUI
//    public MainApp() {
//        // Setup the database connection
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            if (connection != null) {
//                System.out.println("Database connected successfully!");
//            } else {
//                System.out.println("Failed to connect to the database!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Set up the GUI components
//        setTitle("Student Management System");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Main Panel with Card Layout
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Header Panel
//        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        headerPanel.setBackground(new Color(41, 128, 185));
//        JLabel headerLabel = new JLabel("Student Management System", JLabel.CENTER);
//        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        headerLabel.setForeground(Color.WHITE);
//        headerPanel.add(headerLabel);
//        mainPanel.add(headerPanel, BorderLayout.NORTH);
//
//        // Input Panel
//        JPanel inputPanel = new JPanel(new GridBagLayout());
//        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.insets = new Insets(5, 5, 5, 5);
//
//        JLabel idLabel = new JLabel("ID:");
//        JTextField idField = new JTextField(20);
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField(20);
//        JLabel emailLabel = new JLabel("Email:");
//        JTextField emailField = new JTextField(20);
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        inputPanel.add(idLabel, gbc);
//        gbc.gridx = 1;
//        inputPanel.add(idField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        inputPanel.add(nameLabel, gbc);
//        gbc.gridx = 1;
//        inputPanel.add(nameField, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        inputPanel.add(emailLabel, gbc);
//        gbc.gridx = 1;
//        inputPanel.add(emailField, gbc);
//
//        mainPanel.add(inputPanel, BorderLayout.CENTER);
//
//        // Button Panel
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        JButton createButton = new JButton("Create");
//        JButton readButton = new JButton("Read");
//        JButton updateButton = new JButton("Update");
//        JButton deleteButton = new JButton("Delete");
//
//        createButton.setBackground(new Color(46, 204, 113));
//        readButton.setBackground(new Color(52, 152, 219));
//        updateButton.setBackground(new Color(241, 196, 15));
//        deleteButton.setBackground(new Color(231, 76, 60));
//
//        createButton.setForeground(Color.WHITE);
//        readButton.setForeground(Color.WHITE);
//        updateButton.setForeground(Color.WHITE);
//        deleteButton.setForeground(Color.WHITE);
//
//        buttonPanel.add(createButton);
//        buttonPanel.add(readButton);
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
//
//        // Output Area
//        JTextArea outputArea = new JTextArea(10, 30);
//        outputArea.setEditable(false);
//        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//        JScrollPane scrollPane = new JScrollPane(outputArea);
//        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
//        mainPanel.add(scrollPane, BorderLayout.EAST);
//
//        add(mainPanel);
//
//        // Action Listeners for Buttons
//        createButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//                    String name = nameField.getText();
//                    String email = emailField.getText();
//
//                    String query = "INSERT INTO student (id, name, email) VALUES (?, ?, ?)";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setInt(1, id);
//                    preparedStatement.setString(2, name);
//                    preparedStatement.setString(3, email);
//
//                    int rowsInserted = preparedStatement.executeUpdate();
//                    if (rowsInserted > 0) {
//                        outputArea.setText("Student created successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        readButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String query = "SELECT * FROM student";
//                    Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery(query);
//
//                    StringBuilder result = new StringBuilder("ID\tName\tEmail\n");
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String name = resultSet.getString("name");
//                        String email = resultSet.getString("email");
//                        result.append(id).append("\t").append(name).append("\t").append(email).append("\n");
//                    }
//                    outputArea.setText(result.toString());
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        updateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//                    String name = nameField.getText();
//                    String email = emailField.getText();
//
//                    String query = "UPDATE student SET name = ?, email = ? WHERE id = ?";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setString(1, name);
//                    preparedStatement.setString(2, email);
//                    preparedStatement.setInt(3, id);
//
//                    int rowsUpdated = preparedStatement.executeUpdate();
//                    if (rowsUpdated > 0) {
//                        outputArea.setText("Student updated successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//
//        deleteButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int id = Integer.parseInt(idField.getText());
//
//                    String query = "DELETE FROM student WHERE id = ?";
//                    PreparedStatement preparedStatement = connection.prepareStatement(query);
//                    preparedStatement.setInt(1, id);
//
//                    int rowsDeleted = preparedStatement.executeUpdate();
//                    if (rowsDeleted > 0) {
//                        outputArea.setText("Student deleted successfully!\n");
//                    }
//                } catch (Exception ex) {
//                    outputArea.setText("Error: " + ex.getMessage() + "\n");
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }
//
//    
//    // Main Method
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MainApp().setVisible(true);
//            }
//        });
//    }
//}
