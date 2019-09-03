import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
// The Download Manager.
public class DownloadManager extends JFrame
implements Observer
{


// Add download text field.
private JTextField addTextField;
// Download table's data model.
private DownloadsTableModel tableModel;
// Table listing downloads.
private JTable table;
// These are the buttons for managing the selected download.
private JButton pauseButton, resumeButton;
private JButton cancelButton, clearButton;
// Currently selected download.
private Download selectedDownload;
// Flag for whether or not table selection is being cleared.
private boolean clearing;
// Constructor for Download Manager.
public DownloadManager()
{
// Set application title.
setTitle("Download Manager");
// Set window size.
setSize(640, 480);
// Handle window closing events.
addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e) {
actionExit();
}
});
// Set up file menu.
JMenuBar menuBar = new JMenuBar();
JMenu fileMenu = new JMenu("File");
fileMenu.setMnemonic(KeyEvent.VK_F);
JMenuItem fileExitMenuItem = new JMenuItem("Exit",
KeyEvent.VK_X);
fileExitMenuItem.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionExit();
}
});
fileMenu.add(fileExitMenuItem);
menuBar.add(fileMenu);
setJMenuBar(menuBar);
// Set up add panel.
JPanel addPanel = new JPanel();
addTextField = new JTextField(30);



addPanel.add(addTextField);
JButton addButton = new JButton("Add Download");
addButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionAdd();
}
});
addPanel.add(addButton);
// Set up Downloads table.
tableModel = new DownloadsTableModel();
table = new JTable(tableModel);
table.getSelectionModel().addListSelectionListener(new
ListSelectionListener() {
public void valueChanged(ListSelectionEvent e) {
tableSelectionChanged();
}
});
// Allow only one row at a time to be selected.
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
// Set up ProgressBar as renderer for progress column.
ProgressRenderer renderer = new ProgressRenderer(0, 100);
renderer.setStringPainted(true); // show progress text
table.setDefaultRenderer(JProgressBar.class, renderer);
// Set table's row height large enough to fit JProgressBar.
table.setRowHeight(
(int) renderer.getPreferredSize().getHeight());
// Set up downloads panel.
JPanel downloadsPanel = new JPanel();
downloadsPanel.setBorder(
BorderFactory.createTitledBorder("Downloads"));
downloadsPanel.setLayout(new BorderLayout());
downloadsPanel.add(new JScrollPane(table),
BorderLayout.CENTER);
// Set up buttons panel.
JPanel buttonsPanel = new JPanel();
pauseButton = new JButton("Pause");
pauseButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionPause();
}
});
pauseButton.setEnabled(false);
buttonsPanel.add(pauseButton);
resumeButton = new JButton("Resume");
resumeButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionResume();
}
});
resumeButton.setEnabled(false);
buttonsPanel.add(resumeButton);
cancelButton = new JButton("Cancel");
cancelButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionCancel();
}
});
cancelButton.setEnabled(false);
buttonsPanel.add(cancelButton);
clearButton = new JButton("Clear");
clearButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
actionClear();
}
});
clearButton.setEnabled(false);
buttonsPanel.add(clearButton);
// Add panels to display.
getContentPane().setLayout(new BorderLayout());
getContentPane().add(addPanel, BorderLayout.NORTH);
getContentPane().add(downloadsPanel, BorderLayout.CENTER);
getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
}
// Exit this program.
private void actionExit() {
System.exit(0);
}
// Add a new download.
private void actionAdd() {
URL verifiedUrl = verifyUrl(addTextField.getText());
if (verifiedUrl != null) {
tableModel.addDownload(new Download(verifiedUrl));
addTextField.setText(""); // reset add text field
} else {
JOptionPane.showMessageDialog(this,
"Invalid Download URL", "Error",
JOptionPane.ERROR_MESSAGE);
}
}
