package cn.roothub.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.roothub.entity.RootNotice;

public interface RootNoticeDao {

	/**
	 * 查询所有通知列表
	 * @return
	 */
	List<RootNotice> selectAll(@Param("author") String author);
	/**
	 * 分页查询通知列表
	 * @param author
	 * @param start
	 * @param limit
	 * @return
	 */
	List<RootNotice> selectAll(@Param("author") String author, @Param("start") Integer start, @Param("limit") Integer limit);
	
	/**
	 * 根据通知ID查询通知
	 * @param noticeId
	 * @return
	 */
	RootNotice selectByNoticeId(@Param("noticeId") Integer noticeId);
	
	/**
	 * 添加通知
	 * @param notice
	 * @return
	 */
	int insert(RootNotice notice);
	
	/**
	 * 根据通知ID删除通知
	 * @param noticeId
	 * @return
	 */
	int deleteByNoticeId(@Param("noticeId") Integer noticeId);
	
	/**
	 * 根据昵称更新通知
	 * @return
	 */
	int updateByAuthorName(@Param("authorName") String authorName);
	
	/**
	 * 根据昵称统计未读通知
	 * @param authorName
	 * @return
	 */
	int countNotReadByAuthor(@Param("authorName") String authorName);
	
	/**
	 * 根据昵称统计所有通知
	 * @param authorName
	 * @return
	 */
	int countByAuthor(@Param("authorName") String authorName);
}
