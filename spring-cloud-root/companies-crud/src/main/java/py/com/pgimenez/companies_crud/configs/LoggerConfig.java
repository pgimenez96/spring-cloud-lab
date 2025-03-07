package py.com.pgimenez.companies_crud.configs;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static org.apache.logging.log4j.LogManager.ROOT_LOGGER_NAME;

@Configuration
public class LoggerConfig {

    @Autowired
    public LoggerConfig(@Value("${log.level:}") String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.toLevel(logLevel, Level.INFO));

        Logger mcsLogger = loggerContext.getLogger("py.com.pgimenez");
        mcsLogger.setLevel(Level.toLevel(logLevel, Level.INFO));
    }

}
