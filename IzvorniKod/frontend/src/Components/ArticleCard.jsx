import 'bootstrap/dist/css/bootstrap.min.css'
import { useState } from 'react';
import { Container } from 'react-bootstrap';
import Card from 'react-bootstrap/Card'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import React, {useEffect} from 'react';

function ArticleCard(){
    const [articles, setArticles] = useState([]);

    useEffect(() => {
      fetch("/api/getFeatured")
        .then(response => response.json())
        .then(data => setArticles(data))
    }, []);
    return(
       <Row xs={1} sm={2} md={3} lg={4} className="card-container g-3">
       {articles.map((artikl, idx) => (
         <Col key={idx}>
           <Card className='article-card'>
             <Card.Img variant="top" src={"/api/getImage/" + artikl.sifArtikla} className='card-image' />
             <Card.Body className='card-body'>
               <Card.Title className='card-title'>{artikl.nazivArtikla}</Card.Title>
               <Card.Text className='card-text'>
                 {artikl.opcaKategorija}<br />{artikl.kategorijaLezernosti}<br />{artikl.stanjeArtikla}
               </Card.Text>
               <Card.Text className='card-text'>
                Posted by: {artikl.sifKorisnika}
               </Card.Text>
             </Card.Body>
           </Card>
         </Col>
       ))}
    </Row>
  );
}

export default ArticleCard