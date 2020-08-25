package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import commands.*;

public class WardOpsPanel extends JPanel {
    public WardOpsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

    
        JButton wardCurrentStateButton = new JButton("Display current state");
        wardCurrentStateButton.setMaximumSize(wardCurrentStateButton.getPreferredSize());
        add(wardCurrentStateButton);
        wardCurrentStateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        wardCurrentStateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CurrentStateCommand currentStateCommand = new CurrentStateCommand();
                currentStateCommand.findCurState(); 
                String currentStateMessage = currentStateCommand.getCurState();
                if (currentStateCommand.wasSuccessful()) {
                    JOptionPane.showMessageDialog(null, currentStateMessage); 
                }
                else {
                    JOptionPane.showMessageDialog(null, currentStateCommand.getErrorMessage()); 
                }
            }
        });
        add(Box.createVerticalGlue());

     
        BedsPanel interactiveBedList = new BedsPanel();
        add(interactiveBedList);
        add(Box.createVerticalGlue());

        //Exit button for this panel
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