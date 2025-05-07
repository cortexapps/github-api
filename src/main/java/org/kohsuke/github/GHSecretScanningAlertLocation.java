package org.kohsuke.github;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Code scanning alert for a repository
 *
 * <a href="https://docs.github.com/en/rest/reference/code-scanning"></a>
 */
@SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
public class GHSecretScanningAlertLocation {
    private String type;
    private GHSecretScanningAlertLocationDetails details;

    /**
     * Instantiates a new GH secret scanning alert location.
     */
    public GHSecretScanningAlertLocation() {
    }

    /**
     * The type of location.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * The details of the location.
     *
     * @return the details
     */
    public GHSecretScanningAlertLocationDetails getDetails() {
        return details;
    }

}
