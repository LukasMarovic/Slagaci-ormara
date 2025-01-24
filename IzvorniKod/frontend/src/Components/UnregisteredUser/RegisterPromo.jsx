import 'bootstrap/dist/css/bootstrap.min.css';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container'

function RegisterPromo(){
    return(
        <Container className="register-promo-container d-flex flex-column align-items-center">
            <p className='register-title fw-bold'>
                Register To Explore All Features
            </p>
            <p className='register-undertitle'>
                Unlock the full capabilities of our app!
            </p>
            <Button className='register-button p-3 my-4 fw-bold' href='/register'>
                Register
            </Button>
        </Container>
    );
}

export default RegisterPromo