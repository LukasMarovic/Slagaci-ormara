import 'bootstrap/dist/css/bootstrap.min.css'
import './advertiser_user_css/styles.css'
import HeaderAdv from './HeaderAdv.';
import UserAdvertisedArticles from './UserAdvertisedArticles';
import FooterAdv from './FooterAdv';


function AdvertiserPage(){
    return(
        <>
            <HeaderAdv></HeaderAdv>
            <UserAdvertisedArticles></UserAdvertisedArticles>
            <FooterAdv></FooterAdv>
        </>
    );
}

export default AdvertiserPage