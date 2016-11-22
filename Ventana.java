import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame implements ActionListener{
	private int x, y, t, l, b, r;
	private MatteBorder border;
	private JPanel botones, mapa, datos, info;
	private JButton up, down, left, right;
	private JLabel nombre, vida, fuerza, magia, resistencia, inteligencia, iniciativa, experiencia, nivel, bolsa, informacion, arma, casco, botas, peto, accesorio, llave;
	private Mapa m;

	public Ventana(Mapa m){
		this.m = m;
		x = m.getPosicionX();
		y = m.getPosicionY();
		setTitle("Caerdroia");
		setSize(2000,2000);
		setLayout(new GridLayout(2,2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	public void initComponents(){
		mapa = new JPanel();
		mapa.setLayout(new GridLayout(5,5));
		drawMap(mapa);
		add(mapa);
		datos = new JPanel();
		datos.setLayout(new GridLayout(8,2));
		drawInfo(datos);
		add(datos);
		botones = new JPanel();
		botones.setLayout(new GridLayout(2,2));
		up = new JButton("Up");
		down = new JButton("Down");
		left = new JButton("Left");
		right = new JButton("Right");
		up.setActionCommand("up");
		down.setActionCommand("down");
		left.setActionCommand("left");
		right.setActionCommand("right");
		up.addActionListener(this);
		down.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
		botones.add(up);
		botones.add(down);
		botones.add(left);
		botones.add(right);
		add(botones);
		info = new JPanel();
		informacion = new JLabel("Caerdroia © 2016");
		info.add(informacion);
		add(info);
	}

	public void drawInfo(JPanel datos){
		nombre = new JLabel("Nombre: "+m.getHeroe().getNombre());
		vida = new JLabel("Vida: "+m.getHeroe().getVida()+"/"+m.getHeroe().getMaxVida());
		fuerza = new JLabel("Fuerza: "+m.getHeroe().getFuerza());
		magia = new JLabel("Magia: "+m.getHeroe().getMagia());
		resistencia = new JLabel("Resistencia: "+m.getHeroe().getResistencia());
		inteligencia = new JLabel("Inteligencia: "+m.getHeroe().getInteligencia());
		iniciativa = new JLabel("Iniciativa: "+m.getHeroe().getIniciativa());
		experiencia = new JLabel("Experiencia: "+m.getHeroe().getExperiencia());
		bolsa = new JLabel("Bolsa: ");		
		nivel = new JLabel("Nivel: "+m.getHeroe().getNivel());
		arma = new JLabel("Arma: "+m.getHeroe().getBolsa().getArma().getNombre());
		casco = new JLabel("Casco: "+m.getHeroe().getBolsa().getCasco().getNombre());
		botas = new JLabel("Botas: "+m.getHeroe().getBolsa().getBotas().getNombre());
		peto = new JLabel("Peto: "+m.getHeroe().getBolsa().getPeto().getNombre());
		accesorio = new JLabel("Accesorio: "+m.getHeroe().getBolsa().getAccesorio().getNombre());
		llave = new JLabel("Llave: "+m.getHeroe().getBolsa().getLlave());
		datos.add(nombre);
		datos.add(vida);
		datos.add(fuerza);
		datos.add(magia);
		datos.add(resistencia);
		datos.add(inteligencia);
		datos.add(iniciativa);
		datos.add(experiencia);
		datos.add(bolsa);
		datos.add(nivel);
		datos.add(arma);
		datos.add(casco);
		datos.add(botas);
		datos.add(peto);
		datos.add(accesorio);
		datos.add(llave);
	}

	public void drawMap(JPanel mapa){
		for(int i = 0; i<5; i++){
			for(int j = 0; j<5; j++){
				JLabel celda = new JLabel();
				celda.setHorizontalAlignment(JLabel.CENTER);
				if(i==y && x==j){
					celda.setText("Héroe");
				}else{
					if(m.getCamara(j,i).isEntered() == true){
						celda.setText("Despejado");
					}else{
						celda.setText(" ");
					}
				}
				t =	changeNumber(m.getCamara(j,i).getUp());
				l =	changeNumber(m.getCamara(j,i).getLeft());
				b =	changeNumber(m.getCamara(j,i).getDown());
				r =	changeNumber(m.getCamara(j,i).getRight());
				border = BorderFactory.createMatteBorder(t,l,b,r, new Color(165,42,42));			
				celda.setBorder(border);
				mapa.add(celda);
			}
		}
	}

	 public int changeNumber(int num){
	 	switch(num){
	 		case 0:
	 			return 4;
	 		case 1:
	 			return 2;
	 		case 2:
	 			return 0;
	 	}
	 	return 0;
	 }

	  public void actionPerformed(ActionEvent e){
	 	String wall = e.getActionCommand();
	 	boolean move = false;
	 	int w;
	 	switch(wall){
	 		case "up":
	 			w = m.getCamara(x,y).getUp();
	 			move = validateMove(w, wall);
	 			if (move == true){
	 				y--;
	 				m.setPosicionY(y);
	 				mapa.removeAll();
	 				drawMap(mapa);
	 				mapa.revalidate();
	 				mapa.repaint();
	 				m.setHeroe(m.getCamara(x,y).cicloCamara(m.getHeroe()));
	 				if(m.getHeroe().getVida()<=0){
	 					dispose();
	 				}
	 				System.out.println(x+","+y);
	 			}
	 			break;
	 		case "down":
	 			w = m.getCamara(x,y).getDown();
	 			move = validateMove(w, wall);
	 			if (move == true){
	 				y++;
	 				m.setPosicionY(y);
	 				mapa.removeAll();
	 				drawMap(mapa);
	 				mapa.revalidate();
	 				mapa.repaint();
	 				m.setHeroe(m.getCamara(x,y).cicloCamara(m.getHeroe()));
	 				if(m.getHeroe().getVida()<=0){
	 					dispose();
	 				}
	 				System.out.println(x+","+y);
	 			}
	 			break;
	 		case "left":
	 			w = m.getCamara(x,y).getLeft();
	 			move = validateMove(w, wall);
	 			if (move == true){
	 				x--;
	 				m.setPosicionX(x);
	 				mapa.removeAll();
	 				drawMap(mapa);
	 				mapa.revalidate();
	 				mapa.repaint();
	 				m.setHeroe(m.getCamara(x,y).cicloCamara(m.getHeroe()));
	 				if(m.getHeroe().getVida()<=0){
	 					dispose();
	 				}
	 				System.out.println(x+","+y);
	 			}
	 			break;
	 		case "right":
	 			w = m.getCamara(x,y).getRight();
	 			move = validateMove(w, wall);
	 			if (move == true){
	 				x++;
	 				m.setPosicionX(x);
	 				mapa.removeAll();
	 				drawMap(mapa);
	 				mapa.revalidate();
	 				mapa.repaint();
	 				m.setHeroe(m.getCamara(x,y).cicloCamara(m.getHeroe()));
	 				if(m.getHeroe().getVida()<=0){
	 					dispose();
	 				}
	 				System.out.println(x+","+y);
	 			}
	 			break;
	 	}
	 }

	 public boolean validateMove(int w, String dir){
	 	switch(w){
	 		case 0:
	 			JOptionPane.showMessageDialog(null, "Es una pared, no puedes moverte");
	 			return false;
	 		case 1:
	 			JOptionPane.showMessageDialog(null, "Puerta cerrada");
	 			return door(dir);
	 		case 2:
	 			JOptionPane.showMessageDialog(null, "Has cambiado de cámara");
	 			return true;
	 	}
	 	return  false;
	 }

	 public boolean door(String d){
	 	switch(d){
	 		case "up":
	 			if(m.getCamara(x,y-1).getNivel()==m.getHeroe().getBolsa().getLlave().getNivel()){
	 				JOptionPane.showMessageDialog(null, "Has utilizado tu llave");
	 				//resetear llave y cambiar valor de ambas camaras a open-2
	 				m.getHeroe().getBolsa().getLlave().setNivel(0);
	 				m.getCamara(x,y).setUp(2);
	 				m.getCamara(x,y-1).setDown(2);
	 				return true;
	 			}else{
	 				return false;
	 			}
	 		case "down":
	 			if(m.getCamara(x,y+1).getNivel()==m.getHeroe().getBolsa().getLlave().getNivel()){
	 				JOptionPane.showMessageDialog(null, "Has utilizado tu llave");
	 				m.getHeroe().getBolsa().getLlave().setNivel(0);
	 				m.getCamara(x,y+1).setUp(2);
	 				m.getCamara(x,y).setDown(2);
	 				return true;
	 			}else{
	 				return false;
	 			}
	 		case "left":
	 			if(m.getCamara(x-1,y).getNivel()==m.getHeroe().getBolsa().getLlave().getNivel()){
	 				JOptionPane.showMessageDialog(null, "Has utilizado tu llave");
	 				m.getHeroe().getBolsa().getLlave().setNivel(0);
	 				m.getCamara(x,y).setLeft(2);
	 				m.getCamara(x-1,y).setRight(2);
	 				return true;
	 			}else{
	 				return false;
	 			}
	 		case "right":
	 			if(m.getCamara(x+1,y).getNivel()==m.getHeroe().getBolsa().getLlave().getNivel()){
	 				JOptionPane.showMessageDialog(null, "Has utilizado tu llave");
	 				m.getHeroe().getBolsa().getLlave().setNivel(0);
	 				m.getCamara(x+1,y).setLeft(2);
	 				m.getCamara(x,y).setRight(2);
	 				return true;
	 			}else{
	 				return false;
	 			}
	 		}
	 	return false;
	 }
}