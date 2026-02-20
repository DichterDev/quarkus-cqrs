package at.dichterdev.cqrs.domain.common;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainRoot {
    private final List<DomainEvent> events = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        this.events.add(event);
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> cleared = new ArrayList<>(this.events);
        this.events.clear();

        return cleared;
    }
}
