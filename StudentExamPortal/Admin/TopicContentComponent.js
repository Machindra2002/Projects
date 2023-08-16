import { Button, Modal } from "react-bootstrap";
import React, { useEffect, useRef, useState } from "react";
import { Form } from 'react-bootstrap';
import * as allServices from "../AllServices";

export const TopicContent = () => {
    const t_id = useRef();
    const content_id = useRef();
    const c_name = useRef();
    const tutorialname = useRef();

    const [topics, setTopic] = useState([]);
    const [content, setContent] = useState([]);
    const [show, setShow] = useState(false);
    const [selecteData, setSelectedData] = useState({});
    const [btn, setBtn] = useState(false);

    const handleshow = () => {
        setSelectedData({});
        setShow(true)
    };
    const handleClose = () => { setShow(false) };

    useEffect(function () {
        GetTopic();
        GetContent();
    }, []);

    const GetTopic = async () => {
        const resp = await allServices.getTopic();
        setTopic(resp);
    }

    const GetContent = async () => {
        const response = await allServices.getContent();
        setContent(response);
    }

    const AddContent = async () => {
        var tid = t_id.current.value;
        var cn = c_name.current.value;
        var tutorial = tutorialname.current.value;
        var st = {
            "content_name": cn,
            "tblcontent_tutorial": tutorial,
            "topic": { "topic_id": tid }
        };
        const res = await allServices.addContent(st);
        alert("content added Successfully");
        handleClose();
        GetContent();
        Clear();
    }

    const View = (data) => {
        setSelectedData(data);
        setBtn(true)
    }
    
    const UpdateContent = async () => {
        var st = {
            "content_id": content_id.current.value,
            "content_name": c_name.current.value,
            "tblcontent_tutorial": tutorialname.current.value,
            "topic": {
                "topic_id": t_id.current.value,
            },
        };
        const update = await allServices.updateContent(st);
        alert("Updated content successfully");
        handleClose();
        GetContent();
    };
    const Delete = async (id) => {
        const res = await allServices.deleteContent(id);
        alert("Delete content Successfully");
        GetContent();
    }

    const Clear = () => {
        t_id.current.value = "";
        c_name.current.value = "";
        tutorialname.current.value = "";
    }

    return (
        <div>
            <h3>Content</h3>
            <button className='btn btn-primary' onClick={handleshow}><i className="bi-plus-circle-fill"></i>&nbsp;Add</button>
            <br /> <br />

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Topic Content</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Content Id</Form.Label>
                            <Form.Control ref={content_id} disabled
                                value={selecteData?.content_id}
                                onChange={(data) => {
                                    setSelectedData((prev) => {
                                        return { ...prev, content_id: data.target.value };
                                    });
                                }}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Topic</Form.Label>
                            <Form.Select
                                ref={t_id}
                                value={selecteData?.topic?.topic_id}
                                onChange={(data) => {
                                    setSelectedData((prev) => {
                                        return { ...prev, topic: { topic_id: data.target.value } };
                                    });
                                }}
                            >
                                <option disabled>select topic</option>
                                {topics.map((d, k) => (
                                    <option key={k} value={d.topic_id}> {d.topic_name}</option>
                                ))}
                            </Form.Select>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Content</Form.Label>
                            <Form.Control type="text" ref={c_name} placeholder="Enter Content"
                                value={selecteData?.content_name}
                                onChange={(data) => {
                                    setSelectedData((prev) => {
                                        return { ...prev, content_name: data.target.value };
                                    });
                                }}
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Tutorial Name</Form.Label>
                            <Form.Control type="text" ref={tutorialname} placeholder="Enter tutorial name"
                                value={selecteData?.tblcontent_tutorial}
                                onChange={(data) => {
                                    setSelectedData((prev) => {
                                        return { ...prev, tblcontent_tutorial: data.target.value };
                                    });
                                }}
                            >
                            </Form.Control>
                        </Form.Group>
                    </Form>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="primary" onClick={() => AddContent()}>Save</Button>
                    <Button variant="success" onClick={() => UpdateContent()}>Update</Button>
                    <Button variant="secondary" onClick={() => Clear()}>Reset</Button>
                </Modal.Footer>
            </Modal>

            <table className="table table-bordered table-striped table-hover" size="sm">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Content Name</th>
                        <th>Tutorial Name</th>
                        {/* <th>Topic Name</th> */}
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        content.map((d, k) => (
                            <tr key={k}>
                                <td>{k + 1}</td>
                                <td>{d.content_name}</td>
                                <td>{d.tblcontent_tutorial}</td>
                                {/* <td>{d.topic?.topic_name}</td> */}
                                <td>
                                    <Button variant="warning" onClick={() => { handleshow(); View(d); }} >View</Button>
                                    &nbsp; &nbsp;
                                    <Button variant="danger" onClick={() => Delete(d.content_id)}>Delete</Button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}
