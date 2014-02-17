package spms.vo;

import java.io.Serializable;
import java.sql.Date;

// 보통 VO는 equals()와 hashCode(), toString()을 오버라이딩한다.
public class Project implements Serializable {
  private static final long serialVersionUID = 1L;
  
	public static final int READY = 0;
	public static final int WORKING = 1;
	public static final int COMPLETE = 2;
	public static final int CANCEL = 3;
	
	int				no;
	String 		title;
	String 		contents;
	String 		tags;
	Date 			startDate;		// 값 객체는 Serializable을 구현하는 것이 좋다.
	Date			endDate;
	int				state;
	Member 		owner;		// 이 프로젝트의 오너니까 일반 관계를 표현
	
	@Override
  public String toString() {
	  return "Project [no=" + no + ", title=" + title + ", contents=" + contents
	      + ", tags=" + tags + ", startDate=" + startDate + ", endDate="
	      + endDate + ", state=" + state + "]";
  }

	@Override
  public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((contents == null) ? 0 : contents.hashCode());
	  result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
	  result = prime * result + no;
	  result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
	  result = prime * result + state;
	  result = prime * result + ((tags == null) ? 0 : tags.hashCode());
	  result = prime * result + ((title == null) ? 0 : title.hashCode());
	  return result;
  }

	@Override
  public boolean equals(Object obj) {
	  if (this == obj)
		  return true;
	  if (obj == null)
		  return false;
	  if (getClass() != obj.getClass())
		  return false;
	  Project other = (Project) obj;
	  if (contents == null) {
		  if (other.contents != null)
			  return false;
	  } else if (!contents.equals(other.contents))
		  return false;
	  if (endDate == null) {
		  if (other.endDate != null)
			  return false;
	  } else if (!endDate.equals(other.endDate))
		  return false;
	  if (no != other.no)
		  return false;
	  if (startDate == null) {
		  if (other.startDate != null)
			  return false;
	  } else if (!startDate.equals(other.startDate))
		  return false;
	  if (state != other.state)
		  return false;
	  if (tags == null) {
		  if (other.tags != null)
			  return false;
	  } else if (!tags.equals(other.tags))
		  return false;
	  if (title == null) {
		  if (other.title != null)
			  return false;
	  } else if (!title.equals(other.title))
		  return false;
	  return true;
  }

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
	
	
}
