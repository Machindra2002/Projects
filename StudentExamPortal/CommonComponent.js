import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Dashboard } from "./Admin/DashboardComponent";
import { AllStudentDetail } from "./Admin/AllStudentComponent";
import { AllExam } from "./Admin/ExamComponent";
import { Topic } from "./Admin/TopicComponent";
import { TopicContent } from "./Admin/TopicContentComponent";
import { ContentQuestion } from "./Admin/ContentQuestionComponent";
import { Main } from "./MainComponent";
import { Login } from "./loginComponent/LoginPage";
import { StudentMain } from "./Student/StudentMainComponent";
import { DashBoard } from "./Student/DashBoardComponent";
import { Profile } from "./Student/ProfileComponent";
import { Exam } from "./Student/ExamComponent";
import { SignUp } from "./loginComponent/SignUp";



export const Common = () => {

    return (
        <div>
            <Router>
                {/* <div> */}
                <Routes>
                    <Route path="" element={<Login />} />
                    <Route path="signup" element={<SignUp />}/>

                    <Route path="/main" element={<Main />}>
                        {/* admin->student section  */}
                        <Route path="dashboard" element={<Dashboard />} />
                        <Route path="allstudentdetail" element={<AllStudentDetail />} />
                        <Route path="allexam" element={<AllExam />} />
                        {/* admin->Master section */}
                        <Route path="topic" element={<Topic />} />
                        <Route path="topiccontent" element={<TopicContent />} />
                        <Route path="contentquestion" element={<ContentQuestion />} />
                    </Route>
                    <Route path="/studentmain" element={<StudentMain />}>
                        {/* student page  */}
                        <Route path="dashboard" element={<DashBoard />} />
                        <Route path="exam" element={<Exam />} />
                        <Route path="profile" element={<Profile />} />
                        {/* <Route path="result" element={<ShowResults />} ></Route> */}
                    </Route>
                </Routes>
                {/* </div> */}
            </Router >

        </div >
    )
}