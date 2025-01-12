import 'bootstrap/dist/css/bootstrap.min.css'
import Container from 'react-bootstrap/Container'
import AdvertisedArticlesCard from './AdvertisedArticlesCard'

function AdvertisedArticles(){
    return(
        <>
            <Container className='advertised-articles d-flex flex-column align-items-center mb-5 mt-5'>
                <p className='advertised-articles-title fw-bold'>
                    Advertised Articles 
                </p>

                <p className='advertised-articles-undertitle pb-4'>
                    Take a look at what other people are selling!
                </p>

                <Container className='p-0'>
                    <AdvertisedArticlesCard></AdvertisedArticlesCard>
            </Container>
            </Container>
        </>
    )
}

export default AdvertisedArticles