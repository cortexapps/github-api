package org.kohsuke.github;

import java.util.Iterator;

import javax.annotation.Nonnull;

class GHSecretScanningAlertsIterable extends PagedIterable<GHSecretScanningAlert> {
    private final GHRepository owner;
    private final GitHubRequest request;
    private GHSecretScanningAlert[] result;

    GHSecretScanningAlertsIterable(GHRepository owner, GitHubRequest request) {
        this.owner = owner;
        this.request = request;
    }

    @Nonnull
    @Override
    public PagedIterator<GHSecretScanningAlert> _iterator(int pageSize) {
        return new PagedIterator<>(
                adapt(GitHubPageIterator
                        .create(owner.root().getClient(), GHSecretScanningAlert[].class, request, pageSize)),
                null);
    }

    protected Iterator<GHSecretScanningAlert[]> adapt(final Iterator<GHSecretScanningAlert[]> base) {
        return new Iterator<GHSecretScanningAlert[]>() {
            public boolean hasNext() {
                return base.hasNext();
            }

            public GHSecretScanningAlert[] next() {
                GHSecretScanningAlert[] v = base.next();
                if (result == null) {
                    result = v;
                }

                for (GHSecretScanningAlert alert : result) {
                    alert.wrap(owner);
                }
                return result;
            }
        };
    }
}
