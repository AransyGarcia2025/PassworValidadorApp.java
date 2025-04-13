package PasswordValidador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.util.concurrent.locks.ReentrantLock;

public class PasswordValidador {
    private final String archivo;
    private final ReentrantLock lock = new ReentrantLock();

    public PasswordValidador(String archivo) {
        this.archivo = archivo;
    }

    public void validarContraseña(String contraseña) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Contraseña: ").append(contraseña).append("\n");

        boolean esValida = true;

        if (contraseña.length() >= 8) {
            resultado.append("- ✅ Longitud mínima cumplida.\n");
        } else {
            resultado.append("- ❌ Longitud mínima de 8 caracteres no cumplida.\n");
            esValida = false;
        }

        if (Pattern.compile("[^a-zA-Z0-9]").matcher(contraseña).find()) {
            resultado.append("- ✅ Contiene carácter especial.\n");
        } else {
            resultado.append("- ❌ Falta un carácter especial.\n");
            esValida = false;
        }

        long mayusculas = contraseña.chars().filter(Character::isUpperCase).count();
        if (mayusculas >= 2) {
            resultado.append("- ✅ Contiene suficientes letras mayúsculas.\n");
        } else {
            resultado.append("- ❌ Faltan letras mayúsculas (mínimo 2).\n");
            esValida = false;
        }

        long minusculas = contraseña.chars().filter(Character::isLowerCase).count();
        if (minusculas >= 3) {
            resultado.append("- ✅ Contiene suficientes letras minúsculas.\n");
        } else {
            resultado.append("- ❌ Faltan letras minúsculas (mínimo 3).\n");
            esValida = false;
        }

        if (Pattern.compile("\\d").matcher(contraseña).find()) {
            resultado.append("- ✅ Contiene al menos un número.\n");
        } else {
            resultado.append("- ❌ Falta un número.\n");
            esValida = false;
        }

        resultado.append(esValida ? "=== Contraseña VÁLIDA ===\n" : "=== Contraseña INVÁLIDA ===\n");
        resultado.append("---------------------------------------------------\n");

        escribirEnArchivo(resultado.toString());

        System.out.print(resultado);
    }

    private void escribirEnArchivo(String texto) {
        lock.lock();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(texto);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
