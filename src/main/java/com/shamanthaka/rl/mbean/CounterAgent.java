package com.shamanthaka.rl.mbean;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class CounterAgent {

    private MBeanServer mBeanServer;
    private Counter counter;

    private void register() throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        counter = new Counter();
        ObjectName objectName = new ObjectName("counter:bean=Counter, type=service");
        mBeanServer.registerMBean(counter, objectName);
    }

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        CounterAgent counterAgent = new CounterAgent();
        counterAgent.register();

       /* String flag = "";
        Scanner scanner = new Scanner(System.in);
        //to graceful shut down inter "exit"
        System.out.println("to graceful shut down inter exit ");
        flag = scanner.nextLine();
        if("exit".equalsIgnoreCase(flag)){
            System.exit(0);
        }*/

        //to run continuously
        Semaphore semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
