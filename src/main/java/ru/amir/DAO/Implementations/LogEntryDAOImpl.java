package ru.amir.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.amir.DAO.Interfaces.LogEntryDAO;
import ru.amir.Entities.LogEntry;

import java.util.List;

@Repository
public class LogEntryDAOImpl implements LogEntryDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void saveLogEntry(LogEntry logEntry) {
        sessionFactory.getCurrentSession().saveOrUpdate(logEntry);
    }

    @Override
    public void deleteLogEntry(int id) {
        LogEntry logEntry = getLogEntry(id);
        sessionFactory.getCurrentSession().delete(logEntry);
    }

    @Override
    public List<LogEntry> getLogEntries() {
        List<LogEntry> logEntries = sessionFactory.getCurrentSession().createQuery("from log_entry order by id", LogEntry.class).getResultList();
        return logEntries;
    }

    @Override
    public LogEntry getLogEntry(int id) {
        return sessionFactory.getCurrentSession().get(LogEntry.class, id);
    }
}
