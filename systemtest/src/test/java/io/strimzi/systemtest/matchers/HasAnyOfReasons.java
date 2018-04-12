/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.systemtest.matchers;

import io.fabric8.kubernetes.api.model.Event;
import io.strimzi.systemtest.k8s.Events;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>A HasAllOfReasons is custom matcher to check the partial matching of reasons for actual events.
 * Checks at least one match in events.</p>
 */
public class HasAnyOfReasons extends BaseReasonsMatcher {

    public HasAnyOfReasons(Events... eventReasons) {
        super("at least one of", eventReasons);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean matches(Object actualValue) {
        List<String> actualReasons = eventReasons((List<Event>) actualValue);

        List<String> expectedReasons = Arrays.stream(eventReasons)
                .map(Enum::name)
                .collect(Collectors.toList());

        for (String actualReason : actualReasons) {
            if (expectedReasons.contains(actualReason)) {
                return true;
            }
        }

        return false;
    }

}
