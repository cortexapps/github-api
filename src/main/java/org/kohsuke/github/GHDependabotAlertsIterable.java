package org.kohsuke.github;

import java.util.Iterator;

import javax.annotation.Nonnull;

class GHDependabotAlertsIterable extends PagedIterable<GHDependabotAlert> {
    private final GHRepository owner;
    private final GitHubRequest request;
    private GHDependabotAlert[] result;

    GHDependabotAlertsIterable(GHRepository owner, GitHubRequest request) {
        this.owner = owner;
        this.request = request;
    }

    @Nonnull
    @Override
    public PagedIterator<GHDependabotAlert> _iterator(int pageSize) {
        return new PagedIterator<>(
                adapt(GitHubPageIterator
                        .create(owner.root().getClient(), GHDependabotAlert[].class, request, pageSize)),
                null);
    }

    protected Iterator<GHDependabotAlert[]> adapt(final Iterator<GHDependabotAlert[]> base) {
        return new Iterator<GHDependabotAlert[]>() {
            public boolean hasNext() {
                return base.hasNext();
            }

            public GHDependabotAlert[] next() {
                GHDependabotAlert[] v = base.next();
                if (result == null) {
                    result = v;
                }

                for (GHDependabotAlert alert : result) {
                    alert.wrap(owner);
                }
                return result;
            }
        };
    }
}
