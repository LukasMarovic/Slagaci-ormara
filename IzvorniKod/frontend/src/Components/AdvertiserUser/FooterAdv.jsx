import 'bootstrap/dist/css/bootstrap.min.css'
import Container from "react-bootstrap/Container";

function FooterSearchRes(){
    return(
        <>
        <Container className="footer-container">
                <div className="footer-info d-flex justify-content-between pt-5">
                    <div className="footer-left">
                        <span className='title'>CLOSETLY. <br/></span>
                        <br/>
                        <br/>
                        <span className='info'>
                            Unska 3 <br/>
                            10000 Zagreb <br/>
                            Croatia <br/>
                        </span>
                    </div>
                    <div className="footer-right">
                        <br/>
                        <br/>
                        <span>
                            Home <br/>
                            <br/>
                            Shop <br/>
                            <br/>
                            About <br/>
                            <br/>
                        </span>
                    </div>
                </div>
        </Container>
        <Container>
            <div className="footer-copyright">
                    2024 Closetly. All rights reserved for original content. Images courtesy of Unsplash and Freepik.
                </div>
        </Container>
        </>
    );
}

export default FooterSearchRes
