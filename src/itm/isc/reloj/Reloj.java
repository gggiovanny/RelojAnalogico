package itm.isc.reloj;

public class Reloj
{
	private int hora;
	private int minuto;
	private int segundo;
	
	public Reloj()
	{
		this.hora = 0;
		this.minuto = 0;
		this.segundo = 0;
	}
	
	public Reloj(int hora, int minuto, int segundo)
	{
		super();
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
	}
	public int getHora()
	{
		return hora;
	}
	public void setHora(int hora)
	{
		this.hora = hora;
		if(this.hora >= 12)
		{
			this.hora = 0;
		}
	}
	public int getMinuto()
	{
		return minuto;
	}
	public void setMinuto(int minuto)
	{
		this.minuto = minuto;
		if(this.minuto >= 60)
		{
			setHora(getHora() + 1);
			this.minuto = 0;
		}
	}
	public int getSegundo()
	{
		return segundo;
	}
	public void setSegundo(int segundo)
	{
		this.segundo = segundo;
		if(this.segundo >= 60)
		{
			setMinuto(getMinuto() + 1);;
			this.segundo = 0;
		}
	}
	
	public void tick()
	{
		setSegundo(getSegundo() + 1);
	}

	@Override
	public String toString()
	{
		return "Reloj [hora="+hora+", minuto="+minuto+", segundo="+segundo+"]";
	}
	
}
