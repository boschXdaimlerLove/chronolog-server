package com.bxdlove.worktimeserver.startup.setup;

/**
 * Interface for application initialization steps.
 * An initialization step contains the following information:
 *
 * <ul>
 *     <li>name for debug</li>
 *     <li>description for app admin</li>
 *     <li>task to execute</li>
 *     <li>is satisfied (status)</li>
 * </ul>
 *
 * @author Gregor Gottschewski
 */
public interface InitStep {
    String name();
    String description();
    void execute();
    boolean isSatisfied();
}
