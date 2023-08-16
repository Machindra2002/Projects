package mypackage.model;

import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tblTopic_Content")
public class TopicContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int content_id;
	private String content_name;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "topic_id")
	private Topic topic;
	
	private String tblcontent_tutorial;
	
	@OneToMany(mappedBy = "contentquestion",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("contentquestion")
	private Set<ContentQuestion> questions;
	private int flag=0;


	public TopicContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopicContent(int content_id, String content_name, Topic topic, String tblcontent_tutorial,
			Set<ContentQuestion> questions, int flag) {
		super();
		this.content_id = content_id;
		this.content_name = content_name;
		this.topic = topic;
		this.tblcontent_tutorial = tblcontent_tutorial;
		this.questions = questions;
		this.flag = flag;
	}

	public String getTblcontent_tutorial() {
		return tblcontent_tutorial;
	}

	public void setTblcontent_tutorial(String tblcontent_tutorial) {
		this.tblcontent_tutorial = tblcontent_tutorial;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Set<ContentQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<ContentQuestion> questions) {
		this.questions = questions;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getContent_name() {
		return content_name;
	}

	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
