package io.github.rostikq.task02;

public interface WarehouseCommand {
    String getName();
    void execute();
    void undo();
}
