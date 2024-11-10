import React from 'react';
import Container from 'react-bootstrap/Container'
import Button from 'react-bootstrap/Button'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import AdvertiseCards from './AdvertiseCards';

function Advertiser() {
    return (
        <Container className="advertiser-section" fluid style={{ backgroundColor: '#fcf8f3', padding: 0, height: '700px', display: 'flex', flexDirection: 'column' }}>
            <Row className="h-100 align-items-stretch g-0" style={{ flex: 1 }}>
                <Col md={5} className="d-flex flex-column justify-content-center align-items-start"
                    style={{ paddingTop: '30px' }}>
                    <h2 className='header'>Become an <br />Advertiser</h2>
                    <p className="description">Lorem ipsum dolor sit amet,<br /> consectetur adipiscing elit.</p>
                    <Button variant="warning" className="advertiser-button text-white mt-3">
                        Register as Advertiser
                    </Button>
                </Col>
                <AdvertiseCards></AdvertiseCards>
            </Row>
        </Container>
    );
}

export default Advertiser;