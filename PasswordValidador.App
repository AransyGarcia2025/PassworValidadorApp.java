package PasswordValidador;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PasswordValidadorApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			List<String> contraseñas = new ArrayList<>();

			System.out.println("=== Validación Concurrente de Contraseñas ===");
			System.out.print("Ingrese la cantidad de contraseñas a validar: ");
			int cantidad;
			try {
			    cantidad = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			    System.out.println("Error: Ingrese un número válido.");
			    return;
			}

			for (int i = 0; i < cantidad; i++) {
			    System.out.print("Ingrese la contraseña #" + (i + 1) + ": ");
			    String contraseña = scanner.nextLine();
			    contraseñas.add(contraseña);
			}

			ExecutorService executor = Executors.newFixedThreadPool(contraseñas.size());

			String fechaHora = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			String nombreArchivo = "validacion_" + fechaHora + ".txt";

			PasswordValidador validador = new PasswordValidador(nombreArchivo);

			contraseñas.forEach(contraseña -> executor.execute(() -> validador.validarContraseña(contraseña)));

			executor.shutdown();
			while (!executor.isTerminated()) {
			    // Espera a que terminen todas las tareas
			}

			System.out.println("=== Validación finalizada ===");
			System.out.println("Revisa el archivo '" + nombreArchivo + "' para ver los resultados guardados.");
		}
    }
}
