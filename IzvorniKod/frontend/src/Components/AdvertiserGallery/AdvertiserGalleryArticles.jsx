/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import 'bootstrap/dist/css/bootstrap.min.css'
import Container from 'react-bootstrap/Container'
import AdvertiserGalleryCard from './AdvertiserGalleryCard';

function AdvertiserGalleryArticles({advertiser, logo}){
    return(
        <Container className='advertiser-gallery-card-container d-flex flex-column align-items-center mt-3'>
            <p className='advertiser-header fw-bold pb-3'>
            {logo}{advertiser} &apos;s gallery
            </p>

            <Container className='mb-3'>
                <AdvertiserGalleryCard advertiser={advertiser}></AdvertiserGalleryCard>
            </Container>
        </Container>
    );
}

export default AdvertiserGalleryArticles