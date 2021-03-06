## Junit的用法
```java
public class Calculate {
	public int add(int a,int b) {
		return a + b;
	}
	public int subtract(int a, int b) {
		return a - b;
	}
	public int multiply(int a,int b) {
		return a * b;
	}
	public int divide(int a ,int b) {
		return a / b;
	}
}

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {
	/* 1.测试方法上必须使用@Test进行修饰
	 * 2.测试方法必须使用public void 进行修饰，不能带任何的参数
	 * 3.新建一个源代码目录来存放我们的测试代码
	 * 4.测试类的包应该和被测试类保持一致
	 * 5.测试单元中的每个方法必须可以独立测试，测试方法间不能有任何的依赖
	 * 6.测试类使用Test作为类名的后缀（不是必须）
	 * 7.测试方法使用test作为方法名的前缀（不是必须）*/
	@Test
	public void testAdd() {
		assertEquals(6, new Calculate().add(3,3));
	}
	
	@Test
	public void testSubtract() {
		assertEquals(3, new Calculate().subtract(5,2));
	}
	
	@Test
	public void testMultiply() {
		assertEquals(4, new Calculate().multiply(2, 2));
	}
	
	@Test
	public void testDivide() {
		assertEquals(3, new Calculate().divide(6, 2));
	}
}
	/*
	 * @Test:将一个普通的方法修饰成为一个测试方法
	 * 		@Test(expected=XX.class)
	 * 		@Test(timeout=毫秒 )
	 * @BeforeClass：它会在所有的方法运行前被执行，static修饰
	 * @AfterClass:它会在所有的方法运行结束后被执行，static修饰
	 * @Before：会在每一个测试方法被运行前执行一次
	 * @After：会在每一个测试方法运行后被执行一次
	 * @Ignore:所修饰的测试方法会被测试运行器忽略
	 * @RunWith:可以更改测试运行器 org.junit.runner.Runner
	 */

import static org.junit.Assert.*;
import org.junit.Test;
public class ErrorAndFailureTest {
	/* 1.Failure一般由单元测试使用的断言方法判断失败所引起的，这经表示 测试点发现了问题
	 * ，就是说程序输出的结果和我们预期的不一样。
	 * 2.error是由代码异常引起的，它可以产生于测试代码本身的错误，也可以是被测试代码中的
	 * 一个隐藏的bug
	 * 3.测试用例不是用来证明你是对的，而是用来证明你没有错。*/
	@Test
	public void testAdd() {
		assertEquals(5, new Calculate().add(3,3));
	}

	@Test
	public void testDivide() {
		assertEquals(3, new Calculate().divide(6, 0));
	}
}

	/*
	 * 1.@BeforeClass修饰的方法会在所有方法被调用前被执行，
	 * 而且该方法是静态的，所以当测试类被加载后接着就会运行它，
	 * 而且在内存中它只会存在一份实例，它比较适合加载配置文件。
	 * 2.@AfterClass所修饰的方法通常用来对资源的清理，如关闭数据库的连接
	 * 3.@Before和@After会在每个测试方法的前后各执行一次。
	 */


	/*
	 * 1.更改默认的测试运行器为RunWith(Parameterized.class)
	 * 2.声明变量来存放预期值 和结果值
	 * 3.声明一个返回值 为Collection的公共静态方法，并使用@Parameters进行修饰
	 * 4.为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值
	 */




import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TaskTest1.class,TaskTest2.class,TaskTest3.class})
public class SuiteTest {
	/*
	 * 1.测试套件就是组织测试类一起运行的
	 * 写一个作为测试套件的入口类，这个类里不包含其他的方法
	 * 更改测试运行器Suite.class
	 * 将要测试的类作为数组传入到Suite.SuiteClasses（{}）
	 */
}


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterTest {
	/*
	 * 1.更改默认的测试运行器为RunWith(Parameterized.class)
	 * 2.声明变量来存放预期值 和结果值
	 * 3.声明一个返回值 为Collection的公共静态方法，并使用@Parameters进行修饰
	 * 4.为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值
	 */
	int expected =0;
	int input1 = 0;
	int input2 = 0;
	
	@Parameters
	public static Collection<Object[]> t() {
		return Arrays.asList(new Object[][]{
				{3,1,2},
				{4,2,2}
		}) ;
	}
	
	public ParameterTest(int expected,int input1,int input2) {
		this.expected = expected;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Test
	public void testAdd() {
		assertEquals(expected, new Calculate().add(input1, input2));
	}

}
```
