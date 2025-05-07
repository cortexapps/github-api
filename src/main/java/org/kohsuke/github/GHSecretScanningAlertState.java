package org.kohsuke.github;

/**
 * What is the current state of the Secret Scanning Alert
 */
public enum GHSecretScanningAlertState {
    /**
     * Alert is open and still an active issue.
     */
    OPEN,
    /**
     * Issue that has caused the alert has been addressed.
     */
    RESOLVED,
}
