import React, { useEffect, useRef, useState } from "react";
import * as AllServices from "../AllServices";
import {
    Button,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    Form,
    FormGroup,
    Label,
    Input,
    Table,
} from "reactstrap";

export const AllStudentDetail = () => {
    const student_id = useRef();
    const student_name = useRef();
    const password = useRef();
    const student_code = useRef();
    const email_address = useRef();
    const mobile_number = useRef();
    const profile_photo = useRef();
    const city = useRef();
    const [students, setStudents] = useState([]);
    const [show, setShow] = useState(false);
    const [selectedData, setSelectedData] = useState({});
    const [btn, setBtn] = useState(false);
    const [nextCode, setNextCode] = useState("");
    const [file,setFile] = useState([]);

    const handleClose = () => {
        setShow(false);
    };

    const uploadFile=(event)=>{
        var e = event.target.files[0];
        console.log(e);
        setFile(e)
    }

    const handleShow = () => {
        setSelectedData({});
        setBtn(false);
        setShow(true);
    };

    useEffect(() => {
        getAllStudents();
        getNextCode();
    }, []);

    const getAllStudents = async () => {
        const resp = await AllServices.getStudent();
        setStudents(resp);
    };

    const getNextCode = async () => {
        const code = await AllServices.getNextCode();
        setNextCode(code);
    };

    const addStudent = async () => {
        let stnm = student_name.current.value;
        let email = email_address.current.value;
        let mn = mobile_number.current.value;
        let cty = city.current.value;
        let code = student_code.current.value;

        let formdata = new FormData();

        formdata.append("file",file);
        formdata.append("student_name",stnm);
        formdata.append("student_code",code);
        formdata.append("email_address",email);
        formdata.append("mobile_number",mn);
        formdata.append("city",cty);
        console.log(formdata);
        const add = await AllServices.addStudent(formdata);
        
        // await AllServices.SendEmail();
        alert("Student added successfully and email sent");
        // handleClose();
        getNextCode();
        getAllStudents();
    };

    const SendEmail=async()=>{
        const resp = await AllServices.sendEmail(email_address.current.value);
    }
    const updateStudent = async () => {
        if (!selectedData) {
            return;
        }

        var st = {
            student_id: student_id.current.value,
            student_name: student_name.current.value,
            password: password.current.value,
            student_code: student_code.current.value,
            email_address: email_address.current.value,
            mobile_no: mobile_number.current.value,
            profile_photo: profile_photo.current.files[0],
            city: city.current.value,
        };
        console.log(st);
        const updated = await AllServices.updataStudent(st);
        alert("Student updated successfully");
        handleClose();
        getAllStudents();
    };

    const view = (data) => {
        if (!data) {
            return;
        }

        setBtn(true);
        setSelectedData(data);
        setShow(true);
    };

    const deleteStudent = async (id) => {
        const res = await AllServices.deleteStudent(id);
        console.log(res);
        alert("Student deleted successfully");
        getAllStudents();
    };

    return (
        <div>
            <h3>All Students</h3>
            <Button color="primary" onClick={handleShow}>
                <i className="bi bi-plus-circle-fill"></i>&nbsp;Add
            </Button>
            <br /> <br />

            <Modal isOpen={show} toggle={handleClose}>
                <ModalHeader toggle={handleClose}>{btn ? "Update" : "Add"} Student</ModalHeader>
                <ModalBody>
                    <Form onSubmit={()=> SendEmail()}>
                        <FormGroup>
                            <Label for="employeeid">Student Id</Label>
                            <Input
                                type="text"
                                id="studentid"
                                disabled
                                innerRef={student_id}
                                defaultValue={selectedData.student_id}
                            />
                        </FormGroup>

                        <FormGroup>
                            <Label for="studentName">Employee Name</Label>
                            <Input
                                type="text"
                                id="studentName"
                                innerRef={student_name}
                                placeholder="Enter Employee Name"
                                defaultValue={selectedData.student_name}
                            />
                        </FormGroup>
                        <FormGroup>
                            <Label for="studentCode">Student Code</Label>
                            <Input
                                type="text"
                                id="studentCode"
                                disabled
                                innerRef={student_code}
                                defaultValue={nextCode.student_code}
                                onChange={(data) => {
                                    setSelectedData((prev) => {
                                        return { ...prev, student_code: data.target.value };
                                    });
                                }}
                            />
                        </FormGroup>

                        <FormGroup>
                            <Label for="emailAddress">Email Address</Label>
                            <Input
                                type="email"
                                id="emailAddress"
                                innerRef={email_address}
                                placeholder="Enter Email Address"
                                defaultValue={selectedData.email_address}
                            />
                        </FormGroup>

                        <FormGroup>
                            <Label for="mobileNo">Mobile No</Label>
                            <Input
                                type="text"
                                id="mobileNo"
                                innerRef={mobile_number}
                                placeholder="Enter Mobile No"
                                defaultValue={selectedData.mobile_no}
                            />
                        </FormGroup>

                        <FormGroup>
                            <Label for="profilePhoto">Profile Photo</Label>
                            <Input type="file" id="profilePhoto" innerRef={profile_photo} onChange={(e)=>uploadFile(e)} />
                        </FormGroup>

                        <FormGroup>
                            <Label for="city">City</Label>
                            <Input
                                type="text"
                                id="city"
                                innerRef={city}
                                placeholder="Enter City"
                                defaultValue={selectedData.city}
                            />
                        </FormGroup>
                    </Form>
                </ModalBody>

                <ModalFooter>
                    {btn ? (
                        <Button color="primary" onClick={updateStudent}>Update</Button>
                    ) : (
                        <Button color="primary" onClick={addStudent}>Add</Button>
                    )}
                    <Button color="secondary" onClick={handleClose}>Close</Button>
                </ModalFooter>
            </Modal>

            <div>

                <div className="table table-bordered table-striped" size="sm">
                    <Table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Code</th>
                                <th>Email</th>
                                <th>Mobile No</th>
                                <th>Profile Photo</th>
                                <th>City</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {students.map((student, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{student.student_name}</td>
                                    <td>{student.student_code}</td>
                                    <td>{student.email_address}</td>
                                    <td>{student.mobile_number}</td>
                                    <td>{student.profile_photo}</td>
                                    <td>{student.city}</td>
                                    <td>
                                        <Button color="primary" onClick={() => view(student)}>View</Button>
                                        <Button color="danger" onClick={() => deleteStudent(student.student_id)}>Delete</Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            </div>
        </div>
    );
};