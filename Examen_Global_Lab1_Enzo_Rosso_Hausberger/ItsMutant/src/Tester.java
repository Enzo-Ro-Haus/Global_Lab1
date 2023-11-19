/**
 * La clase Tester se encarga de realizar pruebas de mutación en secuencias de ADN.
 * Verifica si hay secuencias idénticas de caracteres en forma horizontal, vertical y diagonal,
 * y marca dichas secuencias con el carácter 'X'.
 */
public class Tester {

    /**
     * Constructor por defecto de la clase Tester.
     */
    public Tester() {
    }

    /**
     * Realiza la prueba de mutación en una secuencia de ADN. La secuencia se analiza en forma horizontal,
     * vertical, diagonal y en la otra diagonal.
     *
     * @param dna La secuencia de ADN a analizar.
     * @return true si se encuentra más de una secuencia de caracteres idénticos en la prueba, false en caso contrario.
     */
    public Boolean isMutant(String[] dna) {
        int counter = 0;
        System.out.println("Start");
        // Realiza la prueba en forma horizontal.
        int horizontal = horizontalFinder(dna, counter);
        System.out.println("\nH: " + horizontal);
        if (horizontal > 1) {
            return true;
        }

        // Realiza la prueba en forma vertical.
        int vertical = verticalFinder(dna, horizontal);
        System.out.println("V: " + vertical);
        if (horizontal + vertical > 1) {
            return true;
        }

        // Realiza la prueba en forma diagonal.
        int diagonal = diagonal(dna, vertical);
        System.out.println("FD: " + diagonal);
        if (diagonal > 1) {
            return true;
        }

        // Realiza la prueba en la otra diagonal.
        int otherDiagonal = otherDiagonal(dna, diagonal);
        System.out.println("OD: " + otherDiagonal + "\n");
        return otherDiagonal > 1;
    }

    /**
     * Encuentra secuencias idénticas de caracteres en forma horizontal y las marca con el carácter 'X'.
     *
     * @param dna La secuencia de ADN a analizar.
     * @param j   El contador de secuencias encontradas.
     * @return El número total de secuencias encontradas.
     */
    private Integer horizontalFinder(String[] dna, int j) {
        for (int k = 0; k < dna.length; k++) {
            for (int i = 0; i < 3; i++) {
                if (dna[k].charAt(i) == dna[k].charAt(i + 1) &&
                        dna[k].charAt(i + 1) == dna[k].charAt(i + 2) &&
                        dna[k].charAt(i + 2) == dna[k].charAt(i + 3)) {
                    String letter = String.valueOf(dna[k].charAt(i));
                    dna[k] = dna[k].replaceAll(letter, "X");
                    j++;
                    break;
                }
            }
            if (j > 1) {
                break;
            }
        }
        return j;
    }

    /**
     * Encuentra secuencias idénticas de caracteres en forma vertical y las marca con el carácter 'X'.
     *
     * @param dna La secuencia de ADN a analizar.
     * @param j   El contador de secuencias encontradas.
     * @return El número total de secuencias encontradas.
     */
    private Integer verticalFinder(String[] dna, int j) {
        for (int i = 0; i < dna.length; i++) {
            for (int k = 0; k < 3; k++) {
                if (dna[k].charAt(i) == dna[k + 1].charAt(i) &&
                        dna[k + 1].charAt(i) == dna[k + 2].charAt(i) &&
                        dna[k + 2].charAt(i) == dna[k + 3].charAt(i)) {
                    for (int l = 0; l < 4; l++) {
                        dna[k + l] = dna[k + l].substring(0, i) + "X" + dna[k + l].substring(i + 1);
                    }
                    j++;
                    break;
                }
                if (j > 1) {
                    break;
                }
            }
        }
        return j;
    }

    /**
     * Encuentra secuencias idénticas de caracteres en forma diagonal y las marca con el carácter 'X'.
     *
     * @param dna La secuencia de ADN a analizar.
     * @param j   El contador de secuencias encontradas.
     * @return El número total de secuencias encontradas.
     */
    private Integer diagonal(String[] dna, int j) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (dna[i].charAt(k) == dna[i + 1].charAt(k + 1) &&
                        dna[i + 1].charAt(k + 1) == dna[i + 2].charAt(k + 2) &&
                        dna[i + 2].charAt(k + 2) == dna[i + 3].charAt(k + 3)) {
                    for (int l = 0; l < 4; l++) {
                        dna[i + l] = dna[i + l].substring(0, k + l) + "X" + dna[i + l].substring(k + l + 1);
                    }
                    j++;
                    break;
                }
            }
            if (j > 1) {
                break;
            }
        }
        return j;
    }

    /**
     * Encuentra secuencias idénticas de caracteres en la otra diagonal y las marca con el carácter 'X'.
     *
     * @param dna La secuencia de ADN a analizar.
     * @param j   El contador de secuencias encontradas.
     * @return El número total de secuencias encontradas.
     */
    private Integer otherDiagonal(String[] dna, int j) {
        for (int i = 0; i < 3; i++) {
            for (int k = dna[i].length() - 1; k >= 3; k--) {
                if (dna[i].charAt(k) == dna[i + 1].charAt(k - 1) &&
                        dna[i + 1].charAt(k - 1) == dna[i + 2].charAt(k - 2) &&
                        dna[i + 2].charAt(k - 2) == dna[i + 3].charAt(k - 3)) {
                    for (int l = 0; l < 4; l++) {
                        dna[i + l] = dna[i + l].substring(0, k - l) + "X" + dna[i + l].substring(k - l + 1);
                    }
                    j++;
                }
            }
            if (j > 1) {
                break;
            }
        }

        return j;
    }
}
