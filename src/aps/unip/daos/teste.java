package aps.unip.daos;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class teste {

	public static void main(String[] args) {
		DAOBuscarUsuarios buscarUsuarios = new DAOBuscarUsuarios();
		Object[][] retorno = buscarUsuarios.busacarUsuarios("ad",15);
		System.out.println("f");
		
		JFrame frame = new JFrame();
		frame.setSize(600,600);
		frame.setLocation(0,0);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Image image = new ImageIcon((byte[]) retorno[3][2]).getImage();//passo de Byte para image
		Image imageRedimensionada = image.getScaledInstance(125, 125, java.awt.Image.SCALE_SMOOTH);// redimenciono
		ImageIcon icon = new ImageIcon(imageRedimensionada);//de image para imageicon
		
		JLabel lbl1 = new JLabel();
		lbl1.setSize(125,125);
		lbl1.setLocation(10,10);
		lbl1.setIcon(icon);
		lbl1.setOpaque(true);
		lbl1.setBackground(Color.yellow);
		frame.add(lbl1);
		
		Image image2 = new ImageIcon((byte[]) retorno[2][2]).getImage();//passo de Byte para image
		Image imageRedimensionada2 = image2.getScaledInstance(125, 125, java.awt.Image.SCALE_SMOOTH);// redimenciono
		ImageIcon icon2 = new ImageIcon(imageRedimensionada2);//de image para imageicon
		
		JLabel lbl2 = new JLabel();
		lbl2.setSize(125,125);
		lbl2.setLocation(140,10);
		lbl2.setIcon(icon2);
		lbl2.setOpaque(true);
		lbl2.setBackground(Color.yellow);
		frame.add(lbl2);
		

	}
}
