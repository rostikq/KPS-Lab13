package io.github.rostikq.task02;

import io.github.rostikq.logger.EventLogger;

import java.util.*;

public class WarehouseScannerController {
    private final Queue<WarehouseCommand> commandQueue = new ArrayDeque<>();
    private final Deque<WarehouseCommand> executedHistory = new ArrayDeque<>();
    private final List<String> auditJournal = new ArrayList<>();

    public void enqueueCommand(WarehouseCommand command) {
        commandQueue.add(command);
        EventLogger.log("Scanner",
                "Отримано команду " + command.getName() + " та додано до черги (в черзі: "
                        + commandQueue.size() + ")");
    }

    public void processQueue() {
        EventLogger.log("WarehouseProcessor",
                "Початок послідовної обробки черги (команд: " + commandQueue.size() + ")");
        while (!commandQueue.isEmpty()) {
            WarehouseCommand cmd = commandQueue.poll();
            EventLogger.log("WarehouseProcessor", "Передано на виконання: " + cmd.getName());
            cmd.execute();
            executedHistory.push(cmd);
            auditJournal.add("EXEC | " + cmd.getName());
        }
        EventLogger.log("WarehouseProcessor", "Чергу оброблено повністю");
    }

    public void undoLastCommand() {
        if (executedHistory.isEmpty()) {
            EventLogger.log("WarehouseProcessor", "Немає виконаних команд для скасування");
            return;
        }
        WarehouseCommand last = executedHistory.pop();
        EventLogger.log("WarehouseProcessor", "Запит на скасування останньої команди: " + last.getName());
        last.undo();
        auditJournal.add("UNDO | " + last.getName());
    }

    public void printAuditJournal() {
        EventLogger.log("AuditJournal", "=== ЖУРНАЛ ОПЕРАЦІЙ СКЛАДУ ===");
        for (String entry : auditJournal) {
            EventLogger.log("AuditJournal", entry);
        }
    }
}
