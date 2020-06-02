package ru.amir.Services.Interfaces;

import ru.amir.Entities.LogEntry;

import java.util.List;

public interface LogEntryService {

    public void saveLogEntry(LogEntry logEntry);

    public void deleteLogEntry(int id);

    public List<LogEntry> getLogEntries();

    public LogEntry getLogEntry(int id);

}
