package com.golansoft.apps.computerbackup;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.golansoft.apps.computerbackup.enums.HardCoded;



public class ComputerBackup implements ActionListener {

    DataSingleton dataSingleton;
    private static final Logger logger = LogManager.getLogger(ComputerBackup.class);

    JButton bProfilesRefresh, bProfileRun, bProfileDelete;
    JTextField tfUserName = new JTextField();
    JTextField tfHostName = new JTextField();
    JComboBox<String> ddProfileName = new JComboBox<>();



    private ComputerBackup() {}



    public static void main(String[] args) {
        ComputerBackup computerBackup = new ComputerBackup();
        computerBackup.setAndShowUi();
    }



    private void setAndShowUi() {

        // init the app.
        appInit();


        // Show the main UI
        JFrame fMain = new JFrame();
        fMain.setSize(500, 300);
        fMain.setTitle("Backup your computer, version " + dataSingleton.getAppVersion());
        fMain.setLayout(null);
        fMain.setLocation(new Point(200, 200));
        fMain.setResizable(false);
        fMain.setLocationRelativeTo(null);
        fMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set author
        JLabel lAuthor = generateAuthor();
        lAuthor.setForeground(Color.BLUE);


        // Set labels
        JLabel lUserName = new JLabel("User name :");
        JLabel lHostName = new JLabel("Host name :");
        JLabel lProfiles = new JLabel("Profiles :");


        // Buttons
        bProfileRun      = new JButton("Run !");
        bProfileDelete   = new JButton("Delete");
        bProfilesRefresh = new JButton("Refresh");


        // Location
        lAuthor.setBounds         (20, 250,  250, 20);
        lUserName.setBounds       (20,  20,  100, 20);
        lHostName.setBounds       (20,  60,  100, 20);
        lProfiles.setBounds       (20,  100, 100, 20);
        tfUserName.setBounds      (100, 20,  150, 20);
        tfHostName.setBounds      (100, 60,  150, 20);
        ddProfileName.setBounds   (100, 100, 200, 20);
        bProfileRun.setBounds     (20,  160, 120, 40);
        bProfileDelete.setBounds  (150, 160, 120, 40);
        bProfilesRefresh.setBounds(300, 100, 90,  20);


        // Add components on the form
        Container container = fMain.getContentPane();
        container.add(lAuthor);

        container.add(lUserName);
        container.add(lHostName);
        container.add(lProfiles);

        container.add(tfUserName);
        container.add(tfHostName);

        container.add(ddProfileName);

        container.add(bProfileRun);
        container.add(bProfileDelete);
        container.add(bProfilesRefresh);


        // Insert initial data into the form
        tfUserName.setText(dataSingleton.getUserName());
        tfHostName.setText(dataSingleton.getHostName());


        // Add action listeners
        bProfileRun.addActionListener     (this);
        bProfileDelete.addActionListener  (this);
        bProfilesRefresh.addActionListener(this);


        // Get profiles for the given name and host.
        Profiles profiles = new Profiles();
        List<String> profilesList = profiles.getProfiles(dataSingleton.getUserName(), dataSingleton.getHostName());
        if (! profilesList.isEmpty()) {
            for (String s : profilesList) {
                ddProfileName.addItem(s);
            }
        }
        ddProfileName.addItem(HardCoded.CREATE_NEW_PROFILE.getText());


        // show the form
        fMain.setVisible(true);
    }





    private JLabel generateAuthor() {
        JLabel l = new JLabel("Created by: Yaron Golan <yargolan@gmail.com>");
        l.setFont(new Font("Verdana", Font.ITALIC, 8));
        return l;
    }


    private void appInit() {
        dataSingleton = DataSingleton.getInstance();
    }



    private void create_new_profile() {
        System.out.println(122312312);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        logger.info("Button pressed - " + e.getActionCommand());

        String name = (String)ddProfileName.getSelectedItem();
        if (name == null) {
            logger.info("No profile name provided for " + e.getActionCommand());
            return;
        }


        Profile profile = new Profile(
                tfUserName.getText(),
                tfHostName.getText(),
                (String)ddProfileName.getSelectedItem()
        );


        if (e.getSource() == bProfileDelete) {
            if (name.equals(HardCoded.CREATE_NEW_PROFILE.getText())) {
                Messages.showMessage(Messages.MESSAGE_ERR, "Really ?");
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("You are about to delete the '%s' profile.\n", profile.getName()));
            stringBuilder.append("This action is not reversible !\n");
            stringBuilder.append("\n");
            stringBuilder.append("Are you sure that you want to delete it?");
            if (Messages.areYouSure(stringBuilder.toString())) {
                PythonRunner pythonRunner = new PythonRunner();
                pythonRunner.runCommand(PythonRunner.COMMAND_DEL, profile);
            }
            else {
                Messages.showMessage(Messages.MESSAGE_INF, "No profile was deleted.");
            }
        }
        else if (e.getSource() == bProfileRun) {
            if (name.equals(HardCoded.CREATE_NEW_PROFILE.getText())) {
                create_new_profile();
                return;
            }

            PythonRunner pythonRunner = new PythonRunner();
            pythonRunner.runCommand(PythonRunner.COMMAND_RUN, profile);
        }
        else if (e.getSource() == bProfilesRefresh) {

            // Clean existing profiles, if any.
            ddProfileName.removeAllItems();

            // Get profiles for the given name and host.
            Profiles profiles = new Profiles();
            List<String> profilesList = profiles.getProfiles(tfUserName.getText(), tfHostName.getText());
            if (! profilesList.isEmpty()) {
                for (String s : profilesList) {
                    ddProfileName.addItem(s);
                }
            }
            ddProfileName.addItem(HardCoded.CREATE_NEW_PROFILE.getText());
        }
        else {
            Messages.showMessage(Messages.MESSAGE_ERR, "Unknown button was pressed - " + e.getActionCommand());
        }
    }
}
