package connect4.agent;

import connect4.LC;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
//@Categories.IncludeCategory(BVT.class)
//@Categories.IncludeCategory(EC.class)
@Categories.IncludeCategory(LC.class)
//@Categories.IncludeCategory(IPT.class)
@Suite.SuiteClasses(AgentTest.class)
public class AgentCategoryTest {
}