import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Navbar from 'react-bootstrap/Navbar'
import NavbarBrand from 'react-bootstrap/NavbarBrand'
import Nav from 'react-bootstrap/Nav'
import { FaUser } from 'react-icons/fa'

import 'bootstrap/dist/css/bootstrap.min.css'
import { NavLink } from 'react-bootstrap'

function Header(){
    return(
        <Navbar className="header" expand="lg">
            <Container className='d-flex justify-content-around'>
            <Navbar.Brand className='d-flex align-items-center fw-bold title' href="#home">
                    {
                    //<img src="src\assets\react.svg" className='logo'></img>
                    }
                    CLOSETLY
                </Navbar.Brand>

            <Navbar.Toggle aria-controls="basic-navbar-nav" />

            <Navbar.Collapse id="basic-navbar-nav">
                
                <Nav className='me-auto'> {/* Align links to the left */}
                    <Nav.Link className='nav-link' href="#home">Home</Nav.Link>
                    <Nav.Link className='nav-link' href="#register">Shop</Nav.Link>
                    <Nav.Link className='nav-link' href="#about">About</Nav.Link>
                    <Nav.Link className='nav-link' href="#footer">Contact</Nav.Link>
                </Nav>
                <Nav>
                    <Nav.Link className='register' href="/login">
                        Register or Log in
                        <FaUser className="user-icon"></FaUser>
                    </Nav.Link>
                </Nav>
            </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header

