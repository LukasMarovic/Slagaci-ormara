import 'bootstrap/dist/css/bootstrap.min.css'
import Container from 'react-bootstrap/Container'
import ArticleCard from './ArticleCard';

function FeaturedArticles(){
    return(
        <Container className='featured-articles d-flex flex-column align-items-center p-0'>
            <p className='featured-articles-title fw-bold'>
                Featured Articles
            </p>
            <p className='featured-articles-undertitle pb-4'>
                Take a look at some of this week's featured articles
            </p>
            <Container className='p-0'>
                <ArticleCard></ArticleCard>
            </Container>
        </Container>
    );
}

export default FeaturedArticles