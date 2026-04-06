package org.kohsuke.github;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 * Dependabot alert for a repository with a dependency affected by a security vulnerability.
 *
 * <a href="https://docs.github.com/en/rest/dependabot/alerts">Dependabot alerts API</a>
 */
@SuppressFBWarnings(value = { "UUF_UNUSED_FIELD" }, justification = "JSON API")
public class GHDependabotAlert extends GHObject {
    @JsonIgnore
    private GHRepository owner;
    private long number;
    private String html_url;
    private GHDependabotAlertState state;
    private Dependency dependency;
    private SecurityAdvisory security_advisory;
    private SecurityVulnerability security_vulnerability;
    private String created_at;
    private String updated_at;
    private String dismissed_at;
    private GHUser dismissed_by;
    private String dismissed_reason;
    private String dismissed_comment;
    private String fixed_at;
    private String auto_dismissed_at;

    GHDependabotAlert wrap(GHRepository owner) {
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
     * State of alert.
     *
     * @return the state
     */
    public GHDependabotAlertState getState() {
        return state;
    }

    /**
     * Dependency that is affected by the vulnerability.
     *
     * @return the dependency
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public Dependency getDependency() {
        return dependency;
    }

    /**
     * Security advisory associated with the alert.
     *
     * @return the security advisory
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public SecurityAdvisory getSecurityAdvisory() {
        return security_advisory;
    }

    /**
     * Security vulnerability associated with the alert.
     *
     * @return the security vulnerability
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public SecurityVulnerability getSecurityVulnerability() {
        return security_vulnerability;
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
     * Time when alert was dismissed. Non-null when {@link #getState()} is DISMISSED.
     *
     * @return the time
     */
    public Date getDismissedAt() {
        return GitHubClient.parseDate(dismissed_at);
    }

    /**
     * User that dismissed the alert. Non-null when {@link #getState()} is DISMISSED.
     *
     * @return the user
     */
    @SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "Expected behavior")
    public GHUser getDismissedBy() {
        return dismissed_by;
    }

    /**
     * Reason for dismissal. Can be 'fix_started', 'inaccurate', 'no_bandwidth', 'not_used', 'tolerable_risk'.
     *
     * @return the reason
     */
    public String getDismissedReason() {
        return dismissed_reason;
    }

    /**
     * Optional comment associated with the dismissal.
     *
     * @return the comment
     */
    public String getDismissedComment() {
        return dismissed_comment;
    }

    /**
     * Dependency affected by the vulnerability.
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class Dependency {
        @JsonProperty("package")
        private Package pkg;
        private String manifest_path;
        private String scope;

        /**
         * The package affected by the vulnerability.
         *
         * @return the package
         */
        public Package getPackage() {
            return pkg;
        }

        /**
         * Path to the manifest file that declares the dependency.
         *
         * @return the manifest path
         */
        public String getManifestPath() {
            return manifest_path;
        }

        /**
         * Scope of the dependency (runtime or development).
         *
         * @return the scope
         */
        public String getScope() {
            return scope;
        }
    }

    /**
     * A package affected by a vulnerability.
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class Package {
        private String ecosystem;
        private String name;

        /**
         * The package ecosystem (e.g., npm, pip, maven).
         *
         * @return the ecosystem
         */
        public String getEcosystem() {
            return ecosystem;
        }

        /**
         * The package name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Security advisory associated with a Dependabot alert.
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class SecurityAdvisory {
        private String ghsa_id;
        private String cve_id;
        private String summary;
        private String description;
        private String severity;

        /**
         * GitHub Security Advisory ID.
         *
         * @return the GHSA ID
         */
        public String getGhsaId() {
            return ghsa_id;
        }

        /**
         * CVE ID for the advisory, if available.
         *
         * @return the CVE ID
         */
        public String getCveId() {
            return cve_id;
        }

        /**
         * Short summary of the advisory.
         *
         * @return the summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         * Full description of the advisory.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Severity of the advisory (critical, high, medium, low).
         *
         * @return the severity
         */
        public String getSeverity() {
            return severity;
        }
    }

    /**
     * Security vulnerability details.
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class SecurityVulnerability {
        @JsonProperty("package")
        private Package pkg;
        private String severity;
        private String vulnerable_version_range;
        private PatchedVersion first_patched_version;

        /**
         * The package affected by this vulnerability.
         *
         * @return the package
         */
        public Package getPackage() {
            return pkg;
        }

        /**
         * Severity of the vulnerability (critical, high, medium, low).
         *
         * @return the severity
         */
        public String getSeverity() {
            return severity;
        }

        /**
         * Version range affected by the vulnerability.
         *
         * @return the vulnerable version range
         */
        public String getVulnerableVersionRange() {
            return vulnerable_version_range;
        }

        /**
         * First patched version that fixes the vulnerability.
         *
         * @return the first patched version
         */
        public PatchedVersion getFirstPatchedVersion() {
            return first_patched_version;
        }
    }

    /**
     * A patched version identifier.
     */
    @SuppressFBWarnings(value = { "UWF_UNWRITTEN_FIELD" }, justification = "JSON API")
    public static class PatchedVersion {
        private String identifier;

        /**
         * The version identifier.
         *
         * @return the identifier
         */
        public String getIdentifier() {
            return identifier;
        }
    }
}
