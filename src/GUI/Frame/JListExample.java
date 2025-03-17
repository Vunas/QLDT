// package GUI.Frame;

// import javax.swing.*;

// public class JListExample {
//     public static void main(String[] args) {
//         JFrame frame = new JFrame("JList Example");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Create a JList with multiple selection
//         String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
//         JList<String> list = new JList<>(items);
//         list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

//         // Add the JList to a JScrollPane
//         JScrollPane scrollPane = new JScrollPane(list);
//         scrollPane.setBounds(50, 50, 200, 100);

//         // Add the scroll pane to the frame
//         frame.setLayout(null);
//         frame.add(scrollPane);
//         frame.setVisible(true);

//         // Print selected items on selection change
//         list.addListSelectionListener(e -> {
//             if (!e.getValueIsAdjusting()) {
//                 System.out.println("Selected: " + list.getSelectedValuesList());
//             }
//         });
//     }
// }


