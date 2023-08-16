import React, { useRef, useState } from "react";
import { Button, Card, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Login.css";

export const Login = () => {
    const user_name = useRef("");
    const pass = useRef("");
    const [msg, setMessage] = useState("");
    const navigate = useNavigate();

    const CheckLogin = () => {
        const u = user_name.current.value;
        const p = pass.current.value;

        if (u === "admin" && p === "admin@123") {
            navigate("/main");
        }
        else{
            const st = { "student_code": u, "password": p };
            axios
                .post("http://localhost:9090/api/checklogin", st)
                .then((response) => {
                    if (response.data !== "") {
                        const studentId = response.data.student_id;
                        const studentName = response.data.student_name;

                        localStorage.setItem("student_id", studentId);
                        console.log("student_id", studentId);
                        // navigate("/studentmain");
                    } else {
                        setMessage("Invalid Username and password");
                    }
                })
                .catch((error) => {
                    console.error("Error:", error);
                });
        }
    };

    return (
        <div>
            <Container className="container">
                <Card className="card shadow">
                    <Card.Header>WELCOME</Card.Header>
                    <Card.Body>
                        <p>Please enter your login Id and password!</p>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label className="form-label text-center">USER ID</Form.Label>
                                <Form.Control type="text" ref={user_name} placeholder="Enter email" />
                            </Form.Group>

                            <Form.Group className="mb-3">
                                <Form.Label className="form-label text-center">PASSWORD</Form.Label>
                                <Form.Control type="password" ref={pass} placeholder="Password" />
                            </Form.Group>
                        </Form>
                    </Card.Body>
                    <Card.Footer>
                        <Button variant="primary" onClick={() => CheckLogin()} className="btn-primary">
                            LogIn
                        </Button>
                        <h5>
                            Not an Account. <span className="link" onClick={() => navigate("/signup")}>Register here</span>
                        </h5>
                    </Card.Footer>
                </Card>
            </Container>
        </div>
    );
};
