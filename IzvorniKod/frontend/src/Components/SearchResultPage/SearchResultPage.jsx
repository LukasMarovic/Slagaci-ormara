/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css'
import HeaderAdv from './HeaderSearchRes';
import BodySearchRes from './BodySearchRes';
import FooterSearchRes from '../AdvertiserUser/FooterAdv';
import { useLocation } from 'react-router-dom';


function SearchResultPage(){

    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const searchQuery = queryParams.get('query');
    const owned = queryParams.get('owned');
    console.log(queryParams);


    return(
        <>
            <HeaderAdv></HeaderAdv>
            <BodySearchRes 
            searchQuery={searchQuery}
            owned={owned}
            ></BodySearchRes>
            <FooterSearchRes></FooterSearchRes>
        </>
    );
}

export default SearchResultPage