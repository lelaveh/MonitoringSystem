package ru.amir.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.amir.DAO.Interfaces.LogEntryDAO;
import ru.amir.Entities.LogEntry;
import ru.amir.Services.Interfaces.LogEntryService;

import java.util.List;

@Service
public class LogEntryServiceImpl implements LogEntryService {

    @Autowired

    private LogEntryDAO logEntryDAO;

    @Override
    @Transactional
    public void saveLogEntry(LogEntry logEntry) {
        logEntryDAO.saveLogEntry(logEntry);
    }

    @Override
    @Transactional
    public void deleteLogEntry(int id) {
        logEntryDAO.deleteLogEntry(id);
    }

    @Override
    @Transactional
    public List<LogEntry> getLogEntries() {
        return logEntryDAO.getLogEntries();
    }

    @Override
    @Transactional
    public LogEntry getLogEntry(int id) {
        return logEntryDAO.getLogEntry(id);
    }
}
