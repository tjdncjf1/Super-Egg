package spms.dao;

import java.util.List;
import java.util.Map;

import spms.vo.Project;
import spms.vo.ProjectMember;

public interface ProjectDao {

	public List<Project> selectList(Map<String,Object> paramMap) throws Exception;
	public Project selectOne(int no) throws Exception;
	public int insert(Project project) throws Exception;
	public int update(Project project) throws Exception;
	public int delete(int no) throws Exception;
	public List<ProjectMember> selectMyProjects(int no) throws Exception;

}

