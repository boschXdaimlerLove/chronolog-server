package startup;

import api.Status;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import startup.setup.InitStep;
import startup.setup.SetupException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static io.ApplicationDirectories.APPLICATION_HOME;

@ApplicationScoped
public class ApplicationStartup {

    @Inject
    private Status status;

    private static final List<InitStep> initSteps = List.of(
            new InitStep() {
                @Inject
                private Status status;

                @Override
                public String name() {
                    return "Directory creation";
                }

                @Override
                public String description() {
                    return "Creates application directories";
                }

                @Override
                public void execute() {
                    try {
                        Files.createDirectory(APPLICATION_HOME.getPath());
                    } catch (IOException e) {
                        status.addException(new SetupException(this, e.getMessage()));
                    }
                }
            }
    );

    @PostConstruct
    public void init() {
        for (InitStep initStep : initSteps) {
            initStep.execute();
        }

        if (status.getExceptionCount() == 0) {
            status.setRunning(true);
        }
    }
}
