package connect4.board;

import connect4.BVT;
import connect4.EC;
import connect4.IPT;
import connect4.LC;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory({BVT.class, EC.class})
@Categories.ExcludeCategory({IPT.class, LC.class})
@Suite.SuiteClasses(BoardTest.class)
public class BoardCategoryTest {

}
