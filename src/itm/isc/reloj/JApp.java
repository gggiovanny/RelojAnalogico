package itm.isc.reloj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class JApp extends JFrame
{

	Timer temporizador;
	private JPanel contentPane;
	private JSpinner spnHora;
	private JSpinner spnMinuto;
	private JSpinner spnSegundo;
	private Reloj reloj;
	private Draw draw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					JApp frame = new JApp();
					frame.setVisible(true);
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public JApp()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		reloj =  new Reloj();
		draw = new Draw(reloj);
		draw.setMinimumSize(new Dimension(500, 500));
		draw.setPreferredSize(new Dimension(500, 500));
		contentPane.add(draw);		
		
		JPanel pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(250, 500));
		pnlRight.setMinimumSize(new Dimension(250, 500));
		contentPane.add(pnlRight, BorderLayout.EAST);
		pnlRight.setLayout(null);
		
		JLabel lblRelojAnalogico = new JLabel("Reloj Analogico");
		lblRelojAnalogico.setBounds(69, 5, 111, 20);
		lblRelojAnalogico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlRight.add(lblRelojAnalogico);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(72, 70, 59, 14);
		pnlRight.add(lblHora);
		
		JLabel lblMinuto = new JLabel("Minuto:");
		lblMinuto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMinuto.setBounds(72, 112, 59, 14);
		pnlRight.add(lblMinuto);
		
		JLabel lblSegundo = new JLabel("Segundo:");
		lblSegundo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSegundo.setBounds(72, 149, 59, 14);
		pnlRight.add(lblSegundo);
						
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustar();
				temporizador.start();
			}
		});
		btnIniciar.setBounds(23, 366, 95, 23);
		pnlRight.add(btnIniciar);
		
		JButton btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temporizador.stop();
			}
		});
		btnParar.setBounds(144, 366, 95, 23);
		pnlRight.add(btnParar);
		
		JSlider sldVelocidad = new JSlider();
		sldVelocidad.setMaximum(1000);
		sldVelocidad.setValue(0);
		sldVelocidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {				
				temporizador.setDelay(1000 - sldVelocidad.getValue());
				System.out.println(1000-sldVelocidad.getValue());
			}
		});
		sldVelocidad.setBounds(23, 411, 200, 26);
		pnlRight.add(sldVelocidad);
		
		spnHora = new JSpinner();
		spnHora.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ajustar();
			}
		});
		spnHora.setBounds(141, 64, 39, 20);
		pnlRight.add(spnHora);
		
		spnMinuto = new JSpinner();
		spnMinuto.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ajustar();
			}
		});
		spnMinuto.setBounds(141, 106, 39, 20);
		pnlRight.add(spnMinuto);
		
		spnSegundo = new JSpinner();
		spnSegundo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				ajustar();
			}
		});
		spnSegundo.setBounds(141, 143, 39, 20);
		pnlRight.add(spnSegundo);
		
		JButton btnAjustar = new JButton("Ajustar");
		btnAjustar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajustar();
			}
		});
		btnAjustar.setBounds(23, 228, 95, 23);
		pnlRight.add(btnAjustar);
		
		JButton btnRestablecer = new JButton("Reset");
		btnRestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				draw.r.setHora(0);
				draw.r.setMinuto(0);
				draw.r.setSegundo(0);
				spnSegundo.setValue(0);
				spnMinuto.setValue(0);
				spnHora.setValue(0);
				draw.repaint();
			}
		});
		btnRestablecer.setBounds(144, 228, 95, 23);
		pnlRight.add(btnRestablecer);
		
		temporizador = new Timer(1000, new ActionListener() // Aumentar los segundos cada 1000 milisegundos xd
				{			
					@Override
					public void actionPerformed(ActionEvent e)
					{
						draw.r.tick();
						actualizarHoraEnInterfaz();
						System.out.println(draw.r.toString());
						draw.repaint();
					}
				});
	}
	
	private void ajustar()
	{
		if(!temporizador.isRunning())
		{			
			int horaInterfaz = Integer.parseInt(spnHora.getValue().toString());
			int minInterfaz = Integer.parseInt(spnMinuto.getValue().toString());
			int segInterfaz = Integer.parseInt(spnSegundo.getValue().toString());
			if(horaInterfaz < 0)
			{
				int horaCorregida =	12 + horaInterfaz;
				horaInterfaz = horaCorregida;
			}
			if(minInterfaz < 0)
			{
				int minCorregido = 60 + minInterfaz;
				minInterfaz = minCorregido;
			}
			if(segInterfaz < 0)
			{
				int segCorregido = 60 + segInterfaz;
				segInterfaz = segCorregido;
			}
			draw.r.setHora(horaInterfaz);
			draw.r.setMinuto(minInterfaz);
			draw.r.setSegundo(segInterfaz);
			
			spnSegundo.setValue(draw.r.getSegundo());
			spnMinuto.setValue(draw.r.getMinuto());
			spnHora.setValue(draw.r.getHora());
			
			draw.repaint();
		}
	}
	
	private void actualizarHoraEnInterfaz()
	{
		spnSegundo.setValue(draw.r.getSegundo());
		spnMinuto.setValue(draw.r.getMinuto());
		spnHora.setValue(draw.r.getHora());
	}
}
