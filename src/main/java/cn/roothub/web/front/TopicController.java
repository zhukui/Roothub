package cn.roothub.web.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.roothub.base.BaseEntity;
import cn.roothub.dto.PageDataBody;
import cn.roothub.dto.Result;
import cn.roothub.dto.RootTopicExecution;
import cn.roothub.entity.RootReply;
import cn.roothub.entity.RootTopic;
import cn.roothub.entity.RootUser;
import cn.roothub.entity.Tab;
import cn.roothub.service.CollectService;
import cn.roothub.service.RootNoticeService;
import cn.roothub.service.RootReplyService;
import cn.roothub.service.RootSectionService;
import cn.roothub.service.RootTopicService;
import cn.roothub.service.RootUserService;
import cn.roothub.service.TabService;
import cn.roothub.util.Base64Util;
import cn.roothub.util.CookieAndSessionUtil;

@Controller
public class TopicController extends BaseController{

private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RootUserService rootUserService;
	@Autowired
	private RootTopicService rootTopicService;
	@Autowired
	private RootSectionService rootSectionService;
	@Autowired
	private RootReplyService rootReplyService;
	@Autowired
	private CollectService collectDaoService;
	@Autowired
	private RootNoticeService rootNoticeService;
	@Autowired
	private TabService tabService;
	
	/**
	 * 话题详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
	private String detail(@PathVariable Integer id, Model model,@RequestParam(value = "p", defaultValue = "1") Integer p,HttpServletRequest request) {
		RootTopic topic = rootTopicService.findByTopicId(id);
		RootUser user = getUser(request);
		if(topic == null) {
			return "error-page/404";
		}
		//浏览量+1
		topic.setViewCount(topic.getViewCount()+ 1);
		rootTopicService.updateTopic(topic);//更新话题数据
		//分页查询回复
		PageDataBody<RootReply> replyPage = rootReplyService.page(p, 50, id);
		int countByTid = collectDaoService.countByTid(id);//话题被收藏的数量
		int countTopicByUserName = 0;
		int countCollect = 0;
		int notReadNotice = 0;
		if(user != null) {
			countTopicByUserName = rootTopicService.countByUserName(user.getUserName());//用户发布的主题的数量
			countCollect = collectDaoService.count(user.getUserId());//用户收藏话题的数量
			notReadNotice = rootNoticeService.countNotReadNotice(user.getUserName());//统计未读通知的数量
		}
		BaseEntity baseEntity = new BaseEntity();
		model.addAttribute("baseEntity", baseEntity);
		model.addAttribute("topic", topic);
		model.addAttribute("replyPage", replyPage);
		model.addAttribute("user", user);
		model.addAttribute("countByTid", countByTid);
		request.setAttribute("countTopicByUserName", countTopicByUserName);
		request.setAttribute("countCollect", countCollect);
		request.setAttribute("notReadNotice", notReadNotice);
		return "topic/detail";
	}
	
	/**
	 * 发布话题
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topic/create", method = RequestMethod.GET)
	private String create(HttpServletRequest request){
		/*String cookie = CookieAndSessionUtil.getCookie(request, "user");
		if(cookie == null) {
			return "error-page/500";
		}
		RootUser user = rootUserService.findByName(Base64Util.decode(cookie));
		if(user == null) {
			return "error-page/500";
		}*/
		RootUser user = getUser(request);
		if(user == null) {
			return "error-page/500";
		}
		List<Tab> ptabList = tabService.selectAll();
		request.setAttribute("ptabList", ptabList);
		return "topic/create";
	}
	
	@RequestMapping(value = "/topic/save", method = RequestMethod.POST)
	@ResponseBody
	private Result<RootTopicExecution> save(String title, String content, String ptab, String tag,HttpServletRequest request){
		//String cookie = CookieAndSessionUtil.getCookie(request, "user");
		//RootUser user = rootUserService.findByName(Base64Util.decode(cookie));
		RootUser user = getUser(request);
		if(title == null) {
			return new Result<>(false, "请输入标题");
		}
		if(ptab == null) {
			return new Result<>(false, "请选择板块");
		}
		if(tag == null) {
			return new Result<>(false, "请输入标签");
		}
		if(user == null) {
			return new Result<>(false, "请先登录");
		}
		/*
		RootTopic topic = new RootTopic();
		topic.setTab("all");
		topic.setTitle(title);
		topic.setTag(tag);
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		//topic.setLastReplyTime(null);
		//topic.setLastReplyAuthor(null);
		topic.setViewCount(0);
		topic.setAuthor(user.getUserName());
		topic.setTop(false);
		topic.setGood(false);
		topic.setShowStatus(true);
		topic.setReplyCount(0);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setContent(content);
		topic.setPostGoodCount(0);
		topic.setPostBadCount(0);
		topic.setStatusCd("1000");
		//topic.setRemark("");
		RootTopicExecution saveTopic = rootTopicService.saveTopic(topic);
		*/
		RootTopic topic = new RootTopic();
		topic.setPtab(ptab);
		topic.setTab("all");
		topic.setTitle(title);
		topic.setTag(tag);
		topic.setCreateDate(new Date());
		topic.setUpdateDate(new Date());
		//topic.setLastReplyTime(new Date());
		//topic.setLastReplyAuthor(user.getUserName());
		topic.setViewCount(0);
		topic.setAuthor(user.getUserName());
		topic.setTop(false);
		topic.setGood(false);
		topic.setShowStatus(true);
		topic.setReplyCount(0);
		topic.setIsDelete(false);
		topic.setTagIsCount(true);
		topic.setContent(content);
		topic.setAvatar(user.getAvatar());//话题作者的头像
		RootTopicExecution saveTopic = rootTopicService.saveTopic(topic);
		logger.debug(saveTopic.toString());
		return new Result<RootTopicExecution>(true, saveTopic);
	}
	
	/**
	 * 根据标签分页查找话题
	 * @param name
	 * @param model
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/topic/tag/{name}", method = RequestMethod.GET)
	private String tag(@PathVariable String name, Model model,@RequestParam(value = "p", defaultValue = "1") Integer p) {
		PageDataBody<RootTopic> pageByTag = rootTopicService.pageByTag(name, p, 20);
		BaseEntity baseEntity = new BaseEntity();
		model.addAttribute("baseEntity", baseEntity);
		model.addAttribute("tagName", name);
		model.addAttribute("pageByTag", pageByTag);
		return "tag/detail";
	}
}
