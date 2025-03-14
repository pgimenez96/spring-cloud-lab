package py.com.pgimenez.config.server.util;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvLoader {

    private static final Dotenv dotenv;

    // Cargar el archivo .env al inicializar la clase
    static {
        dotenv = Dotenv.configure().load();
    }

    // Método para obtener una variable del archivo .env
    public static String get(String key) {
        return dotenv.get(key);
    }

    // Método para cargar todas las variables del archivo .env en las propiedades del sistema
    public static void loadIntoSystemProperties() {
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
    }

}
