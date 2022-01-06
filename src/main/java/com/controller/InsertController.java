package com.controller;

import com.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import java.util.Calendar;

@RestController
@RequestMapping("insert")
public class InsertController {
    Logger log = LoggerFactory.getLogger(InsertController.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("add")
    public Object addBatch() {
        log.info("Time Start-> " + System.currentTimeMillis());
        for (int i = 0; i < 100; ++i)
            doTask();
        return Calendar.getInstance().getTimeInMillis();
    }

    public void doTask() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        taskExecutor.execute(() -> {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin(); // begin transaction

            //begin insert
            for (int j = 1; j < 100000; ++j) {
                session.save(new User(null, "sajdasfasssfajfn asjdafaf", "sajdasfasssfajfn asjdafaf", "sajdasfasssfajfn asjdafaf"));
                if (j % 10000 == 0) {
                    session.flush();
                    session.clear();

                }
            }

            session.getTransaction().commit(); // commit tran
            session.close(); // close
            log.info("end insert 100000 records");
            log.info("Time end->" + System.currentTimeMillis());
        });
    }
}
