package ru.amir.DAO.Interfaces;

import ru.amir.Entities.LogEntry;

import java.util.List;

public interface LogEntryDAO {

    public void saveLogEntry(LogEntry logEntry);

    public void deleteLogEntry(int id);

    public List<LogEntry> getLogEntries();

    public LogEntry getLogEntry(int id);

}
