import React, { useEffect, useState } from "react";
import { Card } from 'react-bootstrap';
import * as allservices from "../AllServices";


export const DashBoard = () => {

    const [studentData, setStudentData] = useState([]);
    //    const loginstudentId = localStorage.getItem("student_id");

    useEffect(() => {
        StudentCard();
    }, []);

    const StudentCard = async () => {
        const studentId = localStorage.getItem("student_id");
        const res = await allservices.getStudent(studentId);
        setStudentData(res);
    }

    // // Filter student data to show only the card for the logged-in student
    // const filteredStudentData = studentData.filter((student) => student.id === loginstudentId);

    return (
        <>
            {
                studentData.map((d, k) => (
                    <Card key={k} style={{ width: '30rem' }}>
                        <Card.Body>
                            <Card.Title>Student Card</Card.Title>
                            <Card.Text>
                                <strong>Student Name:</strong> {d.student_name}
                            </Card.Text>
                            <Card.Text>
                                <strong>Student Code :</strong> {d.student_code}
                            </Card.Text>
                            <Card.Text>
                                <strong>Mobile Number :</strong> {d.mobile_number}
                            </Card.Text>
                            <Card.Text>
                                <strong>City:</strong> {d.city}
                            </Card.Text>
                            <Card.Text>
                                <strong>Profile Photo:</strong> {d.profile_photo}
                            </Card.Text>
                        </Card.Body>
                    </Card>
                ))}

        </>
    );
};
