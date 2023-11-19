import java.util.Arrays;

/**
 * La clase Subject representa un sujeto de prueba con un identificador, nombre y secuencia de ADN.
 */
public class Subject {

    // Atributos de la clase
    private int id = 0;
    private String name;
    private String[] dna;

    /**
     * Constructor por defecto de la clase Subject.
     */
    public Subject() {
    }

    /**
     * Constructor de la clase Subject que inicializa sus atributos con los valores proporcionados.
     *
     * @param id   El identificador del sujeto.
     * @param name El nombre del sujeto.
     * @param dna  La secuencia de ADN del sujeto.
     */
    public Subject(int id, String name, String[] dna) {
        this.id = id;
        this.name = name;
        this.dna = dna;
    }

    /**
     * Obtiene el nombre del sujeto.
     *
     * @return El nombre del sujeto.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del sujeto.
     *
     * @param name El nuevo nombre del sujeto.
     */
    public void setName(String name) { this.name = name;}

    /**
     * Obtiene la secuencia de ADN del sujeto.
     *
     * @return La secuencia de ADN del sujeto.
     */
    public String[] getDna() {
        return dna;
    }

    /**
     * Establece la secuencia de ADN del sujeto.
     *
     * @param dna La nueva secuencia de ADN del sujeto.
     */
    public void setDna(String[] dna) {
        this.dna = dna;
    }

    /**
     * Establece el identificador del sujeto.
     *
     * @param id El nuevo identificador del sujeto.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve una representación en formato de cadena del objeto Subject.
     *
     * @return Una cadena que representa el objeto Subject.
     */
    @Override
    public String toString() {
        return "\nID:   " + id + "      NAME:   " + name + " \nDNA: " + Arrays.toString(dna) + "    ";
    }
}
