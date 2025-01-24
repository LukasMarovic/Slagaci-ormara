import 'bootstrap/dist/css/bootstrap.min.css'
import Container from 'react-bootstrap/Container'
import UserArticleCard from './UserArticleCard';

function UserAdvertisedArticles(){
    return(
        <Container className='user-advertised-articles-container d-flex flex-column align-items-center mt-5'>
            <p className='advertiser-header fw-bold pb-4'>
                My Gallery
            </p>

            <Container className='mb-3'>
                <UserArticleCard></UserArticleCard>
            </Container>
        </Container>
    );
}

export default UserAdvertisedArticles