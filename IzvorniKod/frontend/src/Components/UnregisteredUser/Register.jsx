import { Card, Form, Button, Container, Row, Col } from 'react-bootstrap';
import React, { useState } from 'react';
import {useNavigate} from 'react-router-dom'
import { FaGoogle, FaFacebook, FaTwitter } from 'react-icons/fa'; 
import { FaArrowCircleLeft } from 'react-icons/fa';
import 'bootstrap/dist/css/bootstrap.min.css';


function Register(){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [f_name, setF_Name] = useState('');
    const [l_name, setL_Name] = useState('');
    const [city, setCity] = useState('');

    const navigate = useNavigate();
  
    const handleLogin = (e) => {
      e.preventDefault();
      const formData = new FormData();
      formData.append("username", f_name + " " + l_name);
      formData.append("email", email);
      formData.append("password", password);
      formData.append("geolocation", city);
      formData.append("role", "registereduser");
      formData.append("image", null);
      fetch("/api/addUser", {
        method: 'POST',
        body: formData,
        credentials: 'include'
      }).then(() => navigate("/"))
    };
  
    return (
      <>
      <Container
fluid
className="login-container d-flex flex-column justify-content-center align-items-center vh-100"
style={{
  backgroundColor: 'hsla(32, 60%, 84%, 0.991)',
  overflow: 'hidden', // Prevents children from spilling out of the viewport
}}
>
<Card
  style={{
    width: '100%',
    maxWidth: '400px', // Restricts the card width to 400px for large screens
    maxHeight: '90vh', // Ensures the card stays within the viewport height
    margin: '1rem', // Adds space around the card for better responsiveness
    overflowY: 'auto', // Enables internal scrolling if the content overflows
  }}
  className="p-4 shadow"
>
  <Card.Body>
    <a href='/'>
      <FaArrowCircleLeft className='back-arrow'></FaArrowCircleLeft>
    </a>
    <h2 className="text-center mb-4">Sign up</h2>
    <Form onSubmit={handleLogin}>
      <Form.Group controlId="formBasicEmail">
        <Form.Label>First Name</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter your first name"
          value={f_name}
          onChange={(e) => setF_Name(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formBasicEmail" className="mt-3">
        <Form.Label>Last Name</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter your last name"
          value={l_name}
          onChange={(e) => setL_Name(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formBasicEmail" className="mt-3">
        <Form.Label>Grad</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter your city"
          value={city}
          onChange={(e) => setCity(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formBasicEmail" className="mt-3">
        <Form.Label>Email address</Form.Label>
        <Form.Control
          type="email"
          placeholder="Enter email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group controlId="formBasicPassword" className="mt-3">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </Form.Group>

      <Button type="submit" className="login-button w-100 mt-4">
        Sign up
      </Button>
    </Form>
    <div className="text-center mt-3">
      <div className="no-account m-3">
        Already have an account? <br />
        <a href="/login" className="register-login-link">
          Login
        </a>
      </div>
    </div>
  </Card.Body>
</Card>
</Container>
    </>
  );
};
  


export default Register
