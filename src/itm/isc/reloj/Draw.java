package itm.isc.reloj;

import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

public class Draw extends JPanel
{
	public Reloj r;
	
	/**
	 * Create the panel. xd
	 */
	public Draw(Reloj r)
	{
		this.r = r;
		setBackground(Color.black);
	}

	public void paintComponent(Graphics gg)
	{
		super.paintComponent(gg);
		dibujarReloj(gg);	
	}
	
	public void dibujarReloj(Graphics gg)
	{
		int cradio;
		int ancho = getWidth();
		int alto = getHeight() - 20;
		
		int ox = getWidth() / 2;
		int oy = getHeight() /2;
		
		//relleno
//		gg.setColor(Color.black);
//		cradio = 200;
//		gg.fillOval(ox - cradio, oy - cradio, cradio*2, cradio*2);
		
		//circulo exterior
		gg.setColor(Color.BLUE);
		cradio = 200;
		gg.drawOval(ox - cradio, oy - cradio, cradio*2, cradio*2);
		
		//rayitas de las horas
		gg.setColor(Color.magenta);
		int x1,y1, x2, y2;
		double angulo;
		for (int i = 0; i<12; i++)
		{
			angulo = i*30;			
			
			x1 = (int)((cradio - 20 )* Math.cos(Math.toRadians(angulo)));
			y1 = (int)((cradio - 20 )* Math.sin(Math.toRadians(angulo)));			
			
			x2 = (int)((cradio - 2)* Math.cos(Math.toRadians(angulo)));
			y2 = (int)((cradio - 2)* Math.sin(Math.toRadians(angulo)));
			
			gg.drawLine(ox + x1, oy + y1, ox + x2, oy + y2);
		}
		
		//numeros de las horas
		gg.setColor(Color.GREEN);
		String num;
		for (int i = 1; i<=12; i++)
		{
			angulo = 270 + i*30;			
			num = "" + i;
			
			x2 = (int)((cradio - 33 )* Math.cos(Math.toRadians(angulo)));
			y2 = (int)((cradio - 33 )* Math.sin(Math.toRadians(angulo)));			
			
			gg.drawString(num, ox - 3 + x2, oy + 5 + y2);
		}		
		
		//segundero
		gg.setColor(Color.red);
		double anguloS = 270 + (r.getSegundo() * 6);		
		int dx, dy;		
		dx = (int)((cradio - 20)* Math.cos(Math.toRadians(anguloS)));
		dy = (int)((cradio - 20)* Math.sin(Math.toRadians(anguloS)));		
		gg.drawLine(ox, oy, ox + dx, oy + dy);
		
		//minutero
		gg.setColor(Color.YELLOW);
		anguloS = 270 + (r.getMinuto() * 6) + (r.getSegundo() * 0.1);
		dx = (int)((cradio - 60)* Math.cos(Math.toRadians(anguloS)));
		dy = (int)((cradio - 60)* Math.sin(Math.toRadians(anguloS)));	
		gg.drawLine(ox, oy, ox + dx, oy + dy);
		
		//horario(?
		gg.setColor(Color.cyan);
		anguloS = 270 + r.getHora()*30 + r.getMinuto()*0.5;
		dx = (int)((cradio - 100)* Math.cos(Math.toRadians(anguloS)));
		dy = (int)((cradio - 100)* Math.sin(Math.toRadians(anguloS)));	
		gg.drawLine(ox, oy, ox + dx, oy + dy);
		
		cradio = 5;
		gg.fillOval(ox - cradio, oy - cradio, cradio*2, cradio*2);
	}
}
