package org.kohsuke.github;

/**
 * What is the current state of the Dependabot Alert
 */
public enum GHDependabotAlertState {
    /**
     * Alert is open and still an active issue.
     */
    OPEN,
    /**
     * Alert has been manually dismissed by a user.
     */
    DISMISSED,
    /**
     * Issue that caused the alert has been fixed.
     */
    FIXED,
    /**
     * Alert has been automatically dismissed by Dependabot.
     */
    AUTO_DISMISSED,
}
