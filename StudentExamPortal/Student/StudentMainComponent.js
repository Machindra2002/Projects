import React, { useState } from "react";
import { ListGroup } from "react-bootstrap";
import { Link, Outlet } from "react-router-dom";


export const StudentMain=()=>{

    const [divstyle] = useState({ color: "white", fontFamily: "cursive", fontSize: "20px", marginLeft: "10px" })
    const menuitem = [
        {
            path: "dashboard",
            name: 'DashBoard'
        },
        {
            path: "exam",
            name: 'Exam'
        },
        {
            path: "profile",
            name: 'Profile'
        }
    ]

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <a href="#" className="navbar-brand">CIIT</a>
                    <div className="navbar-nav ms-auto">
                        <a href="#" class="nav-item nav-link">Login</a>

                    </div>
                </div>
            </nav>
            <hr />
            <div className="row">
                <div className="col-md-3 bg-dark">
                    <div className="container" style={{ height: "100vh" }}>
                        <h3 style={divstyle}>Student</h3>
                        <ListGroup variant="flush">
                            {menuitem.map((d, k) => (
                                <ListGroup.Item>
                                    <Link to={d.path} key={k}>{d.name}</Link>
                                </ListGroup.Item>
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