package org.kohsuke.github;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Code sc for a repository
 *
 * <a href="https://docs.github.com/en/rest/reference/code-scanning"></a>
 */
@SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
public class GHSecretScanningAlertLocationDetails {
    private String path;

    /**
     * Instantiates a new GH secret scanning alert location details.
     */
    public GHSecretScanningAlertLocationDetails() {
    }

    /**
     * The path to the file containing the secret.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }
}
