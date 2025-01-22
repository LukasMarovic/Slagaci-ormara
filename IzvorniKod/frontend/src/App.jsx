import { useState } from 'react';
import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './Components/HomePage';
import Login from './Components/Login';
import Register from './Components/Register';
import RegisteredUserPage from './Components/RegisteredUser/RegisteredUserPage';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login></Login>} />
          <Route path="/register" element={<Register></Register>} />
          <Route path="/registered_user" element={<RegisteredUserPage/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App