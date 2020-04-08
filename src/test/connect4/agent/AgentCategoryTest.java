package connect4.agent;

import connect4.BVT;
import connect4.EC;
import connect4.IPT;
import connect4.LC;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({BVT.class, EC.class, IPT.class, LC.class})
@Suite.SuiteClasses({AgentTest.class, FsmTest.class})
public class AgentCategoryTest {
}