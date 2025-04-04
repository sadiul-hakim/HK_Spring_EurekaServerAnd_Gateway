package xyz.sadiulhakim.utility;

import org.springframework.scheduling.annotation.Scheduled;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.ThreadMXBean;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component
public class Watcher {

    private final DateTimeFormatter DATE_TIME_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a z");

    @Scheduled(fixedRate = 100_000)
    private void getMemoryUsage() {
        String dateTime = ZonedDateTime.now().format(DATE_TIME_FORMATER);
        System.out.println("*-------------------------" + dateTime + "------------------------------*");
        System.out.println("Heap: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        System.out.println("NonHeap: " + ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
        List<MemoryPoolMXBean> beans = ManagementFactory.getMemoryPoolMXBeans();

        System.out.println("************** MEMORY USAGE *********************");
        for (MemoryPoolMXBean bean : beans) {
            System.out.println("Bean Name: " + bean.getName() + "\nUsage: " + bean.getUsage());
        }

        System.out.println("************** GARBAGE COLLECTOR *********************");
        for (GarbageCollectorMXBean bean : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println("Bean Name: " + bean.getName() + "\nCollection Count: " + bean.getCollectionCount() + "\nCollection Time: " + bean.getCollectionTime());
        }

        System.out.println("************** THREAD *********************");
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        System.out.println("Thread Count: " + threadBean.getThreadCount() + " \nThread CPU TIME:" + threadBean.getCurrentThreadCpuTime());
        System.out.println("Active Threads: " + Thread.activeCount());
        System.out.println("+---------------------------------+-----------------------------------+");
    }
}
