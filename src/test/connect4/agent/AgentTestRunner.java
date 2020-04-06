package connect4.agent;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AgentTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AgentTest.class, FsmTest.class);

        System.out.println("=================== Test Report Of Agent===================");
        System.out.println("\033[35;4m" + "Run " + result.getRunCount() + " test cases, Ignore " +
                result.getIgnoreCount() +" test cases" + "\033[0m");

        for (Failure fail : result.getFailures()) {
            System.out.println("\033[31;4m" + fail.toString() + "\033[0m");
        }
        if (result.wasSuccessful()) {
            System.out.println("\033[32;4m" + "All tests finished successfully..." + "\033[0m");
        }
        System.out.println("======================= End =======================");
    }
}
