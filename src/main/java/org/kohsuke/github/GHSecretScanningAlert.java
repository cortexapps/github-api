package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    private String locations_url;
    private GHSecretScanningAlertState state;
    private String resolution;
    private String resolved_at;
    private GHUser resolved_by;
    private String secret_type;
    private String secret_type_display_name;
    private String secret;

    private Boolean push_protection_bypassed;
    private GHUser push_protection_bypassed_by;
    private String push_protection_bypassed_at;

    private String created_at;
    private String updated_at;

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

    @Override
    public URL getHtmlUrl() throws IOException {
        return GitHubClient.parseURL(html_url);
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
     * Display name for tyype of secret that was detected
     *
     * @return the secret type display name
     */
    public String getSecretTypeDisplayName() {
        return secret_type_display_name;
    }

    /**
     * Secret value that was detected
     *
     * @return the secret value
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Whether push protection was bypassed for this alert
     *
     * @return true if push protection was bypassed, false otherwise
     */
    public Boolean isPushProtectionBypassed() {
        return push_protection_bypassed;
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

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return GitHubClient.parseDate(created_at);
    }

    /**
     * Gets updated at.
     *
     * @return the updated at
     */
    public Date getUpdatedAt() {
        return GitHubClient.parseDate(updated_at);
    }

    /**
     * Gets locations url.
     *
     * @return the locations url
     */
    public String getLocationsUrl() {
        return locations_url;
    }

    /**
     * Gets locations.
     *
     * @return the locations array
     * @throws IOException
     *             the io exception
     */
    public List<GHSecretScanningAlertLocation> getLocations() throws IOException {
        return Arrays.asList(
                root().createRequest().withUrlPath(getLocationsUrl()).fetch(GHSecretScanningAlertLocation[].class));
    }

}
