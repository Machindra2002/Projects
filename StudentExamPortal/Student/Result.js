import React from "react";

export const Result=()=>{

    return(
        <div>
           
            <h3>Result page </h3>
            
        </div>
    )
}


// import { useNavigate } from "react-router-dom";
// import { useLocation } from "react-router-dom";
// import axios from "axios";
// import { useEffect, useState } from "react";
// // import incorrect from "./cross-button.png";
// // import correct from "./check-mark.png";

// export const ShowResults=()=>
// {
    
//     let[mydata,setTableData]=useState([]);
//     let mark=0;
    


//     useEffect(function () {
//         ShowResultData();
//     },[])

//     const ShowResultData=()=>
//     {
//         // axios({
//         //     url:"http://localhost:9090/api/getdetailsbyexamid/"+id,
//         //     method:'get',
//         //     contentType:'application/json'
//         // }).then((e)=>
//         // {
//         //     console.log(e.data);
//         //     setTableData(e.data)
//         //     console.log("ganesh")
//         //     console.log(mark)
//         // })
//     }

//     const{state}=useLocation();
//     const{id}=state;
    
//     const navigate=useNavigate();
//     return(
//         <div>

//             <div className="mt-4">
//                 <input type="button" value="back to exam" className="btn btn-info mb-4"onClick={()=>navigate("/employee/exam")} />
//             </div>
            
            
//                 <table className="table table-bordered">
//                     <thead>
//                         <tr className="table-primary">
//                             <th >s.no</th>
//                             <th>Your Question.</th>
//                             <th>submited option</th>
//                             <th>correct option</th>
//                             <th>Result</th>
//                         </tr>
//                     </thead>
//                             <tbody>
                           
                            
//                                 {mydata.map((d,k)=>{
//                                     let ans=0
//                                     var m=0;
//                                     if(d.submitted_option_number==d.content_questions.questions)
//                                     {
//                                         ans=1;
                                        
//                                         mark=mark+1;
//                                     }
//                                     else
//                                     {
//                                         ans=0;
//                                     }
//                                     return(
//                                         <tr >
//                                             <td >{k+1}</td>
//                                             <td><h6>{d.content_questions.correct_option}</h6></td>
//                                             <td><h6>{d.submitted_option_number}</h6></td>
//                                             <td><h6>{d.content_questions.questions}</h6></td>
//                                             {/* <td>{ans===1?<img alt="correct"  src={correct} width={30} style={{"display":"block","margin":"0 auto"}}/>:<img alt="incorrect" src={incorrect} width={30} style={{"display":"block","margin":"0 auto"}}/>}</td> */}
//                                         </tr>
                                        
//                                     )
//                                 })}
//                                 <tr className="table-primary" colspan={3}>
                                    
//                                     <td colspan={2}><h4>Your Result</h4></td>
//                                     <td colspan={3 }><h4>Total Marks: {mark}</h4></td>
//                                 </tr>
//                             </tbody>
//                 </table>
//         </div>
//     )  
// }

// ================================================================
import React, { useEffect, useRef, useState } from "react";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import * as AllServices from "../AllServices";

export const ExamCom = () => {
    const [contentdata, setTopicData] = useState([]);
    const [start_date, setStartDate] = useState(new Date().toLocaleDateString());
    const [start_time, setStartTime] = useState("");
    const [end_time, setEndTime] = useState("");
    const [studentdata, setStudentData] = useState([]);
    const [useroption, setUserOption] = useState([]);
    const [questiondata, setQuestionData] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        FetchTopics();
        FetchExamData();
        console.log(studentdata);
        console.log(ts);
    }, []);

    const FetchTopics = async () => {
        try {
            const res = await AllServices.GetTopics();
            // console.log(res);
            setTopicData(res);
        } catch (error) {
            alert("Error fetching topics");
        }
    };

    const qid = useRef();

    const myquestions = async () => {
        setStartTime(new Date().toLocaleTimeString());
        let id = qid.current.value;
        try {
            if (id) {
                const topwisecon = await AllServices.TopicWiseQuestion(id);
                console.log(topwisecon);
                setQuestionData(topwisecon);
            }
        } catch (error) {
            console.error('Error fetching topic-wise content:', error);
        }
    };

    const SubmitOption = (qid, op) => {
        var st = {
            "content_question": { "question_id": qid },
            "sumbited_question_no": op,
            "exam_detials": { "exam_id": studentdata.exam_id }
        };
        var data = useroption;
        var index = -1;
        data.forEach(function (d, k) {
            console.log("index= " + k);
            console.log("index 2= " + qid);
            if (d.content_question.question_id === qid) {
                index = k;
            }
        });
        if (index !== -1) {
            data[index] = st;
        } else {
            data.push(st);
        }
    };

    var ts = localStorage.getItem("Employee_id");
    const submitExam = async () => {
        var st = {
            "exam_date": start_date,
            "end_time": new Date().toLocaleTimeString(),
            "start_time": start_time,
            "examquestion": useroption,
            "stud_details": { "student_id": ts }
        };
        console.log(st);
        try {
            const addexam = await AllServices.AddExamDetails(st);
            console.log(addexam);
            alert("Exam submitted successfully");
        } catch (error) {
            console.error('Error fetching topic-wise content:', error);
        }
    };

    const FetchExamData = async () => {
        try {
            if (ts) {
                const resp = await AllServices.GetExamDetailsByStudentid(ts);
                console.log(resp);
                setStudentData(resp);
            } else {
                return null;
            }
        } catch (error) {
            console.error(`Failed to fetch exam details for student`, error);
        }
    };

    return (
        <div>
            <h3>Exam</h3>
            <div className="row form-group">
                <label>Topics</label>
                <div className="col-md-2">
                    <select ref={qid} className="form-control">
                        <option disabled value="select topic">Select Topic</option>
                        {contentdata.map((d, k) => (
                            <option key={k} value={d.topic_id}>{d.topic_name}</option>
                        ))}
                    </select>
                </div>
                <div className="col-md-2">
                    <input className="btn btn-outline-secondary" type="button" value="Solve" onClick={myquestions} />
                </div>
            </div>

            <br />
            <h3>Solve All Questions</h3>
            <hr />
            <div>
                {questiondata.map((d, k) => {
                    return (
                        <div key={k}>
                            <h6>{k + 1}.{d.question}</h6>
                            <Form.Check
                                type="radio"
                                label={d.option1}
                                name={d.question_id}
                                onChange={() => SubmitOption(d.question_id, 1)}
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
            <div>
                <input className="btn btn-outline-danger mb-4" type="button" value="Submit" onClick={submitExam} />
            </div>
            <div>
                <div>
                    <div>
                        <table className="table table-bordered">
                            <thead>
                                <tr className="table-primary">
                                    <th>Student id</th>
                                    <th>Start date</th>
                                    <th>Start time</th>
                                    <th>End time</th>
                                    <th>Exam id</th>
                                    <th>Results</th>
                                </tr>
                            </thead>
                            <tbody>
                                {studentdata &&
                                    studentdata.map((d, k) => {
                                        return (
                                            <tr key={k}>
                                                <td>{ts}</td>
                                                <td>{d.start_time}</td>
                                                <td>{d.end_time}</td>
                                                <td>{d.exam_date}</td>
                                                <td>{d.exam_id}</td>
                                                <td>
                                                    <input
                                                        value="Result"
                                                        className="btn btn-info"
                                                        type="button"
                                                        onClick={() => navigate("/result", { state: { id: d.exam_id } })}
                                                    />
                                                </td>
                                            </tr>
                                        )
                                    })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
};