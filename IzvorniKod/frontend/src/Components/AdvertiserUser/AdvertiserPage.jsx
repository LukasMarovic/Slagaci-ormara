import 'bootstrap/dist/css/bootstrap.min.css'
import './advertiser_user_css/styles.css'
import HeaderAdv from './HeaderAdv.';
import UserAdvertisedArticles from './UserAdvertisedArticles';
import FooterAdv from './FooterAdv';
import { useState, useEffect} from 'react'
import { Navigate } from 'react-router-dom'

function AdvertiserPage(){
    const [isLoggedIn, setIsLoggedIn] = useState(null);
        
        useEffect(() => {
            const fetchUserDetails = async () => {
                try {
                    const response = await fetch('/api/auth/checkLogin', {
                        method: 'GET',
                        credentials: 'include',
                    });
                    setIsLoggedIn(response.ok);
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
            <HeaderAdv></HeaderAdv>
            <UserAdvertisedArticles></UserAdvertisedArticles>
            <FooterAdv></FooterAdv>
        </>
    );
}

export default AdvertiserPage