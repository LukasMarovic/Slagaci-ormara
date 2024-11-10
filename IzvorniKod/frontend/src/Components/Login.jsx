import { Card, Form, Button, Container, Row, Col } from 'react-bootstrap';
import React, { useState } from 'react';
import { FaGoogle, FaFacebook, FaTwitter } from 'react-icons/fa'; 
import 'bootstrap/dist/css/bootstrap.min.css';


function Login(){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
  
    const handleLogin = (e) => {
      e.preventDefault();
      console.log("Email:", email);
      console.log("Password:", password);
    };
  
    return (
        <Container fluid className="d-flex justify-content-center align-items-center vh-100"
            style={{backgroundColor:' hsla(32, 60%, 84%, 0.991)'}}>
        <Card style={{ width: '100%', maxWidth: '400px', height:'30rem'}} className="p-4 shadow">
          <Card.Body>
            <h2 className="text-center mb-4">Login</h2>
            <Form onSubmit={handleLogin}>
              <Form.Group controlId="formBasicEmail">
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
  
              <Button variant="primary" type="submit" className="w-100 mt-4">
                Login
              </Button>
            </Form>
            <div className="text-center mt-3">
            <p>or login with:</p>
            <div className="d-flex justify-content-between">
              <Button variant="light" className="flex-fill mx-1" style={{ border: '1px solid #db4437' }}>
                <FaGoogle color="#db4437" /> 
              </Button>
              <Button variant="light" className="flex-fill mx-1" style={{ border: '1px solid #3b5998' }}>
                <FaFacebook color="#3b5998" /> 
              </Button>
              <Button variant="light" className="flex-fill mx-1" style={{ border: '1px solid #1da1f2' }}>
                <FaTwitter color="#1da1f2" /> 
              </Button>
            </div>
          </div>
          </Card.Body>
        </Card>
      </Container>
    );
  };
  


export default Login
