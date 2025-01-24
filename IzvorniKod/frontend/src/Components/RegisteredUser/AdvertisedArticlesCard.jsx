/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { useEffect } from 'react';
import { Link } from 'react-router-dom';

function AdvertisedArticlesCard() {
  const [advertisements, setAdvertisements] = useState([/*
    { title: "Article 17", description: "Description 17", author: "100", image: "/public/vite.svg", advertised: true, logo: "/public/vite.svg" },
    { title: "Article 18", description: "Description 18", author: "101", image: "/public/vite.svg", advertised: true, logo: "/public/vite.svg" },
    { title: "Article 19", description: "Description 19", author: "102", image: "/public/vite.svg", advertised: true, logo: "/public/vite.svg" },
    { title: "Article 20", description: "Description 20", author: "103", image: "/public/vite.svg", advertised: true, logo: "/public/vite.svg" },
  */]);

  useEffect(() => {
    fetch("/api/sellerArticles")
      .then(response => response.json())
      .then(data => {setAdvertisements(data);
      });
  }, []);

  return (
    <Row xs={1} sm={2} md={2} lg={4} className="advertised-cards-container g-3">
      {advertisements.map((artikl, idx) => (
        <Col key={idx}>
          <Card className="article-card position-relative">
            <Card.Img variant="top" src={artikl.image} className="card-image" />
            <Card.Body className="card-body">
              <Card.Title className="card-title fw-bold">{artikl.title}</Card.Title>
              <Card.Text className="card-text">
                {artikl.description}<br />{artikl.author}
              </Card.Text>
              <Card.Text className="card-text">
                Posted by: {artikl.author}
              </Card.Text>
            </Card.Body>
            {artikl.advertised && (
              <Link to={`/advertiser_gallery/${artikl.author}`}>
                <img src={artikl.logo} alt="advertised logo" className="advertised-logo position-absolute bottom-0 end-0 m-3" />
              </Link>
            )}
          </Card>
        </Col>
      ))}
    </Row>
  );
}

export default AdvertisedArticlesCard;
