/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import { useState } from 'react';
import Pagination from 'react-bootstrap/Pagination'
import { FaArrowLeft } from 'react-icons/fa';
import { FaArrowRight } from 'react-icons/fa';
import ArticleCardDetails from '../ArticleCardDetails';

function SearchResultCard({searchQuery}) {
    const [results, setResults] = useState([searchQuery
        // { title: "Article 1", description: "Description 1", author: "100", image: "/public/vite.svg" },
        // { title: "Article 2", description: "Description 2", author: "100", image: "/public/vite.svg" },
        // { title: "Article 3", description: "Description 3", author: "100", image: "/public/vite.svg" },
        // { title: "Article 4", description: "Description 4", author: "100", image: "/public/vite.svg" },
        // { title: "Article 5", description: "Description 5", author: "100", image: "/public/vite.svg" },
        // { title: "Article 6", description: "Description 6", author: "100", image: "/public/vite.svg" },
        // { title: "Article 7", description: "Description 7", author: "100", image: "/public/vite.svg" },
        // { title: "Article 8", description: "Description 8", author: "100", image: "/public/vite.svg" },
        // { title: "Article 9", description: "Description 9", author: "100", image: "/public/vite.svg" },
        // { title: "Article 10", description: "Description 10", author: "100", image: "/public/vite.svg" },
        // { title: "Article 11", description: "Description 11", author: "100", image: "/public/vite.svg" },
        // { title: "Article 12", description: "Description 12", author: "100", image: "/public/vite.svg" },
        // { title: "Article 13", description: "Description 13", author: "100", image: "/public/vite.svg" },
        // { title: "Article 14", description: "Description 14", author: "100", image: "/public/vite.svg" },
        // { title: "Article 15", description: "Description 15", author: "100", image: "/public/vite.svg" },
        // { title: "Article 16", description: "Description 16", author: "100", image: "/public/vite.svg" },
        // { title: "Article 17", description: "Description 17", author: "100", image: "/public/vite.svg" },
        // { title: "Article 18", description: "Description 18", author: "100", image: "/public/vite.svg" },
        // { title: "Article 19", description: "Description 19", author: "100", image: "/public/vite.svg" },
        // { title: "Article 20", description: "Description 20", author: "100", image: "/public/vite.svg" },
        // { title: "Article 21", description: "Description 21", author: "100", image: "/public/vite.svg" },
        // { title: "Article 22", description: "Description 22", author: "100", image: "/public/vite.svg" },
        // { title: "Article 23", description: "Description 23", author: "100", image: "/public/vite.svg" },
        // { title: "Article 24", description: "Description 24", author: "100", image: "/public/vite.svg" },
        // { title: "Article 1", description: "Description 1", author: "100", image: "/public/vite.svg" },
        // { title: "Article 2", description: "Description 2", author: "100", image: "/public/vite.svg" },
        // { title: "Article 3", description: "Description 3", author: "100", image: "/public/vite.svg" },
        // { title: "Article 4", description: "Description 4", author: "100", image: "/public/vite.svg" },
        // { title: "Article 5", description: "Description 5", author: "100", image: "/public/vite.svg" },
        // { title: "Article 6", description: "Description 6", author: "100", image: "/public/vite.svg" },
        // { title: "Article 7", description: "Description 7", author: "100", image: "/public/vite.svg" },
        // { title: "Article 8", description: "Description 8", author: "100", image: "/public/vite.svg" },
        // { title: "Article 9", description: "Description 9", author: "100", image: "/public/vite.svg" },
        // { title: "Article 10", description: "Description 10", author: "100", image: "/public/vite.svg" },
        // { title: "Article 11", description: "Description 11", author: "100", image: "/public/vite.svg" },
        // { title: "Article 12", description: "Description 12", author: "100", image: "/public/vite.svg" },
        // { title: "Article 13", description: "Description 13", author: "100", image: "/public/vite.svg" },
        // { title: "Article 14", description: "Description 14", author: "100", image: "/public/vite.svg" },
        // { title: "Article 15", description: "Description 15", author: "100", image: "/public/vite.svg" },
        // { title: "Article 16", description: "Description 16", author: "100", image: "/public/vite.svg" },
        // { title: "Article 17", description: "Description 17", author: "100", image: "/public/vite.svg" },
        // { title: "Article 1", description: "Description 1", author: "100", image: "/public/vite.svg" },
        // { title: "Article 2", description: "Description 2", author: "100", image: "/public/vite.svg" },
        // { title: "Article 3", description: "Description 3", author: "100", image: "/public/vite.svg" },
        // { title: "Article 4", description: "Description 4", author: "100", image: "/public/vite.svg" },
        // { title: "Article 5", description: "Description 5", author: "100", image: "/public/vite.svg" },
        // { title: "Article 6", description: "Description 6", author: "100", image: "/public/vite.svg" },
        // { title: "Article 7", description: "Description 7", author: "100", image: "/public/vite.svg" },
        // { title: "Article 8", description: "Description 8", author: "100", image: "/public/vite.svg" },
        // { title: "Article 9", description: "Description 9", author: "100", image: "/public/vite.svg" },
        // { title: "Article 10", description: "Description 10", author: "100", image: "/public/vite.svg" },
        // { title: "Article 11", description: "Description 11", author: "100", image: "/public/vite.svg" },
        // { title: "Article 12", description: "Description 12", author: "100", image: "/public/vite.svg" },
        // { title: "Article 13", description: "Description 13", author: "100", image: "/public/vite.svg" },
        // { title: "Article 14", description: "Description 14", author: "100", image: "/public/vite.svg" },
        // { title: "Article 15", description: "Description 15", author: "100", image: "/public/vite.svg" },
        // { title: "Article 16", description: "Description 16", author: "100", image: "/public/vite.svg" },
        // { title: "Article 17", description: "Description 17", author: "100", image: "/public/vite.svg" },
    ]);

    const [active, setActive] = useState(1);
    const [selectedArticle, setSelectedArticle] = useState({});
    const [showPreviewBool, setShowPreviewBool] = useState(false);
    const results_per_page = 16;
    const pageCount = Math.ceil(results.length / results_per_page);
    const displayedResults = results.slice((active - 1) * results_per_page, active * results_per_page);

    let pagination_items = [];

    let handleClick = (article) =>{
        setSelectedArticle(article);
        setShowPreviewBool(true);
    }

    if (pageCount <= 5) {
        for (let i = 1; i <= pageCount; i++) {
            pagination_items.push(
                <Pagination.Item 
                    key={i} 
                    active={active === i} 
                    onClick={() => setActive(i)}
                >
                    {i}
                </Pagination.Item>
            );
        }
    } else {
        if (active <= 3) {
            for (let i = 1; i <= 3; i++) {
                pagination_items.push(
                    <Pagination.Item 
                        key={i} 
                        active={active === i} 
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            pagination_items.push(
                <Pagination.Item 
                    key={pageCount} 
                    onClick={() => setActive(pageCount)}
                >
                    {pageCount}
                </Pagination.Item>
            );
        } else if (active >= pageCount - 2) {
            pagination_items.push(
                <Pagination.Item 
                    key={1} 
                    onClick={() => setActive(1)}
                >
                    1
                </Pagination.Item>
            );
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            for (let i = pageCount - 2; i <= pageCount; i++) {
                pagination_items.push(
                    <Pagination.Item 
                        key={i} 
                        active={active === i} 
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
        } else {
            pagination_items.push(
                <Pagination.Item 
                    key={1} 
                    onClick={() => setActive(1)}
                >
                    1
                </Pagination.Item>
            );
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            for (let i = active - 1; i <= active + 1; i++) {
                pagination_items.push(
                    <Pagination.Item 
                        key={i} 
                        active={active === i} 
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
            pagination_items.push(<Pagination.Ellipsis key="ellipsis2" />);
            pagination_items.push(
                <Pagination.Item 
                    key={pageCount} 
                    onClick={() => setActive(pageCount)}
                >
                    {pageCount}
                </Pagination.Item>
            );
        }
    }

    let pagination_leading_component = (
        <>
            <Pagination.First key={"first"} onClick={() => setActive(1)}>
                <FaArrowLeft className='pagination-icon' />
            </Pagination.First>
            <Pagination.Prev key={"prev"} onClick={() => setActive(Math.max(active - 1, 1))}>
                Previous
            </Pagination.Prev>
        </>
    );

    let pagination_trailing_component = (
        <>
            <Pagination.Next key={"next"} onClick={() => setActive(Math.min(active + 1, pageCount))}>
                Next
            </Pagination.Next>
            <Pagination.Last key={"last"} onClick={() => setActive(pageCount)}>
                <FaArrowRight className='pagination-icon' />
            </Pagination.Last>
        </>
    );

    return (
        <>
        <Row xs={1} sm={2} md={3} lg={4} className="card-container g-3">
            {displayedResults[0].map((article, idx) => (
                <Col key={idx}>
                    <Card className="article-card" onClick={() => handleClick(article)}>
                        <Card.Img variant="top" src={article.articlepicture} className="article-card-image" />
                        <Card.Body className="article-card-body">
                            <Card.Title className="article-card-title">{article.articlename}</Card.Title>
                            <Card.Text className="article-card-text">{article.availability}</Card.Text>
                            <Card.Text className="article-card-text">Price: {article.userid}</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            ))}
        </Row>
        <div className='d-flex justify-content-center mt-5'>
                {pageCount !== 0
                    ? pageCount > 2
                        ?   <Pagination>{pagination_leading_component}{pagination_items}{pagination_trailing_component}</Pagination>
                        :    <Pagination>{pagination_items}</Pagination>
                    :  <></>}  
            </div>
            <ArticleCardDetails
            show={showPreviewBool}
            handleClose={() => setShowPreviewBool(false)}
            cardData={selectedArticle}
        >
        </ArticleCardDetails>
        </>
    );
}

export default SearchResultCard;
