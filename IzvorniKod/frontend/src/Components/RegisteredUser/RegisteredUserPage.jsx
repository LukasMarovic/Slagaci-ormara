import HeaderReg from "./HeaderReg";
import SearchBarReg from "./SearchBarReg";
import AdvertisedArticles from "./AdvertisedArticles"
import Closets from "./Closets";
import FooterReg from "./FooterReg";
import Recommendation from "./Recommendation";

import { Navigate } from 'react-router-dom'
import React, { useState, useEffect } from 'react';

function RegisteredUserPage(){
    const [isLoggedIn, setIsLoggedIn] = useState(null);
    
    useEffect(() => {
        const fetchUserDetails = async () => {
            try {
                const response = await fetch('/api/auth/checkLogin', {
                    method: 'GET',
                    credentials: 'include',
                });
                setIsLoggedIn(response.ok);
                console.log(isLoggedIn);
            } catch (error) {
                console.error('Error:', error);
            }
        };

        fetchUserDetails();
    }, []);

    if (isLoggedIn === null) {
        return <div>Loading...</div>
    }

    if (isLoggedIn == false) {
        return <Navigate to="/login"/>
    }

    return(
        <>
            <HeaderReg></HeaderReg>
            <SearchBarReg></SearchBarReg>
            <AdvertisedArticles></AdvertisedArticles>
            <Closets></Closets>
            <Recommendation></Recommendation>
            <FooterReg></FooterReg>
        </>
    );
}

export default RegisteredUserPage;