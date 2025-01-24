import 'bootstrap/dist/css/bootstrap.min.css'
import ExploreAndShareArticles from './FeaturesCards/ExploreAndShareArticles'
import CreateClosets from './FeaturesCards/CreateClosets'
import CustomizedRecommendations from './FeaturesCards/CustomizedRecommendations'
import Container from 'react-bootstrap/Container'


function FeaturesContainer(){
    return(
        <Container className='mt-5'>
            <Container className='d-flex justify-content-end m-0 p-0'>
                <ExploreAndShareArticles className=''></ExploreAndShareArticles>
            </Container>
            <Container className='d-flex justify-content-start m-0 p-0'>
                <CreateClosets></CreateClosets>
            </Container>
            <Container className='d-flex justify-content-end m-0 p-0'>
                <CustomizedRecommendations></CustomizedRecommendations>
            </Container>
        </Container>
    );
}

export default FeaturesContainer