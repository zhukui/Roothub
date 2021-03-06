package priv.sen.root.config.test;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.roothub.config.CookieConfig;
import cn.roothub.config.SiteConfig;
import cn.roothub.util.StringUtil;
import priv.sen.root.dao.test.BaseTest;


public class ConfigTest extends BaseTest{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SiteConfig citeConfig;
	@Autowired
	private CookieConfig cookieConfig;
	
	
	@Test
	public void test02() throws Exception{
		String name = citeConfig.getCookieConfig().getName();
		int maxAge = citeConfig.getCookieConfig().getMaxAge();
		String path = citeConfig.getCookieConfig().getPath();
		boolean httpOnly = citeConfig.getCookieConfig().isHttpOnly();
		logger.debug("cookie.name === "+name);
		logger.debug("cookie.maxAge === "+maxAge);
		logger.debug("cookie.path === "+path);
		logger.debug("cookie.httpOnly === "+httpOnly);
	}
	
	@Test
	public void test03() throws Exception{
		String string = UUID.randomUUID().toString();
		logger.debug(string);
	}
	
	@Test
	public void test04() throws Exception{
		String uuid = StringUtil.getUUID();
		logger.debug(uuid);
	}
	
	@Test
	public void test05() throws Exception{
		logger.debug(citeConfig.getIntro());
	}
}
