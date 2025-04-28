package py.com.pgimenez.config.server.util;

import java.util.Objects;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class DotEnvLoader {

    private Dotenv dotenv;

    public DotEnvLoader(String profile) {
        try {
            dotenv = Dotenv.configure()
                .directory("./")
                .filename(".env." + profile)
                .load();
        } catch (DotenvException e) {
            dotenv = null;
        }
    }

    // Método para obtener una variable del archivo .env
    public String get(String key) {
        return Objects.nonNull(dotenv)
            ? dotenv.get(key)
            : null;
    }

    // Método para cargar todas las variables del archivo .env en las propiedades del sistema
    public void loadIntoSystemProperties() {
        if (Objects.nonNull(dotenv)) {
            dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue()));
        }
    }

}
