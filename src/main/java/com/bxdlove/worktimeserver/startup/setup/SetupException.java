package com.bxdlove.worktimeserver.startup.setup;

public class SetupException extends RuntimeException {
    public SetupException(InitStep initStep, String message) {
        super("Error in step: " + initStep.name() + ": " + message);
    }
}
