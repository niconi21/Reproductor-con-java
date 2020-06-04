package Objetos;
public final class Cancion {
   private String titulo,autor,genero,duracion;

    public Cancion(String titulo, String autor, String genero,String duracion) {
        setTitulo(titulo);
        setAutor(autor);
        setGenero(genero);
        setDuracion(duracion);
    }
    
   public Cancion(){
       this("Titulo","Autor","Genero","Duracion");
   } 

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
   
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
   
    public String toString(){
        return titulo+","+autor+","+genero+","+duracion;
    }
}
