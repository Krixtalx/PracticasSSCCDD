package com.uja.ssccdd.sesion3.Ejercicio3;

/**
 *
 * @author José Antonio
 */
public class FileMock {

    private String content[];
    private int index;

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder buffer = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                buffer.append((char) indice);
            }
            content[i] = buffer.toString();
        }
        index = 0;
    }

    /**
     * Returns true if the file has more lines to process or false if not
     *
     * @return true if the file has more lines to process or false if not
     */
    public boolean hasMoreLines() {
        return index < content.length;
    }

    /**
     * Returns the next line of the simulate file or null if there aren't more
     * lines
     *
     * @return
     */
    public String getLine() {
        if (this.hasMoreLines()) {
            System.out.println("Mock: " + (content.length - index));
            return content[index++];
        }
        return null;
    }

}
