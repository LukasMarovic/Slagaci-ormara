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
      fetch("http://localhost:8080/api/getFeatured")
        .then(response => response.json())
        .then(data => setArticles(data))
    }, []);
    return(
       <Row xs={1} sm={2} md={3} lg={4} className="card-container g-3">
       {articles.map((artikl, idx) => (
         <Col key={idx}>
           <Card className='article-card'>
             <Card.Img variant="top" src="/src/assets/images/react.svg" className='card-image' />
             <Card.Body className='card-body'>
               <Card.Title className='card-title'>{artikl.nazivArtikla}</Card.Title>
               <Card.Text className='card-text'>
                 This is a longer card with supporting text below as a natural
                 lead-in to additional content. This content is a little bit
                 longer.
               </Card.Text>
             </Card.Body>
           </Card>
         </Col>
       ))}
    </Row>
  );
}

export default ArticleCard