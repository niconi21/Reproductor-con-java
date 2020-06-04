/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

/**
 *
 * @author nicolas
 */
public class Desencriptar {
       private final char letrasMayusculas[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final char letrasMinusculas[] = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 's', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final char caracteresEspeciales[] = new char[]{'!', '"', '#', '$', '%', '&', '/', '(', ')', '=', '?', '¡', '+', '{', '}', '[', ']', '|', '°', '¬'};
    private char contrasenia[] = new char[0];

    public Desencriptar(String contrasenia) {
        char caracteres[] = toCaracteres(contrasenia, new char[contrasenia.length()], 0);
        caracteres = mayusculas(caracteres, new char[contrasenia.length()], 0);
        caracteres = minusculas(caracteres, new char[contrasenia.length()], 0);
        caracteres = caracteresEspeciales(caracteres, new char[contrasenia.length()],0);
        this.contrasenia = caracteres;
    }

    private char[] caracteresEspeciales(char[] caracteres) {
        char caracteresEspeciales[] = new char[caracteres.length];
        for (int i = 0; i < caracteres.length; i++) {
            for (int j = 0; j < this.caracteresEspeciales.length; j++) {
                if (caracteres[i] == this.caracteresEspeciales[j]) {
                    if (j >= 13) {
                        caracteresEspeciales[i] = this.caracteresEspeciales[j - 5];
                    }
                    if (j <= 15) {
                        caracteresEspeciales[i] = this.caracteresEspeciales[j - 5];
                    }
                } else {
                    caracteresEspeciales[i] = caracteres[i];
                }
            }
        }
        return caracteresEspeciales;
    }
    private char[] caracteresEspeciales(char caracteres[], char caracteresEspeciales[], int i) {
        if (i < caracteres.length) {
            for (int j = 0; j < this.caracteresEspeciales.length; j++) {
                if (caracteres[i] == this.caracteresEspeciales[j]) {
                    if (j <= 13) {
                        caracteresEspeciales[i] = this.caracteresEspeciales[j - 5];
                    }
                    if (j >= 15) {
                        caracteresEspeciales[i] = this.caracteresEspeciales[j + 5];
                    }
                    break;
                }else{
                    caracteresEspeciales[i]=caracteres[i];
                }
            }
            return this.caracteresEspeciales(caracteres, caracteresEspeciales, i+1);
        }
        return caracteresEspeciales;
    }

//    private char[] minusculas(char[] caracteres) {
//        char minusculas[] = new char[caracteres.length];
//        for (int i = 0; i < caracteres.length; i++) {
//            for (int j = 0; j < letrasMinusculas.length; j++) {
//                if (caracteres[i] == letrasMinusculas[j]) {
//                    if (j <= 20) {
//                        minusculas[i] = letrasMinusculas[j + 5];
//                    }
//                    if (j >= 21) {
//                        minusculas[i] = letrasMinusculas[j - 5];
//                    }
//                    break;
//                } else {
//                    minusculas[i] = caracteres[i];
//                }
//            }
//        }
//        return minusculas;
//    }

    private char[] minusculas(char caracteres[], char minusculas[], int i) {
        if (i < caracteres.length) {
            for (int j = 0; j < letrasMinusculas.length; j++) {
                if (caracteres[i] == letrasMinusculas[j]) {
                    if (j <= 20) {
                        minusculas[i] = letrasMinusculas[j - 5];
                    }
                    if (j >= 21) {
                        minusculas[i] = letrasMinusculas[j - 5];
                    }
                    break;
                } else {
                    minusculas[i] = caracteres[i];
                }
            }
            return minusculas(caracteres, minusculas, i + 1);
        }
        return minusculas;
    }

//    private char[] mayusculas(char[] caracteres) {
//        char mayusculas[] = new char[caracteres.length];
//        for (int i = 0; i < caracteres.length; i++) {
//            for (int j = 0; j < letrasMayusculas.length; j++) {
//                if (caracteres[i] == letrasMayusculas[j]) {
//                    if (j <= 20) {
//                        mayusculas[i] = letrasMayusculas[j + 5];
//                    }
//                    if (j >= 21) {
//                        mayusculas[i] = letrasMayusculas[j - 5];
//                    }
//                    break;
//                } else {
//                    mayusculas[i] = caracteres[i];
//                }
//            }
//        }
//        return mayusculas;
//    }
    private char[] mayusculas(char caracteres[], char mayusculas[], int i) {
        if (i < caracteres.length) {
            for (int j = 0; j < letrasMayusculas.length; j++) {
                if (caracteres[i] == letrasMayusculas[j]) {
                    if (j <= 20) {
                        mayusculas[i] = letrasMayusculas[j - 5];
                    }
                    if (j >= 21) {
                        mayusculas[i] = letrasMayusculas[j - 5];
                    }
                    break;
                } else {
                    mayusculas[i] = caracteres[i];
                }
            }
            return mayusculas(caracteres, mayusculas, i + 1);
        }
        return mayusculas;
    }

    private char[] toCaracteres(String cadena, char caracteres[], int i) {
        if (i < cadena.length()) {
            caracteres[i] = cadena.charAt(i);
            return toCaracteres(cadena, caracteres, i + 1);
        }
        return caracteres;
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (int i = 0; i < contrasenia.length; i++) {
            mensaje += contrasenia[i];
        }
        return mensaje;
    }

}
 

