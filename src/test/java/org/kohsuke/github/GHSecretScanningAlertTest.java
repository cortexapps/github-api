package org.kohsuke.github;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

/**
 * <p>
 * Note : As the code scanning alerts cannot be tailored as part of test setup, lot of the test cases are dependent on
 * manual setup of the mock repo. Assertions and verifications will often simply check that the values are non-null
 * rather than depending on hard-coded values, to prevent making the tests flimsy
 * </p>
 */
public class GHSecretScanningAlertTest extends AbstractGitHubWireMockTest {
    private static final String REPO_NAME = "Pixi";
    private GHRepository repo;

    /**
     * Gets the mock repo
     *
     * @throws Exception
     *             the exception
     */
    @Before
    public void setUp() throws Exception {
        repo = gitHub.getRepository("cortextests" + "/" + "secret-scanning");
    }

    /**
     * Test list secret scanning alert payload
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testListSecretScanningAlerts() throws Exception {
        // Arrange

        // Act
        List<GHSecretScanningAlert> alerts = repo.listSecretScanningAlerts()._iterator(2).nextPage();

        // Assert
        assertThat(alerts.size(), equalTo(2));

        GHSecretScanningAlert alert1 = alerts.get(0);
        assertThat(alert1.getNumber(), equalTo(2L));
        assertThat(alert1.getState(), equalTo(GHSecretScanningAlertState.OPEN));
        assertThat(alert1.getSecretType(), equalTo("npm_access_token"));
        assertThat(alert1.getSecret(), equalTo("secret1"));
        assertThat(alert1.isPushProtectionBypassed(), equalTo(false));
        assertThat(alert1.getResolvedBy(), nullValue());
        assertThat(alert1.getResolvedAt(), nullValue());

        List<GHSecretScanningAlertLocation> locations = alert1.getLocations();
        assertThat(locations.size(), equalTo(1));
        assertThat(locations.get(0).getType(), equalTo("commit"));
        assertThat(locations.get(0).getDetails().getPath(), equalTo("secrets/secrets1.env"));

        GHSecretScanningAlert alert2 = alerts.get(1);
        assertThat(alert2.getNumber(), equalTo(1L));
        assertThat(alert2.getState(), equalTo(GHSecretScanningAlertState.OPEN));
        assertThat(alert2.getSecretType(), equalTo("stripe_test_secret_key"));
        assertThat(alert2.getSecret(), equalTo("secret2"));
        assertThat(alert2.isPushProtectionBypassed(), equalTo(true));
        assertThat(alert2.getPushProtectionBypassedBy().getLogin(), equalTo("lukbla"));
        assertThat(alert2.getPushProtectionBypassedAt(), equalTo(GitHubClient.parseDate("2025-05-05T15:32:05Z")));
        assertThat(alert2.getResolvedBy(), nullValue());
        assertThat(alert2.getResolvedAt(), nullValue());

        List<GHSecretScanningAlertLocation> locations2 = alert2.getLocations();
        assertThat(locations2.size(), equalTo(1));
        assertThat(locations2.get(0).getType(), equalTo("commit"));
        assertThat(locations2.get(0).getDetails().getPath(), equalTo("secrets.env"));
    }

}
