package no.bekk.fagdag.aktorer;

import com.google.common.eventbus.Subscribe;

/**
 * @author Eirik Wang - eirik.wang@bekk.no
 */
public class LogConsumer {
    @Subscribe public void takeAll(Object o) {
        System.out.println(o.toString());
    }
}
