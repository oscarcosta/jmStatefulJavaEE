package com.jm.statefulapp.entities.utils;

import static com.jm.statefulapp.entities.utils.ThreadLocalEntityManager.associateWithThread;
import static com.jm.statefulapp.entities.utils.ThreadLocalEntityManager.cleanupThread;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
public class EntityManagerInjector {

    @PersistenceContext
    private EntityManager em;

    @AroundInvoke
    public Object associate(InvocationContext ic) throws Exception {
        associateWithThread(em);
        try {
            return ic.proceed();
        } finally {
            cleanupThread();
        }
    }

}
