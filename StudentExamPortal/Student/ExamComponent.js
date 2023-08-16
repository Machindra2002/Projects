import React, { useEffect, useRef, useState } from "react";
import * as allServices from "../AllServices";
import { Form } from "react-bootstrap";

export const Exam = () => {
    const qid = useRef();
    const [topics, setTopics] = useState([]);
    const [question, setQuestionData] = useState([]);
    const [studentdata,setStudentData] = useState([]);

    const [starttime,setStartTime] = useState("");
    useEffect(function () {
        GetTopic();
    }, []);

    const GetTopic= async ()=>{
        const topic = await allServices.getTopic();
        setTopics(topic); 
    }

    const GetExamQuestion = async()=>{
        setStartTime(new Date().toLocaleTimeString());
        let id = qid.current.value;
        if(id){
            const topicwisequestion = await allServices.gettopicwiseQuestion(id);
            console.log(topicwisequestion);
            setQuestionData(topicwisequestion);
        }
    } 
    
    const SubmitOption=(id)=>{
            
    }

    return (
        <div>

            <h3>Exam</h3>
            <div>
                <label>Topics</label>
                <div className="col-md-2">
                    <select ref={qid} className="form-control">
                        <option value="select topic">Select Topic</option>
                        {topics.map((d, k) => (
                            <option key={k} value={d.topic_id}>{d.topic_name}</option>
                        ))}
                    </select>
                </div>
                <br />
                <div className="col-md-2">
                    <input className="btn btn-outline-secondary" type="button" value="Solve" onClick={GetExamQuestion} />
                </div>
            </div>
            <br />
            <h2>Solve Questions</h2>
            <div>
                {question.map((d,k)=>{
                    return (
                        <div key={k}>
                        <h6>{k + 1}.{d.questions}</h6>
                        <Form.Check
                            type="radio"
                            label={d.option1}
                            name={d.question_id}
                            onChange={() => SubmitOption(d.content_question?.question_id, 1)}
                            value={d.option1}
                        />
                        <Form.Check
                            type="radio"
                            label={d.option2}
                            name={d.content_question?.question_id}
                            onChange={() => SubmitOption(d.content_question?.question_id, 2)}
                            value={d.option2}
                        />
                        <Form.Check
                            type="radio"
                            label={d.option3}
                            name={d.content_question?.question_id}
                            onChange={() => SubmitOption(d.content_question?.question_id, 3)}
                            value={d.option3}
                        />
                        <Form.Check
                            type="radio"
                            label={d.option4}
                            name={d.content_question?.question_id}
                            onChange={() => SubmitOption(d.content_question?.question_id, 4)}
                            value={d.option4}
                        />
                        <hr />
                    </div>
                    )
                })}
            </div>

            {/* <div>
                <table className="table table-bordered table-striped ">
                    <thead>
                        <tr>
                            <th>Student Id</th>
                            <th>Exam Data</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Exam Id</th>
                            <th>Result</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Array.isArray(studentdata) && studentdata.map((d, k) => (
                            <tr key={k}>
                                <td>{sid}</td>
                                <td>{d.exam_data}</td>
                                <td>{d.start_time}</td>
                                <td>{d.end_time}</td>
                                <td>{d.exam_id}</td>
                                <td>
                                    <input type="button" value="view result" />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div> */}
        </div>

    )
}

