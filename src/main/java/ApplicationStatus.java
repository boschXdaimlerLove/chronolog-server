import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * This application scoped bean stores the status of the application.
 * With the available flag, the general status of the application can be checked.
 *
 * @author Gregor Gottschewski
 */
@ApplicationScoped
public class ApplicationStatus {
    private boolean available;

    @PostConstruct
    public void init() {
        available = false;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
