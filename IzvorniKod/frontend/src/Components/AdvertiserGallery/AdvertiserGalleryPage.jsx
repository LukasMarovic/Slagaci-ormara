/* eslint-disable no-unused-vars */
import { useParams } from 'react-router-dom'; // Import useParams from react-router-dom
import 'bootstrap/dist/css/bootstrap.min.css';
import './advertiser_gallery_css/style.css';
import HeaderGall from './HeaderGall';
import FooterGall from './FooterGall';
import AdvertiserGalleryArticles from './AdvertiserGalleryArticles';

function AdvertiserGalleryPage() {
    const { id } = useParams(); 
    console.log(id) 

    const logo = '';

    return (
        <>
            <HeaderGall />
            <AdvertiserGalleryArticles advertiser={id} logo={logo}/>
            <FooterGall />
        </>
    );
}

export default AdvertiserGalleryPage;
