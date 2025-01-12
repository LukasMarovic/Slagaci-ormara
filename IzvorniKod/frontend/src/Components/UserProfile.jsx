import { Button, Nav } from 'react-bootstrap'
import {useNavigate} from 'react-router-dom'
import { FaUser } from 'react-icons/fa'
import React, { useState, useEffect } from 'react';

function UserProfile() {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);

    const signOut = (e) => {
        e.preventDefault();
        fetch("/api/auth/sign-out", {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
            },
            credentials: 'include',
          }).then(response => {
            if (response.status == 200) {
              setUser(null);
              console.log("Logged out!");
              navigate('/')
            } else {
              console.log("Failure!");
            }
          })
    };

    useEffect(() => {
        // console.log(user);
        const fetchUserDetails = async () => {
            if (document.cookie) {
                try {
                    const response = await fetch('/api/user', {
                        method: 'GET',
                        credentials: 'include',
                    });
        
                    if (response.ok) {
                        const data = await response.text();
                        setUser(data);
                    } else {
                        console.error('Failed to fetch user details');
                    }
                } catch (error) {
                    console.error('Error:', error);
                }
            }
        };
    
        fetchUserDetails();
    }, []);

    if (!user) 
    {
        return (
        <Nav.Link className='register' href="/login">
            Register or Log in
            <FaUser className="user-icon"></FaUser>
        </Nav.Link>
        ) 
    }
    else 
    {
        return (
            <div className='nav-link'>
                <p>Welcome back <br></br>{user}</p>
                <Button className='register' onClick={signOut}>
                    Sign-out
                </Button>
            </div>
        )
    }
}

export default UserProfile