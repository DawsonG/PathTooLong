import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils; 

public class PathTooLong extends Applet {
	/**
	 * Required to suppress warnings! :P
	 */
	private static final long serialVersionUID = 7208350071099494723L;

	public void init() {
		this.setLayout(null);
		this.setBackground(Color.lightGray);
		this.setSize(new Dimension(300, 70));

		final TextField txtPath = new TextField();
		txtPath.setBounds(10, 10, 250, 20);
		
		Button btnBrowse = new Button("...");
		btnBrowse.setBounds(262, 10, 20, 20);
		btnBrowse.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				JList list = new JList();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(list)) {
				   File folder = fc.getSelectedFile();
				   txtPath.setText(folder.getPath());
				}
			}

			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}	
        });
		
		
		Button btnExecute = new Button("Execute Path Removal");
        btnExecute.setBounds(80, 32, 150, 20);
        btnExecute.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {
				String path = txtPath.getText();
				if (path.length() == 0)
					return;
	            
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this folder? \n" + path, "Warning! - Please Confirm", JOptionPane.YES_NO_OPTION);
				if (response == 0) {
					if (runDelete(path)) {
						JOptionPane.showMessageDialog(null, "File path removed successfully!");
						txtPath.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "An error has occured.");
					}
				}
			}

			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}	
        });
        
		add(txtPath);
		add(btnBrowse);
		add(btnExecute);
   }


   public static void main(String [] args) {
       Frame f = new Frame("PathTooLong Utility");
       f.setResizable(false);
       f.addWindowListener(new WindowAdapter(){
    	   public void windowClosing(WindowEvent we){
    	       System.exit(0);
    	   }
       });
       
       PathTooLong ex = new PathTooLong();
       ex.init();

       f.add("Center", ex);
       f.pack();
       
       f.setVisible(true);
   }
   
   private boolean runDelete(String path) {
	   boolean retval = false;
	   try {
			File rm = new File(path);
			FileUtils.deleteDirectory(rm);
			retval = true;
		} catch (Exception e) {
			e.printStackTrace();
			retval = false;
		}
		return retval;
   }
}