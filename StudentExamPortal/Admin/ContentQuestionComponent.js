import React, { useEffect, useRef, useState } from "react";
import { Button, Col, Form, Row, Modal } from "react-bootstrap";
import * as allServices from "../AllServices";

export const ContentQuestion = () => {
  const t_id = useRef();
  const c_id = useRef();
  const q_id = useRef();
  const e_question = useRef();
  const option_1 = useRef();
  const option_2 = useRef();
  const option_3 = useRef();
  const option_4 = useRef();
  const answer = useRef();

  const [topic, setTopic] = useState([]);
  const [content, setContent] = useState([]);
  const [questions, setQuestions] = useState([]);
  const [selectedData, setSelectedData] = useState({});
  const [btn, setBtn] = useState(false);
  const [show, setShow] = useState(false);

  const handleShow = () => {
    setSelectedData({});
    setShow(true);
    setBtn(false);
  };

  const handleClose = () => { setShow(false) };

  useEffect(function () {
    GetTopic();
    GetQuestions();
  }, []);

  const GetTopic = async () => {
    const res = await allServices.getTopic();
    setTopic(res);
  }

  const GetTopicWiseContent = async () => {
    const tid = t_id.current.value;
    if (tid) {
      const resp = await allServices.gettopicwisecontent(tid);
      setContent(resp);
    }
  }

  const GetQuestions = async () => {
    const resp = await allServices.getQuestions();
    setQuestions(resp);
  }

  const AddQuestion = async () => {
    const question = e_question.current.value;
    const o1 = option_1.current.value;
    const o2 = option_2.current.value;
    const o3 = option_3.current.value;
    const o4 = option_4.current.value;
    const ans = answer.current.value;
    // const content_id = c_id.current.value;
    const st = {
      "topics":{"topic_id":t_id.current.value},
      "questions": question,
      "option1": o1,
      "option2": o2,
      "option3": o3,
      "option4": o4,
      "correct_answer_no": ans,
      "contentquestion": { "content_id": c_id.current.value}
    };
    const res = await allServices.addQuestion(st);
    alert("Question added successfully");
    handleClose();
    GetQuestions();
    Clear();
  }

  const View = (data) => {
    setBtn(true);
    setSelectedData(data);
    setShow(true);
  }

  const UpdateQuestion = async () => {
    const st = {
      "topics":{
        "topic_id":t_id.current.value
      },
      "question_id": q_id.current.value,
      "questions": e_question.current.value,
      "option1": option_1.current.value,
      "option2": option_2.current.value,
      "option3": option_3.current.value,
      "option4": option_4.current.value,
      "correct_answer_no": answer.current.value,
      "contentquestion": {
        "content_id": c_id.current.value
      },
    };
    console.log(st);
    const updated = await allServices.updateQuestion(st);
    alert("Question updated successfully");
    // handleClose();
    GetQuestions();
  }

  const DeleteQuestion = async (id) => {
    const res = await allServices.deleteQuestion(id);
    alert("Question deleted successfully");
    setQuestions(prevQuestions => prevQuestions.filter(question => question.question_id !== id));
  };

  const Clear = () => {
    t_id.current.value = "";
    c_id.current.value = "";
    e_question.current.value = "";
    option_1.current.value = "";
    option_2.current.value = "";
    option_3.current.value = "";
    option_4.current.value = "";
    answer.current.value = "";
  }

  return (
    <div>
      <h3>Content</h3>
      <button className='btn btn-primary' onClick={handleShow}>Add</button>
      <br /> <br />
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Content Question</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Topic</Form.Label>
              <Form.Select
                ref={t_id}
                value={selectedData?.topic?.topic_id}
                onChange={(data) => {
                  setSelectedData(prev => ({ ...prev, topic: { topic_id: data.target.value } }));
                  GetTopicWiseContent();
                }}
              >
                <option selected disabled>select topic</option>
                {topic.map((d, k) => (
                  <option key={k} value={d.topic_id}>{d.topic_name}</option>
                ))}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Content</Form.Label>
              <Form.Select
                ref={c_id}
                value={selectedData?.contentquestion?.content_id}
                onChange={(data) => {
                  setSelectedData(prev => ({ ...prev, contentquestion: { content_id: data.target.value } }));
                }}
              >
                <option selected disabled>select content</option>
                {content.map((d, k) => (
                  <option key={k} value={d.content_id}>{d.content_name}</option>
                ))}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Enter Question</Form.Label>
              <Form.Control
                type="text"
                ref={e_question}
                placeholder="Enter Question"
                value={selectedData?.questions}
                onChange={(data) => {
                  setSelectedData(prev => ({ ...prev, questions: data.target.value }));
                }}
              />
            </Form.Group>
            <Row className="mb-3">
              <Form.Group as={Col}>
                <Form.Label>Option1</Form.Label>
                <Form.Control
                  type="text"
                  ref={option_1}
                  placeholder="option1"
                  value={selectedData?.option1}
                  onChange={(data) => {
                    setSelectedData(prev => ({ ...prev, option1: data.target.value }));
                  }}
                />
              </Form.Group>
              <Form.Group as={Col}>
                <Form.Label>Option2</Form.Label>
                <Form.Control
                  type="text"
                  ref={option_2}
                  placeholder="option2"
                  value={selectedData?.option2}
                  onChange={(data) => {
                    setSelectedData(prev => ({ ...prev, option2: data.target.value }));
                  }}
                />
              </Form.Group>
            </Row>
            <Row className="mb-3">
              <Form.Group as={Col}>
                <Form.Label>Option3</Form.Label>
                <Form.Control
                  type="text"
                  ref={option_3}
                  placeholder="option3"
                  value={selectedData?.option3}
                  onChange={(data) => {
                    setSelectedData(prev => ({ ...prev, option3: data.target.value }));
                  }}
                />
              </Form.Group>

              <Form.Group as={Col}>
                <Form.Label>Option4</Form.Label>
                <Form.Control
                  type="text"
                  ref={option_4}
                  placeholder="option4"
                  value={selectedData?.option4}
                  onChange={(data) => {
                    setSelectedData(prev => ({ ...prev, option4: data.target.value }));
                  }}
                />
              </Form.Group>
            </Row>
            <Form.Group className="mb-3">
              <Form.Label>Correct Answer</Form.Label>
              <Form.Control
                type="text"
                placeholder="enter correct answer"
                ref={answer}
                value={selectedData?.correct_answer_no}
                onChange={(data) => {
                  setSelectedData(prev => ({ ...prev, correct_answer_no: data.target.value }));
                }}
              />
            </Form.Group>

            <Button variant="primary" hidden={!btn} type="up" onClick={UpdateQuestion}>Update</Button>
            <Button variant="primary" hidden={btn} type="submit" onClick={AddQuestion}>Submit</Button>
            <Button variant="secondary" type="Clear" onClick={Clear}>Clear</Button>
          </Form>
        </Modal.Body>
      </Modal>

      <table className="table table-bordered table-striped table-hover" size="sm">
        <thead>
          <tr>
            <th>#</th>
            <th>Question</th>
            <th>Option1</th>
            <th>Option2</th>
            <th>Option3</th>
            <th>Option4</th>
            <th>Correct Answer</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {
            questions.map((d, k) => (
              <tr key={k}>
                <td>{k + 1}</td>
                <td>{d.questions}</td>
                <td>{d.option1}</td>
                <td>{d.option2}</td>
                <td>{d.option3}</td>
                <td>{d.option4}</td>
                <td>{d.correct_answer_no}</td>
                <td>
                  <Button variant="warning" onClick={() => { handleShow(); View(d); }}>View</Button>
                  <Button variant="danger" onClick={() => DeleteQuestion(d.question_id)}>Delete</Button>
                </td>
              </tr>
            ))
          }
        </tbody>
      </table>
    </div>
  );
}
