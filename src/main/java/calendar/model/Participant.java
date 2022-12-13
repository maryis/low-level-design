package calendar.model;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected String name;
    public abstract boolean isTeam();
    public abstract List<User> getMembers();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == null) return true;
        if (o == null || getClass()!= o.getClass()) return false;
        Participant other = (Participant)o;
        return this.getName().equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
