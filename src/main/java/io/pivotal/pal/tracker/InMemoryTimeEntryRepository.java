package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> map = new HashMap<>();
    long idCounter = 1;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry;
        long id = idCounter++;

        newTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        map.put(id, newTimeEntry);
        return newTimeEntry;
    }

    public List list() {
        return map.values().stream().collect(Collectors.toList());
    }

    public TimeEntry find(long timeEntryId) {
        return map.get(timeEntryId);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (!map.containsKey(id)) {
            return null;
        }

        TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        map.put(id, updatedTimeEntry);
        return map.get(id);
    }

    public void delete(long id) {
        map.remove(id);
    }

}
