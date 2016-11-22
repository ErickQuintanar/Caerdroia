public class Alquimista extends Personaje{

	public Alquimista(String nombre, int nivel){
		super(nombre, nivel);
		vida = vida*85;
		fuerza = fuerza*10;
		magia = magia*12;
		resistencia = resistencia*4;
		inteligencia = inteligencia*5;
		iniciativa = iniciativa*6;
		hFisica = new Habilidad("Habilidad Física", 0, 0);
		hMagica = new Habilidad("Habilidad Mágica", 0, 0);
	}

	public Alquimista(int nivel){
		super(nivel);
		vida = vida*85;
		fuerza = fuerza*10;
		magia = magia*12;
		resistencia = resistencia*4;
		inteligencia = inteligencia*5;
		iniciativa = iniciativa*6;
		hFisica = new Habilidad("Habilidad Física", 0, 0);
		hMagica = new Habilidad("Habilidad Mágica", 0, 0);
	}

}