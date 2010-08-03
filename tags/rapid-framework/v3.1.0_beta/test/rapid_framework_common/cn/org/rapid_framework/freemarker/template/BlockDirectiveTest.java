package cn.org.rapid_framework.freemarker.template;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import cn.org.rapid_framework.freemarker.FreemarkerTemplateException;
import cn.org.rapid_framework.freemarker.FreemarkerTemplateProcessor;
import freemarker.template.Configuration;

public class BlockDirectiveTest {
	Configuration conf = new Configuration();
	FreemarkerTemplateProcessor processor = new FreemarkerTemplateProcessor();
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{
		processor.setConfiguration(conf);
		conf.setSharedVariable("block", new BlockDirective());
		conf.setSharedVariable("override", new OverrideDirective());
		conf.setSharedVariable("extends", new ExtendsDirective());
		File dir = ResourceUtils.getFile("classpath:fortest_freemarker");
		conf.setDirectoryForTemplateLoading(dir);
		System.out.println(dir.getAbsolutePath());
	}
	
	@Test
	public void testOverride() throws FileNotFoundException, IOException {

		System.out.println(processor.processTemplate("child.flt",new HashMap()));
		assertEquals("<html><head>base_head_content</head><body>base_body_content</body></html>",processTemplate("base.flt"));
		assertEquals("<html><head>base_head_content</head><body><divclass='content'>PoweredByrapid-framework</div></body></html>",processTemplate("child.flt").trim());
		assertEquals("<html><head>grandchild_head_content</head><body>grandchild_body_content</body></html>",processTemplate("grandchild.flt").trim());
		assertEquals("<html><head>base_head_content</head><body>base_body_content</body></html>",processTemplate("base-ext.flt"));
	}

	private String processTemplate(String templateName) {
		return processor.processTemplate(templateName, new HashMap()).replaceAll("\\s", "");
	}
	
	@Test(expected=FreemarkerTemplateException.class)
	public void testDirective() {
		assertEquals("",processor.processTemplate("all-directive-test.flt", new HashMap()));
	}
}