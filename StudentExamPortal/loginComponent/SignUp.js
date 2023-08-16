import React, { useRef, useState } from "react";
import * as AllServices from "../AllServices";
import Form from 'react-bootstrap/Form';
import { Button } from "react-bootstrap";
import Card from 'react-bootstrap/Card';
import { useNavigate } from "react-router-dom";
import "../css/signup.css";

export const SignUp = () => {
    const txtname = useRef();
    const txtemail = useRef();
    const txtphone = useRef();
    const txtcity = useRef();
    const [studentcode, setStudentCode] = useState();
    const [file, setFile] = useState([]);

    const navigate = useNavigate();

    const uploadFile = (event) => {
        var e = event.target.files[0];
        console.log(e);
        setFile(e)
    }

    const getNextCode = async () => {
        const code = await AllServices.getNextCode();
        setStudentCode(code);
    };

    const StudentRegister = async () => {
        let name = txtname.current.value;
        let email = txtemail.current.value;
        let phone = txtphone.current.value;
        let city = txtcity.current.value;

        if (name !== "" && email !== "" && phone !== "" && city !== "") {
            let formData = new FormData();
            formData.append("file", file);
            formData.append("student_name", name);
            formData.append("student_code", studentcode);
            formData.append("email_address", email);
            formData.append("mobile_number", phone);
            formData.append("city", city);
            console.log(formData);
            try {
                const add = await AllServices.addStudent(formData);
                console.log(add);
                alert("student Register successfully")
                navigate("/");
            } catch (error) {
                console.error("Error:", error);
            }
        } else {
            alert("Please fill all the information");
        }
    };

    return (
        <div className="background">
            <div className="container">
                <div className="form-container">
                    <Card className="shadow-lg">
                        <Card.Header>
                            <h2>Please Register Here</h2>
                        </Card.Header>
                        <Card.Body>
                            <Form>
                                <div className="row">
                                    <div className="col-md-12">
                                        <Form.Group>
                                            <Form.Label>Name</Form.Label>
                                            <Form.Control type="text" placeholder="Enter Name" required ref={txtname} />
                                        </Form.Group>
                                    </div>
                                    <div className="col-md-11">
                                        <Form.Group>
                                            <Form.Label>Email</Form.Label>
                                            <Form.Control type="text" placeholder="Email" required ref={txtemail} />
                                        </Form.Group>
                                    </div>
                                    <div className="col-md-6">
                                        <Form.Group>
                                            <Form.Label>Phone</Form.Label>
                                            <Form.Control type="text" placeholder="Phone Number" required ref={txtphone} />
                                        </Form.Group>
                                    </div>
                                    <div className="col-md-6">
                                        <Form.Group>
                                            <Form.Label>Profile Photo</Form.Label>
                                            <Form.Control type="file" onChange={uploadFile} />
                                        </Form.Group>
                                    </div>
                                    <div className="col-md-6">
                                        <Form.Group>
                                            <Form.Label>City</Form.Label>
                                            <Form.Control type="text" placeholder="City" required ref={txtcity} />
                                        </Form.Group>
                                    </div>
                                </div>
                            </Form>
                        </Card.Body>
                        <Card.Footer>
                            <Button variant="primary" type="submit" onClick={StudentRegister}>Submit</Button>
                            <Button variant="link" type="submit" onClick={() => navigate("/")}>Back to login</Button>
                        </Card.Footer>
                    </Card>
                </div>

            </div>
        </div>
    );
};
