import javax.swing.*;
import java.util.ArrayList;
/**
 * Clase principal que ejecuta el programa de prueba de mutación en ADN. Permite al usuario interactuar
 * mediante un menú para ingresar, eliminar, realizar pruebas y mostrar información sobre sujetos de prueba.
 */
public class Main {
    /**
     * Método principal que inicia la ejecución del programa.
     *
     * @param args Argumentos de la línea de comandos (no utilizado en este programa).
     */
    public static void main(String[] args) {
        // Crear una lista donde se almacenan los sujetos
        ArrayList<Subject> subList = new ArrayList<>();
        // Se agregan casos de prueba predefinidos
        testCases(subList);

        // Bucle principal
        while (true) {
            // Mostrar menú al usuario
                String menu = JOptionPane.showInputDialog("""
                        --- MENU ---
                        1) Enter a sample
                        2) Delete a sample
                        3) Perform test
                        4) Show samples (%d stored)
                        Cancel to  Exit""".formatted(subList.size()));

                // Verificar si se ingresó una opción en el menú
                if (menu != null){
                    // Validar la opción ingresada
                    Integer numMenu = inputValidator(menu, 4);
                    if (numMenu != null) {
                        if (numMenu == 1) {                 //Opcion 1: Agregar un nuevo sujeto
                            validSubjectCreator(subList);
                        } else if (numMenu == 2) {          //Opcion 2: Eliminar un sujeto
                            deleteSample(subList);
                        } else if (numMenu == 3) {          //Opcion 3: Realizar prueba de mutación
                            performTest(subList);
                        } else if (numMenu == 4) {          //Opcion 4:  Mostrar la lista de sujetos
                            JOptionPane.showMessageDialog(null, showList(subList));
                        }
                    }
                }else{          // Si se elige Cancelar, muestra el mensaje de despedida y sale del programa.
                    JOptionPane.showMessageDialog(null, "---- GOOD BYE ----");
                    break;
                }

        }
    }

    /**
     * Genera una representación de texto de la lista de sujetos de prueba, incluyendo sus identificadores, nombres y ADN.
     * Si la lista está vacía, devuelve un mensaje indicando que la lista está vacía.
     *
     * @param subjectsAL La lista de sujetos de prueba.
     * @return Una cadena que representa la lista de sujetos de prueba, o un mensaje de lista vacía si la lista está vacía.
     */
    public static String showList(ArrayList<Subject> subjectsAL) {
        if (subjectsAL.isEmpty()) {
            return "Empty List";
        } else {
            String aList = "";
            for (int i = 0; i < subjectsAL.size(); i++) {
                subjectsAL.get(i).setId(i + 1);     //Actualiza las id de los sujetos.
                aList = aList.concat("\t" + subjectsAL.get(i).toString() + "\n");
            }
            return aList;
        }
    }

    /**
     * Elimina un sujeto de prueba de la lista de sujetos disponibles. Muestra una lista de sujetos y solicita al usuario
     * que elija un sujeto por su identificador. Luego, elimina el sujeto seleccionado de la lista y muestra un mensaje
     * de confirmación.
     *
     * @param subjectsAL La lista de sujetos de prueba disponibles.
     */
    public static void deleteSample(ArrayList<Subject> subjectsAL) {
        String option;
        Integer numOption;
        while (true) {
            option = JOptionPane.showInputDialog(showList(subjectsAL) + "\nChose one id: ");
            if (option != null){
                numOption = inputValidator(option, subjectsAL.size());
                if (numOption != null) {
                    String msg = subjectsAL.get(numOption-1).getName();
                    JOptionPane.showMessageDialog(null, msg + " was deleted.");
                    subjectsAL.remove(subjectsAL.get(numOption-1));
                    break;
                }
            }else{
                break;
            }
        }
    }

    /**
     * Realiza una prueba para determinar si un sujeto de prueba es mutante o no. Muestra una lista de sujetos disponibles
     * y solicita al usuario que elija un sujeto por su identificador. Luego, realiza la prueba de mutación en la secuencia
     * de ADN del sujeto seleccionado y muestra el resultado.
     *
     * @param subjectsAL La lista de sujetos de prueba disponibles.
     */
    public static void performTest(ArrayList<Subject> subjectsAL) {
        Tester test = new Tester();
        String option;
        Integer numOption;
        while (true) {
            option = JOptionPane.showInputDialog(showList(subjectsAL) + "\nChose one id: ");
            if(option != null){
                numOption = inputValidator(option, subjectsAL.size());
                if (numOption != null) {
                    String name = subjectsAL.get(numOption-1).getName();
                    String msg = (test.isMutant(subjectsAL.get(numOption-1).getDna())) ? "MUTANT" : "NOT MUTANT";
                    JOptionPane.showMessageDialog(null, name + ": " + msg);
                    break;
                }
            }else {
                break;
            }
        }
    }

    /**
     * Crea y valida la información de un nuevo sujeto de prueba, incluyendo su nombre y secuencia de ADN.
     * Si el nombre y la secuencia de ADN son válidos, se crea un nuevo objeto Subject y se agrega a la lista de sujetos.
     *
     * @param subjectsAL La lista de sujetos a la que se agregará el nuevo sujeto de prueba.
     */
    public static void validSubjectCreator(ArrayList<Subject> subjectsAL){
        String name = nameCreator();
        if (name != null) {
            String[] dna = dnaCreator();
            if (dna != null) {
                Subject subj = new Subject();
                subj.setName(name);
                subj.setDna(dna);
                subjectsAL.add(subj);
            }
        }
    }

    /**
     * Solicita al usuario que ingrese una serie de secuencias de ADN para crear un conjunto de genes.
     * Cada secuencia debe tener una longitud de 6 caracteres y contener solo los caracteres 'A', 'T', 'G' o 'C'.
     *
     * @return Un arreglo de cadenas que representa las secuencias de ADN ingresadas y validadas, o null si se cancela la entrada.
     */
    public static String[] dnaCreator() {
        String[] dna = new String[6];
        for (int i = 0; i < dna.length; i++) {
            while (true) {
                // Solicitar al usuario que ingrese una secuencia de ADN
                String gens = JOptionPane.showInputDialog("Insert the sequence Nª" + (i + 1) +
                        " (6 genes like A, T, G, C):");
                // Validar la secuencia ingresada por el usuario
                if (gens == null) {
                    return null;
                } else {
                    // Validar la longitud y los caracteres de la secuencia ingresada

                    if (gens.length() != 6) {
                        JOptionPane.showMessageDialog(null,
                                "<html><div style='color: red;'>Invalid length, please try again.</div></html>");
                    } else if (gens.matches(".*[^ATGC].*")) {
                        JOptionPane.showMessageDialog(null,
                                "<html><div style='color: red;'>Invalid characters, please try again.</div></html>");
                    } else if (gens.matches("^[ATGC]+$")) {
                        JOptionPane.showMessageDialog(null, "Sequence Nª" + (i + 1) + " : VALID");
                        dna[i] = gens;
                        break;
                    }
                }
            }
        }
        return dna;
    }

    /**
     * Solicita al usuario que ingrese el nombre de un sujeto de prueba y valida la entrada.
     * El nombre debe contener solo letras y tener una longitud mayor a 1.
     *
     * @return Un objeto String que representa el nombre validado en minúsculas, o null si se cancela la entrada.
     */
    public static String nameCreator() {
        String name;
        while (true) {
            name = JOptionPane.showInputDialog("Enter the name of the test subject: ");
            if (name == null) {
                return null;
            } else {
                if (name.matches("[a-zA-Z]+") && name.length() > 1) {
                    return name.toLowerCase();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "<html><div style='color: red;'>Invalid name, please try again.</div></html>");
                }
            }
        }
    }


    /**
     * Valida la entrada del usuario para asegurarse de que sea un número válido en el rango especificado.
     * @param input La cadena de entrada del usuario.
     * @param range El rango máximo permitido para el número.
     * @return Un objeto Integer que representa el número validado, o null si la entrada no es válida.
     */
    public static Integer inputValidator(String input, int range) {
        // Verifica si la entrada del usuario es un número válido en el rango especificado
        if (!input.isEmpty() && !input.isBlank() && input.matches("[0-9]*$")) {
            int len = input.length();
            int inputNumber = Integer.parseInt(input);
            if (inputNumber > 0 && inputNumber <= range && len == 1) {
                return inputNumber;
            }
        }
        //Muestra un mensaje de error si la entrada no es válida
        JOptionPane.showMessageDialog(null,
                            "<html><div style='color: red;'>Invalid input, please try again.</div></html>");
        return null;
    }


    /**
     * Genera sujetos de prueba con sus respectivas cadenas de ADN y los agrega a una lista de sujetos.
     *
     * @param sl La lista de sujetos a la que se agregarán los sujetos de prueba.
     */
    public static void testCases(ArrayList<Subject> sl) {
        // Crear sujetos de prueba con sus respectivas cadenas de adn.
        String[] dnaHuman = {"AATTCC", "CCAATT", "GGGCCC", "AAATTT", "CCCAAA", "TTTGGG"};
        Subject human = new Subject(1, "Enzo", dnaHuman);

        String[] dnaMutant = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        Subject mutant1 = new Subject(2, "beast", dnaMutant);

        String[] dnaHorizontal = {"AAAATT", "CCCCCC", "TTAAAA", "AGAAGG", "CTCCTA", "TCACTG"};
        Subject mutant2 = new Subject(2, "cyclops", dnaHorizontal);

        String[] dnaVertical = {"ATGCGG", "ATATGT", "ATATGG", "ATAAGG", "CTACTG", "TTACTG"};
        Subject mutant3 = new Subject(3, "storm", dnaVertical);

        String[] firstDiagonal = {"ACGCTT", "CACGCT", "CTATGC", "ACTATG", "TACAAT", "GTACCA"};
        Subject mutant4 = new Subject(2, "wolverine", firstDiagonal);

        String[] secondDiagonal = {"TGACTA", "CACGAT", "TCTATG", "CGAGGA", "TATGTG", "ACGCAC"};
        Subject mutant5 = new Subject(2, "gambit", secondDiagonal);

        String[] bothDiagonals = {"ACTACG", "TGCTGC", "ACTATA", "GTACAT", "CAGTAG", "ATCTA"};
        Subject mutant6 = new Subject(2, "professor x", bothDiagonals);

        //Se agregan los sujetos a la lista.
        sl.add(human);
        sl.add(mutant1);
        sl.add(mutant2);
        sl.add(mutant3);
        sl.add(mutant4);
        sl.add(mutant5);
        sl.add(mutant6);
    }
}
