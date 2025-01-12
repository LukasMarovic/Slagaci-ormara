import HeaderReg from "./HeaderReg";
import SearchBarReg from "./SearchBarReg";
import AdvertisedArticles from "./AdvertisedArticles"
import Closets from "./Closets";
import FooterReg from "./FooterReg";
import Recommendation from "./Recommendation";

function RegisteredUserPage(){
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