package startup.setup;

public interface InitStep {
    String name();
    String description();
    void execute();
}
