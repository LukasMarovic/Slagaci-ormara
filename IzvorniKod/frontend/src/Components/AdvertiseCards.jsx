import React, { useState } from 'react';
import { Col, Button } from 'react-bootstrap';
import './CustomCss/NavigationButton.css';

const images = [
    "/assets/images/plcholder1.jpg",
    "/assets/images/plcholder2.jpg",
    "/assets/images/plcholder3.jpg",
    "/assets/images/plcholder4.jpg",
];

function AdvertiseCards() {
    const [currentIndex, setCurrentIndex] = useState(0);
    const [fade, setFade] = useState(true);

    const handleRightClick = () => {
        setFade(false);
        setTimeout(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
            setFade(true);
        }, 300);
    };

    const handleLeftClick = () => {
        setFade(false);
        setTimeout(() => {
            setCurrentIndex((prevIndex) => (prevIndex - 1 + images.length) % images.length);
            setFade(true);
        }, 300);
    };

    
    const visibleImages = [
        images[currentIndex],
        images[(currentIndex + 1) % images.length],
        images[(currentIndex + 2) % images.length],
    ];

    return (
        <Col md={7} className="carousel-col" style={{ padding: 0, height: '100%', display: 'flex' }}>
            
            <div className="adv-first-column">
                <img
                    src={visibleImages[0]}
                    alt="First Image"
                    className={`adv-image1 ${fade ? 'fade-in' : 'fade-out'}`}
                />
               
                <div className="overlay">
                    <div className="overlay-content">
                        <span className="thick-arrow">Lorem <br />ipsum</span>
                    </div>
                    <Button className="overlay-button">    
                        <svg
                            className="custom-arrow"
                            width="24"
                            height="24"
                            viewBox="0 0 24 24"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                        >
                        <path
                        d="M5 12h14M13 6l6 6-6 6"
                        stroke="currentColor"
                        strokeWidth="1.7" 
                        strokeLinecap="round"
                        strokeLinejoin="round"/>  </svg>
                    </Button>

                </div>
                <Button className="navigation-button left" onClick={handleLeftClick}>
                    &#8249;
                </Button>
            </div>


            <div className="adv-second-column">
                <div className="second-column-image">
                    <img
                        src={visibleImages[1]}
                        alt="Second Image"
                        className={`adv-image2 ${fade ? 'fade-in' : 'fade-out'}`}
                    />
                </div>
                <div className="second-column-buttons">
                    {images.map((_, index) => (
                        <Button
                            key={index}
                            className={`custom-button ${index === currentIndex ? 'active' : ''}`}
                            variant="outline-secondary"
                            disabled
                        />
                    ))}
                </div>
            </div>

            <div className="adv-third-column">
                <img
                    src={visibleImages[2]}
                    alt="Third Image"
                    className={`adv-image3 ${fade ? 'fade-in' : 'fade-out'}`}
                />
            </div>

            <div className="button-wrapper">
                <Button className="navigation-button right" onClick={handleRightClick}>
                    &#8250;
                </Button>
            </div>
        </Col>
    );
}

export default AdvertiseCards;