/*
 * Copyright 2018, Strimzi authors.
 * License: Apache License 2.0 (see the file LICENSE or http://apache.org/licenses/LICENSE-2.0.html).
 */
package io.strimzi.systemtest.matchers;

import io.fabric8.kubernetes.api.model.Event;
import io.strimzi.systemtest.k8s.Events;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseReasonsMatcher extends BaseMatcher<List<Event>> {

    protected final Events[] eventReasons;
    private final String matcherDescription;

    public BaseReasonsMatcher(String matcherDescription, Events... eventReasons) {
        this.matcherDescription = matcherDescription;
        this.eventReasons = eventReasons;
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText("was ").appendValue(eventReasons((List<Event>) item));
    }

    @Override
    public void describeTo(Description description) {
        description.appendValueList("The resource should contain " + this.matcherDescription + " the following events {", ", ", "}. ", eventReasons);
    }

    protected List<String> eventReasons(List<Event> item) {
        return item.stream()
                    .map(Event::getReason)
                    .collect(Collectors.toList());
    }
}
