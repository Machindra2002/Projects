import React from "react";
import { useState } from "react";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import { Link, Outlet } from "react-router-dom";

export const Main = () => {
    const [divstyle] = useState({ color: "white", fontFamily: "cursive", fontSize: "20px", marginLeft: "10px" })
    const menuitem1 = [
        {
            path: "topic",
            name: 'Topic'
        },
        {
            path: "topiccontent",
            name: 'TopicContent'
        },
        {
            path: "contentquestion",
            name: 'ContentQuestion'
        }
    ]

    const menuitem = [
        {
            path: "dashboard",
            name: 'Dashboard'
        },
        {
            path: "allstudentdetail",
            name: 'AllStudentDetail'
        },
        {
            path: "allexam",
            name: 'AllExam'
        }
    ]
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <a href="#" className="navbar-brand">CIIT</a>
                    <div className="navbar-nav ms-auto">
                        <a href="#" class="nav-item nav-link">LogOut</a>

                    </div>
                </div>
            </nav>
            <hr />
            <div className="row">
                <div className="col-md-3 bg-dark">
                    <div className="" style={{ height: "100vh" }}>
                        <h3 style={divstyle}>Student Section</h3>
                        <ListGroup variant="flush">
                            {menuitem.map((d, k) => (
                                <ListGroup.Item><Link to={d.path} key={k}>{d.name}</Link></ListGroup.Item>
                            ))}
                        </ListGroup>

                        <h3 style={divstyle}>Master Section</h3>

                        <ListGroup variant="flush">
                            {menuitem1.map((item, index) => (
                                <ListGroup.Item><Link to={item.path} key={index}>{item.name}</Link></ListGroup.Item>
                            ))}
                        </ListGroup>
                    </div>
                </div>
                <div className="col-md-9">
                    <Outlet />
                </div>
            </div>
        </div>
    )
}















