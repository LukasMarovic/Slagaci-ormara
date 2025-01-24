import Container from 'react-bootstrap/Container'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'
import { FaUser } from 'react-icons/fa'

import UserProfile from '../UserProfile'

import 'bootstrap/dist/css/bootstrap.min.css'

function HeaderReg(){
    return(
        <Navbar className="header-container" expand="lg">
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
                    <UserProfile />
                </Nav>
            </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default HeaderReg

