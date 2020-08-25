package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import containers.DoctorSetAccess;

/**
 * The panel that is responsible for the Doctor Operations. Contains a button to add a doctor, a text field to look up doctors by their name,
 * a buttons to list all the doctors in the system, and an exit button.
 */
public class DoctorOpsPanel extends JPanel {
    public DoctorOpsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        
        JButton addButton = new JButton("Add doctor");
        addButton.setMaximumSize(addButton.getPreferredSize());
        add(addButton);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent event) {
                DoctorAddFrame frame = new DoctorAddFrame(); 
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        add(Box.createVerticalGlue());


        DoctorAccessPanel accessPanel = new DoctorAccessPanel();
        add(accessPanel);
        add(Box.createVerticalGlue());

        
        JButton listAllButton = new JButton("List all doctors in system");
        listAllButton.setMaximumSize(listAllButton.getPreferredSize());
        add(listAllButton);
        listAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        listAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) { 
                JOptionPane.showMessageDialog(null, DoctorSetAccess.dictionary().values());
            }
        });
        add(Box.createVerticalGlue());

        
        final JButton exitButton = new JButton("Exit");
        exitButton.setMaximumSize(exitButton.getPreferredSize());
        add(exitButton);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                exitButton.getTopLevelAncestor().setVisible(false);
            }
        });
        add(Box.createVerticalGlue());
    }

    public static final long serialVersionUID = 1;
}