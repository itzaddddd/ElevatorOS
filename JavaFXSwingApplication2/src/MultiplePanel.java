/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class MultiplePanel implements ActionListener{
    JFrame frame = new JFrame();
//    Broad broad = new Broad(2,2);
    Lift lift = new Lift();

    
    MultiplePanel(){
        frame.setPreferredSize(new Dimension(1400,960));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(broad,BorderLayout.CENTER);
        frame.add(lift,BorderLayout.EAST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
