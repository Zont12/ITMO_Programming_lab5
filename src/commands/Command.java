package commands;

import java.util.Objects;

public abstract class Command {
    private final String name;

    private final String arguments;

    private final String description;

    public Command(String name, String arguments, String description) {
        this.name = name;
        this.arguments = arguments;
        this.description = description;
    }

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
        this.arguments = "";
    }

    public abstract void execute() throws Exception;

    public abstract void initCommandArgs(String args[]) throws Exception;


    @Override
    public String toString() {
        String res = this.name;
        if (!this.arguments.isEmpty()) res += " " + this.arguments;
        res += ": " + this.description;
        return res;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Command command = (Command) obj;
        return Objects.equals(getName(), command.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
