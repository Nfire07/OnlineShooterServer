import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    private static ArrayList<ShooterServer> servers = new ArrayList<>();
    private static DefaultTableModel tableModel;
    private static JTable table;

    public static void main(String[] args) {

        JFrame window = new JFrame("--Online Shooter Server--");
        window.setSize(750, 520);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.DARK_GRAY);
        window.setResizable(false);
        window.setLayout(null);

        JLabel title = new JLabel("Online Shooter Server Manager");
        title.setBounds(0, 20, window.getWidth(), 30);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("monospace", Font.BOLD, 22));
        window.add(title);

        JLabel portLabel = new JLabel("Port:");
        portLabel.setBounds(50, 70, 80, 30);
        portLabel.setForeground(Color.WHITE);
        window.add(portLabel);

        JTextField portField = new JTextField("");
        portField.setBounds(130, 70, 150, 30);
        portField.setHorizontalAlignment(JTextField.CENTER);
        window.add(portField);

        JLabel mapLabel = new JLabel("Map:");
        mapLabel.setBounds(50, 110, 80, 30);
        mapLabel.setForeground(Color.WHITE);
        window.add(mapLabel);

        String[] mapList = {"0", "1", "2", "3", "4"};
        JComboBox<String> mapBox = new JComboBox<>(mapList);
        mapBox.setBounds(130, 110, 150, 30);
        window.add(mapBox);

        JButton startButton = new JButton("Start Server");
        startButton.setBounds(300, 70, 150, 30);
        startButton.setFocusable(false);
        startButton.setBackground(Color.GREEN);
        startButton.setForeground(Color.BLACK);
        startButton.setBorder(null);

        startButton.addActionListener((e) -> {
            int port;
            int map;

            try {
                port = Integer.parseInt(portField.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(startButton, "Please insert a valid port!");
                return;
            }

            map = Integer.parseInt(mapBox.getSelectedItem().toString());

            try {
                ShooterServer server = new ShooterServer(port, map);
                servers.add(server);
                tableModel.addRow(new Object[]{port, "Active", "REMOVE", map});
                portField.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(startButton, "Error: Port already in use or invalid!");
            }
        });
        window.add(startButton);

        String[] columnNames = {"Port", "State", "Action", "Map"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        };

        table = new JTable(tableModel);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK);
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 160, 650, 300);
        window.add(scrollPane);

        table.getColumn("Action").setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
            JButton btn = new JButton("REMOVE");
            btn.setBackground(Color.RED);
            btn.setForeground(Color.WHITE);
            btn.setFocusable(false);
            return btn;
        });

        table.getColumn("Action").setCellEditor(new AbstractCellEditor() {
            private JButton btn = new JButton("REMOVE");

            {
                btn.setBackground(Color.RED);
                btn.setForeground(Color.WHITE);
                btn.setFocusable(false);

                btn.addActionListener((e) -> {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        ShooterServer server = servers.get(row);
                        server.stop();
                        servers.remove(row);
                        tableModel.removeRow(row);
                        fireEditingStopped();
                    }
                });
            }

            @Override
            public Object getCellEditorValue() {
                return "Elimina";
            }

            @Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                    boolean isSelected, int row, int column) {
                return btn;
            }
        });

        window.setVisible(true);
    }
}

class ShooterServer {
    private ServerSocket serverSocket;
    private Socket client1, client2;
    private PrintWriter writer1, writer2;
    private BufferedReader reader1, reader2;
    private int port;
    private int map;       
    private boolean running = true;

    public ShooterServer(int port, int map) throws IOException {
        this.port = port;
        this.map = map;

        this.serverSocket = new ServerSocket(port);
        
        Thread serverThread = new Thread(() -> {
            try {
                client1 = serverSocket.accept();
                GameMap[] gameMaps = new GameMap[2];
                writer1 = new PrintWriter(client1.getOutputStream(), true);
                reader1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
                String nickname1 = reader1.readLine();
                System.out.println(nickname1);
                String screenSize1 = reader1.readLine();
                String[] wh1 = screenSize1.split("x");
                int width1 = Integer.parseInt(wh1[0]);
                int height1 = Integer.parseInt(wh1[1]);
                gameMaps[0] = new GameMap(map, width1, height1);
                writer1.println("" + map);
                int[] pos1 = gameMaps[0].getStartingPosition();
                writer1.println(pos1[0] + ";" + pos1[1]);

                client2 = serverSocket.accept();
                writer2 = new PrintWriter(client2.getOutputStream(), true);
                reader2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
                String nickname2 = reader2.readLine();
                System.out.println(nickname2);
                String screenSize2 = reader2.readLine();
                String[] wh2 = screenSize2.split("x");
                int width2 = Integer.parseInt(wh2[0]);
                int height2 = Integer.parseInt(wh2[1]);
                gameMaps[1] = new GameMap(map, width2, height2);
                writer2.println("" + map);
                int[] pos2 = gameMaps[1].getStartingPosition();
                writer2.println(pos2[0] + ";" + pos2[1]);

                writer1.println("START");
                writer2.println("START");

                Thread t1 = new Thread(() -> passMessages(reader1, writer2, nickname1));
                Thread t2 = new Thread(() -> passMessages(reader2, writer1, nickname2));

                t1.start();
                t2.start();

                while (running) {
                    Socket extra = serverSocket.accept();
                    PrintWriter pw = new PrintWriter(extra.getOutputStream(), true);
                    pw.println("FULL");
                    extra.close();
                }

            } catch (IOException e) {
                if (running) {
                    e.printStackTrace();
                }
            }
        });

        serverThread.setDaemon(true);
        serverThread.start();
    }

    private void passMessages(BufferedReader reader, PrintWriter writer, String clientName) {
        try {
            String message;
            while ((message = reader.readLine()) != null && running) {
                writer.println(message);
            }
        } catch (IOException e) {
            if (running) {
                System.out.println(clientName + " disconnected");
            }
        }
    }

    public void stop() {
        running = false;
        try {
            if (client1 != null) client1.close();
            if (client2 != null) client2.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class AbstractCellEditor extends javax.swing.AbstractCellEditor
        implements javax.swing.table.TableCellEditor {
    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
