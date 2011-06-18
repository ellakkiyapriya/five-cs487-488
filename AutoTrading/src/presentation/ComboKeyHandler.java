/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Dinh
 */
public class ComboKeyHandler extends KeyAdapter{
    private final JComboBox comboBox;
    private final Vector<Object> list = new Vector<Object>();
    
    public ComboKeyHandler(JComboBox combo) {
        this.comboBox = combo;
        for(int i=0;i<comboBox.getModel().getSize();i++) {
            list.addElement(comboBox.getItemAt(i));
        }
    }
    private boolean shouldHide = false;
    @Override public void keyTyped(final KeyEvent e) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                String text = ((JTextField)e.getSource()).getText();
                if(text.length()==0) {
                    setSuggestionModel(comboBox, new DefaultComboBoxModel(list), "");
                    comboBox.hidePopup();
                }else{
                    ComboBoxModel m = getSuggestedModel(list, text);
                    if(m.getSize()==0 || shouldHide) {
                        comboBox.hidePopup();
                    }else{
                        setSuggestionModel(comboBox, m, text);
                        comboBox.showPopup();
                    }
                }
            }
        });
    }
    @Override public void keyPressed(KeyEvent e) {
        JTextField textField = (JTextField)e.getSource();
        String text = textField.getText();
        shouldHide = false;
        switch(e.getKeyCode()) {
          case KeyEvent.VK_RIGHT:
            for(Object s: list) {
                if(s.toString().startsWith(text)) {
                    textField.setText(s.toString());
                    return;
                }
            }
            break;
          case KeyEvent.VK_ENTER:
            if(!list.contains(text)) {
                list.addElement(text);
                Collections.sort(list, new ObjectComparable());
                //setSuggestionModel(comboBox, new DefaultComboBoxModel(list), text);
                setSuggestionModel(comboBox, getSuggestedModel(list, text), text);
            }
            shouldHide = true;
            break;
          case KeyEvent.VK_ESCAPE:
            shouldHide = true;
            break;
        }
    }
    private static void setSuggestionModel(JComboBox comboBox, ComboBoxModel mdl, String str) {
        comboBox.setModel(mdl);
        comboBox.setSelectedIndex(-1);
        ((JTextField)comboBox.getEditor().getEditorComponent()).setText(str);
    }
    private static ComboBoxModel getSuggestedModel(Vector<Object> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(Object s: list) {
            if(s.toString().startsWith(text)) m.addElement(s);
        }
        return m;
    }

    private class ObjectComparable implements Comparator<Object> {

        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
        }
        
    }
}