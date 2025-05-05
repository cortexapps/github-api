package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Secret scanning alert for a repository
 *
 * <a href="https://docs.github.com/en/rest/secret-scanning/secret-scanning"></a>
 */
@SuppressFBWarnings(value = { "UUF_UNUSED_FIELD" }, justification = "JSON API")
public class GHSecretScanningAlert extends GHObject {
    @JsonIgnore
    private GHRepository owner;
    private long number;
    private String html_url;
    private GHSecretScanningAlertState state;
    private String resolution;
    private String resolved_at;
    private GHUser resolved_by;
    private String secret_type;
    private Secret secret;
    private String push_protection_bypassed;
    private GHUser push_protection_bypassed_by;
    private String push_protection_bypassed_at;

    GHSecretScanningAlert wrap(GHRepository owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Id/number of the alert.
     *
     * @return the id/number
     * @see #getId()
     */
    public long getNumber() {
        return number;
    }

    /**
     * Id/number of the alert.
     *
     * @return the id/number
     * @see #getNumber()
     */
    @Override
    public long getId() {
        return getNumber();
    }

    /**
     * State of alert
     *
     * @return the state
     */
    public GHSecretScanningAlertState getState() {
        return state;
    }

    /**
     * Resolution of the alert. Can be 'false_positive', 'wont_fix', 'revoked', 'used_in_tests', or null.
     *
     * @return the resolution
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Time when alert was resolved. Non-null when {@link #getState()} is <i>Resolved</i>
     *
     * @return the time
     */
    public Date getResolvedAt() {
        return GitHubClient.parseDate(resolved_at);
    }

    /**
     * User that has resolved the alert. Non-null when {@link #getState()} is <i>Resolved</i>
     *
     * <p>
     * Note: User object returned by secret scanning GitHub API does not contain all fields. Use with caution
     * </p>
     *
     * @return the user
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public GHUser getResolvedBy() {
        return resolved_by;
    }

    /**
     * Type of secret that was detected
     *
     * @return the secret type
     */
    public String getSecretType() {
        return secret_type;
    }

    /**
     * Secret that was detected
     *
     * @return the secret
     */
    public Secret getSecret() {
        return secret;
    }

    /**
     * Whether push protection was bypassed for this alert
     *
     * @return true if push protection was bypassed, false otherwise
     */
    public boolean isPushProtectionBypassed() {
        return push_protection_bypassed != null && !push_protection_bypassed.isEmpty();
    }

    /**
     * User that bypassed push protection. Non-null when {@link #isPushProtectionBypassed()} is true
     *
     * @return the user
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public GHUser getPushProtectionBypassedBy() {
        return push_protection_bypassed_by;
    }

    /**
     * Time when push protection was bypassed. Non-null when {@link #isPushProtectionBypassed()} is true
     *
     * @return the time
     */
    public Date getPushProtectionBypassedAt() {
        return GitHubClient.parseDate(push_protection_bypassed_at);
    }

    @Override
    public URL getHtmlUrl() throws IOException {
        return GitHubClient.parseURL(html_url);
    }

    /**
     * Secret details
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class Secret {
        private String name;
        private String type;
        private String value;

        /**
         * Name of the secret
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Type of the secret
         *
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * Value of the secret
         *
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }
}
