package mypackage.service;

import mypackage.model.*;

import mypackage.repository.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServices {

	@Autowired
	TopicRepository trepo;
	@Autowired
	TopicContentRepository crepo;
	@Autowired
	ContentQuestionRepository cqrepo;

	public Topic AddTopic(Topic t) {
		Topic tp = trepo.save(t);
		return tp;
	}

	public List<Topic> GetAllTopics() {
		List<Topic> lst = new ArrayList<Topic>();
		for (Topic t : trepo.findAll()) {
			if (t.getFlag() == 0) {
				Topic topic = new Topic(t.getTopic_id(), t.getTopic_name(), null, null, t.getFlag());
				lst.add(topic);
			}
		}
		return lst;
	}

	public Topic GetTopicById(int id) {
		Topic t = trepo.findById(id).get();
		Topic topic = new Topic(t.getTopic_id(), t.getTopic_name(), null, null, t.getFlag());
		return topic;
	}

	public Topic DeleteTopicById(int id) {
		Topic t = trepo.findById(id).get();
		Topic topic = new Topic(t.getTopic_id(), t.getTopic_name(), null, null, 1);
		Topic tp = trepo.save(topic);
		return tp;
	}

	public Topic UpdateTopic(Topic t) {
		Topic tp = trepo.save(t);
		return tp;
	}

	// this service use for deleted data parmanently
//	public Topic DeleteTopic(int id) {
//		Topic t = GetTopicById(id);
//		trepo.delete(t);
//		return t;
//	}

//	=====================================================================

	public TopicContent AddTopicContent(TopicContent t) {
		TopicContent tc = crepo.save(t);
		return tc;
	}

	public List<TopicContent> GetAllTopicContent() {
		List<TopicContent> lst = new ArrayList<TopicContent>();
		for (TopicContent c : crepo.findAll()) {
			if (c.getFlag() == 0) {
				Topic topic = new Topic(c.getTopic().getTopic_id(), c.getTopic().getTopic_name(), null, null,
						c.getTopic().getFlag());
				TopicContent tc = new TopicContent(c.getContent_id(), c.getContent_name(), topic,
						c.getTblcontent_tutorial(), null, c.getFlag());
				lst.add(tc);
			}
		}
		return lst;
	}

	public TopicContent GetAllTopicContentById(int id) {
		TopicContent lst = crepo.findById(id).get();
		Topic t = new Topic(lst.getTopic().getTopic_id(), lst.getTopic().getTopic_name(), null, null,
				lst.getTopic().getFlag());
		TopicContent tc = new TopicContent(lst.getContent_id(), lst.getContent_name(), t, lst.getTblcontent_tutorial(),
				null, lst.getFlag());
		return tc;
	}

	public List<TopicContent> GetTopicWiseContent(int topic_id) {
		List<TopicContent> lst = new ArrayList<TopicContent>();
		for (TopicContent c : crepo.findAll()) {
			if (c.getTopic().getTopic_id() == topic_id && c.getFlag() == 0) {
				Topic topic = new Topic(c.getTopic().getTopic_id(), c.getTopic().getTopic_name(), null, null,
						c.getTopic().getFlag());
				TopicContent tc = new TopicContent(c.getContent_id(), c.getContent_name(), topic,
						c.getTblcontent_tutorial(), null, c.getFlag());
				lst.add(tc);
			}
		}
		return lst;
	}

	public TopicContent UpdateTopicContent(TopicContent t) {
		TopicContent tc = crepo.save(t);
		return tc;
	}

	public TopicContent deleteTopicContent(int id) {
		TopicContent tc = crepo.findById(id).get();
		Topic t = new Topic(tc.getTopic().getTopic_id(), tc.getTopic().getTopic_name(), null, null,
				tc.getTopic().getFlag());
		TopicContent tcc = new TopicContent(tc.getContent_id(), tc.getContent_name(), t, tc.getTblcontent_tutorial(),
				null, 1);
		TopicContent tcontent = crepo.save(tcc);
		return tcontent;
	}

//	public TopicContent deleteTopicContent(int id) {
//		TopicContent t = GetAllTopicContentById(id);
//		crepo.delete(t);
//		return t;
//	}

//	==========================================================================

	public ContentQuestion AddContentQuestion(ContentQuestion c) {
		ContentQuestion q = cqrepo.save(c);
		return q;
	}

	public List<ContentQuestion> GetAllContentQuestion() {
		List<ContentQuestion> lst = new ArrayList<ContentQuestion>();
		for (ContentQuestion q : cqrepo.findAll()) {
			if (q.getFlag() == 0) {
				TopicContent tc = new TopicContent(q.getContentquestion().getContent_id(),
						q.getContentquestion().getContent_name(), null, q.getContentquestion().getTblcontent_tutorial(),
						null, q.getContentquestion().getFlag());
				ContentQuestion cq = new ContentQuestion(q.getQuestion_id(), q.getQuestions(), q.getOption1(),
						q.getOption2(), q.getOption3(), q.getOption4(), q.getCorrect_answer_no(), tc, null, null,
						q.getFlag());
				lst.add(cq);
			}
		}
		return lst;
	}

	public List<ContentQuestion> GetTopicWiseQuestion(int tid) {
		List<ContentQuestion> lst = new ArrayList<ContentQuestion>();
		for (ContentQuestion cq : cqrepo.findAll()) {
			if (cq.getTopics().getTopic_id() == tid) {
				Topic topic = new Topic(cq.getTopics().getTopic_id(), cq.getTopics().getTopic_name(), null, null,
						cq.getTopics().getFlag());
				ContentQuestion q = new ContentQuestion(cq.getQuestion_id(), cq.getQuestions(), cq.getOption1(),
						cq.getOption2(), cq.getOption3(), cq.getOption4(), cq.getCorrect_answer_no(), null, null, topic,
						cq.getFlag());
				lst.add(q);
			}
		}
		return lst;
	}

	public ContentQuestion UpdateContentQuestion(ContentQuestion c) {
		ContentQuestion cq = cqrepo.save(c);
		return cq;
	}

	public ContentQuestion deleteContentQuestion(int id) {
		ContentQuestion cq = cqrepo.findById(id).get();
		Topic t = new Topic(cq.getTopics().getTopic_id(), cq.getTopics().getTopic_name(), null, null,
				cq.getTopics().getFlag());
		TopicContent tcc = new TopicContent(cq.getContentquestion().getContent_id(),
				cq.getContentquestion().getContent_name(), t, cq.getContentquestion().getTblcontent_tutorial(), null,
				cq.getContentquestion().getFlag());
		ContentQuestion q = new ContentQuestion(cq.getQuestion_id(), cq.getQuestions(), cq.getOption1(),
				cq.getOption2(), cq.getOption3(), cq.getOption4(), cq.getCorrect_answer_no(), tcc, null, t, 1);
		ContentQuestion deletedQuestion = cqrepo.save(q);
		return deletedQuestion;
	}
	
	
//	public ContentQuestion deleteContentQuestion(int id) {
//		ContentQuestion cq = cqrepo.findById(id).orElse(null);
//		if (cq != null) {
//			cq.setFlag(1);
//			ContentQuestion deletedQuestion = cqrepo.save(cq);
//			return deletedQuestion;
//		}
//		return null;
//	}

}
